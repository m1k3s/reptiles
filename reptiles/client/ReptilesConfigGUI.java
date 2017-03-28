// ReptilesConfigGUI.java
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

import com.reptiles.common.ConfigHandler;
import com.reptiles.common.Reptiles;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ReptilesConfigGUI extends GuiConfig {

	public ReptilesConfigGUI(GuiScreen parentScreen)
	{
		super(parentScreen,
				new ConfigElement(ConfigHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
				Reptiles.modid, true, true, GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()),
				"Changes here require a restart before they take effect!");
	}
	
}
