package com.mod.immortal.common.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.common.core.ImmortalCreativeTabs;

public abstract class ItemMod extends Item {

	public ItemMod(String name) {
		super();
		setUnlocalizedName(MakeMeImmortal.MODID + ":" + name);
		setRegistryName(new ResourceLocation(MakeMeImmortal.MODID, name));
		setCreativeTab(ImmortalCreativeTabs.TAB_IMMORTAL);
		
	}
	
}