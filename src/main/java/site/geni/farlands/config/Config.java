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
}