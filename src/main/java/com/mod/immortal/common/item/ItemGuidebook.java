package com.mod.immortal.common.item;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWrittenBook;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.common.core.ImmortalCreativeTabs;
import com.mod.immortal.common.lib.GuiIDs;
import com.mod.immortal.common.lib.ItemNames;

public class ItemGuidebook extends ItemMod {
    
	public ItemGuidebook() {
		super(ItemNames.GUIDEBOOK);

	}

    /**
     * Called when the equipped item is right clicked.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.openGui(MakeMeImmortal.instance, GuiIDs.GUIDEBOOK, worldIn, 0, 0, 0);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }

}
