package site.geni.FarLands;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import site.geni.FarLands.utils.Config;

import java.nio.file.Paths;

@SuppressWarnings("unused")
public class FarLandsMod implements ModInitializer {
	@Override
	public void onInitialize() {
		Config.createConfig(Paths.get(FabricLoader.getInstance().getConfigDirectory().getPath(), "FarLands.json").toFile());
	}
}
