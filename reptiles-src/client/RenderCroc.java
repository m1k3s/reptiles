//
// This work is licensed under the Creative Commons
// Attribution-ShareAlike 3.0 Unported License. To view a copy of this
// license, visit http://creativecommons.org/licenses/by-sa/3.0/
//

package reptiles.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//

import org.lwjgl.opengl.GL11;

import reptiles.common.EntityCroc;

public class RenderCroc extends RenderLiving
{
  public RenderCroc(ModelBase modelbase, float f) {
    super(modelbase, f);
    setRenderPassModel((ModelCroc)modelbase);
  }
  
  public RenderCroc() {
    super(new ModelCroc(), 0.8F);
    setRenderPassModel(new ModelCroc());
  }

  public void renderCroc(EntityCroc entitycroc, double d, double d1, double d2, float f, float f1) {
    super.doRenderLiving(entitycroc, d, d1, d2, f, f1);
  }

  public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2, float f, float f1) {
    renderCroc((EntityCroc)entityliving, d, d1, d2, f, f1);
  }

  public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
    renderCroc((EntityCroc)entity, d, d1, d2, f, f1);
  }
  
  protected int setCrocEyeBrightness(EntityCroc entitycroc, int i, float f) {
    if (i != 0) {
      return -1;
    } else {
      loadTexture("/mob/croc_eyes32.png");
//      float alpha = (1.0F - entitycroc.getBrightness(1.0F)) * 0.5F;
//      GL11.glEnable(GL11.GL_BLEND);
//      GL11.glDisable(GL11.GL_ALPHA_TEST);
//      GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//      GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
      GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
      char color = 61680;
      int u = color % 65536;
      int v = color / 65536;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)u / 1.0F, (float)v / 1.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      return 1;
    }
  }

  protected int shouldRenderPass(EntityLiving entityliving, int i, float f) {
    return setCrocEyeBrightness((EntityCroc)entityliving, i, f);
  }
  
}
