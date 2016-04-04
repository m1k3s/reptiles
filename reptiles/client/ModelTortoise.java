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

import com.reptiles.common.EntityTortoise;
import net.minecraft.client.renderer.GlStateManager;

public class ModelTortoise extends ModelBase {

	public ModelRenderer carapace;
	public ModelRenderer head;
	public ModelRenderer leg1;
	public ModelRenderer leg2;
	public ModelRenderer leg3;
	public ModelRenderer leg4;
	public ModelRenderer plastron;
	public ModelRenderer tail;

	public ModelTortoise()
	{
		float yPos = 19F;

		carapace = new ModelRenderer(this, 0, 18);
		carapace.addBox(-4F, 0F, -3F, 8, 6, 8);
		carapace.setRotationPoint(0F, yPos - 3F, 0F);

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-2F, 0F, -4F, 4, 3, 4);
		head.setRotationPoint(0F, yPos, -3F);

		leg1 = new ModelRenderer(this, 56, 0);
		leg1.addBox(-2F, 0F, 0F, 2, 3, 2);
		leg1.setRotationPoint(4F, yPos + 2, -3F);

		leg2 = new ModelRenderer(this, 56, 0);
		leg2.addBox(-2F, 0F, 0F, 2, 3, 2);
		leg2.setRotationPoint(4F, yPos + 2, 3F);

		leg3 = new ModelRenderer(this, 56, 0);
		leg3.addBox(0F, 0F, 0F, 2, 3, 2);
		leg3.setRotationPoint(-4F, yPos + 2, -3F);

		leg4 = new ModelRenderer(this, 56, 0);
		leg4.addBox(0F, 0F, 0F, 2, 3, 2);
		leg4.setRotationPoint(-4F, yPos + 2, 3F);

		plastron = new ModelRenderer(this, 16, 0);
		plastron.addBox(-5F, -1F, -4F, 10, 1, 10);
		plastron.setRotationPoint(0F, yPos + 4F, 0F);

		tail = new ModelRenderer(this, 58, 29);
		tail.addBox(0F, 0F, 0F, 1, 1, 2);
		tail.setRotationPoint(0F, yPos + 1F, 5F);
		tail.rotateAngleX = 5.934119456780721F;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
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
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
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
	public void setLivingAnimations(EntityLivingBase entityliving, float f, float f1, float f2)
	{
		EntityTortoise entitytortoise = (EntityTortoise) entityliving;

		if (entitytortoise.isSitting()) {
			float yPos = 21F;
			carapace.setRotationPoint(0F, yPos - 4, 0F);
			plastron.setRotationPoint(0F, yPos + 3, 0F);
			tail.setRotationPoint(-0.5F, yPos + 2, 4F);
			head.setRotationPoint(0F, yPos - 1, -2F);

			leg1.setRotationPoint(1F, yPos + 2, -3F);
			leg2.setRotationPoint(1F, yPos + 2, 3F);
			leg3.setRotationPoint(-1F, yPos + 2, -3F);
			leg4.setRotationPoint(-1F, yPos + 2, 3F);

			leg1.rotateAngleZ = 1.570799F;
			leg2.rotateAngleZ = 4.712389F;
			leg3.rotateAngleZ = 1.570799F;
			leg4.rotateAngleZ = 4.712389F;
		} else {
			float yPos = 19F;
			carapace.setRotationPoint(0F, yPos - 4F, 0F);
			plastron.setRotationPoint(0F, yPos + 3F, 0F);
			head.setRotationPoint(0F, yPos - 1, -3F);
			tail.setRotationPoint(-0.5F, yPos + 2, 5F);

			leg1.setRotationPoint(4F, yPos + 2, -3F);
			leg2.setRotationPoint(4F, yPos + 2, 3F);
			leg3.setRotationPoint(-4F, yPos + 2, -3F);
			leg4.setRotationPoint(-4F, yPos + 2, 3F);

			leg1.rotateAngleZ = 0F;
			leg2.rotateAngleZ = 0F;
			leg3.rotateAngleZ = 0F;
			leg4.rotateAngleZ = 0F;
		}
	}

}
