package kr.hahaha98757.zombiesaddon.listeners.powupalarm;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.listeners.EventListener;
import kr.hahaha98757.zombiesaddon.utils.GameDetect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PowerupAlarmListener {
	private static final String POWERUP_CHAT = " \u00A7ehas spawned";
	private static final String[] IGNORE_ENTITY_SET = { "Armor Stand", "\u00A7c\u00A7lTarget Practice",
			"\u00A7e\u00A7lHOLD SNEAK TO REVIVE!", "\u00A7e¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á¡á" };
	private static final Pattern REVIVE_SECONDS_PATTERN = Pattern.compile("\u00A7c\\d+\\.\\d+s");

	public static boolean insta;
	public static boolean max;
	public static boolean dg;
	public static boolean carpenter;
	public static boolean ss;
	public static boolean bg;

	private static final String[] INSTA_PATTERN1 = { "2", "5", "8", "11", "14", "17", "20", "23" };
	private static final String[] INSTA_PATTERN2 = { "3", "6", "9", "12", "15", "18", "21", "24" };
	private static final String[] MAX_PATTERN1 = { "2", "5", "8", "12", "16", "21", "26", "31", "36", "41", "46", "51",
			"61", "66", "71", "76", "81", "86", "91", "96" };
	private static final String[] MAX_PATTERN2 = { "3", "6", "9", "13", "17", "22", "27", "32", "37", "42", "47", "52",
			"62", "67", "72", "77", "82", "87", "92", "97", "102" };
	private static final String[] SS_PATTERN1 = { "5", "15", "45", "55", "65", "75", "85", "95", "105" };
	private static final String[] SS_PATTERN2 = { "6", "16", "26", "36", "46", "66", "76", "86", "96" };
	private static final String[] SS_PATTERN3 = { "7", "17", "27", "37", "47", "67", "77", "87", "97" };

	public static int instaP;
	public static int maxP;
	public static int ssP;

	public static boolean instaRound = true;
	public static boolean maxRound = true;
	public static boolean ssRound = true;

	public static Set<EntityLivingBase> spawnedEntities = new HashSet<>();

	@SubscribeEvent
	public void onPowerupText(RenderGameOverlayEvent.Post event) {// insta:1 max:2, dg:3, carpenter:4, ss:5, bg:6
		if (!ZombiesAddonConfig.enableMod) {
			return;
		}

		if (event.type != RenderGameOverlayEvent.ElementType.ALL) {
			return;
		}
		if (!ZombiesAddonConfig.togglePowerupAlarm) {
			return;
		}

		if (insta) {
			int timer = PowerupTimer.getTimer(1);

			int secondLeft = timer / 20;

			String timerText = String.format("%02d", secondLeft);

			String str = "\u00A7cInsta Kill: " + "\u00A7f" + timerText + "s";
			FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
			int stringHeight = fr.FONT_HEIGHT;
			int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
			int y = screenHeight - stringHeight;
			fr.drawStringWithShadow(str,
					((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
					y - 60, 0);
			if (timer == 1) {
				insta = false;
			}
		} else if (instaP != 0 && instaRound) {
			int round = GameDetect.getRound();
			int patternRound = 0;

			if (instaP == 2) {
				for (String str : INSTA_PATTERN1) {
					int i = Integer.parseInt(str);
					if (round <= i) {
						if (GameDetect.getMap() == 1 && i == 5) {
							patternRound = 8;
							break;
						}
						if (GameDetect.getMap() != 3 && i == 20) {
							patternRound = 23;
							break;
						}
						patternRound = i;
						break;
					}
				}

				if (patternRound != 0) {
					String str = "\u00A7cInsta Kill: " + "\u00A7fRound " + patternRound;
					FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
					int stringHeight = fr.FONT_HEIGHT;
					int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
					int y = screenHeight - stringHeight;
					fr.drawStringWithShadow(str, ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
							- fr.getStringWidth(str)), y - 60, 0);
				}
			} else if (instaP == 3) {
				for (String str : INSTA_PATTERN2) {
					int i = Integer.parseInt(str);
					if (round <= i) {
						if (GameDetect.getMap() != 3 && i == 15) {
							patternRound = 18;
							break;
						}
						if (GameDetect.getMap() == 3 && i == 24) {
							patternRound = 0;
							break;
						}
						patternRound = i;
						break;
					}
				}

				if (patternRound != 0) {
					String str = "\u00A7cInsta Kill: " + "\u00A7fRound " + patternRound;
					FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
					int stringHeight = fr.FONT_HEIGHT;
					int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
					int y = screenHeight - stringHeight;
					fr.drawStringWithShadow(str, ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
							- fr.getStringWidth(str)), y - 60, 0);
				}
			}
		}

		if (max) {
			int timer = PowerupTimer.getTimer(2);

			int secondLeft = timer / 20;

			String timerText = String.format("%02d", secondLeft);

			String str = "\u00A79Max Ammo: " + "\u00A7f" + timerText + "s";
			FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
			int stringHeight = fr.FONT_HEIGHT;
			int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
			int y = screenHeight - stringHeight;
			fr.drawStringWithShadow(str,
					((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
					y - 68, 0);
			if (timer == 1) {
				max = false;
			}
		} else if (maxP != 0 && maxRound) {
			int round = GameDetect.getRound();
			int patternRound = 0;

			if (maxP == 2) {
				for (String str : MAX_PATTERN1) {
					int i = Integer.parseInt(str);
					if (round <= i) {
						if (GameDetect.getMap() == 1 && i == 5) {
							patternRound = 8;
							break;
						}
						if (GameDetect.getMap() != 3 && i >= 31) {
							patternRound = 0;
							break;
						}
						patternRound = i;
						break;
					}
				}

				if (patternRound != 0) {
					String str = "\u00A79Max Ammo: " + "\u00A7fRound " + patternRound;
					FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
					int stringHeight = fr.FONT_HEIGHT;
					int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
					int y = screenHeight - stringHeight;
					fr.drawStringWithShadow(str, ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
							- fr.getStringWidth(str)), y - 68, 0);
				}
			} else if (maxP == 3) {
				for (String str : MAX_PATTERN2) {
					int i = Integer.parseInt(str);
					if (round <= i) {
						if (GameDetect.getMap() != 3 && i >= 32) {
							patternRound = 0;
							break;
						}
						patternRound = i;
						break;
					}
				}

				if (patternRound != 0) {
					String str = "\u00A79Max Ammo: " + "\u00A7fRound " + patternRound;
					FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
					int stringHeight = fr.FONT_HEIGHT;
					int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
					int y = screenHeight - stringHeight;
					fr.drawStringWithShadow(str, ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
							- fr.getStringWidth(str)), y - 68, 0);
				}
			}
		}

		if (dg) {
			int timer = PowerupTimer.getTimer(3);

			int secondsLeft = timer / 20;

			String timerText = String.format("%02d", secondsLeft);

			String str = "\u00A76Double Gold: " + "\u00A7f" + timerText + "s";
			FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
			int stringHeight = fr.FONT_HEIGHT;
			int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
			int y = screenHeight - stringHeight;
			fr.drawStringWithShadow(str,
					((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
					y - 76, 0);
			if (timer == 1) {
				dg = false;
			}
		}

		if (carpenter) {
			int timer = PowerupTimer.getTimer(4);

			int secondsLeft = timer / 20;

			String timerText = String.format("%02d", secondsLeft);

			String str = "\u00A79Carpenter: " + "\u00A7f" + timerText + "s";
			FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
			int stringHeight = fr.FONT_HEIGHT;
			int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
			int y = screenHeight - stringHeight;
			fr.drawStringWithShadow(str,
					((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
					y - 84, 0);
			if (timer == 1) {
				carpenter = false;
			}
		}

		if (ss) {
			int timer = PowerupTimer.getTimer(5);

			int secondsLeft = timer / 20;

			String timerText = String.format("%02d", secondsLeft);

			String str = "\u00A75Shopping Spree: " + "\u00A7f" + timerText + "s";
			FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
			int stringHeight = fr.FONT_HEIGHT;
			int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
			int y = screenHeight - stringHeight;
			fr.drawStringWithShadow(str,
					((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
					y - 92, 0);
			if (timer == 1) {
				ss = false;
			}
		} else if (ssP != 0 && GameDetect.getMap() == 3 && ssRound) {
			int round = GameDetect.getRound();
			int patternRound = 0;

			if (ssP == 5) {
				for (String str : SS_PATTERN1) {
					int i = Integer.parseInt(str);
					if (round <= i) {
						patternRound = i;
						break;
					}
				}

				if (patternRound != 0) {
					String str = "\u00A75Shopping Spree: " + "\u00A7fRound " + patternRound;
					FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
					int stringHeight = fr.FONT_HEIGHT;
					int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
					int y = screenHeight - stringHeight;
					fr.drawStringWithShadow(str, ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
							- fr.getStringWidth(str)), y - 92, 0);
				}
			} else if (ssP == 6) {
				for (String str : SS_PATTERN2) {
					int i = Integer.parseInt(str);
					if (round <= i) {
						patternRound = i;
						break;
					}
				}

				if (patternRound != 0) {
					String str = "\u00A75Shopping Spree: " + "\u00A7fRound " + patternRound;
					FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
					int stringHeight = fr.FONT_HEIGHT;
					int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
					int y = screenHeight - stringHeight;
					fr.drawStringWithShadow(str, ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
							- fr.getStringWidth(str)), y - 92, 0);
				}
			} else if (ssP == 7) {
				for (String str : SS_PATTERN3) {
					int i = Integer.parseInt(str);
					if (round <= i) {
						patternRound = i;
						break;
					}
				}

				if (patternRound != 0) {
					String str = "\u00A75Shopping Spree: " + "\u00A7fRound " + patternRound;
					FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
					int stringHeight = fr.FONT_HEIGHT;
					int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
					int y = screenHeight - stringHeight;
					fr.drawStringWithShadow(str, ((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth()
							- fr.getStringWidth(str)), y - 92, 0);
				}
			}
		}

		if (bg) {
			int timer = PowerupTimer.getTimer(6);

			int secondsLeft = timer / 20;

			String timerText = String.format("%02d", secondsLeft);

			String str = "\u00A76Bonus Gold: " + "\u00A7f" + timerText + "s";
			FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
			int stringHeight = fr.FONT_HEIGHT;
			int screenHeight = new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight();
			int y = screenHeight - stringHeight;
			fr.drawStringWithShadow(str,
					((new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() - fr.getStringWidth(str)),
					y - 100, 0);
			if (timer == 1) {
				bg = false;
			}
		}
	}

	@SubscribeEvent
	public void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent livingUpdateEvent) {
		if (!ZombiesAddonConfig.enableMod) {
			return;
		}

		if (!GameDetect.isZombiesGame() || !ZombiesAddonConfig.togglePowerupAlarm) {
			return;
		}
		EntityLivingBase entity = livingUpdateEvent.entityLiving;
		if (!(entity instanceof EntityArmorStand)) {
			return;
		}

		String name = entity.getName();

		for (String i : IGNORE_ENTITY_SET) {
			if (i.equals(name)) {
				return;
			}
		}

		if (spawnedEntities.contains(entity)) {
			return;
		}

		isInsta(entity);
		isMax(entity);
		isDG(entity);
		isCarpenter(entity);
		isSS(entity);
		isBG(entity);
	}

	public void isInsta(EntityLivingBase entity) {
		String name = EnumChatFormatting.getTextWithoutFormattingCodes(entity.getName())
				.replaceAll("[^A-Z\uAC00-\uD7A3]", "");
		if (name.equals("INSTAKILL") || name.equals("\uC989\uC2DC\ucc98\uCE58")) {
			Minecraft.getMinecraft().thePlayer
					.addChatMessage(new ChatComponentText("\u00A7cInsta Kill" + POWERUP_CHAT));
			spawnedEntities.add(entity);
			insta = true;
			PowerupTimer.resetTimer(1);

			for (String str : INSTA_PATTERN1) {
				if (str.equals(String.valueOf(GameDetect.getRound()))) {
					instaP = 2;
					break;
				}
			}
			for (String str : INSTA_PATTERN2) {
				if (str.equals(String.valueOf(GameDetect.getRound()))) {
					instaP = 3;
					break;
				}
			}

		} else {
			return;
		}
	}

	public void isMax(EntityLivingBase entity) {
		String name = EnumChatFormatting.getTextWithoutFormattingCodes(entity.getName())
				.replaceAll("[^A-Z\uAC00-\uD7A3]", "");
		if (name.equals("MAXAMMO") || name.equals("\uD0C4\uC57D\uCDA9\uC804")) {
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("\u00A79Max Ammo" + POWERUP_CHAT));
			spawnedEntities.add(entity);
			max = true;
			PowerupTimer.resetTimer(2);

			for (String str : MAX_PATTERN1) {
				if (str.equals(String.valueOf(GameDetect.getRound()))) {
					maxP = 2;
					break;
				}
			}
			for (String str : MAX_PATTERN2) {
				if (str.equals(String.valueOf(GameDetect.getRound()))) {
					maxP = 3;
					break;
				}
			}

		} else {
			return;
		}
	}

	public void isDG(EntityLivingBase entity) {
		String name = EnumChatFormatting.getTextWithoutFormattingCodes(entity.getName())
				.replaceAll("[^A-Z\uAC00-\uD7A3]", "");
		if (name.equals("DOUBLEGOLD") || name.equals("\uB354\uBE14\uACE8\uB4DC")) {
			Minecraft.getMinecraft().thePlayer
					.addChatMessage(new ChatComponentText("\u00A76Double Gold" + POWERUP_CHAT));
			spawnedEntities.add(entity);
			dg = true;
			PowerupTimer.resetTimer(3);
		} else {
			return;
		}
	}

	public void isCarpenter(EntityLivingBase entity) {
		String name = EnumChatFormatting.getTextWithoutFormattingCodes(entity.getName())
				.replaceAll("[^A-Z\uAC00-\uD7A3]", "");
		if (name.equals("CARPENTER") || name.equals("\uCE74\uD39C\uD130")) {
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("\u00A79Carpenter" + POWERUP_CHAT));
			spawnedEntities.add(entity);
			carpenter = true;
			PowerupTimer.resetTimer(4);
		} else {
			return;
		}
	}

	public void isSS(EntityLivingBase entity) {
		String name = EnumChatFormatting.getTextWithoutFormattingCodes(entity.getName())
				.replaceAll("[^A-Z\uAC00-\uD7A3]", "");
		if (name.equals("SHOPPINGSPREE") || name.equals("\uC9C0\uB984\uC2E0\uAC15\uB9BC")) {
			Minecraft.getMinecraft().thePlayer
					.addChatMessage(new ChatComponentText("\u00A75Shopping Spree" + POWERUP_CHAT));
			spawnedEntities.add(entity);
			ss = true;
			PowerupTimer.resetTimer(5);

			for (String str : SS_PATTERN1) {
				if (str.equals(String.valueOf(GameDetect.getRound()))) {
					ssP = 5;
					break;
				}
			}
			for (String str : SS_PATTERN2) {
				if (str.equals(String.valueOf(GameDetect.getRound()))) {
					ssP = 6;
					break;
				}
			}
			for (String str : SS_PATTERN3) {
				if (str.equals(String.valueOf(GameDetect.getRound()))) {
					ssP = 7;
					break;
				}
			}

		} else {
			return;
		}
	}

	public void isBG(EntityLivingBase entity) {
		String name = EnumChatFormatting.getTextWithoutFormattingCodes(entity.getName())
				.replaceAll("[^A-Z\uAC00-\uD7A3]", "");
		if (name.equals("BONUSGOLD") || name.equals("\uBCF4\uB108\uC2A4\uACE8\uB4DC")) {
			Minecraft.getMinecraft().thePlayer
					.addChatMessage(new ChatComponentText("\u00A76Bonus Gold" + POWERUP_CHAT));
			spawnedEntities.add(entity);
			bg = true;
			PowerupTimer.resetTimer(6);
		} else {
			return;
		}
	}

	@SubscribeEvent
	public void onChatReceived(ClientChatReceivedEvent event) {
		if (!ZombiesAddonConfig.enableMod) {
			return;
		}

		String message = event.message.getUnformattedText();
		if (message.contains(">")) {
			return;
		}

		if (!message.contains("activated") && !message.contains("\uBC1C\uB3D9")) {
			return;
		}

		if (!ZombiesAddonConfig.togglePowerupAlarm) {
			return;
		}

		message = EnumChatFormatting.getTextWithoutFormattingCodes(message).replaceAll("[^A-Za-z0-9\uAC00-\uD7A3]", "");

		if (message.contains("InstaKill") || message.contains("\uC989\uC2DC\ucc98\uCE58")) {
			insta = false;
		} else if (message.contains("MaxAmmo") || message.contains("\uD0C4\uC57D\uBCF4\uAE09")) {
			max = false;
		} else if (message.contains("DoubleGold") || message.contains("\uB354\uBE14\uACE8\uB4DC")) {
			dg = false;
		} else if (message.contains("Carpenter") || message.contains("\uBAA9\uC218")) {
			carpenter = false;
		} else if (message.contains("ShoppingSpree") || message.contains("\uC9C0\uB984\uC2E0\uAC15\uB9BC")) {
			ss = false;
		} else if (message.contains("BonusGold") || message.contains("\uBCF4\uB108\uC2A4\uACE8\uB4DC")) {
			bg = false;
		}
	}

}
