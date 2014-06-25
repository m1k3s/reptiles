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
// Copyright 2011-2014 Michael Sheppard (crackedEgg)
//
package com.reptiles.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.*;
import java.util.LinkedList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.*;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.config.Configuration;

@Mod(
		modid = Reptiles.modid,
		name = Reptiles.name,
		version = Reptiles.version
)

public class Reptiles {

	public String getVersion()
	{
		return Reptiles.version;
	}

	public static final String modid = "reptilemod";
	public static final String name = "Reptile Mod";
	public static final String version = "1.7.10";
	
	@Mod.Instance(modid)
	public static Reptiles instance;

	private int komodoSpawnProb;
	private int griseusSpawnProb;
	private int laceSpawnProb;
	private int perentieSpawnProb;
	private int savannaSpawnProb;
	private int crocSpawnProb;
	private int largeCrocSpawnProb;
	private int desertTortoiseSpawnProb;
	private int littleTurtleSpawnProb;
	private int iguanaSpawnProb;
	private int tortoiseSpawnProb;
	private int gatorSpawnProb;
	private int chameleonSpawnProb;
	private int crocMonitorSpawnProb;
	private int megalaniaSpawnProb;
	private boolean despawn;
	private boolean randomScale;
	private boolean followOwner;
	
	@SidedProxy(
			clientSide = "com.reptiles.client.ClientProxyReptiles",
			serverSide = "com.reptiles.common.CommonProxyReptiles"
	)

	public static CommonProxyReptiles proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		String comments = Reptiles.name + " Config\n Michael Sheppard (crackedEgg)\n"
				+ " For Minecraft Version " + Reptiles.version;
				
		String randomScaleComment = "Set to false to disable random scaling of monitors, default is true.";
		String despawnComment = "Set to false to not despawn. default is true.";
		String followOwnerComment = "Set to false to have tamed monitors not follow owner, default is true.";

		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		config.addCustomCategoryComment("Spawning", "Spawn Probabilities\nSet xxxSpawnProb to zero to disable spawning of that entity");
		config.addCustomCategoryComment("Misc", "Miscellaneous Variables");

		komodoSpawnProb = config.get("Spawning", "komodoSpawnProb", 10).getInt();
		griseusSpawnProb = config.get("Spawning", "griseusSpawnProb", 12).getInt();
		laceSpawnProb = config.get("Spawning", "laceSpawnProb", 12).getInt();
		perentieSpawnProb = config.get("Spawning", "perentieSpawnProb", 12).getInt();
		savannaSpawnProb = config.get("Spawning", "savannaSpawnProb", 12).getInt();
		crocSpawnProb = config.get("Spawning", "crocSpawnProb", 5).getInt();
		largeCrocSpawnProb = config.get("Spawning", "largeCrocSpawnProb", 4).getInt();
		desertTortoiseSpawnProb = config.get("Spawning", "desertTortoiseSpawnProb", 12).getInt();
		littleTurtleSpawnProb = config.get("Spawning", "littleTurtleSpawnProb", 10).getInt();
		iguanaSpawnProb = config.get("Spawning", "iguanaSpawnProb", 12).getInt();
		tortoiseSpawnProb = config.get("Spawning", "tortoiseSpawnProb", 12).getInt();
		gatorSpawnProb = config.get("Spawning", "gatorSpawnProb", 5).getInt();
		chameleonSpawnProb = config.get("Spawning", "chameleonSpawnProb", 12).getInt();
		crocMonitorSpawnProb = config.get("Spawning", "crocMonitorSpawnProb", 12).getInt();
		megalaniaSpawnProb = config.get("Spawning", "megalaniaSpawnProb", 12).getInt();
		
		randomScale = config.get("Misc", "randomScale", true, randomScaleComment).getBoolean(true);
		despawn = config.get("Misc", "despawn", true, despawnComment).getBoolean(true);
		followOwner = config.get("Misc", "followOwner", true, followOwnerComment).getBoolean(true);

		config.addCustomCategoryComment("Information", comments);

		config.save();

