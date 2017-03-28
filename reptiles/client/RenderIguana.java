// RenderIguana.java
// Copyright (c) 2017 Michael Sheppard
//
//  =====GPL=============================================================
//  reptilemod is free software: you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License
//  along with this program.  If not, see http://www.gnu.org/licenses.
//  =====================================================================
//

package com.reptiles.client;

import com.reptiles.common.EntityIguana;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;

public class RenderIguana<T extends EntityIguana> extends RenderLiving<T> {

    private final float scaleFactor = 0.4F;
    private static final ResourceLocation skin = new ResourceLocation("reptilemod", "textures/entity/reptiles/iguana.png");

    public RenderIguana(RenderManager rm) {
        super(rm, new ModelIguana(), 0.0f);
    }

    @Override
    protected void preRenderCallback(T entityliving, float f) {
        GlStateManager.scale(scaleFactor, scaleFactor, scaleFactor);
        super.preRenderCallback(entityliving, f);
    }

    @Override
    protected ResourceLocation getEntityTexture(T t) {
        return skin;
    }
}
