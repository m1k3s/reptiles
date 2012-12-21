package reptiles.common;


//
// Copyright 2011 Michael Sheppard (crackedEgg)
//

import java.util.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

// base class for all turtles and tortoises
public class EntityTurtle extends EntityTameable//EntityAnimal
{
	private int turtleTimer;
	private EntityAIEatPlants plantEating = new EntityAIEatPlants(this);
	private EntityAIRandomMating randomMating = new EntityAIRandomMating(this);

	public EntityTurtle(World world) {
		super(world);
		setSize(0.5F, 0.5F);
		moveSpeed = 0.25F;

		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		tasks.addTask(2, aiSit);
		tasks.addTask(3, new EntityAIMate(this, 0.2F));
		tasks.addTask(4, new EntityAITempt(this, 0.25F, Block.plantRed.blockID, false));
		tasks.addTask(4, new EntityAITempt(this, 0.25F, Block.plantYellow.blockID, false));
		tasks.addTask(5, new EntityAIFollowOwner(this, moveSpeed, 10.0F, 2.0F));
		tasks.addTask(6, plantEating);
		tasks.addTask(7, randomMating);
		tasks.addTask(7, new EntityAIWander(this, 0.2F));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(8, new EntityAILookIdle(this));
	}

	public boolean isAIEnabled() {
		return true;
	}

	protected void updateAITasks() {
		turtleTimer = plantEating.getEatPlantTick();
		if (isTamed()) { // no random mating when tame
			randomMating.resetTask();
		}
		super.updateAITasks();
	}

	// This MUST be overridden in the derived class
	public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
		System.err.println("[ERROR] Do NOT call this base class method directly!");
		return null;
	}

	public int getMaxHealth() {
		return 8;
	}
	
	protected boolean isSandOrGrassBlock(int x, int y, int z) {
		int blockID = worldObj.getBlockId(x, y, z);
		return (blockID == Block.sand.blockID || blockID == Block.grass.blockID);
	}

	// ///////////////////////////////////////////////
	// AI grass and plant eating functions
	
	@SideOnly(Side.CLIENT)
	public float func_44003_c(float par1) {
		return turtleTimer <= 0 ? 0.0F
				: (turtleTimer >= 4 && turtleTimer <= 36 ? 1.0F
						: (turtleTimer < 4 ? ((float) turtleTimer - par1) / 4.0F
								: -((float) (turtleTimer - 40) - par1) / 4.0F));
	}

	@SideOnly(Side.CLIENT)
	public float func_44002_d(float par1) {
		if (turtleTimer > 4 && turtleTimer <= 36) {
			float var2 = ((float) (turtleTimer - 4) - par1) / 32.0F;
			return ((float) Math.PI / 5F) + ((float) Math.PI * 7F / 100F)	* MathHelper.sin(var2 * 28.7F);
		} else {
			return turtleTimer > 0 ? ((float) Math.PI / 5F) : rotationPitch / 57.2957795131F;
		}
	}

	public void eatGrassBonus() {
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

	public void onLivingUpdate() {
		if (worldObj.isRemote) {
			turtleTimer = Math.max(0, turtleTimer - 1);
		}
		super.onLivingUpdate();
	}
	
	protected boolean isFavoriteFood(ItemStack itemstack) {
		return (itemstack.itemID == Block.plantRed.blockID || itemstack.itemID == Block.plantYellow.blockID);
	}
	
	public boolean isWheat(ItemStack itemstack) {
        return itemstack != null && isFavoriteFood(itemstack);
    }

	protected String getLivingSound() {
		return null;
	}

	protected String getHurtSound() {
		return null;
	}

	protected String getDeathSound() {
		return null;
	}

	protected int getDropItemId() {
		return -1;
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
        } else if (!(entityAnimal instanceof EntityTurtle)) {
            return false;
        } else {
            EntityTurtle t = (EntityTurtle)entityAnimal;
            return !t.isTamed() ? false : isInLove() && t.isInLove();
        }
    }

	@Override
	public EntityAgeable func_90011_a(EntityAgeable var1) {
		return this.spawnBabyAnimal(var1);
	}

}
