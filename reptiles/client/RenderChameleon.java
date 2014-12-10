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
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import com.reptiles.common.EntityChameleon;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;

public class RenderChameleon extends RenderLiving {

	private final float scaleFactor = 0.25F;
	private static final ResourceLocation skin = new ResourceLocation("reptilemod", "textures/entity/reptiles/chameleon.png");

	public RenderChameleon(RenderManager rm, ModelBase modelbase, float shadowSize)
	{
		super(rm, modelbase, shadowSize);
		addLayer(new LayerChameleonSkin(this));
	}
	
	@Override
	public void doRender(EntityLiving entity, double x, double y, double z, float par8, float par9)
	{
		renderChameleon((EntityChameleon) entity, x, y, z, par8, par9);
	}

	public void renderChameleon(EntityChameleon entitychameleon, double d, double d1, double d2, float f, float f1)
	{
		super.doRender(entitychameleon, d, d1, d2, f, f1);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f)
	{
		GlStateManager.scale(scaleFactor, scaleFactor, scaleFactor);
		super.preRenderCallback(entityliving, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return skin;
	}

}
