/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reptiles.client;

import com.reptiles.common.EntityChameleon;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author mike
 */
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
