package com.mod.immortal.common.item;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import com.mod.immortal.common.lib.ItemNames;

public class ItemTwine extends ItemMod {

    protected Random rand = new Random();
    
	public ItemTwine() {
		super(ItemNames.TWINE);
	}
	
    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }

    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count)
    {
    	if((count & 7) == 0 && this.rand.nextInt(4) == 0) {
    		player.world.addWeatherEffect(
    			new EntityLightningBolt(player.world, player.posX + this.rand.nextGaussian()*4D, player.posY, player.posZ+this.rand.nextGaussian()*4D, false));
    	}
    }
    
    /**
     * Called when the equipped item is right clicked.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
    }


}
