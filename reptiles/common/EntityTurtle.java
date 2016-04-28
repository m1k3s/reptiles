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
package com.reptiles.common;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

// base class for all turtles and tortoises
public class EntityTurtle extends EntityAnimal {
    private static final DataParameter<Float> health = EntityDataManager.createKey(EntityTurtle.class, DataSerializers.FLOAT);
    private int turtleTimer;
    private final EntityAIEatPlants plantEating = new EntityAIEatPlants(this);
    private final int maxHealth = 10;


    public EntityTurtle(World world) {
        super(world);
        setSize(0.5F, 0.5F);
        double moveSpeed = 0.75;
        enablePersistence();
        setPathPriority(PathNodeType.WATER, 0.0f);

        tasks.addTask(1, new EntityAISwimming(this));
        //tasks.addTask(1, new EntityAIPanic(this, 0.38F));
        tasks.addTask(3, new EntityAIMate(this, moveSpeed));
        tasks.addTask(4, new EntityAITempt(this, moveSpeed, Items.carrot, false));
        tasks.addTask(4, new EntityAITempt(this, moveSpeed, Items.golden_carrot, false));
        tasks.addTask(6, plantEating);
        tasks.addTask(7, new EntityAIWander(this, moveSpeed));
        tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        tasks.addTask(8, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0); // health
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2); // move speed
    }

    @Override
    protected boolean canDespawn() {
        return ConfigHandler.shouldDespawn();
    }
    
    @Override
    protected void entityInit() {
        super.entityInit();
        dataWatcher.register(health, getHealth());
    }

    @Override
    protected void updateAITasks() {
        turtleTimer = plantEating.getEatingPlantsTimer();
        super.updateAITasks();
    }

    // This MUST be overridden in the derived class
    public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
        Reptiles.proxy.error("[ERROR] Do NOT call this base class method directly!");
        return null;
    }

    private boolean isHardenedClay(BlockPos bp) {
        Block block = worldObj.getBlockState(bp).getBlock();
        return block == Blocks.hardened_clay;
    }

    private boolean isSandOrGrassBlock(BlockPos bp) {
        Block block = worldObj.getBlockState(bp).getBlock();
        return (block == Blocks.sand || block == Blocks.grass);
    }

    @Override
    public boolean getCanSpawnHere() {
        AxisAlignedBB entityAABB = getEntityBoundingBox();
        if (worldObj.checkNoEntityCollision(entityAABB)) {
            if (worldObj.getCollisionBoxes(entityAABB).isEmpty()) {
                if (!worldObj.isAnyLiquid(entityAABB)) {
                    int x = MathHelper.floor_double(posX);
                    int y = MathHelper.floor_double(entityAABB.minY);
                    int z = MathHelper.floor_double(posZ);
                    BlockPos bp = new BlockPos(x, y, z);
                    if (isHardenedClay(bp) || isSandOrGrassBlock(bp)) {
                        if (worldObj.getLight(bp) > 8) {
							Reptiles.proxy.info("Spawning turtle ***");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2);
    }

    @Override
    public void onLivingUpdate() {
        if (worldObj.isRemote) {
            turtleTimer = Math.max(0, turtleTimer - 1);
        }
        super.onLivingUpdate();
    }

    private boolean isFavoriteFood(ItemStack itemstack) {
        return (itemstack != null && (itemstack.getItem() == Items.carrot || itemstack.getItem() == Items.golden_carrot));
    }

    @Override
    public boolean isBreedingItem(ItemStack itemStack) {
        return itemStack != null && (itemStack.getItem() instanceof ItemFood && isFavoriteFood(itemStack));
    }

    @Override
    public boolean processInteract(EntityPlayer entityplayer, EnumHand hand, ItemStack itemstack) {
        return super.processInteract(entityplayer, EnumHand.MAIN_HAND, itemstack);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable var1) {
        return this.spawnBabyAnimal(var1);
    }
}