		proxy.registerRenderers();
		proxy.registerHandlers();
	}

	@EventHandler
	public void Init(FMLInitializationEvent evt)
	{
		registerEntity(EntityKomodo.class, "Komodo", 0x006400, 0x98FB98);
		registerEntity(EntitySavanna.class, "Savanna", 0x8B8989, 0xCDC5BF);
		registerEntity(EntityGriseus.class, "Griseus", 0xCD853F, 0xDEB887);
		registerEntity(EntityPerentie.class, "Perentie", 0x363636, 0x7F7F7F);
		registerEntity(EntityLace.class, "Lace", 0x0A0A0A, 0xABABAB);
		registerEntity(EntityCroc.class, "Croc", 0x008B00, 0xA2CD5A);
		registerEntity(EntityDesertTortoise.class, "DesertTortoise", 0x8B4513, 0x8B4C39);
		registerEntity(EntityLittleTurtle.class, "LittleTurtle", 0xFF7F24, 0xFF8C69);
		registerEntity(EntityLargeCroc.class, "LargeCroc", 0x8B4513, 0x8B5A2B);
		registerEntity(EntityIguana.class, "Iguana", 0x00CD00, 0xC0FF3E);
		registerEntity(EntityTortoise.class, "Tortoise", 0x008B45, 0xC0FF3E);
		registerEntity(EntityGator.class, "Alligator", 0x008B45, 0xC0FF3E);
		registerEntity(EntityChameleon.class, "Chameleon", 0xB22222, 0x228B22);
		registerEntity(EntitySalvadorii.class, "CrocMonitor", 0x008BCC, 0xA2CD5A);
		registerEntity(EntityMegalania.class, "Megalania", 0x050505, 0x05c505);
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event)
	{
		BiomeDictionary.registerAllBiomesAndGenerateEvents();
		
		proxy.print("*** Checking for monitor biomes");
		BiomeGenBase[] monitorBiomes = getBiomes(Type.FOREST, Type.JUNGLE, Type.BEACH, Type.PLAINS);

		proxy.print("*** Checking for tortoise biomes");
		BiomeGenBase[] tortoiseBiomes = getBiomes(Type.DESERT, Type.WASTELAND);

		proxy.print("*** Checking for turtle biomes");
		BiomeGenBase[] turtleBiomes = getBiomes(Type.FOREST, Type.JUNGLE, Type.SWAMP);

		proxy.print("*** Checking for lizard biomes");
		BiomeGenBase[] lizardBiomes = getBiomes(Type.FOREST, Type.HILLS, Type.JUNGLE, Type.MUSHROOM, Type.PLAINS, Type.MOUNTAIN);

		proxy.print("*** Checking for crocodilian biomes");
		BiomeGenBase[] crocBiomes = getBiomes(Type.BEACH, Type.SWAMP, Type.MUSHROOM);

		addSpawn(EntityKomodo.class, komodoSpawnProb, 1, 4, monitorBiomes);
		addSpawn(EntitySavanna.class, savannaSpawnProb, 1, 4, monitorBiomes);
		addSpawn(EntityGriseus.class, griseusSpawnProb, 1, 4, tortoiseBiomes);
		addSpawn(EntityPerentie.class, perentieSpawnProb, 1, 4, monitorBiomes);
		addSpawn(EntityLace.class, laceSpawnProb, 1, 4, monitorBiomes);
		addSpawn(EntitySalvadorii.class, crocMonitorSpawnProb, 1, 4, monitorBiomes);
		addSpawn(EntityMegalania.class, megalaniaSpawnProb, 1, 2, monitorBiomes);

		addSpawn(EntityCroc.class, crocSpawnProb, 1, 2, crocBiomes);
		addSpawn(EntityLargeCroc.class, largeCrocSpawnProb, 1, 2, crocBiomes);
		addSpawn(EntityGator.class, gatorSpawnProb, 1, 2, crocBiomes);

		addSpawn(EntityDesertTortoise.class, desertTortoiseSpawnProb, 1, 4, tortoiseBiomes);
		addSpawn(EntityLittleTurtle.class, littleTurtleSpawnProb, 1, 4, turtleBiomes);
		addSpawn(EntityTortoise.class, tortoiseSpawnProb, 1, 4, turtleBiomes);

		addSpawn(EntityIguana.class, iguanaSpawnProb, 1, 4, lizardBiomes);
		addSpawn(EntityChameleon.class, chameleonSpawnProb, 1, 4, lizardBiomes);
	}
	
	public void registerEntity(Class<? extends Entity> entityClass, String entityName, int bkEggColor, int fgEggColor)
	{
		int id = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(entityClass, entityName, id, bkEggColor, fgEggColor);
//		EntityRegistry.registerModEntity(entityClass, entityName, id, this, 80, 3, true);
	}

	public void addSpawn(Class<? extends EntityLiving> entityClass, int spawnProb, int min, int max, BiomeGenBase[] biomes)
	{
		if (spawnProb > 0) {
			EntityRegistry.addSpawn(entityClass, spawnProb, min, max, EnumCreatureType.creature, biomes);
		}
	}

	public BiomeGenBase[] getBiomes(Type... types)
	{
		LinkedList<BiomeGenBase> list = new LinkedList<BiomeGenBase>();
		for (Type t : types) {
			BiomeGenBase[] biomes = BiomeDictionary.getBiomesForType(t);
			for (BiomeGenBase bgb : biomes) {
				if (BiomeDictionary.isBiomeOfType(bgb, Type.FROZEN) || bgb.temperature < 0.32F) { // exclude cold climates
//					proxy.print("  <<< Excluding " + bgb.biomeName + " for spawning");
					continue;
				}
				if (BiomeDictionary.isBiomeOfType(bgb, Type.WATER)) { // exclude ocean biomes
//					proxy.print("  <<< Excluding " + bgb.biomeName + " for spawning");
					continue;
				}
				if (!list.contains(bgb)) {
					list.add(bgb);
					proxy.print("  >>> Adding " + bgb.biomeName + " for spawning");
				}
			}
		}
		return (BiomeGenBase[]) list.toArray(new BiomeGenBase[0]);
	}
	
	public boolean useRandomScaling()
	{
		return randomScale;
	}
	
	public boolean shouldDespawn()
	{
		return despawn;
	}
	
	public boolean getFollowOwner()
	{
		return followOwner;
	}
	
}
