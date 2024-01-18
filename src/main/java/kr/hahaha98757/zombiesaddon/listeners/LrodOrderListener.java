package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class LrodOrderListener {

	public static final LrodOrderListener instance = new LrodOrderListener();

	public static int LrodOrderDelays = 100;
	private static int lrod;
	private static int delay;

	private static String lrod1 = "\u00A7c1";
	private static String lrod2 = "\u00A7c2";
	private static String lrod3 = "\u00A7c3";
	private static String lrod4 = "\u00A7c4";

	@SubscribeEvent
	public void onRenderGui(RenderGameOverlayEvent.Post event) {
		if (event.type != ElementType.EXPERIENCE) {
			return;
		}
		if (lrod == 0) {
			return;
		}
		if (lrod == 1) {
			lrod1 = "\u00A7a1";
			lrod2 = "\u00A7c2";
			lrod3 = "\u00A7c3";
			lrod4 = "\u00A7c4";
		} else if (lrod == 2) {
			lrod1 = "\u00A7a1";
			lrod2 = "\u00A7a2";
			lrod3 = "\u00A7c3";
			lrod4 = "\u00A7c4";
		} else if (lrod == 3) {
			lrod1 = "\u00A7a1";
			lrod2 = "\u00A7a2";
			lrod3 = "\u00A7a3";
			lrod4 = "\u00A7c4";
		} else if (lrod == 4) {
			lrod1 = "\u00A7a1";
			lrod2 = "\u00A7a2";
			lrod3 = "\u00A7a3";
			lrod4 = "\u00A7a4";
		} else {
			lrod1 = "\u00A7c1";
			lrod2 = "\u00A7c2";
			lrod3 = "\u00A7c3";
			lrod4 = "\u00A7c4";
		}

		ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());

		int screenWidth = scaledResolution.getScaledWidth();
		int screenHeight = scaledResolution.getScaledHeight();

		String str = lrod1 + " \u00A7r\u00A7e- " + lrod2 + " \u00A7r\u00A7e- " + lrod3 + " \u00A7r\u00A7e- " + lrod4;

		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(str,
				screenWidth / 2 - Minecraft.getMinecraft().fontRendererObj.getStringWidth(str) / 2, screenHeight / 1.2F,
				16777045);
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.START) {
			if (lrod == 0) {
				return;
			}

			if (delay >= LrodOrderDelays) {
				resetLrod();
				return;
			}

			delay++;
		}
	}

	public void useLrod() {
		if (lrod == 4) {
			resetLrod();
			return;
		}
		if (delay < LrodOrderDelays) {
			delay = 0;
			lrod++;
		}
	}

	public void resetLrod() {
		delay = 0;
		lrod = 0;
		lrod1 = "\u00A7c1";
		lrod2 = "\u00A7c2";
		lrod3 = "\u00A7c3";
		lrod4 = "\u00A7c4";
	}
}
