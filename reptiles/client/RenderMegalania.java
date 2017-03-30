/*
 * RenderMegalania.java
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

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import com.reptiles.common.EntityMegalania;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;

import javax.annotation.Nonnull;

public class RenderMegalania<T extends EntityMegalania> extends RenderLiving<T> {

    private static final ResourceLocation skin = new ResourceLocation("reptilemod", "textures/entity/reptiles/megalania32.png");

    public RenderMegalania(RenderManager rm) {
        super(rm, new ModelMegalania(), 0.0f);
    }

    public void renderMegalania(T entitymegalania, double d, double d1, double d2, float f, float f1) {
        super.doRender(entitymegalania, d, d1, d2, f, f1);
    }

    @Override
    public void doRender(@Nonnull T entityliving, double d, double d1, double d2, float f, float f1) {
        renderMegalania(entityliving, d, d1, d2, f, f1);
    }

    @Override
    protected ResourceLocation getEntityTexture(@Nonnull T t) {
        return skin;
    }

    @Override
    protected void preRenderCallback(T entitylivingbase, float f) {
        GlStateManager.scale(2.5, 2.5, 3.0);
        super.preRenderCallback(entitylivingbase, f);
    }
}
