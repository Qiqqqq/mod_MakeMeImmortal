package com.mod.immortal.common.item;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.common.core.ImmortalCreativeTabs;

public abstract class ItemMod extends Item {

	public ItemMod(String name) {
		setUnlocalizedName(MakeMeImmortal.MODID + ":" + name);
		setRegistryName(new ResourceLocation(MakeMeImmortal.MODID, name));
		setCreativeTab(ImmortalCreativeTabs.TAB_IMMORTAL);
		
	}
	
}