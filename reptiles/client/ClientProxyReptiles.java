/*
 * ClientProxyReptiles.java
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

import com.reptiles.common.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;


@SuppressWarnings("unused")
public class ClientProxyReptiles implements IProxy {

    public void registerRenderers() {
        RenderManager rm = Minecraft.getMinecraft().getRenderManager();
        RenderingRegistry.registerEntityRenderingHandler(EntityKomodo.class, RenderKomodo::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySavanna.class, RenderSavanna::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPerentie.class, RenderPerentie::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGriseus.class, RenderGriseus::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySalvadorii.class, RenderSalvadorii::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityLace.class, RenderLace::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCrocBase.class, RenderCroc::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityLargeCroc.class, RenderLargeCroc::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDesertTortoise.class, RenderTurtle::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityLittleTurtle.class, RenderLittleTurtle::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityIguana.class, RenderIguana::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTortoise.class, RenderTortoise::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGator.class, RenderGator::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityChameleon.class, RenderChameleon::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMegalania.class, RenderMegalania::new);

        ModelResourceLocation cookedMeatResource = new ModelResourceLocation(Reptiles.MODID + ":" + Reptiles.reptileCookedName);
        ModelLoader.setCustomModelResourceLocation(Reptiles.REPTILE_MEAT_COOKED, 0, cookedMeatResource);

        ModelResourceLocation rawMeatResource = new ModelResourceLocation(Reptiles.MODID + ":" + Reptiles.reptileRawName);
        ModelLoader.setCustomModelResourceLocation(Reptiles.REPTILE_MEAT_RAW, 0, rawMeatResource);

        ModelResourceLocation reptileHideResource = new ModelResourceLocation(Reptiles.MODID + ":" + Reptiles.reptileHideName);
        ModelLoader.setCustomModelResourceLocation(Reptiles.REPTILE_LEATHER, 0, reptileHideResource);

        ModelResourceLocation cookedTurtleResource = new ModelResourceLocation(Reptiles.MODID + ":" + Reptiles.turtleCookedName);
        ModelLoader.setCustomModelResourceLocation(Reptiles.TURTLE_MEAT_COOKED, 0, cookedTurtleResource);

        ModelResourceLocation rawTurtleResource = new ModelResourceLocation(Reptiles.MODID + ":" + Reptiles.turtleRawName);
        ModelLoader.setCustomModelResourceLocation(Reptiles.TURTLE_MEAT_RAW, 0, rawTurtleResource);

        ModelResourceLocation turtleHideResource = new ModelResourceLocation(Reptiles.MODID + ":" + Reptiles.turtleHideName);
        ModelLoader.setCustomModelResourceLocation(Reptiles.TURTLE_LEATHER, 0, turtleHideResource);

        ModelResourceLocation cookedCrocResource = new ModelResourceLocation(Reptiles.MODID + ":" + Reptiles.crocCookedName);
        ModelLoader.setCustomModelResourceLocation(Reptiles.CROC_MEAT_COOKED, 0, cookedCrocResource);

        ModelResourceLocation rawCrocResource = new ModelResourceLocation(Reptiles.MODID + ":" + Reptiles.crocRawName);
        ModelLoader.setCustomModelResourceLocation(Reptiles.CROC_MEAT_RAW, 0, rawCrocResource);

        ModelResourceLocation crocHideResource = new ModelResourceLocation(Reptiles.MODID + ":" + Reptiles.crocHideName);
        ModelLoader.setCustomModelResourceLocation(Reptiles.CROC_LEATHER, 0, crocHideResource);

        ModelResourceLocation cookedMegaResource = new ModelResourceLocation(Reptiles.MODID + ":" + Reptiles.megaCookedName);
        ModelLoader.setCustomModelResourceLocation(Reptiles.MEGA_MEAT_COOKED, 0, cookedMegaResource);

        ModelResourceLocation rawMegaResource = new ModelResourceLocation(Reptiles.MODID + ":" + Reptiles.megaRawName);
        ModelLoader.setCustomModelResourceLocation(Reptiles.MEGA_MEAT_RAW, 0, rawMegaResource);

        ModelResourceLocation megaHideResource = new ModelResourceLocation(Reptiles.MODID + ":" + Reptiles.megaHideName);
        ModelLoader.setCustomModelResourceLocation(Reptiles.MEGA_LEATHER, 0, megaHideResource);
    }

    @Override
    public void preInit() {
        registerRenderers();
    }

    @Override
    public void Init() {

    }

    @Override
    public void postInit() {

    }
}
