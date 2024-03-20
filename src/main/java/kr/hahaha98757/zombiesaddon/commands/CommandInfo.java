package kr.hahaha98757.zombiesaddon.commands;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

public class CommandInfo extends CommandBase {
	public static final String LINE = "\u00A7e-----------------------------------------------------";

	@Override
	public String getCommandName() {
		return "info";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "\u00A7cUsage: /info [mod name]";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (!(sender instanceof EntityPlayer)) {
			return;
		}
		EntityPlayer player = (EntityPlayer) sender;
		if (args.length == 0) {
			player.addChatComponentMessage(new ChatComponentText(LINE + "\nMod name: " + ZombiesAddon.NAME
					+ "\nVersion: " + ZombiesAddon.VERSION
					+ "\nMod list: Cornering, Block Alarm, DPS Counter, NOTLAST, Auto Splits, ZSV, SLA, Advanced SLA, Auto Rejoin, Powerup Alarm, Boss Alarm, Grow ESP, and Gstep Guide.\nInfo: A mod that helps playing hypixel zombies.\nCommand tips: Use /info [Cornering|BlockAlarm|DPSCounter|NOTLAST|AutoSplits|ZSV|SLA|AdvancedSLA|AutoRejoin|PowerupAlarm|BossAlarm|GrowESP|GstepGuide] to view mod's description.\nURL: https://github.com/hahaha98757/ZombiesAddon\n"
					+ LINE));
			return;
		}

		String text;

		switch (args[0]) {
		case "Cornering":
			text = "Name: Cornering\nVersion: 1.1.1\nCode by: syeyoung and thamid\nInfo: Toggles the visibility of players nearby.\nCommands: /cornering\nKeybinds: V\nConfig: Used";
			break;
		case "BlockAlarm":
			text = "Name: Block Alarm\nVersion: 1.1.1\nCode by: syeyoung\nInfo: If you survived alone and there are players within 1.5m that can revive, show \"BLOCK\" on display.\nKeybinds: B\nConfig: Used";
			break;
		case "DPSCounter":
			text = "Name: DPS Counter\nVersion: 1.3\nCode by: thamid\nInfo: Counts your party's DPS.\nConfig: Used";
			break;
		case "NOTLAST":
			text = "Name: NOTLAST\nVersion: 1.2\nCode by: thamid\nInfo: Shows who is kill the last in every round.\nConfig: Used";
			break;
		case "AutoSplits":
			text = "Name: Auto Splits\nVersion: 1.0\nCode by: thamid\nInfo: Run LiveSplits or internal timer at start of round.\nConfig: Used\nConfig: Used";
			break;
		case "ZSV":
			text = "Name: ZSV(Zombies Strat Viewer)\nVersion: 1.1.1\nCode by: syeyoung\nInfo: Displays the text in https://pastebin.com/ on the in-game screen.\nCommands: /ZSV and /ZSVLines";
			break;
		case "SLA":
			text = "Name: SLA(Spawn Limit Action)\nVersion: 1.3\nCode by: thamid\nInfo: Display number of windows where zombies can spawn.\nCommands: /SLA\nConfig: Used";
			break;
		case "AdvancedSLA":
			text = "Name: Advanced SLA\nVersion: 1.1.1\nCode by: Stachelbeere1248\nInfo: SLA is displayed in more detail.\nCommands: /SLA\nConfig: Used";
			break;
		case "AutoRejoin":
			text = "Name: Auto Rejoin\nVersion: 1.1\nCode by: thamid\nInfo: Automatically rejoin the Zombies game.\nKeybinds: Null\nConfig: Used";
			break;
		case "PowerupAlarm":
			text = "Name: Powerup Alarm\nCode by: hahaha98757\nInfo: You will receive an alarm when a powerup is spawned. You will know when powerup spawn and despawn.\nCommands: /powerupAlarm\nConfig: Used";
			break;
		case "BossAlarm":
			text = "Name: Boss Alarm\nCode by: hahaha98757\nInfo: You will know the area where the boss spawned.\nConfig: Used";
			break;
		case "LrodOrder":
			text = "Name: Lrod Order\nCode by: hahaha9875\nInfo: Shows the order of use of Lrod.\nConfig: Used";
			break;
		case "GrowESP":
			text = "Name: Grow ESP\nCode by: Dreaght\nInfo: Shows the arithmetic mean of the size of the slimes for growing.\nConfig: Used";
			break;
		case "GstepGuide":
			text = "Name: Gstep Guide\nCode by: hahaha98757\nInfo: Shows hitbox of armor stand in Gstep.\nConfig: Used";
			break;
		default:
			player.addChatComponentMessage(new ChatComponentText(getCommandUsage(null)));
			return;
		}
		player.addChatComponentMessage(new ChatComponentText(LINE + "\n" + text + "\n" + LINE));
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1) {
			return getListOfStringsMatchingLastWord(args,
					new String[] { "Cornering", "BlockAlarm", "DPSCounter", "NOTLAST", "AutoSplits", "ZSV", "SLA",
							"AdvancedSLA", "AutoRejoin", "PowerupAlarm", "BosAlarm", "LrodOrder", "GrowESP",
							"GstepGuide" });
		}
		return null;
	}
}
