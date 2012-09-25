package reptiles.common;

import net.minecraft.src.*;


public class RenderPerentie extends RenderLiving
{
  public RenderPerentie(ModelBase modelbase, float shadowSize) {
    super(modelbase, shadowSize);
  }

  public void func_177_a(EntityPerentie entityPerentie, double d, double d1, double d2, float f, float f1) {
    super.doRenderLiving(entityPerentie, d, d1, d2, f, f1);
  }

  public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2, float f, float f1) {
    func_177_a((EntityPerentie)entityliving, d, d1, d2, f, f1);
  }

  public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
    func_177_a((EntityPerentie)entity, d, d1, d2, f, f1);
  }
}
