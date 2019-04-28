package site.geni.FarLands.util;

import me.shedaniel.cloth.api.ConfigScreenBuilder;
import me.shedaniel.cloth.gui.ClothConfigScreen;
import me.shedaniel.cloth.gui.entries.BooleanListEntry;
import me.shedaniel.cloth.gui.entries.DoubleListEntry;
import me.shedaniel.cloth.gui.entries.SubCategoryListEntry;
import me.shedaniel.cloth.gui.entries.TextListEntry;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.TextFormat;
import site.geni.FarLands.gui.entries.EstimateListEntry;
import site.geni.FarLands.gui.entries.OutsideWorldListEntry;
import site.geni.FarLands.gui.entries.ScaleListEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Categories {
	public static class General {
		/**
		 * Creates all options of the "General" category
		 *
		 * @param general The "General" category's {@link me.shedaniel.cloth.api.ConfigScreenBuilder.CategoryBuilder}
		 * @author geni
		 */
		public static void createCategory(ConfigScreenBuilder.CategoryBuilder general) {
			// Adds the option for enabling the Far Lands
			general.addOption(new BooleanListEntry(
				"config.farlands.farLandsEnabled",
				Config.getConfig().farLandsEnabled,
				"text.cloth.reset_value",
				() -> true,
				bool -> Config.getConfig().farLandsEnabled = bool
			));

			// Adds the option for killing entities in the Far Lands
			general.addOption(new BooleanListEntry(
				"config.farlands.killEntities",
				Config.getConfig().killFallingBlockEntitiesInFarLands,
				"text.cloth.reset_value",
				() -> false,
				bool -> Config.getConfig().killFallingBlockEntitiesInFarLands = bool
			));
		}
	}


	public static class World {
		private static final String INVALID = TextFormat.RED + I18n.translate("config.farlands.invalid");

		/**
		 * Creates all options of the "World" category
		 *
		 * @param world The "World" category's {@link me.shedaniel.cloth.api.ConfigScreenBuilder.CategoryBuilder}
		 * @author geni
		 */
		public static void createCategory(ConfigScreenBuilder.CategoryBuilder world) {
			// Adds the warning in the "World" category
			world.addOption(new TextListEntry(
				"",
				TextFormat.RED + I18n.translate("config.farlands.category.world.warning")
			));

			// Adds the option for setting the coordinate scale
			world.addOption(new ScaleListEntry(
				"config.farlands.coordinateScale",
				Config.getConfig().coordinateScale,
				"text.cloth.reset_value",
				() -> 684.4119873046875,
				scale -> Config.getConfig().coordinateScale = scale,
				world
			));

			// Adds the option for setting the coordinate scale multiplier
			world.addOption(new ScaleListEntry(
				"config.farlands.coordinateScaleMultiplier",
				Config.getConfig().coordinateScaleMultiplier,
				"text.cloth.reset_value",
				() -> 1.0,
				scale -> Config.getConfig().coordinateScaleMultiplier = scale,
				world
			));

			// Adds the option for setting the height scale
			world.addOption(new DoubleListEntry(
				"config.farlands.heightScale",
				Config.getConfig().heightScale,
				"text.cloth.reset_value",
				() -> 684.4119873046875,
				scale -> Config.getConfig().heightScale = scale
			));

			// Adds the option for setting the height scale multiplier
			world.addOption(new DoubleListEntry(
				"config.farlands.heightScaleMultiplier",
				Config.getConfig().heightScaleMultiplier,
				"text.cloth.reset_value",
				() -> 1.0,
				scale -> Config.getConfig().heightScaleMultiplier = scale
			));

			World.createEstimatesSubCategory(world);
		}

		/**
		 * @param world The "World" category's {@link me.shedaniel.cloth.api.ConfigScreenBuilder.CategoryBuilder}
		 * @author geni
		 */
		private static void createEstimatesSubCategory(ConfigScreenBuilder.CategoryBuilder world) {
			// This sub-category's entries
			List<ClothConfigScreen.AbstractListEntry> entries = Arrays.asList(
				// Adds the estimate for the Far Lands' location
				new EstimateListEntry(
					"config.farlands.estimatedPosition",
					LocationUtil.getFarlandsLocationString()
				),

				// Adds the estimate for the Farther Lands' location
				new EstimateListEntry(
					"config.farlands.estimatedFartherPosition",
					LocationUtil.getFartherLandsLocationString()
				),

				// Adds the estimate for the Fartherer Lands' location
				new EstimateListEntry(
					"config.farlands.estimatedFarthererPosition",
					LocationUtil.getFarthererLandsLocationString()
				),

				// Adds the estimate for the Farthest Lands' location
				new EstimateListEntry(
					"config.farlands.estimatedFarthestPosition",
					LocationUtil.getFartherLandsLocationString()
				)
			);

			// Adds the "Estimates" sub-category to the "World" category
			world.addOption(new SubCategoryListEntry(
				"config.farlands.category.world.subcategory.estimates",
				entries,
				true
			));
		}
	}


	public static class Fixes {
		// All particles fixed by the "Fix Particles/Entities" option, ready for usage in its tooltip
		private static final String[] PARTICLES = new String[]{
			"water", "lava", "tnt", "end_portal", "falling_block", "mycelium", "leaves", "repeater", "nether_portal"
		};

		// All entities fixed by the "Fix Particles/Entities" option, ready for usage in its tooltip
		private static final String[] ENTITIES = new String[]{
			"tnt", "enchanting_table"
		};


		/**
		 * Creates all options and sub-categories of the "Fixes" category
		 *
		 * @param fixes The "Fixes" category's {@link me.shedaniel.cloth.api.ConfigScreenBuilder.CategoryBuilder}
		 * @author geni
		 */
		public static void createCategory(ConfigScreenBuilder.CategoryBuilder fixes) {
			// Adds the option for fixing ore generation
			fixes.addOption(new BooleanListEntry(
				"config.farlands.fixOreGeneration",
				Config.getConfig().fixOreGeneration,
				"text.cloth.reset_value",
				() -> true,
				bool -> Config.getConfig().fixOreGeneration = bool,
				() -> Optional.of(new String[]{
					I18n.translate("config.farlands.fixOreGeneration.tooltip")
				})
			));

			// Adds the option for fixing particles/entities
			fixes.addOption(new BooleanListEntry(
				"config.farlands.fixParticles",
				Config.getConfig().fixParticles,
				"text.cloth.reset_value",
				() -> true,
				bool -> Config.getConfig().fixParticles = bool,
				() -> Optional.of(
					Fixes.createParticlesTooltip()
				)
			));

			Fixes.createExperimentalSubCategory(fixes);
		}

		/**
		 * Creates the "Fixes" category's "Fix Particles/Entities" option's tooltip
		 *
		 * @return The tooltip to be used by "Fix Particles/Entities"
		 * @author geni
		 */
		private static String[] createParticlesTooltip() {
			// "Fix particles/entities" option's tooltip
			final List<String> particleTooltip = new ArrayList<>();

			// Add particles to tooltip
			particleTooltip.add(I18n.translate("config.farlands.fixParticles.tooltip.description"));
			for (final String particle : PARTICLES) {
				particleTooltip.add(TextFormat.GREEN + I18n.translate("config.farlands.fixParticles.tooltip.description." + particle));
			}

			// Add entities to tooltip
			particleTooltip.add(I18n.translate("config.farlands.fixParticles.tooltip.description.entities"));
			for (final String entity : ENTITIES) {
				particleTooltip.add(TextFormat.GREEN + I18n.translate("config.farlands.fixParticles.tooltip.description.entities.") + entity);
			}

			return particleTooltip.toArray(new String[0]);
		}

		/**
		 * Creates all options of the "Fixes" category's "Experimental" sub-category and adds it
		 *
		 * @param fixes The "Fixes" {@link me.shedaniel.cloth.api.ConfigScreenBuilder.CategoryBuilder}
		 * @author geni
		 */
		private static void createExperimentalSubCategory(ConfigScreenBuilder.CategoryBuilder fixes) {
			// "Fixes" category's "Experimental" sub-category's entries
			List<ClothConfigScreen.AbstractListEntry> experimentalEntries = Arrays.asList(
				// Warning message (16733525 is TextFormat.RED's color)
				new TextListEntry("",
					I18n.translate("config.farlands.category.fixes.subcategory.experimental.warning"),
					16733525
				),

				// Lighting
				new OutsideWorldListEntry(
					"config.farlands.fixLighting",
					Config.getConfig().fixLighting,
					"text.cloth.reset_value",
					() -> false,
					bool -> Config.getConfig().fixLighting = bool,
					() -> Optional.of(new String[]{
						I18n.translate("config.farlands.fixLighting.tooltip.description"),
						TextFormat.RED + I18n.translate("config.farlands.fixLighting.tooltip.warning"),
						TextFormat.RED + I18n.translate("config.farlands.fixLighting.tooltip.world")
					})
				),

				// Mob spawning
				new BooleanListEntry(
					"config.farlands.fixMobSpawning",
					Config.getConfig().fixMobSpawning,
					"text.cloth.reset_value",
					() -> false,
					bool -> Config.getConfig().fixMobSpawning = bool,
					() -> Optional.of(new String[]{
						I18n.translate("config.farlands.fixMobSpawning.tooltip")
					})
				)
			);

			// Adds the "Experimental" sub-category to the "Fixes" category
			fixes.addOption(new SubCategoryListEntry(
				"config.farlands.category.fixes.subcategory.experimental",
				experimentalEntries,
				false
			));
		}
	}
}
