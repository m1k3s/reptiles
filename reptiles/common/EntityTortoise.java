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
//
package com.reptiles.common;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

public final class EntityTortoise extends EntityTurtle {

	public EntityTortoise(World world)
	{
		super(world);
		setSize(1.5F, 1.5F);
		setTamed(false);
	}

	@Override
	public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable)
	{
		EntityTortoise t = new EntityTortoise(worldObj);
		String s = getOwnerId();
		if (s != null && s.trim().length() > 0) {
			t.setOwnerId(s);
			t.setTamed(true);
		}
		System.out.printf("Spawned entity of type %s", getClass().toString());
		return t;
	}

}
