package site.geni.farlands.gui;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.impl.builders.BooleanToggleBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import me.shedaniel.clothconfig2.impl.builders.TextDescriptionBuilder;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import site.geni.farlands.FarLands;

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
					new TranslatableText("text.cloth.reset_value"),
					new TranslatableText("config.farlands.farLandsEnabled"),
					FarLands.getConfig().general.farLandsEnabled
				).setDefaultValue(FarLands.getConfig().general.FAR_LANDS_ENABLED_DEFAULT)
					.setSaveConsumer(value -> FarLands.getConfig().general.farLandsEnabled = value)
					.build()
			);

			// Adds the option for killing entities in the Far Lands
			general.addEntry(
				new BooleanToggleBuilder(
					new TranslatableText("text.cloth.reset_value"),
					new TranslatableText("config.farlands.killEntities"),
					FarLands.getConfig().general.killFallingBlockEntitiesInFarLands
				).setDefaultValue(FarLands.getConfig().general.KILL_FALLING_BLOCK_ENTITIES_IN_FARLANDS_DEFAULT)
					.setSaveConsumer(value -> FarLands.getConfig().general.killFallingBlockEntitiesInFarLands = value)
					.setTooltip(new TranslatableText("config.farlands.killEntities.tooltip"))
					.build()
			);
		}
	}

	static class Fixes {
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
					new TranslatableText("text.cloth.reset_value"),
					new TranslatableText("config.farlands.fixOreGeneration"),
					FarLands.getConfig().fixes.fixOreGeneration
				).setDefaultValue(FarLands.getConfig().fixes.FIX_ORE_GENERATION_DEFAULT)
					.setSaveConsumer(value -> FarLands.getConfig().fixes.fixOreGeneration = value)
					.setTooltip(new TranslatableText("config.farlands.fixOreGeneration.tooltip"))
					.build()
			);

			Fixes.createExperimentalSubCategory(fixes);
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
				new TextDescriptionBuilder(new LiteralText(""),
					new LiteralText(""),
					new TranslatableText("config.farlands.category.fixes.subcategory.experimental.warning")
				).setColor(0xff5555)
					.build(),

				// Lighting
				new BooleanToggleBuilder(
					new TranslatableText("text.cloth.reset_value"),
					new TranslatableText("config.farlands.fixLighting"),
					FarLands.getConfig().fixes.fixLighting
				).setTooltip(
					new TranslatableText("config.farlands.fixLighting.tooltip.description"),
					new TranslatableText("config.farlands.fixLighting.tooltip.warning").formatted(Formatting.RED),
					new TranslatableText("config.farlands.fixLighting.tooltip.restart").formatted(Formatting.RED))
					.setDefaultValue(FarLands.getConfig().fixes.FIX_LIGHTING_DEFAULT)
					.setSaveConsumer(value -> FarLands.getConfig().fixes.fixLighting = value)
					.build()
			);

			// Adds the "Experimental" sub-category to the "Fixes" category
			final SubCategoryBuilder subCategoryBuilder = new SubCategoryBuilder(
				new TranslatableText("text.cloth-config.reset_value"),
				new TranslatableText("config.farlands.category.fixes.subcategory.experimental")
			);
			subCategoryBuilder.addAll(experimentalEntries);
			fixes.addEntry(subCategoryBuilder.build());
		}
	}
}
