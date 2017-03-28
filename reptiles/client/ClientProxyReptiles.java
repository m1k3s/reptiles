/*
 * ClientProxyReptiles.java
 *
 *  Copyright (c) 2017 Michael Sheppard
 *
 * =====GPL=============================================================
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
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.RenderingRegistry;


@SuppressWarnings("unused")
public class ClientProxyReptiles extends CommonProxyReptiles {

	@Override
	public void registerRenderers()
	{
		RenderManager rm = Minecraft.getMinecraft().getRenderManager();
		RenderingRegistry.registerEntityRenderingHandler(EntityKomodo.class, RenderKomodo::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySavanna.class, RenderSavanna::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPerentie.class, RenderPerentie::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityGriseus.class, RenderGriseus::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySalvadorii.class, RenderSalvadorii::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityLace.class, RenderLace::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityCroc.class, RenderCroc::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityLargeCroc.class, RenderLargeCroc::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDesertTortoise.class, RenderTurtle::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityLittleTurtle.class, RenderLittleTurtle::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityIguana.class, RenderIguana::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTortoise.class, RenderTortoise::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityGator.class, RenderGator::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityChameleon.class, RenderChameleon::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityMegalania.class, RenderMegalania::new);
	}
	
}
