package com.mod.immortal.common.block;

import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.common.block.BlockMod;
import com.mod.immortal.common.item.ItemLoader;
import com.mod.immortal.common.lib.BlockNames;
import com.mod.immortal.common.lib.GuiIDs;
import com.mod.immortal.common.world.ImmortalWorldSavedData;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTPCore extends BlockMod {
	
    public BlockTPCore() {
		super(Material.ROCK, BlockNames.TP_CORE);
		setHardness(2.0F);
		setSoundType(SoundType.STONE);
	}

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = playerIn.getHeldItem(hand);

    	ImmortalWorldSavedData.get(worldIn).add(pos, pos.toString());
    	
        if (!itemstack.isEmpty() && (itemstack.getItem() == ItemLoader.MATERIAL_MAKER)) {
            if (!playerIn.capabilities.isCreativeMode) {
                itemstack.shrink(1);
            }
        	playerIn.openGui(MakeMeImmortal.instance, GuiIDs.TP_CIRCLE, worldIn, 0, 0, 0);
            return true;
        }
        else {
            return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
        }
    }
    
}
