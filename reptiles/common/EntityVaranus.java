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

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityVaranus extends EntityTameable {

	private final int maxHealth = 20;
	protected final int targetChance = 200;
	private final float scaleFactor;

	public EntityVaranus(World world)
	{
		super(world);
		setSize(1.2F, 0.6F);
		double moveSpeed = 1.0;

		((PathNavigateGround)getNavigator()).setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIPanic(this, 0.38));
		tasks.addTask(2, aiSit);
		tasks.addTask(5, new EntityAILeapAtTarget(this, 0.4F));
		tasks.addTask(6, new EntityAIAvoidEntity(this, new Predicate()
        {
            public boolean apply(Entity entity)
            {
                return entity instanceof EntityCreeper;
            }
			@Override
            public boolean apply(Object object)
            {
                return apply((Entity)object);
            }
        }, 6.0F, 1.0D, 1.2D));
		tasks.addTask(7, new EntityAIAttackOnCollide(this, EntityPig.class, moveSpeed, true));
		tasks.addTask(8, new EntityAIAttackOnCollide(this, EntityChicken.class, moveSpeed, true));
		if (ConfigHandler.getFollowOwner()) {
			tasks.addTask(9, new EntityAIFollowOwner(this, moveSpeed, 10.0F, 2.0F));
			targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		}
		tasks.addTask(10, new EntityAIMate(this, moveSpeed));
		tasks.addTask(12, new EntityAIWander(this, moveSpeed));
		tasks.addTask(13, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
		tasks.addTask(14, new EntityAILookIdle(this));

		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(3, new EntityAITargetNonTamed(this, EntityAnimal.class, false, new Predicate()
        {
            public boolean func_180094_a(Entity entity)
            {
                return entity instanceof EntityChicken || entity instanceof EntityRabbit || entity instanceof EntitySheep;
            }
			@Override
            public boolean apply(Object object)
            {
                return this.func_180094_a((Entity)object);
            }
        }));
		
		if (ConfigHandler.useRandomScaling()) {
			float scale = rand.nextFloat();
			scaleFactor = scale < 0.55F ? 1.0F : scale;
		} else {
			scaleFactor = 1.0F;
		}
		
	}

	public float getScaleFactor()
	{
		return scaleFactor;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		if (isTamed()) {
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth); // health
		} else {
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0); // health
		}
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3); // move speed
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(18, getHealth());
	}

	// This MUST be overridden in the derived class
	public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable)
	{
		Reptiles.proxy.error("[ERROR] Do NOT call this base class method directly!");
		return null;
	}
	
	@Override
	protected boolean canDespawn()
    {
		if (ConfigHandler.shouldDespawn()) {
			return !isTamed() && ticksExisted > 2400;
		} else {
			return false;
		}
    }
    
    @Override
    public int getMaxSpawnedInChunk()
    {
        return 8;
    }

	@Override
	public int getTalkInterval()
	{
		return 320;
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.3F;
	}

	@Override
	protected String getLivingSound()
	{
		return "reptilemod:hiss";
	}

	@Override
	protected String getHurtSound()
	{
		return "reptilemod:hurt";
	}

	@Override
	protected String getDeathSound()
	{
		return "reptilemod:hurt";
	}

	@Override
	protected void playStepSound(BlockPos blockPos, Block block)
    {
        playSound("mob.pig.step", 0.15F, 1.0F);
    }

	@Override
	protected Item getDropItem()
	{
		return Items.leather;
	}

	@Override
	protected void dropFewItems(boolean flag, int add)
	{
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
	public boolean attackEntityAsMob(Entity entity)
	{
		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2);
	}

	public boolean isFavoriteFood(ItemStack itemstack)
	{
		return (itemstack != null && (itemstack.getItem() == Items.porkchop || itemstack.getItem() == Items.cooked_porkchop));
	}

	@Override
	public boolean isBreedingItem(ItemStack itemStack)
	{
		return itemStack == null ? false : (!(itemStack.getItem() instanceof ItemFood) ? false : isFavoriteFood(itemStack));
	}

	@Override
	protected void updateAITasks()
	{
		dataWatcher.updateObject(18, getHealth());
	}

	@Override
	public boolean interact(EntityPlayer entityplayer)
	{
		ItemStack itemstack = entityplayer.inventory.getCurrentItem();

		if (isTamed()) {
			if (itemstack != null) {
				if (itemstack.getItem() instanceof ItemFood) {
					ItemFood itemfood = (ItemFood) itemstack.getItem();
					if (isFavoriteFood(itemstack) && dataWatcher.getWatchableObjectFloat(18) < maxHealth) {
						if (!entityplayer.capabilities.isCreativeMode) {
							--itemstack.stackSize;
						}

						heal((float) itemfood.getHealAmount(itemstack));

						if (itemstack.stackSize <= 0) {
							entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, (ItemStack) null);
						}

						return true;
					}
				}
			}

			if (isOwner(entityplayer) && !worldObj.isRemote && !isBreedingItem(itemstack)) {
				aiSit.setSitting(!isSitting());
				isJumping = false;
				navigator.clearPathEntity();
                setAttackTarget((EntityLivingBase)null);
			}
		} else if (itemstack != null && isFavoriteFood(itemstack) && entityplayer.getDistanceSqToEntity(this) < 9.0D) {
			if (!entityplayer.capabilities.isCreativeMode) {
				--itemstack.stackSize;
			}

			if (itemstack.stackSize <= 0) {
				entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, (ItemStack) null);
			}

			if (!this.worldObj.isRemote) {
				if (rand.nextInt(3) == 0) {
					setTamed(true);
					navigator.clearPathEntity();
					setAttackTarget((EntityLivingBase)null);
					aiSit.setSitting(true);
					setHealth(maxHealth);
//					setOwner(entityplayer.getCommandSenderName());
					setOwnerId(entityplayer.getUniqueID().toString());
					playTameEffect(true);
					worldObj.setEntityState(this, (byte) 7);
				} else {
					playTameEffect(false);
					worldObj.setEntityState(this, (byte) 6);
				}
			}

			return true;
		}

		return super.interact(entityplayer);
	}

	@Override
	public boolean canMateWith(EntityAnimal entityAnimal)
	{
		if (entityAnimal == this) {
			return false;
		} else if (!isTamed()) {
			return false;
		} else if (!(entityAnimal instanceof EntityVaranus)) {
			return false;
		} else {
			EntityVaranus v = (EntityVaranus) entityAnimal;
			return !v.isTamed() ? false : (v.isSitting() ? false : isInLove() && v.isInLove());
		}
	}

	@Override
	public EntityAgeable createChild(EntityAgeable var1)
	{
		return this.spawnBabyAnimal(var1);
	}

	@Override
	public void setTamed(boolean tamed)
	{
		super.setTamed(tamed);

		if (tamed) {
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth);
		} else {
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
		}
	}

	@Override
	public Entity getOwner() {
		return null;
	}
}
