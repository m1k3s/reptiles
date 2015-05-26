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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityLizard extends EntityAnimal
{
	private final int maxHealth = 10;

	public EntityLizard(World world)
	{
		super(world);
		setSize(1.0F, 1.0F);
		double moveSpeed = 1.0;

		((PathNavigateGround)getNavigator()).setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIPanic(this, 0.38F));
		tasks.addTask(3, new EntityAIMate(this, moveSpeed));
		tasks.addTask(4, new EntityAITempt(this, 1.2, Items.carrot, false));
		tasks.addTask(4, new EntityAITempt(this, 1.2, Items.golden_carrot, false));
		tasks.addTask(6, new EntityAIWander(this, moveSpeed));
		tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(7, new EntityAILookIdle(this));
	}

	@Override
	protected boolean canDespawn()
    {
		return ConfigHandler.shouldDespawn() && ticksExisted > 2400;
    }

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0); // health
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2); // move speed
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
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	@Override
	protected String getLivingSound()
	{
		return null;
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
	protected Item getDropItem()
	{
		return Items.porkchop;
	}

	@Override
	protected void updateAITasks()
	{
		dataWatcher.updateObject(18, getHealth());
	}

	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2);
	}

	@Override
	public boolean canMateWith(EntityAnimal entityAnimal)
	{
		if (entityAnimal == this) {
			return false;
		} else if (!(entityAnimal instanceof EntityLizard)) {
			return false;
		} else {
			EntityLizard l = (EntityLizard) entityAnimal;
			return isInLove() && l.isInLove();
		}
	}

	@Override
	public EntityAgeable createChild(EntityAgeable var1)
	{
		return this.spawnBabyAnimal(var1);
	}

}
