/*
 * RenderChameleon.java
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

import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import com.reptiles.common.EntityChameleon;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.BiomeColorHelper;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;
import java.nio.FloatBuffer;

public class RenderChameleon<T extends EntityChameleon> extends RenderLiving<T> {

    private static final ResourceLocation skin = new ResourceLocation("reptilemod", "textures/entity/reptiles/greyscale_chameleon.png");
    private FloatBuffer currentGLColor = BufferUtils.createFloatBuffer(16);
    float scaleFactor = 0.25f;

    @SuppressWarnings("unchecked")
    public RenderChameleon(RenderManager rm) {
        super(rm, new ModelChameleon(), 0.0f);
        addLayer(new LayerChameleonSkin(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(@Nonnull T t) {
        return skin;
    }

    @Override
    protected void preRenderCallback(T entityliving, float f) {
        GlStateManager.scale(scaleFactor, scaleFactor, scaleFactor);
        super.preRenderCallback(entityliving, f);
    }

    // credit for the chameleon color changing algorithm goes to Nikos creator of the ChameleonCreeper mod
    @Override
    protected void renderModel(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        boolean isNotInvisible = !entity.isInvisible();
        boolean isNotInvisibleToPlayer = !isNotInvisible && !entity.isInvisibleToPlayer(Minecraft.getMinecraft().player);

        if (isNotInvisible || isNotInvisibleToPlayer) {
            if (!bindEntityTexture(entity)) {
                return;
            }

            if (isNotInvisibleToPlayer) {
                GlStateManager.pushMatrix();
                GlStateManager.color(1.0F, 1.0F, 1.0F, 0.15F);
                GlStateManager.depthMask(false);
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                GlStateManager.alphaFunc(GL11.GL_GREATER, 0.003921569F);
            }
            if (entity.hurtTime <= 0 || entity.deathTime > 0) {
                // Save the current OpenGL color to re-set it later
                GL11.glGetFloat(GL11.GL_CURRENT_COLOR, currentGLColor);

                int[] colorTint = getBlockBiomeColors(entity);
                GL11.glColor4f(colorTint[0] / 255.f, colorTint[1] / 255.f, colorTint[2] / 255.f, 1.0F);
            }

            mainModel.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

            // Re-set the GL color
            GL11.glColor4f(currentGLColor.get(0), currentGLColor.get(1), currentGLColor.get(2), currentGLColor.get(3));

            if (isNotInvisibleToPlayer) {
                GlStateManager.disableBlend();
                GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
                GlStateManager.popMatrix();
                GlStateManager.depthMask(true);
            }
        }
    }

    // ripped from the BiomeColors class in ChameleonCreeper mod source and slightly modified to suit the chameleon
    // credit for this chameleon color changing algorithm goes to Nikos creator of the ChameleonCreeper mod
    public static int[] getBlockBiomeColors(EntityLivingBase chameleon) {
        double entityX = chameleon.posX;
        double entityY = chameleon.posY;
        double entityZ = chameleon.posZ;

        int red = 0;
        int green = 0;
        int blue = 0;

        int currColor;
        int blockCount = 27; // 3 * 3 * 3

        for (int x = -1; x <= 1; ++x) {
            for (int y = 0; y <= 2; ++y) {
                for (int z = -1; z <= 1; ++z) {
                    IBlockState iBlockState = chameleon.world.getBlockState(new BlockPos(entityX + x, entityY + y - 0.5, entityZ + z));
                    Block block = iBlockState.getBlock();

                    if (block instanceof BlockAir) {
                        blockCount--;
                        continue;
                    }

                    if (block instanceof BlockGrass || block instanceof BlockTallGrass) {
                        currColor = BiomeColorHelper.getGrassColorAtPos(chameleon.world, new BlockPos(entityX + x, entityY + y, entityZ + z));
                    } else if (block instanceof BlockLeaves) {
                        currColor = BiomeColorHelper.getFoliageColorAtPos(chameleon.world, new BlockPos(entityX + x, entityY + y, entityZ + z));
                    } else {
                        currColor = iBlockState.getMapColor(chameleon.world, new BlockPos(entityX + x, entityY + y, entityZ + z)).colorValue;
                    }
                    red += (currColor & 0xFF0000) >> 16;
                    green += (currColor & 0xFF00) >> 8;
                    blue += currColor & 0xFF;
                }
            }
        }

        // default to grey color if the Chameleon is falling through the air (without any blocks near it)
        // also has the benefit of avoiding a divide by zero
        if (blockCount == 0) {
            return new int[] { 135, 135, 135 };
        }

        red /= blockCount;
        green /= blockCount;
        blue /= blockCount;

        return new int[] { red, green, blue };
    }
}
