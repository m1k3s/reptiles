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
//import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
//import cpw.mods.fml.common.event.FMLMissingMappingsEvent;

public class ClientProxyReptiles extends CommonProxyReptiles {

	@Override
	public void registerRenderers()
	{
		float shadowSize = 0.0F;
		RenderingRegistry.registerEntityRenderingHandler(EntityKomodo.class, new RenderKomodo(new ModelKomodo(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntitySavanna.class, new RenderSavanna(new ModelSavanna(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityPerentie.class, new RenderPerentie(new ModelPerentie(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityGriseus.class, new RenderGriseus(new ModelGriseus(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntitySalvadorii.class, new RenderSalvadorii(new ModelSalvadorii(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityLace.class, new RenderLace(new ModelLace(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityMegalania.class, new RenderMegalania(new ModelMegalania(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityCroc.class, new RenderCroc(new ModelCroc(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityLargeCroc.class, new RenderLargeCroc(new ModelLargeCroc(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityDesertTortoise.class, new RenderTurtle(new ModelTurtle(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityLittleTurtle.class, new RenderLittleTurtle(new ModelLittleTurtle(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityIguana.class, new RenderIguana(new ModelIguana(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityTortoise.class, new RenderTortoise(new ModelTortoise(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityGator.class, new RenderGator(new ModelGator(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityChameleon.class, new RenderChameleon(new ModelChameleon(), shadowSize));
	}
	
	@Override
	public void registerHandlers()
	{
		// allow this mod to load if there are missing mappings
//		FMLClientHandler.instance().setDefaultMissingAction(FMLMissingMappingsEvent.Action.IGNORE);
	}

}
