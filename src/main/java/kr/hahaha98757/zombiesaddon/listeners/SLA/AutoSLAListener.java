package kr.hahaha98757.zombiesaddon.listeners.SLA;

import kr.hahaha98757.zombiesaddon.commands.CommandSLA;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.util.GameDetect;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class AutoSLAListener {
	private static boolean SLA = true;
	private static boolean autoSLA;

	@SubscribeEvent
	public void onPlayerJoin(EntityJoinWorldEvent event) {
		if (event.entity == Minecraft.getMinecraft().thePlayer) {
			SLA = true;
		}
	}

	public static void autoSLA() {
		if (!ZombiesAddonConfig.autoSLA) {
			return;
		}

		if (!SLA) {
			return;
		}

		autoSLA = true;
	}

	@SubscribeEvent
	public void autoSLAMap(ClientTickEvent event) {
		if (event.phase != TickEvent.Phase.START) {
			return;
		}

		if (!autoSLA) {
			return;
		}

		int map = GameDetect.mapDetect();
		if (!(map == 1 || map == 2 || map == 3)) {
			map = GameDetect.mapDetect();
		} else {
			CommandSLA.instance.autoSLA(map);
			SLA = false;
			autoSLA = false;
		}
	}

}