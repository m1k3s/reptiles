package reptiles.common;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;

public class EntityDesertTortoise extends EntityTurtle {

	public EntityDesertTortoise(World world) {
		super(world);
		texture = "/mob/tortoise.png";
	}
	
//	public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal) {
//	  	return new EntityDesertTortoise(this.worldObj);
//	}
	public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal) {
		EntityDesertTortoise t = new EntityDesertTortoise(worldObj);
		if (isTamed()) {
            t.setOwner(getOwnerName());
            t.setTamed(true);
		}
		System.out.printf("Spawned entity of type %s", getClass().toString());
		return t;
	}

}
