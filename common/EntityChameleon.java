package reptiles.common;

import net.minecraft.src.*;

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//

public class EntityChameleon extends EntityLizard {

	public EntityChameleon(World world) {
		super(world);
		setSize(0.25F, 0.25F);
		texture = "/mob/chameleon.png";
		moveSpeed = 0.5F;
	}

	public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal) {
		EntityChameleon c = new EntityChameleon(worldObj);
		if (isTamed()) {
			c.setOwner(getOwnerName());
			c.setTamed(true);
		}
		System.out.printf("Spawned entity of type %s", getClass().toString());
		return c;
	}

}
