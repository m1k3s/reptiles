/*
 * Reptiles.java
 *
 *  Copyright (c) 2017 Michael Sheppard
 *
 * =====GPLv3===========================================================
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses.
 * =====================================================================
 */

package com.reptiles.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
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
import net.minecraftforge.fml.relauncher.Side;

@Mod(
        modid = Reptiles.MODID,
        name = Reptiles.NAME,
        version = Reptiles.VERSION,
        acceptedMinecraftVersions = Reptiles.MCVERSION
)

public class Reptiles {

    public static final String MODID = "reptilemod";
    public static final String NAME = "Reptile Mod";
    public static final String VERSION = "3.8.4";
    public static final String MCVERSION = "1.12.2";
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
            Type.MUSHROOM,
            Type.SNOWY
    ));

    public static final String reptileCookedName = "reptile_cooked";
    public static final String reptileRawName = "reptile_raw";
    public static final String reptileHideName = "reptile_hide";

    public static final String turtleCookedName = "turtle_cooked";
    public static final String turtleRawName = "turtle_raw";
    public static final String turtleHideName = "turtle_hide";

    public static final String crocCookedName = "croc_cooked";
    public static final String crocRawName = "croc_raw";
    public static final String crocHideName = "croc_hide";

    public static final String megaCookedName = "megalania_cooked";
    public static final String megaRawName = "megalania_raw";
    public static final String megaHideName = "megalania_hide";

    public static final Item REPTILE_MEAT_COOKED = new ItemVaranusMeat(8, 0.8f, true, reptileCookedName);
    public static final Item REPTILE_MEAT_RAW = new ItemVaranusMeat(3, 0.3f, true, reptileRawName);
    public static final Item REPTILE_LEATHER = new Item().setUnlocalizedName(Reptiles.MODID + ":" + reptileHideName);

    public static final Item TURTLE_MEAT_COOKED = new ItemTurtleMeat(8, 0.8f, true, turtleCookedName);
    public static final Item TURTLE_MEAT_RAW = new ItemTurtleMeat(8, 0.8f, true, turtleRawName);
    public static final Item TURTLE_LEATHER = new Item().setUnlocalizedName(Reptiles.MODID + ":" + turtleHideName);

    public static final Item CROC_MEAT_COOKED = new ItemCrocMeat(8, 0.8f, true, crocCookedName);
    public static final Item CROC_MEAT_RAW = new ItemCrocMeat(8, 0.8f, true, crocRawName);
    public static final Item CROC_LEATHER = new Item().setUnlocalizedName(Reptiles.MODID + ":" + crocHideName);

    public static final Item MEGA_MEAT_COOKED = new ItemMegaMeat(8, 0.8f, true, megaCookedName);
    public static final Item MEGA_MEAT_RAW = new ItemMegaMeat(8, 0.8f, true, megaRawName);
    public static final Item MEGA_LEATHER = new Item().setUnlocalizedName(Reptiles.MODID + ":" + megaHideName);

    @Mod.Instance(MODID)
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
        registerEntity(EntityCrocBase.class, "croc", 0x008B00, 0xA2CD5A);
        registerEntity(EntityDesertTortoise.class, "deserttortoise", 0x8B4513, 0x8B4C39);
        registerEntity(EntityLittleTurtle.class, "littleturtle", 0xFF7F24, 0xFF8C69);
        registerEntity(EntityLargeCroc.class, "largecroc", 0x8B4513, 0x8B5A2B);
        registerEntity(EntityIguana.class, "iguana", 0x00CD00, 0xC0FF3E);
        registerEntity(EntityTortoise.class, "tortoise", 0x008B45, 0xC0FF3E);
        registerEntity(EntityGator.class, "alligator", 0x008B45, 0xC0FF3E);
        registerEntity(EntityChameleon.class, "chameleon", 0xB22222, 0x228B22);
        registerEntity(EntitySalvadorii.class, "crocmonitor", 0x008BCC, 0xA2CD5A);
        registerEntity(EntityMegalania.class, "megalania", 0x050505, 0x05c505);

        REPTILE_MEAT_COOKED.setRegistryName(new ResourceLocation(Reptiles.MODID, reptileCookedName));
        REPTILE_MEAT_RAW.setRegistryName(new ResourceLocation(Reptiles.MODID, reptileRawName));
        REPTILE_LEATHER.setRegistryName(new ResourceLocation(Reptiles.MODID, reptileHideName));
        REPTILE_LEATHER.setCreativeTab(CreativeTabs.MISC);

        TURTLE_MEAT_COOKED.setRegistryName(new ResourceLocation(Reptiles.MODID, turtleCookedName));
        TURTLE_MEAT_RAW.setRegistryName(new ResourceLocation(Reptiles.MODID, turtleRawName));
        TURTLE_LEATHER.setRegistryName(new ResourceLocation(Reptiles.MODID, turtleHideName));
        TURTLE_LEATHER.setCreativeTab(CreativeTabs.MISC);

        CROC_MEAT_COOKED.setRegistryName(new ResourceLocation(Reptiles.MODID, crocCookedName));
        CROC_MEAT_RAW.setRegistryName(new ResourceLocation(Reptiles.MODID, crocRawName));
        CROC_LEATHER.setRegistryName(new ResourceLocation(Reptiles.MODID, crocHideName));
        CROC_LEATHER.setCreativeTab(CreativeTabs.MISC);

        MEGA_MEAT_COOKED.setRegistryName(new ResourceLocation(Reptiles.MODID, megaCookedName));
        MEGA_MEAT_RAW.setRegistryName(new ResourceLocation(Reptiles.MODID, megaRawName));
        MEGA_LEATHER.setRegistryName(new ResourceLocation(Reptiles.MODID, megaHideName));
        MEGA_LEATHER.setCreativeTab(CreativeTabs.MISC);

        ((ItemFood) REPTILE_MEAT_COOKED).setAlwaysEdible();
        ((ItemFood) TURTLE_MEAT_COOKED).setAlwaysEdible();
        ((ItemFood) CROC_MEAT_COOKED).setAlwaysEdible();
        ((ItemFood) MEGA_MEAT_COOKED).setAlwaysEdible();

        GameRegistry.findRegistry(Item.class).registerAll(REPTILE_MEAT_COOKED, REPTILE_MEAT_RAW, REPTILE_LEATHER);
        GameRegistry.findRegistry(Item.class).registerAll(TURTLE_MEAT_COOKED, TURTLE_MEAT_RAW, TURTLE_LEATHER);
        GameRegistry.findRegistry(Item.class).registerAll(CROC_MEAT_COOKED, CROC_MEAT_RAW, CROC_LEATHER);
        GameRegistry.findRegistry(Item.class).registerAll(MEGA_MEAT_COOKED, MEGA_MEAT_RAW, MEGA_LEATHER);

        GameRegistry.addSmelting(REPTILE_MEAT_RAW, new ItemStack(REPTILE_MEAT_COOKED), 0.1f);
        GameRegistry.addSmelting(TURTLE_MEAT_RAW, new ItemStack(TURTLE_MEAT_COOKED), 0.1f);
        GameRegistry.addSmelting(CROC_MEAT_RAW, new ItemStack(CROC_MEAT_COOKED), 0.1f);
        GameRegistry.addSmelting(MEGA_MEAT_RAW, new ItemStack(MEGA_MEAT_COOKED), 0.1f);

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
        Biome[] crocBiomes = getBiomes("crocs", true, Type.WET, Type.SWAMP, Type.RIVER, Type.BEACH);
        Biome[] savannaBiomes = getBiomes("savanna", false, Type.SAVANNA);
        Biome[] desertBiomes = getBiomes("desert", false, Type.HOT, Type.DRY, Type.SANDY);
        Biome[] combinedBiomes = getBiomes("combined", true, Type.FOREST, Type.SAVANNA);

        int minSpawn = ConfigHandler.getMinSpawn();
        int maxSpawn = ConfigHandler.getMaxSpawn();

        addSpawn(EntityKomodo.class, ConfigHandler.getKomodoSpawnProb(), minSpawn, maxSpawn, combinedBiomes);
        addSpawn(EntitySavanna.class, ConfigHandler.getSavannaSpawnProb(), minSpawn, maxSpawn, savannaBiomes);
        addSpawn(EntityGriseus.class, ConfigHandler.getGriseusSpawnProb(), minSpawn, maxSpawn, desertBiomes);
        addSpawn(EntityPerentie.class, ConfigHandler.getPerentieSpawnProb(), minSpawn, maxSpawn, forestBiomes);
        addSpawn(EntityLace.class, ConfigHandler.getLaceSpawnProb(), minSpawn, maxSpawn, forestBiomes);
        addSpawn(EntitySalvadorii.class, ConfigHandler.getCrocMonitorSpawnProb(), minSpawn, maxSpawn, forestBiomes);
        addSpawn(EntityMegalania.class, ConfigHandler.getMegalaniaSpawnProb(), minSpawn, maxSpawn, forestBiomes);

        addSpawn(EntityCrocBase.class, ConfigHandler.getCrocSpawnProb(), minSpawn, maxSpawn, crocBiomes);
        addSpawn(EntityLargeCroc.class, ConfigHandler.getLargeCrocSpawnProb(), minSpawn, maxSpawn, crocBiomes);
        addSpawn(EntityGator.class, ConfigHandler.getGatorSpawnProb(), minSpawn, maxSpawn, crocBiomes);

        addSpawn(EntityDesertTortoise.class, ConfigHandler.getDesertTortoiseSpawnProb(), minSpawn, maxSpawn, desertBiomes);
        addSpawn(EntityLittleTurtle.class, ConfigHandler.getLittleTurtleSpawnProb(), minSpawn, maxSpawn, jungleBiomes);
        addSpawn(EntityTortoise.class, ConfigHandler.getTortoiseSpawnProb(), minSpawn, maxSpawn, jungleBiomes);

        addSpawn(EntityIguana.class, ConfigHandler.getIguanaSpawnProb(), minSpawn, maxSpawn, jungleBiomes);
        addSpawn(EntityChameleon.class, ConfigHandler.getChameleonSpawnProb(), minSpawn, maxSpawn, plainsBiomes);
    }

    private void registerEntity(Class<? extends Entity> entityClass, String entityName, int bkEggColor, int fgEggColor) {
        EntityRegistry.registerModEntity(new ResourceLocation(MODID, entityName), entityClass, entityName, entityID++, Reptiles.instance, 80, 3, true, bkEggColor, fgEggColor);
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
                continue;
            }
            // process remaining biomes
            if (orTypes) { // add any biome that contains any of the listed types (logical OR)
                for (Type t : types) {
                    if (BiomeDictionary.hasType(biome, t)) {
                        if (!list.contains(biome)) {
                            list.add(biome);
                            biomeDebugMsg(biome);
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
                    biomeDebugMsg(biome);
                }
            }
        }
        return list.toArray(new Biome[0]);
    }

    public void biomeDebugMsg(Biome b) {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            proxy.info("  >>> Including " + b.getBiomeName() + " for spawning");
        }
    }

}
