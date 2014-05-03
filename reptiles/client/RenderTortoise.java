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
// Copyright 2011 Michael Sheppard (crackedEgg)
//
package com.reptiles.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLiving;
import org.lwjgl.opengl.GL11;
import com.reptiles.common.EntityTortoise;


public class RenderTortoise extends RenderLiving {

	private static final ResourceLocation skin = new ResourceLocation("reptilemod", "textures/entity/reptiles/gtortoise2-32.png");
	private final float scaleFactor = 2.0F;

	public RenderTortoise(ModelBase modelbase, float shadowSize)
	{
		super(modelbase, shadowSize);
	}

	public void renderTortoise(EntityTortoise entitytortoise, double d, double d1, double d2, float f, float f1)
	{
		super.doRender(entitytortoise, d, d1, d2, f, f1);
	}

	@Override
	public void doRender(EntityLiving entity, double d, double d1, double d2, float f, float f1)
	{
		renderTortoise((EntityTortoise) entity, d, d1, d2, f, f1);
	}

	protected void scaleEntity(EntityTortoise entityturtle, float f)
	{
		GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f)
	{
		scaleEntity((EntityTortoise) entityliving, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return skin;
	}
}
