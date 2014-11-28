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

//import cpw.mods.fml.relauncher.Side;
//import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.util.MathHelper;
//import net.minecraft.world.ColorizerGrass;
//import net.minecraft.world.biome.BiomeGenBase;
import org.lwjgl.opengl.GL11;
import com.reptiles.common.EntityChameleon;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;

public class RenderChameleon extends RenderLiving {

	private final float scaleFactor = 0.25F;
	private static final ResourceLocation skin = new ResourceLocation("reptilemod", "textures/entity/reptiles/chameleon.png");
	private static final ResourceLocation pattern = new ResourceLocation("reptilemod", "textures/entity/reptiles/chameleon_pattern.png");

	public RenderChameleon(RenderManager rm, ModelBase modelbase, float shadowSize)
	{
		super(rm, modelbase, shadowSize);
//		setRenderPassModel((ModelChameleon) modelbase);
	}
	
	@Override
	public void doRender(EntityLiving entity, double x, double y, double z, float par8, float par9)
	{
		renderChameleon((EntityChameleon) entity, x, y, z, par8, par9);
	}

	public void renderChameleon(EntityChameleon entitychameleon, double d, double d1, double d2, float f, float f1)
	{
		super.doRender(entitychameleon, d, d1, d2, f, f1);
	}

//	@Override
//	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
//	{
//		renderChameleon((EntityChameleon) entity, d, d1, d2, f, f1);
//	}

	// we are using a generic model so we scale to suit our needs
	protected void scaleEntity(EntityChameleon entitychameleon, float f)
	{
		GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f)
	{
		scaleEntity((EntityChameleon) entityliving, f);
	}

	protected int setChameleonSkin(EntityChameleon entitychameleon, int i, float f)
	{
		if (i != 0) {
			return -1;
		} else {
			bindTexture(pattern);
			float alpha = entitychameleon.getBrightness(1.0F) * 0.5F;
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
			return 1;
		}
	}

//	@Override
//	protected int shouldRenderPass(EntityLivingBase entityliving, int i, float f)
//	{
//		return setChameleonSkin((EntityChameleon) entityliving, i, f);
//	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return skin;
	}

//    public static int getSkinColor(double temperature, double humidity)
//    {
//        humidity *= temperature;
//        int i = (int)((1.0D - temperature) * 255.0D);
//        int j = (int)((1.0D - humidity) * 255.0D);
//        return backgroundBuffer[j << 8 | i];
//    }
//    @SideOnly(Side.CLIENT)
//    public int getBackgroundRenderColor(EntityLivingBase entity) {
//        BiomeGenBase biome= entity.worldObj.getBiomeGenForCoords((int)entity.posX, (int)entity.posZ);
//        double temperature = (double)MathHelper.clamp_float(biome.getFloatTemperature(), 0.0F, 1.0F);
//        double humidity = (double)MathHelper.clamp_float(biome.getFloatRainfall(), 0.0F, 1.0F);
//        return ColorizerGrass.getGrassColor(temperature, humidity);
//    }
}
