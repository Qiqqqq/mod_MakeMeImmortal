package com.mod.immortal.client.render;

import com.mod.immortal.client.model.ModelPlayerMod;

import com.mod.immortal.common.lib.ActionNames;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

public class RenderPlayerMod extends RenderPlayer {

	public RenderPlayerMod(RenderManager renderManager){
		this(renderManager, false);
	}
	
	public RenderPlayerMod(RenderManager renderManager, boolean smallArms){
		super(renderManager, smallArms);
        this.mainModel = new ModelPlayerMod(0.0F, smallArms);
        
    }
	
	@Override
    public void doRender(AbstractClientPlayer clientPlayer, double x, double y, double z, float entityYaw, float partialTicks){
		ModelPlayerMod modelplayer = (ModelPlayerMod)this.getMainModel();
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
    	modelplayer.isCasting = isCastingSpell;
    	modelplayer.castingTime = clientPlayer.getItemInUseMaxCount();
		super.doRender(clientPlayer, x, y, z, entityYaw, partialTicks);
		
	}
    
    
}
