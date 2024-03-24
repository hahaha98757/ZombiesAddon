package kr.hahaha98757.zombiesaddon.config;

import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import kr.hahaha98757.zombiesaddon.commands.CommandCornering;
import kr.hahaha98757.zombiesaddon.commands.CommandSLA;
import kr.hahaha98757.zombiesaddon.listeners.WaveDelaysListener;
import kr.hahaha98757.zombiesaddon.listeners.SLA.advanced.AdvancedSLAListener;
import kr.hahaha98757.zombiesaddon.splitter.LiveSplitSplitter;
import kr.hahaha98757.zombiesaddon.splitter.internal.InternalSplitter;
import net.minecraftforge.common.config.Configuration;

public class ZombiesAddonConfig {
	public static Configuration config;

	public static boolean timer = false;

	public static boolean enableMod = true;
	public static boolean showMods = true;
	public static boolean overlayKorean = false;
	public static boolean[] modDefaultValue = { false, false, false };
	public static boolean checkPreRelease = true;

	public static boolean DPSCounter = true;
	public static boolean NOTLAST = false;
	public static boolean powerupAlarm = true;
	public static boolean bossAlarm = false;
	public static boolean growESP = false;
	public static boolean gstepGuide = false;

	public static boolean autoSLA = true;
	public static boolean advancedSLA = false;
	public static boolean shortenedSLA = true;

	public static boolean autoSplits = true;
	public static String host = "localhost";
	public static int port = -1;
	public static boolean showWaveDelays = true;
	public static int waveColorDelays = 3;
	public static boolean bossWaveAlarm = true;
	public static boolean growGuide = false;

	public static boolean hideAutoRejoin = true;

	public static void loadConfig() {
		config.load();

		boolean configEnableMod = config.getBoolean("Zombies Addon: Enable Mod", Configuration.CATEGORY_GENERAL, true,
				"Uses Zombie Addon. If set to false, Zombie Addon is disabled.");
		boolean configShowMods = config.getBoolean("Zombies Addon: Show Mods", Configuration.CATEGORY_GENERAL, true,
				"Display text of the mods on the in-game screen.");
		boolean configOverlayKorean = config.getBoolean("Zombies Addon: Use Zombies Overlay in Korean",
				Configuration.CATEGORY_GENERAL, false, "You can use Zombies Overlay in Korean.");
		boolean[] configModDefaultValue = config.get(Configuration.CATEGORY_GENERAL, "Zombies Addon: Mod Default Value",
				new boolean[] { false, false, false },
				"Sets the default value of Cornering, Block Alarm, and Auto Rejoin.").getBooleanList();
		boolean configcheckPreRelease = config.getBoolean("Zombies Addon: Check Pre-Release",
				Configuration.CATEGORY_GENERAL, true, "It checks the latest pre-releases.");

		boolean configDPSCounter = config.getBoolean("DPS Counter: Toggle DPS Counter", Configuration.CATEGORY_GENERAL,
				true, "Counts your party's DPS.");
		boolean configNOTLAST = config.getBoolean("NOTLAST: Toggle NOTLAST", Configuration.CATEGORY_GENERAL, false,
				"Shows the player who killed the last a zombie");
		boolean configPowerupAlarm = config.getBoolean("Powerup Alarm: Toggle Powerup Alarm",
				Configuration.CATEGORY_GENERAL, true,
				"You will receive an alarm when a powerup is spawned. You can know when powerup spawn and despawn.");
		boolean configBossAlarm = config.getBoolean("Boss Alarm: Toggle Boss Alarm", Configuration.CATEGORY_GENERAL,
				false, "You can know the area where the boss has spawned.");
		boolean configGrowESP = config.getBoolean("Zombies Addon: Grow ESP", Configuration.CATEGORY_GENERAL, false,
				"Shows average of size of blob.");
		boolean configGstepGuide = config.getBoolean("Gstep Guide: Toggle Gstep Guide", Configuration.CATEGORY_GENERAL,
				false, "Show hitbox of Armor Stand in Gstep.");

		float configRange = config.getFloat("Cornering: Range", Configuration.CATEGORY_GENERAL, 2.0F, 0.0F, 100.0F,
				"Set range of cornering.");

		boolean configAutoSLA = config.getBoolean("SLA: Auto SLA", Configuration.CATEGORY_GENERAL, true,
				"Automatically turn on SLA.");
		boolean configAdvancedSLA = config.getBoolean("SLA: Advanced SLA", Configuration.CATEGORY_GENERAL, false,
				"SLA is displayed in more detail.");
		boolean configShortenedSLA = config.getBoolean("SLA: Shortened SLA", Configuration.CATEGORY_GENERAL, true,
				"SLA is displayed shorter. (Advanced SLA only)");

		boolean configAutoSplits = config.getBoolean("Auto Splits: Toggle Auto Splits", Configuration.CATEGORY_GENERAL,
				true, "Run LiveSplits or internal timer at start of round.");
		String configHost = config.getString("Auto Splits: Host", Configuration.CATEGORY_GENERAL, "localhost",
				"The local IP to connect to LiveSplits.");
		int configPort = config.getInt("Auto Splits: Port", Configuration.CATEGORY_GENERAL, -1, -1, 65535,
				"The port to connect to LiveSplits. If value is -1, use internal timer.");
		boolean configShowWaveDelays = config.getBoolean("Auto Splits: Show Wave Delays",
				Configuration.CATEGORY_GENERAL, true, "Show wave delays(Same as Spawn Time).");
		int configWaveColorDelays = config.getInt("Auto Splits: Wave Color Delays", Configuration.CATEGORY_GENERAL, 3,
				0, 10, "Changes color of text to yellow before the wave starts X seconds.");
		boolean configBossWaveAlarm = config.getBoolean("Auto Splits: Boss Wave Alarm", Configuration.CATEGORY_GENERAL,
				true, "You can see the wave which the boss will spawn.");
		boolean configGrowGuide = config.getBoolean("Auto Splits: Grow Guide", Configuration.CATEGORY_GENERAL, false,
				"Displays the blob's grow time.");
		int configRLmodeOffset = config.getInt("Auto Splits: RL-mode offset", Configuration.CATEGORY_GENERAL, -28,
				-2000, 2000, "Ticks to be added to the wave delays time.");

		boolean configHideAutoRejoin = config.getBoolean("Auto Rejoin: Hide Auto Rejoin",
				Configuration.CATEGORY_GENERAL, true, "Hide the text of Auto Rejoin on the in-game screen.");

		enableMod = configEnableMod;
		showMods = configShowMods;
		overlayKorean = configOverlayKorean;
		bossAlarm = configBossAlarm;
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
		WaveDelaysListener.rlmode = configRLmodeOffset;

		powerupAlarm = configPowerupAlarm;

		CommandCornering.range = configRange;
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
