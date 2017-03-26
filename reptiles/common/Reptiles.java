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
// Copyright 2011-2015 Michael Sheppard (crackedEgg)
//
package com.reptiles.common;

import static com.reptiles.common.ConfigHandler.updateConfigInfo;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.*;

import java.util.*;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.*;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(
        modid = Reptiles.modid,
        name = Reptiles.name,
        version = Reptiles.version,
        acceptedMinecraftVersions = Reptiles.mcversion,
        guiFactory = Reptiles.guifactory,
        dependencies = "required-after:FML"
)

public class Reptiles {

    public static final String modid = "reptilemod";
    public static final String name = "Reptile Mod";
    public static final String version = "3.6.2";
    public static final String mcversion = "1.11.2";
    public static final String guifactory = "com.reptiles.client.ReptilesConfigGUIFactory";
    private static int entityID = 0;

    @SuppressWarnings("unchecked")
    // List of always excluded biome types
    private static final List<Type> excludedBiomeTypes = new ArrayList(Arrays.asList(
            Type.END,
            Type.NETHER,
            Type.VOID,
            Type.COLD,
            Type.OCEAN,
            Type.CONIFEROUS,
            Type.MOUNTAIN,
            Type.MUSHROOM
    ));

    @Mod.Instance(modid)
    public static Reptiles instance;

    @SidedProxy(
            clientSide = "com.reptiles.client.ClientProxyReptiles",
            serverSide = "com.reptiles.common.CommonProxyReptiles"
    )

    public static CommonProxyReptiles proxy;

    @SuppressWarnings("unused")
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigHandler.startConfig(event);

        registerEntity(EntityKomodo.class, "komodo", 0x006400, 0x98FB98);
        registerEntity(EntitySavanna.class, "savanna", 0x8B8989, 0xCDC5BF);
        registerEntity(EntityGriseus.class, "griseus", 0xCD853F, 0xDEB887);
        registerEntity(EntityPerentie.class, "perentie", 0x363636, 0x7F7F7F);
        registerEntity(EntityLace.class, "lace", 0x0A0A0A, 0xABABAB);
        registerEntity(EntityCroc.class, "croc", 0x008B00, 0xA2CD5A);
        registerEntity(EntityDesertTortoise.class, "deserttortoise", 0x8B4513, 0x8B4C39);
        registerEntity(EntityLittleTurtle.class, "littleturtle", 0xFF7F24, 0xFF8C69);
        registerEntity(EntityLargeCroc.class, "largecroc", 0x8B4513, 0x8B5A2B);
        registerEntity(EntityIguana.class, "iguana", 0x00CD00, 0xC0FF3E);
        registerEntity(EntityTortoise.class, "tortoise", 0x008B45, 0xC0FF3E);
        registerEntity(EntityGator.class, "alligator", 0x008B45, 0xC0FF3E);
        registerEntity(EntityChameleon.class, "chameleon", 0xB22222, 0x228B22);
        registerEntity(EntitySalvadorii.class, "crocmonitor", 0x008BCC, 0xA2CD5A);
        registerEntity(EntityMegalania.class, "megalania", 0x050505, 0x05c505);

