package reptiles.common;

import net.minecraft.src.*;

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//

import java.util.*;

public class EntityLargeCroc extends EntityCroc {
	public EntityLargeCroc(World world) {
		super(world);
		texture = "/mob/largeCroc32.png";
		setSize(1.08F, 1.75F);
		attackStrength = 3; // they're bigger, duh!
		
		tasks.addTask(5, new EntityAIAttackOnCollide(this, EntitySquid.class, moveSpeed, true));
		tasks.addTask(6, new EntityAIAttackOnCollide(this, EntitySpider.class, moveSpeed, true));

		targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySquid.class, attackDistance, 0, false));
		targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntitySpider.class, attackDistance, 400, false));
	}

	public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
		System.out.printf("Spawned entity of type %s", getClass().toString());
		return new EntityLargeCroc(this.worldObj);
	}

}
