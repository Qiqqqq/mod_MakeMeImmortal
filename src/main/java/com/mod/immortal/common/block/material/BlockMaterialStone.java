package com.mod.immortal.common.block.material;

import com.mod.immortal.common.block.BlockMod;
import com.mod.immortal.common.util.BlockNames;
import com.mod.immortal.common.util.ItemNames;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockMaterialStone extends BlockMod {
	
    public BlockMaterialStone() {
		super(Material.ROCK, BlockNames.MATERIAL_STONE);
		setHardness(2.0F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("Stone", 1);
	}

}
