package reptiles.client;

import reptiles.common.EntityKomodo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;

//
// Copyright 2011 Michael Sheppard (crackedEgg)
//

public class RenderKomodo extends RenderLiving {
	public RenderKomodo(ModelBase modelbase, float shadowSize) {
		super(modelbase, shadowSize);
	}

	public void renderKomodo(EntityKomodo entitykomodo, double d, double d1, double d2, float f, float f1) {
		super.doRenderLiving(entitykomodo, d, d1, d2, f, f1);
	}

	public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2, float f, float f1) {
		super.doRenderLiving((EntityKomodo) entityliving, d, d1, d2, f, f1);
	}

	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
		super.doRenderLiving((EntityKomodo) entity, d, d1, d2, f, f1);
	}
}
