package reptiles.common;


//
// Copyright 2011 Michael Sheppard (crackedEgg)
//

import java.util.*;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

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
