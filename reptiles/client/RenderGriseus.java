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

import com.reptiles.common.EntityGriseus;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLiving;
import org.lwjgl.opengl.GL11;

public class RenderGriseus extends RenderLiving {

	private static final ResourceLocation skin = new ResourceLocation("reptilemod", "textures/entity/reptiles/griseus32.png");

	public RenderGriseus(ModelBase modelbase, float shadowSize)
	{
		super(modelbase, shadowSize);
	}

	public void renderGriseus(EntityGriseus entitygriseus, double d, double d1, double d2, float f, float f1)
	{
		super.doRender(entitygriseus, d, d1, d2, f, f1);
	}

	@Override
	public void doRender(EntityLiving entityliving, double d, double d1, double d2, float f, float f1)
	{
		renderGriseus((EntityGriseus) entityliving, d, d1, d2, f, f1);
	}

//	@Override
//	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
//	{
//		super.doRender((EntityGriseus) entity, d, d1, d2, f, f1);
//	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return skin;
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f)
	{
		float scaleFactor = ((EntityGriseus) entityliving).getScaleFactor();
		GL11.glPushMatrix();
		GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
		GL11.glPopMatrix();
	}
}
