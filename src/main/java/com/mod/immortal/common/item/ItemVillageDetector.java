package com.mod.immortal.common.item;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import com.mod.immortal.common.lib.ItemNames;

public class ItemVillageDetector extends ItemMod {
    
	public ItemVillageDetector() {
		super(ItemNames.VILLAGE_DETECTOR);
	}
    
    /**
     * Called when the equipped item is right clicked.
     */
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

		if(!worldIn.isRemote) {
	        BlockPos villagePos = ((WorldServer)worldIn).getChunkProvider().getNearestStructurePos(worldIn, "Village", playerIn.getPosition(), false);
	        Minecraft.getMinecraft().player.sendChatMessage(villagePos != null ? villagePos.getX()+","+villagePos.getZ() : "failed");
		}
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }


}
