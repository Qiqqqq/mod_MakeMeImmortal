package com.mod.immortal.common.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ItemBlockMod extends ItemBlock {

	public ItemBlockMod(Block block) {
		super(block);
		setUnlocalizedName(block.getUnlocalizedName());
		setRegistryName(new ResourceLocation(block.getUnlocalizedName()));
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
        
	}
}
