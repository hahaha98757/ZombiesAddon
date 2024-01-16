package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.Keybinds;
import kr.hahaha98757.zombiesaddon.ZombiesAddon;
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
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class EventListener {
	public static volatile boolean cornering = ZombiesAddonConfig.getModDefaultValue()[0];
	public static volatile boolean blockAlarm = ZombiesAddonConfig.getModDefaultValue()[1];
	public static volatile boolean dpsCounter = ZombiesAddonConfig.getModDefaultValue()[2];
	public static volatile boolean notlast = ZombiesAddonConfig.getModDefaultValue()[3];
	public static volatile boolean autoSplits = ZombiesAddonConfig.getModDefaultValue()[4];
	public static volatile boolean autoRejoin = ZombiesAddonConfig.getModDefaultValue()[5];
	public static volatile boolean powerupAlarm = ZombiesAddonConfig.getModDefaultValue()[6];

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
		if (Keybinds.toggleDPSCounter.isPressed()) {
			dpsCounter = !dpsCounter;
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
					"\u00A7eToggled DPS Counter to " + (dpsCounter ? "\u00A7aon" : "\u00A7coff")));
		}
		if (Keybinds.toggleNOTLAST.isPressed()) {
			notlast = !notlast;
			Minecraft.getMinecraft().thePlayer.addChatMessage(
					new ChatComponentText("\u00A7eToggled NOTLAST to " + (notlast ? "\u00A7aon" : "\u00A7coff")));
		}
		if (Keybinds.toggleAutoSplits.isPressed()) {
			autoSplits = !autoSplits;
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
					"\u00A7eToggled Auto Splits to " + (autoSplits ? "\u00A7aon" : "\u00A7coff")));
		}
		if (Keybinds.toggleAutoRejoin.isPressed()) {
			autoRejoin = !autoRejoin;
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
					"\u00A7eToggled Auto Rejoin to " + (autoRejoin ? "\u00A7aon" : "\u00A7coff")));
		}
		if (Keybinds.togglePowerupAlarm.isPressed()) {
			powerupAlarm = !powerupAlarm;
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
					"\u00A7eToggled Powerup Alarm to " + (powerupAlarm ? "\u00A7aon" : "\u00A7coff")));
		}
	}

	@SubscribeEvent
	public void showMods(RenderGameOverlayEvent.Post event) {
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
			str = "DPS Counter " + (dpsCounter ? "\u00A7aon" : "\u00A7coff");
			fr.drawStringWithShadow(str, (float) ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
					- fr.getStringWidth(str)), 24.0F, 16777045);
			str = "NOTLAST " + (notlast ? "\u00A7aon" : "\u00A7coff");
			fr.drawStringWithShadow(str, (float) ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
					- fr.getStringWidth(str)), 32.0F, 16777045);
			str = "Auto Splits " + (autoSplits ? "\u00A7aon" : "\u00A7coff");
			fr.drawStringWithShadow(str, (float) ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
					- fr.getStringWidth(str)), 40.0F, 16777045);
			str = "Auto Rejoin " + (autoRejoin ? "\u00A7aon" : "\u00A7coff");
			fr.drawStringWithShadow(str, (float) ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
					- fr.getStringWidth(str)), 48.0F, 16777045);
			str = "Powerup Alarm " + (powerupAlarm ? "\u00A7aon" : "\u00A7coff");
			fr.drawStringWithShadow(str, (float) ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
					- fr.getStringWidth(str)), 56.0F, 16777045);
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

	@SubscribeEvent
	public void hologramRemover(ClientChatReceivedEvent event) {
		String message = event.message.getUnformattedText();
		if (message.contains(">")) {
			return;
		}

		if (!ZombiesAddonConfig.hologramRemover) {
			return;
		}

		if ((message.contains("Power Station") && message.contains("opened"))
				|| (message.contains("\uBC1C\uC804\uC18C") && message.contains("\uC5F4\uC5C8\uC2B5\uB2C8\uB2E4"))) {
			Minecraft.getMinecraft().refreshResources();
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
}
