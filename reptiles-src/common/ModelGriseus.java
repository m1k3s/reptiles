package reptiles.common;

import net.minecraft.src.*;

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//
import org.lwjgl.opengl.GL11;

public class ModelGriseus extends ModelBase
{
  public ModelGriseus() {
    float yPos = 19F;
//    field_40331_g = 8F;
//    field_40332_n = 4F;
    
		griseusBody = new ModelRenderer(this, 21, 16);
		griseusBody.addBox(-3F, -2F, -5F, 6, 4, 10);
		griseusBody.setRotationPoint(0.0F, yPos, 0.0F);

		griseusHead = new ModelRenderer(this, 0, 0);
		griseusHead.addBox(-2F, -2F, -6F, 4, 4, 6);
		griseusHead.setRotationPoint(0F, yPos, -5F);
		
		griseusLeg1 = new ModelRenderer(this, 56, 1);
		griseusLeg1.addBox(-1F, 0F, -1F, 2, 5, 2);
		griseusLeg1.setRotationPoint(4F, yPos, -4F);

		griseusLeg2 = new ModelRenderer(this, 56, 1);
		griseusLeg2.addBox(-1F, 0F, -1F, 2, 5, 2);
		griseusLeg2.setRotationPoint(4F, yPos, 4F);

		griseusLeg3 = new ModelRenderer(this, 56, 1);
		griseusLeg3.mirror = true;
		griseusLeg3.addBox(-1F, 0F, -1F, 2, 5, 2);
		griseusLeg3.setRotationPoint(-4F, yPos, -4F);

		griseusLeg4 = new ModelRenderer(this, 56, 1);
		griseusLeg4.mirror = true;
		griseusLeg4.addBox(-1F, 0F, -1F, 2, 5, 2);
		griseusLeg4.setRotationPoint(-4F, yPos, 4F);

		griseusTail = new ModelRenderer(this, 17, 12);
		griseusTail.addBox(-1F, -1F, 0F, 2, 2, 18);
		griseusTail.setRotationPoint(0F, yPos, 4F);
		griseusTail.rotateAngleX = 6.021385919380437F;

  }

  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.render(entity, f, f1, f2, f3, f4, f5);
    
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    
    if (this.isChild) {
      GL11.glPushMatrix();
//      GL11.glTranslatef(0.0F, field_40331_g * f5, field_40332_n * f5);
//      griseusHead.render(f5);
//      GL11.glPopMatrix();
//      GL11.glPushMatrix();
      GL11.glScalef(0.5F, 0.5F, 0.5F);
      GL11.glTranslatef(0.0F, 24F * f5, 0.0F);
      griseusHead.render(f5);
      griseusBody.render(f5);
      griseusLeg1.render(f5);
      griseusLeg2.render(f5);
      griseusLeg3.render(f5);
      griseusLeg4.render(f5);
      griseusTail.render(f5);
      GL11.glPopMatrix();
    } else {
    	griseusBody.render(f5);
		  griseusHead.render(f5);
		  griseusLeg1.render(f5);
		  griseusLeg2.render(f5);
		  griseusLeg3.render(f5);
		  griseusLeg4.render(f5);
		  griseusTail.render(f5);
    }
  }

  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    
    griseusHead.rotateAngleX = f4 / 57.29578F;
    griseusHead.rotateAngleY = f3 / 57.29578F;
    
    // wag the tail
    griseusTail.rotateAngleY = MathHelper.cos(f * 0.6662F) * 0.4F * f1;
  }
  
public void setLivingAnimations(EntityLiving entityliving, float f, float f1, float f2) {
    EntityGriseus entitygriseus = (EntityGriseus)entityliving;
    
    if (entitygriseus.isSitting()) {
    	float yPos = 21F;
      griseusBody.setRotationPoint(0.0F, yPos, 0.0F);
      griseusTail.setRotationPoint(0F, yPos+1, 4F);
      griseusTail.rotateAngleX = 0.0F;
      griseusHead.setRotationPoint(0F, yPos, -5F);
      
      griseusLeg1.setRotationPoint(4F, yPos+1, -4F);
      griseusLeg2.setRotationPoint(4F, yPos+1, 4F);
      griseusLeg3.setRotationPoint(-4F, yPos+1, -4F);
      griseusLeg4.setRotationPoint(-4F, yPos+1, 4F);
      
      griseusLeg1.rotateAngleX = 4.712389F;
      griseusLeg2.rotateAngleX = 1.570799F;
      griseusLeg3.rotateAngleX = 4.712389F;
      griseusLeg4.rotateAngleX = 1.570799F;
    } else {
    	float yPos = 19F;
      griseusBody.setRotationPoint(0.0F, yPos, 0.0F);
      griseusHead.setRotationPoint(0F, yPos, -5F);
      griseusTail.setRotationPoint(0F, yPos, 4F);
      griseusTail.rotateAngleX = 6.021385919380437F;
      
      griseusLeg1.setRotationPoint(4F, yPos, -4F);
      griseusLeg2.setRotationPoint(4F, yPos, 4F);
      griseusLeg3.setRotationPoint(-4F, yPos, -4F);
      griseusLeg4.setRotationPoint(-4F, yPos, 4F);
      
      griseusLeg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
      griseusLeg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
      griseusLeg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
      griseusLeg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }
  }

  public ModelRenderer griseusBody;
  public ModelRenderer griseusHead;
  public ModelRenderer griseusLeg1;
  public ModelRenderer griseusLeg2;
  public ModelRenderer griseusLeg3;
  public ModelRenderer griseusLeg4;
  ModelRenderer griseusTail;
//  protected float field_40331_g;
//  protected float field_40332_n;
}
