package kr.hahaha98757.zombiesaddon.listeners;

import org.lwjgl.opengl.GL11;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.utils.GameDetect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.particle.EntityBreakingFX.SlimeFactory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BossTrackerListener {

	public static double range = 5.0;

	@SubscribeEvent
	public void onRenderWorldLast(RenderWorldLastEvent event) {
		if (!ZombiesAddonConfig.enableMod) {
			return;
		}
		if (!ZombiesAddonConfig.toggleBossTracker) {
			return;
		}
		if (!GameDetect.isZombiesGame()) {
			return;
		}
		if (GameDetect.getMap() == 4) {
			return;
		}
		for (Entity entity1 : Minecraft.getMinecraft().theWorld.loadedEntityList) {
			if (!(entity1 instanceof EntityLivingBase)) {
				continue;
			}

			EntityLivingBase entity = (EntityLivingBase) entity1;

			if (!isBoss(entity)) {
				continue;
			}

			if (withinDistance(entity)) {
				continue;
			}

			if (entity.deathTime > 0) {
				continue;
			}

			GL11.glColor4f(1.0F, 0.0F, 0.0F, 1.0F);

			RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
			AxisAlignedBB boundingBox = entity.getEntityBoundingBox();

			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE,
					GL11.GL_ZERO);

			GlStateManager.disableTexture2D();
			GlStateManager.disableLighting();
			GlStateManager.disableCull();
			GlStateManager.disableDepth();

			GlStateManager.pushMatrix();
			GlStateManager.translate(-renderManager.viewerPosX, -renderManager.viewerPosY, -renderManager.viewerPosZ);

			AxisAlignedBB adjustedBoundingBox = new AxisAlignedBB(boundingBox.minX, boundingBox.minY, boundingBox.minZ,
					boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);

			RenderGlobal.drawSelectionBoundingBox(adjustedBoundingBox);

			GlStateManager.popMatrix();

			GlStateManager.enableDepth();
			GlStateManager.enableCull();
			GlStateManager.enableLighting();
			GlStateManager.enableTexture2D();
			GlStateManager.disableBlend();
		}
	}

	private static boolean withinDistance(Entity other) {
		return getDistanceSquared(other) < Math.pow(range, 2);
	}

	private static double getDistanceSquared(Entity other) {
		EntityPlayerSP playerSP = Minecraft.getMinecraft().thePlayer;
		return Math.pow(playerSP.posX - other.posX, 2) + Math.pow(playerSP.posY - other.posY, 2)
				+ Math.pow(playerSP.posZ - other.posZ, 2);
	}

	private boolean isBoss(EntityLivingBase entity) {
		if (isPowerup(entity)) {
			return false;
		}

		if (GameDetect.getMap() == 1) {
			if (entity.getMaxHealth() >= 240) {
				return true;
			}
		} else if (GameDetect.getMap() == 2) {
			if ((entity.getMaxHealth() >= 400 && entity.getMaxHealth() <= 410 && entity.getHeldItem() == null)
					|| (entity.getMaxHealth() >= 1900)) {
				return true;
			}
		} else if (GameDetect.getMap() == 3) {
			int r = GameDetect.getRound();
			if (r == 25 || r == 35 || r == 56 || r == 57) {
				if (entity instanceof EntitySlime) {
					return true;
				}
			}
			if (r == 101) {
				if (entity instanceof EntitySkeleton) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isPowerup(EntityLivingBase entity) {
		String name = entity.getCustomNameTag().replaceAll("[^A-Za-z\uAC00-\uD7A3]", "");
		if (name.contains("InstaKill")) {
			return true;
		}
		if (name.contains("DoubleGold")) {
			return true;
		}
		if (name.contains("ShoppingSprea")) {
			return true;
		}
		if (name.contains("\uC989\uC2DC\ucc98\uCE58")) {
			return true;
		}
		if (name.contains("\uB354\uBE14\uACE8\uB4DC")) {
			return true;
		}
		if (name.contains("\uC9C0\uB984\uC2E0\uAC15\uB9BC")) {
			return true;
		}

		return false;
	}

}