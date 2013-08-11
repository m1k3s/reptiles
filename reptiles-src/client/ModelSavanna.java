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

import reptiles.common.EntitySavanna;

public class ModelSavanna extends ModelBase {
	
	public ModelRenderer savannaBody;
	public ModelRenderer savannaHead;
	public ModelRenderer savannaLeg1;
	public ModelRenderer savannaLeg2;
	public ModelRenderer savannaLeg3;
	public ModelRenderer savannaLeg4;
	ModelRenderer savannaTail;
	
	public ModelSavanna() {
		float yPos = 19F;

		savannaBody = new ModelRenderer(this, 30, 2); // 21 16
		savannaBody.addBox(-3F, -2F, -5F, 6, 4, 10);
		savannaBody.setRotationPoint(0.0F, yPos, 0.0F);

		savannaHead = new ModelRenderer(this, 0, 0);
		savannaHead.addBox(-2F, -2F, -6F, 4, 4, 6);
		savannaHead.setRotationPoint(0F, yPos, -5F);

		savannaLeg1 = new ModelRenderer(this, 56, 1);
		savannaLeg1.addBox(-1F, 0F, -1F, 2, 5, 2);
		savannaLeg1.setRotationPoint(4F, yPos, -4F);

		savannaLeg2 = new ModelRenderer(this, 56, 1);
		savannaLeg2.addBox(-1F, 0F, -1F, 2, 5, 2);
		savannaLeg2.setRotationPoint(4F, yPos, 4F);

		savannaLeg3 = new ModelRenderer(this, 56, 1);
		savannaLeg3.mirror = true;
		savannaLeg3.addBox(-1F, 0F, -1F, 2, 5, 2);
		savannaLeg3.setRotationPoint(-4F, yPos, -4F);

		savannaLeg4 = new ModelRenderer(this, 56, 1);
		savannaLeg4.mirror = true;
		savannaLeg4.addBox(-1F, 0F, -1F, 2, 5, 2);
		savannaLeg4.setRotationPoint(-4F, yPos, 4F);

		savannaTail = new ModelRenderer(this, 4, 12); // 17 12
		savannaTail.addBox(-1F, -1F, 0F, 2, 2, 18);
		savannaTail.setRotationPoint(0F, yPos, 4F);
		savannaTail.rotateAngleX = 6.021385919380437F;
	}

    @Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);

		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		if (this.isChild) {
			GL11.glPushMatrix();
//			 GL11.glTranslatef(0.0F, field_40331_g * f5, field_40332_n * f5);
			// savannaHead.render(f5);
			// GL11.glPopMatrix();
			// GL11.glPushMatrix();
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			GL11.glTranslatef(0.0F, 24F * f5, 0.0F);
			savannaHead.render(f5);
			savannaBody.render(f5);
			savannaLeg1.render(f5);
			savannaLeg2.render(f5);
			savannaLeg3.render(f5);
			savannaLeg4.render(f5);
			savannaTail.render(f5);
			GL11.glPopMatrix();
		} else {
			savannaBody.render(f5);
			savannaHead.render(f5);
			savannaLeg1.render(f5);
			savannaLeg2.render(f5);
			savannaLeg3.render(f5);
			savannaLeg4.render(f5);
			savannaTail.render(f5);
		}
	}

    @Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		savannaHead.rotateAngleX = f4 / 57.29578F;
		savannaHead.rotateAngleY = f3 / 57.29578F;

		// wag the tail
		savannaTail.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.4F * f1;
	}

    @Override
	public void setLivingAnimations(EntityLivingBase entityliving, float f, float f1, float f2) {
		EntitySavanna entitysavanna = (EntitySavanna) entityliving;

		if (entitysavanna.isSitting()) {
			float yPos = 21F;
			savannaBody.setRotationPoint(0.0F, yPos, 0.0F);
			savannaTail.setRotationPoint(0F, yPos + 1, 4F);
			savannaTail.rotateAngleX = 0.0F;
			savannaHead.setRotationPoint(0F, yPos, -5F);

			savannaLeg1.setRotationPoint(4F, yPos + 1, -4F);
			savannaLeg2.setRotationPoint(4F, yPos + 1, 4F);
			savannaLeg3.setRotationPoint(-4F, yPos + 1, -4F);
			savannaLeg4.setRotationPoint(-4F, yPos + 1, 4F);

			savannaLeg1.rotateAngleX = 4.712389F;
			savannaLeg2.rotateAngleX = 1.570799F;
			savannaLeg3.rotateAngleX = 4.712389F;
			savannaLeg4.rotateAngleX = 1.570799F;
		} else {
			float yPos = 19F;
			savannaBody.setRotationPoint(0.0F, yPos, 0.0F);
			savannaHead.setRotationPoint(0F, yPos, -5F);
			savannaTail.setRotationPoint(0F, yPos, 4F);
			savannaTail.rotateAngleX = 6.021385919380437F;

			savannaLeg1.setRotationPoint(4F, yPos, -4F);
			savannaLeg2.setRotationPoint(4F, yPos, 4F);
			savannaLeg3.setRotationPoint(-4F, yPos, -4F);
			savannaLeg4.setRotationPoint(-4F, yPos, 4F);

			savannaLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
			savannaLeg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
			savannaLeg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
			savannaLeg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		}
	}

}
