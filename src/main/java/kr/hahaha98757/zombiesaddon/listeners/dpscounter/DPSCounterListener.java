package kr.hahaha98757.zombiesaddon.listeners.dpscounter;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.listeners.EventListener;
import kr.hahaha98757.zombiesaddon.utils.DPSCounter;
import kr.hahaha98757.zombiesaddon.utils.GameDetect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DPSCounterListener extends Gui {
	private final DPSCounter dpsCounter;

	public DPSCounterListener(DPSCounter dpsCounter) {
		this.dpsCounter = dpsCounter;
	}

	@SubscribeEvent
	public void onGameOverlayRender(RenderGameOverlayEvent.Post event) {
		if (!ZombiesAddonConfig.enableMod) {
			return;
		}
		
		if (!GameDetect.isZombiesGame()) {
			return;
		}

		if (ZombiesAddonConfig.toggleDPSCounter && event.type == ElementType.TEXT) {
			this.drawString(FMLClientHandler.instance().getClient().fontRendererObj,
					String.format("DPS: %f", this.dpsCounter.count()), 1,
					(new ScaledResolution(Minecraft.getMinecraft())).getScaledHeight()
							- Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT,
					16777215);
		}

	}

}
