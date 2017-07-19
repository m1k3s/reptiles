package com.reptiles.common;

import net.minecraft.entity.Entity;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CheckSpawnEvent {
    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onCheckSpawnEvent(LivingSpawnEvent.CheckSpawn event) {
        Event.Result result = Event.Result.DEFAULT;
        Entity entity = event.getEntity();
        if (!event.isSpawner()) {
//            boolean varanus = entity instanceof EntityVaranusBase;
//            boolean croc = entity instanceof EntityCrocBase;
//            boolean lizard = entity instanceof EntityLizardBase;
//            boolean turtle = entity instanceof EntityTurtleBase;
//            boolean mega = entity instanceof EntityMegalania;
//
//            if (varanus || croc || lizard || turtle || mega) {
//                result = Event.Result.ALLOW;
                Reptiles.proxy.info("Creature spawn: " + entity.getName());
//            }
        }
        event.setResult(result);
    }

}
