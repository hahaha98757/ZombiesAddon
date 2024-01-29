package kr.hahaha98757.zombiesaddon.listeners;

import java.util.Arrays;

import com.seosean.showspawntime.mapFile.Rounds;

import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.handler.RenderTimeHandler;
import kr.hahaha98757.zombiesaddon.util.GameDetect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class WaveDelaysListener {
	private static String waveColor1 = "8";
	private static String waveColor2 = "8";
	private static String waveColor3 = "8";
	private static String waveColor4 = "8";
	private static String waveColor5 = "8";
	private static String waveColor6 = "8";
	private static final String WAVE = "\u00A7d\u27A4 ";
	private static boolean wave1;
	private static boolean wave2;
	private static boolean wave3;
	private static boolean wave4;
	private static boolean wave5;
	private static boolean wave6;

	public static boolean hard;
	public static boolean rip;

	private static String bossesWave1;
	private static String bossesWave2;
	private static String bossesWave3;
	private static String bossesWave4;
	private static String bossesWave5;
	private static String bossesWave6;

	public String[][] waveDelay(boolean RIP) {
		String[][] DEDelays = new String[29][5];
		DEDelays = new String[][] { { "10", "20" }, { "10", "20" }, { "10", "20", "35" }, { "10", "20", "35" },
				{ "10", "22", "37" }, { "10", "22", "44" }, { "10", "25", "47" }, { "10", "25", "50" },
				{ "10", "22", "38" }, { "10", "24", "45" }, { "10", "25", "48" }, { "10", "25", "50" },
				{ "10", "25", "50" }, { "10", "25", "45" }, { "10", "25", "46" }, { "10", "24", "47" },
				{ "10", "24", "47" }, { "10", "24", "47" }, { "10", "24", "47" }, { "10", "24", "49" },
				{ "10", "23", "44" }, { "10", "23", "45" }, { "10", "23", "42" }, { "10", "23", "43" },
				{ "10", "23", "43" }, { "10", "23", "36" }, { "10", "24", "44" }, { "10", "24", "42" },
				{ "10", "24", "42" }, { "10", "24", "45" } };
		String[][] RIPDelays = new String[29][5];
		RIPDelays = new String[][] { { "10", "20" }, { "10", "20" }, { "10", "20", "35" }, { "10", "20", "35" },
				{ "10", "22", "37" }, { "10", "22", "44" }, { "10", "25", "47" }, { "10", "25", "50" },
				{ "10", "22", "38" }, { "10", "24", "45", "48" }, { "10", "25", "48" }, { "10", "25", "50" },
				{ "10", "25", "50" }, { "10", "25", "45" }, { "10", "25", "46" }, { "10", "24", "47" },
				{ "10", "24", "47" }, { "10", "24", "47" }, { "10", "24", "47" }, { "10", "24", "49", "52" },
				{ "10", "23", "44" }, { "10", "23", "45" }, { "10", "23", "42" }, { "10", "23", "43" },
				{ "10", "23", "43" }, { "10", "23", "36" }, { "10", "24", "44" }, { "10", "24", "42" },
				{ "10", "24", "42" }, { "10", "24", "45" } };
		String[][] BBDelays = new String[29][5];
		BBDelays = new String[][] { { "10", "22" }, { "10", "22" }, { "10", "22" }, { "10", "22", "34" },
				{ "10", "22", "34" }, { "10", "22", "34" }, { "10", "22", "34" }, { "10", "22", "34" },
				{ "10", "22", "34" }, { "10", "22", "34" }, { "10", "22", "34" }, { "10", "22", "34" },
				{ "10", "22", "34" }, { "10", "22", "34" }, { "10", "22", "34" }, { "10", "22", "34" },
				{ "10", "22", "34" }, { "10", "22", "34" }, { "10", "22", "34" }, { "10", "22", "34" },
				{ "10", "22", "34" }, { "10", "22", "34" }, { "10", "22", "34" }, { "10", "22", "34" },
				{ "10", "22", "34" }, { "10", "24", "38" }, { "10", "24", "38" }, { "10", "22", "34" },
				{ "10", "24", "38" }, { "10", "22", "34" } };
		String[][] AADelays = new String[104][5];
		AADelays = new String[][] { { "10", "13", "16", "19" }, { "10", "14", "18", "22" }, { "10", "13", "16", "19" },
				{ "10", "14", "17", "21", "25", "28" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "14", "19", "23", "28", "32" }, { "10", "15", "19", "23", "27", "31" },
				{ "10", "15", "20", "25", "30", "35" }, { "10", "14", "19", "23", "28", "32" },
				{ "10", "16", "22", "27", "33", "38" }, { "10", "16", "21", "27", "32", "38" },
				{ "10", "16", "22", "28", "34", "40" }, { "10", "16", "22", "28", "34", "40" },
				{ "10", "16", "21", "26", "31", "36" }, { "10", "17", "24", "31", "38", "46" },
				{ "10", "16", "22", "27", "33", "38" }, { "10", "14", "19", "23", "28", "32" },
				{ "10", "14", "19", "23", "28", "32" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "15", "21", "26", "31", "36" }, { "10", "14", "19", "23", "28", "32" },
				{ "10", "14", "19", "23", "28", "34" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "14", "19", "23", "28", "32" }, { "10" }, { "10", "23", "36" }, { "10", "22", "34" },
				{ "10", "20", "30" }, { "10", "24", "38" }, { "10", "22", "34" }, { "10", "22", "34" },
				{ "10", "21", "32" }, { "10", "22", "34" }, { "10", "22", "34" }, { "10" }, { "10", "22", "34" },
				{ "10", "20", "31" }, { "10", "22", "34" }, { "10", "22", "34" }, { "10", "22", "34", "37", "45" },
				{ "10", "21", "32" }, { "10", "22", "34" }, { "10", "13", "22", "25", "34", "37" },
				{ "10", "22", "34" }, { "10", "22", "34", "35" }, { "10", "21", "32", "35" }, { "10", "20", "30" },
				{ "10", "20", "30", "33" }, { "10", "21", "32" }, { "10", "22", "34", "37" },
				{ "10", "20", "30", "33" }, { "10", "22", "34", "37" }, { "10", "22", "34", "37" },
				{ "10", "20", "32", "35", "39" }, { "10", "16", "22", "28", "34", "40" }, { "10", "14", "18" },
				{ "10", "14", "18" }, { "10", "22", "34", "37", "38" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "20", "30", "33" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "14", "18", "22", "26", "30" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "14", "18", "22", "26", "30" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "14", "18", "22", "26", "30" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "14", "18", "22", "26", "30" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "14", "18", "22", "26", "30" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "14", "18", "22", "26", "30" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "14", "18", "22", "26", "30" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "14", "18", "22", "26", "30" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "14", "18", "22", "26", "30" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "14", "18", "22", "26", "30" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "14", "18", "22", "26", "30" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "14", "18", "22", "26", "30" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "14", "18", "22", "26", "30" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "14", "18", "22", "26", "30" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "14", "18", "22", "26", "30" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "14", "18", "22", "26", "30" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "14", "18", "22", "26", "30" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "14", "18", "22", "26", "30" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "14", "18", "22", "26", "30" }, { "10", "14", "18", "22", "26", "30" },
				{ "10", "14", "18", "22", "26", "30" }, { "5" }, { "5" }, { "5" }, { "5" }, { "5" } };

		if (GameDetect.mapDetect() == 1 && !RIP) {
			return DEDelays;
		} else if (GameDetect.mapDetect() == 1 && RIP) {
			return RIPDelays;
		} else if (GameDetect.mapDetect() == 2) {
			return BBDelays;
		} else if (GameDetect.mapDetect() == 3) {
			return AADelays;
		}
		return null;
	}

	public int[][] bossesWave() {// Bombie: 1, Inferno: 2, The Broodmother, Wither, or Herobrine: 3, Lily and
									// Ellie: 4 , King Slime or Mega Blob: 5, Giant: 6, The Old One or Mega Magma:
									// 7, Giant and The Old One: 8,
									// World Ender: 9
		int[][] DEDelays = new int[29][5];
		DEDelays = new int[][] { { 0, 0 }, { 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 2 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 3 } };
		int[][] DEHardDelays = new int[29][5];
		DEHardDelays = new int[][] { { 0, 0 }, { 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 1, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 2 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 3, 3 } };
		int[][] DERIPDelays = new int[29][5];
		DERIPDelays = new int[][] { { 0, 0 }, { 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1, 1 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 1, 2 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 2, 2 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 1, 1, 3 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 3, 3, 3 } };
		int[][] BBDelays = new int[29][5];
		BBDelays = new int[][] { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0, 0 }, { 0, 0, 4 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 5 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 3, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 3, 0 } };
		int[][] BBRIPDelays = new int[29][5];
		BBRIPDelays = new int[][] { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0, 0 }, { 0, 0, 4 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 5 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 5, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 3, 0, 3 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 0, 0, 5 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 3, 3, 0 } };
		int[][] AADelays = new int[104][5];
		AADelays = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 6 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 6, 0, 6, 0 },
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 6, 0, 6 }, { 0, 0, 0, 0, 0, 0 }, { 0, 6, 0, 6, 0, 6 }, { 5 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 6, 6, 6 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 0, 0, 0 }, { 7 }, { 0, 6, 6 }, { 0, 6, 6 }, { 0, 6, 6 }, { 0, 6, 6 }, { 0, 6, 6, 0, 7 }, { 0, 6, 6 },
				{ 6, 6, 6 }, { 0, 6, 0, 6, 0, 6 }, { 6, 6, 6 }, { 0, 6, 7, 7 }, { 0, 0, 0, 7 }, { 0, 0, 6 },
				{ 0, 0, 0, 7 }, { 0, 0, 0 }, { 0, 6, 0, 6 }, { 0, 6, 0, 6 }, { 0, 6, 0, 6 }, { 0, 6, 0, 6 },
				{ 0, 8, 0, 6, 7 }, { 6, 6, 6, 6, 8, 7 }, { 5, 0, 0 }, { 7, 0, 0 }, { 0, 8, 0, 6, 7 },
				{ 7, 7, 7, 7, 7, 7 }, { 0, 0, 7, 7 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 7, 7 }, { 0, 0, 0, 6, 6, 6 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 7 },
				{ 0, 0, 0, 0, 7, 7 }, { 0, 0, 0, 0, 7, 7 }, { 0, 7, 7, 8, 8, 8 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 7, 7, 7 }, { 0, 0, 0, 6, 6, 6 },
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 7 }, { 0, 0, 0, 0, 7, 7 }, { 0, 0, 0, 0, 7, 7 },
				{ 0, 7, 7, 8, 8, 8 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 7, 7, 7 }, { 0, 0, 0, 6, 6, 6 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 7 },
				{ 0, 0, 0, 0, 7, 7 }, { 0, 0, 0, 0, 7, 7 }, { 0, 7, 7, 8, 8, 8 }, { 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 7, 7, 7 }, { 0, 0, 0, 6, 6, 6 },
				{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 7 }, { 0, 0, 0, 0, 7, 7 }, { 0, 0, 0, 0, 7, 7 },
				{ 0, 7, 7, 8, 8, 8 }, { 9 }, { 7 }, { 7 }, { 7 }, { 7 } };

		if (GameDetect.mapDetect() == 1) {
			if (hard) {
				return DEHardDelays;
			} else if (rip) {
				return DERIPDelays;
			} else {
				return DEDelays;
			}
		} else if (GameDetect.mapDetect() == 2) {
			if (rip) {
				return BBRIPDelays;
			} else {
				return BBDelays;
			}
		} else if (GameDetect.mapDetect() == 3) {
			return AADelays;
		}
		return null;
	}

	public String[] onWaveDelays() {
		try {
			int r = GameDetect.roundDetect() - 1;

			if ((GameDetect.mapDetect() == 1 && (r == 14 || r == 24 || r == 29)
					&& GameDetect.isDifficult(GameDetect.mapDetect(), r) == 2) || hard) {
				hard = true;
			} else {
				hard = false;
			}
			if ((GameDetect.mapDetect() == 1 && (r == 4 || r == 9 || r == 14 || r == 19 || r == 24 || r == 29)
					&& GameDetect.isDifficult(GameDetect.mapDetect(), r) == 3) || rip) {
				rip = true;
			} else {
				rip = false;
			}
			if ((GameDetect.mapDetect() == 2 && (r == 14 || r == 19 || r == 24 || r == 29)
					&& GameDetect.isDifficult(GameDetect.mapDetect(), r) == 3) || rip) {
				rip = true;
			} else {
				rip = false;
			}
			if (!(r == 4 || r == 9 || r == 14 || r == 19 || r == 24 || r == 29)) {
				hard = false;
				rip = false;
			}

			String[][] strArray = waveDelay(rip);

			int[][] intArray = bossesWave();

			if (!ZombiesAddonConfig.bossWaveAlarm) {
				intArray = new int[][] { { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 } };
			}

			if (strArray[r].length == 1) {// w1
				if (intArray[r][0] == 0) {
					bossesWave1 = "";
				} else if (intArray[r][0] == 1) {
					bossesWave1 = "\u00A76";
				} else if (intArray[r][0] == 2) {
					bossesWave1 = "\u00A7c";
				} else if (intArray[r][0] == 3) {
					bossesWave1 = "\u00A75";
				} else if (intArray[r][0] == 4) {
					bossesWave1 = "\u00A7c";
				} else if (intArray[r][0] == 5) {
					bossesWave1 = "\u00A7a";
				} else if (intArray[r][0] == 6) {
					bossesWave1 = "\u00A73";
				} else if (intArray[r][0] == 7) {
					bossesWave1 = "\u00A74";
				} else if (intArray[r][0] == 8) {
					bossesWave1 = "\u00A73";
				} else if (intArray[r][0] == 9) {
					bossesWave1 = "\u00A70";
				}

				String str = (wave1 ? WAVE : "") + "\u00A7" + waveColor1 + "W1 " + bossesWave1 + "00:"
						+ (intArray[r][0] == 4 ? "\u00A7b" : (intArray[r][0] == 8 ? "\u00A74" : "")) + strArray[r][0];
				FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
				int stringHeight = fr.FONT_HEIGHT;
				int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
				int y = screenHeight - stringHeight;
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 10, 0);
				return strArray[r];

			} else if (strArray[r].length == 2) {// w2
				if (intArray[r][1] == 0) {
					bossesWave2 = "";
				} else if (intArray[r][1] == 1) {
					bossesWave2 = "\u00A76";
				} else if (intArray[r][1] == 2) {
					bossesWave2 = "\u00A7c";
				} else if (intArray[r][1] == 3) {
					bossesWave2 = "\u00A75";
				} else if (intArray[r][1] == 4) {
					bossesWave2 = "\u00A7c";
				} else if (intArray[r][1] == 5) {
					bossesWave2 = "\u00A7a";
				} else if (intArray[r][1] == 6) {
					bossesWave2 = "\u00A73";
				} else if (intArray[r][1] == 7) {
					bossesWave2 = "\u00A74";
				} else if (intArray[r][1] == 8) {
					bossesWave2 = "\u00A73";
				} else if (intArray[r][1] == 9) {
					bossesWave2 = "\u00A70";
				}

				String str = (wave2 ? WAVE : "") + "\u00A7" + waveColor2 + "W2 " + bossesWave2 + "00:"
						+ (intArray[r][1] == 4 ? "\u00A7b" : (intArray[r][1] == 8 ? "\u00A74" : "")) + strArray[r][1];
				FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
				int stringHeight = fr.FONT_HEIGHT;
				int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
				int y = screenHeight - stringHeight;
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 10, 0);

				if (intArray[r][0] == 0) {
					bossesWave1 = "";
				} else if (intArray[r][0] == 1) {
					bossesWave1 = "\u00A76";
				} else if (intArray[r][0] == 2) {
					bossesWave1 = "\u00A7c";
				} else if (intArray[r][0] == 3) {
					bossesWave1 = "\u00A75";
				} else if (intArray[r][0] == 4) {
					bossesWave1 = "\u00A7c";
				} else if (intArray[r][0] == 5) {
					bossesWave1 = "\u00A7a";
				} else if (intArray[r][0] == 6) {
					bossesWave1 = "\u00A73";
				} else if (intArray[r][0] == 7) {
					bossesWave1 = "\u00A74";
				} else if (intArray[r][0] == 8) {
					bossesWave1 = "\u00A73";
				} else if (intArray[r][0] == 9) {
					bossesWave1 = "\u00A70";
				}

				str = (wave1 ? WAVE : "") + "\u00A7" + waveColor1 + "W1 " + bossesWave1 + "00:"
						+ (intArray[r][0] == 4 ? "\u00A7b" : (intArray[r][0] == 8 ? "\u00A74" : "")) + strArray[r][0];
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 18, 0);
				return strArray[r];

			} else if (strArray[r].length == 3) {// w3
				if (intArray[r][2] == 0) {
					bossesWave3 = "";
				} else if (intArray[r][2] == 1) {
					bossesWave3 = "\u00A76";
				} else if (intArray[r][2] == 2) {
					bossesWave3 = "\u00A7c";
				} else if (intArray[r][2] == 3) {
					bossesWave3 = "\u00A75";
				} else if (intArray[r][2] == 4) {
					bossesWave3 = "\u00A7c";
				} else if (intArray[r][2] == 5) {
					bossesWave3 = "\u00A7a";
				} else if (intArray[r][2] == 6) {
					bossesWave3 = "\u00A73";
				} else if (intArray[r][2] == 7) {
					bossesWave3 = "\u00A74";
				} else if (intArray[r][2] == 8) {
					bossesWave3 = "\u00A73";
				} else if (intArray[r][2] == 9) {
					bossesWave3 = "\u00A70";
				}

				String str = (wave3 ? WAVE : "") + "\u00A7" + waveColor3 + "W3 " + bossesWave3 + "00:"
						+ (intArray[r][2] == 4 ? "\u00A7b" : (intArray[r][2] == 8 ? "\u00A74" : "")) + strArray[r][2];
				FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
				int stringHeight = fr.FONT_HEIGHT;
				int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
				int y = screenHeight - stringHeight;
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 10, 0);

				if (intArray[r][1] == 0) {
					bossesWave2 = "";
				} else if (intArray[r][1] == 1) {
					bossesWave2 = "\u00A76";
				} else if (intArray[r][1] == 2) {
					bossesWave2 = "\u00A7c";
				} else if (intArray[r][1] == 3) {
					bossesWave2 = "\u00A75";
				} else if (intArray[r][1] == 4) {
					bossesWave2 = "\u00A7c";
				} else if (intArray[r][1] == 5) {
					bossesWave2 = "\u00A7a";
				} else if (intArray[r][1] == 6) {
					bossesWave2 = "\u00A73";
				} else if (intArray[r][1] == 7) {
					bossesWave2 = "\u00A74";
				} else if (intArray[r][1] == 8) {
					bossesWave2 = "\u00A73";
				} else if (intArray[r][1] == 9) {
					bossesWave2 = "\u00A70";
				}

				str = (wave2 ? WAVE : "") + "\u00A7" + waveColor2 + "W2 " + bossesWave2 + "00:"
						+ (intArray[r][1] == 4 ? "\u00A7b" : (intArray[r][1] == 8 ? "\u00A74" : "")) + strArray[r][1];
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 18, 0);

				if (intArray[r][0] == 0) {
					bossesWave1 = "";
				} else if (intArray[r][0] == 1) {
					bossesWave1 = "\u00A76";
				} else if (intArray[r][0] == 2) {
					bossesWave1 = "\u00A7c";
				} else if (intArray[r][0] == 3) {
					bossesWave1 = "\u00A75";
				} else if (intArray[r][0] == 4) {
					bossesWave1 = "\u00A7c";
				} else if (intArray[r][0] == 5) {
					bossesWave1 = "\u00A7a";
				} else if (intArray[r][0] == 6) {
					bossesWave1 = "\u00A73";
				} else if (intArray[r][0] == 7) {
					bossesWave1 = "\u00A74";
				} else if (intArray[r][0] == 8) {
					bossesWave1 = "\u00A73";
				} else if (intArray[r][0] == 9) {
					bossesWave1 = "\u00A70";
				}

				str = (wave1 ? WAVE : "") + "\u00A7" + waveColor1 + "W1 " + bossesWave1 + "00:"
						+ (intArray[r][0] == 4 ? "\u00A7b" : (intArray[r][0] == 8 ? "\u00A74" : "")) + strArray[r][0];
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 26, 0);
				return strArray[r];

			} else if (strArray[r].length == 4) {// w4
				if (intArray[r][3] == 0) {
					bossesWave4 = "";
				} else if (intArray[r][3] == 1) {
					bossesWave4 = "\u00A76";
				} else if (intArray[r][3] == 2) {
					bossesWave4 = "\u00A7c";
				} else if (intArray[r][3] == 3) {
					bossesWave4 = "\u00A75";
				} else if (intArray[r][3] == 4) {
					bossesWave4 = "\u00A7c";
				} else if (intArray[r][3] == 5) {
					bossesWave4 = "\u00A7a";
				} else if (intArray[r][3] == 6) {
					bossesWave4 = "\u00A73";
				} else if (intArray[r][3] == 7) {
					bossesWave4 = "\u00A74";
				} else if (intArray[r][3] == 8) {
					bossesWave4 = "\u00A73";
				} else if (intArray[r][3] == 9) {
					bossesWave4 = "\u00A70";
				}

				String str = (wave4 ? WAVE : "") + "\u00A7" + waveColor4 + "W4 " + bossesWave4 + "00:"
						+ (intArray[r][3] == 4 ? "\u00A7b" : (intArray[r][3] == 8 ? "\u00A74" : "")) + strArray[r][3];
				FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
				int stringHeight = fr.FONT_HEIGHT;
				int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
				int y = screenHeight - stringHeight;
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 10, 0);

				if (intArray[r][2] == 0) {
					bossesWave3 = "";
				} else if (intArray[r][2] == 1) {
					bossesWave3 = "\u00A76";
				} else if (intArray[r][2] == 2) {
					bossesWave3 = "\u00A7c";
				} else if (intArray[r][2] == 3) {
					bossesWave3 = "\u00A75";
				} else if (intArray[r][2] == 4) {
					bossesWave3 = "\u00A7c";
				} else if (intArray[r][2] == 5) {
					bossesWave3 = "\u00A7a";
				} else if (intArray[r][2] == 6) {
					bossesWave3 = "\u00A73";
				} else if (intArray[r][2] == 7) {
					bossesWave3 = "\u00A74";
				} else if (intArray[r][2] == 8) {
					bossesWave3 = "\u00A73";
				} else if (intArray[r][2] == 9) {
					bossesWave3 = "\u00A70";
				}

				str = (wave3 ? WAVE : "") + "\u00A7" + waveColor3 + "W3 " + bossesWave3 + "00:"
						+ (intArray[r][2] == 4 ? "\u00A7b" : (intArray[r][2] == 8 ? "\u00A74" : "")) + strArray[r][2];
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 18, 0);

				if (intArray[r][1] == 0) {
					bossesWave2 = "";
				} else if (intArray[r][1] == 1) {
					bossesWave2 = "\u00A76";
				} else if (intArray[r][1] == 2) {
					bossesWave2 = "\u00A7c";
				} else if (intArray[r][1] == 3) {
					bossesWave2 = "\u00A75";
				} else if (intArray[r][1] == 4) {
					bossesWave2 = "\u00A7c";
				} else if (intArray[r][1] == 5) {
					bossesWave2 = "\u00A7a";
				} else if (intArray[r][1] == 6) {
					bossesWave2 = "\u00A73";
				} else if (intArray[r][1] == 7) {
					bossesWave2 = "\u00A74";
				} else if (intArray[r][1] == 8) {
					bossesWave2 = "\u00A73";
				} else if (intArray[r][1] == 9) {
					bossesWave2 = "\u00A70";
				}

				str = (wave2 ? WAVE : "") + "\u00A7" + waveColor2 + "W2 " + bossesWave2 + "00:"
						+ (intArray[r][1] == 4 ? "\u00A7b" : (intArray[r][1] == 8 ? "\u00A74" : "")) + strArray[r][1];
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 26, 0);

				if (intArray[r][0] == 0) {
					bossesWave1 = "";
				} else if (intArray[r][0] == 1) {
					bossesWave1 = "\u00A76";
				} else if (intArray[r][0] == 2) {
					bossesWave1 = "\u00A7c";
				} else if (intArray[r][0] == 3) {
					bossesWave1 = "\u00A75";
				} else if (intArray[r][0] == 4) {
					bossesWave1 = "\u00A7c";
				} else if (intArray[r][0] == 5) {
					bossesWave1 = "\u00A7a";
				} else if (intArray[r][0] == 6) {
					bossesWave1 = "\u00A73";
				} else if (intArray[r][0] == 7) {
					bossesWave1 = "\u00A74";
				} else if (intArray[r][0] == 8) {
					bossesWave1 = "\u00A73";
				} else if (intArray[r][0] == 9) {
					bossesWave1 = "\u00A70";
				}

				str = (wave1 ? WAVE : "") + "\u00A7" + waveColor1 + "W1 " + bossesWave1 + "00:"
						+ (intArray[r][0] == 4 ? "\u00A7b" : (intArray[r][0] == 8 ? "\u00A74" : "")) + strArray[r][0];
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 34, 0);
				return strArray[r];

			} else if (strArray[r].length == 5) {// w5
				if (intArray[r][4] == 0) {
					bossesWave5 = "";
				} else if (intArray[r][4] == 1) {
					bossesWave5 = "\u00A76";
				} else if (intArray[r][4] == 2) {
					bossesWave5 = "\u00A7c";
				} else if (intArray[r][4] == 3) {
					bossesWave5 = "\u00A75";
				} else if (intArray[r][4] == 4) {
					bossesWave5 = "\u00A7c";
				} else if (intArray[r][4] == 5) {
					bossesWave5 = "\u00A7a";
				} else if (intArray[r][4] == 6) {
					bossesWave5 = "\u00A73";
				} else if (intArray[r][4] == 7) {
					bossesWave5 = "\u00A74";
				} else if (intArray[r][4] == 8) {
					bossesWave5 = "\u00A73";
				} else if (intArray[r][4] == 9) {
					bossesWave5 = "\u00A70";
				}

				String str = (wave5 ? WAVE : "") + "\u00A7" + waveColor5 + "W5 " + bossesWave5 + "00:"
						+ (intArray[r][4] == 4 ? "\u00A7b" : (intArray[r][4] == 8 ? "\u00A74" : "")) + strArray[r][4];
				FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
				int stringHeight = fr.FONT_HEIGHT;
				int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
				int y = screenHeight - stringHeight;
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 10, 0);

				if (intArray[r][3] == 0) {
					bossesWave4 = "";
				} else if (intArray[r][3] == 1) {
					bossesWave4 = "\u00A76";
				} else if (intArray[r][3] == 2) {
					bossesWave4 = "\u00A7c";
				} else if (intArray[r][3] == 3) {
					bossesWave4 = "\u00A75";
				} else if (intArray[r][3] == 4) {
					bossesWave4 = "\u00A7c";
				} else if (intArray[r][3] == 5) {
					bossesWave4 = "\u00A7a";
				} else if (intArray[r][3] == 6) {
					bossesWave4 = "\u00A73";
				} else if (intArray[r][3] == 7) {
					bossesWave4 = "\u00A74";
				} else if (intArray[r][3] == 8) {
					bossesWave4 = "\u00A73";
				} else if (intArray[r][3] == 9) {
					bossesWave4 = "\u00A70";
				}

				str = (wave4 ? WAVE : "") + "\u00A7" + waveColor4 + "W4 " + bossesWave4 + "00:"
						+ (intArray[r][3] == 4 ? "\u00A7b" : (intArray[r][3] == 8 ? "\u00A74" : "")) + strArray[r][3];
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 18, 0);

				if (intArray[r][2] == 0) {
					bossesWave3 = "";
				} else if (intArray[r][2] == 1) {
					bossesWave3 = "\u00A76";
				} else if (intArray[r][2] == 2) {
					bossesWave3 = "\u00A7c";
				} else if (intArray[r][2] == 3) {
					bossesWave3 = "\u00A75";
				} else if (intArray[r][2] == 4) {
					bossesWave3 = "\u00A7c";
				} else if (intArray[r][2] == 5) {
					bossesWave3 = "\u00A7a";
				} else if (intArray[r][2] == 6) {
					bossesWave3 = "\u00A73";
				} else if (intArray[r][2] == 7) {
					bossesWave3 = "\u00A74";
				} else if (intArray[r][2] == 8) {
					bossesWave3 = "\u00A73";
				} else if (intArray[r][2] == 9) {
					bossesWave3 = "\u00A70";
				}

				str = (wave3 ? WAVE : "") + "\u00A7" + waveColor3 + "W3 " + bossesWave3 + "00:"
						+ (intArray[r][2] == 4 ? "\u00A7b" : (intArray[r][2] == 8 ? "\u00A74" : "")) + strArray[r][2];
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 26, 0);

				if (intArray[r][1] == 0) {
					bossesWave2 = "";
				} else if (intArray[r][1] == 1) {
					bossesWave2 = "\u00A76";
				} else if (intArray[r][1] == 2) {
					bossesWave2 = "\u00A7c";
				} else if (intArray[r][1] == 3) {
					bossesWave2 = "\u00A75";
				} else if (intArray[r][1] == 4) {
					bossesWave2 = "\u00A7c";
				} else if (intArray[r][1] == 5) {
					bossesWave2 = "\u00A7a";
				} else if (intArray[r][1] == 6) {
					bossesWave2 = "\u00A73";
				} else if (intArray[r][1] == 7) {
					bossesWave2 = "\u00A74";
				} else if (intArray[r][1] == 8) {
					bossesWave2 = "\u00A73";
				} else if (intArray[r][1] == 9) {
					bossesWave2 = "\u00A70";
				}

				str = (wave2 ? WAVE : "") + "\u00A7" + waveColor2 + "W2 " + bossesWave2 + "00:"
						+ (intArray[r][1] == 4 ? "\u00A7b" : (intArray[r][1] == 8 ? "\u00A74" : "")) + strArray[r][1];
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 34, 0);

				if (intArray[r][0] == 0) {
					bossesWave1 = "";
				} else if (intArray[r][0] == 1) {
					bossesWave1 = "\u00A76";
				} else if (intArray[r][0] == 2) {
					bossesWave1 = "\u00A7c";
				} else if (intArray[r][0] == 3) {
					bossesWave1 = "\u00A75";
				} else if (intArray[r][0] == 4) {
					bossesWave1 = "\u00A7c";
				} else if (intArray[r][0] == 5) {
					bossesWave1 = "\u00A7a";
				} else if (intArray[r][0] == 6) {
					bossesWave1 = "\u00A73";
				} else if (intArray[r][0] == 7) {
					bossesWave1 = "\u00A74";
				} else if (intArray[r][0] == 8) {
					bossesWave1 = "\u00A73";
				} else if (intArray[r][0] == 9) {
					bossesWave1 = "\u00A70";
				}

				str = (wave1 ? WAVE : "") + "\u00A7" + waveColor1 + "W1 " + bossesWave1 + "00:"
						+ (intArray[r][0] == 4 ? "\u00A7b" : (intArray[r][0] == 8 ? "\u00A74" : "")) + strArray[r][0];
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 42, 0);
				return strArray[r];

			} else {// w6
				if (intArray[r][5] == 0) {
					bossesWave6 = "";
				} else if (intArray[r][5] == 1) {
					bossesWave6 = "\u00A76";
				} else if (intArray[r][5] == 2) {
					bossesWave6 = "\u00A7c";
				} else if (intArray[r][5] == 3) {
					bossesWave6 = "\u00A75";
				} else if (intArray[r][5] == 4) {
					bossesWave6 = "\u00A7c";
				} else if (intArray[r][5] == 5) {
					bossesWave6 = "\u00A7a";
				} else if (intArray[r][5] == 6) {
					bossesWave6 = "\u00A73";
				} else if (intArray[r][5] == 7) {
					bossesWave6 = "\u00A74";
				} else if (intArray[r][5] == 8) {
					bossesWave6 = "\u00A73";
				} else if (intArray[r][5] == 9) {
					bossesWave6 = "\u00A70";
				}

				String str = (wave6 ? WAVE : "") + "\u00A7" + waveColor6 + "W6 " + bossesWave6 + "00:"
						+ (intArray[r][5] == 4 ? "\u00A7b" : (intArray[r][5] == 8 ? "\u00A74" : "")) + strArray[r][5];
				FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
				int stringHeight = fr.FONT_HEIGHT;
				int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
				int y = screenHeight - stringHeight;
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 10, 0);

				if (intArray[r][4] == 0) {
					bossesWave5 = "";
				} else if (intArray[r][4] == 1) {
					bossesWave5 = "\u00A76";
				} else if (intArray[r][4] == 2) {
					bossesWave5 = "\u00A7c";
				} else if (intArray[r][4] == 3) {
					bossesWave5 = "\u00A75";
				} else if (intArray[r][4] == 4) {
					bossesWave5 = "\u00A7c";
				} else if (intArray[r][4] == 5) {
					bossesWave5 = "\u00A7a";
				} else if (intArray[r][4] == 6) {
					bossesWave5 = "\u00A73";
				} else if (intArray[r][4] == 7) {
					bossesWave5 = "\u00A74";
				} else if (intArray[r][4] == 8) {
					bossesWave5 = "\u00A73";
				} else if (intArray[r][4] == 9) {
					bossesWave5 = "\u00A70";
				}

				str = (wave5 ? WAVE : "") + "\u00A7" + waveColor5 + "W5 " + bossesWave5 + "00:"
						+ (intArray[r][4] == 4 ? "\u00A7b" : (intArray[r][4] == 8 ? "\u00A74" : "")) + strArray[r][4];
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 18, 0);

				if (intArray[r][3] == 0) {
					bossesWave4 = "";
				} else if (intArray[r][3] == 1) {
					bossesWave4 = "\u00A76";
				} else if (intArray[r][3] == 2) {
					bossesWave4 = "\u00A7c";
				} else if (intArray[r][3] == 3) {
					bossesWave4 = "\u00A75";
				} else if (intArray[r][3] == 4) {
					bossesWave4 = "\u00A7c";
				} else if (intArray[r][3] == 5) {
					bossesWave4 = "\u00A7a";
				} else if (intArray[r][3] == 6) {
					bossesWave4 = "\u00A73";
				} else if (intArray[r][3] == 7) {
					bossesWave4 = "\u00A74";
				} else if (intArray[r][3] == 8) {
					bossesWave4 = "\u00A73";
				} else if (intArray[r][3] == 9) {
					bossesWave4 = "\u00A70";
				}

				str = (wave4 ? WAVE : "") + "\u00A7" + waveColor4 + "W4 " + bossesWave4 + "00:"
						+ (intArray[r][3] == 4 ? "\u00A7b" : (intArray[r][3] == 8 ? "\u00A74" : "")) + strArray[r][3];
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 26, 0);

				if (intArray[r][2] == 0) {
					bossesWave3 = "";
				} else if (intArray[r][2] == 1) {
					bossesWave3 = "\u00A76";
				} else if (intArray[r][2] == 2) {
					bossesWave3 = "\u00A7c";
				} else if (intArray[r][2] == 3) {
					bossesWave3 = "\u00A75";
				} else if (intArray[r][2] == 4) {
					bossesWave3 = "\u00A7c";
				} else if (intArray[r][2] == 5) {
					bossesWave3 = "\u00A7a";
				} else if (intArray[r][2] == 6) {
					bossesWave3 = "\u00A73";
				} else if (intArray[r][2] == 7) {
					bossesWave3 = "\u00A74";
				} else if (intArray[r][2] == 8) {
					bossesWave3 = "\u00A73";
				} else if (intArray[r][2] == 9) {
					bossesWave3 = "\u00A70";
				}

				str = (wave3 ? WAVE : "") + "\u00A7" + waveColor3 + "W3 " + bossesWave3 + "00:"
						+ (intArray[r][2] == 4 ? "\u00A7b" : (intArray[r][2] == 8 ? "\u00A74" : "")) + strArray[r][2];
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 34, 0);

				if (intArray[r][1] == 0) {
					bossesWave2 = "";
				} else if (intArray[r][1] == 1) {
					bossesWave2 = "\u00A76";
				} else if (intArray[r][1] == 2) {
					bossesWave2 = "\u00A7c";
				} else if (intArray[r][1] == 3) {
					bossesWave2 = "\u00A75";
				} else if (intArray[r][1] == 4) {
					bossesWave2 = "\u00A7c";
				} else if (intArray[r][1] == 5) {
					bossesWave2 = "\u00A7a";
				} else if (intArray[r][1] == 6) {
					bossesWave2 = "\u00A73";
				} else if (intArray[r][1] == 7) {
					bossesWave2 = "\u00A74";
				} else if (intArray[r][1] == 8) {
					bossesWave2 = "\u00A73";
				} else if (intArray[r][1] == 9) {
					bossesWave2 = "\u00A70";
				}

				str = (wave2 ? WAVE : "") + "\u00A7" + waveColor2 + "W2 " + bossesWave2 + "00:"
						+ (intArray[r][1] == 4 ? "\u00A7b" : (intArray[r][1] == 8 ? "\u00A74" : "")) + strArray[r][1];
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 42, 0);

				if (intArray[r][0] == 0) {
					bossesWave1 = "";
				} else if (intArray[r][0] == 1) {
					bossesWave1 = "\u00A76";
				} else if (intArray[r][0] == 2) {
					bossesWave1 = "\u00A7c";
				} else if (intArray[r][0] == 3) {
					bossesWave1 = "\u00A75";
				} else if (intArray[r][0] == 4) {
					bossesWave1 = "\u00A7c";
				} else if (intArray[r][0] == 5) {
					bossesWave1 = "\u00A7a";
				} else if (intArray[r][0] == 6) {
					bossesWave1 = "\u00A73";
				} else if (intArray[r][0] == 7) {
					bossesWave1 = "\u00A74";
				} else if (intArray[r][0] == 8) {
					bossesWave1 = "\u00A73";
				} else if (intArray[r][0] == 9) {
					bossesWave1 = "\u00A70";
				}

				str = (wave1 ? WAVE : "") + "\u00A7" + waveColor1 + "W1 " + bossesWave1 + "00:"
						+ (intArray[r][0] == 4 ? "\u00A7b" : (intArray[r][0] == 8 ? "\u00A74" : "")) + strArray[r][0];
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 50, 0);
				return strArray[r];
			}

		} catch (Exception e) {
			String str = "\u00A7cUnknown";
			FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
			int stringHeight = fr.FONT_HEIGHT;
			int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
			int y = screenHeight - stringHeight;
			fr.drawStringWithShadow(str,
					((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
					y - 10, 0);
			return null;
		}
	}

	public int[] intWaveDelays() {
		try {
			String[] strArray = onWaveDelays();
			int[] intArray = Arrays.stream(strArray).mapToInt(Integer::parseInt).toArray();

			return intArray;
		} catch (Exception e) {
			return null;
		}
	}

	@SubscribeEvent
	public void compareTimer(RenderGameOverlayEvent.Post event) {
		if (!ZombiesAddonConfig.autoSplits || !ZombiesAddonConfig.showWaveDelays) {
			return;
		}
		boolean despawn;
		if (event.type != ElementType.EXPERIENCE) {
			return;
		}
		if (GameDetect.mapDetect() == -1 || GameDetect.roundDetect() == -1) {
			return;
		}
		try {
			int[] wd = intWaveDelays();
			String s = RenderTimeHandler.timer;

			s = s.replaceAll("\\.", ":");

			String m = s.split(":")[0].trim();
			int m2 = Integer.parseInt(m);
			s = s.split(":")[1].trim();
			int time = Integer.parseInt(s);
			if (m2 == 5) {
				despawn = true;
			} else {
				despawn = false;
			}
			if (m2 != 0 && m2 != 5) {
				return;
			} else if (m2 == 5) {
				if (wd.length == 1) {// w1
					if (wd[0] - time <= 0) {
						waveColor1 = "c";
					}

				} else if (wd.length == 2) {// w2
					if (wd[0] - time <= 0) {
						waveColor1 = "c";
					}

					if (wd[1] - time <= 0) {
						waveColor2 = "c";
					}

				} else if (wd.length == 3) {// w3
					if (wd[0] - time <= 0) {
						waveColor1 = "c";
					}

					if (wd[1] - time <= 0) {
						waveColor2 = "c";
					}

					if (wd[2] - time <= 0) {
						waveColor3 = "c";
					}

				} else if (wd.length == 4) {// w4
					if (wd[0] - time <= 0) {
						waveColor1 = "c";
					}

					if (wd[1] - time <= 0) {
						waveColor2 = "c";
					}

					if (wd[2] - time <= 0) {
						waveColor3 = "c";
					}

					if (wd[3] - time <= 0) {
						waveColor4 = "c";
					}

				} else if (wd.length == 5) {// w5
					if (wd[0] - time <= 0) {
						waveColor1 = "c";
					}

					if (wd[1] - time <= 0) {
						waveColor2 = "c";
					}

					if (wd[2] - time <= 0) {
						waveColor3 = "c";
					}

					if (wd[3] - time <= 0) {
						waveColor4 = "c";
					}

					if (wd[4] - time <= 0) {
						waveColor5 = "c";
					}

				} else if (wd.length == 6) {// w6
					if (wd[0] - time <= 0) {
						waveColor1 = "c";
					}

					if (wd[1] - time <= 0) {
						waveColor2 = "c";
					}

					if (wd[2] - time <= 0) {
						waveColor3 = "c";
					}

					if (wd[3] - time <= 0) {
						waveColor4 = "c";
					}

					if (wd[4] - time <= 0) {
						waveColor5 = "c";
					}

					if (wd[5] - time <= 0) {
						waveColor6 = "c";
					}
				}

			} else {
				if (wd.length == 1) {// w1
					if (wd[0] - time <= 0) {
						waveColor1 = "a";
						wave1 = true;
					} else if (wd[0] - time <= ZombiesAddonConfig.waveColorDelays) {
						waveColor1 = "e";
					} else {
						waveColor1 = "8";
						wave1 = false;
					}

				} else if (wd.length == 2) {// w2
					if (wd[0] - time <= 0) {
						waveColor1 = "a";
						wave1 = true;
					} else if (wd[0] - time <= ZombiesAddonConfig.waveColorDelays) {
						waveColor1 = "e";
						return;
					} else {
						waveColor1 = "8";
						wave1 = false;
						wave2 = false;
					}

					if (wd[1] - time <= 0) {
						waveColor1 = "8";
						wave1 = false;
						waveColor2 = "a";
						wave2 = true;
					} else if (wd[1] - time <= ZombiesAddonConfig.waveColorDelays) {
						waveColor1 = "8";
						waveColor2 = "e";
						return;
					} else {
						waveColor2 = "8";
					}

				} else if (wd.length == 3) {// w3
					if (wd[0] - time <= 0) {
						waveColor1 = "a";
						wave1 = true;
					} else if (wd[0] - time <= ZombiesAddonConfig.waveColorDelays) {
						waveColor1 = "e";
						return;
					} else {
						waveColor1 = "8";
						wave1 = false;
						wave2 = false;
						wave3 = false;
					}

					if (wd[1] - time <= 0) {
						waveColor1 = "8";
						wave1 = false;
						waveColor2 = "a";
						wave2 = true;
					} else if (wd[1] - time <= ZombiesAddonConfig.waveColorDelays) {
						waveColor1 = "8";
						waveColor2 = "e";
						return;
					} else {
						waveColor2 = "8";
					}

					if (wd[2] - time <= 0) {
						waveColor2 = "8";
						wave2 = false;
						waveColor3 = "a";
						wave3 = true;
					} else if (wd[2] - time <= ZombiesAddonConfig.waveColorDelays) {
						waveColor2 = "8";
						waveColor3 = "e";
						return;
					} else {
						waveColor3 = "8";
					}

				} else if (wd.length == 4) {// w4
					if (wd[0] - time <= 0) {
						waveColor1 = "a";
						wave1 = true;
					} else if (wd[0] - time <= ZombiesAddonConfig.waveColorDelays) {
						waveColor1 = "e";
						return;
					} else {
						waveColor1 = "8";
						wave1 = false;
						wave2 = false;
						wave3 = false;
						wave4 = false;
					}

					if (wd[1] - time <= 0) {
						waveColor1 = "8";
						wave1 = false;
						waveColor2 = "a";
						wave2 = true;
					} else if (wd[1] - time <= ZombiesAddonConfig.waveColorDelays) {
						waveColor1 = "8";
						waveColor2 = "e";
						return;
					} else {
						waveColor2 = "8";
					}

					if (wd[2] - time <= 0) {
						waveColor2 = "8";
						wave2 = false;
						waveColor3 = "a";
						wave3 = true;
					} else if (wd[2] - time <= ZombiesAddonConfig.waveColorDelays) {
						waveColor2 = "8";
						waveColor3 = "e";
						return;
					} else {
						waveColor3 = "8";
					}

					if (wd[3] - time <= 0) {
						waveColor3 = "8";
						wave3 = false;
						waveColor4 = "a";
						wave4 = true;
					} else if (wd[3] - time <= ZombiesAddonConfig.waveColorDelays) {
						waveColor3 = "8";
						waveColor4 = "e";
						return;
					} else {
						waveColor4 = "8";
					}

				} else if (wd.length == 5) {// w5
					if (wd[0] - time <= 0) {
						waveColor1 = "a";
						wave1 = true;
					} else if (wd[0] - time <= ZombiesAddonConfig.waveColorDelays) {
						waveColor1 = "e";
						return;
					} else {
						waveColor1 = "8";
						wave1 = false;
						wave2 = false;
						wave3 = false;
						wave4 = false;
						wave5 = false;
					}

					if (wd[1] - time <= 0) {
						waveColor1 = "8";
						wave1 = false;
						waveColor2 = "a";
						wave2 = true;
					} else if (wd[1] - time <= ZombiesAddonConfig.waveColorDelays) {
						waveColor1 = "8";
						waveColor2 = "e";
						return;
					} else {
						waveColor2 = "8";
					}

					if (wd[2] - time <= 0) {
						waveColor2 = "8";
						wave2 = false;
						waveColor3 = "a";
						wave3 = true;
					} else if (wd[2] - time <= ZombiesAddonConfig.waveColorDelays) {
						waveColor2 = "8";
						waveColor3 = "e";
						return;
					} else {
						waveColor3 = "8";
					}

					if (wd[3] - time <= 0) {
						waveColor3 = "8";
						wave3 = false;
						waveColor4 = "a";
						wave4 = true;
					} else if (wd[3] - time <= ZombiesAddonConfig.waveColorDelays) {
						waveColor3 = "8";
						waveColor4 = "e";
						return;
					} else {
						waveColor4 = "8";
					}

					if (wd[4] - time <= 0) {
						waveColor4 = "8";
						wave4 = false;
						waveColor5 = "a";
						wave5 = true;
					} else if (wd[4] - time <= ZombiesAddonConfig.waveColorDelays) {
						waveColor4 = "8";
						waveColor5 = "e";
						return;
					} else {
						waveColor5 = "8";
					}

				} else if (wd.length == 6) {// w6
					if (wd[0] - time <= 0) {
						waveColor1 = "a";
						wave1 = true;
					} else if (wd[0] - time <= ZombiesAddonConfig.waveColorDelays) {
						waveColor1 = "e";
						return;
					} else {
						waveColor1 = "8";
						wave1 = false;
						wave2 = false;
						wave3 = false;
						wave4 = false;
						wave5 = false;
						wave6 = false;
					}

					if (wd[1] - time <= 0) {
						waveColor1 = "8";
						wave1 = false;
						waveColor2 = "a";
						wave2 = true;
					} else if (wd[1] - time <= ZombiesAddonConfig.waveColorDelays) {
						waveColor1 = "8";
						waveColor2 = "e";
						return;
					} else {
						waveColor2 = "8";
					}

					if (wd[2] - time <= 0) {
						waveColor2 = "8";
						wave2 = false;
						waveColor3 = "a";
						wave3 = true;
					} else if (wd[2] - time <= ZombiesAddonConfig.waveColorDelays) {
						waveColor2 = "8";
						waveColor3 = "e";
						return;
					} else {
						waveColor3 = "8";
					}

					if (wd[3] - time <= 0) {
						waveColor3 = "8";
						wave3 = false;
						waveColor4 = "a";
						wave4 = true;
					} else if (wd[3] - time <= ZombiesAddonConfig.waveColorDelays) {
						waveColor3 = "8";
						waveColor4 = "e";
						return;
					} else {
						waveColor4 = "8";
					}

					if (wd[4] - time <= 0) {
						waveColor4 = "8";
						wave4 = false;
						waveColor5 = "a";
						wave5 = true;
					} else if (wd[4] - time <= ZombiesAddonConfig.waveColorDelays) {
						waveColor4 = "8";
						waveColor5 = "e";
						return;
					} else {
						waveColor5 = "8";
					}

					if (wd[5] - time <= 0) {
						waveColor5 = "8";
						wave5 = false;
						waveColor6 = "a";
						wave6 = true;
					} else if (wd[5] - time <= ZombiesAddonConfig.waveColorDelays) {
						waveColor5 = "8";
						waveColor6 = "e";
						return;
					} else {
						waveColor6 = "8";
					}
				}
			}
		} catch (Exception e) {
			return;
		}

	}

	public static void waveColor() {
		waveColor1 = "8";
		waveColor2 = "8";
		waveColor3 = "8";
		waveColor4 = "8";
		waveColor5 = "8";
		waveColor6 = "8";

		wave1 = false;
		wave2 = false;
		wave3 = false;
		wave4 = false;
		wave5 = false;
		wave6 = false;
	}
}
