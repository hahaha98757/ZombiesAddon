package kr.hahaha98757.zombiesaddon;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class Keybinds {
	public static final String KEY_CATEGORY = "Zombies Addon Mods";
	public static KeyBinding toggleCornering;
	public static KeyBinding toggleBlockAlarm;
	public static KeyBinding toggleAutoRejoin;

	public static KeyBinding scrollUp;
	public static KeyBinding scrollDown;

	public static KeyBinding toggleRLmode;

	public static void register() {
		toggleCornering = new KeyBinding("Toggle Cornering", Keyboard.KEY_V, KEY_CATEGORY);
		toggleBlockAlarm = new KeyBinding("Toggle Block Alarm", Keyboard.KEY_B, KEY_CATEGORY);
		toggleAutoRejoin = new KeyBinding("Toggle Auto Rejoin", Keyboard.CHAR_NONE, KEY_CATEGORY);

		scrollUp = new KeyBinding("ZSV Scroll Up", Keyboard.KEY_LBRACKET, KEY_CATEGORY);
		scrollDown = new KeyBinding("ZSV Scroll Down", Keyboard.KEY_RBRACKET, KEY_CATEGORY);

		toggleRLmode = new KeyBinding("Toggle RL-mode", Keyboard.KEY_L, KEY_CATEGORY);

		ClientRegistry.registerKeyBinding(toggleCornering);
		ClientRegistry.registerKeyBinding(toggleBlockAlarm);
		ClientRegistry.registerKeyBinding(toggleAutoRejoin);

		ClientRegistry.registerKeyBinding(scrollUp);
		ClientRegistry.registerKeyBinding(scrollDown);

		ClientRegistry.registerKeyBinding(toggleRLmode);
	}
}