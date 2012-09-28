package reptiles.common;

import net.minecraft.src.*;

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//

public class EntitySavanna extends EntityVaranus
{

  public EntitySavanna(World world) {
    super(world);
    setSize(0.6F, 0.6F);
    texture = "/mob/savanna.png";
  }
  
  public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal) {
		EntitySavanna e = new EntitySavanna(worldObj);
		if (isTamed()) {
			e.setOwner(getOwnerName());
			e.setTamed(true);
		}
		System.out.printf("Spawned entity of type %s", getClass().toString());
		return e;
	}
  
}
