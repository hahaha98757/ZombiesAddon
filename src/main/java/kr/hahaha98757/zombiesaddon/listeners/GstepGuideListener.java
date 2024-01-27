package kr.hahaha98757.zombiesaddon.listeners;

import org.lwjgl.opengl.GL11;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.util.GameDetect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GstepGuideListener {

	private static EntityLivingBase entity;

	@SubscribeEvent
	public void onRenderLiving(RenderLivingEvent.Post event) {
		if (!ZombiesAddonConfig.gstepGuide) {
			return;
		}

		if (GameDetect.mapDetect() != 1) {
			return;
		}

		if (event.entity instanceof EntityLivingBase) {
			EntityLivingBase livingEntity = (EntityLivingBase) event.entity;

			if (!(livingEntity instanceof EntityArmorStand)) {
				return;
			}

			if (!livingEntity.getName().replaceAll("\u00A7.", "").equals("1,000 Gold")) {
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
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;

		if (!isPos(entity.posX, this.entity.posX, 0.3) || !isPos(entity.posY, this.entity.posY, 0.3)
				|| !isPos(entity.posZ, this.entity.posZ, 0.3)) {
			return false;
		}

		if (!isPos(entity.posX, player.posX, 5.0) || !isPos(entity.posY, player.posY, 5.0)
				|| !isPos(entity.posZ, player.posZ, 5.0)) {
			return false;
		}
		return true;
	}

	private boolean isPos(double a, double b, double distance) {
		return Math.abs(a - b) <= distance;
	}

	@SubscribeEvent
	public void livingEntity(LivingEvent.LivingUpdateEvent event) {
		if (event.entityLiving.getName().contains("Gallery")) {
			entity = event.entityLiving;
		}
	}
}
