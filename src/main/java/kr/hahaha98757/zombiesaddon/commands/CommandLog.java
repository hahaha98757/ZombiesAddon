package kr.hahaha98757.zombiesaddon.commands;

import java.util.Arrays;
import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

public class CommandLog extends CommandBase {

	public String getCommandName() {
		return "updatelog";
	}

	public int getRequiredPermissionLevel() {
		return 0;
	}

	public String getCommandUsage(ICommandSender sender) {
		return "\u00A7cUsage: /updatelog <version>";
	}

	public List<String> getCommandAliases() {
		return Arrays.<String>asList(new String[] { "log" });
	}

	public void processCommand(ICommandSender sender, String[] args) {
		if (!(sender instanceof EntityPlayer)) {
			return;
		}
		EntityPlayer player = (EntityPlayer) sender;
		if (args.length == 0) {
			player.addChatComponentMessage(new ChatComponentText(getCommandUsage(null)));
			return;
		}

		String text;

		switch (args[0]) {
		case "1.1.0":
			text = "Version: 1.1.0\nLog: Removed ZHF mod.";
			break;
		case "1.2.0":
			text = "Version: 1.2.0\nLog: Added NOTLAST mod.";
			break;
		case "1.2.1":
			text = "Version: 1.2.1\nLog: Added /updatelog command. /info command fixed.";
			break;
		case "1.3.0":
			text = "Version: 1.3.0\nLog: Added Auto Splits mod.";
			break;
		case "1.4.0":
			text = "Version: 1.4.0\nLog: Added ZSV and SLA mod.";
			break;
		case "1.4.1":
			text = "Version: 1.4.1\nLog: Fixed mod's message.";
			break;
		case "1.4.2":
			text = "Version: 1.4.2\nLog: Fixed bug. Block Alarm fixed. Added /blockAlarm command.";
			break;
		case "1.4.3":
			text = "Version: 1.4.3\nLog: Fixed bug. You can use Block Alarm in Korean.";
			break;
		case "1.4.4":
			text = "Version: 1.4.4\nLog: Fixed Block Alarm.";
			break;
		case "1.4.5":
			text = "Version: 1.4.5\nLog: Fixed Block Alarm.";
			break;
		case "1.5.0":
			text = "Version: 1.5.0\nLog: Added Auto Rejoin mod. Fixed bug. Fixed AutoSplits.";
			break;
		case "1.5.1":
			text = "Version: 1.5.1\nLog: Fixed bug.";
			break;
		case "1.5.2":
			text = "Version: 1.5.2\nLog: Fixed bug.";
			break;
		case "1.6.0":
			text = "Version: 1.6.0\nLog: Remake /setMap, /setstrat, /setlines commands name to /SLA, /ZSV, /ZSVLines. Remove /blockAlarm command. Added /cornering command. Added config for Cornering and BlockAlarm.";
			break;
		case "1.6.1":
			text = "Version: 1.6.1\nLog: Fixed Block Alarm.";
			break;
		case "1.6.2":
			text = "Version: 1.6.2\nLog: Fixed NOTLAST and Auto Rejoin. Fixed bug.";
			break;
		case "1.6.3":
			text = "Version: 1.6.3\nLog: Fixed bug.";
			break;
		case "1.6.4":
			text = "Version: 1.6.4\nLog: Fixed bug.";
			break;
		case "1.6.5":
			text = "Version: 1.6.5\nLog: Fixed bug.";
			break;
		case "1.7.0":
			text = "Version: 1.7.0\nLog: Fixed AutoSplits. Fixed /ZSV, /SLA command. Fixed AutoRejoin. Added config for AutoSplits.";
			break;
		case "1.7.1":
			text = "Version: 1.7.1\nLog: Fixed bug.";
			break;
		case "1.7.2":
			text = "Version: 1.7.2\nLog: Fixed bug.";
			break;
		case "1.7.3":
			text = "Version: 1.7.3\nLog: Fixed bug.";
			break;
		case "1.7.4":
			text = "Version: 1.7.4\nLog: Fixed bug.";
			break;
		case "1.7.5":
			text = "Version: 1.7.5\nLog: Fixed bug.";
			break;
		case "1.7.6":
			text = "Version: 1.7.6\nLog: Fixed bug.";
			break;
		case "1.8.0":
			text = "Version: 1.8.0\nLog: Added Powerup Alarm. Fixed bugs. Added config for ZombiesAddon. Fixed Auto Splits.";
			break;
		case "1.8.1":
			text = "Version: 1.8.1\nLog: Fixed Powerup Alarm. Fixed bugs. Added config for SLA.";
			break;
		case "1.8.2":
			text = "Version: 1.8.2\nLog: Fixed bugs.";
			break;
		case "1.8.3":
			text = "Version: 1.8.3\nLog: Fixed bugs.";
			break;
		case "1.8.4":
			text = "Version: 1.8.4\nLog: Fixed bugs. Fixed Auto Splits.";
			break;
		case "1.8.5":
			text = "Version: 1.8.5\nLog: Fixed bugs. Fixed CommandInfo. Added /zombies Command for Zombies Overlay.";
			break;
		case "1.8.6":
			text = "Version: 1.8.6\nLog: Fixed bugs. Removed /zombies command. Added config for Zombies Overlay. Fixed Powerup Alarm.";
			break;
		case "1.8.7":
			text = "Version: 1.8.7\nLog: Fixed bugs.";
			break;
		case "1.8.8":
			text = "Version: 1.8.8\nLog: Fixed bugs. Added Boss Wave Alarm for AutoSplits.";
			break;
		case "1.8.9":
			text = "Version: 1.8.9\nLog: Fixed bugs.";
			break;
		case "1.9.0":
			text = "Version: 1.9.0\nLog: Fixed bugs. Added Boss Alarm. Fixed forge command bug.";
			break;
		case "1.9.1":
			text = "Version: 1.9.1\nLog: Fixed bugs.";
			break;
		case "1.9.2":
			text = "Version: 1.9.2\nLog: Fixed bugs.";
			break;
		case "1.10.0":
			text = "Version: 1.10.0\nLog: Changed package. Code Reorganization. Added Lrod Order. Added Advanced SLA. Fixed command.";
			break;
		case "1.10.1":
			text = "version: 1.10.1\nLog: Fixed bugs.";
			break;
		case "1.10.2":
			text = "version: 1.10.2\nLog: Added Hologram Bug Generator.";
			break;
		case "1.10.3":
			text = "version: 1.10.3\nLog: Fixed bugs.";
			break;
		case "1.11.0":
			text = "versoin: 1.11.0\nLog: Added Health Indicator for Block Alarm. Rename Hologram Bug Generator to Hologram Remover. Removed rev and dead for Block Alarm.";
			break;
		case "1.12.0":
			text = "version: 1.12.0\nLog: Added Grow Guide for Auto Splits. Fixed bugs. Fixed config. Added mod version on the display. Config reset when mod update. Added option which default value of mod be set.";
			break;
		case "1.12.1":
			text = "version: 1.12.1\nLog: Removed Hologram Remover. Added update checker.";
			break;
		case "1.12.2":
			text = "version: 1.12.2\nLog: Fixed update checker. Fixed /info command.";
			break;
		case "1.12.3":
			text = "version: 1.12.3\nLog: Fixed bugs. Fixed update checker.";
			break;
		case "1.13.0":
			text = "version: 1.13.0\nLog: Fixed bugs. Added Grow ESP. Wave delay of SST are off when use with SST.";
			break;
		case "1.13.1":
			text = "version: 1.13.1\nLog: Fixed Grow ESP.";
			break;
		case "1.14.0-pre1":
			text = "version: 1.14.0-pre1\nLog: Added Gstep Guide. Fixed DPS Counter, NOTLAST, Auto Splits, and Powerup Alarm. Fixed bugs.";
			break;
		case "1.14.0-pre2":
			text = "version: 1.14.0-pre2\nLog: Fixed bugs. Fixed Gstep Guide.";
			break;
		default:
			player.addChatComponentMessage(new ChatComponentText(getCommandUsage(null)));
			return;
		}
		player.addChatComponentMessage(new ChatComponentText(CommandInfo.LINE + "\n" + text + "\n" + CommandInfo.LINE));
	}

	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1) {
			return getListOfStringsMatchingLastWord(args,
					new String[] { "1.1.0", "1.2.0", "1.2.1", "1.3.0", "1.4.0", "1.4.1", "1.4.2", "1.4.3", "1.4.4",
							"1.4.5", "1.5.0", "1.5.1", "1.5.2", "1.6.0", "1.6.1", "1.6.2", "1.6.3", "1.6.4", "1.6.5",
							"1.7.0", "1.7.1", "1.7.2", "1.7.3", "1.7.4", "1.7.5", "1.7.6", "1.8.0", "1.8.1", "1.8.2",
							"1.8.3", "1.8.4", "1.8.5", "1.8.6", "1.8.7", "1.8.8", "1.8.9", "1.9.0", "1.9.1", "1.9.2",
							"1.10.0", "1.10.1", "1.10.2", "1.10.3", "1.11.0", "1.12.0", "1.12.1", "1.12.2", "1.12.3",
							"1.13.0", "1.13.1", "1.14.0-pre1", "1.14.0-pre2" });
		}
		return null;
	}
}
