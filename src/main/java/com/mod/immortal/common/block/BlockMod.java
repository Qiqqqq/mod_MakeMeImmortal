package com.mod.immortal.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.common.core.ImmortalCreativeTabs;

public abstract class BlockMod extends Block {

	public BlockMod(Material material, String name) {
		super(material);
		setUnlocalizedName(MakeMeImmortal.MODID + ":" + name);
		setRegistryName(new ResourceLocation(MakeMeImmortal.MODID, name));
		setCreativeTab(ImmortalCreativeTabs.TAB_IMMORTAL);
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
        
	}
}
