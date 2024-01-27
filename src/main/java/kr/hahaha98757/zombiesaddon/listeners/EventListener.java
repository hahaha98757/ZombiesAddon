package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.ClientCrash;
import kr.hahaha98757.zombiesaddon.Keybinds;
import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import kr.hahaha98757.zombiesaddon.commands.CommandInfo;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.listeners.powupalarm.PowerupAlarmListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class EventListener {
	public static volatile boolean cornering = ZombiesAddonConfig.getModDefaultValue()[0];
	public static volatile boolean blockAlarm = ZombiesAddonConfig.getModDefaultValue()[1];
	public static volatile boolean autoRejoin = ZombiesAddonConfig.getModDefaultValue()[2];
	private boolean join = false;

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		if (Keybinds.toggleCornering.isPressed()) {
			cornering = !cornering;
			Minecraft.getMinecraft().thePlayer.addChatMessage(
					new ChatComponentText("\u00A7eToggled Cornering to " + (cornering ? "\u00A7aon" : "\u00A7coff")));
		}
		if (Keybinds.toggleBlockAlarm.isPressed()) {
			blockAlarm = !blockAlarm;
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
					"\u00A7eToggled Block Alarm to " + (blockAlarm ? "\u00A7aon" : "\u00A7coff")));
		}
		if (Keybinds.toggleAutoRejoin.isPressed()) {
			autoRejoin = !autoRejoin;
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
					"\u00A7eToggled Auto Rejoin to " + (autoRejoin ? "\u00A7aon" : "\u00A7coff")));
		}
	}

	@SubscribeEvent
	public void showMods(RenderGameOverlayEvent.Post event) {
		if (ClientCrash.update) {
			String str = "Update Zombies Addon";
			FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
			fr.drawStringWithShadow(str, (float) ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
					- fr.getStringWidth(str)), 0.0F, 16733525);

			return;
		}
		try {
			if (event.type != ElementType.EXPERIENCE || !ZombiesAddonConfig.showMods) {
				return;
			}
			String str = "Zombies Addon v" + ZombiesAddon.VERSION;
			FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
			fr.drawStringWithShadow(str, (float) ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
					- fr.getStringWidth(str)), 0.0F, 16777045);
			str = "Cornering " + (cornering ? "\u00A7aon" : "\u00A7coff");
			fr = Minecraft.getMinecraft().fontRendererObj;
			fr.drawStringWithShadow(str, (float) ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
					- fr.getStringWidth(str)), 8.0F, 16777045);
			str = "Block Alarm " + (blockAlarm ? "\u00A7aon" : "\u00A7coff");
			fr.drawStringWithShadow(str, (float) ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
					- fr.getStringWidth(str)), 16.0F, 16777045);
			if (!ZombiesAddonConfig.hideAutoRejoin) {
				str = "Auto Rejoin " + (autoRejoin ? "\u00A7aon" : "\u00A7coff");
				fr.drawStringWithShadow(str, (float) ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
						- fr.getStringWidth(str)), 24.0F, 16777045);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SubscribeEvent
	public void overlayKorean(ClientChatReceivedEvent event) {
		if (!ZombiesAddonConfig.overlayKorean) {
			return;
		}

		String message = event.message.getUnformattedText();

		if (message.startsWith("\uC628\uB77C\uC778: ")) {// who
			String playerList = message.split(":")[1].trim();

			Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("ONLINE: " + playerList));

		}

		if (message.contains("\uB2D8\uC774 \uCC38\uC5EC\uD588\uC2B5\uB2C8\uB2E4!") && !message.contains(">")) {// join
			String playerName = message.split(" ")[0];
			String number = message.split(" ")[3].split("/")[0].replaceAll("[^0-9]", "");

			Minecraft.getMinecraft().thePlayer
					.addChatComponentMessage(new ChatComponentText(playerName + " has joined (" + number + "/4)!"));
		}

		if (message.contains("\uB2D8\uC774 \uB098\uAC14\uC2B5\uB2C8\uB2E4!") && !message.contains(">")) {// quit
			String playerName = message.split(" ")[0];

			Minecraft.getMinecraft().thePlayer
					.addChatComponentMessage(new ChatComponentText(playerName + " has quit!"));
		}

		if (message.contains("\uC11C\uBC84\uB85C \uC774\uB3D9\uD569\uB2C8\uB2E4!") && !message.contains(">")) {// join
																												// the
																												// zombies
			String text = message.split(" ")[0];

			Minecraft.getMinecraft().thePlayer
					.addChatComponentMessage(new ChatComponentText("Sending you to " + text + "!"));

		}
	}

	public static void gameOver() {
		PowerupAlarmListener.spawnedEntities.clear();

		PowerupAlarmListener.insta = false;
		PowerupAlarmListener.max = false;
		PowerupAlarmListener.dg = false;
		PowerupAlarmListener.carpenter = false;
		PowerupAlarmListener.ss = false;
		PowerupAlarmListener.bg = false;

		PowerupAlarmListener.instaP = 0;
		PowerupAlarmListener.maxP = 0;
		PowerupAlarmListener.ssP = 0;

		WaveDelaysListener.hard = false;
		WaveDelaysListener.rip = false;
	}

	@SubscribeEvent
	public void noSST(EntityJoinWorldEvent event) {
		if (event.entity != Minecraft.getMinecraft().thePlayer) {
			return;
		}
		if (!ZombiesAddon.isSST) {
			return;
		}

		if (join) {
			return;
		}

		Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(CommandInfo.LINE
				+ "\nYou are using Show Spawn Time.\nWave delay of SST will be off.\n" + CommandInfo.LINE));
		join = true;

	}
}
