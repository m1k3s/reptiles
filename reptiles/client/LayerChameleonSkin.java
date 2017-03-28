/*
 * LayerChameleonSkin.java
 *
 *  Copyright (c) 2017 Michael Sheppard
 *
 * =====GPL=============================================================
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

import com.reptiles.common.EntityChameleon;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class LayerChameleonSkin  implements LayerRenderer {
	
	private static final ResourceLocation pattern = new ResourceLocation("reptilemod", "textures/entity/reptiles/chameleon_pattern.png");
	private final RenderChameleon renderChameleon;

	public LayerChameleonSkin(RenderChameleon render) {
		renderChameleon = render;
	}
	
	protected void setChameleonSkin(EntityChameleon entitychameleon, float x, float y, float z, float a, float b, float c, float f)
	{
		renderChameleon.bindTexture(pattern);
		float alpha = entitychameleon.getBrightness(1.0F) * 0.5F;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
	}
	
	@Override
	public void doRenderLayer(EntityLivingBase elb, float x, float y, float z, float a, float b, float c, float f)
	{
		setChameleonSkin((EntityChameleon) elb, x, y, z, a, b, c, f);
	}

	@Override
	public boolean shouldCombineTextures()
	{
		return false;
	}
	
}
