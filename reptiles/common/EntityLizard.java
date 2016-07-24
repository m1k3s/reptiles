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

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityLizard extends EntityTameable {
    private final int maxHealth = 10;
    private static final DataParameter<Float> health = EntityDataManager.createKey(EntityLizard.class, DataSerializers.FLOAT);

    public EntityLizard(World world) {
        super(world);
        setSize(0.2F, 0.25F);
        setPathPriority(PathNodeType.WATER, 0.0f);
        setTamed(false);
    }

    @Override
    protected void initEntityAI() {
        double moveSpeed = 1.0;

        tasks.addTask(1, new EntityAISwimming(this));
        tasks.addTask(2, aiSit = new EntityAISit(this));
        tasks.addTask(2, new EntityAIPanic(this, 0.38F));
        tasks.addTask(3, new EntityAIMate(this, moveSpeed));
        tasks.addTask(4, new EntityAITempt(this, 1.2, Items.CARROT, false));
        tasks.addTask(4, new EntityAITempt(this, 1.2, Items.GOLDEN_CARROT, false));
        if (ConfigHandler.getFollowOwner()) {
            tasks.addTask(5, new EntityAIFollowOwner(this, moveSpeed, 10.0F, 2.0F));
        }
        tasks.addTask(6, new EntityAIWander(this, moveSpeed));
        tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        tasks.addTask(7, new EntityAILookIdle(this));
    }

    @Override
    protected boolean canDespawn() {
        return ConfigHandler.shouldDespawn() && !isTamed();
    }

    @Override
    public boolean getCanSpawnHere() {
        if (super.getCanSpawnHere()) {
            Reptiles.proxy.info("Spawning lizard ***");
            return true;
        }
        return false;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        if (isTamed()) {
            getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(maxHealth); // health
        } else {
            getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0); // health
        }
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2); // move speed
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(health, getHealth());
    }

    // This MUST be overridden in the derived class
    public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
        Reptiles.proxy.error("[ERROR] Do NOT call this base class method directly!");
        return null;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    @Override
    protected SoundEvent getHurtSound() {
        return ReptileSounds.varanus_hurt;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ReptileSounds.varanus_hurt;
    }

    @Override
    protected Item getDropItem() {
        return Items.PORKCHOP;
    }

    private boolean isTamingFood(ItemStack itemstack) {
        return (itemstack != null && (itemstack.getItem() == Items.CARROT || itemstack.getItem() == Items.GOLDEN_CARROT));
    }

    @Override
    public boolean isBreedingItem(ItemStack itemStack) {
        return itemStack != null && (itemStack.getItem() instanceof ItemFood && isTamingFood(itemStack));
    }

    @Override
    protected void updateAITasks() {
        dataManager.set(health, getHealth());
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2);
    }

    // taming stuff //////////////////
    @Override
    public boolean processInteract(EntityPlayer entityplayer, EnumHand enumHand, ItemStack itemstack) {

        if (isTamed()) {
            if (itemstack != null) {
                if (itemstack.getItem() instanceof ItemFood) {
                    ItemFood itemfood = (ItemFood) itemstack.getItem();
                    if (isTamingFood(itemstack) && dataManager.get(health) < maxHealth) {
                        if (!entityplayer.capabilities.isCreativeMode) {
                            --itemstack.stackSize;
                        }

                        heal((float) itemfood.getHealAmount(itemstack));

                        if (itemstack.stackSize <= 0) {
                            entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
                        }

                        return true;
                    }
                }
            }

            if (isOwner(entityplayer) && !worldObj.isRemote && !isBreedingItem(itemstack)) {
                aiSit.setSitting(!isSitting());
                isJumping = false;
                navigator.clearPathEntity();
                setAttackTarget(null);
            }
        } else if (itemstack != null && itemstack.getItem() == Items.APPLE && entityplayer.getDistanceSqToEntity(this) < 9.0D) {
            if (!entityplayer.capabilities.isCreativeMode) {
                --itemstack.stackSize;
            }

            if (itemstack.stackSize <= 0) {
                entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
            }

            if (!this.worldObj.isRemote) {
                if (rand.nextInt(3) == 0) {
                    setTamed(true);
                    navigator.clearPathEntity();
                    setAttackTarget(null);
                    aiSit.setSitting(true);
                    setHealth(maxHealth);
                    setOwnerId(entityplayer.getUniqueID());
                    playTameEffect(true);
                    worldObj.setEntityState(this, (byte) 7);
                } else {
                    playTameEffect(false);
                    worldObj.setEntityState(this, (byte) 6);
                }
            }

            return true;
        }

        return super.processInteract(entityplayer, enumHand, itemstack);
    }

    @Override
    public boolean canMateWith(EntityAnimal entityAnimal) {
        if (entityAnimal == this) {
            return false;
        } else if (!isTamed()) {
            return false;
        } else if (!(entityAnimal instanceof EntityLizard)) {
            return false;
        } else {
            EntityLizard l = (EntityLizard) entityAnimal;
            return l.isTamed() && (!l.isSitting() && (isInLove() && l.isInLove()));
        }
    }

    @Override
    public EntityAgeable createChild(EntityAgeable var1) {
        return this.spawnBabyAnimal(var1);
    }

    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);

        if (tamed) {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(maxHealth);
        } else {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        }
    }

}
