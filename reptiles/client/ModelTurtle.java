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

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//

import com.reptiles.common.EntityTurtle;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;

public class ModelTurtle extends ModelBase {

    public ModelRenderer carapace;
    public ModelRenderer head;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer plastron;
    public ModelRenderer tail;

    public ModelTurtle() {
        float yPos = 22F;

        carapace = new ModelRenderer(this, 0, 23);
        carapace.addBox(-3F, 0F, -3F, 6, 3, 6);
        carapace.setRotationPoint(0F, yPos - 4, 0F);

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-2F, 0F, -4F, 4, 3, 4);
        head.setRotationPoint(0F, yPos - 3, -4F);

        leg1 = new ModelRenderer(this, 60, 0);
        leg1.addBox(0F, 0F, 0F, 1, 3, 1);
        leg1.setRotationPoint(2F, yPos, -3F);
        leg1.rotateAngleZ = 5.497787143782138F;

        leg2 = new ModelRenderer(this, 60, 0);
        leg2.addBox(0F, 0F, 0F, 1, 3, 1);
        leg2.setRotationPoint(2F, yPos, 2F);
        leg2.rotateAngleZ = 5.497787143782138F;

        leg3 = new ModelRenderer(this, 60, 0);
        leg3.addBox(-1F, 0F, 0F, 1, 3, 1);
        leg3.setRotationPoint(-2F, yPos, -3F);
        leg3.rotateAngleZ = 0.7853981633974483F;

        leg4 = new ModelRenderer(this, 60, 0);
        leg4.addBox(-1F, 0F, 0F, 1, 3, 1);
        leg4.setRotationPoint(-2F, yPos, 2F);
        leg4.rotateAngleZ = 0.7853981633974483F;

        plastron = new ModelRenderer(this, 24, 23);
        plastron.addBox(-4F, -1F, -4F, 8, 1, 8);
        plastron.setRotationPoint(0F, yPos, 0F);

        tail = new ModelRenderer(this, 58, 29);
        tail.addBox(0F, 0F, 0F, 1, 1, 2);
        tail.setRotationPoint(-0.5F, yPos - 1, 4F);
        tail.rotateAngleX = 5.934119456780721F;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        if (this.isChild) {
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24F * f5, 0.0F);
            carapace.render(f5);
            head.render(f5);
            leg1.render(f5);
            leg2.render(f5);
            leg3.render(f5);
            leg4.render(f5);
            plastron.render(f5);
            tail.render(f5);
            GlStateManager.popMatrix();
        } else {
            carapace.render(f5);
            head.render(f5);
            leg1.render(f5);
            leg2.render(f5);
            leg3.render(f5);
            leg4.render(f5);
            plastron.render(f5);
            tail.render(f5);
        }
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        head.rotateAngleX = f4 / 57.29578F;
        head.rotateAngleY = f3 / 57.29578F;

        leg1.rotateAngleX = MathHelper.cos(f * 0.8F) * 1.4F * f1;
        leg2.rotateAngleX = MathHelper.cos(f * 0.8F + (float) Math.PI) * 1.4F * f1;
        leg3.rotateAngleX = MathHelper.cos(f * 0.8F + (float) Math.PI) * 1.4F * f1;
        leg4.rotateAngleX = MathHelper.cos(f * 0.8F) * 1.4F * f1;

        tail.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.4F * f1;
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entityliving, float f, float f1, float f2) {
        float yPos = 22F;
        carapace.setRotationPoint(0F, yPos - 4, 0F);
        plastron.setRotationPoint(0F, yPos, 0F);
        head.setRotationPoint(0F, yPos - 3, -4F);
        tail.setRotationPoint(-0.5F, yPos - 1, 4F);

        leg1.setRotationPoint(2F, yPos, -3F);
        leg2.setRotationPoint(2F, yPos, 2F);
        leg3.setRotationPoint(-2F, yPos, -3F);
        leg4.setRotationPoint(-2F, yPos, 2F);

        leg1.rotateAngleZ = 5.497787143782138F;
        leg2.rotateAngleZ = 5.497787143782138F;
        leg3.rotateAngleZ = 0.7853981633974483F;
        leg4.rotateAngleZ = 0.7853981633974483F;
    }

}
