// ReptileSounds.java
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

package com.reptiles.common;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ReptileSounds {
    public static SoundEvent varanus_hiss;
    public static SoundEvent varanus_hurt;
    public static SoundEvent croc_growl;
    public static SoundEvent mega_growl;
    public static SoundEvent mega_purr;
    public static SoundEvent mega_death;


    public static void initializeSoundEvents() {
        varanus_hiss = getRegisteredSoundEvent(Reptiles.modid + ":varanus.hiss");
        varanus_hurt = getRegisteredSoundEvent(Reptiles.modid + ":varanus.hurt");
        croc_growl = getRegisteredSoundEvent(Reptiles.modid + ":croc.growl");
        mega_growl = getRegisteredSoundEvent(Reptiles.modid + ":mega.growl");
        mega_purr = getRegisteredSoundEvent(Reptiles.modid + ":mega.purr");
        mega_death = getRegisteredSoundEvent(Reptiles.modid + ":mega.death");
    }

    public static void registerSoundEvents() {
        GameRegistry.register(new SoundEvent(new ResourceLocation(Reptiles.modid + ":varanus.hiss")).setRegistryName("varanus.hiss"));
        GameRegistry.register(new SoundEvent(new ResourceLocation(Reptiles.modid + ":varanus.hurt")).setRegistryName("varanus.hurt"));
        GameRegistry.register(new SoundEvent(new ResourceLocation(Reptiles.modid + ":croc.growl")).setRegistryName("croc.growl"));
        GameRegistry.register(new SoundEvent(new ResourceLocation(Reptiles.modid + ":mega.growl")).setRegistryName("mega.growl"));
        GameRegistry.register(new SoundEvent(new ResourceLocation(Reptiles.modid + ":mega.purr")).setRegistryName("mega.purr"));
        GameRegistry.register(new SoundEvent(new ResourceLocation(Reptiles.modid + ":mega.death")).setRegistryName("mega.death"));
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
