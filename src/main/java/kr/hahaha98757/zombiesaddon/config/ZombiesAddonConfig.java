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
	public static boolean[] modDefaultValue = { false, false, false };
	public static boolean checkPreRelease = true;
	public static boolean growESP = false;
	public static boolean gstepGuide = false;
	public static boolean DPSCounter = true;
	public static boolean NOTLAST = false;
	public static boolean hideAutoRejoin = false;

	public static boolean autoSplits = true;
	public static String host = "localhost";
	public static int port = -1;
	public static boolean showWaveDelays = true;
	public static int waveColorDelays = 3;
	public static boolean bossWaveAlarm = true;
	public static boolean growGuide = false;
	public static boolean playSound = true;
	public static boolean playCountdown = true;

	public static boolean powerupAlarm = true;
	public static boolean instaRound = true;
	public static boolean maxRound = true;
	public static boolean ssRound = true;

	public static boolean downedPlayerInvisible = false;
	public static boolean translucentPlayer = true;
	public static boolean autoSLA = true;
	public static boolean advancedSLA = false;
	public static boolean shortenedSLA = true;

	public static void loadConfig() {
		config.load();

		boolean configShowMods = config.getBoolean("Zombies Addon: Show Mods", Configuration.CATEGORY_GENERAL, true,
				"Set whether to display the mods on the in-game screen.");
		boolean configOverlayKorean = config.getBoolean("Zombies Addon: Use Zombies Overlay in Korean",
				Configuration.CATEGORY_GENERAL, false, "Set whether Zombies Overlay is available in Korean.");
		boolean configBossAlarm = config.getBoolean("Zombies Addon: Boss Alarm", Configuration.CATEGORY_GENERAL, false,
				"Sets whether the boss can tell which area it spawned in.");
		boolean configLrodOrder = config.getBoolean("Zombies Addon: Lrod Order", Configuration.CATEGORY_GENERAL, false,
				"Shows the order of use of Lrod.");
		int configLrodOrderDelays = config.getInt("Lrod Order: Lrod Order Delays (Unit: ticks, 1 second = 20 ticks)",
				Configuration.CATEGORY_GENERAL, 100, 0, 200, "Set delays of Lrod Order.");
		boolean[] configModDefaultValue = config.get(Configuration.CATEGORY_GENERAL, "Zombies Addon: Mod Default Value",
				new boolean[] { false, false, false },
				"Set the default value of Cornering, Block Alarm, DPS Counter, NOTLAST, Auto Splits, Auto Rejoin, and Powerup Alarm.")
				.getBooleanList();
		boolean configcheckPreRelease = config.getBoolean("Zombies Addon: Check Pre-Release",
				Configuration.CATEGORY_GENERAL, true, "It check the latest pre-releases.");
		boolean configGrowESP = config.getBoolean("Zombies Addon: Grow ESP", Configuration.CATEGORY_GENERAL, false,
				"Shows the arithmetic mean of the size of the slimes for growing.");
		boolean configGstepGuide = config.getBoolean("Zombies Addon: Gstep Guide", Configuration.CATEGORY_GENERAL,
				false, "Show hitbox of Armor Stand in Gstep.");
		boolean configDPSCounter = config.getBoolean("Zombies Addon: DPS Counter", Configuration.CATEGORY_GENERAL, true,
				"Counts your party's DPS.");
		boolean configNOTLAST = config.getBoolean("Zombies Addon: NOTLAST", Configuration.CATEGORY_GENERAL, false,
				"Shows who is kill the last in every round.");

		boolean configAutoSplits = config.getBoolean("Zombies Addon: Auto Splits", Configuration.CATEGORY_GENERAL, true,
				"Run LiveSplits or internal timer at start of round.");
		String configHost = config.getString("Auto Splits: Host", Configuration.CATEGORY_GENERAL, "localhost",
				"The local IP to connect to LiveSplits");
		int configPort = config.getInt("Auto Splits: Port", Configuration.CATEGORY_GENERAL, -1, -1, 65535,
				"The port to connect to LiveSplits");
		boolean configShowWaveDelays = config.getBoolean("Auto Splits: Show Wave Delays",
				Configuration.CATEGORY_GENERAL, true, "Set whether to show wave delays.");
		int configWaveColorDelays = config.getInt("Auto Splits: Wave Color Delays", Configuration.CATEGORY_GENERAL, 3,
				0, 10, "Sets the point at which the color of the wave delays text changes to yellow.");
		boolean configBossWaveAlarm = config.getBoolean("Auto Splits: Boss Wave Alarm", Configuration.CATEGORY_GENERAL,
				true, "Set whether you can see when the boss spawns in wave delays.");
		boolean configGrowGuide = config.getBoolean("Auto Splits: Grow Guide", Configuration.CATEGORY_GENERAL, false,
				"Displays the blob's grow time.");
		boolean configPlaySound = config.getBoolean("Auto Splits: Play Sound", Configuration.CATEGORY_GENERAL, true, "Play sound when wave start.");
		boolean configPlayCountdown = config.getBoolean("Auto Splits: Play Countdown", Configuration.CATEGORY_GENERAL, true, "Play countdown when last wave start.");

		boolean configPowerupAlarm = config.getBoolean("Zombies Addon: Powerup Alarm", Configuration.CATEGORY_GENERAL,
				true,
				"You will receive an alarm when a powerup is spawned. You will know when powerup spawn and despawn.");
		boolean configInstaRound = config.getBoolean("Powerup Alarm: Insta pattern", Configuration.CATEGORY_GENERAL,
				true, "Set whether to use insta pattern.");
		boolean configMaxRound = config.getBoolean("Powerup Alarm: Max pattern", Configuration.CATEGORY_GENERAL, true,
				"Set whether to use max pattern.");
		boolean configSSRound = config.getBoolean("Powerup Alarm: SS pattern", Configuration.CATEGORY_GENERAL, true,
				"Set whether to use ss pattern.");

		float configRange = config.getFloat("Cornering: Range", Configuration.CATEGORY_GENERAL, 2.0F, 0.0F, 100.0F,
				"Set range of cornering.");
		boolean configDownedPlayerInvisible = config.getBoolean("Cornering: Downed Player Invisible",
				Configuration.CATEGORY_GENERAL, false, "Makes downed players invisible.");
		boolean configAutoSLA = config.getBoolean("SLA: Auto SLA", Configuration.CATEGORY_GENERAL, true,
				"Automatically turn on SLA.");
		boolean configAdvancedSLA = config.getBoolean("SLA: Advanced SLA", Configuration.CATEGORY_GENERAL, false,
				"SLA is displayed in more detail.");
		boolean configShortenedSLA = config.getBoolean("SLA: Shortened SLA", Configuration.CATEGORY_GENERAL, true,
				"SLA is displayed shorter. (Advanced SLA only)");
		boolean configHideAutoRejoin = config.getBoolean("Auto Rejoin: Hide Auto Rejoin",
				Configuration.CATEGORY_GENERAL, false, "Set whether to display Auto Rejoin on the in-game screen.");

		showMods = configShowMods;
		overlayKorean = configOverlayKorean;
		bossAlarm = configBossAlarm;
		lrodOrder = configLrodOrder;
		LrodOrderListener.LrodOrderDelays = configLrodOrderDelays;
		modDefaultValue = configModDefaultValue;
		checkPreRelease = configcheckPreRelease;
		growESP = configGrowESP;
		gstepGuide = configGstepGuide;
		DPSCounter = configDPSCounter;
		NOTLAST = configNOTLAST;

		autoSplits = configAutoSplits;
		host = configHost;
		port = configPort;
		showWaveDelays = configShowWaveDelays;
		waveColorDelays = configWaveColorDelays;
		bossWaveAlarm = configBossWaveAlarm;
		growGuide = configGrowGuide;
		playSound = configPlaySound;
		playCountdown = configPlayCountdown;

		powerupAlarm = configPowerupAlarm;
		instaRound = configInstaRound;
		maxRound = configMaxRound;
		ssRound = configSSRound;

		CommandCornering.range = configRange;
		downedPlayerInvisible = configDownedPlayerInvisible;
		autoSLA = configAutoSLA;
		advancedSLA = configAdvancedSLA;
		shortenedSLA = configShortenedSLA;
		hideAutoRejoin = configHideAutoRejoin;

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
