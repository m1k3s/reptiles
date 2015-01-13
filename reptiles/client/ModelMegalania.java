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
//
// Copyright 2011 Michael Sheppard (crackedEgg)
//
package com.reptiles.client;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelMegalania extends ModelBase {

	public ModelRenderer megalaniaBody;
	public ModelRenderer megalaniaHead;
	public ModelRenderer megalaniaLeg1;
	public ModelRenderer megalaniaLeg2;
	public ModelRenderer megalaniaLeg3;
	public ModelRenderer megalaniaLeg4;
	public ModelRenderer megalaniaTail;
	public ModelRenderer megalaniaTail0;
	public ModelRenderer megalaniaFin;

	public ModelMegalania()
	{
		float yPos = 16F;

		megalaniaBody = new ModelRenderer(this, 12, 8);
		megalaniaBody.addBox(-3F, -3F, -8F, 6, 8, 16);
		megalaniaBody.setRotationPoint(0.0F, yPos, 0.0F);

		megalaniaFin = new ModelRenderer(this, 48, 0);
		megalaniaFin.addBox(0F, -8F, -8F, 0, 8, 16);
		megalaniaFin.setRotationPoint(0.0F, yPos, 0.0F);

		megalaniaHead = new ModelRenderer(this, 0, 0);
		megalaniaHead.addBox(-3F, -3F, -8F, 6, 6, 8);
		megalaniaHead.setRotationPoint(0.0F, yPos, -8F);

		megalaniaLeg1 = new ModelRenderer(this, 48, 0);
		megalaniaLeg1.addBox(-2F, 0.0F, -2F, 4, 8, 4);
		megalaniaLeg1.setRotationPoint(5F, yPos, -5F);

		megalaniaLeg2 = new ModelRenderer(this, 48, 0);
		megalaniaLeg2.addBox(-2F, 0.0F, -2F, 4, 8, 4);
		megalaniaLeg2.setRotationPoint(5F, yPos, 5F);

		megalaniaLeg3 = new ModelRenderer(this, 48, 0);
		megalaniaLeg3.mirror = true;
		megalaniaLeg3.addBox(-2F, 0.0F, -2F, 4, 8, 4);
		megalaniaLeg3.setRotationPoint(-5F, yPos, -5F);

		megalaniaLeg4 = new ModelRenderer(this, 48, 0);
		megalaniaLeg4.mirror = true;
		megalaniaLeg4.addBox(-2F, 0.0F, -2F, 4, 8, 4);
		megalaniaLeg4.setRotationPoint(-5F, yPos, 5F);

		megalaniaTail = new ModelRenderer(this, 16, 8);
		megalaniaTail.addBox(-2F, -2F, 0.0F, 4, 4, 20);
		megalaniaTail.setRotationPoint(0.0F, yPos, 6F);
		megalaniaTail.rotateAngleX = 5.934119F;

		megalaniaTail = new ModelRenderer(this, 21, 14);
		megalaniaTail.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 10);
		megalaniaTail.setRotationPoint(0.0F, yPos, 6F);
		megalaniaTail.rotateAngleX = 5.934119F;

		megalaniaTail0 = new ModelRenderer(this, 21, 14);
		megalaniaTail0.addBox(-2.0F, -2.0F, 4.0F, 4, 3, 10);
		megalaniaTail0.setRotationPoint(0F, yPos + 3F, 11F);
		megalaniaTail0.rotateAngleX = 5.934119F;
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

			megalaniaHead.render(f5);
			megalaniaBody.render(f5);
			megalaniaLeg1.render(f5);
			megalaniaLeg2.render(f5);
			megalaniaLeg3.render(f5);
			megalaniaLeg4.render(f5);
			megalaniaTail.render(f5);
			megalaniaTail0.render(f5);
			megalaniaFin.render(f5);

			GlStateManager.popMatrix();
		} else {
			// make the head longer and narrower
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.95F, 0.95F, 1.1F);
			GlStateManager.translate(0.0F, f5, f5);
			megalaniaHead.render(f5);
			GlStateManager.popMatrix();

			megalaniaBody.render(f5);
			megalaniaLeg1.render(f5);
			megalaniaLeg2.render(f5);
			megalaniaLeg3.render(f5);
			megalaniaLeg4.render(f5);
			megalaniaTail.render(f5);
			megalaniaTail0.render(f5);
			megalaniaFin.render(f5);
		}
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		megalaniaHead.rotateAngleX = f4 / 57.29578F;
		megalaniaHead.rotateAngleY = f3 / 57.29578F;

		megalaniaLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		megalaniaLeg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		megalaniaLeg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		megalaniaLeg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;

		// wag the tail
		megalaniaTail.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.4F * f1;
		megalaniaTail0.rotateAngleY = MathHelper.sin(f * 0.6662F) * 0.4F * f1;

		// fin waves back and forth when walking
		megalaniaFin.rotateAngleZ = MathHelper.sin(f * 0.6662F) * 0.4F * f1;
	}

}
