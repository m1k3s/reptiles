/*
 * EntityIguana.java
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

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

import java.util.UUID;

public final class EntityIguana extends EntityLizardBase {

    public EntityIguana(World world) {
        super(world);
        setSize(0.2F, 0.1F);
        setTamed(false);
    }

    @Override
    public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
        EntityIguana i = new EntityIguana(world);
        UUID uuid = getOwnerId();
        if (uuid != null) {
            i.setOwnerId(uuid);
            i.setTamed(true);
        }
        Reptiles.instance.info("Spawned entity of type " + getClass().toString());
        return i;
    }

}
