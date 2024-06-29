package kr.hahaha98757.zombiesaddon.listeners.sla;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import kr.hahaha98757.zombiesaddon.data.Room;
import kr.hahaha98757.zombiesaddon.data.Window;
import kr.hahaha98757.zombiesaddon.utils.GameDetect;

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
		case 4:
			this.rooms = Room.getPR();
			break;
		default:
			throw new IllegalStateException("Unexpected value: " + map);
		}
	}

	public void refreshActives() {
		List<EntityPlayer> players = Minecraft.getMinecraft().theWorld.playerEntities;
		for (Room room : rooms) {
			room.resetActiveWindowCount();
			for (Window window : room.getWindows()) {
				boolean isActive = false;
				boolean isActiveByOthers = false;

				for (EntityPlayer player : players) {
					if (!isZombiesPlayer(player.getName())) {
						continue;
					}
					final double[] playerCoords = { (player.posX), player.posY, player.posZ };
					double distanceDoubledThenSquared = 0;
					for (int i = 0; i < 3; i++) {
						distanceDoubledThenSquared += ((playerCoords[i] * 2 - window.getXYZ()[i])
								* (playerCoords[i] * 2 - window.getXYZ()[i]));
					}
					
					if (distanceDoubledThenSquared < 10000) {
						if (player.equals(Minecraft.getMinecraft().thePlayer)) {
							isActive = true;
						} else {
							isActiveByOthers = true;
						}
					}
				}

				window.setActive(isActive);
				window.setActiveByOthers(isActiveByOthers);

				if (isActive) {
					room.increaseActiveWindowCount();
				}
				if (isActiveByOthers) {
					room.increaseActiveWindowCountByOthers();
				}
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
	
	private boolean isZombiesPlayer(String playerName) {
		for (String name : GameDetect.getPlayerNames()) {
			if (playerName.equals(name)) {
				return true;
			}
		}
		return false;
	}
}
