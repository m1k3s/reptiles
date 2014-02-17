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
package reptiles.common;


import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;


public final class EntityKomodo extends EntityVaranus {
	
	public EntityKomodo(World world) {

		super(world);
		tasks.addTask(7, new EntityAIAttackOnCollide(this, EntitySheep.class, 1.0, true));
		targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntitySheep.class, targetChance, false));
		targetTasks.addTask(5, new EntityAITargetNonTamed(this, EntityPlayer.class, targetChance + 100, false));
		setTamed(false);
//        tasks.removeTask(avoid); // komodos don't avoid humans
	}

    @Override
	public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
		EntityKomodo e = new EntityKomodo(worldObj);
		if (isTamed()) {
			e.setOwner(getOwnerName());
			e.setTamed(true);
		}
		System.out.printf("Spawned entity of type %s", getClass().toString());
		return e;
	}
	
	// the idea is that if the entity is tame to remove the 
	// targetTask entry from the list so the player
	// is not attacked by a tame komodo
//    @Override
//	protected void updateAITasks() {
//		if (playerAttack && isTamed()) { // don't attack players when tame
//			targetTasks.taskEntries.remove(attackPlayer);
//			playerAttack = false;
//		}
//		super.updateAITasks();
//	}

}
