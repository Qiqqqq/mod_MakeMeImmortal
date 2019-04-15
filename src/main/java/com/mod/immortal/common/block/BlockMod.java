package com.mod.immortal.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.common.core.ImmortalCreativeTabs;

public abstract class BlockMod extends Block {
	public BlockMod(Material material, String name) {
		this(material, material.getMaterialMapColor(), name);
	}
	
	public BlockMod(Material material, MapColor color, String name) {
		super(material, color);
		setUnlocalizedName(MakeMeImmortal.MODID + ":" + name);
		setRegistryName(new ResourceLocation(MakeMeImmortal.MODID, name));
		setCreativeTab(ImmortalCreativeTabs.TAB_IMMORTAL);
		
	}
}
