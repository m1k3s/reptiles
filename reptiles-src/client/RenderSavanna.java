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

package reptiles.client;

import reptiles.common.EntitySavanna;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
//import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class RenderSavanna extends RenderLiving {
	private static final ResourceLocation skin = new ResourceLocation("reptilemod", "textures/entity/reptiles/savanna32.png");
	
	public RenderSavanna(ModelBase modelbase, float shadowSize) {
		super(modelbase, shadowSize);
	}

	public void renderSavanna(EntitySavanna entitysavanna, double d, double d1, double d2, float f, float f1) {
		super.doRenderLiving(entitysavanna, d, d1, d2, f, f1);
	}

	public void doRenderLiving(EntityLivingBase entityliving, double d, double d1, double d2, float f, float f1) {
		super.doRenderLiving((EntitySavanna) entityliving, d, d1, d2, f, f1);
	}

    @Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
		super.doRenderLiving((EntitySavanna) entity, d, d1, d2, f, f1);
	}

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		return skin;
	}
}
