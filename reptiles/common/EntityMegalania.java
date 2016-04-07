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
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityMegalania extends EntityAnimal {

    private final int maxHealth = 60;
    private final float scaleFactor = 2.5f; // same scalefactor used in rendering

    public EntityMegalania(World world) {
        super(world);
        setSize(1.0F * scaleFactor, 0.6F * scaleFactor);

        double moveSpeed = 0.9;

        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(1, new EntityAILeapAtTarget(this, 0.4F));
        tasks.addTask(2, new EntityAIMate(this, moveSpeed));
        tasks.addTask(3, new EntityAIWander(this, moveSpeed));
        tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(5, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityChicken.class, false));
        targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPig.class, false));
        targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
        targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySheep.class, false));
        targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityCow.class, false));
        targetTasks.addTask(7, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, false));
        targetTasks.addTask(8, new EntityAINearestAttackableTarget(this, EntityRabbit.class, false));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(maxHealth); // health
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);  // move speed
    }

    public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
        return new EntityMegalania(worldObj);
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
    public boolean processInteract(EntityPlayer entityplayer, EnumHand enumHand, ItemStack itemStack) {
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
	protected SoundEvent getAmbientSound()
	{
		return CommonProxyReptiles.mega_purr;
	}

	@Override
	protected SoundEvent getHurtSound()
	{
        return CommonProxyReptiles.mega_growl;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
        return CommonProxyReptiles.mega_death;
	}

    @Override
    protected void playStepSound(BlockPos blockPos, Block block) {
        playSound(SoundEvents.entity_cow_step, 0.15F, 1.0F);
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
        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 4);
    }

    @Override
    protected int getExperiencePoints(EntityPlayer entityPlayer) {
        return 1 + worldObj.rand.nextInt(5);
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
