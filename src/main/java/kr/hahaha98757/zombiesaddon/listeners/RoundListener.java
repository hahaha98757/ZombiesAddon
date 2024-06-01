package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.events.TitleEvent;
import kr.hahaha98757.zombiesaddon.listeners.sla.AutoSLAListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class RoundListener {

	@SubscribeEvent
	public void onTitle(TitleEvent event) {
		if (!ZombiesAddonConfig.enableMod) {
			return;
		}
		if (EventListener.disableMod) {
			return;
		}
		
		String raw = EnumChatFormatting.getTextWithoutFormattingCodes(event.getTitle());

		if (ZombiesAddonConfig.toggleNotLast) {
			if ((raw.startsWith("Round ") && !raw.equals("Round 1"))
					|| (raw.matches(".*\\d+\uB77C\uC6B4\uB4DC") && !raw.equals("1\uB77C\uC6B4\uB4DC"))) {
				NotLastListener.resetTick();
				MinecraftForge.EVENT_BUS.register(new NotLastListener());
			}
		}

		if (EventListener.autoRejoin) {
			if (raw.startsWith("Round ") || raw.matches(".*\\d+\uB77C\uC6B4\uB4DC")) {
				Minecraft.getMinecraft().thePlayer
						.addChatMessage(new ChatComponentText("\u00A7eAuto Rejoin: Rejoining..."));
				Minecraft.getMinecraft().thePlayer.sendChatMessage("/l");
				TickListener tickListener = new TickListener(() -> {
					Minecraft.getMinecraft().thePlayer.sendChatMessage("/rejoin");
				});
				MinecraftForge.EVENT_BUS.register(tickListener);
			}
		}
		
		if (raw.equals("Round1") || raw.equals("1\uB77C\uC6B4\uB4DC")) {
			EventListener.gameOver();
			
			AutoSLAListener.autoSLA();
		}


	}
}
