// RenderLargeCroc.java
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

import com.reptiles.common.EntityLargeCroc;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderLargeCroc<T extends EntityLargeCroc> extends RenderLiving<T> {

    private static final ResourceLocation skin = new ResourceLocation("reptilemod", "textures/entity/reptiles/largecroc32.png");
    //	private static final ResourceLocation eyes = new ResourceLocation("reptilemod", "textures/entity/reptiles/croc_eyes32.png");
    private final float scaleFactor = 1.5F;

    public RenderLargeCroc(RenderManager rm) {
        super(rm, new ModelLargeCroc(), 0.0f);
        addLayer(new LayerLargeCrocEyes(this));
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
