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

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelLargeCroc extends ModelBase {

	public ModelRenderer crocBody;
	public ModelRenderer crocHead;
	public ModelRenderer crocLeg1;
	public ModelRenderer crocLeg2;
	public ModelRenderer crocLeg3;
	public ModelRenderer crocLeg4;
	ModelRenderer crocTail1;
	ModelRenderer crocTail2;
	public ModelRenderer rfin;
	public ModelRenderer lfin;
	public ModelRenderer cfin;
	// public ModelRenderer leyefin;
	// public ModelRenderer reyefin;

	public ModelLargeCroc()
	{
		float yPos = 19F;

		crocBody = new ModelRenderer(this, 0, 0);
		crocBody.addBox(-5F, -2F, -11F, 10, 4, 22);
		crocBody.setRotationPoint(0F, yPos, 0F);

		rfin = new ModelRenderer(this, 0, 10);
		rfin.addBox(3F, -2F, -10F, 0, 2, 20);
		rfin.setRotationPoint(0F, yPos - 2, 0F);

		lfin = new ModelRenderer(this, 0, 10);
		lfin.addBox(-3F, -2F, -10F, 0, 2, 20);
		lfin.setRotationPoint(0F, yPos - 2, 0F);

		cfin = new ModelRenderer(this, 0, 10);
		cfin.addBox(0F, -2F, -10F, 0, 2, 20);
		cfin.setRotationPoint(0F, yPos - 2, 0F);

		crocHead = new ModelRenderer(this, 50, 0);
		crocHead.addBox(-4F, 2F, -14F, 8, 3, 14);
		crocHead.setRotationPoint(0F, yPos - 3, -11F);

		// leyefin = new ModelRenderer(this, 0, 27);
		// leyefin.addBox(-2F, -2F, -1F, 0, 2, 3);
		// leyefin.setRotationPoint(0F, yPos, -13F);
		//
		// reyefin = new ModelRenderer(this, 0, 27);
		// reyefin.addBox(2F, -2F, -1F, 0, 2, 3);
		// reyefin.setRotationPoint(0F, yPos, -13F);
		crocLeg1 = new ModelRenderer(this, 50, 0);
		crocLeg1.addBox(0F, 0F, -2F, 3, 6, 4);
		crocLeg1.setRotationPoint(5F, yPos + 1F, -8F);
		crocLeg1.rotateAngleZ = 5.497787143782138F;

		crocLeg2 = new ModelRenderer(this, 50, 0);
		crocLeg2.addBox(0F, 0F, -2F, 3, 6, 4);
		crocLeg2.setRotationPoint(5F, yPos + 1F, 8F);
		crocLeg2.rotateAngleZ = 5.497787143782138F;

		crocLeg3 = new ModelRenderer(this, 50, 0);
		crocLeg3.mirror = true;
		crocLeg3.addBox(-3F, 0F, -2F, 3, 6, 4);
		crocLeg3.setRotationPoint(-5F, yPos + 1F, -8F);
		crocLeg3.rotateAngleZ = 0.7853981633974483F;

		crocLeg4 = new ModelRenderer(this, 50, 0);
		crocLeg4.mirror = true;
		crocLeg4.addBox(-3F, 0F, -2F, 3, 6, 4);
		crocLeg4.setRotationPoint(-5F, yPos + 1F, 8F);
		crocLeg4.rotateAngleZ = 0.7853981633974483F;

		crocTail1 = new ModelRenderer(this, 16, 15);
		crocTail1.addBox(-4F, -1F, 0F, 8, 3, 8);
		crocTail1.setRotationPoint(0F, yPos + 0F, 11F);

		crocTail2 = new ModelRenderer(this, 16, 14);
		crocTail2.addBox(-3F, -1F, -5F, 6, 2, 10);
		crocTail2.setRotationPoint(0F, yPos + 1F, 24F);

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		if (this.isChild) {
			GL11.glPushMatrix();
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			GL11.glTranslatef(0.0F, 24F * f5, 0.0F);
			crocBody.render(f5);
			rfin.render(f5);
			lfin.render(f5);
			cfin.render(f5);
			// reyefin.render(f5);
			// leyefin.render(f5);
			crocHead.render(f5);
			crocLeg1.render(f5);
			crocLeg2.render(f5);
			crocLeg3.render(f5);
			crocLeg4.render(f5);
			crocTail1.render(f5);
			crocTail2.render(f5);
			GL11.glPopMatrix();
		} else {
			crocBody.render(f5);
			rfin.render(f5);
			lfin.render(f5);
			cfin.render(f5);
			// reyefin.render(f5);
			// leyefin.render(f5);
			crocHead.render(f5);
			crocLeg1.render(f5);
			crocLeg2.render(f5);
			crocLeg3.render(f5);
			crocLeg4.render(f5);
			crocTail1.render(f5);
			crocTail2.render(f5);
		}
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		crocHead.rotateAngleX = f4 / 57.29578F;
		crocHead.rotateAngleY = f4 / 57.29578F;
		// reyefin.rotateAngleX = crocHead.rotateAngleX;
		// reyefin.rotateAngleY = crocHead.rotateAngleY;
		// leyefin.rotateAngleX = crocHead.rotateAngleX;
		// leyefin.rotateAngleY = crocHead.rotateAngleY;

		crocLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		crocLeg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		crocLeg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		crocLeg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;

		crocTail1.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.4F * f1;
		crocTail2.rotateAngleY = MathHelper.sin(f * 0.6662F) * 0.4F * f1;
	}

}
