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

package reptiles.client;



import reptiles.common.Reptiles;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class ClientSoundEvents {

	@ForgeSubscribe
	public void onSoundsLoaded(SoundLoadEvent event) {
		String[] soundFiles = {
			"hiss1", 
			"hurt1", 
			"growl1",
			"growl2", 
			"growl3", 
			"growl4",
            "megagrowl",
            "purr1",
            "purr2",
            "death1",
		};

		for (String sound : soundFiles) {
			String soundStr = "reptilemod:" + sound + ".ogg";
			event.manager.soundPoolSounds.addSound(soundStr);
			Reptiles.proxy.print("Loaded sound file: " + soundStr);
		}
	}
	
//	private URL getSound(String sound) {
//        return this.getClass().getResource("reptilemod:/sound/mob/" + sound + ".ogg");
//    }
	
}
