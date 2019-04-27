package com.mod.immortal.common.block.material;

import com.mod.immortal.common.block.BlockMod;
import com.mod.immortal.common.lib.BlockNames;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockMaterialStone extends BlockMod {
	
    public BlockMaterialStone() {
		super(Material.ROCK, BlockNames.MATERIAL_STONE);
		setHardness(2.0F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("Stone", 1);
	}

}
