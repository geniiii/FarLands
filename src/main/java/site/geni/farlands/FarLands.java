package site.geni.farlands;

import io.github.cottonmc.cotton.logging.ModLogger;
import net.fabricmc.api.ModInitializer;
import site.geni.farlands.config.Config;

@SuppressWarnings("unused")
public class FarLands implements ModInitializer {
	private static Config CONFIG;

	static {
		CONFIG = new Config();
		CONFIG.load();
		CONFIG.save();
	}

	private static final ModLogger LOGGER = new ModLogger(FarLands.class);

	public static Config.Pojo getConfig() {
		return CONFIG.getPojo();
	}

	public static void saveConfig() {
		CONFIG.save();
	}

	public static ModLogger getLogger() {
		return LOGGER;
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Initialized");
	}
}
