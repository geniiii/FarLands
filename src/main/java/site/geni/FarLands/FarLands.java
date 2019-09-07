package site.geni.FarLands;

import io.github.cottonmc.cotton.logging.ModLogger;
import me.zeroeightsix.fiber.exception.FiberException;
import net.fabricmc.api.ModInitializer;
import site.geni.FarLands.config.Config;

@SuppressWarnings("unused")
public class FarLands implements ModInitializer {
	private static Config CONFIG;

	private static final ModLogger LOGGER = new ModLogger(FarLands.class);

	@Override
	public void onInitialize() {
		try {
			FarLands.CONFIG = new Config().load();
		} catch (FiberException e) {
			e.printStackTrace();
		}
		LOGGER.info("[FarLands] Initialized");
	}

	public static Config getConfig() {
		return CONFIG;
	}

	public static void saveConfig() {
		CONFIG.save();
	}

	public static ModLogger getLogger() {
		return LOGGER;
	}
}
