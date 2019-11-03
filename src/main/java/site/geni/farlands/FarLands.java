package site.geni.farlands;

import io.github.cottonmc.cotton.logging.ModLogger;
import net.fabricmc.api.ModInitializer;
import site.geni.farlands.config.Config;

@SuppressWarnings("unused")
public class FarLands implements ModInitializer {
	private static final Config CONFIG = new Config().load();
	private static final ModLogger LOGGER = new ModLogger(FarLands.class);

	public static Config getConfig() {
		return CONFIG;
	}

	public static void saveConfig() {
		CONFIG.save();
	}

	public static ModLogger getLogger() {
		return LOGGER;
	}

	@Override
	public void onInitialize() {
		LOGGER.info("[FarLands] Initialized");
	}
}
