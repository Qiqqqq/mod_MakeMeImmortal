package com.mod.immortal.common.item;

import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.common.block.BlockLoader;
import com.mod.immortal.common.item.material.ItemMaterialMaker;
import com.mod.immortal.common.util.ItemNames;

import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;


@Mod.EventBusSubscriber(modid = MakeMeImmortal.MODID)
public class ItemLoader {

    public static final Item DREAM_GLASS = new ItemDreamGlass();
    public static final Item MATERIAL_MAKER = new ItemMaterialMaker();
    public static final Item TWINE = new ItemTwine();
    public static final Item HERB = new ItemHerb(0, 0.7F, BlockLoader.MATERIAL_HERB, ItemNames.HERB);

    private static Item[] items = {
            DREAM_GLASS,
            MATERIAL_MAKER,
            TWINE,
            HERB
    };

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> itemReg = event.getRegistry();
        for (Item item : items) {
        	ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
            itemReg.register(item);
        }
    }
}
