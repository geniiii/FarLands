package site.geni.FarLands;

import io.github.cottonmc.cotton.config.ConfigManager;
import net.fabricmc.api.ModInitializer;
import site.geni.FarLands.config.Config;

@SuppressWarnings("unused")
public class FarLands implements ModInitializer {
	private static Config config;

	public static Config getConfig() {
		return config;
	}

	@Override
	public void onInitialize() {
		ConfigManager.loadConfig(Config.class);
	}
}
