package com.mod.immortal;

import com.mod.immortal.common.CommonProxy;
import com.mod.immortal.common.gen.OreGenerator;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = MakeMeImmortal.MODID, name = MakeMeImmortal.NAME, version = MakeMeImmortal.VERSION, acceptedMinecraftVersions = "1.12.2")
public class MakeMeImmortal {
    public static final String MODID = "immortal";
    public static final String NAME = "Make Me Immortal";
    public static final String VERSION = "1.0.0";

    @SidedProxy(clientSide = "com.mod.immortal.client.ClientProxy", serverSide = "com.mod.immortal.common.CommonProxy")
    public static CommonProxy proxy;
    @Mod.Instance(MakeMeImmortal.MODID)
    public static MakeMeImmortal instance;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        proxy.init(event);

        GameRegistry.registerWorldGenerator(OreGenerator.INSTANCE, 0);
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
