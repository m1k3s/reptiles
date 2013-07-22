//  
//  =====GPL=============================================================
//  This program is free software; you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation; version 2 dated June, 1991.
// 
//  This program is distributed in the hope that it will be useful, 
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
// 
//  You should have received a copy of the GNU General Public License
//  along with this program;  if not, write to the Free Software
//  Foundation, Inc., 675 Mass Ave., Cambridge, MA 02139, USA.
//  =====================================================================
//


package reptiles.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;



//
// Copyright 2011 Michael Sheppard (crackedEgg)
//
import org.lwjgl.opengl.GL11;

import reptiles.common.EntityChameleon;

public class RenderChameleon extends RenderLiving {
	private final float scaleFactor = 0.25F;
	private static final ResourceLocation skin = new ResourceLocation("reptilemod", "textures/entity/reptiles/chameleon.png");
	private static final ResourceLocation pattern = new ResourceLocation("reptilemod", "textures/entity/reptiles/chameleon_pattern.png");

	public RenderChameleon(ModelBase modelbase, float shadowSize) {
		super(modelbase, shadowSize);
		setRenderPassModel((ModelChameleon) modelbase);
	}

	public void renderChameleon(EntityChameleon entitychameleon, double d, double d1, double d2, float f, float f1) {
		super.doRenderLiving(entitychameleon, d, d1, d2, f, f1);
	}
	
    @Override
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
			func_110776_a(pattern);
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

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		return skin;
	}

}
