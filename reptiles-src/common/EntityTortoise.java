package reptiles.common;

import net.minecraft.src.*;

import java.util.*;

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//

public class EntityTortoise extends EntityTurtle {
	public EntityTortoise(World world) {
		super(world);
		texture = "/mob/gtortoise2-32.png";
		setSize(1.5F, 1.5F);
		moveSpeed = 0.4F;
	}

	public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
		EntityTortoise t = new EntityTortoise(worldObj);
		if (isTamed()) {
            t.setOwner(getOwnerName());
            t.setTamed(true);
		}
		System.out.printf("Spawned entity of type %s", getClass().toString());
		return t;
	}
	
}
