package com.mod.immortal.common.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MyBiomeDecorator extends BiomeDecorator
{   
    
	public MyBiomeDecorator() {
		super();
	    this.treesPerChunk = -999;
	    this.deadBushPerChunk = 0;
	    this.reedsPerChunk = 0;
	    this.cactiPerChunk = 0;
	}
	
	@Override
    public void decorate(World worldIn, Random random, Biome biome, BlockPos pos)
    {
    	super.decorate(worldIn, random, biome, pos);
    	if (random.nextInt(8) == 0) {
	        int j = random.nextInt(16) + 8;
	        int k = random.nextInt(16) + 8;
	        WorldGenTPCircle.INSTANCE.generate(worldIn, random, worldIn.getTopSolidOrLiquidBlock(this.chunkPos.add(j, 0, k)));
    	}
    }
    
    
}