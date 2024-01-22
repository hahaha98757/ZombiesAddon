package kr.hahaha98757.zombiesaddon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kr.hahaha98757.zombiesaddon.commands.CommandInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class UpdateChecker {

	@SubscribeEvent
	public void playerJoinWorld(EntityJoinWorldEvent event) {
		if (event.entity != Minecraft.getMinecraft().thePlayer) {
			return;
		}
		if (ZombiesAddon.VERSION.contains("Beta")) {
			MinecraftForge.EVENT_BUS.unregister(this);
			return;
		}

		checkUpdate();
		MinecraftForge.EVENT_BUS.unregister(this);

	}

	private void checkUpdate() {
		String modVer = ZombiesAddon.VERSION;
		String latestVer = getlatestVer();

		if (latestVer != null) {
			displayText(compareVersions(modVer, latestVer),
					latestVer.startsWith("!") ? latestVer.replaceAll("!", "") : latestVer);
		}
	}

	// 0: Latest Version, 1: Latest Pre-Version, 2: New Version, 3: New Pre-Version,
	// 4: New required release
	private int compareVersions(String modVer, String latestVer) {
		if (latestVer.startsWith("!")) {
			latestVer.replaceAll("!", "");
			if (!modVer.equals(latestVer)) {
				return 4;
			}
		}

		boolean update = false;
		boolean modPre = false;
		boolean latestPre = false;
		String modPreVer = "9999";
		String latestPreVer = "9999";

		if (modVer.contains("pre")) {
			modPre = true;
			modPreVer = modVer.split("pre")[1];
			modVer = modVer.split("-")[0];
		}

		if (latestVer.contains("pre")) {
			latestPre = true;
			latestPreVer = latestVer.split("pre")[1];
			latestVer = latestVer.split("-")[0];
		}

		String[] modVerA = (modVer + "." + modPreVer).split("\\.");
		String[] latestVerA = (latestVer + "." + latestPreVer).split("\\.");

		for (int i = 0; i <= 3; i++) {
			int j = Integer.valueOf(modVerA[i]);
			int k = Integer.valueOf(latestVerA[i]);

			if (k > j) {
				update = true;
				break;
			}
		}
		if (!update) {
			return modPre ? 1 : 0;
		}

		if (latestPre) {
			return 3;
		}
		return 2;
	}

	private void displayText(int i, String latestVer) {
		if (i == 0) {
			return;
		}
		if (i == 1) {
			Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(CommandInfo.LINE
					+ "\nYou are using pre-release.\n\u00A7cPre-release is not perfect. There may be bugs.\n"
					+ CommandInfo.LINE));
		}
		if (i == 2) {
			ChatComponentText text1 = new ChatComponentText(CommandInfo.LINE + "\nA new release is available. ");
			ChatComponentText downloadText = new ChatComponentText("\u00A79\u00A7nClick here to download.");
			ChatComponentText text2 = new ChatComponentText("\n\u00A7rCurrent version: " + ZombiesAddon.VERSION
					+ "\nLatest version: " + latestVer + "\n" + CommandInfo.LINE);

			downloadText.getChatStyle().setChatClickEvent(
					new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/hahaha98757/ZombiesAddon/releases"));
			downloadText.getChatStyle().setChatHoverEvent(
					new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Open download URL.")));

			ChatComponentText text = new ChatComponentText("");
			text.appendSibling(text1);
			text.appendSibling(downloadText);
			text.appendSibling(text2);

			Minecraft.getMinecraft().thePlayer.addChatComponentMessage(text);
		}
		if (i == 3) {
			ChatComponentText text1 = new ChatComponentText(CommandInfo.LINE + "\nA new pre-release is available. ");
			ChatComponentText downloadText = new ChatComponentText("\u00A79\u00A7nClick here to download.");
			ChatComponentText text2 = new ChatComponentText("\n\u00A7rCurrent version: " + ZombiesAddon.VERSION
					+ "\nLatest version: " + latestVer
					+ "\n\u00A7cPre-release is not perfect. There may be bugs.\n\u00A7rNote: You can set hide this message in config.\n"
					+ CommandInfo.LINE);

			downloadText.getChatStyle().setChatClickEvent(
					new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/hahaha98757/ZombiesAddon/releases"));
			downloadText.getChatStyle().setChatHoverEvent(
					new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Open download URL.")));

			ChatComponentText text = new ChatComponentText("");
			text.appendSibling(text1);
			text.appendSibling(downloadText);
			text.appendSibling(text2);

			Minecraft.getMinecraft().thePlayer.addChatComponentMessage(text);
		}
		if (i == 4) {
			ChatComponentText text1 = new ChatComponentText(CommandInfo.LINE + "\n\u00A7cRequired updates released. ");
			ChatComponentText downloadText = new ChatComponentText("\u00A79\u00A7nClick here to download.");
			ChatComponentText text2 = new ChatComponentText(
					"\n\u00A7r\u00A7cUPDATE NOW.\n\u00A7c\u00A7lThe game ends after 10 seconds.\n" + CommandInfo.LINE);

			downloadText.getChatStyle().setChatClickEvent(
					new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/hahaha98757/ZombiesAddon/releases"));
			downloadText.getChatStyle().setChatHoverEvent(
					new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Open download URL.")));

			ChatComponentText text = new ChatComponentText("");
			text.appendSibling(text1);
			text.appendSibling(downloadText);
			text.appendSibling(text2);

			Minecraft.getMinecraft().thePlayer.addChatComponentMessage(text);
			ClientCrash.update();
			MinecraftForge.EVENT_BUS.register(new ClientCrash());
		}
	}

	private String getlatestVer() {
		try {
			URL url = new URL("https://raw.githubusercontent.com/hahaha98757/ZombiesAddon/main/build.gradle");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;

			while ((line = reader.readLine()) != null) {
				if (line.contains("version =")) {
					Matcher matcher = Pattern.compile("\"(\\d+\\.\\d+\\.\\d+(?:-pre\\d+)?)\"").matcher(line);
					if (matcher.find()) {
						String version = matcher.group(1);
						if (line.contains("//required")) {
							return "!" + version;
						} else {
							return version;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
