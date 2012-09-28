package reptiles.common;

import net.minecraft.src.*;

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//

public class ModelIguana extends ModelBase {
	public ModelRenderer iguanaBody;
	public ModelRenderer iguanaHead;
	public ModelRenderer iguanaLeg1;
	public ModelRenderer iguanaLeg2;
	public ModelRenderer iguanaLeg3;
	public ModelRenderer iguanaLeg4;
	ModelRenderer iguanaTail;

	public ModelIguana() {
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

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);

		setRotationAngles(f, f1, f2, f3, f4, f5);

		iguanaBody.render(f5);
		iguanaHead.render(f5);
		iguanaLeg1.render(f5);
		iguanaLeg2.render(f5);
		iguanaLeg3.render(f5);
		iguanaLeg4.render(f5);
		iguanaTail.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5);

		iguanaHead.rotateAngleX = f4 / 57.29578F;
		iguanaHead.rotateAngleY = f3 / 57.29578F;

		// wag the tail
		iguanaTail.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.4F * f1;
	}

	public void setLivingAnimations(EntityLiving entityliving, float f, float f1, float f2) {
		EntityIguana entityiguana = (EntityIguana) entityliving;

		if (entityiguana.isSitting()) {
			float yPos = 21F;
			iguanaBody.setRotationPoint(0.0F, yPos, 0.0F);
			iguanaTail.setRotationPoint(0F, yPos + 1, 4F);
			iguanaTail.rotateAngleX = 0.0F;
			iguanaHead.setRotationPoint(0F, yPos, -5F);

			iguanaLeg1.setRotationPoint(4F, yPos + 1, -4F);
			iguanaLeg2.setRotationPoint(4F, yPos + 1, 4F);
			iguanaLeg3.setRotationPoint(-4F, yPos + 1, -4F);
			iguanaLeg4.setRotationPoint(-4F, yPos + 1, 4F);

			iguanaLeg1.rotateAngleX = 4.712389F;
			iguanaLeg2.rotateAngleX = 1.570799F;
			iguanaLeg3.rotateAngleX = 4.712389F;
			iguanaLeg4.rotateAngleX = 1.570799F;
		} else {
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

}
