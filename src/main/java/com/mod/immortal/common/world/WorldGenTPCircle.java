package com.mod.immortal.common.world;

import java.util.Random;

import com.mod.immortal.common.block.BlockLoader;

import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTPCircle extends WorldGenerator{
	
	public static WorldGenTPCircle INSTANCE = new WorldGenTPCircle();
	
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		int j = rand.nextInt(2) + 2;
	    int minoffset = -j - 1;
	    int maxoffset = j + 1;
	
	    for (int k3 = minoffset; k3 <= maxoffset; ++k3)
	    {
	        for (int i4 = 3; i4 >= -1; --i4)
	        {
	            for (int k4 = minoffset; k4 <= maxoffset; ++k4)
	            {
	                BlockPos blockpos1 = position.add(k3, i4, k4);
	
	                if (k3 != minoffset && i4 != -1 && k4 != minoffset && k3 != maxoffset && k4 != maxoffset)
	                {
	                	worldIn.setBlockToAir(blockpos1);
	                }
	                else
	                {
	                    worldIn.setBlockState(blockpos1, Blocks.REDSTONE_BLOCK.getDefaultState(), 2);
	                }
	            }
	        }
	    }
        worldIn.setBlockState(position, BlockLoader.TP_CORE.getDefaultState(), 2);
        System.out.println(position.getX()+","+position.getY()+","+position.getZ());
    	
	    return true;
	}
}