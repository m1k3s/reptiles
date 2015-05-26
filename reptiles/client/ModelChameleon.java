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

import com.reptiles.common.EntityChameleon;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelChameleon extends ModelBase {

	public ModelRenderer chameleonBody;
	public ModelRenderer chameleonHead;
	public ModelRenderer chameleonLeg1;
	public ModelRenderer chameleonLeg2;
	public ModelRenderer chameleonLeg3;
	public ModelRenderer chameleonLeg4;
	ModelRenderer chameleonTail;

	public ModelChameleon()
	{
		float yPos = 19F;

		chameleonBody = new ModelRenderer(this, 21, 16);
		chameleonBody.addBox(-3F, -2F, -5F, 6, 4, 10);
		chameleonBody.setRotationPoint(0.0F, yPos, 0.0F);

		chameleonHead = new ModelRenderer(this, 0, 0);
		chameleonHead.addBox(-2F, -2F, -6F, 4, 4, 6);
		chameleonHead.setRotationPoint(0F, yPos, -5F);

		chameleonLeg1 = new ModelRenderer(this, 56, 1);
		chameleonLeg1.addBox(-1F, 0F, -1F, 2, 5, 2);
		chameleonLeg1.setRotationPoint(4F, yPos, -4F);

		chameleonLeg2 = new ModelRenderer(this, 56, 1);
		chameleonLeg2.addBox(-1F, 0F, -1F, 2, 5, 2);
		chameleonLeg2.setRotationPoint(4F, yPos, 4F);

		chameleonLeg3 = new ModelRenderer(this, 56, 1);
		chameleonLeg3.mirror = true;
		chameleonLeg3.addBox(-1F, 0F, -1F, 2, 5, 2);
		chameleonLeg3.setRotationPoint(-4F, yPos, -4F);

		chameleonLeg4 = new ModelRenderer(this, 56, 1);
		chameleonLeg4.mirror = true;
		chameleonLeg4.addBox(-1F, 0F, -1F, 2, 5, 2);
		chameleonLeg4.setRotationPoint(-4F, yPos, 4F);

		chameleonTail = new ModelRenderer(this, 17, 12);
		chameleonTail.addBox(-1F, -1F, 0F, 2, 2, 18);
		chameleonTail.setRotationPoint(0F, yPos, 4F);
		chameleonTail.rotateAngleX = 6.021385919380437F;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);

		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		chameleonBody.render(f5);
		chameleonHead.render(f5);
		chameleonLeg1.render(f5);
		chameleonLeg2.render(f5);
		chameleonLeg3.render(f5);
		chameleonLeg4.render(f5);
		chameleonTail.render(f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		chameleonHead.rotateAngleX = f4 / 57.29578F;
		chameleonHead.rotateAngleY = f3 / 57.29578F;

		// wag the tail
		chameleonTail.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.4F * f1;
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entityliving, float f, float f1, float f2)
	{
		float yPos = 19F;
		chameleonBody.setRotationPoint(0.0F, yPos, 0.0F);
		chameleonHead.setRotationPoint(0F, yPos, -5F);
		chameleonTail.setRotationPoint(0F, yPos, 4F);
		chameleonTail.rotateAngleX = 6.021385919380437F;

		chameleonLeg1.setRotationPoint(4F, yPos, -4F);
		chameleonLeg2.setRotationPoint(4F, yPos, 4F);
		chameleonLeg3.setRotationPoint(-4F, yPos, -4F);
		chameleonLeg4.setRotationPoint(-4F, yPos, 4F);

		chameleonLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		chameleonLeg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		chameleonLeg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		chameleonLeg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
	}

}
