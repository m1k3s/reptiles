package reptiles.common;

import net.minecraft.src.*;


public class RenderLace extends RenderLiving
{
  public RenderLace(ModelBase modelbase, float shadowSize) {
    super(modelbase, shadowSize);
  }

  public void func_177_a(EntityLace entityLace, double d, double d1, double d2, float f, float f1) {
    super.doRenderLiving(entityLace, d, d1, d2, f, f1);
  }

  public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2, float f, float f1) {
    func_177_a((EntityLace)entityliving, d, d1, d2, f, f1);
  }

  public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
    func_177_a((EntityLace)entity, d, d1, d2, f, f1);
  }
}
