//
// This work is licensed under the Creative Commons
// Attribution-ShareAlike 3.0 Unported License. To view a copy of this
// license, visit http://creativecommons.org/licenses/by-sa/3.0/
//

package reptiles.common;

import java.util.*;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityCroc extends EntityAnimal {
	final float attackDistance;
	protected int attackStrength;
	private int maxHealth = 20;

	public EntityCroc(World world) {
		super(world);
		texture = "/mob/croc32.png";
		setSize(0.8F, 1.5F);

		attackStrength = 2;
		moveSpeed = 0.25F;
		attackDistance = 16F;
		health = maxHealth;

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
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, attackDistance, 0, true));
		targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityCow.class, attackDistance, 0, false));
		targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntitySheep.class, attackDistance, 0, false));
		targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityPig.class, attackDistance, 0, false));
	}

	public boolean isAIEnabled() {
		return true;
	}
	
	public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
		Reptiles.proxy.print("Spawned entity of type " + getClass().toString());
		return new EntityCroc(worldObj);
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}

	protected String getLivingSound() {
		return "croc.growl";
	}

	protected String getHurtSound() {
		return "croc.growl";
	}

	protected String getDeathSound() {
		return "croc.growl";
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
