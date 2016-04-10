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
        SoundEvent soundevent = SoundEvent.soundEventRegistry.getObject(new ResourceLocation(id));

        if (soundevent == null) {
            throw new IllegalStateException("Invalid Sound requested: " + id);
        } else {
            return soundevent;
        }
    }
}
