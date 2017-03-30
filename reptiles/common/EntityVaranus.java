/*
 * EntityVaranus.java
 *
 *  Copyright (c) 2017 Michael Sheppard
 *
 * =====GPLv3===========================================================
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses.
 * =====================================================================
 */

package com.reptiles.common;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class EntityVaranus extends EntityTameable {

    private final int maxHealth = 20;
    private final float scaleFactor;
    private static final DataParameter<Float> health = EntityDataManager.createKey(EntityVaranus.class, DataSerializers.FLOAT);

    @SuppressWarnings("unchecked")
    public EntityVaranus(World world) {
        super(world);
        setSize(0.4F, 0.85F);
        setPathPriority(PathNodeType.WATER, 0.0f); // avoid water

        if (ConfigHandler.useRandomScaling()) {
            float scale = rand.nextFloat();
            scaleFactor = scale < 0.55F ? 1.0F : scale;
        } else {
            scaleFactor = 1.0F;
        }
        setTamed(false);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void initEntityAI() {
        double moveSpeed = 1.0;
        tasks.addTask(1, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAIPanic(this, 0.38));
        tasks.addTask(2, aiSit = new EntityAISit(this));
        tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, true));
        tasks.addTask(4, new EntityAITempt(this, 1.2, Items.PORKCHOP, false));
        tasks.addTask(4, new EntityAITempt(this, 1.2, Items.COOKED_PORKCHOP, false));
        targetTasks.addTask(5, new EntityAITargetNonTamed(this, EntityAnimal.class, false, entity -> rand.nextInt(6) == 0 && (entity instanceof EntityPig || entity instanceof EntityRabbit)));
        if (ConfigHandler.getFollowOwner()) {
            tasks.addTask(6, new EntityAIFollowOwner(this, moveSpeed, 10.0F, 2.0F));
            targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        }
        tasks.addTask(7, new EntityAIMate(this, moveSpeed));
        tasks.addTask(8, new EntityAIWander(this, moveSpeed));
        tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        tasks.addTask(10, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
    }


    public float getScaleFactor() {
        return scaleFactor;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);

        if (isTamed()) {
            getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        } else {
            getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        }

        getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(health, getHealth());
    }
    
    @Override
    public void setAttackTarget(@Nullable EntityLivingBase entitylivingbase) {
        super.setAttackTarget(entitylivingbase);
	}

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
    }
    
    @Override
    public void onUpdate() {
		super.onUpdate();
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
        return ConfigHandler.getTalkInterval();
    }

    @Override
    protected float getSoundVolume() {
        return ConfigHandler.getTalkVolume();
    }

    @Override
    public boolean getCanSpawnHere() {
        return super.getCanSpawnHere();
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
        playSound(SoundEvents.ENTITY_PIG_STEP, 0.15F, 1.0F);
    }

    @Override
    protected Item getDropItem() {
        return Items.LEATHER;
    }

    @Override
    protected void dropFewItems(boolean flag, int add) {
        int count = rand.nextInt(3) + rand.nextInt(1 + add);
        dropItem(Items.LEATHER, count);

        count = rand.nextInt(3) + 1 + rand.nextInt(1 + add);
        if (isBurning()) {
            dropItem(Items.COOKED_BEEF, count);
        } else {
            dropItem(Items.BEEF, count);
        }
    }

    @Override
    public boolean attackEntityAsMob(@Nonnull Entity entity) {
        boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((int)getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));

        if (flag) {
            applyEnchantments(this, entity);
        }

        return flag;
    }
    
    @Override
    public boolean attackEntityFrom(@Nonnull DamageSource source, float amount) {
        if (isEntityInvulnerable(source)) {
            return false;
        } else {
            Entity entity = source.getEntity();

            if (aiSit != null) {
                aiSit.setSitting(false);
            }

            if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
                amount = (amount + 1.0F) / 2.0F;
            }

            return super.attackEntityFrom(source, amount);
        }
    }

	public boolean shouldAttackEntity(EntityLivingBase entityToAttack, EntityLivingBase entityOwner) {
        if (!(entityToAttack instanceof EntityCreeper) && !(entityToAttack instanceof EntityGhast)) {
            if (entityToAttack instanceof EntityVaranus) {
                EntityVaranus entityvaranus = (EntityVaranus)entityToAttack;

                if (entityvaranus.isTamed() && entityvaranus.getOwner() == entityOwner) {
                    return false;
                }
            }
//            return entityToAttack instanceof EntityPlayer && entityOwner instanceof EntityPlayer && !((EntityPlayer)entityOwner).canAttackPlayer((EntityPlayer)entityToAttack) ? false : !(entityToAttack instanceof EntityHorse) || !((EntityHorse)entityToAttack).isTame();
            return !(entityToAttack instanceof EntityPlayer && entityOwner instanceof EntityPlayer && !((EntityPlayer) entityOwner).canAttackPlayer((EntityPlayer) entityToAttack)) && (!(entityToAttack instanceof EntityHorse) || !((EntityHorse) entityToAttack).isTame());
        } else {
            return false;
        }
    }

    private boolean isFavoriteFood(ItemStack itemstack) {
        return (itemstack != null && (itemstack.getItem() == Items.COOKED_PORKCHOP));
    }

    @Override
    public boolean isBreedingItem(ItemStack itemStack) {
        return itemStack != null && (itemStack.getItem() instanceof ItemFood && isFavoriteFood(itemStack));
    }

    @Override
    protected void updateAITasks() {
        dataManager.set(health, getHealth());
    }

    @Override
    public boolean processInteract(EntityPlayer entityplayer, @Nonnull EnumHand enumHand) {
        ItemStack itemstack = entityplayer.getHeldItem(enumHand);

        if (isTamed()) {
            if (!itemstack.isEmpty()) {
                if (itemstack.getItem() instanceof ItemFood) {
                    ItemFood itemfood = (ItemFood) itemstack.getItem();
                    if (isFavoriteFood(itemstack) && dataManager.get(health) < maxHealth) {
                        if (!entityplayer.capabilities.isCreativeMode) {
                            itemstack.shrink(1);
                        }

                        heal((float) itemfood.getHealAmount(itemstack));
                        return true;
                    }
                }
            }

            if (isOwner(entityplayer) && !world.isRemote && !isBreedingItem(itemstack)) {
                aiSit.setSitting(!isSitting());
                isJumping = false;
                navigator.clearPathEntity();
                setAttackTarget(null);
            }
        } else if (itemstack.getItem() == Items.PORKCHOP) { // raw porkchop
            if (!entityplayer.capabilities.isCreativeMode) {
                itemstack.shrink(1);
            }

            if (!world.isRemote) {
                if (rand.nextInt(3) == 0) {
                    setTamed(true);
                    navigator.clearPathEntity();
                    setAttackTarget(null);
                    aiSit.setSitting(true);
                    setHealth(maxHealth);
                    setOwnerId(entityplayer.getUniqueID());
                    playTameEffect(true);
                    world.setEntityState(this, (byte) 7);
                } else {
                    playTameEffect(false);
                    world.setEntityState(this, (byte) 6);
                }
            }
            return true;
        }

        return super.processInteract(entityplayer, enumHand);
    }

    @Override
    public boolean canMateWith(@Nonnull EntityAnimal otherAnimal) {
        if (otherAnimal == this) {
            return false;
        } else if (!isTamed()) {
            return false;
        } else if (!(otherAnimal instanceof EntityVaranus)) {
            return false;
        } else {
            EntityVaranus v = (EntityVaranus) otherAnimal;
            return v.isTamed() && (!v.isSitting() && (isInLove() && v.isInLove()));
        }
    }

    @Override
    public EntityAgeable createChild(@Nonnull EntityAgeable entityAgeable) {
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
    public boolean canBeLeashedTo(EntityPlayer player) {
        return super.canBeLeashedTo(player);
    }

}
