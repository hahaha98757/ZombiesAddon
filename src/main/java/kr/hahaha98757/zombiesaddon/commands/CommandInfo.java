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
		return "\u00A7cUsage: /info [mod name] or /info ZombiesAddon [option name]";
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
					+ "\nMod list: Cornering, Block Alarm, DPS Counter, NOTLAST, Auto Splits, ZSV, SLA, Advanced SLA, and Auto Rejoin.\nOption list: Powerup Alarm, Boss Alarm, Lrod Order, and Hologram Remover.\nInfo: A mod that helps playing hypixel zombies.\nCommand tips: Use /info [Cornering|BlockAlarm|DPSCounter|NOTLAST|AutoSplits|ZSV|SLA|AdvancedSLA|AutoRejoin|ZombiesAddon] to view mod's description.\nURL: https://blog.naver.com/hahaha98757/223012598464\n"
					+ LINE));
			return;
		}

		String text;

		switch (args[0]) {
		case "Cornering":
			text = "Name: Cornering\nVersion: 1.1.1\nCode by: syeyoung and thamid\nInfo: Toggles the visibility of players nearby.\nCommands: /cornering\nKeybinds: V\nConfig: Used";
			break;
		case "BlockAlarm":
			text = "Name: Block Alarm\nVersion: 1.1.1\nCode by: syeyoung\nInfo: Show \"BLOCK\" if you survived alone.\nKeybinds: B\nConfig: Used";
			break;
		case "DPSCounter":
			text = "Name: DPS Counter\nVersion: 1.3\nCode by: thamid\nInfo: Counts your party's DPS.\nKeybinds: X";
			break;
		case "NOTLAST":
			text = "Name: NOTLAST\nVersion: 1.2\nCode by: thamid\nInfo: Shows who is last every round.\nKeybinds: L";
			break;
		case "AutoSplits":
			text = "Name: Auto Splits\nVersion: 1.0\nCode by: thamid\nInfo: LiveSplits integration for Zombies.\nKeybinds: O\nConfig: Used";
			break;
		case "ZSV":
			text = "Name: ZSV(Zombies Strat Viewer)\nVersion: 1.1.1\nCode by: syeyoung\nInfo: A mod that helps viewing zombies strat on hypixel zombies.\nCommands: /ZSV and /ZSVLines";
			break;
		case "SLA":
			text = "Name: SLA(Spawn Limit Action)\nVersion: 1.3\nCode by: thamid\nInfo: SLA HUD Range Mod.\nCommands: /SLA\nConfig: Used";
			break;
		case "AdvancedSLA":
			text = "Name: Advanced SLA\nVersion: 1.1.1\nCode by: Stachelbeere1248\nInfo: SLA is displayed in more detail.\nCommands: /SLA\nConfig: Used";
			break;
		case "AutoRejoin":
			text = "Name: Auto Rejoin\nVersion: 1.1\nCode by: thamid\nInfo: Automatically rejoins Zombies games.\nKeybinds: Null";
			break;
		case "ZombiesAddon":
			if (args.length == 1) {
				player.addChatComponentMessage(new ChatComponentText(LINE
						+ "\nInfo: Mods made by hahaha98757.\nOption list: Powerup Alarm, Boss Alarm, Lrod Order, and Hologram Remover.\nCommand tips: Use /info ZombiesAddon [PowerupAlarm|BossAlarm|LrodOrder|HologramRemover] to view option's description.\n"
						+ LINE));
				return;
			}
			switch (args[1]) {
			case "PowerupAlarm":
				text = "Name: Powerup Alarm\nCode by: hahaha98757\nInfo: You will receive an alarm when a power-up is spawned.\nCommands: /powerupAlarm\nKeybinds: P\nConfig: Used";
				break;
			case "BossAlarm":
				text = "Name: Boss Alarm\nCode by: hahaha98757\nInfo: You can tell which area the boss spawned in.\nConfig: Used";
				break;
			case "LrodOrder":
				text = "Name: Lrod Order\nCode by: hahaha9875\nInfo: Shows the order of use of Lrod.\nConfig: Used";
				break;
			case "HologramBug":
				text = "Name: Hologram Remover\nCode by: hahaha98757\nInfo: Remove hologram in Gstep. (may fail)\nConfig: Used";
				break;
			}
		default:
			player.addChatComponentMessage(new ChatComponentText(getCommandUsage(null)));
			return;
		}
		player.addChatComponentMessage(new ChatComponentText(LINE + "\n" + text + "\n" + LINE));
	}

	@Override
	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		switch (args.length) {
		case 1:
			return getListOfStringsMatchingLastWord(args, new String[] { "Cornering", "BlockAlarm", "DPSCounter",
					"NOTLAST", "AutoSplits", "ZSV", "SLA", "AdvancedSLA", "AutoRejoin", "ZombiesAddon" });
		case 2:
			if (!args[0].equals("ZombiesAddon")) {
				return null;
			}
			return getListOfStringsMatchingLastWord(args,
					new String[] { "PowerupAlarm", "BossAlarm", "LrodOrder", "HologramRemover" });
		default:
			return null;
		}
	}
}