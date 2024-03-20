package kr.hahaha98757.zombiesaddon.utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class DPSCounter {
	private final Queue<Long> timeQueue = new LinkedList();
	private final Queue<Float> damageQueue = new LinkedList();

	public void add(float enqueue) {
		this.timeQueue.add(System.currentTimeMillis() + 1000L);
		this.damageQueue.add(enqueue);
	}

	public float count() {
		long time = System.currentTimeMillis();

		while (!this.timeQueue.isEmpty() && (Long) this.timeQueue.peek() < time) {
			this.timeQueue.poll();
			this.damageQueue.poll();
		}

		float damage = 0.0F;

		Float element;
		for (Iterator var4 = this.damageQueue.iterator(); var4.hasNext(); damage += element) {
			element = (Float) var4.next();
		}

		return damage;
	}
}
