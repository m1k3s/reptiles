package reptiles.common;

import net.minecraft.src.*;

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//

import org.lwjgl.opengl.GL11;

public class ModelKomodo extends ModelBase {
	public ModelKomodo() {
		float yPos = 16F;

		komodoBody = new ModelRenderer(this, 12, 8);
		komodoBody.addBox(-3F, -3F, -8F, 6, 8, 16);
		komodoBody.setRotationPoint(0.0F, yPos, 0.0F);

		komodoHead = new ModelRenderer(this, 0, 0);
		komodoHead.addBox(-3F, -3F, -8F, 6, 6, 8);
		komodoHead.setRotationPoint(0.0F, yPos, -8F);

		komodoLeg1 = new ModelRenderer(this, 48, 0);
		komodoLeg1.addBox(-2F, 0.0F, -2F, 4, 8, 4);
		komodoLeg1.setRotationPoint(5F, yPos, -5F);

		komodoLeg2 = new ModelRenderer(this, 48, 0);
		komodoLeg2.addBox(-2F, 0.0F, -2F, 4, 8, 4);
		komodoLeg2.setRotationPoint(5F, yPos, 5F);

		komodoLeg3 = new ModelRenderer(this, 48, 0);
		komodoLeg3.mirror = true;
		komodoLeg3.addBox(-2F, 0.0F, -2F, 4, 8, 4);
		komodoLeg3.setRotationPoint(-5F, yPos, -5F);

		komodoLeg4 = new ModelRenderer(this, 48, 0);
		komodoLeg4.mirror = true;
		komodoLeg4.addBox(-2F, 0.0F, -2F, 4, 8, 4);
		komodoLeg4.setRotationPoint(-5F, yPos, 5F);

		komodoTail = new ModelRenderer(this, 16, 8);
		komodoTail.addBox(-2F, -2F, 0.0F, 4, 4, 20);
		komodoTail.setRotationPoint(0.0F, yPos, 6F);
		komodoTail.rotateAngleX = 5.934119F;

		// komodoTongue = new ModelRenderer(this, 0, 14);
		// komodoTongue.addBox(-0.5F, -0.5F, -16F, 1, 1, 16);
		// komodoTongue.setRotationPoint(0.0F, yPos+2, -16F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);

		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		// EntityKomodo entitykomodo = (EntityKomodo)entity;

		if (this.isChild) {
			GL11.glPushMatrix();
			// GL11.glTranslatef(0.0F, field_40331_g * f5, field_40332_n * f5);
			// komodoHead.render(f5);
			// GL11.glPopMatrix();
			// GL11.glPushMatrix();
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			GL11.glTranslatef(0.0F, 24F * f5, 0.0F);
			komodoHead.render(f5);
			komodoBody.render(f5);
			komodoLeg1.render(f5);
			komodoLeg2.render(f5);
			komodoLeg3.render(f5);
			komodoLeg4.render(f5);
			komodoTail.render(f5);
			// if (entitykomodo.isSmelling())
			// komodoTongue.render(f5);

			GL11.glPopMatrix();
		} else {
			komodoBody.render(f5);
			komodoHead.render(f5);
			komodoLeg1.render(f5);
			komodoLeg2.render(f5);
			komodoLeg3.render(f5);
			komodoLeg4.render(f5);
			komodoTail.render(f5);
			// if (entitykomodo.isSmelling())
			// komodoTongue.render(f5);
		}
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		komodoHead.rotateAngleX = f4 / 57.29578F;
		komodoHead.rotateAngleY = f3 / 57.29578F;

		// komodoTongue.rotateAngleX = f4 / 57.29578F;
		// komodoTongue.rotateAngleY = f3 / 57.29578F;

		// wag the tail
		komodoTail.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.4F * f1;
		// tongue moves up and down when out
		// komodoTongue.rotateAngleX = MathHelper.cos(f * 0.6662F) * 0.4F * f1;
	}

	public void setLivingAnimations(EntityLiving entityliving, float f, float f1, float f2) {
		EntityKomodo entitykomodo = (EntityKomodo) entityliving;

		if (entitykomodo.isSitting()) {
			float yPos = 19F;
			komodoBody.setRotationPoint(0.0F, yPos, 0.0F);
			komodoTail.setRotationPoint(0F, yPos + 2, 6F);
			komodoTail.rotateAngleX = 0.0F;
			komodoHead.setRotationPoint(0F, yPos, -8F);
			// komodoTongue.setRotationPoint(0F, yPos+2, -16F);

			komodoLeg1.setRotationPoint(5F, yPos + 3, -5F);
			komodoLeg2.setRotationPoint(5F, yPos + 3, 5F);
			komodoLeg3.setRotationPoint(-5F, yPos + 3, -5F);
			komodoLeg4.setRotationPoint(-5F, yPos + 3, 5F);

			komodoLeg1.rotateAngleX = 4.712389F;
			komodoLeg2.rotateAngleX = 1.570799F;
			komodoLeg3.rotateAngleX = 4.712389F;
			komodoLeg4.rotateAngleX = 1.570799F;
		} else {
			float yPos = 16F;
			komodoBody.setRotationPoint(0.0F, yPos, 0.0F);
			komodoHead.setRotationPoint(0F, yPos, -8F);
			komodoTail.setRotationPoint(0F, yPos, 6F);
			komodoTail.rotateAngleX = 5.934119F;
			// komodoTongue.setRotationPoint(0F, yPos+2, -16F);

			komodoLeg1.setRotationPoint(5F, yPos, -5F);
			komodoLeg2.setRotationPoint(5F, yPos, 5F);
			komodoLeg3.setRotationPoint(-5F, yPos, -5F);
			komodoLeg4.setRotationPoint(-5F, yPos, 5F);

			komodoLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
			komodoLeg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
			komodoLeg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
			komodoLeg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		}
		// TODO: stick out tongue occasionally, render tongue inside head and
		// animate to front of head
		// moving tongue up and down at the same time.
		// if (entitykomodo.isSmelling()) {
		// komodoTongue.setRotationPoint(0F, yPos, zPos);
		// zPos++;
		// } else {
		// zPos--;
		// }
	}

	public ModelRenderer komodoBody;
	public ModelRenderer komodoHead;
	public ModelRenderer komodoLeg1;
	public ModelRenderer komodoLeg2;
	public ModelRenderer komodoLeg3;
	public ModelRenderer komodoLeg4;
	ModelRenderer komodoTail;
	// ModelRenderer komodoTongue;
}
