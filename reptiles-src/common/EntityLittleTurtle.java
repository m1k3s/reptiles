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
