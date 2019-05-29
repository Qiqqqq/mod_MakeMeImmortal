package com.mod.immortal.client.util;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.profiler.Profiler;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.common.lib.TagNames;
import com.mod.immortal.common.util.PlayerTagManager;

@Mod.EventBusSubscriber(modid = MakeMeImmortal.MODID)
public final class HUDHandler {

	private HUDHandler() {}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onDrawScreenPre(RenderGameOverlayEvent.Pre event) {
		Minecraft mc = Minecraft.getMinecraft();
		Profiler profiler = mc.mcProfiler;
        
		if(event.getType() == ElementType.HEALTH) {
			profiler.startSection("immortal-hud");
			String str = String.valueOf(PlayerTagManager.getImmortalTagValue(mc.player, TagNames.TAG_IMMORTAL_SPIRIT));
			int x = event.getResolution().getScaledWidth() / 2 - mc.fontRenderer.getStringWidth(str) / 2;
			int y = event.getResolution().getScaledHeight() / 2 + 10;
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.disableAlpha();

			mc.fontRenderer.drawStringWithShadow(str, x, y, 0xbb9966);

			GlStateManager.enableAlpha();
			GlStateManager.color(1F, 1F, 1F, 1F);
			mc.renderEngine.bindTexture(Gui.ICONS);
			
			profiler.endSection();
		}
		
		
	}
	
}
