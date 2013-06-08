//
// This work is licensed under the Creative Commons
// Attribution-ShareAlike 3.0 Unported License. To view a copy of this
// license, visit http://creativecommons.org/licenses/by-sa/3.0/
//

package reptiles.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

import reptiles.common.EntitySalvadorii;

public class ModelSalvadorii extends ModelBase {
	
	public ModelRenderer salvadoriiBody;
	public ModelRenderer salvadoriiHead;
	public ModelRenderer salvadoriiLeg1;
	public ModelRenderer salvadoriiLeg2;
	public ModelRenderer salvadoriiLeg3;
	public ModelRenderer salvadoriiLeg4;
	ModelRenderer salvadoriiTail;
	
	public ModelSalvadorii() {
		float yPos = 19F;

		salvadoriiBody = new ModelRenderer(this, 21, 16);
		salvadoriiBody.addBox(-3F, -2F, -5F, 6, 4, 10);
		salvadoriiBody.setRotationPoint(0.0F, yPos, 0.0F);

		salvadoriiHead = new ModelRenderer(this, 0, 0);
		salvadoriiHead.addBox(-2F, -2F, -6F, 4, 4, 6);
		salvadoriiHead.setRotationPoint(0F, yPos, -5F);

		salvadoriiLeg1 = new ModelRenderer(this, 56, 1);
		salvadoriiLeg1.addBox(-1F, 0F, -1F, 2, 5, 2);
		salvadoriiLeg1.setRotationPoint(4F, yPos, -4F);

		salvadoriiLeg2 = new ModelRenderer(this, 56, 1);
		salvadoriiLeg2.addBox(-1F, 0F, -1F, 2, 5, 2);
		salvadoriiLeg2.setRotationPoint(4F, yPos, 4F);

		salvadoriiLeg3 = new ModelRenderer(this, 56, 1);
		salvadoriiLeg3.mirror = true;
		salvadoriiLeg3.addBox(-1F, 0F, -1F, 2, 5, 2);
		salvadoriiLeg3.setRotationPoint(-4F, yPos, -4F);

		salvadoriiLeg4 = new ModelRenderer(this, 56, 1);
		salvadoriiLeg4.mirror = true;
		salvadoriiLeg4.addBox(-1F, 0F, -1F, 2, 5, 2);
		salvadoriiLeg4.setRotationPoint(-4F, yPos, 4F);

		salvadoriiTail = new ModelRenderer(this, 17, 12);
		salvadoriiTail.addBox(-1F, -1F, 0F, 2, 2, 18);
		salvadoriiTail.setRotationPoint(0F, yPos, 4F);
		salvadoriiTail.rotateAngleX = 6.021385919380437F;
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);

		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		if (this.isChild) {
			GL11.glPushMatrix();
			// GL11.glTranslatef(0.0F, field_40331_g * f5, field_40332_n * f5);
			// salvadoriiHead.render(f5);
			// GL11.glPopMatrix();
			// GL11.glPushMatrix();
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			GL11.glTranslatef(0.0F, 24F * f5, 0.0F);
			salvadoriiHead.render(f5);
			salvadoriiBody.render(f5);
			salvadoriiLeg1.render(f5);
			salvadoriiLeg2.render(f5);
			salvadoriiLeg3.render(f5);
			salvadoriiLeg4.render(f5);
			salvadoriiTail.render(f5);
			GL11.glPopMatrix();
		} else {
			salvadoriiBody.render(f5);
			salvadoriiHead.render(f5);
			salvadoriiLeg1.render(f5);
			salvadoriiLeg2.render(f5);
			salvadoriiLeg3.render(f5);
			salvadoriiLeg4.render(f5);
			salvadoriiTail.render(f5);
		}
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		salvadoriiHead.rotateAngleX = f4 / 57.29578F;
		salvadoriiHead.rotateAngleY = f3 / 57.29578F;

		// salvadoriiLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		// salvadoriiLeg2.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) *
		// 1.4F * f1;
		// salvadoriiLeg3.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) *
		// 1.4F * f1;
		// salvadoriiLeg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		// salvadoriiTail.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.4F * f1;
		// wag the tail
		salvadoriiTail.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.4F * f1;
	}

	public void setLivingAnimations(EntityLiving entityliving, float f, float f1, float f2) {
		if (((EntitySalvadorii)entityliving).isSitting()) {
			float yPos = 21F;
			salvadoriiBody.setRotationPoint(0.0F, yPos, 0.0F);
			salvadoriiTail.setRotationPoint(0F, yPos + 1, 4F);
			salvadoriiTail.rotateAngleX = 0.0F;
			salvadoriiHead.setRotationPoint(0F, yPos, -5F);

			salvadoriiLeg1.setRotationPoint(4F, yPos + 1, -4F);
			salvadoriiLeg2.setRotationPoint(4F, yPos + 1, 4F);
			salvadoriiLeg3.setRotationPoint(-4F, yPos + 1, -4F);
			salvadoriiLeg4.setRotationPoint(-4F, yPos + 1, 4F);

			salvadoriiLeg1.rotateAngleX = 4.712389F;
			salvadoriiLeg2.rotateAngleX = 1.570799F;
			salvadoriiLeg3.rotateAngleX = 4.712389F;
			salvadoriiLeg4.rotateAngleX = 1.570799F;
		} else {
			float yPos = 19F;
			salvadoriiBody.setRotationPoint(0.0F, yPos, 0.0F);
			salvadoriiHead.setRotationPoint(0F, yPos, -5F);
			salvadoriiTail.setRotationPoint(0F, yPos, 4F);
			salvadoriiTail.rotateAngleX = 6.021385919380437F;

			salvadoriiLeg1.setRotationPoint(4F, yPos, -4F);
			salvadoriiLeg2.setRotationPoint(4F, yPos, 4F);
			salvadoriiLeg3.setRotationPoint(-4F, yPos, -4F);
			salvadoriiLeg4.setRotationPoint(-4F, yPos, 4F);

			salvadoriiLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
			salvadoriiLeg2.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
			salvadoriiLeg3.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
			salvadoriiLeg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		}
	}

}
