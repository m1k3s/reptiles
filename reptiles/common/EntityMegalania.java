/*
 * EntityMegalania.java
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
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityMegalania extends EntityAnimal {

    public EntityMegalania(World world) {
        super(world);
        float scaleFactor = 2.5f;
        setSize(1.0F * scaleFactor, 0.6F * scaleFactor);
        setPathPriority(PathNodeType.WATER, 0.0f);
        setHealth(60.0f);

        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAILeapAtTarget(this, 0.4F));
        tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, true));
        tasks.addTask(3, new EntityAIWander(this, 0.9));
        tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(5, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityChicken.class, true));
        targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityPig.class, true));
        targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        targetTasks.addTask(5, new EntityAINearestAttackableTarget<>(this, EntitySheep.class, true));
        targetTasks.addTask(6, new EntityAINearestAttackableTarget<>(this, EntityCow.class, true));
        targetTasks.addTask(7, new EntityAINearestAttackableTarget<>(this, EntitySkeleton.class, true));
        targetTasks.addTask(8, new EntityAINearestAttackableTarget<>(this, EntityRabbit.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0); // health
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);  // move speed
    }

    public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
        return new EntityMegalania(world);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable entityageable) {
        return this.spawnBabyAnimal(entityageable);
    }

    @Override
    public int getTotalArmorValue() {
        return 4; // thick hide
    }

    @Override
    public boolean processInteract(EntityPlayer entityplayer, EnumHand enumHand) {
        return false;
    }

    @Override
    public int getTalkInterval() {
        return 320;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ReptileSounds.mega_purr;
    }

//    @Override
//    protected SoundEvent getHurtSound() {
//        return ReptileSounds.mega_growl;
//    }

    @Override
    protected SoundEvent getDeathSound() {
        return ReptileSounds.mega_death;
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
    public boolean attackEntityAsMob(Entity entity) {
        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 4);
    }

    @Override
    protected int getExperiencePoints(EntityPlayer entityPlayer) {
        return 1 + world.rand.nextInt(5);
    }

    @Override
    public boolean canMateWith(EntityAnimal entityAnimal) {
        if (entityAnimal == this) {
            return false;
        } else if (!(entityAnimal instanceof EntityMegalania)) {
            return false;
        } else {
            EntityMegalania m = (EntityMegalania) entityAnimal;
            return isInLove() && m.isInLove();
        }
    }

}
