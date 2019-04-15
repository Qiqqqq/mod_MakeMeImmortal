package com.mod.immortal.common.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockMod extends ItemBlock {

	public ItemBlockMod(Block block) {
		super(block);
		setUnlocalizedName(block.getUnlocalizedName());
		setRegistryName(block.getRegistryName());
		
	}
}
