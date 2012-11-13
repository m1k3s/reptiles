package reptiles.common;

import net.minecraft.src.*;

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//

import java.util.*;

public class EntityLittleTurtle extends EntityTurtle
{
  public EntityLittleTurtle(World world) {
    super(world);
    texture = "/mob/littleturtle.png";
    setSize(0.2F, 0.2F);
  }
  
//  public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal) {
//  	return new EntityLittleTurtle(this.worldObj);
//  }
  public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
		EntityLittleTurtle t = new EntityLittleTurtle(worldObj);
		if (isTamed()) {
          t.setOwner(getOwnerName());
          t.setTamed(true);
          System.out.printf("Spawned entity of type %s", getClass().toString());
		}
		return t;
	}
  
}
