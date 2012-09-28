package reptiles.common;

import net.minecraft.src.*;

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//

import java.util.*;

public class EntityLizard extends EntityTameable//EntityAnimal
{
	private EntityAIRandomMating randomMating = new EntityAIRandomMating(this);
	
	public EntityLizard(World world) {
		super(world);
		setSize(1.0F, 1.0F);
		moveSpeed = 0.35F;

		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, aiSit);
		tasks.addTask(2, new EntityAIPanic(this, 0.38F));
		tasks.addTask(3, new EntityAIMate(this, 0.2F));
		tasks.addTask(4, new EntityAITempt(this, 0.25F, Block.plantRed.blockID, false));
		tasks.addTask(4, new EntityAITempt(this, 0.25F, Block.plantYellow.blockID, false));
		tasks.addTask(5, new EntityAIFollowOwner(this, moveSpeed, 10.0F, 2.0F));
		tasks.addTask(6, randomMating);
		tasks.addTask(6, new EntityAIWander(this, 0.2F));
		tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(7, new EntityAILookIdle(this));
	}

	public boolean isAIEnabled() {
		return true;
	}
	
	// This MUST be overridden in the derived class
	public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal) {
		System.err.println("[ERROR] Do NOT call this base class method directly!");
		return null;
	}

	public int getMaxHealth() {
		return 8;
	}

	protected float getSoundVolume() {
		return 0.4F;
	}

	protected String getLivingSound() {
		return null;
	}

	protected String getHurtSound() {
		return "hurt";
	}

	protected String getDeathSound() {
		return "hurt";
	}

	protected int getDropItemId() {
		return Item.porkRaw.shiftedIndex;
	}

	protected boolean isFavoriteFood(ItemStack itemstack) {
		return (itemstack.itemID == Block.plantRed.blockID || itemstack.itemID == Block.plantYellow.blockID);
	}
	
	public boolean isWheat(ItemStack itemstack) {
        return itemstack != null && isFavoriteFood(itemstack);
    }
	
	protected void updateAITasks() {
		if (isTamed()) { // no random mating when tame
			randomMating.resetTask();
		}
		super.updateAITasks();
	}
	
	// taming stuff //////////////////
	
	public boolean interact(EntityPlayer entityplayer) {
		ItemStack itemstack = entityplayer.inventory.getCurrentItem();
	
	    if (isTamed()) {
	        if (entityplayer.username.equalsIgnoreCase(getOwnerName()) && !worldObj.isRemote && !isWheat(itemstack)) {
	            aiSit.setSitting(!isSitting());
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
	                setOwner(entityplayer.username);
	                playTameEffect(true);
	                aiSit.setSitting(true);
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
        } else if (!(entityAnimal instanceof EntityLizard)) {
            return false;
        } else {
            EntityLizard l = (EntityLizard)entityAnimal;
            return !l.isTamed() ? false : isInLove() && l.isInLove();
        }
    }

}
