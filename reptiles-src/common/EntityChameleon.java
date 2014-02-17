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

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public final class EntityChameleon extends EntityLizard {

	public EntityChameleon(World world) {
		super(world);
		setSize(0.25F, 0.25F);
		setTamed(false);
	}

    @Override
	public EntityAnimal spawnBabyAnimal(EntityAgeable entityageable) {
		EntityChameleon c = new EntityChameleon(worldObj);
		if (isTamed()) {
			c.setOwner(getOwnerName());
			c.setTamed(true);
		}
		System.out.printf("Spawned entity of type %s", getClass().toString());
		return c;
	}
    
    @Override
    public float getBrightness(float par1) {
        int i = MathHelper.floor_double(posX);
        int j = MathHelper.floor_double(posZ);

        if (worldObj.blockExists(i, 0, j)) {
            double d0 = (boundingBox.maxY - boundingBox.minY) * 0.66D;
            int k = MathHelper.floor_double(posY - (double)yOffset + d0);
            return worldObj.getLightBrightness(i, k, j);
        } else {
            return 0.25F;
        }
    }

}
