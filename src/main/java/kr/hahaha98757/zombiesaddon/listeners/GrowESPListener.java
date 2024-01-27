package kr.hahaha98757.zombiesaddon.listeners;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.handler.RenderTimeHandler;
import kr.hahaha98757.zombiesaddon.util.GameDetect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GrowESPListener {

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.####");

	@SubscribeEvent
	public void onRender(RenderGameOverlayEvent.Post event) {
		if (!ZombiesAddonConfig.growESP) {
			return;
		}

		if (GameDetect.mapDetect() != 3) {
			return;
		}

		List<Entity> entities = Minecraft.getMinecraft().theWorld.getLoadedEntityList();
		List<Integer> sizes = new ArrayList<>();

		for (Entity entity : entities) {
			if (entity instanceof EntitySlime) {
				Integer size = ((EntitySlime) entity).getSlimeSize();
				sizes.add(size);
			}
		}

		OptionalDouble average = sizes.stream().mapToDouble(a -> a).average();

		double average_size = average.isPresent() ? average.getAsDouble() : 0;
		if (average_size == 0) {
			return;
		}

		String text = String.format("Grow: %s/10", DECIMAL_FORMAT.format(average_size));

		FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
		int stringHeight = fr.FONT_HEIGHT;
		int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
		int y = screenHeight - stringHeight;

		fr.drawStringWithShadow(text, 1, y - 10.0F, 16777215);
	}
}
