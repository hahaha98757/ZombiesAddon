package kr.hahaha98757.zombiesaddon.listeners;

import java.util.HashSet;
import java.util.Set;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class BossAlarmListener {
	private static Set<EntityLivingBase> spawnedEntities = new HashSet<>();

	private static final String BOSS_BAR = "\u00A78[\u00A7c|\u00A7c|\u00A7c|\u00A7c|\u00A7c|\u00A7c|\u00A7c|\u00A7c|\u00A7c|\u00A7c|\u00A7c|\u00A7c|\u00A7c|\u00A7c|\u00A7c|\u00A7c|\u00A7c|\u00A7c|\u00A7c|\u00A7c|\u00A78]";

	private static final double MAX_ALLOWED_DIFFERENCE = 1;

	private static final double[][] DE_ALLEY = { { 6.5, 69.5, 31.5 }, { 4.5, 69.5, 43.5 }, { 39.5, 70.5, 8.5 },
			{ 42.5, 70.5, 29.5 } };
	private static final double[][] DE_OFFICE = { { 42.5, 76.5, 26.5 }, { 52.5, 76.5, 31.5 }, { 57.5, 76.5, 64.5 } };
	private static final double[][] DE_HOTEL = { { 0.5, 68.5, 46.5 }, { -9.5, 68.5, 14.5 }, { 25.5, 69.5, -3.5 },
			{ 26.5, 69.5, 3.5 }, { -3.5, 76.5, -21.5 }, { 25.5, 76.5, -5.5 } };
	private static final double[][] DE_APT = { { 19.5, 76.5, 9.5 }, { -15.5, 76.5, 15.5 }, { -13.5, 76.5, 51.5 },
			{ -4.5, 76.5, 62.5 } };
	private static final double[][] DE_PS = { { 3.5, 83.5, 62.5 }, { -2.5, 83.5, 32.5 }, { -5.5, 68.5, 66.5 } };
	private static final double[][] DE_ROOF = { { -15.5, 83.5, 64.5 }, { -13.5, 83.5, 30.5 }, { -49.5, 83.5, 38.5 },
			{ -37.5, 83.5, 25.5 } };
	private static final double[][] DE_GALL = { { 22.5, 76.5, 77.5 }, { 30.5, 76.5, 54.5 }, { 15.5, 76.5, 65.5 } };
	private static final double[][] DE_GARDEN = { { 0.5, 68.5, -16.5 }, { 24.5, 68.5, -33.5 }, { 34.5, 68.5, -16.5 } };

	private static final double[][] BB_CY = { { 24.5, 69.5, -18.5 }, { 30.5, 69.5, 10.5 }, { 19.5, 69.5, 20.5 },
			{ 12.5, 69.5, -17.5 } };
	private static final double[][] BB_MANSION = { { 0.5, 74.5, -17.5 }, { 0.5, 74.5, 18.5 }, { -12.5, 73.5, 28.5 } };
	private static final double[][] BB_LIB = { { 1.5, 74.5, -44.5 }, { -20.5, 74.5, -29.5 }, { -40.5, 74.5, -30.5 },
			{ -39.5, 74.5, -57.5 }, { -54.5, 74.5, -46.5 }, { -53.5, 74.5, -33.5 } };
	private static final double[][] BB_DUN = { { -28.5, 68.5, -34.5 }, { -36.5, 68.5, -11.5 }, { -9.5, 68.5, -18.5 },
			{ -9.5, 68.5, -22.5 }, { -10.5, 68.5, -49.5 } };
	private static final double[][] BB_CRYPTS = { { -3.5, 68.5, -2.5 }, { -15.5, 68.5, 0.5 }, { -28.5, 68.5, 20.5 } };
	private static final double[][] BB_GY = { { -6.5, 68.5, 33.5 }, { -35.5, 68.5, 31.5 }, { -16.5, 68.5, 50.5 } };
	private static final double[][] BB_BAL = { { -41.5, 68.5, 27.5 }, { -53.5, 72.5, 12.5 }, { -56.5, 74.5, 2.5 },
			{ -32.5, 74.5, -18.5 } };
	private static final double[][] BB_HALL = { { -19.5, 74.5, -13.5 }, { -31.5, 76.5, 15.5 }, { -27.5, 74.5, 15.5 } };

	private static int lang = -1;// Unknown: -1, English: 0, Korean: 1

	private static boolean resetLang;

	private static String bossName;

	private boolean isPos(double a, double b) {
		return Math.abs(a - b) <= MAX_ALLOWED_DIFFERENCE;
	}

	private boolean isWindow(Entity entity, double[] window) {
		if (isPos(entity.posX, window[0]) && isPos(entity.posY, window[1]) && isPos(entity.posZ, window[2])) {
			return true;
		}
		return false;
	}

	@SubscribeEvent
	public void bossDetect(LivingEvent.LivingUpdateEvent event) {
		if (!ZombiesAddonConfig.bossAlarm) {
			return;
		}

		EntityLivingBase entity = event.entityLiving;
		if (!(entity instanceof EntityArmorStand) || !entity.getName().equals(BOSS_BAR)) {
			return;
		}

		if (spawnedEntities.contains(entity)) {
			return;
		}

		bossPos(entity);
		spawnedEntities.add(entity);
	}

	public void bossPos(Entity entity) {
		if (isWindow(entity, DE_ALLEY[0]) || isWindow(entity, DE_ALLEY[1]) || isWindow(entity, DE_ALLEY[2])
				|| isWindow(entity, DE_ALLEY[3])) {
			if (lang == 0) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
						bossName + " \u00A7r\u00A77has spawned in the \u00A7r\u00A7a\u00A7lAlley!"));
			} else if (lang == 1) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(bossName
						+ "\u00A7r\u00A77\uC774(\uAC00) \u00A7r\u00A7a\u00A7l\uACE8\uBAA9\u00A7r\u00A77\uC5D0\uC11C \uC18C\uD658\uB418\uC5C8\uC2B5\uB2C8\uB2E4!"));
			}
		}
		if (isWindow(entity, DE_OFFICE[0]) || isWindow(entity, DE_OFFICE[1]) || isWindow(entity, DE_OFFICE[2])) {
			if (lang == 0) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
						bossName + " \u00A7r\u00A77has spawned in the \u00A7r\u00A7a\u00A7lOffice!"));
			} else if (lang == 1) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(bossName
						+ "\u00A7r\u00A77\uC774(\uAC00) \u00A7r\u00A7a\u00A7l\uC0AC\uBB34\uC2E4\u00A7r\u00A77\uC5D0\uC11C \uC18C\uD658\uB418\uC5C8\uC2B5\uB2C8\uB2E4!"));
			}
		}
		if (isWindow(entity, DE_HOTEL[0]) || isWindow(entity, DE_HOTEL[1]) || isWindow(entity, DE_HOTEL[2])
				|| isWindow(entity, DE_HOTEL[3]) || isWindow(entity, DE_HOTEL[4]) || isWindow(entity, DE_HOTEL[5])) {
			if (lang == 0) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
						bossName + " \u00A7r\u00A77has spawned in the \u00A7r\u00A7a\u00A7lHotel!"));
			} else if (lang == 1) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(bossName
						+ "\u00A7r\u00A77\uC774(\uAC00) \u00A7r\u00A7a\u00A7l\uD638\uD154\u00A7r\u00A77\uC5D0\uC11C \uC18C\uD658\uB418\uC5C8\uC2B5\uB2C8\uB2E4!"));
			}
		}
		if (isWindow(entity, DE_APT[0]) || isWindow(entity, DE_APT[1]) || isWindow(entity, DE_APT[2])
				|| isWindow(entity, DE_APT[3])) {
			if (lang == 0) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
						bossName + " \u00A7r\u00A77has spawned in the \u00A7r\u00A7a\u00A7lApartment!"));
			} else if (lang == 1) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(bossName
						+ "\u00A7r\u00A77\uC774(\uAC00) \u00A7r\u00A7a\u00A7l\uC544\uD30C\uD2B8\u00A7r\u00A77\uC5D0\uC11C \uC18C\uD658\uB418\uC5C8\uC2B5\uB2C8\uB2E4!"));
			}
		}
		if (isWindow(entity, DE_PS[0]) || isWindow(entity, DE_PS[1]) || isWindow(entity, DE_PS[2])) {
			if (lang == 0) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
						bossName + " \u00A7r\u00A77has spawned in the \u00A7r\u00A7a\u00A7lPower Station!"));
			} else if (lang == 1) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(bossName
						+ "\u00A7r\u00A77\uC774(\uAC00) \u00A7r\u00A7a\u00A7l\uBC1C\uC804\uC18C\u00A7r\u00A77\uC5D0\uC11C \uC18C\uD658\uB418\uC5C8\uC2B5\uB2C8\uB2E4!"));
			}
		}
		if (isWindow(entity, DE_ROOF[0]) || isWindow(entity, DE_ROOF[1]) || isWindow(entity, DE_ROOF[2])
				|| isWindow(entity, DE_ROOF[3])) {
			if (lang == 0) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
						bossName + " \u00A7r\u00A77has spawned in the \u00A7r\u00A7a\u00A7lRoof Top!"));
			} else if (lang == 1) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(bossName
						+ "\u00A7r\u00A77\uC774(\uAC00) \u00A7r\u00A7a\u00A7l\uC625\uC0C1\u00A7r\u00A77\uC5D0\uC11C \uC18C\uD658\uB418\uC5C8\uC2B5\uB2C8\uB2E4!"));
			}
		}
		if (isWindow(entity, DE_GALL[0]) || isWindow(entity, DE_GALL[1]) || isWindow(entity, DE_GALL[2])) {
			if (lang == 0) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
						bossName + " \u00A7r\u00A77has spawned in the \u00A7r\u00A7a\u00A7lGallery!"));
			} else if (lang == 1) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(bossName
						+ "\u00A7r\u00A77\uC774(\uAC00) \u00A7r\u00A7a\u00A7l\uBBF8\uC220\uAD00\u00A7r\u00A77\uC5D0\uC11C \uC18C\uD658\uB418\uC5C8\uC2B5\uB2C8\uB2E4!"));
			}
		}
		if (isWindow(entity, DE_GARDEN[0]) || isWindow(entity, DE_GARDEN[1]) || isWindow(entity, DE_GARDEN[2])) {
			if (lang == 0) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
						bossName + " \u00A7r\u00A77has spawned in the \u00A7r\u00A7a\u00A7lGarden!"));
			} else if (lang == 1) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(bossName
						+ "\u00A7r\u00A77\uC774(\uAC00) \u00A7r\u00A7a\u00A7l\uC815\uC6D0\u00A7r\u00A77\uC5D0\uC11C \uC18C\uD658\uB418\uC5C8\uC2B5\uB2C8\uB2E4!"));
			}
		}

		if (isWindow(entity, BB_CY[0]) || isWindow(entity, BB_CY[1]) || isWindow(entity, BB_CY[2])
				|| isWindow(entity, BB_CY[3])) {
			if (lang == 0) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
						bossName + " \u00A7r\u00A77has spawned in the \u00A7r\u00A7a\u00A7lCourtyard!"));
			} else if (lang == 1) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(bossName
						+ "\u00A7r\u00A77\uC774(\uAC00) \u00A7r\u00A7a\u00A7l\uC815\uC6D0\u00A7r\u00A77\uC5D0\uC11C \uC18C\uD658\uB418\uC5C8\uC2B5\uB2C8\uB2E4!"));
			}
		}
		if (isWindow(entity, BB_MANSION[0]) || isWindow(entity, BB_MANSION[1]) || isWindow(entity, BB_MANSION[2])) {
			if (lang == 0) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
						bossName + " \u00A7r\u00A77has spawned in the \u00A7r\u00A7a\u00A7lMansion!"));
			} else if (lang == 1) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(bossName
						+ "\u00A7r\u00A77\uC774(\uAC00) \u00A7r\u00A7a\u00A7l\uC800\uD0DD\u00A7r\u00A77\uC5D0\uC11C \uC18C\uD658\uB418\uC5C8\uC2B5\uB2C8\uB2E4!"));
			}
		}
		if (isWindow(entity, BB_LIB[0]) || isWindow(entity, BB_LIB[1]) || isWindow(entity, BB_LIB[2])
				|| isWindow(entity, BB_LIB[3]) || isWindow(entity, BB_LIB[4]) || isWindow(entity, BB_LIB[5])) {
			if (lang == 0) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
						bossName + " \u00A7r\u00A77has spawned in the \u00A7r\u00A7a\u00A7lLibrary!"));
			} else if (lang == 1) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(bossName
						+ "\u00A7r\u00A77\uC774(\uAC00) \u00A7r\u00A7a\u00A7l\uB3C4\uC11C\uAD00\u00A7r\u00A77\uC5D0\uC11C \uC18C\uD658\uB418\uC5C8\uC2B5\uB2C8\uB2E4!"));
			}
		}
		if (isWindow(entity, BB_DUN[0]) || isWindow(entity, BB_DUN[1]) || isWindow(entity, BB_DUN[2])
				|| isWindow(entity, BB_DUN[3]) || isWindow(entity, BB_DUN[4])) {
			if (lang == 0) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
						bossName + " \u00A7r\u00A77has spawned in the \u00A7r\u00A7a\u00A7lDungeon!"));
			} else if (lang == 1) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(bossName
						+ "\u00A7r\u00A77\uC774(\uAC00) \u00A7r\u00A7a\u00A7l\uB358\uC804\u00A7r\u00A77\uC5D0\uC11C \uC18C\uD658\uB418\uC5C8\uC2B5\uB2C8\uB2E4!"));
			}
		}
		if (isWindow(entity, BB_CRYPTS[0]) || isWindow(entity, BB_CRYPTS[1]) || isWindow(entity, BB_CRYPTS[2])) {
			if (lang == 0) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
						bossName + " \u00A7r\u00A77has spawned in the \u00A7r\u00A7a\u00A7lCrypt!"));
			} else if (lang == 1) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(bossName
						+ "\u00A7r\u00A77\uC774(\uAC00) \u00A7r\u00A7a\u00A7l\uC9C0\uD558\uC2E4\u00A7r\u00A77\uC5D0\uC11C \uC18C\uD658\uB418\uC5C8\uC2B5\uB2C8\uB2E4!"));
			}
		}
		if (isWindow(entity, BB_GY[0]) || isWindow(entity, BB_GY[1]) || isWindow(entity, BB_GY[2])) {
			if (lang == 0) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
						bossName + " \u00A7r\u00A77has spawned in the \u00A7r\u00A7a\u00A7lGraveyard!"));
			} else if (lang == 1) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(bossName
						+ "\u00A7r\u00A77\uC774(\uAC00) \u00A7r\u00A7a\u00A7l\uBB18\uC9C0\u00A7r\u00A77\uC5D0\uC11C \uC18C\uD658\uB418\uC5C8\uC2B5\uB2C8\uB2E4!"));
			}
		}
		if (isWindow(entity, BB_BAL[0]) || isWindow(entity, BB_BAL[1]) || isWindow(entity, BB_BAL[2])
				|| isWindow(entity, BB_BAL[3])) {
			if (lang == 0) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
						bossName + " \u00A7r\u00A77has spawned in the \u00A7r\u00A7a\u00A7lBalcony!"));
			} else if (lang == 1) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(bossName
						+ "\u00A7r\u00A77\uC774(\uAC00) \u00A7r\u00A7a\u00A7l\uBC1C\uCF54\uB2C8\u00A7r\u00A77\uC5D0\uC11C \uC18C\uD658\uB418\uC5C8\uC2B5\uB2C8\uB2E4!"));
			}
		}
		if (isWindow(entity, BB_HALL[0]) || isWindow(entity, BB_HALL[1]) || isWindow(entity, BB_HALL[2])) {
			if (lang == 0) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
						bossName + " \u00A7r\u00A77has spawned in the \u00A7r\u00A7a\u00A7lGreat Hall!"));
			} else if (lang == 1) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(bossName
						+ "\u00A7r\u00A77\uC774(\uAC00) \u00A7r\u00A7a\u00A7l\uADF8\uB808\uC774\uD2B8 \uD640\u00A7r\u00A77\uC5D0\uC11C \uC18C\uD658\uB418\uC5C8\uC2B5\uB2C8\uB2E4!"));
			}
		}
		resetLang = true;
	}

	@SubscribeEvent
	public void chatDetect(ClientChatReceivedEvent event) {
		if (!ZombiesAddonConfig.bossAlarm) {
			return;
		}
		String message = event.message.getUnformattedText();
		if (message.contains(">") || message.contains("in") || message.contains("\uC5D0\uC11C")) {
			return;
		}

		if (message.contains("spawned")) {
			lang = 0;

			if (message.split(" ")[0].equals("Bombie")) {
				bossName = "\u00A76\u00A7lBombie";
			} else if (message.split(" ")[0].equals("Inferno")) {
				bossName = "\u00A7c\u00A7lInferno";
			} else if (message.split(" ")[0].equals("The")) {
				bossName = "\u00A75\u00A7lThe Broodmother";
			} else if (message.split(" ")[0].equals("King")) {
				bossName = "\u00A7a\u00A7lKing Slime";
			} else if (message.split(" ")[0].equals("Mega")) {
				if (message.split(" ")[1].equals("Blob")) {
					Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
							"\u00A7a\u00A7lMega Blob \u00A7r\u00A77has spawned in the \u00A7r\u00A7a\u00A7lPark Entrance!"));
					event.setCanceled(true);
					return;
				} else {
					Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
							"\u00A4a\u00A7lMega Magma \u00A7r\u00A77has spawned in the \u00A7r\u00A7a\u00A7lPark Entrance!"));
					event.setCanceled(true);
					return;
				}
			} else if (message.split(" ")[0].equals("World")) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
						"\u00A70\u00A7lWorld Ender \u00A7r\u00A77has spawned in the \u00A7r\u00A7a\u00A7lPark Entrance!"));
				event.setCanceled(true);
				return;
			}
			event.setCanceled(true);
		} else if (message.contains("\uC18C\uD658")) {
			lang = 1;

			if (message.split(" ")[0].contains("\uBD04\uBE44")) {
				bossName = "\u00A76\u00A7l\uBD04\uBE44";
			} else if (message.split(" ")[0].contains("Inferno")) {
				bossName = "\u00A7c\u00A7l\uC778\uD398\uB974\uB178";
			} else if (message.split(" ")[0].contains("\uBE0C\uB8E8\uB4DC\uB9C8\uB354")) {
				bossName = "\u00A75\u00A7l\uBE0C\uB8E8\uB4DC\uB9C8\uB354";
			} else if (message.split(" ")[0].contains("\uD0B9")) {
				bossName = "\u00A7a\u00A7l\uD0B9 \uC2AC\uB77C\uC784";
			} else if (message.split(" ")[0].contains("\uBA54\uAC00")) {
				if (message.split(" ")[1].contains("\uBE14\uB86D")) {
					Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
							"\u00A7a\u00A7l\uBA54\uAC00 \uBE14\uB86D\u00A7r\u00A77\uC774(\uAC00) \u00A7r\u00A7a\u00A7l\uACF5\uC6D0 \uC785\uAD6C\u00A7r\u00A77\uC5D0\uC11C \uC18C\uD658\uB418\uC5C8\uC2B5\uB2C8\uB2E4!"));
					event.setCanceled(true);
					return;
				} else {
					Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
							"\u00A74\u00A7l\uBA54\uAC00 \uB9C8\uADF8\uB9C8\u00A7r\u00A77\uC774(\uAC00) \u00A7r\u00A7a\u00A7l\uACF5\uC6D0 \uC785\uAD6C\u00A7r\u00A77\uC5D0\uC11C \uC18C\uD658\uB418\uC5C8\uC2B5\uB2C8\uB2E4!"));
					event.setCanceled(true);
					return;
				}
			} else if (message.split(" ")[0].contains("\uC138\uACC4")) {
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(
						"\u00A70\u00A7l\uC138\uACC4 \uC5D4\uB354\u00A7r\u00A77\uC774(\uAC00) \u00A7r\u00A7a\u00A7l\uACF5\uC6D0 \uC785\uAD6C\u00A7r\u00A77\uC5D0\uC11C \uC18C\uD658\uB418\uC5C8\uC2B5\uB2C8\uB2E4!"));
				event.setCanceled(true);
				return;
			}
			event.setCanceled(true);
		} else {
			if (resetLang) {
				lang = -1;
				resetLang = false;
			}
		}
	}
}
