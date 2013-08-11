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

package reptiles.common;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;


public class EntityVaranus extends EntityTameable
{
//	protected final float attackDistance;
	private final int maxHealth = 20;
	protected final int targetChance = 200;
	private EntityAIRandomMating randomMating = new EntityAIRandomMating(this);

	public EntityVaranus(World world) {
		super(world);
		setSize(0.6F, 0.8F);
		double moveSpeed = 1.0;

		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIPanic(this, 0.38));
		tasks.addTask(2, aiSit);
		tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        tasks.addTask(3, new EntityAIAvoidEntity(this, EntityCreeper.class, 6.0F, 1.0D, 1.2D));
		tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityPig.class, moveSpeed, true));
		tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityChicken.class, moveSpeed, true));
		tasks.addTask(5, new EntityAITempt(this, 1.2D, Item.porkRaw.itemID, false));
		tasks.addTask(6, new EntityAIFollowOwner(this, moveSpeed, 10.0F, 2.0F));
		tasks.addTask(7, new EntityAIMate(this, moveSpeed));
		tasks.addTask(8, randomMating);
		tasks.addTask(9, new EntityAIWander(this, moveSpeed));
		tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(9, new EntityAILookIdle(this));
		
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		targetTasks.addTask(3, new EntityAITargetNonTamed(this, EntityChicken.class, targetChance, false));
		targetTasks.addTask(3, new EntityAITargetNonTamed(this, EntityPig.class, targetChance, false));
        setTamed(false);
	}
	
    @Override
	protected void func_110147_ax() {
        super.func_110147_ax();
        if (this.isTamed()) {
            func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(maxHealth); // health
        } else {
            func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(8.0); // health
        }
        func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3); // move speed
    }

    @Override
	public boolean isAIEnabled() {
		return true;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	// This MUST be overridden in the derived class
	public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
		Reptiles.proxy.print("[ERROR] Do NOT call this base class method directly!");
		return null;
	}

    @Override
	public int getTalkInterval() {
		return 320;
	}

    @Override
	protected float getSoundVolume() {
		return 0.3F;
	}

    @Override
	protected String getLivingSound() {
		return "reptilemod:hiss";
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
	protected void playStepSound(int x, int y, int z, int blockID) {
        playSound("mob.pig.step", 0.15F, 1.0F);
    }

    @Override
	protected int getDropItemId() {
		return Item.leather.itemID;
	}

    @Override
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
	
    @Override
	public boolean attackEntityAsMob(Entity entity) {
        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 2);
    }
	
	public boolean isFavoriteFood(ItemStack itemstack) {
		return (itemstack != null && itemstack.itemID == Item.porkRaw.itemID);
	}
	
	public boolean isWheat(ItemStack itemstack) {
		return (itemstack != null && isFavoriteFood(itemstack));
	}
	
    @Override
	public boolean isBreedingItem(ItemStack itemStack) {
        return itemStack != null && itemStack.getItem().itemID == Item.porkRaw.itemID;
    }
	
	// taming stuff //////////////////
	
    @Override
	protected void updateAITasks() {
		if (isTamed()) { // no random mating when tame
			randomMating.resetTask();
		}
		super.updateAITasks();
	}
	
    @Override
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
	
    @Override
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
	public EntityAgeable createChild(EntityAgeable var1) {
		return this.spawnBabyAnimal(var1);
	}
    
    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);

        if (tamed) {
            this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(maxHealth);
        } else {
            this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(8.0D);
        }
    }
	
}
