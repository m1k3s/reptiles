package reptiles.common;

import net.minecraft.src.*;

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//

import java.util.*;

public class EntityGator extends EntityCroc
{
  public EntityGator(World world) {
    super(world);
    texture = "/mob/gator32.png";
  }
  
  public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
	  System.out.printf("Spawned entity of type %s", getClass().toString());
    return new EntityGator(this.worldObj);
  }

}
