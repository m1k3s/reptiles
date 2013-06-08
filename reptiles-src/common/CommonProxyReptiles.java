//
// This work is licensed under the Creative Commons
// Attribution-ShareAlike 3.0 Unported License. To view a copy of this
// license, visit http://creativecommons.org/licenses/by-sa/3.0/
//

package reptiles.common;

import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class CommonProxyReptiles {
	private Logger logger;
	
	public void registerRenderers() {}
	
	public void registerSounds() {}
	
	public void registerLogger() {
		logger = Logger.getLogger(Reptiles.modid);
		logger.setParent(FMLLog.getLogger());
	}
	
	public void print(String s) {
		logger.info(s);
	}
}
