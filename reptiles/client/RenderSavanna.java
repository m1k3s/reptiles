/*
 * RenderSavanna.java
 *
 *  Copyright (c) 2017 Michael Sheppard
 *
 * =====GPLv3===========================================================
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

import com.reptiles.common.EntitySavanna;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class RenderSavanna<T extends EntitySavanna> extends RenderLiving<T> {

    private static final ResourceLocation skin = new ResourceLocation("reptilemod", "textures/entity/reptiles/savanna32.png");

    public RenderSavanna(RenderManager rm) {
        super(rm, new ModelSavanna(), 0.0f);
    }

    @Override
    protected void preRenderCallback(T entityliving, float f) {
        float scaleFactor = entityliving.getScaleFactor();
        GlStateManager.scale(scaleFactor, scaleFactor, scaleFactor);
        super.preRenderCallback(entityliving, f);
    }

    @Override
    protected ResourceLocation getEntityTexture(@Nonnull T t) {
        return skin;
    }
}
