// EntityLargeCroc.java
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

// EntityLargeCroc.java
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

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.world.World;

public class EntityLargeCroc extends EntityCroc {

	public EntityLargeCroc(World world)
	{
		super(world);
		float scaleFactor = 1.5f;
		setSize(1.0F * scaleFactor, 0.6F * scaleFactor);

		targetTasks.addTask(5, new EntityAINearestAttackableTarget<>(this, EntitySquid.class, false));
		targetTasks.addTask(6, new EntityAINearestAttackableTarget<>(this, EntitySpider.class, false));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		// getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0); // health
		// getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25); // move speed
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		// getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	}

}
