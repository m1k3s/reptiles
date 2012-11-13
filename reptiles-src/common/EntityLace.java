package reptiles.common;

import net.minecraft.src.*;

import java.util.*;

public class EntityLace extends EntityVaranus
{

  public EntityLace(World world) {
    super(world);
    setSize(0.6F, 0.6F);
    texture = "/mob/lace.png";
  }
  
  public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
		EntityLace e = new EntityLace(worldObj);
		if (isTamed()) {
			e.setOwner(getOwnerName());
			e.setTamed(true);
		}
		System.out.printf("Spawned entity of type %s", getClass().toString());
		return e;
	}
  
  protected int getDropItemId() {
    return Item.egg.shiftedIndex; 
  }
  
}
