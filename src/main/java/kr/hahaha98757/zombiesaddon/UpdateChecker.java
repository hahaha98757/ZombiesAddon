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
		checkUpdate();
		MinecraftForge.EVENT_BUS.unregister(this);

	}

	private void checkUpdate() {
		String modVer = ZombiesAddon.VERSION;
		String latestVer = getlatestVer();

		if (latestVer != null) {
			if (!modVer.equals(latestVer)) {
				updateText(latestVer);
			}
		}
	}

	private void updateText(String latestVer) {
		ChatComponentText text1 = new ChatComponentText(CommandInfo.LINE + "\n" + "\u00A7eZombiesAddon: Mod updated. ");
		ChatComponentText downloadText = new ChatComponentText("\u00A79\u00A7nClick here to download.");
		ChatComponentText text2 = new ChatComponentText("\n\u00A7r\u00A7eCurrent version: " + ZombiesAddon.VERSION
				+ "\n\u00A7eLatest version: " + latestVer + "\n" + CommandInfo.LINE);

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

	private String getlatestVer() {
		try {
			URL url = new URL("https://raw.githubusercontent.com/hahaha98757/ZombiesAddon/main/build.gradle");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;

			while ((line = reader.readLine()) != null) {
				if (line.contains("version =")) {
					Matcher matcher = Pattern.compile("\"(\\d+\\.\\d+\\.\\d+)\"").matcher(line);
					if (matcher.find()) {
						return matcher.group(1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
