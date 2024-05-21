package kr.hahaha98757.zombiesaddon.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.data.Room;
import kr.hahaha98757.zombiesaddon.data.Window;
import kr.hahaha98757.zombiesaddon.listeners.SLA.DisplaySLAOverlayListener;
import kr.hahaha98757.zombiesaddon.listeners.SLA.advanced.AdvancedSLAListener;
import kr.hahaha98757.zombiesaddon.utils.Point;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

public class CommandSLA extends CommandBase {
	public static final CommandSLA instance = new CommandSLA(new DisplaySLAOverlayListener());
	private final DisplaySLAOverlayListener listener;
	private final Map<String, Room[]> maps;

	public CommandSLA(DisplaySLAOverlayListener listener) {
		this.listener = listener;
		this.maps = new HashMap();
		this.maps.put("bb", new Room[] { new Room("Courtyard",
				new Window[] { new Window(new Point(24.5, 69.5, -18.5)), new Window(new Point(30.5, 69.5, 10.5)),
						new Window(new Point(19.5, 69.5, 20.5)), new Window(new Point(12.5, 69.5, -17.5)) }),
				new Room("Mansion",
						new Window[] { new Window(new Point(0.5, 74.5, -17.5)), new Window(new Point(0.5, 74.5, 18.5)),
								new Window(new Point(-12.5, 73.5, 28.5)) }),
				new Room("Library",
						new Window[] { new Window(new Point(1.5, 74.5, -44.5)),
								new Window(new Point(-20.5, 74.5, -29.5)), new Window(new Point(-40.5, 74.5, -30.5)),
								new Window(new Point(-39.5, 74.5, -57.5)), new Window(new Point(-54.5, 74.5, -46.5)),
								new Window(new Point(-53.5, 74.5, -33.5)) }),
				new Room("Dungeon",
						new Window[] { new Window(new Point(-28.5, 68.5, -34.5)),
								new Window(new Point(-36.5, 68.5, -11.5)), new Window(new Point(-9.5, 68.5, -18.5)),
								new Window(new Point(-9.5, 68.5, -22.5)), new Window(new Point(-10.5, 68.5, -49.5)) }),
				new Room("Crypts",
						new Window[] { new Window(new Point(-3.5, 68.5, -2.5)), new Window(new Point(-15.5, 68.5, 0.5)),
								new Window(new Point(-28.5, 68.5, 20.5)) }),
				new Room("Graveyard",
						new Window[] { new Window(new Point(-6.5, 68.5, 33.5)),
								new Window(new Point(-35.5, 68.5, 31.5)), new Window(new Point(-16.5, 68.5, 50.5)) }),
				new Room("Balcony",
						new Window[] { new Window(new Point(-41.5, 68.5, 27.5)),
								new Window(new Point(-53.5, 72.5, 12.5)), new Window(new Point(-56.5, 74.5, 2.5)),
								new Window(new Point(-32.5, 74.5, -18.5)) }),
				new Room("Great Hall", new Window[] { new Window(new Point(-19.5, 74.5, -13.5)),
						new Window(new Point(-31.5, 76.5, 15.5)), new Window(new Point(-27.5, 74.5, 15.5)) }) });
		this.maps.put("de", new Room[] {
				new Room("Alley",
						new Window[] { new Window(new Point(6.5, 69.5, 31.5)), new Window(new Point(4.5, 69.5, 43.5)),
								new Window(new Point(39.5, 70.5, 8.5)), new Window(new Point(42.5, 70.5, 29.5)) }),
				new Room("Office",
						new Window[] { new Window(new Point(42.5, 76.5, 26.5)), new Window(new Point(52.5, 76.5, 31.5)),
								new Window(new Point(57.5, 76.5, 64.5)) }),
				new Room("Hotel",
						new Window[] { new Window(new Point(0.5, 68.5, 46.5)), new Window(new Point(-9.5, 68.5, 14.5)),
								new Window(new Point(25.5, 69.5, -3.5)), new Window(new Point(26.5, 69.5, 3.5)),
								new Window(new Point(-3.5, 76.5, -21.5)), new Window(new Point(25.5, 76.5, -5.5)) }),
				new Room("Apartments",
						new Window[] { new Window(new Point(19.5, 76.5, 9.5)), new Window(new Point(-15.5, 76.5, 15.5)),
								new Window(new Point(-13.5, 76.5, 51.5)), new Window(new Point(-4.5, 76.5, 62.5)) }),
				new Room("Power Station",
						new Window[] { new Window(new Point(3.5, 83.5, 62.5)), new Window(new Point(-2.5, 83.5, 32.5)),
								new Window(new Point(-5.5, 68.5, 66.5)) }),
				new Room("Rooftop",
						new Window[] { new Window(new Point(-15.5, 83.5, 64.5)),
								new Window(new Point(-13.5, 83.5, 30.5)), new Window(new Point(-49.5, 83.5, 38.5)),
								new Window(new Point(-37.5, 83.5, 25.5)) }),
				new Room("Gallery",
						new Window[] { new Window(new Point(22.5, 76.5, 77.5)), new Window(new Point(30.5, 76.5, 54.5)),
								new Window(new Point(15.5, 76.5, 65.5)) }),
				new Room("Garden", new Window[] { new Window(new Point(0.5, 68.5, -16.5)),
						new Window(new Point(24.5, 68.5, -33.5)), new Window(new Point(34.5, 68.5, -16.5)) }) });
		this.maps.put("aa", new Room[] {
				new Room("Park Entrance",
						new Window[] { new Window(new Point(6.5, 72.5, 31.5)), new Window(new Point(-10.5, 72.5, -5.5)),
								new Window(new Point(-21.5, 72.5, 10.5)), new Window(new Point(-22.5, 72.5, 15.5)),
								new Window(new Point(22.5, 72.5, 13.5)) }),
				new Room("Roller Coaster",
						new Window[] { new Window(new Point(-28.5, 72.5, 27.5)),
								new Window(new Point(-12.5, 72.5, 39.5)) }),
				new Room("Ferris Wheel",
						new Window[] { new Window(new Point(17.5, 72.5, 44.5)),
								new Window(new Point(27.5, 72.5, 31.5)) }),
				new Room("Bumper Cars", new Window[] { new Window(new Point(33.5, 73.5, -1.5)),
						new Window(new Point(22.5, 73.5, -13.5)) }) });
		this.maps.put("off", (Room[]) null);
		listener.setRooms((Room[]) ((Room[]) this.maps.get("off")));
	}

