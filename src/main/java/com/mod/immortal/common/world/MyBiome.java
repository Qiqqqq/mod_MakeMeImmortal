package com.mod.immortal.common.world;

import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.common.block.BlockLoader;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

public class MyBiome extends Biome {
	
	public MyBiome(Biome.BiomeProperties properties)
    {
        super(properties);
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.topBlock = BlockLoader.MATERIAL_STONE.getDefaultState();
        this.fillerBlock = BlockLoader.MATERIAL_STONE.getDefaultState();
        this.decorator = new MyBiomeDecorator();

        this.setRegistryName(new ResourceLocation(MakeMeImmortal.MODID, "myBiome"));
    }
}