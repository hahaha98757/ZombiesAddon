package kr.hahaha98757.zombiesaddon.config;

import java.util.LinkedHashMap;

import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import kr.hahaha98757.zombiesaddon.commands.CommandCornering;
import kr.hahaha98757.zombiesaddon.commands.CommandSLA;
import kr.hahaha98757.zombiesaddon.listeners.BossTrackerListener;
import kr.hahaha98757.zombiesaddon.listeners.EventListener;
import kr.hahaha98757.zombiesaddon.listeners.PlayerTrackerListener;
import kr.hahaha98757.zombiesaddon.listeners.WaveDelaysListener;
import kr.hahaha98757.zombiesaddon.listeners.sla.SLAListener;
import kr.hahaha98757.zombiesaddon.splitter.LiveSplitSplitter;
import kr.hahaha98757.zombiesaddon.splitter.internal.InternalSplitter;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.IConfigElement;

public class ZombiesAddonConfig {
	public static Configuration config;

	public static LinkedHashMap<String, IConfigElement> zombiesAddon = new LinkedHashMap<>();
	public static LinkedHashMap<String, IConfigElement> waveDelays = new LinkedHashMap<>();
	public static LinkedHashMap<String, IConfigElement> sla = new LinkedHashMap<>();
	public static LinkedHashMap<String, IConfigElement> trackers = new LinkedHashMap<>();
	public static LinkedHashMap<String, IConfigElement> others = new LinkedHashMap<>();

	public static boolean enableMod = true;
	public static boolean showMods = true;
	public static boolean[] modDefaultValue = { false, false, false };
	public static boolean checkPreRelease = false;

	public static boolean toggleWaveDelays = true;
	public static boolean bossWaveAlarm = true;
	public static String textStyle = "Zombies Addon";
	public static String highlightStyle = "Zombies Addon";
	public static boolean playSound = true;
	public static boolean playCount = true;
	public static String soundName = "";
	public static double soundPitch = 1.0;
	public static String lastSoundName = "";
	public static double lastSoundPitch = 1.0;
	public static String countSoundName = "";
	public static double countSoundPitch = 1.0;

	public static boolean autoSLA = true;
	public static boolean showActiveWindowsByOthers = false;
	public static boolean showInactiveWindows = false;

	public static boolean togglePlayerTracker = false;
	public static boolean toggleBossTracker = false;

	public static boolean toggleDPSCounter = true;
	public static boolean toggleNotLast = true;
	public static boolean toggleAutoSplits = true;
	public static boolean togglePowerupAlarm = true;
	public static boolean onlyPowerupPattern = false;
	public static boolean toggleGstepGuide = false;
	public static boolean hideAutoRejoin = true;
	public static boolean overlayKorean = false;

