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
