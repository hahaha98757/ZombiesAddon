package kr.hahaha98757.zombiesaddon.listeners;

import java.util.Arrays;

import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.handler.RenderTimerHandler;
import kr.hahaha98757.zombiesaddon.utils.GameDetect;
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
	private static final String WAVE = "\u00A75\u27A4 ";
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

	private static final int DESPAWN_TIME = 300;
	
	private static final int HIGHLIGHT_DELAY_ZOMBIESADDON = 3;
	
	private static final int HIGHLIGHT_DELAY_SST = 2;

	public static int rlmode = -28;

	private static String pattern1IntDot1Int = "\\d{1}\\.\\d";

	public static boolean stop = false;
	public static int round = 0;
	
	public String[][] getWaveDelay(boolean RIP) {
		String[][] DEDelays = new String[29][5];
		DEDelays = new String[][] { { "10.0", "20.0" }, { "10.0", "20.0" }, { "10.0", "20.0", "35.0" },
				{ "10.0", "20.0", "35.0" }, { "10.0", "22.0", "37.0" }, { "10.0", "22.0", "44.0" },
				{ "10.0", "25.0", "47.0" }, { "10.0", "25.0", "50.0" }, { "10.0", "22.0", "38.0" },
				{ "10.0", "24.0", "45.0" }, { "10.0", "25.0", "48.0" }, { "10.0", "25.0", "50.0" },
				{ "10.0", "25.0", "50.0" }, { "10.0", "25.0", "45.0" }, { "10.0", "25.0", "46.0" },
				{ "10.0", "24.0", "47.0" }, { "10.0", "24.0", "47.0" }, { "10.0", "24.0", "47.0" },
				{ "10.0", "24.0", "47.0" }, { "10.0", "24.0", "49.0" }, { "10.0", "23.0", "44.0" },
				{ "10.0", "23.0", "45.0" }, { "10.0", "23.0", "42.0" }, { "10.0", "23.0", "43.0" },
				{ "10.0", "23.0", "43.0" }, { "10.0", "23.0", "36.0" }, { "10.0", "24.0", "44.0" },
				{ "10.0", "24.0", "42.0" }, { "10.0", "24.0", "42.0" }, { "10.0", "24.0", "45.0" } };
		String[][] RIPDelays = new String[29][5];
		RIPDelays = new String[][] { { "10.0", "20.0" }, { "10.0", "20.0" }, { "10.0", "20.0", "35.0" },
				{ "10.0", "20.0", "35.0" }, { "10.0", "22.0", "37.0" }, { "10.0", "22.0", "44.0" },
				{ "10.0", "25.0", "47.0" }, { "10.0", "25.0", "50.0" }, { "10.0", "22.0", "38.0" },
				{ "10.0", "24.0", "45.0", "48.0" }, { "10.0", "25.0", "48.0" }, { "10.0", "25.0", "50.0" },
				{ "10.0", "25.0", "50.0" }, { "10.0", "25.0", "45.0" }, { "10.0", "25.0", "46.0" },
				{ "10.0", "24.0", "47.0" }, { "10.0", "24.0", "47.0" }, { "10.0", "24.0", "47.0" },
				{ "10.0", "24.0", "47.0" }, { "10.0", "24.0", "49.0", "52.0" }, { "10.0", "23.0", "44.0" },
				{ "10.0", "23.0", "45.0" }, { "10.0", "23.0", "42.0" }, { "10.0", "23.0", "43.0" },
				{ "10.0", "23.0", "43.0" }, { "10.0", "23.0", "36.0" }, { "10.0", "24.0", "44.0" },
				{ "10.0", "24.0", "42.0" }, { "10.0", "24.0", "42.0" }, { "10.0", "24.0", "45.0" } };
		String[][] BBDelays = new String[29][5];
		BBDelays = new String[][] { { "10.0", "22.0" }, { "10.0", "22.0" }, { "10.0", "22.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "24.0", "38.0" }, { "10.0", "24.0", "38.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "24.0", "38.0" }, { "10.0", "22.0", "34.0" } };
		String[][] AADelays = new String[104][5];
		AADelays = new String[][] { { "10.0", "13.0", "16.0", "19.0" }, { "10.0", "14.0", "18.0", "22.0" },
				{ "10.0", "13.0", "16.0", "19.0" }, { "10.0", "14.0", "17.0", "21.0", "25.0", "28.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "19.0", "23.0", "28.0", "32.0" },
				{ "10.0", "15.0", "19.0", "23.0", "27.0", "31.0" }, { "10.0", "15.0", "20.0", "25.0", "30.0", "35.0" },
				{ "10.0", "14.0", "19.0", "23.0", "28.0", "32.0" }, { "10.0", "16.0", "22.0", "27.0", "33.0", "38.0" },
				{ "10.0", "16.0", "21.0", "27.0", "32.0", "38.0" }, { "10.0", "16.0", "22.0", "28.0", "34.0", "40.0" },
				{ "10.0", "16.0", "22.0", "28.0", "34.0", "40.0" }, { "10.0", "16.0", "21.0", "26.0", "31.0", "36.0" },
				{ "10.0", "17.0", "24.0", "31.0", "38.0", "46.0" }, { "10.0", "16.0", "22.0", "27.0", "33.0", "38.0" },
				{ "10.0", "14.0", "19.0", "23.0", "28.0", "32.0" }, { "10.0", "14.0", "19.0", "23.0", "28.0", "32.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "15.0", "21.0", "26.0", "31.0", "36.0" },
				{ "10.0", "14.0", "19.0", "23.0", "28.0", "32.0" }, { "10.0", "14.0", "19.0", "23.0", "28.0", "34.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "19.0", "23.0", "28.0", "32.0" },
				{ "10.0" }, { "10.0", "23.0", "36.0" }, { "10.0", "22.0", "34.0" }, { "10.0", "20.0", "30.0" },
				{ "10.0", "24.0", "38.0" }, { "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" },
				{ "10.0", "21.0", "32.0" }, { "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0" }, { "10.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "20.0", "31.0" }, { "10.0", "22.0", "34.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0", "37.0", "45.0" }, { "10.0", "21.0", "32.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "13.0", "22.0", "25.0", "34.0", "37.0" },
				{ "10.0", "22.0", "34.0" }, { "10.0", "22.0", "34.0", "35.0" }, { "10.0", "21.0", "32.0", "35.0" },
				{ "10.0", "20.0", "30.0" }, { "10.0", "20.0", "30.0", "33.0" }, { "10.0", "21.0", "32.0" },
				{ "10.0", "22.0", "34.0", "37.0" }, { "10.0", "20.0", "30.0", "33.0" },
				{ "10.0", "22.0", "34.0", "37.0" }, { "10.0", "22.0", "34.0", "37.0" },
				{ "10.0", "20.0", "32.0", "35.0", "39.0" }, { "10.0", "16.0", "22.0", "28.0", "34.0", "40.0" },
				{ "10.0", "14.0", "18.0" }, { "10.0", "14.0", "18.0" }, { "10.0", "22.0", "34.0", "37.0", "38.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "20.0", "30.0", "33.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" }, { "10.0", "14.0", "18.0", "22.0", "26.0", "30.0" },
				{ "5.0" }, { "5.0" }, { "5.0" }, { "5.0" }, { "5.0" } };

		if (GameDetect.getMap() == 1 && !RIP) {
			return DEDelays;
		} else if (GameDetect.getMap() == 1 && RIP) {
			return RIPDelays;
		} else if (GameDetect.getMap() == 2) {
			return BBDelays;
		} else if (GameDetect.getMap() == 3) {
			return AADelays;
		}
		return null;
	}

	public int[][] getBossWave() {// Bombie: 1, Inferno: 2, The Broodmother, Wither, or Herobrine: 3, Lily and
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

		if (GameDetect.getMap() == 1) {
			if (hard) {
				return DEHardDelays;
			} else if (rip) {
				return DERIPDelays;
			} else {
				return DEDelays;
			}
		} else if (GameDetect.getMap() == 2) {
			if (rip) {
				return BBRIPDelays;
			} else {
				return BBDelays;
			}
		} else if (GameDetect.getMap() == 3) {
			return AADelays;
		}
		return null;
	}

	private double[] getDoubleWaveDelays(String[] stringWaveDelays) {
		try {
			double[] doubleArray = Arrays.stream(stringWaveDelays).mapToDouble(Double::parseDouble).toArray();
			return doubleArray;
		} catch (Exception e) {
			return null;
		}
	}

	private String[][] getStringWaveDelaysWithRLmode(String[][] stringWaveDelays) {
		boolean plus;

		int rl = rlmode;

		try {
			if (rl >= 0) {
				plus = true;
			} else {
				plus = false;
				rl = Math.abs(rl);
			}

			int ds = rl / 2;

			String[][] newWaveDelays = stringWaveDelays;

			for (int i = 0; true; i++) {

				try {
					if (stringWaveDelays[i] == null) {
						break;
					}
				} catch (Exception e) {
					break;
				}

				for (int j = 0; true; j++) {

					try {
						if (stringWaveDelays[i][j] == null) {
							break;
						}
					} catch (Exception e) {
						break;
					}

					String str = String
							.valueOf(Integer.valueOf(stringWaveDelays[i][j].replaceAll("\\.", "")) + (plus ? ds : -ds));

					if (str.startsWith("-")) {
						str = "0";
					}

					if (str.length() > 1) {// 소수점 추가
						str = str.substring(0, str.length() - 1) + "." + str.charAt(str.length() - 1);
					} else if (str.length() == 1) {
						str = "0." + str;
					} else {
						str = "0.0";
					}

					newWaveDelays[i][j] = str;

				}
			}
			return newWaveDelays;
		} catch (Exception e) {
			return stringWaveDelays;
		}
	}

	@SubscribeEvent
	public void onRender(RenderGameOverlayEvent.Post event) {
		if (!ZombiesAddonConfig.enableMod) {
			return;
		}

		if (event.type != ElementType.EXPERIENCE) {
			return;
		}

		if (!ZombiesAddonConfig.toggleWaveDelays) {
			return;
		}
		
		if (!GameDetect.isZombiesGame()) {
			stop = false;
			return;
		}
		
		if (!stop) {
			if (GameDetect.getMap() == 0 || GameDetect.getRound() == 0) {
				return;
			}

			round = GameDetect.getRound() - 1;
		}

		if ((GameDetect.getMap() == 1 && (round == 14 || round == 24 || round == 29)
				&& GameDetect.getDifficult(GameDetect.getMap(), round) == 2) || (hard)) {
			hard = true;
		} else {
			hard = false;
		}
		if ((GameDetect.getMap() == 1 && (round == 4 || round == 9 || round == 14 || round == 19 || round == 24 || round == 29)
				&& GameDetect.getDifficult(GameDetect.getMap(), round) == 3) || rip) {
			rip = true;
		} else {
			rip = false;
		}
		if ((GameDetect.getMap() == 2 && (round == 14 || round == 19 || round == 24 || round == 29)
				&& GameDetect.getDifficult(GameDetect.getMap(), round) == 3) || rip) {
			rip = true;
		} else {
			rip = false;
		}
		if (!(round == 4 || round == 9 || round == 14 || round == 19 || round == 24 || round == 29)) {
			hard = false;
			rip = false;
		}

		String[][] strArray = getWaveDelay(rip);
		String[][] orgStrArray = getWaveDelay(rip);

		int[][] bossWave = getBossWave();

		if (!ZombiesAddonConfig.bossWaveAlarm) {
			bossWave = new int[][] { { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
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

		if (strArray == null) {
			return;
		}

		try {
			if (EventListener.rlmode) {
				strArray = getStringWaveDelaysWithRLmode(strArray);
			}

			String min;
			String sec;
			String str;
			FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
			int stringHeight = fr.FONT_HEIGHT;
			int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
			int y = screenHeight - stringHeight;

			if (strArray[round].length == 1) {// w1
				if (bossWave[round][0] == 0) {
					bossesWave1 = "";
				} else if (bossWave[round][0] == 1) {
					bossesWave1 = "\u00A76";
				} else if (bossWave[round][0] == 2) {
					bossesWave1 = "\u00A7c";
				} else if (bossWave[round][0] == 3) {
					bossesWave1 = "\u00A75";
				} else if (bossWave[round][0] == 4) {
					bossesWave1 = "\u00A7c";
				} else if (bossWave[round][0] == 5) {
					bossesWave1 = "\u00A7a";
				} else if (bossWave[round][0] == 6) {
					bossesWave1 = "\u00A73";
				} else if (bossWave[round][0] == 7) {
					bossesWave1 = "\u00A74";
				} else if (bossWave[round][0] == 8) {
					bossesWave1 = "\u00A73";
				} else if (bossWave[round][0] == 9) {
					bossesWave1 = "\u00A70";
				}

				min = String.valueOf(Integer.valueOf(strArray[round][0].split("\\.")[0]) / 60);
				sec = String.valueOf(Integer.valueOf(strArray[round][0].split("\\.")[0]) % 60) + "."
						+ strArray[round][0].split("\\.")[1];
				
				if (ZombiesAddonConfig.textStyle.equals("Zombies Addon")) {
					str = (wave1 ? WAVE : "") + "\u00A7" + waveColor1 + "W1 " + bossesWave1 + min + ":"
							+ (bossWave[round][0] == 4 ? "\u00A7b" : (bossWave[round][0] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec;
				} else {
					str = (wave1 ? WAVE : "") + "\u00A7" + waveColor1 + "W1 " + bossesWave1 + "0" + min + ":"
							+ (bossWave[round][0] == 4 ? "\u00A7b" : (bossWave[round][0] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec.split("\\.")[0];
				}
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 10, 0);

			} else if (strArray[round].length == 2) {// w2
				if (bossWave[round][1] == 0) {
					bossesWave2 = "";
				} else if (bossWave[round][1] == 1) {
					bossesWave2 = "\u00A76";
				} else if (bossWave[round][1] == 2) {
					bossesWave2 = "\u00A7c";
				} else if (bossWave[round][1] == 3) {
					bossesWave2 = "\u00A75";
				} else if (bossWave[round][1] == 4) {
					bossesWave2 = "\u00A7c";
				} else if (bossWave[round][1] == 5) {
					bossesWave2 = "\u00A7a";
				} else if (bossWave[round][1] == 6) {
					bossesWave2 = "\u00A73";
				} else if (bossWave[round][1] == 7) {
					bossesWave2 = "\u00A74";
				} else if (bossWave[round][1] == 8) {
					bossesWave2 = "\u00A73";
				} else if (bossWave[round][1] == 9) {
					bossesWave2 = "\u00A70";
				}

				min = String.valueOf(Integer.valueOf(strArray[round][1].split("\\.")[0]) / 60);
				sec = String.valueOf(Integer.valueOf(strArray[round][1].split("\\.")[0]) % 60) + "."
						+ strArray[round][1].split("\\.")[1];
				
				if (ZombiesAddonConfig.textStyle.equals("Zombies Addon")) {
					str = (wave2 ? WAVE : "") + "\u00A7" + waveColor2 + "W2 " + bossesWave2 + min + ":"
							+ (bossWave[round][1] == 4 ? "\u00A7b" : (bossWave[round][1] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec;
				} else {
					str = (wave2 ? WAVE : "") + "\u00A7" + waveColor2 + "W2 " + bossesWave2 + "0" + min + ":"
							+ (bossWave[round][1] == 4 ? "\u00A7b" : (bossWave[round][1] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec.split("\\.")[0];
				}
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 10, 0);

				if (bossWave[round][0] == 0) {
					bossesWave1 = "";
				} else if (bossWave[round][0] == 1) {
					bossesWave1 = "\u00A76";
				} else if (bossWave[round][0] == 2) {
					bossesWave1 = "\u00A7c";
				} else if (bossWave[round][0] == 3) {
					bossesWave1 = "\u00A75";
				} else if (bossWave[round][0] == 4) {
					bossesWave1 = "\u00A7c";
				} else if (bossWave[round][0] == 5) {
					bossesWave1 = "\u00A7a";
				} else if (bossWave[round][0] == 6) {
					bossesWave1 = "\u00A73";
				} else if (bossWave[round][0] == 7) {
					bossesWave1 = "\u00A74";
				} else if (bossWave[round][0] == 8) {
					bossesWave1 = "\u00A73";
				} else if (bossWave[round][0] == 9) {
					bossesWave1 = "\u00A70";
				}

				min = String.valueOf(Integer.valueOf(strArray[round][0].split("\\.")[0]) / 60);
				sec = String.valueOf(Integer.valueOf(strArray[round][0].split("\\.")[0]) % 60) + "."
						+ strArray[round][0].split("\\.")[1];
				
				if (ZombiesAddonConfig.textStyle.equals("Zombies Addon")) {
					str = (wave1 ? WAVE : "") + "\u00A7" + waveColor1 + "W1 " + bossesWave1 + min + ":"
							+ (bossWave[round][0] == 4 ? "\u00A7b" : (bossWave[round][0] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec;
				} else {
					str = (wave1 ? WAVE : "") + "\u00A7" + waveColor1 + "W1 " + bossesWave1 + "0" + min + ":"
							+ (bossWave[round][0] == 4 ? "\u00A7b" : (bossWave[round][0] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec.split("\\.")[0];
				}
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 18, 0);

			} else if (strArray[round].length == 3) {// w3
				if (bossWave[round][2] == 0) {
					bossesWave3 = "";
				} else if (bossWave[round][2] == 1) {
					bossesWave3 = "\u00A76";
				} else if (bossWave[round][2] == 2) {
					bossesWave3 = "\u00A7c";
				} else if (bossWave[round][2] == 3) {
					bossesWave3 = "\u00A75";
				} else if (bossWave[round][2] == 4) {
					bossesWave3 = "\u00A7c";
				} else if (bossWave[round][2] == 5) {
					bossesWave3 = "\u00A7a";
				} else if (bossWave[round][2] == 6) {
					bossesWave3 = "\u00A73";
				} else if (bossWave[round][2] == 7) {
					bossesWave3 = "\u00A74";
				} else if (bossWave[round][2] == 8) {
					bossesWave3 = "\u00A73";
				} else if (bossWave[round][2] == 9) {
					bossesWave3 = "\u00A70";
				}

				min = String.valueOf(Integer.valueOf(strArray[round][2].split("\\.")[0]) / 60);
				sec = String.valueOf(Integer.valueOf(strArray[round][2].split("\\.")[0]) % 60) + "."
						+ strArray[round][2].split("\\.")[1];
				
				if (ZombiesAddonConfig.textStyle.equals("Zombies Addon")) {
					str = (wave3 ? WAVE : "") + "\u00A7" + waveColor3 + "W3 " + bossesWave3 + min + ":"
							+ (bossWave[round][2] == 4 ? "\u00A7b" : (bossWave[round][2] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec;
				} else {
					str = (wave3 ? WAVE : "") + "\u00A7" + waveColor3 + "W3 " + bossesWave3 + "0" + min + ":"
							+ (bossWave[round][2] == 4 ? "\u00A7b" : (bossWave[round][2] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec.split("\\.")[0];
				}
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 10, 0);

				if (bossWave[round][1] == 0) {
					bossesWave2 = "";
				} else if (bossWave[round][1] == 1) {
					bossesWave2 = "\u00A76";
				} else if (bossWave[round][1] == 2) {
					bossesWave2 = "\u00A7c";
				} else if (bossWave[round][1] == 3) {
					bossesWave2 = "\u00A75";
				} else if (bossWave[round][1] == 4) {
					bossesWave2 = "\u00A7c";
				} else if (bossWave[round][1] == 5) {
					bossesWave2 = "\u00A7a";
				} else if (bossWave[round][1] == 6) {
					bossesWave2 = "\u00A73";
				} else if (bossWave[round][1] == 7) {
					bossesWave2 = "\u00A74";
				} else if (bossWave[round][1] == 8) {
					bossesWave2 = "\u00A73";
				} else if (bossWave[round][1] == 9) {
					bossesWave2 = "\u00A70";
				}

				min = String.valueOf(Integer.valueOf(strArray[round][1].split("\\.")[0]) / 60);
				sec = String.valueOf(Integer.valueOf(strArray[round][1].split("\\.")[0]) % 60) + "."
						+ strArray[round][1].split("\\.")[1];

				if (ZombiesAddonConfig.textStyle.equals("Zombies Addon")) {
					str = (wave2 ? WAVE : "") + "\u00A7" + waveColor2 + "W2 " + bossesWave2 + min + ":"
							+ (bossWave[round][1] == 4 ? "\u00A7b" : (bossWave[round][1] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec;
				} else {
					str = (wave2 ? WAVE : "") + "\u00A7" + waveColor2 + "W2 " + bossesWave2 + "0" + min + ":"
							+ (bossWave[round][1] == 4 ? "\u00A7b" : (bossWave[round][1] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec.split("\\.")[0];
				}
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 18, 0);

				if (bossWave[round][0] == 0) {
					bossesWave1 = "";
				} else if (bossWave[round][0] == 1) {
					bossesWave1 = "\u00A76";
				} else if (bossWave[round][0] == 2) {
					bossesWave1 = "\u00A7c";
				} else if (bossWave[round][0] == 3) {
					bossesWave1 = "\u00A75";
				} else if (bossWave[round][0] == 4) {
					bossesWave1 = "\u00A7c";
				} else if (bossWave[round][0] == 5) {
					bossesWave1 = "\u00A7a";
				} else if (bossWave[round][0] == 6) {
					bossesWave1 = "\u00A73";
				} else if (bossWave[round][0] == 7) {
					bossesWave1 = "\u00A74";
				} else if (bossWave[round][0] == 8) {
					bossesWave1 = "\u00A73";
				} else if (bossWave[round][0] == 9) {
					bossesWave1 = "\u00A70";
				}

				min = String.valueOf(Integer.valueOf(strArray[round][0].split("\\.")[0]) / 60);
				sec = String.valueOf(Integer.valueOf(strArray[round][0].split("\\.")[0]) % 60) + "."
						+ strArray[round][0].split("\\.")[1];

				if (ZombiesAddonConfig.textStyle.equals("Zombies Addon")) {
					str = (wave1 ? WAVE : "") + "\u00A7" + waveColor1 + "W1 " + bossesWave1 + min + ":"
							+ (bossWave[round][0] == 4 ? "\u00A7b" : (bossWave[round][0] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec;
				} else {
					str = (wave1 ? WAVE : "") + "\u00A7" + waveColor1 + "W1 " + bossesWave1 + "0" + min + ":"
							+ (bossWave[round][0] == 4 ? "\u00A7b" : (bossWave[round][0] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec.split("\\.")[0];
				}
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 26, 0);

			} else if (strArray[round].length == 4) {// w4
				if (bossWave[round][3] == 0) {
					bossesWave4 = "";
				} else if (bossWave[round][3] == 1) {
					bossesWave4 = "\u00A76";
				} else if (bossWave[round][3] == 2) {
					bossesWave4 = "\u00A7c";
				} else if (bossWave[round][3] == 3) {
					bossesWave4 = "\u00A75";
				} else if (bossWave[round][3] == 4) {
					bossesWave4 = "\u00A7c";
				} else if (bossWave[round][3] == 5) {
					bossesWave4 = "\u00A7a";
				} else if (bossWave[round][3] == 6) {
					bossesWave4 = "\u00A73";
				} else if (bossWave[round][3] == 7) {
					bossesWave4 = "\u00A74";
				} else if (bossWave[round][3] == 8) {
					bossesWave4 = "\u00A73";
				} else if (bossWave[round][3] == 9) {
					bossesWave4 = "\u00A70";
				}

				min = String.valueOf(Integer.valueOf(strArray[round][3].split("\\.")[0]) / 60);
				sec = String.valueOf(Integer.valueOf(strArray[round][3].split("\\.")[0]) % 60) + "."
						+ strArray[round][3].split("\\.")[1];

				if (ZombiesAddonConfig.textStyle.equals("Zombies Addon")) {
					str = (wave4 ? WAVE : "") + "\u00A7" + waveColor4 + "W4 " + bossesWave4 + min + ":"
							+ (bossWave[round][3] == 4 ? "\u00A7b" : (bossWave[round][3] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec;
				} else {
					str = (wave4 ? WAVE : "") + "\u00A7" + waveColor4 + "W4 " + bossesWave4 + "0" + min + ":"
							+ (bossWave[round][3] == 4 ? "\u00A7b" : (bossWave[round][3] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec.split("\\.")[0];
				}
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 10, 0);

				if (bossWave[round][2] == 0) {
					bossesWave3 = "";
				} else if (bossWave[round][2] == 1) {
					bossesWave3 = "\u00A76";
				} else if (bossWave[round][2] == 2) {
					bossesWave3 = "\u00A7c";
				} else if (bossWave[round][2] == 3) {
					bossesWave3 = "\u00A75";
				} else if (bossWave[round][2] == 4) {
					bossesWave3 = "\u00A7c";
				} else if (bossWave[round][2] == 5) {
					bossesWave3 = "\u00A7a";
				} else if (bossWave[round][2] == 6) {
					bossesWave3 = "\u00A73";
				} else if (bossWave[round][2] == 7) {
					bossesWave3 = "\u00A74";
				} else if (bossWave[round][2] == 8) {
					bossesWave3 = "\u00A73";
				} else if (bossWave[round][2] == 9) {
					bossesWave3 = "\u00A70";
				}

				min = String.valueOf(Integer.valueOf(strArray[round][2].split("\\.")[0]) / 60);
				sec = String.valueOf(Integer.valueOf(strArray[round][2].split("\\.")[0]) % 60) + "."
						+ strArray[round][2].split("\\.")[1];

				if (ZombiesAddonConfig.textStyle.equals("Zombies Addon")) {
					str = (wave3 ? WAVE : "") + "\u00A7" + waveColor3 + "W3 " + bossesWave3 + min + ":"
							+ (bossWave[round][2] == 4 ? "\u00A7b" : (bossWave[round][2] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec;
				} else {
					str = (wave3 ? WAVE : "") + "\u00A7" + waveColor3 + "W3 " + bossesWave3 + "0" + min + ":"
							+ (bossWave[round][2] == 4 ? "\u00A7b" : (bossWave[round][2] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec.split("\\.")[0];
				}
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 18, 0);

				if (bossWave[round][1] == 0) {
					bossesWave2 = "";
				} else if (bossWave[round][1] == 1) {
					bossesWave2 = "\u00A76";
				} else if (bossWave[round][1] == 2) {
					bossesWave2 = "\u00A7c";
				} else if (bossWave[round][1] == 3) {
					bossesWave2 = "\u00A75";
				} else if (bossWave[round][1] == 4) {
					bossesWave2 = "\u00A7c";
				} else if (bossWave[round][1] == 5) {
					bossesWave2 = "\u00A7a";
				} else if (bossWave[round][1] == 6) {
					bossesWave2 = "\u00A73";
				} else if (bossWave[round][1] == 7) {
					bossesWave2 = "\u00A74";
				} else if (bossWave[round][1] == 8) {
					bossesWave2 = "\u00A73";
				} else if (bossWave[round][1] == 9) {
					bossesWave2 = "\u00A70";
				}

				min = String.valueOf(Integer.valueOf(strArray[round][1].split("\\.")[0]) / 60);
				sec = String.valueOf(Integer.valueOf(strArray[round][1].split("\\.")[0]) % 60) + "."
						+ strArray[round][1].split("\\.")[1];

				if (ZombiesAddonConfig.textStyle.equals("Zombies Addon")) {
					str = (wave2 ? WAVE : "") + "\u00A7" + waveColor2 + "W2 " + bossesWave2 + min + ":"
							+ (bossWave[round][1] == 4 ? "\u00A7b" : (bossWave[round][1] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec;
				} else {
					str = (wave2 ? WAVE : "") + "\u00A7" + waveColor2 + "W2 " + bossesWave2 + "0" + min + ":"
							+ (bossWave[round][1] == 4 ? "\u00A7b" : (bossWave[round][1] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec.split("\\.")[0];
				}
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 26, 0);

				if (bossWave[round][0] == 0) {
					bossesWave1 = "";
				} else if (bossWave[round][0] == 1) {
					bossesWave1 = "\u00A76";
				} else if (bossWave[round][0] == 2) {
					bossesWave1 = "\u00A7c";
				} else if (bossWave[round][0] == 3) {
					bossesWave1 = "\u00A75";
				} else if (bossWave[round][0] == 4) {
					bossesWave1 = "\u00A7c";
				} else if (bossWave[round][0] == 5) {
					bossesWave1 = "\u00A7a";
				} else if (bossWave[round][0] == 6) {
					bossesWave1 = "\u00A73";
				} else if (bossWave[round][0] == 7) {
					bossesWave1 = "\u00A74";
				} else if (bossWave[round][0] == 8) {
					bossesWave1 = "\u00A73";
				} else if (bossWave[round][0] == 9) {
					bossesWave1 = "\u00A70";
				}

				min = String.valueOf(Integer.valueOf(strArray[round][0].split("\\.")[0]) / 60);
				sec = String.valueOf(Integer.valueOf(strArray[round][0].split("\\.")[0]) % 60) + "."
						+ strArray[round][0].split("\\.")[1];

				if (ZombiesAddonConfig.textStyle.equals("Zombies Addon")) {
					str = (wave1 ? WAVE : "") + "\u00A7" + waveColor1 + "W1 " + bossesWave1 + min + ":"
							+ (bossWave[round][0] == 4 ? "\u00A7b" : (bossWave[round][0] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec;
				} else {
					str = (wave1 ? WAVE : "") + "\u00A7" + waveColor1 + "W1 " + bossesWave1 + "0" + min + ":"
							+ (bossWave[round][0] == 4 ? "\u00A7b" : (bossWave[round][0] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec.split("\\.")[0];
				}
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 34, 0);
				
			} else if (strArray[round].length == 5) {// w5
				if (bossWave[round][4] == 0) {
					bossesWave5 = "";
				} else if (bossWave[round][4] == 1) {
					bossesWave5 = "\u00A76";
				} else if (bossWave[round][4] == 2) {
					bossesWave5 = "\u00A7c";
				} else if (bossWave[round][4] == 3) {
					bossesWave5 = "\u00A75";
				} else if (bossWave[round][4] == 4) {
					bossesWave5 = "\u00A7c";
				} else if (bossWave[round][4] == 5) {
					bossesWave5 = "\u00A7a";
				} else if (bossWave[round][4] == 6) {
					bossesWave5 = "\u00A73";
				} else if (bossWave[round][4] == 7) {
					bossesWave5 = "\u00A74";
				} else if (bossWave[round][4] == 8) {
					bossesWave5 = "\u00A73";
				} else if (bossWave[round][4] == 9) {
					bossesWave5 = "\u00A70";
				}

				min = String.valueOf(Integer.valueOf(strArray[round][4].split("\\.")[0]) / 60);
				sec = String.valueOf(Integer.valueOf(strArray[round][4].split("\\.")[0]) % 60) + "."
						+ strArray[round][4].split("\\.")[1];
				
				if (ZombiesAddonConfig.textStyle.equals("Zombies Addon")) {
					str = (wave5 ? WAVE : "") + "\u00A7" + waveColor5 + "W5 " + bossesWave5 + min + ":"
							+ (bossWave[round][4] == 4 ? "\u00A7b" : (bossWave[round][4] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec;
				} else {
					str = (wave5 ? WAVE : "") + "\u00A7" + waveColor5 + "W5 " + bossesWave5 + "0" + min + ":"
							+ (bossWave[round][4] == 4 ? "\u00A7b" : (bossWave[round][4] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec.split("\\.")[0];
				}
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 10, 0);

				if (bossWave[round][3] == 0) {
					bossesWave4 = "";
				} else if (bossWave[round][3] == 1) {
					bossesWave4 = "\u00A76";
				} else if (bossWave[round][3] == 2) {
					bossesWave4 = "\u00A7c";
				} else if (bossWave[round][3] == 3) {
					bossesWave4 = "\u00A75";
				} else if (bossWave[round][3] == 4) {
					bossesWave4 = "\u00A7c";
				} else if (bossWave[round][3] == 5) {
					bossesWave4 = "\u00A7a";
				} else if (bossWave[round][3] == 6) {
					bossesWave4 = "\u00A73";
				} else if (bossWave[round][3] == 7) {
					bossesWave4 = "\u00A74";
				} else if (bossWave[round][3] == 8) {
					bossesWave4 = "\u00A73";
				} else if (bossWave[round][3] == 9) {
					bossesWave4 = "\u00A70";
				}

				min = String.valueOf(Integer.valueOf(strArray[round][3].split("\\.")[0]) / 60);
				sec = String.valueOf(Integer.valueOf(strArray[round][3].split("\\.")[0]) % 60) + "."
						+ strArray[round][3].split("\\.")[1];

				if (ZombiesAddonConfig.textStyle.equals("Zombies Addon")) {
					str = (wave4 ? WAVE : "") + "\u00A7" + waveColor4 + "W4 " + bossesWave4 + min + ":"
							+ (bossWave[round][3] == 4 ? "\u00A7b" : (bossWave[round][3] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec;
				} else {
					str = (wave4 ? WAVE : "") + "\u00A7" + waveColor4 + "W4 " + bossesWave4 + "0" + min + ":"
							+ (bossWave[round][3] == 4 ? "\u00A7b" : (bossWave[round][3] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec.split("\\.")[0];
				}
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 18, 0);

				if (bossWave[round][2] == 0) {
					bossesWave3 = "";
				} else if (bossWave[round][2] == 1) {
					bossesWave3 = "\u00A76";
				} else if (bossWave[round][2] == 2) {
					bossesWave3 = "\u00A7c";
				} else if (bossWave[round][2] == 3) {
					bossesWave3 = "\u00A75";
				} else if (bossWave[round][2] == 4) {
					bossesWave3 = "\u00A7c";
				} else if (bossWave[round][2] == 5) {
					bossesWave3 = "\u00A7a";
				} else if (bossWave[round][2] == 6) {
					bossesWave3 = "\u00A73";
				} else if (bossWave[round][2] == 7) {
					bossesWave3 = "\u00A74";
				} else if (bossWave[round][2] == 8) {
					bossesWave3 = "\u00A73";
				} else if (bossWave[round][2] == 9) {
					bossesWave3 = "\u00A70";
				}

				min = String.valueOf(Integer.valueOf(strArray[round][2].split("\\.")[0]) / 60);
				sec = String.valueOf(Integer.valueOf(strArray[round][2].split("\\.")[0]) % 60) + "."
						+ strArray[round][2].split("\\.")[1];

				if (ZombiesAddonConfig.textStyle.equals("Zombies Addon")) {
					str = (wave3 ? WAVE : "") + "\u00A7" + waveColor3 + "W3 " + bossesWave3 + min + ":"
							+ (bossWave[round][2] == 4 ? "\u00A7b" : (bossWave[round][2] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec;
				} else {
					str = (wave3 ? WAVE : "") + "\u00A7" + waveColor3 + "W3 " + bossesWave3 + "0" + min + ":"
							+ (bossWave[round][2] == 4 ? "\u00A7b" : (bossWave[round][2] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec.split("\\.")[0];
				}
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 26, 0);

				if (bossWave[round][1] == 0) {
					bossesWave2 = "";
				} else if (bossWave[round][1] == 1) {
					bossesWave2 = "\u00A76";
				} else if (bossWave[round][1] == 2) {
					bossesWave2 = "\u00A7c";
				} else if (bossWave[round][1] == 3) {
					bossesWave2 = "\u00A75";
				} else if (bossWave[round][1] == 4) {
					bossesWave2 = "\u00A7c";
				} else if (bossWave[round][1] == 5) {
					bossesWave2 = "\u00A7a";
				} else if (bossWave[round][1] == 6) {
					bossesWave2 = "\u00A73";
				} else if (bossWave[round][1] == 7) {
					bossesWave2 = "\u00A74";
				} else if (bossWave[round][1] == 8) {
					bossesWave2 = "\u00A73";
				} else if (bossWave[round][1] == 9) {
					bossesWave2 = "\u00A70";
				}

				min = String.valueOf(Integer.valueOf(strArray[round][1].split("\\.")[0]) / 60);
				sec = String.valueOf(Integer.valueOf(strArray[round][1].split("\\.")[0]) % 60) + "."
						+ strArray[round][1].split("\\.")[1];

				if (ZombiesAddonConfig.textStyle.equals("Zombies Addon")) {
					str = (wave2 ? WAVE : "") + "\u00A7" + waveColor2 + "W2 " + bossesWave2 + min + ":"
							+ (bossWave[round][1] == 4 ? "\u00A7b" : (bossWave[round][1] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec;
				} else {
					str = (wave2 ? WAVE : "") + "\u00A7" + waveColor2 + "W2 " + bossesWave2 + "0" + min + ":"
							+ (bossWave[round][1] == 4 ? "\u00A7b" : (bossWave[round][1] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec.split("\\.")[0];
				}
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 34, 0);

				if (bossWave[round][0] == 0) {
					bossesWave1 = "";
				} else if (bossWave[round][0] == 1) {
					bossesWave1 = "\u00A76";
				} else if (bossWave[round][0] == 2) {
					bossesWave1 = "\u00A7c";
				} else if (bossWave[round][0] == 3) {
					bossesWave1 = "\u00A75";
				} else if (bossWave[round][0] == 4) {
					bossesWave1 = "\u00A7c";
				} else if (bossWave[round][0] == 5) {
					bossesWave1 = "\u00A7a";
				} else if (bossWave[round][0] == 6) {
					bossesWave1 = "\u00A73";
				} else if (bossWave[round][0] == 7) {
					bossesWave1 = "\u00A74";
				} else if (bossWave[round][0] == 8) {
					bossesWave1 = "\u00A73";
				} else if (bossWave[round][0] == 9) {
					bossesWave1 = "\u00A70";
				}

				min = String.valueOf(Integer.valueOf(strArray[round][0].split("\\.")[0]) / 60);
				sec = String.valueOf(Integer.valueOf(strArray[round][0].split("\\.")[0]) % 60) + "."
						+ strArray[round][0].split("\\.")[1];

				if (ZombiesAddonConfig.textStyle.equals("Zombies Addon")) {
					str = (wave1 ? WAVE : "") + "\u00A7" + waveColor1 + "W1 " + bossesWave1 + min + ":"
							+ (bossWave[round][0] == 4 ? "\u00A7b" : (bossWave[round][0] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec;
				} else {
					str = (wave1 ? WAVE : "") + "\u00A7" + waveColor1 + "W1 " + bossesWave1 + "0" + min + ":"
							+ (bossWave[round][0] == 4 ? "\u00A7b" : (bossWave[round][0] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec.split("\\.")[0];
				}
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 42, 0);

			} else if (strArray[round].length == 6) {// w6
				if (bossWave[round][5] == 0) {
					bossesWave6 = "";
				} else if (bossWave[round][5] == 1) {
					bossesWave6 = "\u00A76";
				} else if (bossWave[round][5] == 2) {
					bossesWave6 = "\u00A7c";
				} else if (bossWave[round][5] == 3) {
					bossesWave6 = "\u00A75";
				} else if (bossWave[round][5] == 4) {
					bossesWave6 = "\u00A7c";
				} else if (bossWave[round][5] == 5) {
					bossesWave6 = "\u00A7a";
				} else if (bossWave[round][5] == 6) {
					bossesWave6 = "\u00A73";
				} else if (bossWave[round][5] == 7) {
					bossesWave6 = "\u00A74";
				} else if (bossWave[round][5] == 8) {
					bossesWave6 = "\u00A73";
				} else if (bossWave[round][5] == 9) {
					bossesWave6 = "\u00A70";
				}
				
				min = String.valueOf(Integer.valueOf(strArray[round][5].split("\\.")[0]) / 60);
				sec = String.valueOf(Integer.valueOf(strArray[round][5].split("\\.")[0]) % 60) + "."
						+ strArray[round][5].split("\\.")[1];
				
				if (ZombiesAddonConfig.textStyle.equals("Zombies Addon")) {
					str = (wave6 ? WAVE : "") + "\u00A7" + waveColor6 + "W6 " + bossesWave6 + min + ":"
							+ (bossWave[round][5] == 4 ? "\u00A7b" : (bossWave[round][5] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec;
				} else {
					str = (wave6 ? WAVE : "") + "\u00A7" + waveColor6 + "W6 " + bossesWave6 + "0" + min + ":"
							+ (bossWave[round][5] == 4 ? "\u00A7b" : (bossWave[round][5] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec.split("\\.")[0];
				}
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 10, 0);

				if (bossWave[round][4] == 0) {
					bossesWave5 = "";
				} else if (bossWave[round][4] == 1) {
					bossesWave5 = "\u00A76";
				} else if (bossWave[round][4] == 2) {
					bossesWave5 = "\u00A7c";
				} else if (bossWave[round][4] == 3) {
					bossesWave5 = "\u00A75";
				} else if (bossWave[round][4] == 4) {
					bossesWave5 = "\u00A7c";
				} else if (bossWave[round][4] == 5) {
					bossesWave5 = "\u00A7a";
				} else if (bossWave[round][4] == 6) {
					bossesWave5 = "\u00A73";
				} else if (bossWave[round][4] == 7) {
					bossesWave5 = "\u00A74";
				} else if (bossWave[round][4] == 8) {
					bossesWave5 = "\u00A73";
				} else if (bossWave[round][4] == 9) {
					bossesWave5 = "\u00A70";
				}

				min = String.valueOf(Integer.valueOf(strArray[round][4].split("\\.")[0]) / 60);
				sec = String.valueOf(Integer.valueOf(strArray[round][4].split("\\.")[0]) % 60) + "."
						+ strArray[round][4].split("\\.")[1];
				
				if (ZombiesAddonConfig.textStyle.equals("Zombies Addon")) {
					str = (wave5 ? WAVE : "") + "\u00A7" + waveColor5 + "W5 " + bossesWave5 + min + ":"
							+ (bossWave[round][4] == 4 ? "\u00A7b" : (bossWave[round][4] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec;
				} else {
					str = (wave5 ? WAVE : "") + "\u00A7" + waveColor5 + "W5 " + bossesWave5 + "0" + min + ":"
							+ (bossWave[round][4] == 4 ? "\u00A7b" : (bossWave[round][4] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec.split("\\.")[0];
				}
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 18, 0);

				if (bossWave[round][3] == 0) {
					bossesWave4 = "";
				} else if (bossWave[round][3] == 1) {
					bossesWave4 = "\u00A76";
				} else if (bossWave[round][3] == 2) {
					bossesWave4 = "\u00A7c";
				} else if (bossWave[round][3] == 3) {
					bossesWave4 = "\u00A75";
				} else if (bossWave[round][3] == 4) {
					bossesWave4 = "\u00A7c";
				} else if (bossWave[round][3] == 5) {
					bossesWave4 = "\u00A7a";
				} else if (bossWave[round][3] == 6) {
					bossesWave4 = "\u00A73";
				} else if (bossWave[round][3] == 7) {
					bossesWave4 = "\u00A74";
				} else if (bossWave[round][3] == 8) {
					bossesWave4 = "\u00A73";
				} else if (bossWave[round][3] == 9) {
					bossesWave4 = "\u00A70";
				}

				min = String.valueOf(Integer.valueOf(strArray[round][3].split("\\.")[0]) / 60);
				sec = String.valueOf(Integer.valueOf(strArray[round][3].split("\\.")[0]) % 60) + "."
						+ strArray[round][3].split("\\.")[1];
				
				if (ZombiesAddonConfig.textStyle.equals("Zombies Addon")) {
					str = (wave4 ? WAVE : "") + "\u00A7" + waveColor4 + "W4 " + bossesWave4 + min + ":"
							+ (bossWave[round][3] == 4 ? "\u00A7b" : (bossWave[round][3] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec;
				} else {
					str = (wave4 ? WAVE : "") + "\u00A7" + waveColor4 + "W4 " + bossesWave4 + "0" + min + ":"
							+ (bossWave[round][3] == 4 ? "\u00A7b" : (bossWave[round][3] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec.split("\\.")[0];
				}
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 26, 0);

				if (bossWave[round][2] == 0) {
					bossesWave3 = "";
				} else if (bossWave[round][2] == 1) {
					bossesWave3 = "\u00A76";
				} else if (bossWave[round][2] == 2) {
					bossesWave3 = "\u00A7c";
				} else if (bossWave[round][2] == 3) {
					bossesWave3 = "\u00A75";
				} else if (bossWave[round][2] == 4) {
					bossesWave3 = "\u00A7c";
				} else if (bossWave[round][2] == 5) {
					bossesWave3 = "\u00A7a";
				} else if (bossWave[round][2] == 6) {
					bossesWave3 = "\u00A73";
				} else if (bossWave[round][2] == 7) {
					bossesWave3 = "\u00A74";
				} else if (bossWave[round][2] == 8) {
					bossesWave3 = "\u00A73";
				} else if (bossWave[round][2] == 9) {
					bossesWave3 = "\u00A70";
				}

				min = String.valueOf(Integer.valueOf(strArray[round][2].split("\\.")[0]) / 60);
				sec = String.valueOf(Integer.valueOf(strArray[round][2].split("\\.")[0]) % 60) + "."
						+ strArray[round][2].split("\\.")[1];
				
				if (ZombiesAddonConfig.textStyle.equals("Zombies Addon")) {
					str = (wave3 ? WAVE : "") + "\u00A7" + waveColor3 + "W3 " + bossesWave3 + min + ":"
							+ (bossWave[round][2] == 4 ? "\u00A7b" : (bossWave[round][2] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec;
				} else {
					str = (wave3 ? WAVE : "") + "\u00A7" + waveColor3 + "W3 " + bossesWave3 + "0" + min + ":"
							+ (bossWave[round][2] == 4 ? "\u00A7b" : (bossWave[round][2] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec.split("\\.")[0];
				}
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 34, 0);

				if (bossWave[round][1] == 0) {
					bossesWave2 = "";
				} else if (bossWave[round][1] == 1) {
					bossesWave2 = "\u00A76";
				} else if (bossWave[round][1] == 2) {
					bossesWave2 = "\u00A7c";
				} else if (bossWave[round][1] == 3) {
					bossesWave2 = "\u00A75";
				} else if (bossWave[round][1] == 4) {
					bossesWave2 = "\u00A7c";
				} else if (bossWave[round][1] == 5) {
					bossesWave2 = "\u00A7a";
				} else if (bossWave[round][1] == 6) {
					bossesWave2 = "\u00A73";
				} else if (bossWave[round][1] == 7) {
					bossesWave2 = "\u00A74";
				} else if (bossWave[round][1] == 8) {
					bossesWave2 = "\u00A73";
				} else if (bossWave[round][1] == 9) {
					bossesWave2 = "\u00A70";
				}

				min = String.valueOf(Integer.valueOf(strArray[round][1].split("\\.")[0]) / 60);
				sec = String.valueOf(Integer.valueOf(strArray[round][1].split("\\.")[0]) % 60) + "."
						+ strArray[round][1].split("\\.")[1];
				
				if (ZombiesAddonConfig.textStyle.equals("Zombies Addon")) {
					str = (wave2 ? WAVE : "") + "\u00A7" + waveColor2 + "W2 " + bossesWave2 + min + ":"
							+ (bossWave[round][1] == 4 ? "\u00A7b" : (bossWave[round][1] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec;
				} else {
					str = (wave2 ? WAVE : "") + "\u00A7" + waveColor2 + "W2 " + bossesWave2 + "0" + min + ":"
							+ (bossWave[round][1] == 4 ? "\u00A7b" : (bossWave[round][1] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec.split("\\.")[0];
				}
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 42, 0);

				if (bossWave[round][0] == 0) {
					bossesWave1 = "";
				} else if (bossWave[round][0] == 1) {
					bossesWave1 = "\u00A76";
				} else if (bossWave[round][0] == 2) {
					bossesWave1 = "\u00A7c";
				} else if (bossWave[round][0] == 3) {
					bossesWave1 = "\u00A75";
				} else if (bossWave[round][0] == 4) {
					bossesWave1 = "\u00A7c";
				} else if (bossWave[round][0] == 5) {
					bossesWave1 = "\u00A7a";
				} else if (bossWave[round][0] == 6) {
					bossesWave1 = "\u00A73";
				} else if (bossWave[round][0] == 7) {
					bossesWave1 = "\u00A74";
				} else if (bossWave[round][0] == 8) {
					bossesWave1 = "\u00A73";
				} else if (bossWave[round][0] == 9) {
					bossesWave1 = "\u00A70";
				}

				min = String.valueOf(Integer.valueOf(strArray[round][0].split("\\.")[0]) / 60);
				sec = String.valueOf(Integer.valueOf(strArray[round][0].split("\\.")[0]) % 60) + "."
						+ strArray[round][0].split("\\.")[1];
				
				if (ZombiesAddonConfig.textStyle.equals("Zombies Addon")) {
					str = (wave1 ? WAVE : "") + "\u00A7" + waveColor1 + "W1 " + bossesWave1 + min + ":"
							+ (bossWave[round][0] == 4 ? "\u00A7b" : (bossWave[round][0] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec;
				} else {
					str = (wave1 ? WAVE : "") + "\u00A7" + waveColor1 + "W1 " + bossesWave1 + "0" + min + ":"
							+ (bossWave[round][0] == 4 ? "\u00A7b" : (bossWave[round][0] == 8 ? "\u00A74" : ""))
							+ (sec.matches(pattern1IntDot1Int) ? "0" : "") + sec.split("\\.")[0];
				}
				fr.drawStringWithShadow(str,
						((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
						y - 50, 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (RenderTimerHandler.timer == null) {
			return;
		}

		try {

			double[] waveDelays = getDoubleWaveDelays(strArray[round]);
			double[] orgWaveDelays = getDoubleWaveDelays(orgStrArray[round]);

			String s = RenderTimerHandler.timer;

			double time = Double.valueOf(s.split(":")[0]) * 60 + Double.valueOf(s.split(":")[1]);
			
			if (ZombiesAddonConfig.highlightStyle.equals("Zombies Addon")) {
				if (waveDelays.length == 1) {// w1
					if (time - orgWaveDelays[0] >= DESPAWN_TIME) {
						waveColor1 = "c";
					} else if (waveDelays[0] - time <= 0) {
						waveColor1 = "a";
						wave1 = true;
					} else if (waveDelays[0] - time <= HIGHLIGHT_DELAY_ZOMBIESADDON) {
						waveColor1 = "e";
					} else {
						waveColor1 = "8";
						wave1 = false;
					}

				} else if (waveDelays.length == 2) {// w2
					if (time - orgWaveDelays[0] >= DESPAWN_TIME) {
						waveColor1 = "c";
					} else if (waveDelays[0] - time <= 0) {
						if (!wave2) {
							wave1 = true;
						}
						waveColor1 = "a";
					} else if (waveDelays[0] - time <= HIGHLIGHT_DELAY_ZOMBIESADDON) {
						waveColor1 = "e";
					} else {
						waveColor1 = "8";
						wave1 = false;
					}

					if (time - orgWaveDelays[1] >= DESPAWN_TIME) {
						waveColor2 = "c";
					} else if (waveDelays[1] - time <= 0) {
						wave1 = false;
						waveColor2 = "a";
						wave2 = true;
					} else if (waveDelays[1] - time <= HIGHLIGHT_DELAY_ZOMBIESADDON) {
						waveColor2 = "e";
					} else {
						waveColor2 = "8";
						wave2 = false;
					}

				} else if (waveDelays.length == 3) {// w3
					if (time - orgWaveDelays[0] >= DESPAWN_TIME) {
						waveColor1 = "c";
					} else if (waveDelays[0] - time <= 0) {
						if (!wave2 && !wave3) {
							wave1 = true;
						}
						waveColor1 = "a";
					} else if (waveDelays[0] - time <= HIGHLIGHT_DELAY_ZOMBIESADDON) {
						waveColor1 = "e";
					} else {
						waveColor1 = "8";
						wave1 = false;
					}

					if (time - orgWaveDelays[1] >= DESPAWN_TIME) {
						waveColor2 = "c";
					} else if (waveDelays[1] - time <= 0) {
						if (!wave3) {
							wave2 = true;
						}
						wave1 = false;
						waveColor2 = "a";
					} else if (waveDelays[1] - time <= HIGHLIGHT_DELAY_ZOMBIESADDON) {
						waveColor2 = "e";
					} else {
						waveColor2 = "8";
						wave2 = false;
					}

					if (time - orgWaveDelays[2] >= DESPAWN_TIME) {
						waveColor3 = "c";
					} else if (waveDelays[2] - time <= 0) {
						wave2 = false;
						waveColor3 = "a";
						wave3 = true;
					} else if (waveDelays[2] - time <= HIGHLIGHT_DELAY_ZOMBIESADDON) {
						waveColor3 = "e";
					} else {
						waveColor3 = "8";
						wave3 = false;
					}

				} else if (waveDelays.length == 4) {// w4
					if (time - orgWaveDelays[0] >= DESPAWN_TIME) {
						waveColor1 = "c";
					} else if (waveDelays[0] - time <= 0) {
						if (!wave2 && !wave3 && !wave4) {
							wave1 = true;
						}
						waveColor1 = "a";
					} else if (waveDelays[0] - time <= HIGHLIGHT_DELAY_ZOMBIESADDON) {
						waveColor1 = "e";
					} else {
						waveColor1 = "8";
						wave1 = false;
					}

					if (time - orgWaveDelays[1] >= DESPAWN_TIME) {
						waveColor2 = "c";
					} else if (waveDelays[1] - time <= 0) {
						if (!wave3 && !wave4) {
							wave2 = true;
						}
						wave1 = false;
						waveColor2 = "a";
					} else if (waveDelays[1] - time <= HIGHLIGHT_DELAY_ZOMBIESADDON) {
						waveColor2 = "e";
					} else {
						waveColor2 = "8";
						wave2 = false;
					}

					if (time - orgWaveDelays[2] >= DESPAWN_TIME) {
						waveColor3 = "c";
					} else if (waveDelays[2] - time <= 0) {
						if (!wave4) {
							wave3 = true;
						}
						wave2 = false;
						waveColor3 = "a";
					} else if (waveDelays[2] - time <= HIGHLIGHT_DELAY_ZOMBIESADDON) {
						waveColor3 = "e";
					} else {
						waveColor3 = "8";
						wave3 = false;
					}

					if (time - orgWaveDelays[3] >= DESPAWN_TIME) {
						waveColor4 = "c";
					} else if (waveDelays[3] - time <= 0) {
						wave3 = false;
						waveColor4 = "a";
						wave4 = true;
					} else if (waveDelays[3] - time <= HIGHLIGHT_DELAY_ZOMBIESADDON) {
						waveColor4 = "e";
					} else {
						waveColor4 = "8";
						wave4 = false;
					}

				} else if (waveDelays.length == 5) {// w5
					if (time - orgWaveDelays[0] >= DESPAWN_TIME) {
						waveColor1 = "c";
					} else if (waveDelays[0] - time <= 0) {
						if (!wave2 && !wave3 && !wave4 && !wave5) {
							wave1 = true;
						}
						waveColor1 = "a";
						wave1 = true;
					} else if (waveDelays[0] - time <= HIGHLIGHT_DELAY_ZOMBIESADDON) {
						waveColor1 = "e";
					} else {
						waveColor1 = "8";
						wave1 = false;
					}

					if (time - orgWaveDelays[1] >= DESPAWN_TIME) {
						waveColor2 = "c";
					} else if (waveDelays[1] - time <= 0) {
						if (!wave3 && !wave4 && !wave5) {
							wave2 = true;
						}
						wave1 = false;
						waveColor2 = "a";
					} else if (waveDelays[1] - time <= HIGHLIGHT_DELAY_ZOMBIESADDON) {
						waveColor2 = "e";
					} else {
						waveColor2 = "8";
						wave2 = false;
					}

					if (time - orgWaveDelays[2] >= DESPAWN_TIME) {
						waveColor3 = "c";
					} else if (waveDelays[2] - time <= 0) {
						if (!wave4 && !wave5) {
							wave3 = true;
						}
						wave2 = false;
						waveColor3 = "a";
						wave3 = true;
					} else if (waveDelays[2] - time <= HIGHLIGHT_DELAY_ZOMBIESADDON) {
						waveColor3 = "e";
					} else {
						waveColor3 = "8";
						wave3 = false;
					}

					if (time - orgWaveDelays[3] >= DESPAWN_TIME) {
						waveColor4 = "c";
					} else if (waveDelays[3] - time <= 0) {
						if (!wave5) {
							wave4 = true;
						}
						wave3 = false;
						waveColor4 = "a";
						wave4 = true;
					} else if (waveDelays[3] - time <= HIGHLIGHT_DELAY_ZOMBIESADDON) {
						waveColor4 = "e";
					} else {
						waveColor4 = "8";
						wave4 = false;
					}

					if (time - orgWaveDelays[4] >= DESPAWN_TIME) {
						waveColor5 = "c";
					} else if (waveDelays[4] - time <= 0) {
						wave4 = false;
						waveColor5 = "a";
						wave5 = true;
					} else if (waveDelays[4] - time <= HIGHLIGHT_DELAY_ZOMBIESADDON) {
						waveColor5 = "e";
					} else {
						waveColor5 = "8";
						wave5 = false;
					}

				} else if (waveDelays.length == 6) {// w6
					if (time - orgWaveDelays[0] >= DESPAWN_TIME) {
						waveColor1 = "c";
					} else if (waveDelays[0] - time <= 0) {
						if (!wave2 && !wave3 && !wave4 && !wave5 && !wave6) {
							wave1 = true;
						}
						waveColor1 = "a";
						wave1 = true;
					} else if (waveDelays[0] - time <= HIGHLIGHT_DELAY_ZOMBIESADDON) {
						waveColor1 = "e";
					} else {
						waveColor1 = "8";
						wave1 = false;
					}

					if (time - orgWaveDelays[1] >= DESPAWN_TIME) {
						waveColor2 = "c";
					} else if (waveDelays[1] - time <= 0) {
						if (!wave3 && !wave4 && !wave5 && !wave6) {
							wave2 = true;
						}
						wave1 = false;
						waveColor2 = "a";
					} else if (waveDelays[1] - time <= HIGHLIGHT_DELAY_ZOMBIESADDON) {
						waveColor2 = "e";
					} else {
						waveColor2 = "8";
						wave2 = false;
					}

					if (time - orgWaveDelays[2] >= DESPAWN_TIME) {
						waveColor3 = "c";
					} else if (waveDelays[2] - time <= 0) {
						if (!wave4 && !wave5 && !wave6) {
							wave3 = true;
						}
						wave2 = false;
						waveColor3 = "a";
						wave3 = true;
					} else if (waveDelays[2] - time <= HIGHLIGHT_DELAY_ZOMBIESADDON) {
						waveColor3 = "e";
					} else {
						waveColor3 = "8";
						wave3 = false;
					}

					if (time - orgWaveDelays[3] >= DESPAWN_TIME) {
						waveColor4 = "c";
					} else if (waveDelays[3] - time <= 0) {
						if (!wave5 && !wave6) {
							wave4 = true;
						}
						wave3 = false;
						waveColor4 = "a";
						wave4 = true;
					} else if (waveDelays[3] - time <= HIGHLIGHT_DELAY_ZOMBIESADDON) {
						waveColor4 = "e";
					} else {
						waveColor4 = "8";
						wave4 = false;
					}

					if (time - orgWaveDelays[4] >= DESPAWN_TIME) {
						waveColor5 = "c";
					} else if (waveDelays[4] - time <= 0) {
						if (!wave6) {
							wave5 = true;
						}
						wave4 = false;
						waveColor5 = "a";
					} else if (waveDelays[4] - time <= HIGHLIGHT_DELAY_ZOMBIESADDON) {
						waveColor5 = "e";
					} else {
						waveColor5 = "8";
						wave5 = false;
					}

					if (time - orgWaveDelays[5] >= DESPAWN_TIME) {
						waveColor6 = "c";
					} else if (waveDelays[5] - time <= 0) {
						wave5 = false;
						waveColor6 = "a";
						wave6 = true;
					} else if (waveDelays[5] - time <= HIGHLIGHT_DELAY_ZOMBIESADDON) {
						waveColor6 = "e";
					} else {
						waveColor6 = "8";
						wave6 = false;
					}
				}
			} else if (GameDetect.getMap() != 3) {//SST
				if (waveDelays.length == 1) {// w1
					if (time - orgWaveDelays[0] >= DESPAWN_TIME) {
						waveColor1 = "c";
					} else {
						waveColor1 = "e";
						wave1 = true;
					}

				} else if (waveDelays.length == 2) {// w2
					if (time - orgWaveDelays[0] >= DESPAWN_TIME) {
						waveColor1 = "c";
					} else if (wave2) {
						waveColor1 = "8";
						wave1 = false;
					} else {
						waveColor1 = "e";
						wave1 = true;
					}

					if (time - orgWaveDelays[1] >= DESPAWN_TIME) {
						waveColor2 = "c";
					} else if (time - waveDelays[0] >= HIGHLIGHT_DELAY_SST) {
						waveColor2 = "e";
						wave2 = true;
					} else {
						waveColor2 = "7";
						wave2 = false;
					}

				} else if (waveDelays.length == 3) {// w3
					if (time - orgWaveDelays[0] >= DESPAWN_TIME) {
						waveColor1 = "c";
					} else if (wave2 || wave3) {
						waveColor1 = "8";
						wave1 = false;
					} else {
						waveColor1 = "e";
						wave1 = true;
					}

					if (time - orgWaveDelays[1] >= DESPAWN_TIME) {
						waveColor2 = "c";
					} else if (wave3) {
						waveColor2 = "8";
						wave2 = false;
					} else if (time - waveDelays[0] >= HIGHLIGHT_DELAY_SST) {
						waveColor2 = "e";
						wave2 = true;
					} else {
						waveColor2 = "7";
						wave2 = false;
					}

					if (time - orgWaveDelays[2] >= DESPAWN_TIME) {
						waveColor3 = "c";
					} else if (time - waveDelays[1] >= HIGHLIGHT_DELAY_SST) {
						waveColor3 = "e";
						wave3 = true;
					} else {
						waveColor3 = "7";
						wave3 = false;
					}

				} else if (waveDelays.length == 4) {// w4
					if (time - orgWaveDelays[0] >= DESPAWN_TIME) {
						waveColor1 = "c";
					} else if (wave2 || wave3 || wave4) {
						waveColor1 = "8";
						wave1 = false;
					} else {
						waveColor1 = "e";
						wave1 = true;
					}

					if (time - orgWaveDelays[1] >= DESPAWN_TIME) {
						waveColor2 = "c";
					} else if (wave3 || wave4) {
						waveColor2 = "8";
						wave2 = false;
					} else if (time - waveDelays[0] >= HIGHLIGHT_DELAY_SST) {
						waveColor2 = "e";
						wave2 = true;
					} else {
						waveColor2 = "7";
						wave2 = false;
					}

					if (time - orgWaveDelays[2] >= DESPAWN_TIME) {
						waveColor3 = "c";
					} else if (wave4) {
						waveColor3 = "8";
						wave3 = false;
					} else if (time - waveDelays[1] >= HIGHLIGHT_DELAY_SST) {
						waveColor3 = "e";
						wave3 = true;
					} else {
						waveColor3 = "7";
						wave3 = false;
					}

					if (time - orgWaveDelays[3] >= DESPAWN_TIME) {
						waveColor4 = "c";
					} else if (time - waveDelays[2] >= HIGHLIGHT_DELAY_SST) {
						waveColor4 = "e";
						wave4 = true;
					} else {
						waveColor4 = "7";
						wave4 = false;
					}

				} else if (waveDelays.length == 5) {// w5
					if (time - orgWaveDelays[0] >= DESPAWN_TIME) {
						waveColor1 = "c";
					} else if (wave2 || wave3 || wave4 || wave5) {
						waveColor1 = "8";
						wave1 = false;
					} else {
						waveColor1 = "e";
						wave1 = true;
					}

					if (time - orgWaveDelays[1] >= DESPAWN_TIME) {
						waveColor2 = "c";
					} else if (wave3 || wave4 || wave5) {
						waveColor2 = "8";
						wave2 = false;
					} else if (time - waveDelays[0] >= HIGHLIGHT_DELAY_SST) {
						waveColor2 = "e";
						wave2 = true;
					} else {
						waveColor2 = "7";
						wave2 = false;
					}

					if (time - orgWaveDelays[2] >= DESPAWN_TIME) {
						waveColor3 = "c";
					} else if (wave4 || wave5) {
						waveColor3 = "8";
						wave3 = false;
					} else if (time - waveDelays[1] >= HIGHLIGHT_DELAY_SST) {
						waveColor3 = "e";
						wave3 = true;
					} else {
						waveColor3 = "7";
						wave3 = false;
					}

					if (time - orgWaveDelays[3] >= DESPAWN_TIME) {
						waveColor4 = "c";
					} else if (wave5) {
						waveColor4 = "8";
						wave4 = false;
					} else if (time - waveDelays[2] >= HIGHLIGHT_DELAY_SST) {
						waveColor4 = "e";
						wave4 = true;
					} else {
						waveColor4 = "7";
						wave4 = false;
					}

					if (time - orgWaveDelays[4] >= DESPAWN_TIME) {
						waveColor5 = "c";
					} else if (time - waveDelays[3] >= HIGHLIGHT_DELAY_SST) {
						waveColor5 = "e";
						wave5 = true;
					} else {
						waveColor5 = "7";
						wave5 = false;
					}

				} else if (waveDelays.length == 6) {// w6
					if (time - orgWaveDelays[0] >= DESPAWN_TIME) {
						waveColor1 = "c";
					} else if (wave2 || wave3 || wave4 || wave5 || wave6) {
						waveColor1 = "8";
						wave1 = false;
					} else {
						waveColor1 = "e";
						wave1 = true;
					}

					if (time - orgWaveDelays[1] >= DESPAWN_TIME) {
						waveColor2 = "c";
					} else if (wave3 || wave4 || wave5 || wave6) {
						waveColor2 = "8";
						wave2 = false;
					} else if (time - waveDelays[0] >= HIGHLIGHT_DELAY_SST) {
						waveColor2 = "e";
						wave2 = true;
					} else {
						waveColor2 = "7";
						wave2 = false;
					}

					if (time - orgWaveDelays[2] >= DESPAWN_TIME) {
						waveColor3 = "c";
					} else if (wave4 || wave5 || wave6) {
						waveColor3 = "8";
						wave3 = false;
					} else if (time - waveDelays[1] >= HIGHLIGHT_DELAY_SST) {
						waveColor3 = "e";
						wave3 = true;
					} else {
						waveColor3 = "7";
						wave3 = false;
					}

					if (time - orgWaveDelays[3] >= DESPAWN_TIME) {
						waveColor4 = "c";
					} else if (wave5 || wave6) {
						waveColor4 = "8";
						wave4 = false;
					} else if (time - waveDelays[2] >= HIGHLIGHT_DELAY_SST) {
						waveColor4 = "e";
						wave4 = true;
					} else {
						waveColor4 = "7";
						wave4 = false;
					}

					if (time - orgWaveDelays[4] >= DESPAWN_TIME) {
						waveColor5 = "c";
					} else if (wave6) {
						waveColor5 = "8";
						wave5 = false;
					} else if (time - waveDelays[3] >= HIGHLIGHT_DELAY_SST) {
						waveColor5 = "e";
						wave5 = true;
					} else {
						waveColor5 = "7";
						wave5 = false;
					}

					if (time - orgWaveDelays[5] >= DESPAWN_TIME) {
						waveColor6 = "c";
					} else if (time - waveDelays[4] >= HIGHLIGHT_DELAY_SST) {
						waveColor6 = "e";
						wave6 = true;
					} else {
						waveColor6 = "7";
						wave6 = false;
					}
				}
			} else {//SST AA
				if (waveDelays.length == 1) {// w1
					if (time - orgWaveDelays[0] >= DESPAWN_TIME) {
						waveColor1 = "c";
					} else {
						waveColor1 = "e";
						wave1 = true;
					}

				} else if (waveDelays.length == 2) {// w2
					if (time - orgWaveDelays[0] >= DESPAWN_TIME) {
						waveColor1 = "c";
					} else if (wave2) {
						waveColor1 = "8";
						wave1 = false;
					} else {
						waveColor1 = "e";
						wave1 = true;
					}

					if (time - orgWaveDelays[1] >= DESPAWN_TIME) {
						waveColor2 = "c";
					} else if (time - waveDelays[0] >= 0) {
						waveColor2 = "e";
						wave2 = true;
					} else {
						waveColor2 = "7";
						wave2 = false;
					}

				} else if (waveDelays.length == 3) {// w3
					if (time - orgWaveDelays[0] >= DESPAWN_TIME) {
						waveColor1 = "c";
					} else if (wave2 || wave3) {
						waveColor1 = "8";
						wave1 = false;
					} else {
						waveColor1 = "e";
						wave1 = true;
					}

					if (time - orgWaveDelays[1] >= DESPAWN_TIME) {
						waveColor2 = "c";
					} else if (wave3) {
						waveColor2 = "8";
						wave2 = false;
					} else if (time - waveDelays[0] >= 0) {
						waveColor2 = "e";
						wave2 = true;
					} else {
						waveColor2 = "7";
						wave2 = false;
					}

					if (time - orgWaveDelays[2] >= DESPAWN_TIME) {
						waveColor3 = "c";
					} else if (time - waveDelays[1] >= 0) {
						waveColor3 = "e";
						wave3 = true;
					} else {
						waveColor3 = "7";
						wave3 = false;
					}

				} else if (waveDelays.length == 4) {// w4
					if (time - orgWaveDelays[0] >= DESPAWN_TIME) {
						waveColor1 = "c";
					} else if (wave2 || wave3 || wave4) {
						waveColor1 = "8";
						wave1 = false;
					} else {
						waveColor1 = "e";
						wave1 = true;
					}

					if (time - orgWaveDelays[1] >= DESPAWN_TIME) {
						waveColor2 = "c";
					} else if (wave3 || wave4) {
						waveColor2 = "8";
						wave2 = false;
					} else if (time - waveDelays[0] >= 0) {
						waveColor2 = "e";
						wave2 = true;
					} else {
						waveColor2 = "7";
						wave2 = false;
					}

					if (time - orgWaveDelays[2] >= DESPAWN_TIME) {
						waveColor3 = "c";
					} else if (wave4) {
						waveColor3 = "8";
						wave3 = false;
					} else if (time - waveDelays[1] >= 0) {
						waveColor3 = "e";
						wave3 = true;
					} else {
						waveColor3 = "7";
						wave3 = false;
					}

					if (time - orgWaveDelays[3] >= DESPAWN_TIME) {
						waveColor4 = "c";
					} else if (time - waveDelays[2] >= 0) {
						waveColor4 = "e";
						wave4 = true;
					} else {
						waveColor4 = "7";
						wave4 = false;
					}

				} else if (waveDelays.length == 5) {// w5
					if (time - orgWaveDelays[0] >= DESPAWN_TIME) {
						waveColor1 = "c";
					} else if (wave2 || wave3 || wave4 || wave5) {
						waveColor1 = "8";
						wave1 = false;
					} else {
						waveColor1 = "e";
						wave1 = true;
					}

					if (time - orgWaveDelays[1] >= DESPAWN_TIME) {
						waveColor2 = "c";
					} else if (wave3 || wave4 || wave5) {
						waveColor2 = "8";
						wave2 = false;
					} else if (time - waveDelays[0] >= 0) {
						waveColor2 = "e";
						wave2 = true;
					} else {
						waveColor2 = "7";
						wave2 = false;
					}

					if (time - orgWaveDelays[2] >= DESPAWN_TIME) {
						waveColor3 = "c";
					} else if (wave4 || wave5) {
						waveColor3 = "8";
						wave3 = false;
					} else if (time - waveDelays[1] >= 0) {
						waveColor3 = "e";
						wave3 = true;
					} else {
						waveColor3 = "7";
						wave3 = false;
					}

					if (time - orgWaveDelays[3] >= DESPAWN_TIME) {
						waveColor4 = "c";
					} else if (wave5) {
						waveColor4 = "8";
						wave4 = false;
					} else if (time - waveDelays[2] >= 0) {
						waveColor4 = "e";
						wave4 = true;
					} else {
						waveColor4 = "7";
						wave4 = false;
					}

					if (time - orgWaveDelays[4] >= DESPAWN_TIME) {
						waveColor5 = "c";
					} else if (time - waveDelays[3] >= 0) {
						waveColor5 = "e";
						wave5 = true;
					} else {
						waveColor5 = "7";
						wave5 = false;
					}

				} else if (waveDelays.length == 6) {// w6
					if (time - orgWaveDelays[0] >= DESPAWN_TIME) {
						waveColor1 = "c";
					} else if (wave2 || wave3 || wave4 || wave5 || wave6) {
						waveColor1 = "8";
						wave1 = false;
					} else {
						waveColor1 = "e";
						wave1 = true;
					}

					if (time - orgWaveDelays[1] >= DESPAWN_TIME) {
						waveColor2 = "c";
					} else if (wave3 || wave4 || wave5 || wave6) {
						waveColor2 = "8";
						wave2 = false;
					} else if (time - waveDelays[0] >= 0) {
						waveColor2 = "e";
						wave2 = true;
					} else {
						waveColor2 = "7";
						wave2 = false;
					}

					if (time - orgWaveDelays[2] >= DESPAWN_TIME) {
						waveColor3 = "c";
					} else if (wave4 || wave5 || wave6) {
						waveColor3 = "8";
						wave3 = false;
					} else if (time - waveDelays[1] >= 0) {
						waveColor3 = "e";
						wave3 = true;
					} else {
						waveColor3 = "7";
						wave3 = false;
					}

					if (time - orgWaveDelays[3] >= DESPAWN_TIME) {
						waveColor4 = "c";
					} else if (wave5 || wave6) {
						waveColor4 = "8";
						wave4 = false;
					} else if (time - waveDelays[2] >= 0) {
						waveColor4 = "e";
						wave4 = true;
					} else {
						waveColor4 = "7";
						wave4 = false;
					}

					if (time - orgWaveDelays[4] >= DESPAWN_TIME) {
						waveColor5 = "c";
					} else if (wave6) {
						waveColor5 = "8";
						wave5 = false;
					} else if (time - waveDelays[3] >= 0) {
						waveColor5 = "e";
						wave5 = true;
					} else {
						waveColor5 = "7";
						wave5 = false;
					}

					if (time - orgWaveDelays[5] >= DESPAWN_TIME) {
						waveColor6 = "c";
					} else if (time - waveDelays[4] >= 0) {
						waveColor6 = "e";
						wave6 = true;
					} else {
						waveColor6 = "7";
						wave6 = false;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
