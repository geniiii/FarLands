package site.geni.farlands.config;

import io.github.fablabsmc.fablabs.api.fiber.v1.annotation.AnnotatedSettings;
import io.github.fablabsmc.fablabs.api.fiber.v1.annotation.Setting;
import io.github.fablabsmc.fablabs.api.fiber.v1.annotation.Settings;
import io.github.fablabsmc.fablabs.api.fiber.v1.exception.FiberException;
import io.github.fablabsmc.fablabs.api.fiber.v1.serialization.FiberSerialization;
import io.github.fablabsmc.fablabs.api.fiber.v1.serialization.JanksonValueSerializer;
import io.github.fablabsmc.fablabs.api.fiber.v1.tree.ConfigTree;
import net.fabricmc.loader.api.FabricLoader;
import site.geni.farlands.FarLands;

import java.io.*;


public class Config {
	private static final String CONFIG_NAME = "FarLands.json5";

	private Pojo pojo = new Pojo();
	private AnnotatedSettings annotatedSettings = AnnotatedSettings.builder().build();
	private ConfigTree configTree = ConfigTree.builder().applyFromPojo(pojo, annotatedSettings).build();

	public Pojo getPojo() {
		return pojo;
	}

	public void load() {
		final JanksonValueSerializer serializer = new JanksonValueSerializer(false);

		File configFile = new File(FabricLoader.getInstance().getConfigDirectory(), CONFIG_NAME);
		if (!configFile.exists()) {
			save();
			return;
		}
		try {
			InputStream configIn = new FileInputStream(configFile);
			FiberSerialization.deserialize(configTree, configIn, serializer);
		} catch (IOException | FiberException e) {
			FarLands.getLogger().error(String.format("Failed to load configuration file \"%s\": %s; falling back to defaults...", CONFIG_NAME, e.getMessage()));
		}
	}

	public void save() {
		final JanksonValueSerializer serializer = new JanksonValueSerializer(false);

		try {
			annotatedSettings.applyToNode(configTree, pojo);
		} catch (FiberException e) {
			e.printStackTrace();
		}

		File configFile = new File(FabricLoader.getInstance().getConfigDirectory(), CONFIG_NAME);
		try {
			OutputStream configOut = new FileOutputStream(configFile);
			FiberSerialization.serialize(configTree, configOut, serializer);
		} catch (IOException e) {
			FarLands.getLogger().error(String.format("Failed to write configuration file \"%s\": %s", CONFIG_NAME, e.getMessage()));
		}
	}

	@Settings
	public static class Pojo {
		@Setting.Group(name = "general")
		public General general = new General();
		@Setting.Group(name = "fixes")
		public Fixes fixes = new Fixes();

		public class General {
			@Setting(ignore = true)
			public static final boolean FAR_LANDS_ENABLED_DEFAULT = true;
			@Setting(comment = "Whether or not the Far Lands should generate.")
			public Boolean farLandsEnabled = FAR_LANDS_ENABLED_DEFAULT;

			@Setting(ignore = true)
			public static final boolean KILL_FALLING_BLOCK_ENTITIES_IN_FARLANDS_DEFAULT = false;
			@Setting(comment = "Whether or not falling block entities (such as sand, gravel, etc...) should be killed when spawned in the Far Lands.")
			public Boolean killFallingBlockEntitiesInFarLands = KILL_FALLING_BLOCK_ENTITIES_IN_FARLANDS_DEFAULT;
		}

		public class Fixes {
			@Setting(ignore = true)
			public static final boolean FIX_ORE_GENERATION_DEFAULT = true;
			@Setting(comment = "Fixes precision loss in ore generation, which causes ores to be always 2^n blocks apart from each other at far X/Z positions.")
			public boolean fixOreGeneration = FIX_ORE_GENERATION_DEFAULT;

			@Setting(ignore = true)
			public static final boolean FIX_LIGHTING_DEFAULT = false;
			@Setting(comment = "Fixes lighting up to X/Z: ±2^26, causes issues with existing worlds and multiplayer servers.")
			public boolean fixLighting = FIX_LIGHTING_DEFAULT;
		}
	}
}
