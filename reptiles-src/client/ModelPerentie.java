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


package reptiles.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

import reptiles.common.EntityPerentie;

public class ModelPerentie extends ModelBase {
	
	public ModelRenderer perentieBody;
	public ModelRenderer perentieHead;
	public ModelRenderer perentieLeg1;
	public ModelRenderer perentieLeg2;
	public ModelRenderer perentieLeg3;
	public ModelRenderer perentieLeg4;
	ModelRenderer perentieTail;
	
	public ModelPerentie() {
		float yPos = 19F;

		perentieBody = new ModelRenderer(this, 21, 16);
		perentieBody.addBox(-3F, -2F, -5F, 6, 4, 10);
		perentieBody.setRotationPoint(0.0F, yPos, 0.0F);

		perentieHead = new ModelRenderer(this, 0, 0);
		perentieHead.addBox(-2F, -2F, -6F, 4, 4, 6);
		perentieHead.setRotationPoint(0F, yPos, -5F);

		perentieLeg1 = new ModelRenderer(this, 56, 1);
		perentieLeg1.addBox(-1F, 0F, -1F, 2, 5, 2);
		perentieLeg1.setRotationPoint(4F, yPos, -4F);

		perentieLeg2 = new ModelRenderer(this, 56, 1);
		perentieLeg2.addBox(-1F, 0F, -1F, 2, 5, 2);
		perentieLeg2.setRotationPoint(4F, yPos, 4F);

		perentieLeg3 = new ModelRenderer(this, 56, 1);
		perentieLeg3.mirror = true;
		perentieLeg3.addBox(-1F, 0F, -1F, 2, 5, 2);
		perentieLeg3.setRotationPoint(-4F, yPos, -4F);

		perentieLeg4 = new ModelRenderer(this, 56, 1);
		perentieLeg4.mirror = true;
		perentieLeg4.addBox(-1F, 0F, -1F, 2, 5, 2);
		perentieLeg4.setRotationPoint(-4F, yPos, 4F);

		perentieTail = new ModelRenderer(this, 17, 12);
		perentieTail.addBox(-1F, -1F, 0F, 2, 2, 18);
		perentieTail.setRotationPoint(0F, yPos, 4F);
		perentieTail.rotateAngleX = 6.021385919380437F;
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);

		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		if (this.isChild) {
			GL11.glPushMatrix();
			// GL11.glTranslatef(0.0F, field_40331_g * f5, field_40332_n * f5);
			// perentieHead.render(f5);
			// GL11.glPopMatrix();
			// GL11.glPushMatrix();
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			GL11.glTranslatef(0.0F, 24F * f5, 0.0F);
			perentieHead.render(f5);
			perentieBody.render(f5);
			perentieLeg1.render(f5);
			perentieLeg2.render(f5);
			perentieLeg3.render(f5);
			perentieLeg4.render(f5);
			perentieTail.render(f5);
			GL11.glPopMatrix();
		} else {
			perentieBody.render(f5);
			perentieHead.render(f5);
			perentieLeg1.render(f5);
			perentieLeg2.render(f5);
			perentieLeg3.render(f5);
			perentieLeg4.render(f5);
			perentieTail.render(f5);
		}
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		perentieHead.rotateAngleX = f4 / 57.29578F;
		perentieHead.rotateAngleY = f3 / 57.29578F;

		// perentieLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		// perentieLeg2.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) *
		// 1.4F * f1;
		// perentieLeg3.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) *
		// 1.4F * f1;
		// perentieLeg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		// perentieTail.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.4F * f1;
		// wag the tail
		perentieTail.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.4F * f1;
	}

	public void setLivingAnimations(EntityLivingBase entityliving, float f, float f1, float f2) {
		if (((EntityPerentie)entityliving).isSitting()) {
			float yPos = 21F;
			perentieBody.setRotationPoint(0.0F, yPos, 0.0F);
			perentieTail.setRotationPoint(0F, yPos + 1, 4F);
			perentieTail.rotateAngleX = 0.0F;
			perentieHead.setRotationPoint(0F, yPos, -5F);

			perentieLeg1.setRotationPoint(4F, yPos + 1, -4F);
			perentieLeg2.setRotationPoint(4F, yPos + 1, 4F);
			perentieLeg3.setRotationPoint(-4F, yPos + 1, -4F);
			perentieLeg4.setRotationPoint(-4F, yPos + 1, 4F);

			perentieLeg1.rotateAngleX = 4.712389F;
			perentieLeg2.rotateAngleX = 1.570799F;
			perentieLeg3.rotateAngleX = 4.712389F;
			perentieLeg4.rotateAngleX = 1.570799F;
		} else {
			float yPos = 19F;
			perentieBody.setRotationPoint(0.0F, yPos, 0.0F);
			perentieHead.setRotationPoint(0F, yPos, -5F);
			perentieTail.setRotationPoint(0F, yPos, 4F);
			perentieTail.rotateAngleX = 6.021385919380437F;

			perentieLeg1.setRotationPoint(4F, yPos, -4F);
			perentieLeg2.setRotationPoint(4F, yPos, 4F);
			perentieLeg3.setRotationPoint(-4F, yPos, -4F);
			perentieLeg4.setRotationPoint(-4F, yPos, 4F);

			perentieLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
			perentieLeg2.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
			perentieLeg3.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
			perentieLeg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		}
	}

}
