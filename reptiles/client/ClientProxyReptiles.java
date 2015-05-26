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

import com.reptiles.common.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
//import cpw.mods.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
//import cpw.mods.fml.common.event.FMLMissingMappingsEvent;

public class ClientProxyReptiles extends CommonProxyReptiles {

	@Override
	public void registerRenderers()
	{
		float shadowSize = 0.0F;
		RenderManager rm = Minecraft.getMinecraft().getRenderManager();
		RenderingRegistry.registerEntityRenderingHandler(EntityKomodo.class, new RenderKomodo(rm, new ModelKomodo(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntitySavanna.class, new RenderSavanna(rm, new ModelSavanna(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityPerentie.class, new RenderPerentie(rm, new ModelPerentie(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityGriseus.class, new RenderGriseus(rm, new ModelGriseus(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntitySalvadorii.class, new RenderSalvadorii(rm, new ModelSalvadorii(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityLace.class, new RenderLace(rm, new ModelLace(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityCroc.class, new RenderCroc(rm, new ModelCroc(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityLargeCroc.class, new RenderLargeCroc(rm, new ModelLargeCroc(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityDesertTortoise.class, new RenderTurtle(rm, new ModelTurtle(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityLittleTurtle.class, new RenderLittleTurtle(rm, new ModelLittleTurtle(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityIguana.class, new RenderIguana(rm, new ModelIguana(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityTortoise.class, new RenderTortoise(rm, new ModelTortoise(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityGator.class, new RenderGator(rm, new ModelGator(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityChameleon.class, new RenderChameleon(rm, new ModelChameleon(), shadowSize));
	}
	
	@Override
	public void registerHandlers()
	{
		// allow this mod to load if there are missing mappings
//		FMLClientHandler.instance().setDefaultMissingAction(FMLMissingMappingsEvent.Action.IGNORE);
	}

}
