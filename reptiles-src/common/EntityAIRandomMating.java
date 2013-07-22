//  
//  =====GPL=============================================================
//  This program is free software; you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation; version 2 dated June, 1991.
// 
//  This program is distributed in the hope that it will be useful, 
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
// 
//  You should have received a copy of the GNU General Public License
//  along with this program;  if not, write to the Free Software
//  Foundation, Inc., 675 Mass Ave., Cambridge, MA 02139, USA.
//  =====================================================================
//

//
//

package reptiles.common;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;


public class EntityAIRandomMating extends EntityAIBase {

	private EntityLiving creature;
	private World theWorld;
	int matingTick = 0;

	public EntityAIRandomMating(EntityLiving entityLiving) {
		creature = entityLiving;
		theWorld = entityLiving.worldObj;
		setMutexBits(7);
	}

    @Override
	public boolean shouldExecute() {
		if (creature.getRNG().nextInt(8192) != 0 || creature.isChild()) {
//			System.err.println("shouldExecute is returning false. (RND != 0 || isChild)");
			return false;
		}
		double range = 16.0;
		List list = theWorld.getEntitiesWithinAABB(creature.getClass(), creature.boundingBox.expand(range, range, range));
		if (list.size() > 4) {
//			System.err.println("shouldExecute is returning false. (too many entities in area)");
			return false;
		}
//		System.err.println("shouldExecute is returning true.");
		return true;
	}

    @Override
	public void startExecuting() {
		matingTick = 40;
	}

    @Override
	public void resetTask() {
		matingTick = 0;
	}
	
    @Override
	public boolean continueExecuting() {
		return matingTick > 0;
	}

	public int getMatingTick() {
		return matingTick;
	}

    @Override
	public void updateTask() {
		matingTick = Math.max(0, matingTick - 1); // decrement matingTick

		if (matingTick == 4 && creature.getRNG().nextInt(4) == 0) {
			((EntityAnimal)creature).inLove = 600;
//			System.err.println("matingTick == 4 condition is TRUE. (mating may occur).");
			resetTask();
		}
	}

}
