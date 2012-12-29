package reptiles.common;

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.*;
import net.minecraftforge.common.Configuration;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

import cpw.mods.fml.common.registry.*;
import cpw.mods.fml.common.event.*;
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
	public static final String version = "1.4.7";

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
	
	@SidedProxy(
		clientSide = "reptiles.client.ClientProxyReptiles",
		serverSide = "reptiles.common.CommonProxyReptiles"
	)
	
	public static CommonProxyReptiles proxy;

	public Reptiles() {
	}

	@PreInit
	public void preLoad(FMLPreInitializationEvent event) {
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
		
		config.addCustomCategoryComment(Configuration.CATEGORY_GENERAL, comments);
		
		config.save();
		
		proxy.registerRenderers();
		proxy.registerSounds();
	}

	@Init
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

		BiomeGenBase[] monitorBiomes = {
			BiomeGenBase.beach,
			BiomeGenBase.forest, 
			BiomeGenBase.forestHills,
			BiomeGenBase.jungle,
			BiomeGenBase.jungleHills,
			BiomeGenBase.plains,
			BiomeGenBase.extremeHillsEdge
		};
//		BiomeGenBase[] monitorBiomes = getBiomes();

		BiomeGenBase[] tortoiseBiomes = {
			BiomeGenBase.desert,
			BiomeGenBase.desertHills,
			BiomeGenBase.plains
		};

		BiomeGenBase[] turtleBiomes = { 
			BiomeGenBase.beach, 
			BiomeGenBase.forest, 
			BiomeGenBase.forestHills, 
			BiomeGenBase.jungle,
			BiomeGenBase.jungleHills, 
			BiomeGenBase.mushroomIsland, 
			BiomeGenBase.mushroomIslandShore, 
			BiomeGenBase.plains,
			BiomeGenBase.swampland 
		};
//		BiomeGenBase[] turtleBiomes = getBiomes();

		BiomeGenBase[] lizardBiomes = { 
			BiomeGenBase.forest, 
			BiomeGenBase.forestHills, 
			BiomeGenBase.jungle,
			BiomeGenBase.jungleHills 
		};

		BiomeGenBase[] crocBiomes = { 
			BiomeGenBase.beach, 
			BiomeGenBase.jungle, 
			BiomeGenBase.mushroomIsland,
			BiomeGenBase.mushroomIslandShore, 
			BiomeGenBase.swampland 
		};

		addSpawn(EntityKomodo.class, komodoSpawnProb, 1, 4, monitorBiomes);
		addSpawn(EntitySavanna.class, savannaSpawnProb, 1, 4, monitorBiomes);
		addSpawn(EntityGriseus.class, griseusSpawnProb, 1, 4, tortoiseBiomes);
		addSpawn(EntityPerentie.class, perentieSpawnProb, 1, 4, monitorBiomes);
		addSpawn(EntityLace.class, laceSpawnProb, 1, 4, monitorBiomes);

		addSpawn(EntityCroc.class, crocSpawnProb, 1, 2, crocBiomes);
		addSpawn(EntityLargeCroc.class, largeCrocSpawnProb, 1, 2, crocBiomes);
		addSpawn(EntityGator.class, gatorSpawnProb, 1, 2, crocBiomes);

		addSpawn(EntityDesertTortoise.class, desertTortoiseSpawnProb, 1, 4, tortoiseBiomes);
		addSpawn(EntityLittleTurtle.class, littleTurtleSpawnProb, 1, 4, turtleBiomes);
		addSpawn(EntityTortoise.class, tortoiseSpawnProb, 1, 4, turtleBiomes);

		addSpawn(EntityIguana.class, iguanaSpawnProb, 1, 4, lizardBiomes);
		addSpawn(EntityChameleon.class, chameleonSpawnProb, 1, 4, lizardBiomes);
	}

	public void registerEntity(Class<? extends Entity> entityClass, String entityName, int bkEggColor, int fgEggColor) {
		int id = EntityRegistry.findGlobalUniqueEntityId();
//		System.out.println(entityClass.toString() + " ID: " + id);
//		int trackingRange = 80;
//		int updateFreq = 3;
//		boolean sendsVelUpdates = true;
//		
//		EntityRegistry.registerModEntity(entityClass, entityName, id, this, trackingRange, updateFreq, sendsVelUpdates);
		EntityRegistry.registerGlobalEntityID(entityClass, entityName, id, bkEggColor, fgEggColor);
	}

	public void addSpawn(Class<? extends EntityLiving> entityClass, int spawnProb, int min, int max, BiomeGenBase[] biomes) {
		if (spawnProb > 0) {
			EntityRegistry.addSpawn(entityClass, spawnProb, min, max, EnumCreatureType.creature, biomes);
		}
	}
	
//	public BiomeGenBase[] getBiomes() {
//		LinkedList linkedlist = new LinkedList();
//		for (BiomeGenBase biomegenbase : BiomeGenBase.biomeList) {
//			if (biomegenbase == null) {
//				continue;
//			}
//			if (!forbiddenBiome(biomegenbase)) {
//				linkedlist.add(biomegenbase);
//			}
//		}
//		return (BiomeGenBase[]) linkedlist.toArray(new BiomeGenBase[0]);
//	}
//	
//	public boolean forbiddenBiome(BiomeGenBase biome) {
//		// exclude Hell and the End 
//		if ((biome instanceof BiomeGenHell) || (biome instanceof BiomeGenEnd)) {
//			return true;
//		}
//		// exclude all cold biomes
//		if ((biome.biomeID == 0) || (biome.biomeID == 5) ||	(biome.biomeID >=10 && biome.biomeID <= 13) || (biome.biomeID == 19)) {
//			return true;
//		}
//		
//		return false;
//	}
	
}