	public String getCommandName() {
		return "SLA";
	}

	public int getRequiredPermissionLevel() {
		return 0;
	}

	public String getCommandUsage(ICommandSender sender) {
		return "\u00A7cUsage: /SLA <de|bb|aa|off>";
	}

	public void processCommand(ICommandSender sender, String[] args) {
		if (!(sender instanceof EntityPlayer)) {
			return;
		}
		EntityPlayer player = (EntityPlayer) sender;

		if (args.length >= 1) {
			if (!args[0].equals("de") && !args[0].equals("bb") && !args[0].equals("aa") && !args[0].equals("off")) {
				player.addChatComponentMessage(new ChatComponentText(getCommandUsage(null)));
			} else if (args[0].equals("off")) {
				player.addChatComponentMessage(new ChatComponentText("\u00A7eSLA: Set SLA to \u00A7coff"));
				this.listener.setRooms((Room[]) ((Room[]) this.maps.get(args[0])));

				AdvancedSLAListener.drop();
			} else {
				if (ZombiesAddonConfig.advancedSLA) {
					switch (args[0]) {
					case "de":
						AdvancedSLAListener.instance = new AdvancedSLAListener(1);
						Minecraft.getMinecraft().thePlayer
								.addChatMessage(new ChatComponentText("\u00A7eSLA: Set SLA to \u00A7ade"));
						break;
					case "bb":
						AdvancedSLAListener.instance = new AdvancedSLAListener(2);
						Minecraft.getMinecraft().thePlayer
								.addChatMessage(new ChatComponentText("\u00A7eSLA: Set SLA to \u00A7abb"));
						break;
					case "aa":
						AdvancedSLAListener.instance = new AdvancedSLAListener(3);
						Minecraft.getMinecraft().thePlayer
								.addChatMessage(new ChatComponentText("\u00A7eSLA: Set SLA to \u00A7aaa"));
						break;
					}

				} else {
					player.addChatComponentMessage(
							new ChatComponentText(String.format("\u00A7eSLA: Set SLA to \u00A7a%s", args[0])));
					this.listener.setRooms((Room[]) ((Room[]) this.maps.get(args[0])));
				}
			}
		} else {
			player.addChatComponentMessage(new ChatComponentText(getCommandUsage(null)));
		}
	}

	public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1) {
			return getListOfStringsMatchingLastWord(args, new String[] { "de", "bb", "aa", "off" });
		}
		return null;
	}

	public void autoSLA(int map) {

		switch (map) {
		case 0:
			listener.setRooms((Room[]) ((Room[]) maps.get("off")));
			break;
		case 1:
			if (ZombiesAddonConfig.advancedSLA) {
				AdvancedSLAListener.instance = new AdvancedSLAListener(1);
			} else {
				listener.setRooms((Room[]) ((Room[]) maps.get("de")));
			}
			Minecraft.getMinecraft().thePlayer
					.addChatComponentMessage(new ChatComponentText("\u00A7eAuto SLA: Set SLA to \u00A7ade"));
			break;
		case 2:
			if (ZombiesAddonConfig.advancedSLA) {
				AdvancedSLAListener.instance = new AdvancedSLAListener(2);
			} else {
				listener.setRooms((Room[]) ((Room[]) maps.get("bb")));
			}
			Minecraft.getMinecraft().thePlayer
					.addChatComponentMessage(new ChatComponentText("\u00A7eAuto SLA: Set SLA to \u00A7abb"));
			break;
		case 3:
			if (ZombiesAddonConfig.advancedSLA) {
				AdvancedSLAListener.instance = new AdvancedSLAListener(3);
			} else {
				listener.setRooms((Room[]) ((Room[]) maps.get("aa")));
			}
			Minecraft.getMinecraft().thePlayer
					.addChatComponentMessage(new ChatComponentText("\u00A7eAuto SLA: Set SLA to \u00A7aaa"));
			break;
		}
	}
}
