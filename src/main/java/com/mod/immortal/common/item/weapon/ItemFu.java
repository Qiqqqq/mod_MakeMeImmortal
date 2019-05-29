package com.mod.immortal.common.item.weapon;

import javax.annotation.Nonnull;

import com.mod.immortal.common.lib.ActionNames;
import com.mod.immortal.common.lib.FiveEleTypes;
import com.mod.immortal.common.util.SpellHelper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemFu extends ItemWeaponMod  {
	
	
	public ItemFu(String name, FiveEleTypes fiveEletype) {
		super(name, fiveEletype);
        this.setMaxDamage(20);
        
	}

	@Nonnull
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return ActionNames.SPELL.parseEnumAction();
	}

    /**
     * Called when the player stops using an Item (stops holding the right mouse button).
     */
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)entityLiving;
            //TODO abstract factory
            int i = this.getMaxItemUseDuration(stack) - timeLeft;
            if (i < 20 || stack.isEmpty()) return;
            if(SpellHelper.castSpell(worldIn, player, fiveEletype, 1) == EnumActionResult.SUCCESS){
            	stack.damageItem(1, player);
            }
        }
    }

	
    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
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
