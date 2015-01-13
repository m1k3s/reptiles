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
import net.minecraft.world.World;

public class EntityAIEatPlants extends EntityAIBase {

	private static final Predicate blockstate = BlockStateHelper.forBlock(Blocks.tallgrass).where(BlockTallGrass.TYPE, Predicates.equalTo(BlockTallGrass.EnumType.GRASS));
	private final EntityLiving creature;
	private final World theWorld;
	int eatPlantTick = 0;

	public EntityAIEatPlants(EntityLiving entityLiving)
	{
		creature = entityLiving;
		theWorld = entityLiving.worldObj;
		setMutexBits(7);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean shouldExecute()
	{
		if (creature.getRNG().nextInt(creature.isChild() ? 50 : 1000) != 0) {
			return false;
		} else {
			BlockPos blockPos = new BlockPos(creature.posX, creature.posY, creature.posZ);
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

	@Override
	public void resetTask()
	{
		eatPlantTick = 0;
	}

	@Override
	public boolean continueExecuting()
	{
		return eatPlantTick > 0;
	}

	public int getEatPlantTick()
	{
		return eatPlantTick;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void updateTask()
	{
		eatPlantTick = Math.max(0, eatPlantTick - 1);

		if (eatPlantTick == 4) {
			BlockPos bp0 = new BlockPos(creature.posX, creature.posY, creature.posZ);

			if (blockstate.apply(theWorld.getBlockState(bp0))) {
				if (theWorld.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
					theWorld.destroyBlock(bp0, false);
				}

				creature.eatGrassBonus();
			} else {
				BlockPos bpDown = bp0.down();

				if (isFlower(bpDown) || isTallgrass(bpDown)) {
					if (theWorld.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
						theWorld.playAuxSFX(2001, bpDown, Block.getIdFromBlock(Blocks.grass));
						theWorld.setBlockState(bpDown, Blocks.dirt.getDefaultState(), 2);
					}

					creature.eatGrassBonus();
				}
			}
		}
	}
	public boolean isFlower(BlockPos bp)
	{
		Block block = theWorld.getBlockState(bp.down()).getBlock();

		return (block == Blocks.red_flower || block == Blocks.yellow_flower);
	}

	public boolean isTallgrass(BlockPos bp)
	{
		Block block = theWorld.getBlockState(bp.down()).getBlock();
		return (block == Blocks.tallgrass);
	}
}
