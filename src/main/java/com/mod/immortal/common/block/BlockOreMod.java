package com.mod.immortal.common.block;

import java.util.Random;

import com.mod.immortal.common.item.ItemLoader;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockOreMod extends BlockMod {
	
    public BlockOreMod(String name) {
        this(Material.ROCK.getMaterialMapColor(), name);
    }

    public BlockOreMod(MapColor color, String name) {
        super(Material.ROCK, color, name);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (this == BlockLoader.ORE_CYAN) {
            return ItemLoader.MATERIAL_MAKER;
        } 
        else {
            return Item.getItemFromBlock(this);
        }
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random) {
        return 4 + random.nextInt(5);
    }

    /**
     * Get the quantity dropped based on the given fortune level
     */
    public int quantityDroppedWithBonus(int fortune, Random random) {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState)this.getBlockState().getValidStates().iterator().next(), random, fortune)) {
            int i = random.nextInt(fortune + 2) - 1;

            if (i < 0) {
                i = 0;
            }

            return this.quantityDropped(random) * (i + 1);
        }
        else {
            return this.quantityDropped(random);
        }
    }

    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this)) {
            int i = 0;

            if (this == BlockLoader.ORE_CYAN) {
                i = MathHelper.getInt(rand, 0, 2);
            }

            return i;
        }
        return 0;
    }

}