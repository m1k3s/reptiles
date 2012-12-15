package reptiles.common;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;


public class EntityDesertTortoise extends EntityTurtle {

	public EntityDesertTortoise(World world) {
		super(world);
		texture = "/mob/tortoise.png";
	}
	
//	public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal) {
//	  	return new EntityDesertTortoise(this.worldObj);
//	}
	public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
		EntityDesertTortoise t = new EntityDesertTortoise(worldObj);
		if (isTamed()) {
            t.setOwner(getOwnerName());
            t.setTamed(true);
		}
		System.out.printf("Spawned entity of type %s", getClass().toString());
		return t;
	}

}
