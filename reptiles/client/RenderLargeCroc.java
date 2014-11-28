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
//
// Copyright 2011-2014 Michael Sheppard (crackedEgg)
//
package com.reptiles.client;

import net.minecraft.client.model.ModelBase;
//import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLiving;
import org.lwjgl.opengl.GL11;
import com.reptiles.common.EntityLargeCroc;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderLargeCroc extends RenderLiving {

	private static final ResourceLocation skin = new ResourceLocation("reptilemod", "textures/entity/reptiles/largeCroc32.png");
//	private static final ResourceLocation eyes = new ResourceLocation("reptilemod", "textures/entity/reptiles/croc_eyes32.png");
	private final float scaleFactor = 1.5F;

	public RenderLargeCroc(RenderManager rm, ModelBase modelbase, float f)
	{
		super(rm, modelbase, f);
		this.addLayer(new LayerLargeCrocEyes(this));
//		setRenderPassModel((ModelLargeCroc) modelbase);
	}

//	public RenderLargeCroc()
//	{
//		super(new ModelLargeCroc(), 0.8F);
//		setRenderPassModel(new ModelLargeCroc());
//	}

	public void renderLargeCroc(EntityLargeCroc entitycroc, double d, double d1, double d2, float f, float f1)
	{
		super.doRender(entitycroc, d, d1, d2, f, f1);
	}

	@Override
	public void doRender(EntityLiving entityliving, double d, double d1, double d2, float f, float f1)
	{
		renderLargeCroc((EntityLargeCroc) entityliving, d, d1, d2, f, f1);
	}

//	protected int setCrocEyeBrightness(EntityLargeCroc entitycroc, int i, float f)
//	{
//		if (i != 0) {
//			return -1;
//		} else {
//			bindTexture(eyes);
//			// float alpha = (1.0F - entitycroc.getBrightness(1.0F)) * 0.5F;
//			// GL11.glEnable(GL11.GL_BLEND);
//			// GL11.glDisable(GL11.GL_ALPHA_TEST);
//			// GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//			// GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
//			GL11.glEnable(GL11.GL_BLEND);
//			GL11.glDisable(GL11.GL_ALPHA_TEST);
//			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
//
//			if (entitycroc.isInvisible()) {
//				GL11.glDepthMask(false);
//			} else {
//				GL11.glDepthMask(true);
//			}
//			char color = 61680;
//			int u = color % 65536;
//			int v = color / 65536;
//			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) u / 1.0F, (float) v / 1.0F);
//			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//			return 1;
//		}
//	}
//
//	@Override
//	protected int shouldRenderPass(EntityLivingBase entityliving, int i, float f)
//	{
//		return setCrocEyeBrightness((EntityLargeCroc) entityliving, i, f);
//	}

	protected void scaleEntity(EntityLargeCroc entitycroc, float f)
	{
		GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f)
	{
		scaleEntity((EntityLargeCroc) entityliving, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return skin;
	}

}
