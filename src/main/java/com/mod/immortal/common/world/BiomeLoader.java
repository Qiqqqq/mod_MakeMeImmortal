package com.mod.immortal.common.world;

import static net.minecraftforge.common.BiomeDictionary.Type.DRY;
import static net.minecraftforge.common.BiomeDictionary.Type.HOT;
import static net.minecraftforge.common.BiomeDictionary.Type.SANDY;

import com.mod.immortal.MakeMeImmortal;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Biomes;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeBeach;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = MakeMeImmortal.MODID)
public class BiomeLoader {
	
	public static final Biome myBiome = 
			new MyBiome((new Biome.BiomeProperties("MyBiome")).setBaseHeight(0.0F).setHeightVariation(0.025F).setTemperature(0.8F).setRainfall(0.4F));
	
	
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Biome> event) {
        IForgeRegistry<Biome> biomeReg = event.getRegistry();
        biomeReg.register(myBiome);
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(myBiome, 600));
        BiomeManager.addSpawnBiome(myBiome);

        BiomeDictionary.addTypes(myBiome, BiomeDictionary.Type.BEACH, BiomeDictionary.Type.SANDY);
    }
}
