package com.mod.immortal.common.util;


import com.mod.immortal.MakeMeImmortal;
import com.mod.immortal.client.render.RenderCushion;
import com.mod.immortal.common.entity.EntityCushion;
import com.mod.immortal.common.entity.EntityRenderFactory;
import com.mod.immortal.common.lib.ActionNames;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;


@Mod.EventBusSubscriber(modid = MakeMeImmortal.MODID)
public class RenderHandler {

    @SubscribeEvent
    public static void onRenderPlayerPre(RenderPlayerEvent.Pre event){

        if(!(event.getRenderer().getMainModel() instanceof ModelBiped))
            return;

        ModelBiped modelplayer = (ModelBiped)event.getRenderer().getMainModel();
        EntityPlayer clientPlayer = event.getEntityPlayer();

		boolean isCastingSpell = false;
		ItemStack itemstack = clientPlayer.getHeldItemMainhand();
        ItemStack itemstack1 = clientPlayer.getHeldItemOffhand();
        if (!itemstack.isEmpty()){
            if (clientPlayer.getItemInUseCount() > 0){
                EnumAction enumaction = itemstack.getItemUseAction();
                if (enumaction == ActionNames.SPELL.parseEnumAction()){
                	isCastingSpell = true;
                }
            }
        }
        if (!itemstack1.isEmpty()){
            if (clientPlayer.getItemInUseCount() > 0){
                EnumAction enumaction1 = itemstack1.getItemUseAction();
                if (enumaction1 == ActionNames.SPELL.parseEnumAction()){
                	isCastingSpell = true;
                }
            }
        }
    	
	    if (isCastingSpell) {
	        GlStateManager.pushMatrix();
	    	int castingTime = clientPlayer.getItemInUseMaxCount();
	    	float r = castingTime < 20F ? castingTime / 20F : 1F;
	    	modelplayer.bipedRightArm.rotateAngleY = 0.5F + modelplayer.bipedHead.rotateAngleY;
	    	modelplayer.bipedLeftArm.rotateAngleY = -0.5F + modelplayer.bipedHead.rotateAngleY;
	    	modelplayer.bipedRightArm.rotateAngleX = -((float)Math.PI / (2F - r)) + modelplayer.bipedHead.rotateAngleX;
	    	modelplayer.bipedLeftArm.rotateAngleX = -((float)Math.PI / (2F - r)) + modelplayer.bipedHead.rotateAngleX;
	        GlStateManager.popMatrix();
	    }
    }
    
    @SubscribeEvent
    public static void bindEntityRenderer(ModelRegistryEvent event) {
    	RenderingRegistry.registerEntityRenderingHandler(EntityCushion.class, new EntityRenderFactory<EntityCushion>(RenderCushion.class));
    }
    
    
}

