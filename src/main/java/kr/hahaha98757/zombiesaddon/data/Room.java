package kr.hahaha98757.zombiesaddon.data;

public class Room {
	private String name;
	private Window[] windows;
	private int count;

	public Room(String name, Window[] windows) {
		this.name = name;
		this.windows = windows;
	}

	public String getName() {
		return this.name;
	}

	public Window[] getWindows() {
		return this.windows;
	}

	public int getCount() {
		return this.count;
	}

	public void increment() {
		++this.count;
	}

	public void zero() {
		this.count = 0;
	}
}
