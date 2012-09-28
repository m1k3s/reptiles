package reptiles.common;

import net.minecraft.src.*;

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//
import org.lwjgl.opengl.GL11;


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
