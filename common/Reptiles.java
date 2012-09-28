package reptiles.common;

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//

import java.util.Map;
import java.util.Properties;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.net.URI;
import java.net.URL;
import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;

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
	modid = "ReptileMod", 
	name = "Reptile Mod", 
	version = "1.3.2"
)

@NetworkMod(
	clientSideRequired = true, 
	serverSideRequired = false
)

public class Reptiles {
	
	public String getVersion() {
		return "1.3.2";
	}
	
	public final Properties props = new Properties();

	@Instance
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
	
	@SidedProxy(
		clientSide = "reptiles.client.ClientProxyReptiles",
		serverSide = "reptiles.common.CommonProxyReptiles"
	)
	
	public static CommonProxyReptiles proxy;

	public Reptiles() {
	}

	@PreInit
	public void preLoad(FMLPreInitializationEvent event) {
		File configFile = event.getSuggestedConfigurationFile();
		try {
			init(configFile);
		} catch (IOException e) {
			System.err.println("Doh! Reptiles::init() crashed.");
		}
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
//		int trackingRange = 80;
//		int updateFreq = 3;
//		boolean sendsVelUpdates = true;

//		EntityRegistry.registerModEntity(entityClass, entityName, id, this, trackingRange, updateFreq, sendsVelUpdates);
		EntityRegistry.registerGlobalEntityID(entityClass, entityName, id, bkEggColor, fgEggColor);
	}

	public void addSpawn(Class<? extends EntityLiving> entityClass, int spawnProb, int min, int max, BiomeGenBase[] biomes) {
		if (spawnProb > 0) {
			EntityRegistry.addSpawn(entityClass, spawnProb, min, max, EnumCreatureType.creature, biomes);
		}
	}

	public void loadConfig(File cfgFile) throws IOException {
		try {
			if (!cfgFile.exists() && !cfgFile.createNewFile()) {
				return;
			}

			if (cfgFile.canRead()) {
				FileInputStream fileinputstream = new FileInputStream(cfgFile);
				props.load(fileinputstream);
				fileinputstream.close();
			}
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		}
	}

	public void saveConfig(File cfgFile) throws IOException {
		String comments = " Reptile Mod Config\n\n" + " Michael Sheppard (crackedEgg)\n";

		try {
			if (!cfgFile.exists() && !cfgFile.createNewFile()) {
				return;
			}
			if (cfgFile.canWrite()) {
				FileOutputStream fileoutputstream = new FileOutputStream(cfgFile);
				props.store(fileoutputstream, comments);
				fileoutputstream.close();
			}
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		}
	}

	private void init(File cfgFile) throws IOException {
		// initialize to default values
		initDefaults();

		try {
			loadConfig(cfgFile);

			if (props.containsKey("komodoSpawnProb")) {
				komodoSpawnProb = Integer.parseInt(props.getProperty("komodoSpawnProb"));
			}
			if (props.containsKey("griseusSpawnProb")) {
				griseusSpawnProb = Integer.parseInt(props.getProperty("griseusSpawnProb"));
			}
			if (props.containsKey("laceSpawnProb")) {
				laceSpawnProb = Integer.parseInt(props.getProperty("laceSpawnProb"));
			}
			if (props.containsKey("perentieSpawnProb")) {
				perentieSpawnProb = Integer.parseInt(props.getProperty("perentieSpawnProb"));
			}
			if (props.containsKey("savannaSpawnProb")) {
				savannaSpawnProb = Integer.parseInt(props.getProperty("savannaSpawnProb"));
			}
			if (props.containsKey("crocSpawnProb")) {
				crocSpawnProb = Integer.parseInt(props.getProperty("crocSpawnProb"));
			}
			if (props.containsKey("largeCrocSpawnProb")) {
				largeCrocSpawnProb = Integer.parseInt(props.getProperty("largeCrocSpawnProb"));
			}
			if (props.containsKey("desertTortoiseSpawnProb")) {
				desertTortoiseSpawnProb = Integer.parseInt(props.getProperty("desertTortoiseSpawnProb"));
			}
			if (props.containsKey("littleTurtleSpawnProb")) {
				littleTurtleSpawnProb = Integer.parseInt(props.getProperty("littleTurtleSpawnProb"));
			}
			if (props.containsKey("iguanaSpawnProb")) {
				iguanaSpawnProb = Integer.parseInt(props.getProperty("iguanaSpawnProb"));
			}
			if (props.containsKey("tortoiseSpawnProb")) {
				tortoiseSpawnProb = Integer.parseInt(props.getProperty("tortoiseSpawnProb"));
			}
			if (props.containsKey("gatorSpawnProb")) {
				gatorSpawnProb = Integer.parseInt(props.getProperty("gatorSpawnProb"));
			}
			if (props.containsKey("chameleonSpawnProb")) {
				chameleonSpawnProb = Integer.parseInt(props.getProperty("chameleonSpawnProb"));
			}

			props.setProperty("komodoSpawnProb", Integer.toString(komodoSpawnProb));
			props.setProperty("griseusSpawnProb", Integer.toString(griseusSpawnProb));
			props.setProperty("laceSpawnProb", Integer.toString(laceSpawnProb));
			props.setProperty("perentieSpawnProb", Integer.toString(perentieSpawnProb));
			props.setProperty("savannaSpawnProb", Integer.toString(savannaSpawnProb));
			props.setProperty("crocSpawnProb", Integer.toString(crocSpawnProb));
			props.setProperty("largeCrocSpawnProb", Integer.toString(largeCrocSpawnProb));
			props.setProperty("desertTortoiseSpawnProb", Integer.toString(desertTortoiseSpawnProb));
			props.setProperty("littleTurtleSpawnProb", Integer.toString(littleTurtleSpawnProb));
			props.setProperty("iguanaSpawnProb", Integer.toString(iguanaSpawnProb));
			props.setProperty("tortoiseSpawnProb", Integer.toString(tortoiseSpawnProb));
			props.setProperty("gatorSpawnProb", Integer.toString(gatorSpawnProb));
			props.setProperty("chameleonSpawnProb", Integer.toString(chameleonSpawnProb));
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			saveConfig(cfgFile);
		}
	}

	protected void initDefaults() {
		komodoSpawnProb = 10;
		griseusSpawnProb = 12;
		laceSpawnProb = 12;
		perentieSpawnProb = 12;
		savannaSpawnProb = 12;
		crocSpawnProb = 5;
		largeCrocSpawnProb = 4;
		desertTortoiseSpawnProb = 12;
		littleTurtleSpawnProb = 10;
		iguanaSpawnProb = 12;
		tortoiseSpawnProb = 12;
		gatorSpawnProb = 5;
		chameleonSpawnProb = 12;
	}

}
