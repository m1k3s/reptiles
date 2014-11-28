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
package com.reptiles.common;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.pattern.BlockStateHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAIEatPlants extends EntityAIBase {

	private static final Predicate blockstate = BlockStateHelper.forBlock(Blocks.tallgrass).func_177637_a(BlockTallGrass.field_176497_a, Predicates.equalTo(BlockTallGrass.EnumType.GRASS));
	private final EntityLiving creature;
	private final World theWorld;
	int eatPlantTick = 0;

	public EntityAIEatPlants(EntityLiving entityLiving)
	{
		creature = entityLiving;
		theWorld = entityLiving.worldObj;
		setMutexBits(7);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 * @return 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean shouldExecute()
	{
		if (creature.getRNG().nextInt(creature.isChild() ? 50 : 1000) != 0) {
			return false;
		} else {
			int x = MathHelper.floor_double(creature.posX);
			int y = MathHelper.floor_double(creature.posY);
			int z = MathHelper.floor_double(creature.posZ);
			BlockPos blockPos = new BlockPos(x, y, z);
			return blockstate.apply(theWorld.getBlockState(blockPos)) ? true : isFlower(blockPos);
		}
	}

	@Override
	public void startExecuting()
	{
		eatPlantTick = 40;
		theWorld.setEntityState(creature, (byte) 10);
		creature.getNavigator().clearPathEntity();
	}

	/**
	 * Resets the task
	 */
	@Override
	public void resetTask()
	{
		eatPlantTick = 0;
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	@Override
	public boolean continueExecuting()
	{
		return eatPlantTick > 0;
	}

	public int getEatPlantTick()
	{
		return eatPlantTick;
	}

	/**
	 * Updates the task
	 */
	@Override
	public void updateTask()
	{
		eatPlantTick = Math.max(0, eatPlantTick - 1);

//		if (eatPlantTick == 4) {
//			int x = MathHelper.floor_double(creature.posX);
//			int y = MathHelper.floor_double(creature.posY);
//			int z = MathHelper.floor_double(creature.posZ);
//
//			if (isFlower(x, y, z)) {
//				theWorld.playAuxSFX(2001, x, y, z, Blocks.tallgrass);
//				theWorld.setBlock(x, y - 1, z, Blocks.dirt, 0, 2);
//                creature.eatGrassBonus();
//			} else if (isTallgrass(x, y, z)) {
//				theWorld.playAuxSFX(2001, x, y, z, Blocks.tallgrass);
//				theWorld.setBlock(x, y - 1, z, Blocks.dirt, 0, 2);
//                creature.eatGrassBonus();
//			}
//		}
	}

	public boolean isFlower(BlockPos bp)
	{
		boolean result = false;
//		BlockPos bp = new BlockPos(x, y, z);
		Block block = theWorld.getBlockState(bp.offsetDown()).getBlock(); //.getBlockId(x, y, z);

		if (block == Blocks.red_flower || block == Blocks.yellow_flower) {
			result = true;
		}
		return result;
	}

	public boolean isTallgrass(BlockPos bp)
	{
		boolean result = false;
//		BlockPos bp = new BlockPos(x, y, z);
		Block block = theWorld.getBlockState(bp.offsetDown()).getBlock();
		if (block == Blocks.tallgrass) {
			result = true;
		}
		return result;
	}
}
