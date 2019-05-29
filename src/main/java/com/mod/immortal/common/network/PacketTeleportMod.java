package com.mod.immortal.common.network;

import java.util.EnumSet;

import com.mod.immortal.common.lib.TagNames;
import com.mod.immortal.common.util.PlayerTagManager;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketTeleportMod implements IMessage {
	
	public NBTTagCompound nbt;

	public void fromBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		nbt = ByteBufUtils.readTag(buf);
	}

	public void toBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		ByteBufUtils.writeTag(buf, nbt);
	}
	
	
	public static class Handler implements IMessageHandler<PacketTeleportMod, IMessage> {

		public IMessage onMessage(final PacketTeleportMod message, final MessageContext ctx) {
			if (ctx.side == Side.SERVER) {
				Minecraft.getMinecraft().addScheduledTask(new Runnable()
				{
					public void run() {
						EntityPlayerMP player = ctx.getServerHandler().player;
				    	BlockPos coords = new BlockPos(message.nbt.getInteger("x"),message.nbt.getInteger("y"),message.nbt.getInteger("z"));
				    	
				    	player.rotationPitch = 0F;
				    	player.rotationYaw = 0F;
				    	player.setPositionAndUpdate(coords.getX() + 0.5, coords.getY() + 1, coords.getZ() + 0.5);
				    	
					}
					
				});
				
			}
			return null;
		}
	}

}
