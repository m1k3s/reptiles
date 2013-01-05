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

import reptiles.common.EntityChameleon;

public class RenderChameleon extends RenderLiving {
	private final float scaleFactor = 0.25F;

	public RenderChameleon(ModelBase modelbase, float shadowSize) {
		super(modelbase, shadowSize);
		setRenderPassModel((ModelChameleon) modelbase);
	}

	public void renderChameleon(EntityChameleon entitychameleon, double d, double d1, double d2, float f, float f1) {
		super.doRenderLiving(entitychameleon, d, d1, d2, f, f1);
	}

	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
		renderChameleon((EntityChameleon) entity, d, d1, d2, f, f1);
	}

	// we are using a generic model so we scale to suit our needs
	protected void scaleEntity(EntityChameleon entitychameleon, float f) {
		GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
	}

	protected void preRenderCallback(EntityLiving entityliving, float f) {
		scaleEntity((EntityChameleon) entityliving, f);
	}

	protected int setChameleonSkin(EntityChameleon entitychameleon, int i, float f) {
		if (i != 0) {
			return -1;
		} else {
			loadTexture("/mob/chameleon_pattern.png");
			// float alpha = (1.0F - entitychameleon.getEntityBrightness(1.0F)) * 0.5F;
			float alpha = entitychameleon.getBrightness(1.0F);// * 0.5F;
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
			return 1;
		}
	}

	protected int shouldRenderPass(EntityLiving entityliving, int i, float f) {
		return setChameleonSkin((EntityChameleon) entityliving, i, f);
	}

}
