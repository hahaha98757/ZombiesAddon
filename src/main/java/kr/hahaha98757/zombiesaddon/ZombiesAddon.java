package kr.hahaha98757.zombiesaddon;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.logging.log4j.Logger;

import kr.hahaha98757.zombiesaddon.commands.CommandCornering;
import kr.hahaha98757.zombiesaddon.commands.CommandInfo;
import kr.hahaha98757.zombiesaddon.commands.CommandLog;
import kr.hahaha98757.zombiesaddon.commands.CommandPowerupAlarm;
import kr.hahaha98757.zombiesaddon.commands.CommandSLA;
import kr.hahaha98757.zombiesaddon.commands.CommandZSV;
import kr.hahaha98757.zombiesaddon.commands.CommandZSVLines;
import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.handler.ConfigChangedHandler;
import kr.hahaha98757.zombiesaddon.handler.PacketClientConnectHandler;
import kr.hahaha98757.zombiesaddon.handler.RenderGameOverlayHandler;
import kr.hahaha98757.zombiesaddon.handler.RenderTimeHandler;
import kr.hahaha98757.zombiesaddon.listeners.BlockAlarmListener;
import kr.hahaha98757.zombiesaddon.listeners.BossAlarmListener;
import kr.hahaha98757.zombiesaddon.listeners.CorneringListener;
import kr.hahaha98757.zombiesaddon.listeners.EventListener;
import kr.hahaha98757.zombiesaddon.listeners.GrowESPListener;
import kr.hahaha98757.zombiesaddon.listeners.GrowGuideListener;
import kr.hahaha98757.zombiesaddon.listeners.GstepGuideListener;
import kr.hahaha98757.zombiesaddon.listeners.LrodOrderListener;
import kr.hahaha98757.zombiesaddon.listeners.RoundListener;
import kr.hahaha98757.zombiesaddon.listeners.TitleListener;
import kr.hahaha98757.zombiesaddon.listeners.WaveDelaysListener;
import kr.hahaha98757.zombiesaddon.listeners.ZSVListener;
import kr.hahaha98757.zombiesaddon.listeners.SLA.AutoSLAListener;
import kr.hahaha98757.zombiesaddon.listeners.SLA.DisplaySLAOverlayListener;
import kr.hahaha98757.zombiesaddon.listeners.dpscounter.DPSCounterListener;
import kr.hahaha98757.zombiesaddon.listeners.dpscounter.EntityListener;
import kr.hahaha98757.zombiesaddon.listeners.powupalarm.PowerupAlarmListener;
import kr.hahaha98757.zombiesaddon.listeners.powupalarm.PowerupTimer;
import kr.hahaha98757.zombiesaddon.packet.AutoSplitPacketInterceptor;
import kr.hahaha98757.zombiesaddon.packet.PacketInterceptor;
import kr.hahaha98757.zombiesaddon.splitter.LiveSplitSplitter;
import kr.hahaha98757.zombiesaddon.splitter.internal.InternalSplitter;
import kr.hahaha98757.zombiesaddon.splitter.socket.LiveSplitSocketSplitter;
import kr.hahaha98757.zombiesaddon.util.DPSCounter;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ZombiesAddon.MODID, name = ZombiesAddon.NAME, version = ZombiesAddon.VERSION, guiFactory = "kr.hahaha98757.zombiesaddon.config.ZombiesAddonGuiFactory")
public class ZombiesAddon {
	public static final String MODID = "ZombiesAddon";
	public static final String NAME = "Zombies Addon";
	public static final String VERSION = "1.15.0";
	public static final String CONFIG_VERSION = "6";

	private final DisplaySLAOverlayListener slaListener = new DisplaySLAOverlayListener();

	@Instance(MODID)
	public static ZombiesAddon instance;

	public static Logger logger;

	private AutoSplitPacketInterceptor packetInterceptor;
	private InternalSplitter internalSplitter;
	private RenderTimeHandler renderTimeHandler;
	private final static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

	public static boolean gameStart;
	public static boolean isConfigReset;
	public static boolean isSST;
	public static boolean isZombiesUtils;

	public static ZombiesAddon getInstance() {
		return instance;
	}

	public Logger getLogger() {
		return logger;
	}

