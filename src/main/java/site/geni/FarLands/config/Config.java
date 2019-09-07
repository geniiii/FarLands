package site.geni.FarLands.config;

import me.zeroeightsix.fiber.JanksonSettings;
import me.zeroeightsix.fiber.annotation.Setting;
import me.zeroeightsix.fiber.exception.FiberException;
import me.zeroeightsix.fiber.tree.ConfigNode;
import me.zeroeightsix.fiber.tree.ConfigValue;
import me.zeroeightsix.fiber.tree.Node;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.resource.language.I18n;
import site.geni.FarLands.FarLands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Config {
	private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDirectory(), "FarLands.json5");

	public ConfigNode root = new ConfigNode();

	private Node general = root.fork("general");
	private Node fixes = root.fork("fixes");
	private Node world = root.fork("world");

	// General
	public ConfigValue<Boolean> farLandsEnabled = ConfigValue.builder(Boolean.class)
		.withParent(general)
		.withDefaultValue(true)
		.withComment(I18n.translate("config.farlands.farLandsEnabled.tooltip"))
		.build();
	public ConfigValue<Boolean> killFallingBlockEntitiesInFarLands = ConfigValue.builder(Boolean.class)
		.withParent(general)
		.withDefaultValue(false)
		.withComment(I18n.translate("config.farlands.killEntities.tooltip"))
		.build();

	// World
	public ConfigValue<Double> coordinateScale = ConfigValue.builder(Double.class)
		.withParent(world)
		.withDefaultValue(684.4119873046875)
		.withComment(I18n.translate("config.farlands.coordinateScale.comment"))
		.build();
	public ConfigValue<Double> heightScale = ConfigValue.builder(Double.class)
		.withParent(world)
		.withDefaultValue(684.4119873046875)
		.withComment(I18n.translate("config.farlands.heightScale.comment"))
		.build();
	public ConfigValue<Double> coordinateScaleMultiplier = ConfigValue.builder(Double.class)
		.withParent(world)
		.withDefaultValue(1D)
		.withComment(I18n.translate("config.farlands.coordinateScaleMultiplier.comment"))
		.build();
	public ConfigValue<Double> heightScaleMultiplier = ConfigValue.builder(Double.class)
		.withParent(world)
		.withDefaultValue(1D)
		.withComment(I18n.translate("config.farlands.heightScaleMultiplier.comment"))
		.build();

	// Fixes
	public ConfigValue<Boolean> fixOreGeneration = ConfigValue.builder(Boolean.class)
		.withParent(fixes)
		.withDefaultValue(true)
		.withComment(I18n.translate("config.farlands.fixOreGeneration.tooltip"))
		.build();
	public ConfigValue<Boolean> fixParticlesEntities = ConfigValue.builder(Boolean.class)
		.withParent(fixes)
		.withDefaultValue(true)
		.withComment(I18n.translate("config.farlands.fixParticlesEntities.comment"))
		.build();
	public ConfigValue<Boolean> fixLighting = ConfigValue.builder(Boolean.class)
		.withParent(fixes)
		.withDefaultValue(true)
		.withComment(I18n.translate("config.farlands.fixLighting.tooltip.description"))
		.build();
	public ConfigValue<Boolean> fixMobSpawning = ConfigValue.builder(Boolean.class)
		.withParent(fixes)
		.withDefaultValue(true)
		.withComment(I18n.translate("config.farlands.fixMobSpawning.tooltip"))
		.build();


	public Config() throws FiberException {

	}

	public void save() {
		FarLands.getLogger().info("[FarLands] Saving config.");
		try {
			new JanksonSettings().serialize(this.root, Files.newOutputStream(Config.CONFIG_FILE.toPath()), false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Config load() {
		FarLands.getLogger().info("[FarLands] Loading config.");
		if (!CONFIG_FILE.exists()) {
			FarLands.getLogger().info("[FarLands] Config not found. Generating config...");
			this.save();
		}
		try {
			new JanksonSettings().serialize(this.root, Files.newOutputStream(Config.CONFIG_FILE.toPath()), false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}
}