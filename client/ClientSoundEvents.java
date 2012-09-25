package reptiles.client;

import net.minecraft.client.Minecraft;
import net.minecraft.src.SoundManager;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class ClientSoundEvents {

	@ForgeSubscribe
	public void onSoundsLoaded(SoundLoadEvent event) {
		SoundManager manager = event.manager;
		String[] soundFiles = {
			"/sound/monitor/hiss1.ogg", 
			"/sound/monitor/hurt1.ogg", 
			"/sound/croc/growl1.ogg",
			"/sound/croc/growl2.ogg", 
			"/sound/croc/growl3.ogg", 
			"/sound/croc/growl4.ogg", 
		};

		for (int i = 0; i < soundFiles.length; i++) {
			// remove '/sound/' portion for the name
			String soundName = soundFiles[i].substring(7);
			manager.soundPoolSounds.addSound(soundName, this.getClass().getResource(soundFiles[i]));
		}
	}

}