	private void writeCfgVersion() {
		try {
			Path filePath = Paths.get("config/ZombiesAddonCfgVersion.txt");

			List<String> content = Arrays.asList("#DO NOT EDIT", CONFIG_VERSION);

			Files.write(filePath, content, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readCfgVersion() {
		try {
			Path filePath = Paths.get("config/ZombiesAddonCfgVersion.txt");

			List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);

			String str = lines.get(1);
			if (!str.equals(CONFIG_VERSION)) {
				resetConfig();
			}
			return;
		} catch (IOException e) {
			resetConfig();
		}
	}

	private void resetConfig() {
		String cfgFilePath = "config/ZombiesAddon.cfg";

		File cfgFile = new File(cfgFilePath);

		if (cfgFile.exists()) {
			if (cfgFile.delete()) {
				isConfigReset = true;
			}
		}
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		readCfgVersion();
		writeCfgVersion();
		gameStart = false;
		logger = event.getModLog();

		ZombiesAddonConfig.config = new Configuration(event.getSuggestedConfigurationFile());
		ZombiesAddonConfig.loadConfig();

		Keybinds.register();

		LiveSplitSplitter splitter = createSplitter();
		packetInterceptor = new AutoSplitPacketInterceptor(Minecraft.getMinecraft(), logger, splitter);
		if (splitter instanceof InternalSplitter) {
			internalSplitter = (InternalSplitter) splitter;
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		gameStart = true;
		ClientCommandHandler.instance.registerCommand(new CommandInfo());
		ClientCommandHandler.instance.registerCommand(new CommandLog());
		ClientCommandHandler.instance.registerCommand(new CommandCornering());
		ClientCommandHandler.instance.registerCommand(new CommandZSV());
		ClientCommandHandler.instance.registerCommand(new CommandZSVLines());
		ClientCommandHandler.instance.registerCommand(new CommandSLA(this.slaListener));
		ClientCommandHandler.instance.registerCommand(new CommandPowerupAlarm());

		MinecraftForge.EVENT_BUS.register(new UpdateChecker());
		MinecraftForge.EVENT_BUS.register(new ConfigChangedHandler(this));
		MinecraftForge.EVENT_BUS.register(new EventListener());
		MinecraftForge.EVENT_BUS.register(new BossAlarmListener());
		MinecraftForge.EVENT_BUS.register(new LrodOrderListener());
		MinecraftForge.EVENT_BUS.register(new CorneringListener());
		MinecraftForge.EVENT_BUS.register(new BlockAlarmListener());
		MinecraftForge.EVENT_BUS.register(new WaveDelaysListener());
		MinecraftForge.EVENT_BUS.register(new PowerupAlarmListener());
		MinecraftForge.EVENT_BUS.register(new PowerupTimer());
		MinecraftForge.EVENT_BUS.register(new ZSVListener());
		MinecraftForge.EVENT_BUS.register(new AutoSLAListener());
		MinecraftForge.EVENT_BUS.register(new LrodOrderListener());
		MinecraftForge.EVENT_BUS.register(new GrowGuideListener());
		MinecraftForge.EVENT_BUS.register(new GrowESPListener());
		MinecraftForge.EVENT_BUS.register(new GstepGuideListener());

		DPSCounter dpsCounter = new DPSCounter();
		DPSCounterListener dpsCounterListener = new DPSCounterListener(dpsCounter);
		MinecraftForge.EVENT_BUS.register(dpsCounterListener);
		MinecraftForge.EVENT_BUS.register(new EntityListener(dpsCounter));

		RoundListener roundListener = new RoundListener();
		MinecraftForge.EVENT_BUS.register(roundListener);
		MinecraftForge.EVENT_BUS.register(new TitleListener());

		MinecraftForge.EVENT_BUS.register(this.slaListener);
		MinecraftForge.EVENT_BUS.register(new RenderGameOverlayHandler());

		Iterable<PacketInterceptor> interceptors = Collections.singleton(packetInterceptor);
		MinecraftForge.EVENT_BUS.register(new PacketClientConnectHandler(interceptors));
		MinecraftForge.EVENT_BUS.register(new ConfigChangedHandler(this));

		Minecraft minecraft = Minecraft.getMinecraft();
		int color = 0xFFFFFF;
		renderTimeHandler = new RenderTimeHandler(minecraft, minecraft.fontRendererObj, color);
		if (internalSplitter != null) {
			renderTimeHandler.setSplitter(internalSplitter);
		}
		MinecraftForge.EVENT_BUS.register(renderTimeHandler);

		instance = this;
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if (Loader.isModLoaded("ShowSpawnTime")) {
			isSST = true;
		} else {
			isSST = false;
		}

		if (Loader.isModLoaded("zombiesutils")) {
			isZombiesUtils = true;
		} else {
			isZombiesUtils = false;
		}
	}

	public void reloadConfig2() {
		LiveSplitSplitter splitter = createSplitter();
		packetInterceptor.setSplitter(splitter);
		if (splitter instanceof InternalSplitter) {
			internalSplitter = (InternalSplitter) splitter;
			renderTimeHandler.setSplitter(internalSplitter);
		}
	}

	private LiveSplitSplitter createSplitter() {
		if (ZombiesAddonConfig.port == -1) {
			ZombiesAddonConfig.timer = true;
			return new InternalSplitter(executor);
		} else {
			ZombiesAddonConfig.timer = false;
		}
		return new LiveSplitSocketSplitter(executor, ZombiesAddonConfig.host, ZombiesAddonConfig.port);
	}
}