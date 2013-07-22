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

package reptiles.common;

import java.util.LinkedList;
//import java.util.logging.Level;
//import java.util.logging.Logger;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.*;
//import net.minecraftforge.common.BiomeDictionary;
//import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.*;
import cpw.mods.fml.common.SidedProxy;

@Mod(
	modid = Reptiles.modid, 
	name = Reptiles.name, 
	version = Reptiles.version
)

@NetworkMod(
	clientSideRequired = true, 
	serverSideRequired = false
)

public class Reptiles {
	
	public String getVersion() {
		return Reptiles.version;
	}
	
	@Instance
	public static Reptiles instance;
	
	public static final String modid = "ReptileMod";
	public static final String name = "Reptile Mod";
	public static final String version = "1.6.2";

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
	private int salvadoriiSpawnProb;
	
//	private Logger logger;
	
	@SidedProxy(
		clientSide = "reptiles.client.ClientProxyReptiles",
		serverSide = "reptiles.common.CommonProxyReptiles"
	)
	
	public static CommonProxyReptiles proxy;

	public Reptiles() {
	}

	@EventHandler
	public void preLoad(FMLPreInitializationEvent event) {
//		logger = Logger.getLogger(Reptiles.modid);
//		logger.setParent(FMLLog.getLogger());
		
		String comments = Reptiles.name + " Config\n Michael Sheppard (crackedEgg)\n"
										+ "Set xxxSpawnProb to zero to disable spawn of that entity\n";
		
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		komodoSpawnProb = config.get(Configuration.CATEGORY_GENERAL, "komodoSpawnProb", 10).getInt();
		griseusSpawnProb = config.get(Configuration.CATEGORY_GENERAL, "griseusSpawnProb", 12).getInt();
		laceSpawnProb = config.get(Configuration.CATEGORY_GENERAL, "laceSpawnProb", 12).getInt();
		perentieSpawnProb = config.get(Configuration.CATEGORY_GENERAL, "perentieSpawnProb", 12).getInt();
		savannaSpawnProb = config.get(Configuration.CATEGORY_GENERAL, "savannaSpawnProb", 12).getInt();
		crocSpawnProb = config.get(Configuration.CATEGORY_GENERAL, "crocSpawnProb", 5).getInt();
		largeCrocSpawnProb = config.get(Configuration.CATEGORY_GENERAL, "largeCrocSpawnProb", 4).getInt();
		desertTortoiseSpawnProb = config.get(Configuration.CATEGORY_GENERAL, "desertTortoiseSpawnProb", 12).getInt();
		littleTurtleSpawnProb = config.get(Configuration.CATEGORY_GENERAL, "littleTurtleSpawnProb", 10).getInt();
		iguanaSpawnProb = config.get(Configuration.CATEGORY_GENERAL, "iguanaSpawnProb", 12).getInt();
		tortoiseSpawnProb = config.get(Configuration.CATEGORY_GENERAL, "tortoiseSpawnProb", 12).getInt();
		gatorSpawnProb = config.get(Configuration.CATEGORY_GENERAL, "gatorSpawnProb", 5).getInt();
		chameleonSpawnProb = config.get(Configuration.CATEGORY_GENERAL, "chameleonSpawnProb", 12).getInt();
		salvadoriiSpawnProb = config.get(Configuration.CATEGORY_GENERAL, "salvadoriiSpawnProb", 12).getInt();
		
		config.addCustomCategoryComment(Configuration.CATEGORY_GENERAL, comments);
		
		config.save();
		
		proxy.registerRenderers();
		proxy.registerSounds();
		proxy.registerLogger();
	}

