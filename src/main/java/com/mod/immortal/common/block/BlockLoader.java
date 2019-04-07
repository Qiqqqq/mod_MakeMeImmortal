package com.mod.immortal.common.block;

import com.mod.immortal.common.block.material.BlockHerb;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.common.block.material.BlockMaterialStone;
import com.mod.immortal.common.item.material.ItemMaterialMaker;

@Mod.EventBusSubscriber(modid = MakeMeImmortal.MODID)
public class BlockLoader {
    public static final Block MATERIAL_STONE = new BlockMaterialStone();
    public static final Block MATERIAL_HERB = new BlockHerb();

    private static Block[] blocks = {
            MATERIAL_STONE,
            MATERIAL_HERB,
    };

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> blockReg = event.getRegistry();
        for (Block block : blocks) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
            blockReg.register(block);
        }
    }

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> itemReg = event.getRegistry();
        for (Block block : blocks) {
            Item itemBlock = new ItemBlockMod(block);
            ModelLoader.setCustomModelResourceLocation(itemBlock, 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
            itemReg.register(itemBlock);
        }
    }
}
