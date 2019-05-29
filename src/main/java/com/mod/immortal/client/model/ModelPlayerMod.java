package com.mod.immortal.client.model;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelPlayerMod extends ModelPlayer {
	public boolean isCasting = false;
	public int castingTime = 0;
	
	public ModelPlayerMod(float modelSize, boolean smallArms)
    {
        super(modelSize, smallArms);
    }

    @Override
    public void render(Entity entityIn, float f1, float f2, float f3, float f4, float f5, float scale)
    {
        GlStateManager.pushMatrix();
        super.render(entityIn, f1, f2, f3, f4, f5, scale);
        GlStateManager.popMatrix();
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
	    if (this.isCasting) {
	    	float r = castingTime < 20F ? castingTime / 20F : 1F;
	        this.bipedRightArm.rotateAngleY = 0.5F + this.bipedHead.rotateAngleY;
	        this.bipedLeftArm.rotateAngleY = -0.5F + this.bipedHead.rotateAngleY;
	        this.bipedRightArm.rotateAngleX = -((float)Math.PI / (2F - r)) + this.bipedHead.rotateAngleX;
	        this.bipedLeftArm.rotateAngleX = -((float)Math.PI / (2F - r)) + this.bipedHead.rotateAngleX;
	    }
    }

}
