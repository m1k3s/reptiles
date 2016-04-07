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
//
//
package com.reptiles.common;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

public class CommonProxyReptiles {
	
	private static final Logger logger = FMLLog.getLogger();
	public static SoundEvent varanus_hiss;
	public static SoundEvent varanus_hurt;
	public static SoundEvent croc_growl;
	public static SoundEvent mega_growl;
	public static SoundEvent mega_purr;
	public static SoundEvent mega_death;

	public void registerRenderers()
	{
	}
	
	public void registerSoundEvents()
	{
		varanus_hiss = registerSoundEvent(Reptiles.modid + ":varanus.hiss");
		varanus_hurt = registerSoundEvent(Reptiles.modid + ":varanus.hurt");
		croc_growl = registerSoundEvent(Reptiles.modid + ":croc.growl");
		mega_growl = registerSoundEvent(Reptiles.modid + ":mega.growl");
		mega_purr = registerSoundEvent(Reptiles.modid + ":mega.purr");
		mega_death = registerSoundEvent(Reptiles.modid + ":mega.death");
	}

	public void info(String s)
	{
		logger.info(s);
	}
	
	public void warn(String s)
	{
		logger.warn(s);
	}
	
	public void error(String s)
	{
		logger.error(s);
	}

	private SoundEvent registerSoundEvent(String sound) {
		ResourceLocation resourceLocation = new ResourceLocation(sound);
		SoundEvent soundEvent = new SoundEvent(resourceLocation).setRegistryName(resourceLocation);
		GameRegistry.register(soundEvent);
		return soundEvent;
	}

//	public SoundEvent getRegisteredSoundEvent(ResourceLocation resourceLocation) {
//		SoundEvent sound =  SoundEvent.soundEventRegistry.getObject(resourceLocation);
//		if (sound == null) {
//			Reptiles.proxy.error("SoundEvent " + resourceLocation.getResourcePath() + " was NOT found!");
//		}
//		return sound;
//	}
}
