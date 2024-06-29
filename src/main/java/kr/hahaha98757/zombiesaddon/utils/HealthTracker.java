package kr.hahaha98757.zombiesaddon.utils;

import net.minecraft.entity.EntityLiving;

public class HealthTracker {
	private final EntityLiving entity;
	private float health;

	public HealthTracker(EntityLiving entity) {
		this.entity = entity;
		this.health = entity.getHealth();
	}

	public float check() {
		float damage = this.health - this.entity.getHealth();
		this.health = this.entity.getHealth();
		return damage > 0.0F ? damage : 0.0F;
	}

	public EntityLiving getEntity() {
		return this.entity;
	}
}
