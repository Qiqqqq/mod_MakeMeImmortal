package com.mod.immortal.common.block;

import com.mod.immortal.MakeMeImmortal;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

public class ItemBlockMod extends ItemBlock {

	public ItemBlockMod(Block block) {
		super(block);
		setUnlocalizedName(block.getUnlocalizedName());
		setRegistryName(new ResourceLocation(block.getUnlocalizedName()));
	}
}
