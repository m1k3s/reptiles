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


import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityLizard extends EntityTameable//EntityAnimal
{
	private EntityAIRandomMating randomMating = new EntityAIRandomMating(this);
	
	public EntityLizard(World world) {
		super(world);
		setSize(1.0F, 1.0F);
		double moveSpeed = 0.35;

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

    @Override
	public boolean isAIEnabled() {
		return true;
	}
	
    @Override
	protected void func_110147_ax() {
        super.func_110147_ax();
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0); // health
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2); // move speed
    }
	
	// This MUST be overridden in the derived class
	public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
		Reptiles.proxy.print("[ERROR] Do NOT call this base class method directly!");
		return null;
	}

	public int getMaxHealth() {
		return 8;
	}

    @Override
	protected float getSoundVolume() {
		return 0.4F;
	}

    @Override
	protected String getLivingSound() {
		return null;
	}

    @Override
	protected String getHurtSound() {
		return "reptilemod:hurt";
	}

    @Override
	protected String getDeathSound() {
		return "reptilemod:hurt";
	}

    @Override
	protected int getDropItemId() {
		return Item.porkRaw.itemID;
	}

	protected boolean isFavoriteFood(ItemStack itemstack) {
		return (itemstack.itemID == Block.plantRed.blockID || itemstack.itemID == Block.plantYellow.blockID);
	}
	
	public boolean isWheat(ItemStack itemstack) {
        return itemstack != null && isFavoriteFood(itemstack);
    }
	
    @Override
	protected void updateAITasks() {
		if (isTamed()) { // no random mating when tame
			randomMating.resetTask();
		}
		super.updateAITasks();
	}
	
	// taming stuff //////////////////
	
    @Override
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
	
    @Override
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

	@Override
	public EntityAgeable createChild(EntityAgeable var1) {
		return this.spawnBabyAnimal(var1);
	}

}
