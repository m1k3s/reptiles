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
// Copyright 2011 Michael Sheppard (crackedEgg)
//
package com.reptiles.common;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import com.google.common.base.Predicate;


public class EntityVaranus extends EntityTameable {

    private final int maxHealth = 20;
    private final float scaleFactor;
    private static final DataParameter<Float> health = EntityDataManager.createKey(EntityVaranus.class, DataSerializers.FLOAT);

    @SuppressWarnings("unchecked")
    public EntityVaranus(World world) {
        super(world);
        setSize(0.6F, 0.85F);
        double moveSpeed = 1.0;
        enablePersistence();

        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIPanic(this, 0.38));
        tasks.addTask(2, new EntityAISit(this));
        tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
//        tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, true));
        targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntityAnimal.class, false, new Predicate<Entity>() {
            public boolean apply(Entity entity) {
                return entity instanceof EntityPig || entity instanceof EntityRabbit;
            }
        }));
        if (ConfigHandler.getFollowOwner()) {
            tasks.addTask(9, new EntityAIFollowOwner(this, moveSpeed, 10.0F, 2.0F));
            targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        }
        tasks.addTask(10, new EntityAIMate(this, moveSpeed));
        tasks.addTask(12, new EntityAIWander(this, moveSpeed));
        tasks.addTask(13, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        tasks.addTask(14, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));

        if (ConfigHandler.useRandomScaling()) {
            float scale = rand.nextFloat();
            scaleFactor = scale < 0.55F ? 1.0F : scale;
        } else {
            scaleFactor = 1.0F;
        }

    }

    public float getScaleFactor() {
        return scaleFactor;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);

        if (this.isTamed()) {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        } else {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        }

        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataWatcher.register(health, getHealth());
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    // This MUST be overridden in the derived class
    public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
        Reptiles.proxy.error("[ERROR] Do NOT call this base class method directly!");
        return null;
    }

    @Override
    protected boolean canDespawn() {
        return ConfigHandler.shouldDespawn() && !isTamed();
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 8;
    }

    @Override
    public int getTalkInterval() {
        return 320;
    }

    @Override
    protected float getSoundVolume() {
        return 0.3F;
    }

    @Override
    public boolean getCanSpawnHere() {
        if (super.getCanSpawnHere()) {
//            Reptiles.proxy.info("Spawning varanus ***");
            return true;
        }
        return false;
    }


    @Override
    protected SoundEvent getAmbientSound() {
        return ReptileSounds.varanus_hiss;
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
    protected void playStepSound(BlockPos blockPos, Block block) {
        playSound(SoundEvents.entity_pig_step, 0.15F, 1.0F);
    }

    @Override
    protected Item getDropItem() {
        return Items.leather;
    }

    @Override
    protected void dropFewItems(boolean flag, int add) {
        int count = rand.nextInt(3) + rand.nextInt(1 + add);
        dropItem(Items.leather, count);

        count = rand.nextInt(3) + 1 + rand.nextInt(1 + add);
        if (isBurning()) {
            dropItem(Items.cooked_beef, count);
        } else {
            dropItem(Items.beef, count);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        boolean entityFrom = entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));

        if (entityFrom) {
            this.applyEnchantments(this, entity);
        }

        return entityFrom;
    }

    public boolean isFavoriteFood(ItemStack itemstack) {
        return (itemstack != null && (itemstack.getItem() == Items.porkchop || itemstack.getItem() == Items.cooked_porkchop));
    }

    @Override
    public boolean isBreedingItem(ItemStack itemStack) {
        return itemStack != null && (itemStack.getItem() instanceof ItemFood && isFavoriteFood(itemStack));
    }

    @Override
    protected void updateAITasks() {
        dataWatcher.set(health, getHealth());
    }

    @Override
    public boolean processInteract(EntityPlayer entityplayer, EnumHand enumHand, ItemStack itemstack) {

        if (isTamed()) {
            if (itemstack != null) {
                if (itemstack.getItem() instanceof ItemFood) {
                    ItemFood itemfood = (ItemFood) itemstack.getItem();
                    if (isFavoriteFood(itemstack) && dataWatcher.get(health) < maxHealth) {
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
        } else if (itemstack != null && isFavoriteFood(itemstack) && entityplayer.getDistanceSqToEntity(this) < 9.0D) {
            if (!entityplayer.capabilities.isCreativeMode) {
                --itemstack.stackSize;
            }

            if (itemstack.stackSize <= 0) {
                entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
            }

            if (!worldObj.isRemote) {
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
        } else if (!(entityAnimal instanceof EntityVaranus)) {
            return false;
        } else {
            EntityVaranus v = (EntityVaranus) entityAnimal;
            return v.isTamed() && (!v.isSitting() && (isInLove() && v.isInLove()));
        }
    }

    @Override
    public EntityAgeable createChild(EntityAgeable entityAgeable) {
        return this.spawnBabyAnimal(entityAgeable);
    }

    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);

        if (tamed) {
            getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(maxHealth);
        } else {
            getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        }
    }

    @Override
    public EntityLivingBase getOwner() {
        return null;
    }
}
