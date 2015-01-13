package com.reptiles.client;

import com.reptiles.common.EntityCroc;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerCrocEyes implements LayerRenderer {

	private static final ResourceLocation eyes = new ResourceLocation("reptilemod", "textures/entity/reptiles/croc_eyes32.png");
	private final RenderCroc renderCroc;

	public LayerCrocEyes(RenderCroc render)
	{
		this.renderCroc = render;
	}

	public void renderCrocEyes(EntityCroc entityCroc, float x, float y, float z, float a, float b, float c, float f)
	{
		this.renderCroc.bindTexture(eyes);
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.blendFunc(1, 1);

		if (entityCroc.isInvisible()) {
			GlStateManager.depthMask(false);
		} else {
			GlStateManager.depthMask(true);
		}

		char c0 = 61680;
		int i = c0 % 65536;
		int j = c0 / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) i / 1.0F, (float) j / 1.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.renderCroc.getMainModel().render(entityCroc, x, y, a, b, c, f);
		int k = entityCroc.getBrightnessForRender(z);
		i = k % 65536;
		j = k / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) i / 1.0F, (float) j / 1.0F);
		this.renderCroc.func_177105_a(entityCroc, z);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
	}

	@Override
	public boolean shouldCombineTextures()
	{
		return false;
	}

	@Override
	public void doRenderLayer(EntityLivingBase entityLivingBase, float x, float y, float z, float a, float b, float c, float f)
	{
		this.renderCrocEyes((EntityCroc) entityLivingBase, x, y, z, a, b, c, f);
	}
}
