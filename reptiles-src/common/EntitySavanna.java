//
// This work is licensed under the Creative Commons
// Attribution-ShareAlike 3.0 Unported License. To view a copy of this
// license, visit http://creativecommons.org/licenses/by-sa/3.0/
//

package reptiles.common;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

public class EntitySavanna extends EntityVaranus
{

  public EntitySavanna(World world) {
    super(world);
    setSize(0.6F, 0.6F);
    texture = "/mob/savanna.png";
  }
  
  public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
		EntitySavanna e = new EntitySavanna(worldObj);
		if (isTamed()) {
			e.setOwner(getOwnerName());
			e.setTamed(true);
		}
		System.out.printf("Spawned entity of type %s", getClass().toString());
		return e;
	}
  
}
