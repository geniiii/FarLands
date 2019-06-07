package site.geni.FarLands;

import io.github.cottonmc.cotton.config.ConfigManager;
import io.github.cottonmc.cotton.logging.ModLogger;
import net.fabricmc.api.ModInitializer;
import site.geni.FarLands.config.Config;

@SuppressWarnings("unused")
public class FarLands implements ModInitializer {
	private static final Config CONFIG = ConfigManager.loadConfig(Config.class);
	private static final ModLogger LOGGER = new ModLogger(FarLands.class);

	@Override
	public void onInitialize() {
		LOGGER.info("Initialized");
	}

	public static Config getConfig() {
		return CONFIG;
	}

	public static void saveConfig() {
		ConfigManager.saveConfig(CONFIG);
	}

	public static ModLogger getLogger() {
		return LOGGER;
	}
}
