package com.caske2000.caskearmor.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class CustomArmorModel extends ModelBiped
{

    ModelRenderer rightShoulder;
    ModelRenderer rightArm;
    ModelRenderer rightHand;
    ModelRenderer leftShoulder;
    ModelRenderer leftArm;
    ModelRenderer leftHand;

    // Took from Blood Magic mod for testing
    public CustomArmorModel(float f, boolean addChestPiece)
    {
        super(f, 0.0F, 64, 64);
        textureWidth = 64;
        textureHeight = 64;

        rightShoulder = new ModelRenderer(this, 0, 32);
        rightShoulder.addBox(-5F, -3F, -4F, 6, 6, 8);
        rightShoulder.setRotationPoint(0F, 0F, 0F);
        rightShoulder.setTextureSize(64, 64);
        rightShoulder.mirror = true;
        setRotation(rightShoulder, 0F, 0F, 0F);

        rightArm = new ModelRenderer(this, 28, 32);
        rightArm.addBox(-4F, 3F, -4F, 5, 5, 6);
        rightArm.setRotationPoint(0F, 0F, 0F);
        rightArm.setTextureSize(64, 64);
        rightArm.mirror = true;
        setRotation(rightArm, 0F, 0F, 0F);

        rightHand = new ModelRenderer(this, 0, 46);
        rightHand.addBox(-5F, 8F, -5F, 6, 3, 8);
        rightHand.setRotationPoint(0F, 0F, 0F);
        rightHand.setTextureSize(64, 64);
        rightHand.mirror = true;
        setRotation(rightHand, 0F, 0F, 0F);

        leftShoulder = new ModelRenderer(this, 0, 32);
        leftShoulder.addBox(-1F, -3F, -4F, 6, 6, 8);
        rightShoulder.setRotationPoint(0F, 0F, 0F);
        leftShoulder.setTextureSize(64, 64);
        leftShoulder.mirror = true;
        setRotation(leftShoulder, 0F, 0F, 0F);

        leftArm = new ModelRenderer(this, 28, 32);
        leftArm.addBox(-1F, 3F, -3F, 5, 5, 6);
        leftArm.setRotationPoint(0F, 0F, 0F);
        leftArm.setTextureSize(64, 64);
        leftArm.mirror = true;
        setRotation(leftArm, 0F, 0F, 0F);

        leftHand = new ModelRenderer(this, 0, 46);
        leftHand.addBox(-1F, 8F, -4F, 6, 3, 8);
        leftHand.setRotationPoint(0F, 0F, 0F);
        leftHand.setTextureSize(64, 64);
        leftHand.mirror = true;
        setRotation(leftHand, 0F, 0F, 0F);

        this.bipedRightArm.cubeList.clear();
        if (addChestPiece)
        {
            this.bipedRightArm.addChild(rightShoulder);
            this.bipedRightArm.addChild(rightArm);
            this.bipedRightArm.addChild(rightHand);
        }

        this.bipedLeftArm.cubeList.clear();
        if (addChestPiece)
        {
            this.bipedLeftArm.addChild(leftShoulder);
            this.bipedLeftArm.addChild(leftArm);
            this.bipedLeftArm.addChild(leftHand);
        }
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        this.bipedHead.render(f5);
        this.bipedBody.render(f5);
        this.bipedLeftArm.render(f5);
        this.bipedRightArm.render(f5);
        this.bipedLeftLeg.render(f5);
        this.bipedRightLeg.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}
