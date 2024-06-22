package kr.hahaha98757.zombiesaddon.listeners.sla.advanced;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

import java.util.Arrays;
import java.util.Optional;

import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import kr.hahaha98757.zombiesaddon.data.advanced.Room;
import kr.hahaha98757.zombiesaddon.data.advanced.Window;
import kr.hahaha98757.zombiesaddon.listeners.sla.DisplaySLAOverlayListener;

public class AdvancedSLAListener {
	public static AdvancedSLAListener instance = null;
	private final Room[] rooms;

	public AdvancedSLAListener(int map) {
		switch (map) {
		case 1:
			this.rooms = Room.getDE();
			break;
		case 2:
			this.rooms = Room.getBB();
			break;
		case 3:
			this.rooms = Room.getAA();
			break;
		default:
			throw new IllegalStateException("Unexpected value: " + map);
		}
	}

	public void refreshActives() {
		final EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
		final double[] playerCoords = { (player.posX), player.posY, player.posZ };
		for (Room room : rooms) {
			room.resetActiveWindowCount();
			for (Window window : room.getWindows()) {
				double distanceDoubledThenSquared = 0;
				for (int i = 0; i < 3; i++) {
					distanceDoubledThenSquared += ((playerCoords[i] * 2 - window.getXYZ()[i])
							* (playerCoords[i] * 2 - window.getXYZ()[i]));
				}

				if (distanceDoubledThenSquared < (DisplaySLAOverlayListener.oldZombiesSLA ? 10000 : 6400)) {
					window.setActive(true);
					room.increaseActiveWindowCount();
				} else
					window.setActive(false);
			}
		}
	}

	public static Optional<AdvancedSLAListener> getInstance() {
		return Optional.ofNullable(instance);
	}

	public static void drop() {
		instance = null;
	}

	public Room[] getRooms() {
		return rooms;
	}
}
