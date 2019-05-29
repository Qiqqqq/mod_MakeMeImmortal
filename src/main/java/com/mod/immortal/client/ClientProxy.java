package com.mod.immortal.client;

import java.lang.reflect.Field;
import java.util.Map;

import com.mod.immortal.client.render.RenderPlayerMod;
import com.mod.immortal.common.CommonProxy;
import com.mod.immortal.common.util.RenderHandler;

import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        
        try {
            RenderManager renderManager = FMLClientHandler.instance().getClient().getRenderManager();
            
            for (Field field : RenderManager.class.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    if ("playerRenderer".equals(field.getName()) || "field_178637_m".equals(field.getName())){
                    	field.set(renderManager, new RenderPlayerMod(renderManager));
                    }
                    if ("skinMap".equals(field.getName()) || "field_178636_l".equals(field.getName())){
                    	@SuppressWarnings("unchecked")
            			Map<String, RenderPlayer> skinMap = (Map<String, RenderPlayer>) field.get(FMLClientHandler.instance().getClient().getRenderManager());
                        skinMap.put("default", new RenderPlayerMod(renderManager, false));
                        skinMap.put("slim", new RenderPlayerMod(renderManager, true));
                    }
                }catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
