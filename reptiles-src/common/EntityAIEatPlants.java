//
// This work is licensed under the Creative Commons
// Attribution-ShareAlike 3.0 Unported License. To view a copy of this
// license, visit http://creativecommons.org/licenses/by-sa/3.0/
//

package reptiles.common;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;


public class EntityAIEatPlants extends EntityAIBase {
	private EntityLiving creature;
	private World theWorld;
	int eatPlantTick = 0;

	public EntityAIEatPlants(EntityLiving entityLiving) {
		creature = entityLiving;
		theWorld = entityLiving.worldObj;
		setMutexBits(7);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		if (creature.getRNG().nextInt(creature.isChild() ? 50 : 1000) != 0) {
			return false;
		} else {
			int x = MathHelper.floor_double(creature.posX);
			int y = MathHelper.floor_double(creature.posY);
			int z = MathHelper.floor_double(creature.posZ);
			return isFlower(x, y, z) && theWorld.getBlockMetadata(x, y, z) == 1 ? true : isTallgrass(x, y, z);
		}
	}

	public void startExecuting() {
		eatPlantTick = 40;
		theWorld.setEntityState(creature, (byte) 10);
		creature.getNavigator().clearPathEntity();
	}

	/**
	 * Resets the task
	 */
	public void resetTask() {
		eatPlantTick = 0;
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean continueExecuting() {
		return eatPlantTick > 0;
	}

	public int getEatPlantTick() {
		return eatPlantTick;
	}

	/**
	 * Updates the task
	 */
	public void updateTask() {
		eatPlantTick = Math.max(0, eatPlantTick - 1);

		if (eatPlantTick == 4) {
			int x = MathHelper.floor_double(creature.posX);
			int y = MathHelper.floor_double(creature.posY);
			int z = MathHelper.floor_double(creature.posZ);

			if (isFlower(x, y, z)) {
				theWorld.playAuxSFX(2001, x, y, z, Block.tallGrass.blockID + 4096);
				theWorld.setBlockWithNotify(x, y, z, 0);
				creature.eatGrassBonus();
			} else if (isTallgrass(x, y, z)) {
				theWorld.playAuxSFX(2001, x, y, z, Block.tallGrass.blockID);
				theWorld.setBlockWithNotify(x, y, z, 0);
				creature.eatGrassBonus();
			}
		}
	}

	public boolean isFlower(int x, int y, int z) {
		boolean result = false;
		int blockID = theWorld.getBlockId(x, y, z);
		
		if (blockID == Block.plantRed.blockID || blockID == Block.plantYellow.blockID) {
			result = true;
		}
		return result;
	}

	public boolean isTallgrass(int x, int y, int z) {
		boolean result = false;
		if (theWorld.getBlockId(x, y, z) == Block.tallGrass.blockID) {
			result = true;
		}
		return result;
	}
}
