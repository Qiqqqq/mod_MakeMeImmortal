package com.mod.immortal.common.item.material;

import com.mod.immortal.common.core.ImmortalCreativeTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Locale;

public class ItemMaterialMaker extends Item {
    public final MaterialType type;

    public ItemMaterialMaker(MaterialType type) {
        this.type = type;
        this.setUnlocalizedName(type.unlocalizedName);
        this.setRegistryName(type.registryName);
        this.setCreativeTab(CreativeTabs.MATERIALS);
    }

    //right-click
    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        return EnumActionResult.SUCCESS;
    }
}
