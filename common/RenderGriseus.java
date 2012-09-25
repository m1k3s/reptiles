package reptiles.common;

import net.minecraft.src.*;

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//


public class RenderGriseus extends RenderLiving
{
  public RenderGriseus(ModelBase modelbase, float shadowSize) {
    super(modelbase, shadowSize);
  }
  
  public void renderGriseus(EntityGriseus entitygriseus, double d, double d1, double d2, float f, float f1) {
    super.doRenderLiving(entitygriseus, d, d1, d2, f, f1);
  }

  public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2, float f, float f1) {
  	super.doRenderLiving((EntityGriseus)entityliving, d, d1, d2, f, f1);
  }

  public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
    super.doRenderLiving((EntityGriseus)entity, d, d1, d2, f, f1);
  }
}
