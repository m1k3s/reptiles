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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

// base class for all turtles and tortoises
public class EntityTurtle extends EntityTameable//EntityAnimal
{

	private int turtleTimer;
	private final EntityAIEatPlants plantEating = new EntityAIEatPlants(this);
	private final int maxHealth = 10;

	public EntityTurtle(World world)
	{
		super(world);
		setSize(0.5F, 0.5F);
		double moveSpeed = 0.75;

		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		tasks.addTask(2, aiSit);
		tasks.addTask(3, new EntityAIMate(this, moveSpeed));
		tasks.addTask(4, new EntityAITempt(this, moveSpeed, Items.carrot, false));
		tasks.addTask(4, new EntityAITempt(this, moveSpeed, Items.golden_carrot, false));
		tasks.addTask(5, new EntityAIFollowOwner(this, moveSpeed, 10.0F, 2.0F));
		tasks.addTask(6, plantEating);
		tasks.addTask(7, new EntityAIWander(this, moveSpeed));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(8, new EntityAILookIdle(this));
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
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2); // move speed
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(18, new Float(getHealth()));
	}

	@Override
	protected void updateAITasks()
	{
		turtleTimer = plantEating.getEatPlantTick();
		super.updateAITasks();
	}

	// This MUST be overridden in the derived class
	public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable)
	{
		Reptiles.proxy.print("[ERROR] Do NOT call this base class method directly!");
		return null;
	}

	protected boolean isSandOrGrassBlock(int x, int y, int z)
	{
		Block block = worldObj.getBlock(x, y, z);
		return (block == Blocks.sand || block == Blocks.grass);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2);
	}

	// ///////////////////////////////////////////////
	// AI grass and plant eating functions
	@SideOnly(Side.CLIENT)
	public float func_44003_c(float par1)
	{
		return turtleTimer <= 0 ? 0.0F
				: (turtleTimer >= 4 && turtleTimer <= 36 ? 1.0F
				: (turtleTimer < 4 ? ((float) turtleTimer - par1) / 4.0F
				: -((float) (turtleTimer - 40) - par1) / 4.0F));
	}

	@SideOnly(Side.CLIENT)
	public float func_44002_d(float par1)
	{
		if (turtleTimer > 4 && turtleTimer <= 36) {
			float var2 = ((float) (turtleTimer - 4) - par1) / 32.0F;
			return ((float) Math.PI / 5F) + ((float) Math.PI * 7F / 100F) * MathHelper.sin(var2 * 28.7F);
		} else {
			return turtleTimer > 0 ? ((float) Math.PI / 5F) : rotationPitch / 57.2957795131F;
		}
	}

	@Override
	public void eatGrassBonus()
	{
		if (isChild()) {
			int time = getGrowingAge() + 1200;
			if (time > 0) {
				time = 0;
			}
			setGrowingAge(time);
		}
	}

	// end AI plant eating functions
	// ////////////////////////////////////////////////
	@Override
	public void onLivingUpdate()
	{
		if (worldObj.isRemote) {
			turtleTimer = Math.max(0, turtleTimer - 1);
		}
		super.onLivingUpdate();
	}

	@Override
	protected void updateAITick()
	{
		dataWatcher.updateObject(18, Float.valueOf(getHealth()));
	}

	protected boolean isFavoriteFood(ItemStack itemstack)
	{
		return (itemstack != null && (itemstack.getItem() == Items.carrot || itemstack.getItem() == Items.golden_carrot));
	}

	@Override
	public boolean isBreedingItem(ItemStack itemStack)
	{
		return itemStack == null ? false : (!(itemStack.getItem() instanceof ItemFood) ? false : isFavoriteFood(itemStack));
	}

	@Override
	protected String getLivingSound()
	{
		return null;
	}

	@Override
	protected String getHurtSound()
	{
		return null;
	}

	@Override
	protected String getDeathSound()
	{
		return null;
	}

	// taming stuff //////////////////
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

						heal((float) itemfood.func_150905_g(itemstack));

						if (itemstack.stackSize <= 0) {
							entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, (ItemStack) null);
						}

						return true;
					}
				}
			}

			if (entityplayer.getCommandSenderName().equalsIgnoreCase(getOwnerName()) && !worldObj.isRemote && !isBreedingItem(itemstack)) {
				aiSit.setSitting(!isSitting());
				isJumping = false;
				setPathToEntity((PathEntity) null);
				setTarget((Entity) null);
				setAttackTarget((EntityLivingBase) null);
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
					setPathToEntity((PathEntity) null);
					setAttackTarget((EntityLiving) null);
					aiSit.setSitting(true);
					setHealth(maxHealth);
					setOwner(entityplayer.getCommandSenderName());
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
		} else if (!(entityAnimal instanceof EntityTurtle)) {
			return false;
		} else {
			EntityTurtle t = (EntityTurtle) entityAnimal;
			return !t.isTamed() ? false : (t.isSitting() ? false : isInLove() && t.isInLove());
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

}
