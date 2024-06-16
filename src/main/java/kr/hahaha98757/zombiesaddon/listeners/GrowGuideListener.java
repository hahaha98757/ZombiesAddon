package kr.hahaha98757.zombiesaddon.listeners;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.handler.RenderTimerHandler;
import kr.hahaha98757.zombiesaddon.utils.GameDetect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GrowGuideListener {
	private static int growTime;
	private static final String EXTRA_TIME = "[or earlier]";
	private static boolean useExtraTime;

	@SubscribeEvent
	public void onRender(RenderGameOverlayEvent.Post event) {
		if (!ZombiesAddonConfig.enableMod) {
			return;
		}

		if (event.type != ElementType.EXPERIENCE) {
			return;
		}
		if (!ZombiesAddonConfig.toggleGrowGuide) {
			return;
		}
		if (GameDetect.getMap() != 3) {
			return;
		}

		int round = GameDetect.getRound();
		if (round == 18 || round == 23) {
			growTime = 38;
			useExtraTime = false;
		} else if (round == 26) {
			growTime = 40;
			useExtraTime = false;
		} else if (round == 29) {
			growTime = 36;
			useExtraTime = true;
		} else if (round == 31 || round == 33 || round == 34) {
			growTime = 42;
			useExtraTime = false;
		} else if (round == 39) {
			growTime = 22;
			useExtraTime = false;
		} else if (round == 43) {
			growTime = 13;
			useExtraTime = false;
		} else if (round == 47) {
			growTime = 41;
			useExtraTime = true;
		} else if (round == 52) {
			growTime = 27;
			useExtraTime = false;
		} else {
			return;
		}
		FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
		int stringHeight = fr.FONT_HEIGHT;
		int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
		int y = screenHeight - stringHeight;
		int timerWidth = fr.getStringWidth(RenderTimerHandler.timer);

		String str = "T: " + String.valueOf(growTime) + "s" + (useExtraTime ? EXTRA_TIME : "");
		fr.drawStringWithShadow(str, (float) ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
				- fr.getStringWidth(str) - timerWidth) - 10.0F, y, getColor());
	}

	public static int getColor() {
		try {
			String s = RenderTimerHandler.timer;

			s = s.replaceAll("\\.", ":");

			String m = s.split(":")[0].trim();
			int m2 = Integer.parseInt(m);
			s = s.split(":")[1].trim();
			int timer = Integer.parseInt(s);
			if (m2 != 0) {
				return 5635925;
			}

			if (timer >= growTime) {
				return 5635925;
			} else {
				return 16733525;
			}
		} catch (Exception e) {
			return 16733525;
		}
	}
}
