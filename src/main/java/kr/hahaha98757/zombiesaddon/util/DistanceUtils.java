package kr.hahaha98757.zombiesaddon.util;

public class DistanceUtils {

	public static double getDistanceSquared(Point a, Point b) {
		double dx = a.getX() - b.getX();
		double dy = a.getY() - b.getY();
		double dz = a.getZ() - b.getZ();
		return dx * dx + dy * dy + dz * dz;
	}
}
