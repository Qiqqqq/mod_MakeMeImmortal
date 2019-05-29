package com.mod.immortal.common.item;

import javax.annotation.Nonnull;

import com.mod.immortal.common.lib.ItemNames;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemFiendCore extends ItemMod  {
	
	public ItemFiendCore() {
		super(ItemNames.FIEND_CORE);
	}

    @Nonnull
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		BlockPos coords = world.getSpawnPoint();
		player.rotationPitch = 0F;
		player.rotationYaw = 0F;
		player.setPositionAndUpdate(coords.getX() + 0.5, coords.getY() + 0.5, coords.getZ() + 0.5);

		while(!world.getCollisionBoxes(player, player.getEntityBoundingBox()).isEmpty())
			player.setPositionAndUpdate(player.posX, player.posY + 1, player.posZ);

		world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.PLAYERS, 1F, 1F);
		stack.shrink(1);
		return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
	}
}