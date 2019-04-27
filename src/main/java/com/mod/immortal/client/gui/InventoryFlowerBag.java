package com.mod.immortal.client.gui;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;

public class InventoryFlowerBag implements IItemHandlerModifiable {

	private final IItemHandlerModifiable bagInv;
	final ItemStack bag;

	public InventoryFlowerBag(ItemStack bag) {
		this.bag = bag;
		bagInv = (IItemHandlerModifiable) bag.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
	}

	public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
		bagInv.setStackInSlot(slot, stack);
	}

	public int getSlots() {
		return bagInv.getSlots();
	}

	@Nonnull
	public ItemStack getStackInSlot(int slot) {
		return bagInv.getStackInSlot(slot);
	}

	@Nonnull
	public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
		return bagInv.insertItem(slot, stack, simulate);
	}

	@Nonnull
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		return bagInv.extractItem(slot, amount, simulate);
	}

	public int getSlotLimit(int slot) {
		return bagInv.getSlotLimit(slot);
	}
}