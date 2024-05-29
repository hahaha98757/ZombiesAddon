package kr.hahaha98757.zombiesaddon.config;

import java.util.LinkedHashMap;

import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import kr.hahaha98757.zombiesaddon.commands.CommandCornering;
import kr.hahaha98757.zombiesaddon.commands.CommandSLA;
import kr.hahaha98757.zombiesaddon.listeners.WaveDelaysListener;
import kr.hahaha98757.zombiesaddon.listeners.sla.advanced.AdvancedSLAListener;
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
	public static LinkedHashMap<String, IConfigElement> others = new LinkedHashMap<>();

	public static boolean enableMod = true;
	public static boolean autoDisableMod = true;
	public static boolean showMods = true;
	public static boolean[] modDefaultValue = { false, false, false };
	public static boolean checkPreRelease = false;
	
	public static boolean toggleWaveDelays = true;
	public static boolean bossWaveAlarm = true;
	public static String textStyle = "Zombies Addon";
	public static String highlightStyle = "Zombies Addon";
	
	public static boolean autoSLA = true;
	public static boolean advancedSLA = true;
	public static boolean shortenedSLA = true;
	
	public static boolean toggleDPSCounter = true;
	public static boolean toggleNotLast = true;
	public static boolean toggleAutoSplits = true;
	public static boolean togglePowerupAlarm = true;
	public static boolean toggleBossAlarm = true;
	public static boolean toggleGrowESP = true;
	public static boolean toggleGstepGuide = true;
	public static boolean toggleGrowGuide = false;
	public static boolean hideAutoRejoin = true;
	public static boolean overlayKorean = false;
	

	public static void loadConfig() {
		config.load();
		
		
		//Zombies Addon
		Property propertyEnableMod = config.get(Configuration.CATEGORY_GENERAL, "Enable Mod", true, "Uses Zombie Addon. If set to false, Zombie Addon is disabled.");
		enableMod = propertyEnableMod.getBoolean();
		zombiesAddon.put(propertyEnableMod.getName(), new ConfigElement(propertyEnableMod));
		
		Property propertyAutoDisableMod = config.get(Configuration.CATEGORY_GENERAL, "Auto Disable Mod", true, "Zombies Addon is disabled when not playing zombies.");
		autoDisableMod = propertyAutoDisableMod.getBoolean();
		zombiesAddon.put(propertyAutoDisableMod.getName(), new ConfigElement(propertyAutoDisableMod));
		
		Property propertyShowMods = config.get(Configuration.CATEGORY_GENERAL, "Show Mods", true, "Display text of the mods on the in-game screen.");
		showMods = propertyShowMods.getBoolean();
		zombiesAddon.put(propertyShowMods.getName(), new ConfigElement(propertyShowMods));
		
		Property propertyModDefaultValue = config.get(Configuration.CATEGORY_GENERAL, "Mod Default Value", new boolean[] { false, false, false }, "Sets the default value of Cornering, Block Alarm, and Auto Rejoin.");
		modDefaultValue = propertyModDefaultValue.getBooleanList();
		zombiesAddon.put(propertyModDefaultValue.getName(), new ConfigElement(propertyModDefaultValue));
		
		
		Property propertyCheckPreRelease = config.get(Configuration.CATEGORY_GENERAL, "Check Pre-Release", false, "It checks the latest pre-releases.");
		checkPreRelease = propertyCheckPreRelease.getBoolean();
		zombiesAddon.put(propertyCheckPreRelease.getName(), new ConfigElement(propertyCheckPreRelease));
		
		//Wave Delays
		Property propertyToggleWaveDelays = config.get(Configuration.CATEGORY_GENERAL, "Toggle Wave Delays", true, "Show wave delays(Same as Spawn Time).");
		toggleWaveDelays = propertyToggleWaveDelays.getBoolean();
		waveDelays.put(propertyToggleWaveDelays.getName(), new ConfigElement(propertyToggleWaveDelays));
		
		Property propertyBossWaveAlarm = config.get(Configuration.CATEGORY_GENERAL, "Boss Wave Alarm", true, "You can see the wave which the boss will spawn.");
		bossWaveAlarm = propertyBossWaveAlarm.getBoolean();
		waveDelays.put(propertyBossWaveAlarm.getName(), new ConfigElement(propertyBossWaveAlarm));

		Property propertyTextStyle = config.get(Configuration.CATEGORY_GENERAL, "Text Style", "Zombies Addon", "Sets the style of the text.", new String[] { "Zombies Addon", "SST" } );
		textStyle = propertyTextStyle.getString();
		waveDelays.put(propertyTextStyle.getName(), new ConfigElement(propertyTextStyle));
		
		Property propertyHighlightStyle = config.get(Configuration.CATEGORY_GENERAL, "Highlight Style", "Zombies Addon", "Sets the style of the highlight.", new String[] { "Zombies Addon", "SST" });
		highlightStyle = propertyHighlightStyle.getString();
		waveDelays.put(propertyHighlightStyle.getName(), new ConfigElement(propertyHighlightStyle));
		
		Property propertyRLmodeOffset = config.get(Configuration.CATEGORY_GENERAL, "RL-mode offset", -28, "Ticks to be added to the wave delays time.", -2000, 2000);
		WaveDelaysListener.rlmode = propertyRLmodeOffset.getInt();
		waveDelays.put(propertyRLmodeOffset.getName(), new ConfigElement(propertyRLmodeOffset));
		
		//SLA
		Property propertyAutoSLA = config.get(Configuration.CATEGORY_GENERAL, "Auto SLA", true, "Automatically turn on SLA.");
		autoSLA = propertyAutoSLA.getBoolean();
		sla.put(propertyAutoSLA.getName(), new ConfigElement(propertyAutoSLA));
		
		Property propertySLAVersion = config.get(Configuration.CATEGORY_GENERAL, "SLA Version", "SLAv2", "SLAv2 is displayed in more detail.", new String[] { "SLAv1", "SLAv2" });
		advancedSLA = propertySLAVersion.getString().equals("SLAv2");
		sla.put(propertySLAVersion.getName(), new ConfigElement(propertySLAVersion));
		
		Property propertyShortenedSLA = config.get(Configuration.CATEGORY_GENERAL, "Shortened SLA", true, "SLA is displayed shorter.(SLAv2 only)");
		shortenedSLA = propertyShortenedSLA.getBoolean();
		sla.put(propertyShortenedSLA.getName(), new ConfigElement(propertyShortenedSLA));
		
		//Others
		Property propertyToggleDPSCounter = config.get(Configuration.CATEGORY_GENERAL, "DPS Counter: Toggle DPS Counter", true, "Counts your party's DPS.");
		toggleDPSCounter = propertyToggleDPSCounter.getBoolean();
		others.put(propertyToggleDPSCounter.getName(), new ConfigElement(propertyToggleDPSCounter));
		
		Property propertyToggleNotLast = config.get(Configuration.CATEGORY_GENERAL, "Not Last: Toggle Not Last", true, "Shows the player who killed the last a zombie");
		toggleNotLast = propertyToggleNotLast.getBoolean();
		others.put(propertyToggleNotLast.getName(), new ConfigElement(propertyToggleNotLast));
		
		Property propertyToggleAutoSplits = config.get(Configuration.CATEGORY_GENERAL, "Auto Splits: Toggle Auto Splits", true, "Run timer at start of round.");
		toggleAutoSplits = propertyToggleAutoSplits.getBoolean();
		others.put(propertyToggleAutoSplits.getName(), new ConfigElement(propertyToggleAutoSplits));
		
		Property propertyTogglePowerupAlarm = config.get(Configuration.CATEGORY_GENERAL, "Powerup Alarm: Toggle Powerup Alarm", true, "You will receive an alarm when a powerup is spawned. You can know when powerup spawn and despawn.");
		togglePowerupAlarm = propertyTogglePowerupAlarm.getBoolean();
		others.put(propertyTogglePowerupAlarm.getName(), new ConfigElement(propertyTogglePowerupAlarm));
		
		Property propertyToggleBossAlarm = config.get(Configuration.CATEGORY_GENERAL, "Boss Alarm: Toggle Boss Alarm", true, "You can know the area where the boss has spawned.");
		toggleBossAlarm = propertyToggleBossAlarm.getBoolean();
		others.put(propertyToggleBossAlarm.getName(), new ConfigElement(propertyToggleBossAlarm));
		
		Property propertyToggleGrowESP = config.get(Configuration.CATEGORY_GENERAL, "Grow ESP: Toggle Grow ESP", true, "Shows average of size of blob.");
		toggleGrowESP = propertyToggleGrowESP.getBoolean();
		others.put(propertyToggleGrowESP.getName(), new ConfigElement(propertyToggleGrowESP));
		
		Property propertyToggleGstepGuide = config.get(Configuration.CATEGORY_GENERAL, "Gstep Guide: Toggle Gstep Guide", true, "Show hitbox of Armor Stand in Gstep.");
		toggleGstepGuide = propertyToggleGstepGuide.getBoolean();
		others.put(propertyToggleGstepGuide.getName(), new ConfigElement(propertyToggleGstepGuide));
		
		Property propertyToggleGrowGuide = config.get(Configuration.CATEGORY_GENERAL, "Grow Guide: Toggle Grow Guide", false, "Displays the blob's grow time.");
		toggleGrowGuide = propertyToggleGrowGuide.getBoolean();
		others.put(propertyToggleGrowGuide.getName(), new ConfigElement(propertyToggleGrowGuide));
		
		Property propertyCorneringRange = config.get(Configuration.CATEGORY_GENERAL, "Cornering: Range", 2.5, "Set range of Cornering.", 0.0, 100.0);
		CommandCornering.range = propertyCorneringRange.getDouble();
		others.put(propertyCorneringRange.getName(), new ConfigElement(propertyCorneringRange));
		
		Property propertyHideAutoRejoin = config.get(Configuration.CATEGORY_GENERAL, "Auto Rejoin: Hide Auto Rejoin", true, "Hide the text of Auto Rejoin on the in-game screen.");
		hideAutoRejoin = propertyHideAutoRejoin.getBoolean();
		others.put(propertyHideAutoRejoin.getName(), new ConfigElement(propertyHideAutoRejoin));
		
		Property propertyOverlayKorean = config.get(Configuration.CATEGORY_GENERAL, "Use Zombeis Overlay in Korean", false, "You can use Zombies Overlay in Korean.");
		overlayKorean = propertyOverlayKorean.getBoolean();
		others.put(propertyOverlayKorean.getName(), new ConfigElement(propertyOverlayKorean));
				
		
		
		if (!autoDisableMod) {
			enableMod = propertyEnableMod.getBoolean();
		}
		
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
	}

	public static boolean[] getModDefaultValue() {
		return modDefaultValue;
	}
}
