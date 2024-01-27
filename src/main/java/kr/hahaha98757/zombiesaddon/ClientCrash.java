package kr.hahaha98757.zombiesaddon;

import kr.hahaha98757.zombiesaddon.util.UpdateRequired;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class ClientCrash {

	public static boolean update = false;

	private int delay = 0;

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) throws UpdateRequired {
		if (event.phase == TickEvent.Phase.START) {
			delay++;
			if (delay == 200) {
				throw new UpdateRequired(
						"Update Zombies Addon. URL: https://github.com/hahaha98757/ZombiesAddon/releases");
			}
		}
	}

	public static void update() {
		update = true;
	}
}
