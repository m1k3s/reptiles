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
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.world.World;

public class EntityLargeCroc extends EntityCroc {
	
	private final float scaleFactor = 1.5f;

	public EntityLargeCroc(World world)
	{
		super(world);
		setSize(1.0F * scaleFactor, 0.6F * scaleFactor);
		attackStrength = 3; // they're bigger, duh!

		tasks.addTask(5, new EntityAIAttackOnCollide(this, EntitySquid.class, 1.0, true));
		tasks.addTask(6, new EntityAIAttackOnCollide(this, EntitySpider.class, 1.0, true));

		targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySquid.class, false));
		targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntitySpider.class, false));
	}

	@Override
	public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable)
	{
		Reptiles.proxy.info("Spawned entity of type " + getClass().toString());
		return new EntityLargeCroc(this.worldObj);
	}

}
