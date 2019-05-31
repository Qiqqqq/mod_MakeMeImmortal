package com.mod.immortal.common.gen;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

import com.mod.immortal.common.block.BlockLoader;

public class OreGenerator implements IWorldGenerator {

  public static OreGenerator INSTANCE = new OreGenerator();

  public WorldGenMinable cobaltGen;
  public WorldGenMinable arditeGen;

  public OreGenerator() {
    cobaltGen = new WorldGenMinable(BlockLoader.MATERIAL_STONE.getDefaultState(),
                                    15,
                                    BlockMatcher.forBlock(Blocks.STONE));

    arditeGen = new WorldGenMinable(BlockLoader.MATERIAL_HERB.getDefaultState(),
                                    15,
                                    BlockMatcher.forBlock(Blocks.STONE));
  }
  
  @Override
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
    if(world.provider instanceof WorldProviderSurface) {
      generateOre(arditeGen, 20, random, chunkX, chunkZ, world);
      generateOre(cobaltGen, 20, random, chunkX, chunkZ, world);
      
    }
  }

  public void generateOre(WorldGenMinable gen, int rate, Random random, int chunkX, int chunkZ, World world) {
    BlockPos pos;
    for(int i = 0; i < rate; i += 2) {
      pos = new BlockPos(chunkX * 16, 32, chunkZ * 16);
      pos = pos.add(random.nextInt(16), random.nextInt(64), random.nextInt(16));
      gen.generate(world, random, pos);

      pos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
      pos = pos.add(random.nextInt(16), random.nextInt(128), random.nextInt(16));
      gen.generate(world, random, pos);
    }
  }
}