	public static void loadConfig() {
		config.load();

		// Zombies Addon
		Property propertyEnableMod = config.get(Configuration.CATEGORY_GENERAL, "Enable Mod", true,
				"Uses Zombie Addon. If set to false, Zombie Addon is disabled.");
		enableMod = propertyEnableMod.getBoolean();
		zombiesAddon.put(propertyEnableMod.getName(), new ConfigElement(propertyEnableMod));

		Property propertyShowMods = config.get(Configuration.CATEGORY_GENERAL, "Show Mods", true,
				"Display text of the mods on the in-game screen.");
		showMods = propertyShowMods.getBoolean();
		zombiesAddon.put(propertyShowMods.getName(), new ConfigElement(propertyShowMods));

		Property propertyModDefaultValue = config.get(Configuration.CATEGORY_GENERAL, "Mod Default Value",
				new boolean[] { false, false, false },
				"Sets the default value of Cornering, Block Alarm, and Auto Rejoin.");
		modDefaultValue = propertyModDefaultValue.getBooleanList();
		zombiesAddon.put(propertyModDefaultValue.getName(), new ConfigElement(propertyModDefaultValue));

		Property propertyCheckPreRelease = config.get(Configuration.CATEGORY_GENERAL, "Check Pre-Release", false,
				"It checks the latest pre-releases.");
		checkPreRelease = propertyCheckPreRelease.getBoolean();
		zombiesAddon.put(propertyCheckPreRelease.getName(), new ConfigElement(propertyCheckPreRelease));

		// Wave Delays
		Property propertyToggleWaveDelays = config.get(Configuration.CATEGORY_GENERAL, "Toggle Wave Delays", true,
				"Show wave delays(Same as Spawn Time).");
		toggleWaveDelays = propertyToggleWaveDelays.getBoolean();
		waveDelays.put(propertyToggleWaveDelays.getName(), new ConfigElement(propertyToggleWaveDelays));

		Property propertyBossWaveAlarm = config.get(Configuration.CATEGORY_GENERAL, "Boss Wave Alarm", true,
				"You can see the wave which the boss will spawn.");
		bossWaveAlarm = propertyBossWaveAlarm.getBoolean();
		waveDelays.put(propertyBossWaveAlarm.getName(), new ConfigElement(propertyBossWaveAlarm));

		Property propertyTextStyle = config.get(Configuration.CATEGORY_GENERAL, "Text Style", "Zombies Addon",
				"Sets the style of the text.", new String[] { "Zombies Addon", "SST" });
		textStyle = propertyTextStyle.getString();
		waveDelays.put(propertyTextStyle.getName(), new ConfigElement(propertyTextStyle));

		Property propertyHighlightStyle = config.get(Configuration.CATEGORY_GENERAL, "Highlight Style", "Zombies Addon",
				"Sets the style of the highlight.", new String[] { "Zombies Addon", "SST" });
		highlightStyle = propertyHighlightStyle.getString();
		waveDelays.put(propertyHighlightStyle.getName(), new ConfigElement(propertyHighlightStyle));

		Property propertyRLmodeOffset = config.get(Configuration.CATEGORY_GENERAL, "RL-mode offset", -28,
				"Ticks to be added to the wave delays time.", -2000, 2000);
		WaveDelaysListener.rlmode = propertyRLmodeOffset.getInt();
		waveDelays.put(propertyRLmodeOffset.getName(), new ConfigElement(propertyRLmodeOffset));
		
		Property propertyPlaySound = config.get(Configuration.CATEGORY_GENERAL, "Play Sound", true, "Play sound when wave start.");
		playSound = propertyPlaySound.getBoolean();
		waveDelays.put(propertyPlaySound.getName(), new ConfigElement(propertyPlaySound));
		
		Property propertyPlayCount = config.get(Configuration.CATEGORY_GENERAL, "Play Count", true, "Play countdown before last wave start.");
		playCount = propertyPlayCount.getBoolean();
		waveDelays.put(propertyPlayCount.getName(), new ConfigElement(propertyPlayCount));
		
		Property propertySoundName = config.get(Configuration.CATEGORY_GENERAL, "Sound Name", "note.pling", "Sets the sound name of the non-last wave.");
		soundName = propertySoundName.getString();
		waveDelays.put(propertySoundName.getName(), new ConfigElement(propertySoundName));
		
		Property propertySoundPitch = config.get(Configuration.CATEGORY_GENERAL, "Sound Pitch", 2.0, "Sets the sound pitch of the non-last wave.", 0.0, 2.0);
		soundPitch = propertySoundPitch.getDouble();
		waveDelays.put(propertySoundPitch.getName(), new ConfigElement(propertySoundPitch));
		
		Property propertyLastSoundName = config.get(Configuration.CATEGORY_GENERAL, "Last Sound Name", "random.orb", "Sets the sound name of the last wave.");
		lastSoundName = propertyLastSoundName.getString();
		waveDelays.put(propertyLastSoundName.getName(), new ConfigElement(propertyLastSoundName));
		
		Property propertyLastSoundPitch = config.get(Configuration.CATEGORY_GENERAL, "Last Sound Pitch", 0.5, "Sets the sound pitch of the last wave.", 0.0, 2.0);
		lastSoundPitch = propertyLastSoundPitch.getDouble();
		waveDelays.put(propertyLastSoundPitch.getName(), new ConfigElement(propertyLastSoundPitch));
		
		Property propertyCountSoundName = config.get(Configuration.CATEGORY_GENERAL, "Count Sound Name", "note.pling", "Sets the countdown name.");
		countSoundName = propertyCountSoundName.getString();
		waveDelays.put(propertyCountSoundName.getName(), new ConfigElement(propertyCountSoundName));
		
		Property propertyCountSoundPitch = config.get(Configuration.CATEGORY_GENERAL, "Count Sound Pitch", 1.5, "Sets the countdown pitch.", 0.0, 2.0);
		countSoundPitch = propertyCountSoundPitch.getDouble();
		waveDelays.put(propertyCountSoundPitch.getName(), new ConfigElement(propertyCountSoundPitch));
		
		// SLA
		Property propertyAutoSLA = config.get(Configuration.CATEGORY_GENERAL, "Auto SLA", true,
				"Automatically turn on SLA.");
		autoSLA = propertyAutoSLA.getBoolean();
		sla.put(propertyAutoSLA.getName(), new ConfigElement(propertyAutoSLA));
		
		Property propertyShowActiveWindowsByOthers = config.get(Configuration.CATEGORY_GENERAL, "Show Active Windows By Others", false,
				"Show active windows by other players.");
		showActiveWindowsByOthers = propertyShowActiveWindowsByOthers.getBoolean();
		sla.put(propertyShowActiveWindowsByOthers.getName(), new ConfigElement(propertyShowActiveWindowsByOthers));

		Property propertyShowInactiveWindows = config.get(Configuration.CATEGORY_GENERAL, "Show Inactive Windows", false,
				"Show inactive windows.");
		showInactiveWindows = propertyShowInactiveWindows.getBoolean();
		sla.put(propertyShowInactiveWindows.getName(), new ConfigElement(propertyShowInactiveWindows));

		// Trackers
		Property propertyTogglePlayerTracker = config.get(Configuration.CATEGORY_GENERAL,
				"Player Tracker: Toggle Player Tracker", false, "Shows the location of the player.");
		togglePlayerTracker = propertyTogglePlayerTracker.getBoolean();
		trackers.put(propertyTogglePlayerTracker.getName(), new ConfigElement(propertyTogglePlayerTracker));

		Property propertyPlayerTrackerRange = config.get(Configuration.CATEGORY_GENERAL,
				"Player Tracker: Player Tracker Range", 2.5, "It will not work for players within this range.", 0.0,
				10.0);
		PlayerTrackerListener.range = propertyPlayerTrackerRange.getDouble();
		trackers.put(propertyPlayerTrackerRange.getName(), new ConfigElement(propertyPlayerTrackerRange));

		Property propertyToggleBossTracker = config.get(Configuration.CATEGORY_GENERAL,
				"Boss Tracker: Toggle Boss Tracker", false, "Shows the location of the boss.");
		toggleBossTracker = propertyToggleBossTracker.getBoolean();
		trackers.put(propertyToggleBossTracker.getName(), new ConfigElement(propertyToggleBossTracker));

		Property propertyBossTrackerRange = config.get(Configuration.CATEGORY_GENERAL,
				"Boss Tracker: Boss Tracker Range", 5.0, "It will not work for bosses within this range.", 0.0, 10.0);
		BossTrackerListener.range = propertyBossTrackerRange.getDouble();
		trackers.put(propertyBossTrackerRange.getName(), new ConfigElement(propertyBossTrackerRange));

		// Others
		Property propertyToggleDPSCounter = config.get(Configuration.CATEGORY_GENERAL,
				"DPS Counter: Toggle DPS Counter", true, "Counts your party's DPS.");
		toggleDPSCounter = propertyToggleDPSCounter.getBoolean();
		others.put(propertyToggleDPSCounter.getName(), new ConfigElement(propertyToggleDPSCounter));

		Property propertyToggleNotLast = config.get(Configuration.CATEGORY_GENERAL, "Not Last: Toggle Not Last", true,
				"Shows the player who killed the last a zombie");
		toggleNotLast = propertyToggleNotLast.getBoolean();
		others.put(propertyToggleNotLast.getName(), new ConfigElement(propertyToggleNotLast));

		Property propertyToggleAutoSplits = config.get(Configuration.CATEGORY_GENERAL,
				"Auto Splits: Toggle Auto Splits", true, "Run timer at start of round.");
		toggleAutoSplits = propertyToggleAutoSplits.getBoolean();
		others.put(propertyToggleAutoSplits.getName(), new ConfigElement(propertyToggleAutoSplits));

		Property propertyTogglePowerupAlarm = config.get(Configuration.CATEGORY_GENERAL,
				"Powerup Alarm: Toggle Powerup Alarm", true,
				"You will receive an alarm when a powerup is spawned. You can know when powerup spawn and despawn.");
		togglePowerupAlarm = propertyTogglePowerupAlarm.getBoolean();
		others.put(propertyTogglePowerupAlarm.getName(), new ConfigElement(propertyTogglePowerupAlarm));
		
		Property propertyOnlyPowerupPattern = config.get(Configuration.CATEGORY_GENERAL, "Powerup Alarm: Only Powerup Pattern", false, "Powerup timer is not allowed on speedrun.com.");
		onlyPowerupPattern = propertyOnlyPowerupPattern.getBoolean();
		others.put(propertyOnlyPowerupPattern.getName(), new ConfigElement(propertyOnlyPowerupPattern));

		Property propertyToggleGstepGuide = config.get(Configuration.CATEGORY_GENERAL,
				"Gstep Guide: Toggle Gstep Guide", false, "Show hitbox of Armor Stand in Gstep.");
		toggleGstepGuide = propertyToggleGstepGuide.getBoolean();
		others.put(propertyToggleGstepGuide.getName(), new ConfigElement(propertyToggleGstepGuide));

		Property propertyCorneringRange = config.get(Configuration.CATEGORY_GENERAL, "Cornering: Range", 2.5,
				"Set range of Cornering.", 0.0, 100.0);
		CommandCornering.range = propertyCorneringRange.getDouble();
		others.put(propertyCorneringRange.getName(), new ConfigElement(propertyCorneringRange));

		Property propertyHideAutoRejoin = config.get(Configuration.CATEGORY_GENERAL, "Auto Rejoin: Hide Auto Rejoin",
				true, "Hide the text of Auto Rejoin on the in-game screen.");
		hideAutoRejoin = propertyHideAutoRejoin.getBoolean();
		others.put(propertyHideAutoRejoin.getName(), new ConfigElement(propertyHideAutoRejoin));

		Property propertyOverlayKorean = config.get(Configuration.CATEGORY_GENERAL, "Use Zombeis Overlay in Korean",
				false, "You can use Zombies Overlay in Korean.");
		overlayKorean = propertyOverlayKorean.getBoolean();
		others.put(propertyOverlayKorean.getName(), new ConfigElement(propertyOverlayKorean));
	}

	public static Configuration getConfig() {
		return config;
	}

	public static void reloadConfig() {
		config.save();

		loadConfig();
	}

	public static boolean[] getModDefaultValue() {
		return modDefaultValue;
	}
}
