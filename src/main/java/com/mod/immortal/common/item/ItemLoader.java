package com.mod.immortal.common.item;

import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.common.item.material.ItemMaterialMaker;
import com.mod.immortal.common.item.material.MaterialType;
import com.mod.immortal.common.util.ItemNames;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;


public class ItemLoader {

    @Mod.EventBusSubscriber(modid = MakeMeImmortal.MODID)
    public static class Registration{

        @SubscribeEvent
        public static void registerMaterialItems(final RegistryEvent.Register<Item> event)
        {
            IForgeRegistry<Item> itemRegistry = event.getRegistry();

            for(MaterialType item : MaterialType.values()){
                ItemMaterialMaker itemMaker = new ItemMaterialMaker(item);
                ModelLoader.setCustomModelResourceLocation(itemMaker,0,new ModelResourceLocation(itemMaker.getRegistryName(), "inventory"));
                itemRegistry.register(itemMaker);
            }
        }
    }
}
