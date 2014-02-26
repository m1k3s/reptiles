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
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMegalania extends EntityAnimal {

	protected final int targetChance = 0;
	private final int maxHealth = 20;

	public EntityMegalania(World world)
	{
		super(world);
		setSize(2.0F, 3.0F);

		double moveSpeed = 1.0;

		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAILeapAtTarget(this, 0.4F));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPig.class, moveSpeed, true));
		tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityChicken.class, moveSpeed, true));
		tasks.addTask(4, new EntityAIAttackOnCollide(this, EntitySheep.class, moveSpeed, true));
		tasks.addTask(5, new EntityAIAttackOnCollide(this, EntityCow.class, moveSpeed, true));
		tasks.addTask(6, new EntityAIAttackOnCollide(this, EntityPlayer.class, moveSpeed, true));
		tasks.addTask(7, new EntityAIMate(this, moveSpeed));
		tasks.addTask(8, new EntityAIWander(this, moveSpeed));
		tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(10, new EntityAILookIdle(this));

		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityChicken.class, targetChance, false));
		targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPig.class, targetChance, false));
		targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityPlayer.class, targetChance, false));
		targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySheep.class, targetChance, false));
		targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityCow.class, targetChance, false));
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHealth); // health
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);  // move speed
	}

	public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable)
	{
//		Reptiles.proxy.print("Spawned entity of type " + getClass().toString());
		return new EntityMegalania(worldObj);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable)
	{
		return this.spawnBabyAnimal(entityageable);
	}

	@Override
	public int getTotalArmorValue()
	{ // thick hide
		return 2;
	}

	@Override
	public boolean interact(EntityPlayer entityplayer)
	{
		return false;
	}

//	public int getMaxHealth() {
//		return maxHealth;
//	}
	@Override
	public int getTalkInterval()
	{
		return 320;
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	@Override
	protected String getLivingSound()
	{
		return "reptilemod:purr";
	}

	@Override
	protected String getHurtSound()
	{
		return "reptilemod:megagrowl";
	}

	@Override
	protected String getDeathSound()
	{
		return "reptilemod:death";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block)
	{
		playSound("mob.pig.step", 0.15F, 1.0F);
	}

//    @Override
//	protected void playStepSound(int x, int y, int z, int blockID) {
//        playSound("mob.cow.step", 0.15F, 1.0F);
//    }
//
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

	@Override
	protected int getExperiencePoints(EntityPlayer entityPlayer)
	{
		return 1 + worldObj.rand.nextInt(5);
	}

	@Override
	public boolean canMateWith(EntityAnimal entityAnimal)
	{
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
