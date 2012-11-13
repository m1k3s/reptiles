package reptiles.common;

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//

import java.util.*;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.*;

public class EntityVaranus extends EntityTameable
{
	protected final float attackDistance;
	private final int maxHealth = 20;
	protected final int targetChance = 200;
	private EntityAIRandomMating randomMating = new EntityAIRandomMating(this);

	public EntityVaranus(World world) {
		super(world);
		setSize(1.0F, 1.0F);
		moveSpeed = 0.3F;
		attackDistance = 16F;
		health = maxHealth;

		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		tasks.addTask(2, aiSit);
		tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
		tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityPig.class, moveSpeed, true));
		tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityChicken.class, moveSpeed, true));
		tasks.addTask(5, new EntityAITempt(this, 0.25F, Item.porkRaw.shiftedIndex, false));
		tasks.addTask(6, new EntityAIFollowOwner(this, moveSpeed, 10.0F, 2.0F));
		tasks.addTask(7, new EntityAIMate(this, moveSpeed));
		tasks.addTask(8, randomMating);
		tasks.addTask(8, new EntityAIWander(this, moveSpeed));
		tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(9, new EntityAILookIdle(this));
		
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityChicken.class, attackDistance, targetChance, false));
		targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPig.class, attackDistance, targetChance, false));
	}

	public boolean isAIEnabled() {
		return true;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	// This MUST be overridden in the derived class
	public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
		System.err.println("[ERROR] Do NOT call this base class method directly!");
		return null;
	}

	public int getTalkInterval() {
		return 320;
	}

	protected float getSoundVolume() {
		return 0.3F;
	}

	protected String getLivingSound() {
		return "monitor.hiss";
	}

	protected String getHurtSound() {
		return "monitor.hurt";
	}

	protected String getDeathSound() {
		return "monitor.hurt";
	}

	protected int getDropItemId() {
		return Item.leather.shiftedIndex;
	}

	protected void dropFewItems(boolean flag, int add) {
		int count = rand.nextInt(3) + rand.nextInt(1 + add);
		dropItem(Item.leather.shiftedIndex, count);

		count = rand.nextInt(3) + 1 + rand.nextInt(1 + add);
		if (isBurning()) {
			dropItem(Item.beefCooked.shiftedIndex, count);
		} else {
			dropItem(Item.beefRaw.shiftedIndex, count);
		}
	}
	
	public boolean attackEntityAsMob(Entity entity) {
        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2);
    }
	
	public boolean isFavoriteFood(ItemStack itemstack) {
		return (itemstack != null && itemstack.itemID == Item.porkRaw.shiftedIndex);
	}
	
	public boolean isWheat(ItemStack itemstack) {
		return (itemstack != null && isFavoriteFood(itemstack));
	}
	
	// taming stuff //////////////////
	
	protected void updateAITasks() {
		if (isTamed()) { // no random mating when tame
			randomMating.resetTask();
		}
		super.updateAITasks();
	}
	
	public boolean interact(EntityPlayer entityplayer) {
		ItemStack itemstack = entityplayer.inventory.getCurrentItem();
	
	    if (isTamed()) {
	        if (entityplayer.username.equalsIgnoreCase(getOwnerName()) && !worldObj.isRemote && !isWheat(itemstack)) {
	            aiSit.setSitting(!isSitting());
	            isJumping = false;
                setPathToEntity((PathEntity)null);
	        }
	    } else if (itemstack != null && isFavoriteFood(itemstack) && entityplayer.getDistanceSqToEntity(this) < 9.0D) {
	        if (!entityplayer.capabilities.isCreativeMode) {
	            --itemstack.stackSize;
	        }
	
	        if (itemstack.stackSize <= 0) {
	            entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, (ItemStack)null);
	        }
	
	        if (!this.worldObj.isRemote) {
	            if (rand.nextInt(3) == 0) {
	                setTamed(true);
	                setPathToEntity((PathEntity)null);
                    setAttackTarget((EntityLiving)null);
                    aiSit.setSitting(true);
                    setEntityHealth(maxHealth);
	                setOwner(entityplayer.username);
	                playTameEffect(true);
	                worldObj.setEntityState(this, (byte)7);
	            } else {
	                playTameEffect(false);
	                worldObj.setEntityState(this, (byte)6);
	            }
	        }
	
	        return true;
	    }
	
	    return super.interact(entityplayer);
    }
	
	public boolean canMateWith(EntityAnimal entityAnimal) {
        if (entityAnimal == this) {
            return false;
        } else if (!isTamed()) {
            return false;
        } else if (!(entityAnimal instanceof EntityVaranus)) {
            return false;
        } else {
            EntityVaranus v = (EntityVaranus)entityAnimal;
            return !v.isTamed() ? false : isInLove() && v.isInLove();
        }
    }

	@Override
	public EntityAgeable func_90011_a(EntityAgeable var1) {
		return this.spawnBabyAnimal(var1);
	}
	
}
