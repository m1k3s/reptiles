/*
 * RenderLittleTurtle.java
 *
 *  Copyright (c) 2017 Michael Sheppard
 *
 * =====GPLv3===========================================================
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses.
 * =====================================================================
 */

package com.reptiles.client;

import com.reptiles.common.EntityLittleTurtle;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;

import javax.annotation.Nonnull;

public class RenderLittleTurtle<T extends EntityLittleTurtle> extends RenderLiving<T> {

	private static final ResourceLocation skin = new ResourceLocation("reptilemod", "textures/entity/reptiles/littleturtle.png");

	public RenderLittleTurtle(RenderManager rm)
	{
		super(rm, new ModelLittleTurtle(), 0.0f);
	}

	@SuppressWarnings("unchecked")
	public void renderLittleTurtle(T entitytortoise, double d, double d1, double d2, float f, float f1)
	{
		super.doRender(entitytortoise, d, d1, d2, f, f1);
	}

	@Override
	public void doRender(@Nonnull T entity, double d, double d1, double d2, float f, float f1)
	{
		renderLittleTurtle(entity, d, d1, d2, f, f1);
	}

	@Override
	protected void preRenderCallback(T entityliving, float f)
	{
		float scaleFactor = 0.5F;
		GlStateManager.scale(scaleFactor, scaleFactor, scaleFactor);
		super.preRenderCallback(entityliving, f);
	}

	@Override
	protected ResourceLocation getEntityTexture(@Nonnull T entity)
	{
		return skin;
	}
}
