package com.mod.immortal.common.item;

import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.common.item.material.ItemMaterialMaker;
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


@Mod.EventBusSubscriber(modid = MakeMeImmortal.MODID)
public class ItemLoader {

    public static final Item DREAM_GLASS = new ItemDreamGlass();
    public static final Item MATERIAL_MAKER = new ItemMaterialMaker();
    public static final Item TWINE = new ItemTwine();
    	
    private static Item[] items = {
       	DREAM_GLASS,
       	MATERIAL_MAKER,
       	TWINE
    };

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> itemReg = event.getRegistry();
        for(Item item : items){
            ModelLoader.setCustomModelResourceLocation(item,0,new ModelResourceLocation(item.getRegistryName(), "inventory"));
        	itemReg.register(item);
        }
    }
}
