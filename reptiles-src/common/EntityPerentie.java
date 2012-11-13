package reptiles.common;

import net.minecraft.src.*;

import java.util.*;

public class EntityPerentie extends EntityVaranus
{

  public EntityPerentie(World world) {
    super(world);
    setSize(0.6F, 0.6F);
    texture = "/mob/perentie.png";
  }
  
  protected int getDropItemId() {
    return Item.egg.shiftedIndex; 
  }
  
  public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
		EntityPerentie e = new EntityPerentie(worldObj);
		if (isTamed()) {
			e.setOwner(getOwnerName());
			e.setTamed(true);
		}
		System.out.printf("Spawned entity of type %s", getClass().toString());
		return e;
	}
  
}
