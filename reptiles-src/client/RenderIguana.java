//
// This work is licensed under the Creative Commons
// Attribution-ShareAlike 3.0 Unported License. To view a copy of this
// license, visit http://creativecommons.org/licenses/by-sa/3.0/
//

package reptiles.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//
import org.lwjgl.opengl.GL11;

import reptiles.common.EntityIguana;


public class RenderIguana extends RenderLiving
{
  public RenderIguana(ModelBase modelbase, float shadowSize) {
    super(modelbase, shadowSize);
  }

	public void renderIguana(EntityIguana entityiguana, double d, double d1, double d2, float f, float f1) {
    super.doRenderLiving(entityiguana, d, d1, d2, f, f1);
  }
  
  public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
  	renderIguana((EntityIguana)entity, d, d1, d2, f, f1);
  }
	
	// we are using a generic model so we scale to suit our needs
  protected void scaleEntity(EntityIguana entityiguana, float f) {
    GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
  }
  
  protected void preRenderCallback(EntityLiving entityliving, float f) {
  	scaleEntity((EntityIguana)entityliving, f);
  }
  
  private final float scaleFactor = 0.4F;
}
