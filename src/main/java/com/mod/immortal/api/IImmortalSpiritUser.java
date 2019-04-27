package com.mod.immortal.api;

import net.minecraft.entity.player.EntityPlayer;


public interface IImmortalSpiritUser {

	public int getSpiritCost();

	public boolean isSpiritEnough(EntityPlayer player);
	
}
