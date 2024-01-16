package kr.hahaha98757.zombiesaddon.config;

import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import kr.hahaha98757.zombiesaddon.commands.CommandCornering;
import kr.hahaha98757.zombiesaddon.commands.CommandSLA;
import kr.hahaha98757.zombiesaddon.listeners.LrodOrderListener;
import kr.hahaha98757.zombiesaddon.listeners.SLA.advanced.AdvancedSLAListener;
import kr.hahaha98757.zombiesaddon.splitter.LiveSplitSplitter;
import kr.hahaha98757.zombiesaddon.splitter.internal.InternalSplitter;
import net.minecraftforge.common.config.Configuration;

public class ZombiesAddonConfig {
	public static Configuration config;

	public static boolean timer = false;

	public static boolean showMods = true;
	public static boolean overlayKorean = false;
	public static boolean bossAlarm = false;
	public static boolean lrodOrder = false;
	public static boolean hologramRemover = false;
	public static boolean[] modDefaultValue = { false, false, false, false, false, false, false };
	public static boolean downedPlayerInvisible = false;
	public static boolean healthIndicator = false;
	public static String host = "localhost";
	public static int port = 16834;
	public static boolean showWaveDelays = true;
	public static int waveColorDelays = 5;
	public static boolean bossWaveAlarm = true;
	public static boolean growGuide = false;
	public static boolean autoSLA = false;
	public static boolean advancedSLA = false;
	public static boolean shortenedSLA = true;
	public static boolean instaRound = true;
	public static boolean maxRound = true;
	public static boolean ssRound = true;

	public static void loadConfig() {
		config.load();

		boolean configShowMods = config.getBoolean("Zombies Addon: Show Mods", Configuration.CATEGORY_GENERAL, true,
				"Set whether to display the mods on the in-game screen.");
		boolean configOverlayKorean = config.getBoolean("Zombies Addon: Use Zombies Overlay in Korean",
				Configuration.CATEGORY_GENERAL, false, "Set whether Zombies Overlay is available in Korean.");
		boolean configBossAlarm = config.getBoolean("ZombiesAddon: Boss Alarm", Configuration.CATEGORY_GENERAL, false,
				"Sets whether the boss can tell which area it spawned in.");
		boolean configLrodOrder = config.getBoolean("Zombies Addon: Lrod Order", Configuration.CATEGORY_GENERAL, false,
				"Shows the order of use of Lrod.");
		int configLrodOrderDelays = config.getInt("Zombies Addon: Lrod Order Delays (Unit: ticks, 1 second = 20 ticks)",
				Configuration.CATEGORY_GENERAL, 100, 0, 200, "Set delays of Lrod Order.");
		boolean configHologramRemover = config.getBoolean("Zombies Addon: Hologram Remover",
				Configuration.CATEGORY_GENERAL, false, "Remove hologram in Gstep (may fail)");
		boolean[] configModDefaultValue = config.get(Configuration.CATEGORY_GENERAL, "Zombies Addon: Mod Default Value",
				new boolean[] { false, false, false, false, false, false, false },
				"Set the default value of Cornering, Block Alarm, DPS Counter, NOTLAST, Auto Splits, Auto Rejoin, and Powerup Alarm.")
				.getBooleanList();
		float configRange = config.getFloat("Cornering: Range", Configuration.CATEGORY_GENERAL, 2.0F, 0.0F, 100.0F,
				"Set range of cornering.");
		boolean configDownedPlayerInvisible = config.getBoolean("Cornering: Downed Player Invisible",
				Configuration.CATEGORY_GENERAL, false, "Makes downed players invisible.");
		boolean configHealthIndicator = config.getBoolean("Block Alarm: Health Indicator",
				Configuration.CATEGORY_GENERAL, false, "Display player's health.");
		String configHost = config.getString("Auto Splits: Host", Configuration.CATEGORY_GENERAL, "localhost",
				"The local IP to connect to LiveSplits");
		int configPort = config.getInt("Auto Splits: Port", Configuration.CATEGORY_GENERAL, 16834, -1, 65535,
				"The port to connect to LiveSplits");
		boolean configShowWaveDelays = config.getBoolean("Auto Splits: Show Wave Delays",
				Configuration.CATEGORY_GENERAL, true, "Set whether to show wave delays.");
		int configWaveColorDelays = config.getInt("Auto Splits: Wave Color Delays", Configuration.CATEGORY_GENERAL, 5,
				0, 10, "Sets the point at which the color of the wave delays text changes to yellow.");
		boolean configBossWaveAlarm = config.getBoolean("Auto Splits: Boss Wave Alarm", Configuration.CATEGORY_GENERAL,
				true, "Set whether you can see when the boss spawns in wave delays.");
		boolean configGrowGuide = config.getBoolean("Auto Splits: Grow Guide", Configuration.CATEGORY_GENERAL, false,
				"Displays the blob's grow time.");
		boolean configAutoSLA = config.getBoolean("SLA: Auto SLA", Configuration.CATEGORY_GENERAL, false,
				"Automatically turn on SLA.");
		boolean configAdvancedSLA = config.getBoolean("SLA: Advanced SLA", Configuration.CATEGORY_GENERAL, false,
				"SLA is displayed in more detail.");
		boolean configShortenedSLA = config.getBoolean("SLA: Shortened SLA", Configuration.CATEGORY_GENERAL, true,
				"SLA is displayed shorter. (Advanced SLA only)");
		boolean configInstaRound = config.getBoolean("Powerup Alarm: Insta pattern", Configuration.CATEGORY_GENERAL,
				true, "Set whether to use insta pattern.");
		boolean configMaxRound = config.getBoolean("Powerup Alarm: Max pattern", Configuration.CATEGORY_GENERAL, true,
				"Set whether to use max pattern.");
		boolean configSSRound = config.getBoolean("Powerup Alarm: SS pattern", Configuration.CATEGORY_GENERAL, true,
				"Set whether to use ss pattern.");

		showMods = configShowMods;
		overlayKorean = configOverlayKorean;
		bossAlarm = configBossAlarm;
		lrodOrder = configLrodOrder;
		LrodOrderListener.LrodOrderDelays = configLrodOrderDelays;
		hologramRemover = configHologramRemover;
		modDefaultValue = configModDefaultValue;
		CommandCornering.range = configRange;
		downedPlayerInvisible = configDownedPlayerInvisible;
		healthIndicator = configHealthIndicator;
		host = configHost;
		port = configPort;
		showWaveDelays = configShowWaveDelays;
		waveColorDelays = configWaveColorDelays;
		bossWaveAlarm = configBossWaveAlarm;
		growGuide = configGrowGuide;
		autoSLA = configAutoSLA;
		advancedSLA = configAdvancedSLA;
		shortenedSLA = configShortenedSLA;
		instaRound = configInstaRound;
		maxRound = configMaxRound;
		ssRound = configSSRound;
		if (!ZombiesAddon.gameStart) {
			return;
		}
		if (advancedSLA) {
			CommandSLA.instance.autoSLA(0);
		} else {
			AdvancedSLAListener.drop();
		}
	}

	public static Configuration getConfig() {
		return config;
	}

	public static void reloadConfig() {
		config.save();

		loadConfig();

		ZombiesAddon.instance.reloadConfig2();
	}

	public static boolean[] getModDefaultValue() {
		return modDefaultValue;
	}
}
