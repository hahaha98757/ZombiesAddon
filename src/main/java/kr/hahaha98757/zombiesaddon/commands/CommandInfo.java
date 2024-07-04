package kr.hahaha98757.zombiesaddon.commands;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.ibm.icu.impl.ICUService.Key;
import com.mojang.realmsclient.gui.ChatFormatting;

import kr.hahaha98757.zombiesaddon.Keybinds;
import kr.hahaha98757.zombiesaddon.ZombiesAddon;
import kr.hahaha98757.zombiesaddon.listeners.EventListener;
import net.minecraft.client.settings.KeyBinding;
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
					+ "\nInfo: A mod that helps playing hypixel zombies.\nURL: https://github.com/hahaha98757/ZombiesAddon\n"
					+ LINE));
			return;
		}

		String text;

		switch (args[0]) {
		case "Cornering":
			text = "Name: Cornering\nVersion: 1.1.1\nCode by: syeyoung and thamid\nInfo: Toggles the visibility of players nearby.\nCommands: /cornering\nKeybinds: "
					+ Keyboard.getKeyName(Keybinds.toggleCornering.getKeyCode()) + "\nConfig: Used";
			break;
		case "BlockAlarm":
			text = "Name: Block Alarm\nVersion: 1.1.1\nCode by: syeyoung\nInfo: If you survived alone, show \"BLOCK\" on display.\nKeybinds: "
					+ Keyboard.getKeyName(Keybinds.toggleBlockAlarm.getKeyCode());
			break;
		case "DPSCounter":
			text = "Name: DPS Counter\nVersion: 1.3\nCode by: thamid\nInfo: Counts your party's DPS.";
			break;
		case "NotLast":
			text = "Name: Not Last\nVersion: 1.2\nCode by: thamid\nInfo: Shows who is kill the last in every round.";
			break;
		case "AutoSplits":
			text = "Name: Auto Splits\nVersion: 1.0\nCode by: thamid\nInfo: Run timer at start of round.";
			break;
		case "WaveDelays":
			text = "Name: Wave Delays\nCode by: hahaha98757\nInfo: Show wave delays.\nConfig: Used";
			break;
		case "ZSV":
			text = "Name: ZSV(Zombies Strat Viewer)\nVersion: 1.1.1\nCode by: syeyoung\nInfo: Displays the text in https://pastebin.com/ on the in-game screen.\nCommands: /ZSV and /ZSVLines";
			break;
		case "SLA":
			text = "Name: SLA(Spawn Limit Action)\nVersion: 1.1.1\nCode by: Stachelbeere1248\nInfo: Display number and name of windows where zombies can spawn.\nCommands: /SLA\nConfig: Used";
			break;
		case "AutoRejoin":
			text = "Name: Auto Rejoin\nVersion: 1.1\nCode by: thamid\nInfo: Automatically rejoin the Zombies game.\nKeybinds: "
					+ Keyboard.getKeyName(Keybinds.toggleAutoRejoin.getKeyCode()) + "\nConfig: Used";
			break;
		case "PowerupAlarm":
			text = "Name: Powerup Alarm\nCode by: hahaha98757\nInfo: You will receive an alarm when a powerup is spawned. You will know when powerup spawn and despawn.\nCommands: /powerupAlarm, /pow";
			break;
		case "GstepGuide":
			text = "Name: Gstep Guide\nCode by: hahaha98757\nInfo: Shows hitbox of armor stand in Gstep.";
			break;
		case "PlayerTracker":
			text = "Name: Player Tracker\nCode by: hahaha98757\nInfo: Shows the location of the player.";
			break;
		case "BossTracker":
			text = "Name: Boss Tracker\nCode by: hahaha98757\nInfo: Shows the location of the boss.";
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
					new String[] { "Cornering", "BlockAlarm", "DPSCounter", "NotLast", "AutoSplits", "WaveDelays", "ZSV", "SLA", "AutoRejoin", "PowerupAlarm",
							"GstepGuide", "PlayerTracker", "BossTracker" });
		}
		return null;
	}
}
