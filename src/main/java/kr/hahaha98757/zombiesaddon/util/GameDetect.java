package kr.hahaha98757.zombiesaddon.util;

import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;

public class GameDetect {

	public static int roundDetect() {
		boolean czlop = false;
		int round = 0;
		Scoreboard scoreboard = Minecraft.getMinecraft().thePlayer.getWorldScoreboard();
		ScoreObjective scoreObjective = scoreboard
				.getObjectiveInDisplaySlot(Scoreboard.getObjectiveDisplaySlotNumber("sidebar"));
		if (scoreObjective != null
				&& scoreObjective.getDisplayName().replaceAll("\u00A7.", "").equalsIgnoreCase("ZOMBIES")) {
			Iterator var7 = scoreboard.getSortedScores(scoreObjective).iterator();

			while (var7.hasNext()) {
				Score score = (Score) var7.next();
				ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
				String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName());
				if (score.getScorePoints() == 1) {
					if (s.contains("czlop")) {
						czlop = true;
					}
				}
				if (czlop ? (score.getScorePoints() >= 10 && score.getScorePoints() <= 13) : score.getScorePoints() == 13) {
					s = s.replaceAll("\u00A7.", "");
					round = 0;
					try {
						s = s.replaceAll("[^0-9]", "").trim();
					} catch (Exception var13) {
						round = 0;
						return round;
					}

					try {
						round = Integer.parseInt(s);
					} catch (Exception e) {
						round = 0;
						return round;
					}

				}
			}
			return round;
		}
		return -1;
	}

	public static int isDifficult(int map, int round) {// Normal: 1, Hard: 2, RIP: 3
		Scoreboard scoreboard = Minecraft.getMinecraft().thePlayer.getWorldScoreboard();
		ScoreObjective scoreObjective = scoreboard
				.getObjectiveInDisplaySlot(Scoreboard.getObjectiveDisplaySlotNumber("sidebar"));
		if (scoreObjective != null
				&& scoreObjective.getDisplayName().replaceAll("\u00A7.", "").equalsIgnoreCase("ZOMBIES")) {
			Iterator var7 = scoreboard.getSortedScores(scoreObjective).iterator();

			while (var7.hasNext()) {
				Score score = (Score) var7.next();
				ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
				String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName());
				if (score.getScorePoints() == 12) {
					s = s.replaceAll("\u00A7.", "");
					try {
						s = s.replaceAll("[^0-9]", "").trim();
					} catch (Exception var13) {
						return 1;
					}
					// 코드 적는 곳
					if (map == 1) {
						if (round == 4) {
							if (s.equals("26")) {
								return 3;
							}
						}
						if (round == 9 && s.equals("34")) {
							return 3;
						}
						if (round == 14) {
							if (s.equals("41")) {
								return 2;
							} else if (s.equals("48")) {
								return 3;
							}
						}
						if (round == 19 && s.equals("68")) {
							return 3;
						}
						if (round == 24) {
							if (s.equals("70")) {
								return 2;
							} else if (s.equals("78")) {
								return 3;
							}
						}
						if (round == 29) {
							if (s.equals("101")) {
								return 2;
							} else if (s.equals("111")) {
								return 3;
							}
						}
					} else if (map == 2) {
						if (round == 14 && s.equals("42")) {
							return 3;
						}
						if (round == 19 && s.equals("44")) {
							return 3;
						}
						if (round == 24 && s.equals("66")) {
							return 3;
						}
						if (round == 29 && s.equals("83")) {
							return 3;
						}
					}
				}
			}
		}
		return 1;
	}

	public static int mapDetect() {// DE: 1, BB: 2, AA: 3
		int map = 0;
		Scoreboard scoreboard = Minecraft.getMinecraft().thePlayer.getWorldScoreboard();
		ScoreObjective scoreObjective = scoreboard
				.getObjectiveInDisplaySlot(Scoreboard.getObjectiveDisplaySlotNumber("sidebar"));
		if (scoreObjective != null
				&& scoreObjective.getDisplayName().replaceAll("\u00A7.", "").equalsIgnoreCase("ZOMBIES")) {
			Iterator var7 = scoreboard.getSortedScores(scoreObjective).iterator();

			while (var7.hasNext()) {
				Score score = (Score) var7.next();
				ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
				String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName());
				if (score.getScorePoints() == 3) {
					s = s.replaceAll("\u00A7.", "");
					map = 0;
					try {
						s = s.split(":")[1].replaceAll("[^a-zA-Z0-9]", "").trim();
					} catch (Exception var13) {
						map = 0;
						return map;
					}

					if (s.equalsIgnoreCase("DeadEnd")) {
						map = 1;
					} else if (s.equalsIgnoreCase("BadBlood")) {
						map = 2;
					} else if (s.equalsIgnoreCase("AlienArcadium")) {
						map = 3;
					} else {
						map = 0;
					}
				}
			}
			return map;
		}
		return -1;
	}

	public static boolean isZombiesGame() {
		try {
			Scoreboard scoreboard = Minecraft.getMinecraft().thePlayer.getWorldScoreboard();
			ScoreObjective scoreObjective = scoreboard
					.getObjectiveInDisplaySlot(Scoreboard.getObjectiveDisplaySlotNumber("sidebar"));
			if (scoreObjective != null
					&& scoreObjective.getDisplayName().replaceAll("\u00A7.", "").equalsIgnoreCase("ZOMBIES")) {
				Iterator var7 = scoreboard.getSortedScores(scoreObjective).iterator();
				while (var7.hasNext()) {
					Score score = (Score) var7.next();
					ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
					String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName());
					if (score.getScorePoints() <= 10 && score.getScorePoints() >= 7) {
						s = s.replaceAll("\u00A7.", "");

						try {
							s = s.split(":")[0].replaceAll("[^a-zA-Z0-9]", "").trim();
						} catch (Exception var13) {
							return false;
						}

						if (s.equalsIgnoreCase(Minecraft.getMinecraft().thePlayer.getName())) {
							return true;
						}
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public static int getLang() {// English: 0, Korean: 1
		boolean czlop = false;
		int lang = 0;
		Scoreboard scoreboard = Minecraft.getMinecraft().thePlayer.getWorldScoreboard();
		ScoreObjective scoreObjective = scoreboard
				.getObjectiveInDisplaySlot(Scoreboard.getObjectiveDisplaySlotNumber("sidebar"));
		if (scoreObjective != null
				&& scoreObjective.getDisplayName().replaceAll("\u00A7.", "").equalsIgnoreCase("ZOMBIES")) {
			Iterator var7 = scoreboard.getSortedScores(scoreObjective).iterator();

			while (var7.hasNext()) {
				Score score = (Score) var7.next();
				ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
				String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName());
				if (score.getScorePoints() == 1) {
					if (s.contains("czlop")) {
						czlop = true;
					}
				}
				if (czlop ? (score.getScorePoints() >= 10 && score.getScorePoints() <= 13 ) : score.getScorePoints() == 13) {
					s = s.replaceAll("\u00A7.", "");
					s = s.replaceAll("[^A-Za-z\uAC00-\uD7A3]", "").trim();
					if (s.contains("Round")) {
						lang = 0;
						return lang;
					} else if (s.contains("\uB77C\uC6B4\uB4DC")) {
						lang = 1;
						return lang;
					}
				}
			}
		}
		return lang;
	}

}
