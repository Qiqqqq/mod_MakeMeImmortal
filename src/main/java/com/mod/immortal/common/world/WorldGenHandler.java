package com.mod.immortal.common.world;

import java.util.Random;

import com.mod.immortal.MakeMeImmortal;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = MakeMeImmortal.MODID)
public class WorldGenHandler {

    @SubscribeEvent
    public static void onPopulateChunkPost(PopulateChunkEvent.Pre event){
    	World world = event.getWorld();
        int x = event.getChunkX();
        int z = event.getChunkZ();
        BlockPos blockpos = new BlockPos((x + 1) << 4, 0, (z + 1) << 4);
        Biome biome = world.getBiome(blockpos);
        if (biome instanceof MyBiome) {
        	
//	        ChunkPos chunkpos = new ChunkPos(x, z);
//	    	villageGenerator.generateStructure(world, event.getRand(), chunkpos);

        }
        
    }

	
    @SubscribeEvent
    public static void onPopulateChunkPost(PopulateChunkEvent.Post event){
    	World world = event.getWorld();
    	Random rand = event.getRand();
        if (net.minecraftforge.event.terraingen.TerrainGen.populate(event.getGen(), world, rand, event.getChunkX(), event.getChunkZ(), true, PopulateChunkEvent.Populate.EventType.CUSTOM))
        {
        	if (event.isHasVillageGenerated()) {
                int coordx = event.getChunkX() * 16 + 8;
                int coordz = event.getChunkZ() * 16 + 8;

                BlockPos position = event.getWorld().getHeight(new BlockPos(coordx, 0, coordz));
                position = position.down(rand.nextInt(6) + 16);
                WorldGenRuins.INSTANCE.generate(world, rand, position);
            }
        }
    	
    }
    
}
