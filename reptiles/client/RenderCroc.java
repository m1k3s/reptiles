/*
 * RenderCroc.java
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
import com.reptiles.common.EntityCroc;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class RenderCroc<T extends EntityCroc> extends RenderLiving<T> {

    private static final ResourceLocation skin = new ResourceLocation("reptilemod", "textures/entity/reptiles/croc32.png");
//	private static final ResourceLocation eyes = new ResourceLocation("reptilemod", "textures/entity/reptiles/croc_eyes32.png");

    @SuppressWarnings("unchecked")
    public RenderCroc(RenderManager rm) {
        super(rm, new ModelCroc(), 0.0f);
        addLayer(new LayerCrocEyes(this));
    }


    @Override
    protected float getDeathMaxRotation(T entityLivingBase) {
        return 180.0F;
    }

    @Override
    protected ResourceLocation getEntityTexture(@Nonnull T entity) {
        return skin;
    }
}