	@EventHandler
	public void load(FMLInitializationEvent evt) {
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

		// add language localization
		LanguageRegistry.instance().addStringLocalization("entity.Komodo.name", "Komodo");
		LanguageRegistry.instance().addStringLocalization("entity.Savanna.name", "Savanna");
		LanguageRegistry.instance().addStringLocalization("entity.Griseus.name", "Griseus");
		LanguageRegistry.instance().addStringLocalization("entity.Perentie.name", "Perentie");
		LanguageRegistry.instance().addStringLocalization("entity.Lace.name", "Lace");
		LanguageRegistry.instance().addStringLocalization("entity.Iguana.name", "Iguana");
		LanguageRegistry.instance().addStringLocalization("entity.Chameleon.name", "Chameleon");
		LanguageRegistry.instance().addStringLocalization("entity.Croc.name", "Crocodile");
		LanguageRegistry.instance().addStringLocalization("entity.DesertTortoise.name", "Desert Tortoise");
		LanguageRegistry.instance().addStringLocalization("entity.LittleTurtle.name", "Little Turtle");
		LanguageRegistry.instance().addStringLocalization("entity.LargeCroc.name", "Large Crocodile");
		LanguageRegistry.instance().addStringLocalization("entity.Alligator.name", "Alligator");
		LanguageRegistry.instance().addStringLocalization("entity.Tortoise.name", "Tortoise");
		LanguageRegistry.instance().addStringLocalization("entity.CrocMonitor.name", "Crocodile Monitor");

		proxy.print("*** Scanning for monitor biomes");
		BiomeGenBase[] monitorBiomes = getBiomes(false, false);

		proxy.print("*** Scanning for tortoise biomes");
		BiomeGenBase[] tortoiseBiomes = getBiomes(true, false);

		proxy.print("*** Scanning for turtle biomes");
		BiomeGenBase[] turtleBiomes = getBiomes(false, false);

		proxy.print("*** Scanning for lizard biomes");
		BiomeGenBase[] lizardBiomes = getBiomes(false, true);

		proxy.print("*** Scanning for crocodilian biomes");
		BiomeGenBase[] crocBiomes = getBiomes(false, true);

		addSpawn(EntityKomodo.class, komodoSpawnProb, 1, 4, monitorBiomes);
		addSpawn(EntitySavanna.class, savannaSpawnProb, 1, 4, monitorBiomes);
		addSpawn(EntityGriseus.class, griseusSpawnProb, 1, 4, tortoiseBiomes);
		addSpawn(EntityPerentie.class, perentieSpawnProb, 1, 4, monitorBiomes);
		addSpawn(EntityLace.class, laceSpawnProb, 1, 4, monitorBiomes);
		addSpawn(EntitySalvadorii.class, salvadoriiSpawnProb, 1, 4, monitorBiomes);

		addSpawn(EntityCroc.class, crocSpawnProb, 1, 2, crocBiomes);
		addSpawn(EntityLargeCroc.class, largeCrocSpawnProb, 1, 2, crocBiomes);
		addSpawn(EntityGator.class, gatorSpawnProb, 1, 2, crocBiomes);

		addSpawn(EntityDesertTortoise.class, desertTortoiseSpawnProb, 1, 4, tortoiseBiomes);
		addSpawn(EntityLittleTurtle.class, littleTurtleSpawnProb, 1, 4, turtleBiomes);
		addSpawn(EntityTortoise.class, tortoiseSpawnProb, 1, 4, turtleBiomes);

		addSpawn(EntityIguana.class, iguanaSpawnProb, 1, 4, lizardBiomes);
		addSpawn(EntityChameleon.class, chameleonSpawnProb, 1, 4, lizardBiomes);
	}
	
//	@EventHandler
//	public void PostInit(FMLPostInitializationEvent event) {
//		BiomeDictionary.registerAllBiomes();
//	}
	

	public void registerEntity(Class<? extends Entity> entityClass, String entityName, int bkEggColor, int fgEggColor) {
		int id = EntityRegistry.findGlobalUniqueEntityId();
//		int trackingRange = 80;
//		int updateFreq = 3;
//		boolean sendsVelUpdates = true;
		
		EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);
//		EntityRegistry.registerModEntity(entityClass, entityName, id, this, trackingRange, updateFreq, sendsVelUpdates);
		EntityList.entityEggs.put(Integer.valueOf(id), new EntityEggInfo(id, bkEggColor, fgEggColor));
	}

	public void addSpawn(Class<? extends EntityLiving> entityClass, int spawnProb, int min, int max, BiomeGenBase[] biomes) {
		if (spawnProb > 0) {
			EntityRegistry.addSpawn(entityClass, spawnProb, min, max, EnumCreatureType.creature, biomes);
		}
	}
	
	public BiomeGenBase[] getBiomes(boolean onlyDry, boolean onlyWet) {
		LinkedList linkedlist = new LinkedList();
//		BiomeGenBase[] biomes = BiomeDictionary.getBiomesForType(Type.FROZEN);
		for (BiomeGenBase biomegenbase : BiomeGenBase.biomeList) {
			if (biomegenbase == null) {
				continue;
			}
			if (!excludedBiome(biomegenbase, onlyDry, onlyWet)) {
				linkedlist.add(biomegenbase);
				proxy.print(" >>> Adding " + biomegenbase.biomeName + " for spawning");
			}
		}
		return (BiomeGenBase[]) linkedlist.toArray(new BiomeGenBase[0]);
	}
	
	public boolean excludedBiome(BiomeGenBase biome, boolean onlyDry, boolean onlyWet) {
		// always exclude Hell and the End 
		if ((biome instanceof BiomeGenHell) || (biome instanceof BiomeGenEnd)) {
			return true;
		}
		// always exclude ocean and all cold biomes
		if ((biome.biomeID == 0) || biome.getEnableSnow() || biome.getFloatTemperature() < 0.2) {
			return true;
		}
		// this should exclude desert-like biomes when the onlyDry flag is false
		if (!onlyDry && (biome.getFloatTemperature() > 1.5) && !biome.isHighHumidity()) {
			return true;
		}
		// This should exclude all biomes that are not desert-like
		if (onlyDry && (biome.getFloatTemperature() < 1.5)) {
			return true;
		}
		// this should exclude all biomes that have a low humidity
		if (onlyWet && !biome.isHighHumidity()) {
			return true;
		}
		
		return false;
	}
	
}
