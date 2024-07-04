package kr.hahaha98757.zombiesaddon.listeners;

import org.lwjgl.opengl.GL11;

import kr.hahaha98757.zombiesaddon.commands.CommandCornering;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.utils.GameDetect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerTrackerListener {

	public static double range = 2.5;

	@SubscribeEvent
	public void onRenderWorldLast(RenderWorldLastEvent event) {
		if (!ZombiesAddonConfig.enableMod) {
			return;
		}
		if (!ZombiesAddonConfig.togglePlayerTracker) {
			return;
		}
		if (!GameDetect.isZombiesGame()) {
			return;
		}
		for (EntityPlayer player : Minecraft.getMinecraft().theWorld.playerEntities) {

			if (player == Minecraft.getMinecraft().thePlayer) {
				continue;
			}

			if (withinDistance(player)) {
				continue;
			}
			
			if (withoutDistance(player)) {
				continue;
			}
			
			if (player.isSneaking()) {
				continue;
			}
			
			if (player.getName().equals(GameDetect.getPlayerNames()[1]) && GameDetect.getPlayerState()[1] <= 1) {
				GL11.glColor4f(0.3F, 1.0F, 0.3F, 0.11F);
			} else if (player.getName().equals(GameDetect.getPlayerNames()[2]) && GameDetect.getPlayerState()[2] <= 1) {
				GL11.glColor4f(0.3F, 1.0F, 1.0F, 0.11F);
			} else if (player.getName().equals(GameDetect.getPlayerNames()[3]) && GameDetect.getPlayerState()[3] <= 1) {
				GL11.glColor4f(1.0F, 0.6F, 0.0F, 0.11F);
			} else {
				continue;
			}
			
			RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
			AxisAlignedBB boundingBox = player.getEntityBoundingBox();

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

	private static boolean withinDistance(EntityPlayer other) {
		return getDistanceSquared(other) < Math.pow(range, 2);
	}
	
	private static boolean withoutDistance(EntityPlayer other) {
		return getDistanceSquared(other) > Math.pow(64, 2);
	}

	private static double getDistanceSquared(EntityPlayer other) {
		EntityPlayerSP playerSP = Minecraft.getMinecraft().thePlayer;
		return Math.pow(playerSP.posX - other.posX, 2) + Math.pow(playerSP.posZ - other.posZ, 2);
	}
}
