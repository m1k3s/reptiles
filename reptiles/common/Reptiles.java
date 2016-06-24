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

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.*;

import java.util.LinkedList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.*;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = Reptiles.modid, name = Reptiles.name, version = Reptiles.version, guiFactory = Reptiles.guifactory)

public class Reptiles {

    public static final String modid = "reptilemod";
    public static final String name = "Reptile Mod";
    public static final String version = "3.4.3";
    public static final String mcversion = "1.9.4";
    public static final String guifactory = "com.reptiles.client.ReptilesConfigGUIFactory";
    private static int entityID = 0;

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
        BiomeDictionary.registerAllBiomesAndGenerateEvents();

        proxy.info("*** Checking for monitor biomes");
        Biome[] forestBiomes = getBiomes(Type.FOREST, Type.BEACH, Type.SWAMP, Type.PLAINS);

        proxy.info("*** Checking for tortoise biomes");
        Biome[] desertBiomes = getBiomes(Type.HOT, Type.SPARSE, Type.SANDY);

        proxy.info("*** Checking for turtle biomes");
        Biome[] jungleBiomes = getBiomes(Type.FOREST, Type.PLAINS, Type.SWAMP);

        proxy.info("*** Checking for lizard biomes");
        Biome[] variousBiomes = getBiomes(Type.FOREST, Type.HILLS, Type.MUSHROOM, Type.PLAINS, Type.MOUNTAIN);

        proxy.info("*** Checking for crocodilian biomes");
        Biome[] swampyBiomes = getBiomes(Type.BEACH, Type.SWAMP, Type.MUSHROOM);

        int minSpawn = ConfigHandler.getMinSpawn();
        int maxSpawn = ConfigHandler.getMaxSpawn();

        addSpawn(EntityKomodo.class, ConfigHandler.getKomodoSpawnProb(), minSpawn, maxSpawn, forestBiomes);
        addSpawn(EntitySavanna.class, ConfigHandler.getSavannaSpawnProb(), minSpawn, maxSpawn, forestBiomes);
        addSpawn(EntityGriseus.class, ConfigHandler.getGriseusSpawnProb(), minSpawn, maxSpawn, desertBiomes);
        addSpawn(EntityPerentie.class, ConfigHandler.getPerentieSpawnProb(), minSpawn, maxSpawn, forestBiomes);
        addSpawn(EntityLace.class, ConfigHandler.getLaceSpawnProb(), minSpawn, maxSpawn, forestBiomes);
        addSpawn(EntitySalvadorii.class, ConfigHandler.getCrocMonitorSpawnProb(), minSpawn, maxSpawn, forestBiomes);

        addSpawn(EntityCroc.class, ConfigHandler.getCrocSpawnProb(), minSpawn, maxSpawn, swampyBiomes);
        addSpawn(EntityLargeCroc.class, ConfigHandler.getLargeCrocSpawnProb(), minSpawn, maxSpawn, swampyBiomes);
        addSpawn(EntityGator.class, ConfigHandler.getGatorSpawnProb(), minSpawn, maxSpawn, swampyBiomes);

        addSpawn(EntityDesertTortoise.class, ConfigHandler.getDesertTortoiseSpawnProb(), minSpawn, maxSpawn, desertBiomes);
        addSpawn(EntityLittleTurtle.class, ConfigHandler.getLittleTurtleSpawnProb(), minSpawn, maxSpawn, jungleBiomes);
        addSpawn(EntityTortoise.class, ConfigHandler.getTortoiseSpawnProb(), minSpawn, maxSpawn, jungleBiomes);

        addSpawn(EntityIguana.class, ConfigHandler.getIguanaSpawnProb(), minSpawn, maxSpawn, variousBiomes);
        addSpawn(EntityChameleon.class, ConfigHandler.getChameleonSpawnProb(), minSpawn, maxSpawn, variousBiomes);
    }

    private void registerEntity(Class<? extends Entity> entityClass, String entityName, int bkEggColor, int fgEggColor) {
        EntityRegistry.registerModEntity(entityClass, entityName, entityID++, Reptiles.instance, 80, 3, true, bkEggColor, fgEggColor);
    }

    private void addSpawn(Class<? extends EntityLiving> entityClass, int spawnProb, int min, int max, Biome[] biomes) {
        if (spawnProb > 0) {
            EntityRegistry.addSpawn(entityClass, spawnProb, min, max, EnumCreatureType.CREATURE, biomes);
        }
    }

    private Biome[] getBiomes(Type... types) {
        LinkedList<Biome> list = new LinkedList<>();
        for (Type t : types) {
            Biome[] biomes = BiomeDictionary.getBiomesForType(t);
            for (Biome bgb : biomes) {
                if (BiomeDictionary.isBiomeOfType(bgb, Type.END) || BiomeDictionary.isBiomeOfType(bgb, Type.NETHER)) {
                    continue;
                }
                if (BiomeDictionary.isBiomeOfType(bgb, Type.SNOWY) || bgb.getTemperature() < 0.32F) { // exclude cold climates
//					proxy.info("  <<< Excluding " + bgb.biomeName + " for spawning");
                    continue;
                }
                if (BiomeDictionary.isBiomeOfType(bgb, Type.WATER)) { // exclude ocean biomes
//					proxy.info("  <<< Excluding " + bgb.biomeName + " for spawning");
                    continue;
                }
                if (!list.contains(bgb)) {
                    list.add(bgb);
                    proxy.info("  >>> Adding " + bgb.getBiomeName() + " for spawning");
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
