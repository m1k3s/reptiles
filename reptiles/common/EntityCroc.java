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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityCroc extends EntityAnimal {

    public EntityCroc(World world) {
        super(world);
        setSize(0.9F, 1.4F);

        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAILeapAtTarget(this, 0.5F));
        tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, true));
        tasks.addTask(3, new EntityAIWander(this, 1.0));
        tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(5, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityCow.class, true));
        targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, EntitySheep.class, true));
        targetTasks.addTask(4, new EntityAINearestAttackableTarget<>(this, EntityPig.class, true));
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }

    @Override
    protected boolean canDespawn() {
        if (ConfigHandler.shouldDespawn()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean getCanSpawnHere() {
        if (super.getCanSpawnHere()) {
            return true;
        }
        return false;
    }

    @Override
    public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn) {
        super.setAttackTarget(entitylivingbaseIn);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25);
        getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ReptileSounds.croc_growl;
    }

    @Override
    protected SoundEvent getHurtSound() {
        return ReptileSounds.croc_growl;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ReptileSounds.croc_growl;
    }

    @Override
    protected void playStepSound(BlockPos blockPos, Block block) {
        playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 1.0F);
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
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
    protected int getExperiencePoints(EntityPlayer par1EntityPlayer) {
        return 1 + world.rand.nextInt(4);
    }

    @Override
    public boolean processInteract(EntityPlayer entityplayer, EnumHand enumHand) {
        // don't allow any interaction, especially breeding
        return false;
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 4);
    }

}
