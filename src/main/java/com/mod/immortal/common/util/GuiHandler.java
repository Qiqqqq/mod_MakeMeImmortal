package com.mod.immortal.common.util;

import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.client.gui.ContainerFlowerBag;
import com.mod.immortal.client.gui.GuiFlowerBag;
import com.mod.immortal.client.gui.GuiGuidebook;
import com.mod.immortal.client.gui.GuiTPCircle;
import com.mod.immortal.client.gui.InventoryFlowerBag;
import com.mod.immortal.common.item.ItemLoader;
import com.mod.immortal.common.lib.GuiIDs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;


public class GuiHandler implements IGuiHandler {


	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int handId, int unused1, int unused2) {
		EnumHand hand = handId == 1 ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND;
		ItemStack stack = player.getHeldItem(hand);

		switch(ID) {
			case GuiIDs.FLOWER_BAG :
				if(stack.getItem() == ItemLoader.FLOWER_BAG)
					return new ContainerFlowerBag(player.inventory, new InventoryFlowerBag(stack));
				break;
				
			case GuiIDs.TP_CIRCLE :
				break;
				
			case GuiIDs.GUIDEBOOK :
				break;
			
		}
		return null;
	}
	
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int handId, int unused1, int unused2) {
		EnumHand hand = handId == 1 ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND;
		ItemStack stack = player.getHeldItem(hand);

		switch(ID) {
			case GuiIDs.FLOWER_BAG :
				if(stack.getItem() == ItemLoader.FLOWER_BAG)
					return new GuiFlowerBag(player.inventory, new InventoryFlowerBag(stack));
				break;

			case GuiIDs.TP_CIRCLE :
				if(stack.getItem() == ItemLoader.MATERIAL_MAKER)
					return new GuiTPCircle();
				break;
				
			case GuiIDs.GUIDEBOOK :
				if(stack.getItem() == ItemLoader.BUIDEBOOK)
					return new GuiGuidebook(player, stack);
				break;
		}
		return null;
	}
}
	