package reptiles.common;

import net.minecraft.src.*;

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//

public class EntityIguana extends EntityLizard
{

  public EntityIguana(World world) {
    super(world);
    setSize(0.4F, 0.4F);
    texture = "/mob/iguana.png";
  }
  
  public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
		EntityIguana i = new EntityIguana(worldObj);
		if (isTamed()) {
			i.setOwner(getOwnerName());
			i.setTamed(true);
		}
		System.out.printf("Spawned entity of type %s", getClass().toString());
		return i;
	}
  
}
