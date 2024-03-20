package kr.hahaha98757.zombiesaddon.listeners.powupalarm;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class PowerupTimer {

	private static int powerupTimer1 = 1200;
	private static int powerupTimer2 = 1200;
	private static int powerupTimer3 = 1200;
	private static int powerupTimer4 = 1200;
	private static int powerupTimer5 = 1200;
	private static int powerupTimer6 = 1200;

	@SubscribeEvent
	public void onClientTick1(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.START) {
			powerupTimer1--;
			if (powerupTimer1 <= 0) {
				powerupTimer1 = 1200;
			}
		}
	}

	@SubscribeEvent
	public void onClientTick2(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.START) {
			powerupTimer2--;
			if (powerupTimer2 <= 0) {
				powerupTimer2 = 1200;
			}
		}
	}

	@SubscribeEvent
	public void onClientTick3(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.START) {
			powerupTimer3--;
			if (powerupTimer3 <= 0) {
				powerupTimer3 = 1200;
			}
		}
	}

	@SubscribeEvent
	public void onClientTick4(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.START) {
			powerupTimer4--;
			if (powerupTimer4 <= 0) {
				powerupTimer4 = 1200;
			}
		}
	}

	@SubscribeEvent
	public void onClientTick5(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.START) {
			powerupTimer5--;
			if (powerupTimer5 <= 0) {
				powerupTimer5 = 1200;
			}
		}
	}

	@SubscribeEvent
	public void onClientTick6(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.START) {
			powerupTimer6--;
			if (powerupTimer6 <= 0) {
				powerupTimer6 = 1200;
			}
		}
	}

	public static int getTimer(int a) {
		if (a == 1) {
			return powerupTimer1;
		} else if (a == 2) {
			return powerupTimer2;
		} else if (a == 3) {
			return powerupTimer3;
		} else if (a == 4) {
			return powerupTimer4;
		} else if (a == 5) {
			return powerupTimer5;
		} else if (a == 6) {
			return powerupTimer6;
		} else {
			return 0;
		}

	}

	public static void resetTimer(int a) {
		if (a == 1) {
			powerupTimer1 = 1200;
		} else if (a == 2) {
			powerupTimer2 = 1200;
		} else if (a == 3) {
			powerupTimer3 = 1200;
		} else if (a == 4) {
			powerupTimer4 = 1200;
		} else if (a == 5) {
			powerupTimer5 = 1200;
		} else if (a == 6) {
			powerupTimer6 = 1200;
		} else {
			return;
		}
	}
}
