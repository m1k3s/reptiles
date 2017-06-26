/*
 * ReptileSounds.java
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

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber
public class ReptileSounds {
    public static SoundEvent varanus_hiss;
    public static SoundEvent varanus_hurt;
    public static SoundEvent croc_growl;
    public static SoundEvent mega_growl;
    public static SoundEvent mega_purr;
    public static SoundEvent mega_death;


    private static void initializeSoundEvents() {
        varanus_hiss = getRegisteredSoundEvent(Reptiles.MODID + ":varanus.hiss");
        varanus_hurt = getRegisteredSoundEvent(Reptiles.MODID + ":varanus.hurt");
        croc_growl = getRegisteredSoundEvent(Reptiles.MODID + ":croc.growl");
        mega_growl = getRegisteredSoundEvent(Reptiles.MODID + ":mega.growl");
        mega_purr = getRegisteredSoundEvent(Reptiles.MODID + ":mega.purr");
        mega_death = getRegisteredSoundEvent(Reptiles.MODID + ":mega.death");
    }

    @SubscribeEvent
    public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().register(new SoundEvent(new ResourceLocation(Reptiles.MODID + ":varanus.hiss")).setRegistryName("varanus.hiss"));
        event.getRegistry().register(new SoundEvent(new ResourceLocation(Reptiles.MODID + ":varanus.hurt")).setRegistryName("varanus.hurt"));
        event.getRegistry().register(new SoundEvent(new ResourceLocation(Reptiles.MODID + ":croc.growl")).setRegistryName("croc.growl"));
        event.getRegistry().register(new SoundEvent(new ResourceLocation(Reptiles.MODID + ":mega.growl")).setRegistryName("mega.growl"));
        event.getRegistry().register(new SoundEvent(new ResourceLocation(Reptiles.MODID + ":mega.purr")).setRegistryName("mega.purr"));
        event.getRegistry().register(new SoundEvent(new ResourceLocation(Reptiles.MODID + ":mega.death")).setRegistryName("mega.death"));

        initializeSoundEvents();
    }

    private static SoundEvent getRegisteredSoundEvent(String id) {
        SoundEvent soundevent = SoundEvent.REGISTRY.getObject(new ResourceLocation(id));

        if (soundevent == null) {
            throw new IllegalStateException("Invalid Sound requested: " + id);
        } else {
            return soundevent;
        }
    }
}
