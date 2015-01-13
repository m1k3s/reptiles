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
// Copyright 2011 Michael Sheppard (crackedEgg)
//
package com.reptiles.common;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.world.World;

public final class EntityKomodo extends EntityVaranus {

	public EntityKomodo(World world)
	{

		super(world);
		tasks.addTask(7, new EntityAIAttackOnCollide(this, EntitySheep.class, 1.0, true));
		targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntitySheep.class, false));
		setTamed(false);
	}

	@Override
	public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable)
	{
		EntityKomodo e = new EntityKomodo(worldObj);
		String s = getOwnerId();
		if (s != null && s.trim().length() > 0) {
			e.setOwnerId(s);
			e.setTamed(true);
		}
		Reptiles.proxy.info("Spawned entity of type " + getClass().toString());
		return e;
	}

}
