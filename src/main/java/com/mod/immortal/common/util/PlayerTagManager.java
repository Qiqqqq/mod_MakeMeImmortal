package com.mod.immortal.common.util;

import com.mod.immortal.common.lib.TagNames;
import com.mod.immortal.common.network.PacketImmortalMsg;
import com.mod.immortal.common.network.NetworkManager;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class PlayerTagManager {
	
	public static void setImmortalTagValue(EntityPlayer player, String tag, int value) {
		NBTTagCompound immortalTag = getImmortalTag(player);
		immortalTag.setInteger(tag, value);
		if(!player.world.isRemote) {
			PacketImmortalMsg msg = new PacketImmortalMsg();
			msg.nbt = immortalTag.copy();
			NetworkManager.instance.sendTo(msg, (EntityPlayerMP) player);
		}
	}
	
	public static int getImmortalTagValue(EntityPlayer player, String tag) {
		return getImmortalTag(player).getInteger(tag);
	}
	
	public static boolean addImmortalTagValue(EntityPlayer player, String tag, int value) {
		NBTTagCompound immortalTag = getImmortalTag(player);

		int val0 = immortalTag.getInteger(tag) + value;
		if(val0 < 0 || val0 > 65535) {
			return false;
		} else {
			setImmortalTagValue(player, tag, val0);
			return true;
		}
	}
	
	public static void merge(EntityPlayer player, NBTTagCompound other) {

		getImmortalTag(player).merge(other);
	}
	
	public static NBTTagCompound getImmortalTag(EntityPlayer player) {
		NBTTagCompound playerData = player.getEntityData();
		if(!playerData.hasKey(TagNames.TAG_IMMORTAL)){
			player.getEntityData().setTag(TagNames.TAG_IMMORTAL, new NBTTagCompound());
		}
		return playerData.getCompoundTag(TagNames.TAG_IMMORTAL);
	}
	
}
