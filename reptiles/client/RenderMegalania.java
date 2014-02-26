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
package com.reptiles.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;
import com.reptiles.common.EntityMegalania;

@SideOnly(Side.CLIENT)
public class RenderMegalania extends RenderLiving {

	private static final ResourceLocation skin = new ResourceLocation("reptilemod", "textures/entity/reptiles/megalania32.png");

	public RenderMegalania(ModelBase modelbase, float shadowSize)
	{
		super(modelbase, shadowSize);
//        setRenderPassModel((ModelMegalania) modelbase);
	}

//    public RenderMegalania() {
//		super(new ModelMegalania(), 0.8F);
//		setRenderPassModel(new ModelMegalania());
//	}
	public void renderMegalania(EntityMegalania entitymegalania, double d, double d1, double d2, float f, float f1)
	{
		super.doRender(entitymegalania, d, d1, d2, f, f1);
	}

	@Override
	public void doRender(EntityLivingBase entityliving, double d, double d1, double d2, float f, float f1)
	{
		super.doRender((EntityMegalania) entityliving, d, d1, d2, f, f1);
	}

	@Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
	{
		super.doRender((EntityMegalania) entity, d, d1, d2, f, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return skin;
	}

	protected void scaleEntity(EntityMegalania entitymegalania, float f)
	{
		GL11.glScalef(2.5F, 2.5F, 3.0F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entitylivingbase, float f)
	{
		scaleEntity((EntityMegalania) entitylivingbase, f);
	}
}
