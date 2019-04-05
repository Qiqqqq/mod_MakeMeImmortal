package com.mod.immortal.common.item.material;

import com.mod.immortal.common.core.ImmortalCreativeTabs;
import com.mod.immortal.common.item.ItemMod;
import com.mod.immortal.common.util.ItemNames;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Locale;

public class ItemMaterialMaker extends ItemMod {

    public ItemMaterialMaker() {
		super(ItemNames.MATERIAL_MAKER);
	}

    //right-click
    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        return EnumActionResult.SUCCESS;
    }
    
}
