package site.geni.farlands.gui;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.impl.builders.BooleanToggleBuilder;
import me.shedaniel.clothconfig2.impl.builders.DoubleFieldBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import me.shedaniel.clothconfig2.impl.builders.TextDescriptionBuilder;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.util.Formatting;
import site.geni.farlands.FarLands;
import site.geni.farlands.gui.entries.EstimateListEntry;
import site.geni.farlands.gui.entries.builders.EstimateFieldBuilder;
import site.geni.farlands.gui.entries.builders.ScaleFieldBuilder;
import site.geni.farlands.util.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("ConstantConditions")
public class Categories {
	static class General {
		/**
		 * Creates all options of the "General" category
		 *
		 * @param general The "General" category's {@link ConfigCategory}
		 * @author geni
		 */
		static void createCategory(ConfigCategory general) {
			// Adds the option for enabling the Far Lands
			general.addEntry(
				new BooleanToggleBuilder(
					"text.cloth.reset_value",
					"config.farlands.farLandsEnabled",
					FarLands.getConfig().farLandsEnabled.getValue()
				).setDefaultValue(FarLands.getConfig().farLandsEnabled.getDefaultValue())
					.setSaveConsumer(bool -> FarLands.getConfig().farLandsEnabled.setValue(bool))
					.build()
			);

			// Adds the option for killing entities in the Far Lands
			general.addEntry(
				new BooleanToggleBuilder(
					"text.cloth.reset_value",
					"config.farlands.killEntities",
					FarLands.getConfig().killFallingBlockEntitiesInFarLands.getValue()
				).setDefaultValue(FarLands.getConfig().killFallingBlockEntitiesInFarLands.getDefaultValue())
					.setSaveConsumer(bool -> FarLands.getConfig().killFallingBlockEntitiesInFarLands.setValue(bool))
					.setTooltip(I18n.translate("config.farlands.killEntities.tooltip"))
					.build()
			);
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
		static void createCategory(ConfigCategory world) {
			// Adds the warning in the "World" category
			world.addEntry(
				new TextDescriptionBuilder(
					"",
					"",
					I18n.translate("config.farlands.category.world.warning")
				).setColor(0xff5555)
					.build()
			);

			// Adds the option for setting the coordinate scale
			world.addEntry(
				new ScaleFieldBuilder(
					"text.cloth.reset_value",
					"config.farlands.coordinateScale",
					FarLands.getConfig().coordinateScale.getValue(),
					world
				).setDefaultValue(FarLands.getConfig().coordinateScale.getDefaultValue())
					.setSaveConsumer(scale -> FarLands.getConfig().coordinateScale.setValue(scale))
					.setTooltip(I18n.translate("config.farlands.coordinateScale.tooltip"))
					.build()
			);

			// Adds the option for setting the coordinate scale multiplier
			world.addEntry(
				new ScaleFieldBuilder(
					"text.cloth.reset_value",
					"config.farlands.coordinateScaleMultiplier",
					FarLands.getConfig().coordinateScaleMultiplier.getValue(),
					world
				).setDefaultValue(FarLands.getConfig().coordinateScaleMultiplier.getDefaultValue())
					.setSaveConsumer(scale -> FarLands.getConfig().coordinateScaleMultiplier.setValue(scale))
					.setTooltip(I18n.translate("config.farlands.coordinateScaleMultiplier.tooltip"))
					.build()
			);

			// Adds the option for setting the height scale
			world.addEntry(
				new DoubleFieldBuilder(
					"text.cloth.reset_value",
					"config.farlands.heightScale",
					FarLands.getConfig().heightScale.getValue()
				).setDefaultValue(FarLands.getConfig().heightScale.getDefaultValue())
					.setSaveConsumer(scale -> FarLands.getConfig().heightScale.setValue(scale))
					.setTooltip(I18n.translate("config.farlands.heightScale.tooltip"))
					.build()
			);

			// Adds the option for setting the height scale multiplier
			world.addEntry(
				new DoubleFieldBuilder(
					"text.cloth.reset_value",
					"config.farlands.heightScaleMultiplier",
					FarLands.getConfig().heightScaleMultiplier.getValue()
				).setDefaultValue(FarLands.getConfig().heightScaleMultiplier.getDefaultValue())
					.setSaveConsumer(scale -> FarLands.getConfig().heightScaleMultiplier.setValue(scale))
					.setTooltip(I18n.translate("config.farlands.heightScaleMultiplier.tooltip"))
					.build()
			);

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
				FAR_LANDS_ESTIMATE = new EstimateFieldBuilder(
					"config.farlands.estimatedPosition",
					Location.FAR_LANDS.getText()
				).build(),

				// Adds the estimate for the Farther Lands' location
				FARTHER_LANDS_ESTIMATE = new EstimateFieldBuilder(
					"config.farlands.estimatedFartherPosition",
					Location.FARTHER_LANDS.getText()
				).build(),

				// Adds the estimate for the Fartherer Lands' location
				FARTHERER_LANDS_ESTIMATE = new EstimateFieldBuilder(
					"config.farlands.estimatedFarthererPosition",
					Location.FARTHERER_LANDS.getText()
				).build(),

				// Adds the estimate for the Farthest Lands' location
				FARTHEST_LANDS_ESTIMATE = new EstimateFieldBuilder(
					"config.farlands.estimatedFarthestPosition",
					Location.FARTHEST_LANDS.getText()
				).build()
			);

			// Adds the "Estimates" sub-category to the "World" category
			final SubCategoryBuilder subCategoryBuilder = new SubCategoryBuilder(
				"text.cloth-config.reset_value",
				"config.farlands.category.world.subcategory.estimates"
			);
			subCategoryBuilder.addAll(entries);
			subCategoryBuilder.setExpanded(true);
			world.addEntry(subCategoryBuilder.build());
		}
	}


