package kr.hahaha98757.zombiesaddon.data;

import kr.hahaha98757.zombiesaddon.utils.Point;

public class Window {
	private Point point;
	private boolean active;

	public Window(Point point) {
		this.point = point;
	}

	public Point getPoint() {
		return this.point;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return this.active;
	}
}
