//
// This work is licensed under the Creative Commons
// Attribution-ShareAlike 3.0 Unported License. To view a copy of this
// license, visit http://creativecommons.org/licenses/by-sa/3.0/
//

package reptiles.common;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

public class EntityChameleon extends EntityLizard {

	public EntityChameleon(World world) {
		super(world);
		setSize(0.25F, 0.25F);
		texture = "/mob/chameleon.png";
		moveSpeed = 0.5F;
	}

	public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
		EntityChameleon c = new EntityChameleon(worldObj);
		if (isTamed()) {
			c.setOwner(getOwnerName());
			c.setTamed(true);
		}
		System.out.printf("Spawned entity of type %s", getClass().toString());
		return c;
	}

}
