package kr.hahaha98757.zombiesaddon.listeners.dpscounter;

import java.util.ArrayList;
import java.util.List;

import kr.hahaha98757.zombiesaddon.utils.DPSCounter;
import kr.hahaha98757.zombiesaddon.utils.HealthTracker;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EntityListener {
	private final DPSCounter dpsCounter;
	private final List<HealthTracker> trackers = new ArrayList();
	int tick = 0;

	public EntityListener(DPSCounter dpsCounter) {
		this.dpsCounter = dpsCounter;
	}

	@SubscribeEvent
	public void onTick(TickEvent.PlayerTickEvent event) {
		if (this.tick == 10) {
			this.tick = 0;
			int i = 0;

			while (i < this.trackers.size()) {
				float damage = ((HealthTracker) this.trackers.get(i)).check();
				if (damage > 0.0F) {
					this.dpsCounter.add(damage);
				}

				if (((HealthTracker) this.trackers.get(i)).getEntity().getHealth() == 0.0F) {
					this.trackers.remove(i);
				} else {
					++i;
				}
			}
		} else {
			++this.tick;
		}

	}

	@SubscribeEvent
	public void onSpawn(EntityJoinWorldEvent event) {
		Entity entity = event.entity;
		if (entity.getEntityWorld().isRemote && entity instanceof EntityLiving) {
			boolean exists = false;

			for (int i = 0; i < this.trackers.size(); ++i) {
				if (((HealthTracker) this.trackers.get(i)).getEntity().isEntityEqual(entity)) {
					exists = true;
					break;
				}
			}

			if (!exists) {
				this.trackers.add(new HealthTracker((EntityLiving) entity));
			}
		}

	}
}
