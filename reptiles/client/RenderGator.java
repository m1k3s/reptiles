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
// Copyright 2011-2015 Michael Sheppard (crackedEgg)
//
package com.reptiles.client;

import com.reptiles.common.EntityGator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderGator<T extends EntityGator> extends RenderLiving<T> {

	private static final ResourceLocation skin = new ResourceLocation("reptilemod", "textures/entity/reptiles/gator32.png");
//	private static final ResourceLocation eyes = new ResourceLocation("reptilemod", "textures/entity/reptiles/gator_eyes32.png");

	public RenderGator(RenderManager rm)
	{
		super(rm, new ModelGator(), 0.0f);
		addLayer(new LayerGatorEyes(this));
	}

	@Override
	protected void preRenderCallback(T entityliving, float f)
	{
		GlStateManager.scale(0.8f, 1.2f, 1.2f);
		super.preRenderCallback(entityliving, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return skin;
	}
}
