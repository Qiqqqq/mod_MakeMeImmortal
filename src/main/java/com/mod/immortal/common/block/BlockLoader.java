package com.mod.immortal.common.block;

import com.mod.immortal.common.block.material.BlockHerb;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.common.block.material.BlockMaterialStone;

@Mod.EventBusSubscriber(modid = MakeMeImmortal.MODID)
public class BlockLoader {
    public static final Block MATERIAL_STONE = new BlockMaterialStone();
    public static final Item IB_MATERIAL_STONE = new ItemBlockMod(MATERIAL_STONE);
    public static final Block MATERIAL_HERB = new BlockHerb();

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> blockReg = event.getRegistry();
        blockReg.register(MATERIAL_STONE);
        blockReg.register(MATERIAL_HERB);
        
    }

    @SubscribeEvent
    public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> itemReg = event.getRegistry();
        itemReg.register(IB_MATERIAL_STONE);

    }
}
