//  
//  =====GPL=============================================================
//  This program is free software; you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation; version 2 dated June, 1991.
// 
//  This program is distributed in the hope that it will be useful, 
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
// 
//  You should have received a copy of the GNU General Public License
//  along with this program;  if not, write to the Free Software
//  Foundation, Inc., 675 Mass Ave., Cambridge, MA 02139, USA.
//  =====================================================================
//
package com.reptiles.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

import com.reptiles.common.EntityLace;
import net.minecraft.client.renderer.GlStateManager;

public class ModelLace extends ModelBase {

    public ModelRenderer laceBody;
    public ModelRenderer laceHead;
    public ModelRenderer laceLeg1;
    public ModelRenderer laceLeg2;
    public ModelRenderer laceLeg3;
    public ModelRenderer laceLeg4;
    ModelRenderer laceTail;

    public ModelLace() {
        float yPos = 19F;

        laceBody = new ModelRenderer(this, 21, 16);
        laceBody.addBox(-3F, -2F, -5F, 6, 4, 10);
        laceBody.setRotationPoint(0.0F, yPos, 0.0F);

        laceHead = new ModelRenderer(this, 0, 0);
        laceHead.addBox(-2F, -2F, -6F, 4, 4, 6);
        laceHead.setRotationPoint(0F, yPos, -5F);

        laceLeg1 = new ModelRenderer(this, 56, 1);
        laceLeg1.addBox(-1F, 0F, -1F, 2, 5, 2);
        laceLeg1.setRotationPoint(4F, yPos, -4F);

        laceLeg2 = new ModelRenderer(this, 56, 1);
        laceLeg2.addBox(-1F, 0F, -1F, 2, 5, 2);
        laceLeg2.setRotationPoint(4F, yPos, 4F);

        laceLeg3 = new ModelRenderer(this, 56, 1);
        laceLeg3.mirror = true;
        laceLeg3.addBox(-1F, 0F, -1F, 2, 5, 2);
        laceLeg3.setRotationPoint(-4F, yPos, -4F);

        laceLeg4 = new ModelRenderer(this, 56, 1);
        laceLeg4.mirror = true;
        laceLeg4.addBox(-1F, 0F, -1F, 2, 5, 2);
        laceLeg4.setRotationPoint(-4F, yPos, 4F);

        laceTail = new ModelRenderer(this, 17, 12);
        laceTail.addBox(-1F, -1F, 0F, 2, 2, 18);
        laceTail.setRotationPoint(0F, yPos, 4F);
        laceTail.rotateAngleX = 6.021385919380437F;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);

        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        if (this.isChild) {
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24F * f5, 0.0F);
            laceHead.render(f5);
            laceBody.render(f5);
            laceLeg1.render(f5);
            laceLeg2.render(f5);
            laceLeg3.render(f5);
            laceLeg4.render(f5);
            laceTail.render(f5);
            GlStateManager.popMatrix();
        } else {
            laceBody.render(f5);
            laceHead.render(f5);
            laceLeg1.render(f5);
            laceLeg2.render(f5);
            laceLeg3.render(f5);
            laceLeg4.render(f5);
            laceTail.render(f5);
        }
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        laceHead.rotateAngleX = f4 / 57.29578F;
        laceHead.rotateAngleY = f3 / 57.29578F;

        // laceLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        // laceLeg2.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        // laceLeg3.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        // laceLeg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        // laceTail.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.4F * f1;
        // wag the tail
        laceTail.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.4F * f1;
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entityliving, float f, float f1, float f2) {
        float yPos = 19F;
        laceBody.setRotationPoint(0.0F, yPos, 0.0F);
        laceHead.setRotationPoint(0F, yPos, -5F);
        laceTail.setRotationPoint(0F, yPos, 4F);
        laceTail.rotateAngleX = 6.021385919380437F;

        laceLeg1.setRotationPoint(4F, yPos, -4F);
        laceLeg2.setRotationPoint(4F, yPos, 4F);
        laceLeg3.setRotationPoint(-4F, yPos, -4F);
        laceLeg4.setRotationPoint(-4F, yPos, 4F);

        laceLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        laceLeg2.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        laceLeg3.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        laceLeg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }

}
