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

package reptiles.common;

import java.util.*;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityCroc extends EntityAnimal {
	final float attackDistance;
	protected int attackStrength;
//	private int maxHealth = 20;

	public EntityCroc(World world) {
		super(world);
		setSize(0.8F, 1.5F);

		attackStrength = 2;
		double moveSpeed = 0.25;
		attackDistance = 16F;

		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAILeapAtTarget(this, 0.5F));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, moveSpeed, true));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityCow.class, moveSpeed, true));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, EntitySheep.class, moveSpeed, true));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPig.class, moveSpeed, true));
		tasks.addTask(3, new EntityAIMate(this, moveSpeed));
		tasks.addTask(4, new EntityAIRandomMating(this));
		tasks.addTask(4, new EntityAIWander(this, moveSpeed));
		tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(5, new EntityAILookIdle(this));

		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityCow.class, 0, false));
		targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntitySheep.class, 0, false));
		targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityPig.class, 0, false));
	}

	public boolean isAIEnabled() {
		return true;
	}
	
	protected void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0); // health
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2); // move speed
    }
	
	public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
		Reptiles.proxy.print("Spawned entity of type " + getClass().toString());
		return new EntityCroc(worldObj);
	}
	
//	public int getMaxHealth() {
//		return maxHealth;
//	}

	protected String getLivingSound() {
		return "reptilemod:growl";
	}

	protected String getHurtSound() {
		return "reptilemod:growl";
	}

	protected String getDeathSound() {
		return "reptilemod:growl";
	}

	protected float getSoundVolume() {
		return 0.4F;
	}

	protected int getDropItemId() {
		return Item.leather.itemID;
	}

	protected void dropFewItems(boolean flag, int add) {
		int count = rand.nextInt(3) + rand.nextInt(1 + add);
		dropItem(Item.leather.itemID, count);

		count = rand.nextInt(3) + 1 + rand.nextInt(1 + add);
		if (isBurning()) {
			dropItem(Item.beefCooked.itemID, count);
		} else {
			dropItem(Item.beefRaw.itemID, count);
		}
	}
	
	protected int getExperiencePoints(EntityPlayer par1EntityPlayer) {
		return 1 + worldObj.rand.nextInt(4);
	}

	public boolean interact(EntityPlayer entityplayer) {
		// don't allow any interaction, especially breeding
		return false;
	}

	public boolean attackEntityAsMob(Entity par1Entity) {
		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 4);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable var1) {
		return this.spawnBabyAnimal(var1);
	}

}