        proxy.registerSoundEvents();
        proxy.registerRenderers();
    }

    @SuppressWarnings("unused")
    @Mod.EventHandler
    public void Init(FMLInitializationEvent evt) {
        MinecraftForge.EVENT_BUS.register(Reptiles.instance);
    }

    @SuppressWarnings("unused")
    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event) {
        Biome[] forestBiomes = getBiomes("forest", true, Type.FOREST);
        Biome[] jungleBiomes = getBiomes("jungle", false, Type.HOT, Type.WET);
        Biome[] plainsBiomes = getBiomes("plains", true, Type.PLAINS);
        Biome[] swampyBiomes = getBiomes("swampy", false, Type.WET, Type.SWAMP);
        Biome[] savannaBiomes = getBiomes("savanna", false, Type.SAVANNA);
        Biome[] desertBiomes = getBiomes("desert", false, Type.HOT, Type.DRY, Type.SANDY);
//        listBiomes(Type.FOREST, Type.SANDY);

        int minSpawn = ConfigHandler.getMinSpawn();
        int maxSpawn = ConfigHandler.getMaxSpawn();

        addSpawn(EntityKomodo.class, ConfigHandler.getKomodoSpawnProb(), minSpawn, maxSpawn, forestBiomes);
        addSpawn(EntitySavanna.class, ConfigHandler.getSavannaSpawnProb(), minSpawn, maxSpawn, savannaBiomes);
        addSpawn(EntityGriseus.class, ConfigHandler.getGriseusSpawnProb(), minSpawn, maxSpawn, desertBiomes);
        addSpawn(EntityPerentie.class, ConfigHandler.getPerentieSpawnProb(), minSpawn, maxSpawn, forestBiomes);
        addSpawn(EntityLace.class, ConfigHandler.getLaceSpawnProb(), minSpawn, maxSpawn, forestBiomes);
        addSpawn(EntitySalvadorii.class, ConfigHandler.getCrocMonitorSpawnProb(), minSpawn, maxSpawn, forestBiomes);
        addSpawn(EntityMegalania.class, ConfigHandler.getMegalaniaSpawnProb(), minSpawn, maxSpawn, forestBiomes);

        addSpawn(EntityCroc.class, ConfigHandler.getCrocSpawnProb(), minSpawn, maxSpawn, swampyBiomes);
        addSpawn(EntityLargeCroc.class, ConfigHandler.getLargeCrocSpawnProb(), minSpawn, maxSpawn, swampyBiomes);
        addSpawn(EntityGator.class, ConfigHandler.getGatorSpawnProb(), minSpawn, maxSpawn, swampyBiomes);

        addSpawn(EntityDesertTortoise.class, ConfigHandler.getDesertTortoiseSpawnProb(), minSpawn, maxSpawn, desertBiomes);
        addSpawn(EntityLittleTurtle.class, ConfigHandler.getLittleTurtleSpawnProb(), minSpawn, maxSpawn, jungleBiomes);
        addSpawn(EntityTortoise.class, ConfigHandler.getTortoiseSpawnProb(), minSpawn, maxSpawn, jungleBiomes);

        addSpawn(EntityIguana.class, ConfigHandler.getIguanaSpawnProb(), minSpawn, maxSpawn, plainsBiomes);
        addSpawn(EntityChameleon.class, ConfigHandler.getChameleonSpawnProb(), minSpawn, maxSpawn, plainsBiomes);
    }

    private void registerEntity(Class<? extends Entity> entityClass, String entityName, int bkEggColor, int fgEggColor) {
        EntityRegistry.registerModEntity(new ResourceLocation(modid, entityName), entityClass, entityName, entityID++, Reptiles.instance, 80, 3, true, bkEggColor, fgEggColor);
    }

    private void addSpawn(Class<? extends EntityLiving> entityClass, int spawnProb, int min, int max, Biome... biomes) {
        if (spawnProb > 0) {
            EntityRegistry.addSpawn(entityClass, spawnProb, min, max, EnumCreatureType.CREATURE, biomes);
        }
    }

    // For some reason BiomeDictionary.getBiomes(type) does not return any biomes for any type
//    private void listBiomes(Type... types) {
//        for (Type t : types) {
//            Set<Biome> biomes = BiomeDictionary.getBiomes(t);
//            proxy.info(t.getName() + ": " + biomes.toString());
//        }
//    }

    private boolean excludeThisBiome(Set<Type> types) {
        boolean excludeBiome = false;
        for (Type ex : excludedBiomeTypes) {
            if (types.contains(ex)) {
                excludeBiome = true;
                break;
            }
        }
        return excludeBiome;
    }

    private Biome[] getBiomes(String str, boolean orTypes, Type... types) {
        proxy.info("*** Creating a list of " + str + " biomes");
        LinkedList<Biome> list = new LinkedList<>();
        List<Biome> biomes = ForgeRegistries.BIOMES.getValues();
        for (Biome biome : biomes) {
            Set<Type> bTypes = BiomeDictionary.getTypes(biome);

            // we exclude certain biomes, i.e., reptiles are ectothermic, no cold biomes
            if (excludeThisBiome(bTypes)) {
//                proxy.info("  >>> Excluding " + biome.getBiomeName() + " biome");
                continue;
            }
            // process remaining biomes
            if (orTypes) { // add any biome that contains any of the listed types (logical OR)
                for (Type t : types) {
                    if (BiomeDictionary.hasType(biome, t)) {
                        if (!list.contains(biome)) {
                            list.add(biome);
                            proxy.info("  >>> Adding " + biome.getBiomeName() + " for spawning");
                        }
                    }
                }
            } else { // add any biome that contains all the types listed (logical AND)
                int count = types.length;
                int shouldAdd = 0;
                for (Type t : types) {
                    if (BiomeDictionary.hasType(biome, t)) {
                        shouldAdd++;
                    }
                }
                if (!list.contains(biome) && shouldAdd == count) {
                    list.add(biome);
                    proxy.info("  >>> Adding " + biome.getBiomeName() + " for spawning");
                }
            }
        }
        return list.toArray(new Biome[0]);
    }

    // user has changed entries in the GUI config. save the results.
    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Reptiles.modid)) {
            Reptiles.proxy.info("Configuration changes have been updated for the " + Reptiles.name);
            updateConfigInfo();
        }
    }

}
