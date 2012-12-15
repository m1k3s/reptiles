package reptiles.common;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;


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
