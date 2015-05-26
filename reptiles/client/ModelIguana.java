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
//
package com.reptiles.client;

import com.reptiles.common.EntityIguana;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelIguana extends ModelBase {

	public ModelRenderer iguanaBody;
	public ModelRenderer iguanaHead;
	public ModelRenderer iguanaLeg1;
	public ModelRenderer iguanaLeg2;
	public ModelRenderer iguanaLeg3;
	public ModelRenderer iguanaLeg4;
	ModelRenderer iguanaTail;

	public ModelIguana()
	{
		float yPos = 19F;

		iguanaBody = new ModelRenderer(this, 21, 16);
		iguanaBody.addBox(-3F, -2F, -5F, 6, 4, 10);
		iguanaBody.setRotationPoint(0.0F, yPos, 0.0F);

		iguanaHead = new ModelRenderer(this, 0, 0);
		iguanaHead.addBox(-2F, -2F, -6F, 4, 4, 6);
		iguanaHead.setRotationPoint(0F, yPos, -5F);

		iguanaLeg1 = new ModelRenderer(this, 56, 1);
		iguanaLeg1.addBox(-1F, 0F, -1F, 2, 5, 2);
		iguanaLeg1.setRotationPoint(4F, yPos, -4F);

		iguanaLeg2 = new ModelRenderer(this, 56, 1);
		iguanaLeg2.addBox(-1F, 0F, -1F, 2, 5, 2);
		iguanaLeg2.setRotationPoint(4F, yPos, 4F);

		iguanaLeg3 = new ModelRenderer(this, 56, 1);
		iguanaLeg3.mirror = true;
		iguanaLeg3.addBox(-1F, 0F, -1F, 2, 5, 2);
		iguanaLeg3.setRotationPoint(-4F, yPos, -4F);

		iguanaLeg4 = new ModelRenderer(this, 56, 1);
		iguanaLeg4.mirror = true;
		iguanaLeg4.addBox(-1F, 0F, -1F, 2, 5, 2);
		iguanaLeg4.setRotationPoint(-4F, yPos, 4F);

		iguanaTail = new ModelRenderer(this, 17, 12);
		iguanaTail.addBox(-1F, -1F, 0F, 2, 2, 18);
		iguanaTail.setRotationPoint(0F, yPos, 4F);
		iguanaTail.rotateAngleX = 6.021385919380437F;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);

		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		iguanaBody.render(f5);
		iguanaHead.render(f5);
		iguanaLeg1.render(f5);
		iguanaLeg2.render(f5);
		iguanaLeg3.render(f5);
		iguanaLeg4.render(f5);
		iguanaTail.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		iguanaHead.rotateAngleX = f4 / 57.29578F;
		iguanaHead.rotateAngleY = f3 / 57.29578F;

		// wag the tail
		iguanaTail.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.4F * f1;
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entityliving, float f, float f1, float f2)
	{
		float yPos = 19F;
		iguanaBody.setRotationPoint(0.0F, yPos, 0.0F);
		iguanaHead.setRotationPoint(0F, yPos, -5F);
		iguanaTail.setRotationPoint(0F, yPos, 4F);
		iguanaTail.rotateAngleX = 6.021385919380437F;

		iguanaLeg1.setRotationPoint(4F, yPos, -4F);
		iguanaLeg2.setRotationPoint(4F, yPos, 4F);
		iguanaLeg3.setRotationPoint(-4F, yPos, -4F);
		iguanaLeg4.setRotationPoint(-4F, yPos, 4F);

		iguanaLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		iguanaLeg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		iguanaLeg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		iguanaLeg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
	}

}
