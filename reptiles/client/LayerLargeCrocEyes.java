/*
 * LayerLargeCrocEyes.java
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

import com.reptiles.common.EntityLargeCroc;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerLargeCrocEyes<T extends EntityLargeCroc> implements LayerRenderer<T> {

	private static final ResourceLocation eyes = new ResourceLocation("reptilemod", "textures/entity/reptiles/croc_eyes32.png");
	private final RenderLargeCroc renderLargeCroc;

	public LayerLargeCrocEyes(RenderLargeCroc render)
	{
		renderLargeCroc = render;
	}

	public void doRenderLayer(T entityCroc, float limbSwing, float limbSwingAmount, float partialTicks, float age, float headYaw, float headPitch, float scale)
	{
		renderLargeCroc.bindTexture(eyes);
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);

		if (entityCroc.isInvisible()) {
			GlStateManager.depthMask(false);
		} else {
			GlStateManager.depthMask(true);
		}

		char c0 = 61680;
		int i = c0 % 65536;
		int j = c0 / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) i, (float) j);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		renderLargeCroc.getMainModel().render(entityCroc, limbSwing, limbSwingAmount, age, headYaw, headPitch, scale);
		Minecraft.getMinecraft().entityRenderer.func_191514_d(false);
		int k = entityCroc.getBrightnessForRender(partialTicks);
		i = k % 65536;
		j = k / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) i, (float) j);
		renderLargeCroc.setLightmap(entityCroc, partialTicks);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
	}

	@Override
	public boolean shouldCombineTextures()
	{
		return false;
	}
}
