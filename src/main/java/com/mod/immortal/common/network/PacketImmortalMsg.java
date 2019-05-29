package com.mod.immortal.common.network;

import com.mod.immortal.common.lib.TagNames;
import com.mod.immortal.common.util.PlayerTagManager;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketImmortalMsg implements IMessage {
	
	public NBTTagCompound nbt;

	public void fromBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		nbt = ByteBufUtils.readTag(buf);
	}

	public void toBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		ByteBufUtils.writeTag(buf, nbt);
	}
	
	
	public static class Handler implements IMessageHandler<PacketImmortalMsg, IMessage> {

		public IMessage onMessage(final PacketImmortalMsg message, MessageContext ctx) {
			if (ctx.side == Side.CLIENT) {
				Minecraft.getMinecraft().addScheduledTask(new Runnable()
				{
					public void run() {
						EntityPlayer player = Minecraft.getMinecraft().player;
						PlayerTagManager.merge(player, message.nbt);
					}
					
				});
				
			}
			return null;
		}
	}

}
