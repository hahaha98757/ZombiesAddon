package kr.hahaha98757.zombiesaddon.config;

import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class ZombiesAddonGuiConfig extends GuiConfig {

	public ZombiesAddonGuiConfig(GuiScreen parent) {
		super(parent, new ConfigElement(ZombiesAddonConfig.getConfig().getCategory(Configuration.CATEGORY_GENERAL))
				.getChildElements(), ZombiesAddon.MODID, false, false, "Zombies Addon Configuration");
	}
}