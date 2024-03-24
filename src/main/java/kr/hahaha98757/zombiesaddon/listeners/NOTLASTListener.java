package kr.hahaha98757.zombiesaddon.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class NOTLASTListener {
	private final Map<String, Integer> scoreMap = new HashMap();
	private final Scoreboard scoreboard;
	private final ScoreObjective scoreObjective;

	public NOTLASTListener() {
		this.scoreboard = Minecraft.getMinecraft().theWorld.getScoreboard();
		this.scoreObjective = scoreboard.getObjectiveInDisplaySlot(Scoreboard.getObjectiveDisplaySlotNumber("list"));
		Iterator var1 = this.scoreboard.getSortedScores(this.scoreObjective).iterator();

		while (var1.hasNext()) {
			Score score = (Score) var1.next();
			this.scoreMap.put(score.getPlayerName(), score.getScorePoints());
		}

	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent tickEvent) {
		if (!ZombiesAddonConfig.enableMod) {
			return;
		}

		List<String> players = new ArrayList();
		Iterator var3 = this.scoreboard.getSortedScores(this.scoreObjective).iterator();

		while (var3.hasNext()) {
			Score score = (Score) var3.next();
			Integer kills = (Integer) this.scoreMap.get(score.getPlayerName());
			if (kills != null && score.getScorePoints() != kills) {
				players.add(score.getPlayerName());
			}
		}

		if (players.size() > 0) {
			this.printLast(players);
		}

	}

	private void printLast(List<String> players) {
		MinecraftForge.EVENT_BUS.unregister(this);
		StringBuilder last = new StringBuilder();
		last.append((String) players.get(0));

		for (int i = 1; i < players.size(); ++i) {
			String player = (String) players.get(i);
			last.append(" or ");
			last.append(player);
		}

		last.append(" was last");
		Minecraft.getMinecraft().thePlayer
				.addChatComponentMessage(new ChatComponentText("\u00A7eNOTLAST: " + last.toString()));
	}
}
