package kr.hahaha98757.zombiesaddon.data;

import java.util.Arrays;

public class Window {
	private final short[] xyz = new short[3];
	private final int id;
	private boolean isActive;
	private boolean isActiveByOthers;

	public Window(int id, int x, int y, int z) {
		this.id = id;
		xyz[0] = (short) x;
		xyz[1] = (short) y;
		xyz[2] = (short) z;
	}

	public int getID() {
		return id;
	}

	public short[] getXYZ() {
		return xyz;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public boolean isActive() {
		return isActive;
	}
	
	public void setActiveByOthers(boolean activeByOthers) {
		isActiveByOthers = activeByOthers;
	}

	public boolean isActiveByOthers() {
		return isActiveByOthers;
	}
}
