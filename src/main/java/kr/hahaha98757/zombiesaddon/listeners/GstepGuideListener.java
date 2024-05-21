package kr.hahaha98757.zombiesaddon.listeners;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.lwjgl.opengl.GL11;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.utils.GameDetect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GstepGuideListener {

	public static List<Entity> spawnedEntities;

	@SubscribeEvent
	public void onRenderLiving(RenderLivingEvent.Post event) {
		if (!ZombiesAddonConfig.enableMod) {
			return;
		}

		if (!ZombiesAddonConfig.gstepGuide) {
			return;
		}

		if (GameDetect.getMap() != 1) {
			return;
		}

		if (event.entity instanceof EntityLivingBase) {
			EntityLivingBase livingEntity = (EntityLivingBase) event.entity;

			if (!(livingEntity instanceof EntityArmorStand)) {
				return;
			}

			if (!livingEntity.getName().replaceAll("\u00A7.", "").equals("1,000 Gold")
					&& !livingEntity.getName().replaceAll("\u00A7.", "").contains("\uACE8\uB4DC")) {
				return;
			}

			if (!isRender(livingEntity)) {
				return;
			}

			RenderManager renderManager = event.renderer.getRenderManager();
			AxisAlignedBB boundingBox = livingEntity.getEntityBoundingBox();

			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE,
					GL11.GL_ZERO);

			GlStateManager.disableTexture2D();
			GlStateManager.disableLighting();
			GlStateManager.disableCull();
			GlStateManager.disableDepth();

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.4F);

			GlStateManager.pushMatrix();
			GlStateManager.translate(-renderManager.viewerPosX, -renderManager.viewerPosY, -renderManager.viewerPosZ);

			double height = 2.325;
			double width = 0.6;
			AxisAlignedBB adjustedBoundingBox = new AxisAlignedBB(boundingBox.minX - 0.1, boundingBox.minY - 0.1,
					boundingBox.minZ - 0.1, boundingBox.minX + width, boundingBox.minY + height,
					boundingBox.minZ + width);

			RenderGlobal.drawSelectionBoundingBox(adjustedBoundingBox);

			GlStateManager.popMatrix();

			GlStateManager.enableDepth();
			GlStateManager.enableCull();
			GlStateManager.enableLighting();
			GlStateManager.enableTexture2D();
			GlStateManager.disableBlend();
		}
	}

	private boolean isRender(EntityLivingBase entity) {
		try {
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			if (getGalleryEntity() == null) {
				return false;
			}
			if (!isPos(entity.posX, getGalleryEntity().posX, 1.0) || !isPos(entity.posY, getGalleryEntity().posY, 1.0)
					|| !isPos(entity.posZ, getGalleryEntity().posZ, 1.0)) {
				return false;
			}

			if (!isPos(entity.posX, player.posX, 5.0) || !isPos(entity.posY, player.posY, 5.0)
					|| !isPos(entity.posZ, player.posZ, 5.0)) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean isPos(double a, double b, double distance) {
		return Math.abs(a - b) <= distance;
	}

	public static Entity getGalleryEntity() {
		spawnedEntities = Minecraft.getMinecraft().theWorld.loadedEntityList;

		for (Entity entity : spawnedEntities) {

			if (entity.getName().contains("Gallery") || entity.getName().contains("\uBBF8\uC220\uAD00")) {
				return entity;
			}

		}
		return null;
	}
}
