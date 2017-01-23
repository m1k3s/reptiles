package com.reptiles.client;

import com.reptiles.common.EntityCroc;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerCrocEyes<T extends EntityCroc> implements LayerRenderer<T> {

	private static final ResourceLocation eyes = new ResourceLocation("reptilemod", "textures/entity/reptiles/croc_eyes32.png");
	private final RenderCroc renderCroc;

	public LayerCrocEyes(RenderCroc render)
	{
		renderCroc = render;
	}

	public void doRenderLayer(T entityCroc, float limbSwing, float limbSwingAmount, float partialTicks, float age, float headYaw, float headPitch, float scale)
	{
		renderCroc.bindTexture(eyes);
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
		renderCroc.getMainModel().render(entityCroc, limbSwing, limbSwingAmount, age, headYaw, headPitch, scale);
		Minecraft.getMinecraft().entityRenderer.func_191514_d(false);
		int k = entityCroc.getBrightnessForRender(partialTicks);
		i = k % 65536;
		j = k / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) i, (float) j);
		renderCroc.setLightmap(entityCroc, partialTicks);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
	}

	@Override
	public boolean shouldCombineTextures()
	{
		return false;
	}

}
