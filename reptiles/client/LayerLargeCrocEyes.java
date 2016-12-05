package com.reptiles.client;

import com.reptiles.common.EntityLargeCroc;
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
		renderLargeCroc.getMainModel().render(entityCroc, limbSwing, limbSwingAmount, age, headYaw, headPitch, scale);
		int k = entityCroc.getBrightnessForRender(partialTicks);
		i = k % 65536;
		j = k / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) i / 1.0F, (float) j / 1.0F);
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
