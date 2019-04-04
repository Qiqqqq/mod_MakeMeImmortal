package com.mod.immortal.common.item;

import com.mod.immortal.MakeMeImmortal;
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
    public static Properties itemProperties;

    @GameRegistry.ObjectHolder(ItemNames.DREAM_GLASS)
    public static Item dreamGlass = new Item();

    @Mod.EventBusSubscriber(modid = MakeMeImmortal.MODID)
    public static class Registration{

        private static Item[] items = {
                dreamGlass.setUnlocalizedName("dreamGlass").setRegistryName("dream_glass").setCreativeTab(CreativeTabs.MATERIALS)
        };

        public static Item getByName(String name){
            for(Item item:items)
                if(item.getUnlocalizedName().equals(name))
                    return item;
            return Items.AIR;
        }
        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event)
        {
            IForgeRegistry<Item> itemRegistry = event.getRegistry();
            for(Item item : items){
                ModelLoader.setCustomModelResourceLocation(item,0,new ModelResourceLocation(item.getRegistryName(), "inventory"));
                itemRegistry.register(item);
            }
        }
    }
}
