package com.mod.immortal.common.block.material;

import java.util.Random;

import com.mod.immortal.common.block.BlockMod;
import com.mod.immortal.common.item.ItemLoader;
import com.mod.immortal.common.lib.BlockNames;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockHerb extends BlockMod {
    public BlockHerb() {
        super(Material.PLANTS, BlockNames.MATERIAL_HERB);
        setHardness(2.0F);
        setSoundType(SoundType.PLANT);
        setHarvestLevel("Wood", 0);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ItemLoader.HERB;
    }
    
}
