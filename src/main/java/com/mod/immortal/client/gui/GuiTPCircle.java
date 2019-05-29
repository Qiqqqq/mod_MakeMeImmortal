package com.mod.immortal.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.SlotItemHandler;

import java.io.IOException;
import java.util.List;

import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.common.block.BlockLoader;
import com.mod.immortal.common.lib.GuiIDs;
import com.mod.immortal.common.network.NetworkManager;
import com.mod.immortal.common.network.PacketImmortalMsg;
import com.mod.immortal.common.network.PacketTeleportMod;
import com.mod.immortal.common.world.ImmortalWorldSavedData;

@SideOnly(Side.CLIENT)
public class GuiTPCircle extends GuiScreen {
	
	ImmortalWorldSavedData data = null;
	
	public void initGui() {
		
		this.buttonList.clear();

		data = ImmortalWorldSavedData.get(this.mc.world);
		for (int i=0; i < data.size(); i++) {
		    this.buttonList.add(new GuiButton(i, this.width / 2 - 100, this.height / 4 + 40 + 20 * i, I18n.format(data.getName(i))));
		}
	
	}
    
    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException
    {
    	if (button.id < data.size()) {
    		this.teleport(data.getPosition(button.id));
    		this.mc.displayGuiScreen((GuiScreen)null);
    	}
    	
//        switch (button.id)
//        {
//            case 0:
//                System.out.println("button.id 0");
//            	this.teleport();
//                this.mc.displayGuiScreen((GuiScreen)null);
//                break;=
//        }
        
    }
    
    
    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, I18n.format("menu.game"), this.width / 2, 40, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    protected void teleport(Vec3i pos) { 

    	PacketTeleportMod msg = new PacketTeleportMod();
		msg.nbt = new NBTTagCompound();
		msg.nbt.setInteger("x", pos.getX());
		msg.nbt.setInteger("y", pos.getY());
		msg.nbt.setInteger("z", pos.getZ());
		
		NetworkManager.instance.sendToServer(msg);
    }
    
}