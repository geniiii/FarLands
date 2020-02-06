package site.geni.farlands.config;

import me.zeroeightsix.fiber.JanksonSettings;
import me.zeroeightsix.fiber.exception.FiberException;
import me.zeroeightsix.fiber.tree.ConfigNode;
import me.zeroeightsix.fiber.tree.ConfigValue;
import me.zeroeightsix.fiber.tree.Node;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Config {
	private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDirectory(), "FarLands.json5");

	private ConfigNode root = new ConfigNode();

	private Node general;
	// General
	public ConfigValue<Boolean> farLandsEnabled = ConfigValue.builder(Boolean.class)
		.withDefaultValue(true)
		.withComment("Whether or not the Far Lands should generate.")
		.withName("farLandsEnabled")
		.withParent(general)
		.build();
	public ConfigValue<Boolean> killFallingBlockEntitiesInFarLands = ConfigValue.builder(Boolean.class)
		.withDefaultValue(false)
		.withComment("Whether or not falling block entities (such as sand, gravel, etc...) should be killed when spawned in the Far Lands.")
		.withName("killFallingBlockEntitiesInFarLands")
		.withParent(general)
		.build();
	private Node fixes;
	// Fixes
	public ConfigValue<Boolean> fixOreGeneration = ConfigValue.builder(Boolean.class)
		.withDefaultValue(true)
		.withComment("Fixes precision loss with ore generation, which was causing ores to be always 2^n blocks apart from each other at far X/Z positions.")
		.withName("fixOreGeneration")
		.withParent(general)
		.build();
	public ConfigValue<Boolean> fixParticlesEntities = ConfigValue.builder(Boolean.class)
		.withDefaultValue(true)
		.withComment("Fixes precision loss with various particles and entities.")
		.withName("fixParticlesEntities")
		.withParent(fixes)
		.build();
	public ConfigValue<Boolean> fixLighting = ConfigValue.builder(Boolean.class)
		.withDefaultValue(false)
		.withComment("Fixes lighting up to X/Z: ±2^26, causes issues with existing worlds and multiplayer servers.")
		.withName("fixLighting")
		.withParent(fixes)
		.build();
	public ConfigValue<Boolean> fixMobSpawning = ConfigValue.builder(Boolean.class)
		.withDefaultValue(false)
		.withComment("Fixes precision loss with mob spawning, however mobs still don't spawn past the default world border.")
		.withName("fixMobSpawning")
		.withParent(fixes)
		.build();
	private Node world;
	// World
	public ConfigValue<Double> coordinateScale = ConfigValue.builder(Double.class)
		.withDefaultValue(684.4119873046875)
		.withComment("The world's coordinate scale.")
		.withName("coordinateScale")
		.withParent(world)
		.build();
	public ConfigValue<Double> heightScale = ConfigValue.builder(Double.class)
		.withDefaultValue(684.4119873046875)
		.withComment("The world's height scale.")
		.withName("heightScale")
		.withParent(world)
		.build();
	public ConfigValue<Double> coordinateScaleMultiplier = ConfigValue.builder(Double.class)
		.withDefaultValue(1D)
		.withComment("The coordinate scale multiplier (coordinate scale * multiplier).")
		.withName("coordinateScaleMultiplier")
		.withParent(world)
		.build();
	public ConfigValue<Double> heightScaleMultiplier = ConfigValue.builder(Double.class)
		.withDefaultValue(1D)
		.withComment("The height scale multiplier (height scale * multiplier).")
		.withName("heightScaleMultiplier")
		.withParent(world)
		.build();

	{
		try {
			general = root.fork("general");
			fixes = root.fork("fixes");
			world = root.fork("world");
		} catch (FiberException e) {
			e.printStackTrace();
		}
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
}