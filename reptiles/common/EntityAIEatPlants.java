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
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityAIEatPlants extends EntityAIBase {
    private static final Predicate<IBlockState> plants = BlockStateMatcher.forBlock(Blocks.TALLGRASS).where(BlockTallGrass.TYPE, Predicates.equalTo(BlockTallGrass.EnumType.GRASS));
    private EntityLiving plantEaterEntity;
    private World entityWorld;
    int eatingPlantsTimer;

    public EntityAIEatPlants(EntityLiving grassEaterEntityIn) {
        plantEaterEntity = grassEaterEntityIn;
        entityWorld = grassEaterEntityIn.world;
        setMutexBits(7);
    }

    @Override
    public boolean shouldExecute() {
        if (plantEaterEntity.getRNG().nextInt(plantEaterEntity.isChild() ? 50 : 1000) != 0) {
            return false;
        } else {
            BlockPos blockpos = new BlockPos(plantEaterEntity.posX, plantEaterEntity.posY, plantEaterEntity.posZ);
            return plants.apply(entityWorld.getBlockState(blockpos)) ? true : entityWorld.getBlockState(blockpos.down()).getBlock() == Blocks.GRASS;
        }
    }

    @Override
    public void startExecuting() {
        eatingPlantsTimer = 40;
        entityWorld.setEntityState(plantEaterEntity, (byte) 10);
        plantEaterEntity.getNavigator().clearPathEntity();
    }

    @Override
    public void resetTask() {
        eatingPlantsTimer = 0;
    }

    @Override
    public boolean continueExecuting() {
        return eatingPlantsTimer > 0;
    }

    public int getEatingPlantsTimer() {
        return eatingPlantsTimer;
    }

    @Override
    public void updateTask() {
        eatingPlantsTimer = Math.max(0, eatingPlantsTimer - 1);

        if (eatingPlantsTimer == 4) {
            BlockPos blockpos = new BlockPos(plantEaterEntity.posX, plantEaterEntity.posY, plantEaterEntity.posZ);

            if (plants.apply(entityWorld.getBlockState(blockpos))) {
                if (entityWorld.getGameRules().getBoolean("mobGriefing")) {
                    entityWorld.destroyBlock(blockpos, false);
                }

                plantEaterEntity.eatGrassBonus();
            } else {
                BlockPos blockpos1 = blockpos.down();

                if (entityWorld.getBlockState(blockpos1).getBlock() == Blocks.GRASS) {
                    if (entityWorld.getGameRules().getBoolean("mobGriefing")) {
                        entityWorld.playEvent(2001, blockpos1, Block.getIdFromBlock(Blocks.GRASS));
                        entityWorld.setBlockState(blockpos1, Blocks.DIRT.getDefaultState(), 2);
                    }

                    plantEaterEntity.eatGrassBonus();
                }
            }
        }
    }
}
