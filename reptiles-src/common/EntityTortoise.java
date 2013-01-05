//
// This work is licensed under the Creative Commons
// Attribution-ShareAlike 3.0 Unported License. To view a copy of this
// license, visit http://creativecommons.org/licenses/by-sa/3.0/
//

package reptiles.common;


import java.util.*;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

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
