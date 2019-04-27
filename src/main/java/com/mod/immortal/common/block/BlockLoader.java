package com.mod.immortal.common.block;

import com.mod.immortal.common.block.material.BlockHerb;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.common.block.material.BlockMaterialStone;
import com.mod.immortal.common.lib.BlockNames;

@Mod.EventBusSubscriber(modid = MakeMeImmortal.MODID)
public class BlockLoader {
	
    public static final Block MATERIAL_STONE = new BlockMaterialStone();
    public static final Item IB_MATERIAL_STONE = new ItemBlockMod(MATERIAL_STONE);
    public static final Block MATERIAL_HERB = new BlockHerb();
    
    public static final Block ORE_CYAN = new BlockOreMod(MapColor.CYAN, BlockNames.ORE_CYAN);
    public static final Item IB_ORE_CYAN = new ItemBlockMod(ORE_CYAN);
    public static final Block ORE_RED = new BlockOreMod(MapColor.RED, BlockNames.ORE_RED);
    public static final Item IB_ORE_RED = new ItemBlockMod(ORE_RED);

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> blockReg = event.getRegistry();

    	registerBlock(MATERIAL_STONE, blockReg);
    	registerBlock(MATERIAL_HERB, blockReg);
    	registerBlock(ORE_CYAN, blockReg);
    	registerBlock(ORE_RED, blockReg);
    }

    @SubscribeEvent
    public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> itemReg = event.getRegistry();

        registerItemBlock(IB_MATERIAL_STONE, itemReg);
        registerItemBlock(IB_ORE_CYAN, itemReg);
        registerItemBlock(IB_ORE_RED, itemReg);
    }
    
	private static void registerBlock(Block block, IForgeRegistry<Block> blockReg){
		blockReg.register(block);
	}

	private static void registerItemBlock(Item item, IForgeRegistry<Item> itemReg){
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		itemReg.register(item);
	}
	
}
