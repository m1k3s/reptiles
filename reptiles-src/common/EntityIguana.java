//
// This work is licensed under the Creative Commons
// Attribution-ShareAlike 3.0 Unported License. To view a copy of this
// license, visit http://creativecommons.org/licenses/by-sa/3.0/
//

package reptiles.common;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

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
