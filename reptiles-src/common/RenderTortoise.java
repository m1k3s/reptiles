package reptiles.common;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//
import org.lwjgl.opengl.GL11;

public class RenderTortoise extends RenderLiving
{
  public RenderTortoise(ModelBase modelbase, float shadowSize) {
    super(modelbase, shadowSize);
  }
  
  public void renderTortoise(EntityTortoise entitytortoise, double d, double d1, double d2, float f, float f1) {
    super.doRenderLiving(entitytortoise, d, d1, d2, f, f1);
  }

  public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
    renderTortoise((EntityTortoise)entity, d, d1, d2, f, f1);
  }
  
  protected void scaleEntity(EntityTortoise entityturtle, float f) {
    GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
  }
  
  protected void preRenderCallback(EntityLiving entityliving, float f) {
  	scaleEntity((EntityTortoise)entityliving, f);
  }
  
  private final float scaleFactor = 2.0F;
}
