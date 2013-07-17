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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
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

		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIPanic(this, 0.38F));
		tasks.addTask(2, aiSit);
		tasks.addTask(3, new EntityAIMate(this, 0.2F));
		tasks.addTask(4, new EntityAITempt(this, 0.25F, Block.plantRed.blockID, false));
		tasks.addTask(4, new EntityAITempt(this, 0.25F, Block.plantYellow.blockID, false));
		tasks.addTask(5, new EntityAIFollowOwner(this, 0.25, 10.0F, 2.0F));
		tasks.addTask(6, plantEating);
		tasks.addTask(7, randomMating);
		tasks.addTask(7, new EntityAIWander(this, 0.25F));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(8, new EntityAILookIdle(this));
	}
	
	protected void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0); // health
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2); // move speed
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
//		System.err.println("[ERROR] Do NOT call this base class method directly!");
		Reptiles.proxy.print("[ERROR] Do NOT call this base class method directly!");
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
	public EntityAgeable createChild(EntityAgeable var1) {
		return this.spawnBabyAnimal(var1);
	}

}
