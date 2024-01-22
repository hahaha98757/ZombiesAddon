package kr.hahaha98757.zombiesaddon.util;

public class Point {
	private final double x;
	private final double y;
	private final double z;

	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y - 0.5;
		this.z = z;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public double getZ() {
		return this.z;
	}
}
