package site.geni.farlands.config;

import io.github.fablabsmc.fablabs.api.fiber.v1.annotation.AnnotatedSettings;
import io.github.fablabsmc.fablabs.api.fiber.v1.annotation.Setting;
import io.github.fablabsmc.fablabs.api.fiber.v1.annotation.Settings;
import io.github.fablabsmc.fablabs.api.fiber.v1.exception.FiberException;
import io.github.fablabsmc.fablabs.api.fiber.v1.serialization.JanksonValueSerializer;
import io.github.fablabsmc.fablabs.api.fiber.v1.tree.ConfigTree;
import io.github.fablabsmc.fablabs.impl.fiber.serialization.FiberSerialization;
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

	@Settings(onlyAnnotated = true)
	public static class Pojo {
		@Setting.Group
		public General general = new General();
		@Setting.Group
		public Fixes fixes = new Fixes();

		public static class General {
			@Setting(ignore = true)
			public static final boolean FAR_LANDS_ENABLED_DEFAULT = true;
			@Setting(comment = "Whether or not the Far Lands should generate.")
			public Boolean farLandsEnabled = FAR_LANDS_ENABLED_DEFAULT;

			@Setting(ignore = true)
			public static final boolean KILL_FALLING_BLOCK_ENTITIES_IN_FARLANDS_DEFAULT = false;
			@Setting(comment = "Whether or not falling block entities (such as sand, gravel, etc...) should be killed when spawned in the Far Lands.")
			public Boolean killFallingBlockEntitiesInFarLands = KILL_FALLING_BLOCK_ENTITIES_IN_FARLANDS_DEFAULT;
		}

		public static class Fixes {
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



/*
import io.github.fablabsmc.fablabs.api.fiber.v1.tree.ConfigNode;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;

public class Config {
	private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDirectory(), "FarLands.json5");

	final private ConfigNode root = new ConfigNode();

	private Node general = root.fork("general");
	// General
	public ConfigValue<Boolean> farLandsEnabled = ConfigValue.builder(Boolean.class)
		.withName("farLandsEnabled")
		.withComment("Whether or not the Far Lands should generate.")
		.withDefaultValue(true)
		.withParent(general)
		.build();
	public ConfigValue<Boolean> killFallingBlockEntitiesInFarLands = ConfigValue.builder(Boolean.class)
		.withName("killFallingBlockEntitiesInFarLands")
		.withComment("Whether or not falling block entities (such as sand, gravel, etc...) should be killed when spawned in the Far Lands.")
		.withDefaultValue(false)
		.withParent(general)
		.build();
	private Node fixes = root.fork("fixes");
	// Fixes
	public ConfigValue<Boolean> fixOreGeneration = ConfigValue.builder(Boolean.class)
		.withName("fixOreGeneration")
		.withComment("Fixes precision loss with ore generation, which was causing ores to be always 2^n blocks apart from each other at far X/Z positions.")
		.withDefaultValue(true)
		.withParent(fixes)
		.build();
	public ConfigValue<Boolean> fixParticlesEntities = ConfigValue.builder(Boolean.class)
		.withName("fixParticlesEntities")
		.withComment("Fixes precision loss with various particles and entities.")
		.withDefaultValue(true)
		.withParent(fixes)
		.build();
	public ConfigValue<Boolean> fixLighting = ConfigValue.builder(Boolean.class)
		.withName("fixLighting")
		.withComment("Fixes lighting up to X/Z: ±2^26, causes issues with existing worlds and multiplayer servers.")
		.withDefaultValue(false)
		.withParent(fixes)
		.build();
	public ConfigValue<Boolean> fixMobSpawning = ConfigValue.builder(Boolean.class)
		.withName("fixMobSpawning")
		.withComment("Fixes precision loss with mob spawning, however mobs still don't spawn past the default world border.")
		.withDefaultValue(false)
		.withParent(fixes)
		.build();
	private Node world = root.fork("world");
	// World
	public ConfigValue<Double> coordinateScale = ConfigValue.builder(Double.class)
		.withName("coordinateScale")
		.withComment("The world's coordinate scale.")
		.withDefaultValue(684.4119873046875)
		.withParent(world)
		.build();
	public ConfigValue<Double> heightScale = ConfigValue.builder(Double.class)
		.withName("heightScale")
		.withComment("The world's height scale.")
		.withDefaultValue(684.4119873046875)
		.withParent(world)
		.build();
	public ConfigValue<Double> coordinateScaleMultiplier = ConfigValue.builder(Double.class)
		.withName("coordinateScaleMultiplier")
		.withComment("The coordinate scale multiplier (coordinate scale * multiplier).")
		.withDefaultValue(1D)
		.withParent(world)
		.build();
	public ConfigValue<Double> heightScaleMultiplier = ConfigValue.builder(Double.class)
		.withName("heightScaleMultiplier")
		.withComment("The height scale multiplier (height scale * multiplier).")
		.withParent(world)
		.withDefaultValue(1D)
		.build();

	public Config() throws FiberException {
	}

	public void save() {
		try {
			new JanksonSettings().serialize(this.root, Files.newOutputStream(Config.CONFIG_FILE.toPath()), false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Config load() {
		if (!CONFIG_FILE.exists()) {
			this.save();
		}

		try {
			new JanksonSettings().deserialize(this.root, Files.newInputStream(Config.CONFIG_FILE.toPath()));
		} catch (IOException | FiberException e) {
			e.printStackTrace();
		}
		return this;
	}
}*/