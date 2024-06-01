package kr.hahaha98757.zombiesaddon.listeners.sla;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.data.Room;
import kr.hahaha98757.zombiesaddon.data.Window;
import kr.hahaha98757.zombiesaddon.listeners.EventListener;
import kr.hahaha98757.zombiesaddon.utils.DistanceUtils;
import kr.hahaha98757.zombiesaddon.utils.Point;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DisplaySLAOverlayListener extends Gui {
	private Room[] rooms;

	public DisplaySLAOverlayListener() {
	}

	@SubscribeEvent
	public void renderGameOverlay(RenderGameOverlayEvent.Post event) {
		if (!ZombiesAddonConfig.enableMod) {
			return;
		}
		if (EventListener.disableMod) {
			return;
		}

		if (event.type == ElementType.TEXT && this.rooms != null) {
			Room[] var2 = this.rooms;
			int var3 = var2.length;

			int var4;
			int var8;
			int var13;
			for (var4 = 0; var4 < var3; ++var4) {
				Room room = var2[var4];
				Window[] var6 = room.getWindows();
				var13 = var6.length;

				for (var8 = 0; var8 < var13; ++var8) {
					Window window = var6[var8];
					if (!window.isActive()
							&& DistanceUtils.getDistanceSquared(new Point(Minecraft.getMinecraft().thePlayer.posX,
									Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ),
									window.getPoint()) <= 2500.0) {
						window.setActive(true);
						room.increment();
					}
				}
			}

			int counter = 0;
			Room[] var12 = this.rooms;
			var4 = var12.length;

			for (var13 = 0; var13 < var4; ++var13) {
				Room room = var12[var13];
				if (room.getCount() != 0) {
					this.drawString(FMLClientHandler.instance().getClient().fontRendererObj,
							String.format("%s: %x", room.getName(), room.getCount()), 1, 10 * counter + 1, 16777215);
					++counter;
				}

				Window[] var15 = room.getWindows();
				var8 = var15.length;

				for (int var16 = 0; var16 < var8; ++var16) {
					Window window = var15[var16];
					window.setActive(false);
				}

				room.zero();
			}
		}

	}

	public void setRooms(Room[] rooms) {
		this.rooms = rooms;
	}
}
