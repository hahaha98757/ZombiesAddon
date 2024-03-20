package kr.hahaha98757.zombiesaddon.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import kr.hahaha98757.zombiesaddon.listeners.ZSVListener;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

public class CommandZSV extends CommandBase implements ICommand {
	public static boolean ZSV = false;
	public static boolean URL = false;

	public String getCommandName() {
		return "ZSV";
	}

	public int getRequiredPermissionLevel() {
		return 0;
	}

	public String getCommandUsage(ICommandSender sender) {
		return "\u00A7cUsage: /ZSV <url|de|bb|aa|off>\nThe url must start with \"https://pastebin.com/raw/\"";
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
		URL = true;
		if (!args[0].startsWith("https://pastebin.com/raw/")) {
			switch (args[0]) {
			case "off":
				ZSVListener.START_LINES.clear();
				ZSVListener.START_LINES.add("");
				ZSVListener.currentLine = 0;
				player.addChatComponentMessage(new ChatComponentText("\u00A7eZSV: Set ZSV to \u00A7coff"));
				ZSV = false;
				return;
			case "de":
				args[0] = "https://pastebin.com/raw/sb65mNA9";
				player.addChatComponentMessage(new ChatComponentText("\u00A7eZSV: Set ZSV to \u00A7ade"));
				URL = false;
				break;
			case "bb":
				args[0] = "https://pastebin.com/raw/NrQEhqQy";
				player.addChatComponentMessage(new ChatComponentText("\u00A7eZSV: Set ZSV to \u00A7abb"));
				URL = false;
				break;

			case "aa":
				args[0] = "https://pastebin.com/raw/4KE9jkXY";
				player.addChatComponentMessage(new ChatComponentText("\u00A7eZSV: Set ZSV to \u00A7aaa"));
				URL = false;
				break;
			default:
				player.addChatComponentMessage(new ChatComponentText(getCommandUsage(null)));
				return;
			}
		}
		ZSV = true;
		ZSVListener.START_LINES.clear();
		ZSVListener.START_LINES.add("");
		ZSVListener.currentLine = 0;
		try {
			URL url = new URL(args[0]);
			HttpURLConnection huc = (HttpURLConnection) url.openConnection();
			huc.setDoInput(true);
			huc.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));

			String str;
			while ((str = br.readLine()) != null) {
				ZSVListener.START_LINES.add(str);
			}

			ZSVListener.recalcActualLines();
			br.close();
			huc.getInputStream().close();
			if (URL) {
				sender.addChatMessage(new ChatComponentText(String.format("\u00A7eZSV: Set ZSV to \u00A7aon")));
			}
		} catch (MalformedURLException var7) {
			var7.printStackTrace();
		} catch (IOException var8) {
			var8.printStackTrace();
		}
	}

	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1) {
			return getListOfStringsMatchingLastWord(args, new String[] { "de", "bb", "aa", "off" });
		}
		return null;
	}
}