	static class Fixes {
		// All particles fixed by the "Fix Particles/Entities" option, ready for usage in its tooltip
		private static final String[] PARTICLES = new String[]{
			"lava", "leaves", "repeater", "bone_meal"
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
		static void createCategory(ConfigCategory fixes) {
			// Adds the option for fixing ore generation
			fixes.addEntry(
				new BooleanToggleBuilder(
					"text.cloth.reset_value",
					"config.farlands.fixOreGeneration",
					FarLands.getConfig().fixOreGeneration.getValue()
				).setDefaultValue(FarLands.getConfig().fixOreGeneration.getDefaultValue())
					.setSaveConsumer(bool -> FarLands.getConfig().fixOreGeneration.setValue(bool))
					.setTooltip(I18n.translate("config.farlands.fixOreGeneration.tooltip"))
					.build()
			);

			// Adds the option for fixing particles/entities
			fixes.addEntry(
				new BooleanToggleBuilder(
					"text.cloth.reset_value",
					"config.farlands.fixParticlesEntities",
					FarLands.getConfig().fixParticlesEntities.getValue()
				).setDefaultValue(FarLands.getConfig().fixParticlesEntities.getDefaultValue())
					.setSaveConsumer(bool -> FarLands.getConfig().fixParticlesEntities.setValue(bool))
					.setTooltip(Fixes.createParticlesTooltip())
					.build()
			);

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
				particleTooltip.add(Formatting.GREEN + I18n.translate("config.farlands.fixParticlesEntities.tooltip.description." + particle));
			}

			// Add entities to tooltip
			particleTooltip.add(I18n.translate("config.farlands.fixParticlesEntities.tooltip.description.entities"));
			for (final String entity : ENTITIES) {
				particleTooltip.add(Formatting.GREEN + I18n.translate("config.farlands.fixParticlesEntities.tooltip.description.entities." + entity));
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
			final List<AbstractConfigListEntry> experimentalEntries = Arrays.asList(
				// Warning message (0xff5555 is Formatting.RED's color)
				new TextDescriptionBuilder("",
					"",
					I18n.translate("config.farlands.category.fixes.subcategory.experimental.warning")
				).setColor(0xff5555)
					.build(),

				// Lighting
				new BooleanToggleBuilder(
					"text.cloth.reset_value",
					"config.farlands.fixLighting",
					FarLands.getConfig().fixLighting.getValue()
				).setTooltip(
					I18n.translate("config.farlands.fixLighting.tooltip.description"),
					Formatting.RED + I18n.translate("config.farlands.fixLighting.tooltip.warning"),
					Formatting.RED + I18n.translate("config.farlands.fixLighting.tooltip.restart")
				).setDefaultValue(FarLands.getConfig().fixLighting.getDefaultValue())
					.setSaveConsumer(bool -> FarLands.getConfig().fixLighting.setValue(bool))
					.build(),

				// Mob spawning
				new BooleanToggleBuilder(
					"text.cloth.reset_value",
					"config.farlands.fixMobSpawning",
					FarLands.getConfig().fixMobSpawning.getValue()
				).setTooltip(I18n.translate("config.farlands.fixMobSpawning.tooltip"))
					.setSaveConsumer(bool -> FarLands.getConfig().fixMobSpawning.setValue(bool))
					.setDefaultValue(FarLands.getConfig().fixMobSpawning.getDefaultValue())
					.build()
			);

			// Adds the "Experimental" sub-category to the "Fixes" category
			final SubCategoryBuilder subCategoryBuilder = new SubCategoryBuilder(
				"text.cloth-config.reset_value",
				"config.farlands.category.fixes.subcategory.experimental"
			);
			subCategoryBuilder.addAll(experimentalEntries);
			fixes.addEntry(subCategoryBuilder.build());
		}
	}
}
