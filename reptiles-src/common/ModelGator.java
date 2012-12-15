package reptiles.common;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;


//
// Copyright 2011 Michael Sheppard (crackedEgg)
//

public class ModelGator extends ModelBase {
	
	public ModelRenderer gatorBody;
	public ModelRenderer gatorHead;
	public ModelRenderer gatorLeg1;
	public ModelRenderer gatorLeg2;
	public ModelRenderer gatorLeg3;
	public ModelRenderer gatorLeg4;
	ModelRenderer gatorTail1;
	ModelRenderer gatorTail2;
	public ModelRenderer rfin;
	public ModelRenderer lfin;
	public ModelRenderer cfin;
	// public ModelRenderer leyefin;
	// public ModelRenderer reyefin;
	
	public ModelGator() {
		float yPos = 19F;

		gatorBody = new ModelRenderer(this, 0, 0);
		gatorBody.addBox(-5F, -2F, -11F, 10, 4, 22);
		gatorBody.setRotationPoint(0F, yPos, 0F);

		rfin = new ModelRenderer(this, 0, 10);
		rfin.addBox(3F, -2F, -10F, 0, 2, 20);
		rfin.setRotationPoint(0F, yPos - 2, 0F);

		lfin = new ModelRenderer(this, 0, 10);
		lfin.addBox(-3F, -2F, -10F, 0, 2, 20);
		lfin.setRotationPoint(0F, yPos - 2, 0F);

		cfin = new ModelRenderer(this, 0, 10);
		cfin.addBox(0F, -2F, -10F, 0, 2, 20);
		cfin.setRotationPoint(0F, yPos - 2, 0F);

		gatorHead = new ModelRenderer(this, 50, 0);
		gatorHead.addBox(-4F, 2F, -14F, 8, 3, 14);
		gatorHead.setRotationPoint(0F, yPos - 3, -11F);

		// leyefin = new ModelRenderer(this, 0, 27);
		// leyefin.addBox(-2F, -2F, -1F, 0, 2, 3);
		// leyefin.setRotationPoint(0F, yPos, -13F);
		//
		// reyefin = new ModelRenderer(this, 0, 27);
		// reyefin.addBox(2F, -2F, -1F, 0, 2, 3);
		// reyefin.setRotationPoint(0F, yPos, -13F);

		gatorLeg1 = new ModelRenderer(this, 50, 0);
		gatorLeg1.addBox(0F, 0F, -2F, 3, 6, 4);
		gatorLeg1.setRotationPoint(5F, yPos + 1F, -8F);
		gatorLeg1.rotateAngleZ = 5.497787143782138F;

		gatorLeg2 = new ModelRenderer(this, 50, 0);
		gatorLeg2.addBox(0F, 0F, -2F, 3, 6, 4);
		gatorLeg2.setRotationPoint(5F, yPos + 1F, 8F);
		gatorLeg2.rotateAngleZ = 5.497787143782138F;

		gatorLeg3 = new ModelRenderer(this, 50, 0);
		gatorLeg3.mirror = true;
		gatorLeg3.addBox(-3F, 0F, -2F, 3, 6, 4);
		gatorLeg3.setRotationPoint(-5F, yPos + 1F, -8F);
		gatorLeg3.rotateAngleZ = 0.7853981633974483F;

		gatorLeg4 = new ModelRenderer(this, 50, 0);
		gatorLeg4.mirror = true;
		gatorLeg4.addBox(-3F, 0F, -2F, 3, 6, 4);
		gatorLeg4.setRotationPoint(-5F, yPos + 1F, 8F);
		gatorLeg4.rotateAngleZ = 0.7853981633974483F;

		gatorTail1 = new ModelRenderer(this, 16, 15);
		gatorTail1.addBox(-4F, -1F, 0F, 8, 3, 8);
		gatorTail1.setRotationPoint(0F, yPos + 0F, 11F);

		gatorTail2 = new ModelRenderer(this, 16, 14);
		gatorTail2.addBox(-3F, -1F, -5F, 6, 2, 10);
		gatorTail2.setRotationPoint(0F, yPos + 1F, 24F);

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		if (this.isChild) {
			GL11.glPushMatrix();
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			GL11.glTranslatef(0.0F, 24F * f5, 0.0F);
			gatorBody.render(f5);
			rfin.render(f5);
			lfin.render(f5);
			cfin.render(f5);
			// reyefin.render(f5);
			// leyefin.render(f5);
			gatorHead.render(f5);
			gatorLeg1.render(f5);
			gatorLeg2.render(f5);
			gatorLeg3.render(f5);
			gatorLeg4.render(f5);
			gatorTail1.render(f5);
			gatorTail2.render(f5);
			GL11.glPopMatrix();
		} else {
			gatorBody.render(f5);
			rfin.render(f5);
			lfin.render(f5);
			cfin.render(f5);
			// reyefin.render(f5);
			// leyefin.render(f5);
			gatorHead.render(f5);
			gatorLeg1.render(f5);
			gatorLeg2.render(f5);
			gatorLeg3.render(f5);
			gatorLeg4.render(f5);
			gatorTail1.render(f5);
			gatorTail2.render(f5);
		}
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		gatorHead.rotateAngleX = f4 / 57.29578F;
		gatorHead.rotateAngleY = f4 / 57.29578F;
		// reyefin.rotateAngleX = gatorHead.rotateAngleX;
		// reyefin.rotateAngleY = gatorHead.rotateAngleY;
		// leyefin.rotateAngleX = gatorHead.rotateAngleX;
		// leyefin.rotateAngleY = gatorHead.rotateAngleY;

		gatorLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		gatorLeg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		gatorLeg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		gatorLeg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;

		gatorTail1.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.4F * f1;
		gatorTail2.rotateAngleY = MathHelper.sin(f * 0.6662F) * 0.4F * f1;
	}

}
