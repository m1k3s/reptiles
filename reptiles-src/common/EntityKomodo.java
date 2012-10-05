package reptiles.common;

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//

import java.util.*;

import net.minecraft.src.*;

public class EntityKomodo extends EntityVaranus {
	
	private EntityAINearestAttackableTarget attackPlayer = new EntityAINearestAttackableTarget(this, EntityPlayer.class, attackDistance, targetChance * 2, true);
	private boolean playerAttack = true;

	public EntityKomodo(World world) {

		super(world);
		texture = "/mob/komodo32.png";
		tasks.addTask(4, new EntityAIAttackOnCollide(this, EntitySheep.class, this.moveSpeed, true));
		targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntitySheep.class, attackDistance, targetChance, false));
		targetTasks.addTask(5, attackPlayer);
	}

	public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal) {
		EntityKomodo e = new EntityKomodo(worldObj);
		if (isTamed()) {
			e.setOwner(getOwnerName());
			e.setTamed(true);
		}
		System.out.printf("Spawned entity of type %s", getClass().toString());
		return e;
	}
	
	// the idea is that if the entity is tame to remove the 
	// targetTask entry for the List so the player
	// is not attacked by a tame komodo
	protected void updateAITasks() {
		if (playerAttack && isTamed()) { // don't attack players when tame
			targetTasks.taskEntries.remove(attackPlayer);
			playerAttack = false;
		}
		super.updateAITasks();
	}

}
