package reptiles.common;

import net.minecraft.src.*;

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//

//import java.util.*;

public class EntityGriseus extends EntityVaranus {

	public EntityGriseus(World world) {
		super(world);
		setSize(0.6F, 0.6F);
		texture = "/mob/griseus32.png";
	}

	public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
		EntityGriseus e = new EntityGriseus(worldObj);
		if (isTamed()) {
			e.setOwner(getOwnerName());
			e.setTamed(true);
		}
		System.out.printf("Spawned entity of type %s", getClass().toString());
		return e;
	}

}
