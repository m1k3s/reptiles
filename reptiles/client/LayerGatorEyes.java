package com.reptiles.client;

import com.reptiles.common.EntityGator;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerGatorEyes<T extends EntityGator> implements LayerRenderer<T> {

	private static final ResourceLocation eyes = new ResourceLocation("reptilemod", "textures/entity/reptiles/croc_eyes32.png");
	private final RenderGator renderGator;

	public LayerGatorEyes(RenderGator render)
	{
		this.renderGator = render;
	}

	public void doRenderLayer(T entityCroc, float limbSwing, float limbSwingAmount, float partialTicks, float age, float headYaw, float headPitch, float scale)
	{
		renderGator.bindTexture(eyes);
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
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) i / 1.0F, (float) j / 1.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		renderGator.getMainModel().render(entityCroc, limbSwing, limbSwingAmount, age, headYaw, headPitch, scale);
		int k = entityCroc.getBrightnessForRender(partialTicks);
		i = k % 65536;
		j = k / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) i / 1.0F, (float) j / 1.0F);
		renderGator.setLightmap(entityCroc, partialTicks);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
	}

	@Override
	public boolean shouldCombineTextures()
	{
		return false;
	}

}
