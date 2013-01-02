package reptiles.client;

import reptiles.common.EntityLace;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;


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
