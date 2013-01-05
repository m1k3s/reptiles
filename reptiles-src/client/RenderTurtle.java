//
// This work is licensed under the Creative Commons
// Attribution-ShareAlike 3.0 Unported License. To view a copy of this
// license, visit http://creativecommons.org/licenses/by-sa/3.0/
//

package reptiles.client;

import reptiles.common.EntityTurtle;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;

public class RenderTurtle extends RenderLiving
{
  public RenderTurtle(ModelBase modelbase, float shadowSize) {
    super(modelbase, shadowSize);
  }
  
  public void renderTurtle(EntityTurtle entityturtle, double d, double d1, double d2, float f, float f1) {
    super.doRenderLiving(entityturtle, d, d1, d2, f, f1);
  }

  public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
    renderTurtle((EntityTurtle)entity, d, d1, d2, f, f1);
  }
  
//  protected void scaleEntity(EntityTurtle entityturtle, float f) {
//    GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
//  }
//  
//  protected void preRenderCallback(EntityLiving entityliving, float f) {
//  	scaleEntity((EntityTurtle)entityliving, f);
//  }
//  
//  private final float scaleFactor = 1.0F;
}
