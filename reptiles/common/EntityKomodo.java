/*
 * EntityKomodo.java
 *
 *  Copyright (c) 2017 Michael Sheppard
 *
 * =====GPL=============================================================
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

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.UUID;

public final class EntityKomodo extends EntityVaranus {

    @SuppressWarnings("unchecked")
    public EntityKomodo(World world) {
        super(world);
        targetTasks.addTask(3, new EntityAITargetNonTamed(this, EntityPlayer.class, false, entity -> rand.nextInt(15) == 0));
        targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, EntitySheep.class, false));
        setTamed(false);
        setSize(0.6f, 0.85f);
    }

    @Override
    public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
        EntityKomodo e = new EntityKomodo(world);
        UUID uuid = getOwnerId();
        if (uuid != null) {
            e.setOwnerId(uuid);
            e.setTamed(true);
        }
        Reptiles.proxy.info("Spawned entity of type " + getClass().toString());
        return e;
    }

}
