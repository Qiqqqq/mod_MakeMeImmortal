package com.mod.immortal.common.block.material;

import com.mod.immortal.common.block.BlockMod;
import com.mod.immortal.common.util.BlockNames;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockHerb extends BlockMod {
    public BlockHerb() {
        super(Material.PLANTS, BlockNames.MATERIAL_HERB);
        setHardness(2.0F);
        setSoundType(SoundType.PLANT);
        setHarvestLevel("Wood", 0);
    }
}
