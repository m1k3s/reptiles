//
// This work is licensed under the Creative Commons
// Attribution-ShareAlike 3.0 Unported License. To view a copy of this
// license, visit http://creativecommons.org/licenses/by-sa/3.0/
//

package reptiles.client;

import reptiles.common.*;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxyReptiles extends CommonProxyReptiles
{
    @Override
    public void registerRenderers() {
    	float shadowSize = 0.8F;
		RenderingRegistry.registerEntityRenderingHandler(EntityKomodo.class, new RenderKomodo(new ModelKomodo(), shadowSize));   
		RenderingRegistry.registerEntityRenderingHandler(EntitySavanna.class, new RenderSavanna(new ModelSavanna(), shadowSize - 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityGriseus.class, new RenderGriseus(new ModelGriseus(), shadowSize - 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPerentie.class, new RenderPerentie(new ModelPerentie(), shadowSize - 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityLace.class, new RenderLace(new ModelLace(), shadowSize - 0.3F));
//		RenderingRegistry.registerEntityRenderingHandler(EntityMegalania.class, new RenderMegalania(new ModelMegalania(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityCroc.class, new RenderCroc(new ModelCroc(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityLargeCroc.class, new RenderLargeCroc( new ModelLargeCroc(), shadowSize + 0.2F));
		RenderingRegistry.registerEntityRenderingHandler(EntityDesertTortoise.class, new RenderTurtle(new ModelTurtle(), shadowSize - 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityLittleTurtle.class, new RenderLittleTurtle( new ModelLittleTurtle(), shadowSize - 0.6F));
		RenderingRegistry.registerEntityRenderingHandler(EntityIguana.class, new RenderIguana(new ModelIguana(), shadowSize - 0.6F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTortoise.class, new RenderTortoise(new ModelTortoise(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityGator.class, new RenderGator(new ModelGator(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityChameleon.class, new RenderChameleon( new ModelChameleon(), shadowSize - 0.7F));
	}
    
    @Override
    public void registerSounds() {
    	MinecraftForge.EVENT_BUS.register(new ClientSoundEvents());
    }
}
