package site.geni.FarLands.util;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.gui.entries.*;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.ChatFormat;
import site.geni.FarLands.FarLands;
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
		 * @param general The "General" category's {@link ConfigCategory}
		 * @author geni
		 */
		public static void createCategory(ConfigCategory general) {
			// Adds the option for enabling the Far Lands
			general.addEntry(new BooleanListEntry(
				"config.farlands.farLandsEnabled",
				FarLands.getConfig().farLandsEnabled,
				"text.cloth.reset_value",
				() -> true,
				bool -> FarLands.getConfig().farLandsEnabled = bool
			));

			// Adds the option for killing entities in the Far Lands
			general.addEntry(new BooleanListEntry(
				"config.farlands.killEntities",
				FarLands.getConfig().killFallingBlockEntitiesInFarLands,
				"text.cloth.reset_value",
				() -> false,
				bool -> FarLands.getConfig().killFallingBlockEntitiesInFarLands = bool
			));
		}
	}


	public static class World {
		public static EstimateListEntry FAR_LANDS_ESTIMATE;
		public static EstimateListEntry FARTHER_LANDS_ESTIMATE;
		public static EstimateListEntry FARTHERER_LANDS_ESTIMATE;
		public static EstimateListEntry FARTHEST_LANDS_ESTIMATE;

		/**
		 * Creates all options of the "World" category
		 *
		 * @param world The "World" category's {@link ConfigCategory}
		 * @author geni
		 */
		public static void createCategory(ConfigCategory world) {
			// Adds the warning in the "World" category
			world.addEntry(new TextListEntry(
				"",
				ChatFormat.RED + I18n.translate("config.farlands.category.world.warning")
			));

			// Adds the option for setting the coordinate scale
			world.addEntry(new ScaleListEntry(
				"config.farlands.coordinateScale",
				FarLands.getConfig().coordinateScale,
				"text.cloth.reset_value",
				() -> 684.4119873046875,
				scale -> FarLands.getConfig().coordinateScale = scale,
				world
			));

			// Adds the option for setting the coordinate scale multiplier
			world.addEntry(new ScaleListEntry(
				"config.farlands.coordinateScaleMultiplier",
				FarLands.getConfig().coordinateScaleMultiplier,
				"text.cloth.reset_value",
				() -> 1.0,
				scale -> FarLands.getConfig().coordinateScaleMultiplier = scale,
				world
			));

			// Adds the option for setting the height scale
			world.addEntry(new DoubleListEntry(
				"config.farlands.heightScale",
				FarLands.getConfig().heightScale,
				"text.cloth.reset_value",
				() -> 684.4119873046875,
				scale -> FarLands.getConfig().heightScale = scale
			));

			// Adds the option for setting the height scale multiplier
			world.addEntry(new DoubleListEntry(
				"config.farlands.heightScaleMultiplier",
				FarLands.getConfig().heightScaleMultiplier,
				"text.cloth.reset_value",
				() -> 1.0,
				scale -> FarLands.getConfig().heightScaleMultiplier = scale
			));

			World.createEstimatesSubCategory(world);
		}

		/**
		 * @param world The "World" category's {@link ConfigCategory}
		 * @author geni
		 */
		private static void createEstimatesSubCategory(ConfigCategory world) {
			// This sub-category's entries
			List<AbstractConfigListEntry> entries = Arrays.asList(
				// Adds the estimate for the Far Lands' location
				FAR_LANDS_ESTIMATE = new EstimateListEntry(
					"config.farlands.estimatedPosition",
					Location.FAR_LANDS.getText()
				),

				// Adds the estimate for the Farther Lands' location
				FARTHER_LANDS_ESTIMATE = new EstimateListEntry(
					"config.farlands.estimatedFartherPosition",
					Location.FARTHER_LANDS.getText()
				),

				// Adds the estimate for the Fartherer Lands' location
				FARTHERER_LANDS_ESTIMATE = new EstimateListEntry(
					"config.farlands.estimatedFarthererPosition",
					Location.FARTHERER_LANDS.getText()
				),

				// Adds the estimate for the Farthest Lands' location
				FARTHEST_LANDS_ESTIMATE = new EstimateListEntry(
					"config.farlands.estimatedFarthestPosition",
					Location.FARTHEST_LANDS.getText()
				)
			);

			// Adds the "Estimates" sub-category to the "World" category
			world.addEntry(new SubCategoryListEntry(
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
		 * @param fixes The "Fixes" category's {@link ConfigCategory}
		 * @author geni
		 */
		public static void createCategory(ConfigCategory fixes) {
			// Adds the option for fixing ore generation
			fixes.addEntry(new BooleanListEntry(
				"config.farlands.fixOreGeneration",
				FarLands.getConfig().fixOreGeneration,
				"text.cloth.reset_value",
				() -> true,
				bool -> FarLands.getConfig().fixOreGeneration = bool,
				() -> Optional.of(new String[]{
					I18n.translate("config.farlands.fixOreGeneration.tooltip")
				})
			));

			// Adds the option for fixing particles/entities
			fixes.addEntry(new BooleanListEntry(
				"config.farlands.fixParticlesEntities",
				FarLands.getConfig().fixParticlesEntities,
				"text.cloth.reset_value",
				() -> true,
				bool -> FarLands.getConfig().fixParticlesEntities = bool,
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
			particleTooltip.add(I18n.translate("config.farlands.fixParticlesEntities.tooltip.description"));
			for (final String particle : PARTICLES) {
				particleTooltip.add(ChatFormat.GREEN + I18n.translate("config.farlands.fixParticlesEntities.tooltip.description." + particle));
			}

			// Add entities to tooltip
			particleTooltip.add(I18n.translate("config.farlands.fixParticlesEntities.tooltip.description.entities"));
			for (final String entity : ENTITIES) {
				particleTooltip.add(ChatFormat.GREEN + I18n.translate("config.farlands.fixParticlesEntities.tooltip.description.entities." + entity));
			}

			return particleTooltip.toArray(new String[0]);
		}

		/**
		 * Creates all options of the "Fixes" category's "Experimental" sub-category and adds it
		 *
		 * @param fixes The "Fixes" {@link ConfigCategory}
		 * @author geni
		 */
		private static void createExperimentalSubCategory(ConfigCategory fixes) {
			// "Fixes" category's "Experimental" sub-category's entries
			List<AbstractConfigListEntry> experimentalEntries = Arrays.asList(
				// Warning message (16733525 is ChatFormat.RED's color)
				new TextListEntry("",
					I18n.translate("config.farlands.category.fixes.subcategory.experimental.warning"),
					16733525
				),

				// Lighting
				new OutsideWorldListEntry(
					"config.farlands.fixLighting",
					FarLands.getConfig().fixLighting,
					"text.cloth.reset_value",
					() -> false,
					bool -> FarLands.getConfig().fixLighting = bool,
					() -> Optional.of(new String[]{
						I18n.translate("config.farlands.fixLighting.tooltip.description"),
						ChatFormat.RED + I18n.translate("config.farlands.fixLighting.tooltip.warning"),
						ChatFormat.RED + I18n.translate("config.farlands.fixLighting.tooltip.world")
					})
				),

				// Mob spawning
				new BooleanListEntry(
					"config.farlands.fixMobSpawning",
					FarLands.getConfig().fixMobSpawning,
					"text.cloth.reset_value",
					() -> false,
					bool -> FarLands.getConfig().fixMobSpawning = bool,
					() -> Optional.of(new String[]{
						I18n.translate("config.farlands.fixMobSpawning.tooltip")
					})
				)
			);

			// Adds the "Experimental" sub-category to the "Fixes" category
			fixes.addEntry(new SubCategoryListEntry(
				"config.farlands.category.fixes.subcategory.experimental",
				experimentalEntries,
				false
			));
		}
	}
}
