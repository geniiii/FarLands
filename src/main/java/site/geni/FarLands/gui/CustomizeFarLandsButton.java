package site.geni.FarLands.gui;

import me.shedaniel.cloth.api.ConfigScreenBuilder;
import me.shedaniel.cloth.gui.ClothConfigScreen;
import me.shedaniel.cloth.gui.entries.BooleanListEntry;
import me.shedaniel.cloth.gui.entries.DoubleListEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import site.geni.FarLands.utils.Config;

@Environment(EnvType.CLIENT)
public class CustomizeFarLandsButton extends ButtonWidget {
	public CustomizeFarLandsButton(Screen parent, int x, int y, int width, int height, String text) {
		super(x, y, width, height, text, (buttonWidget) -> {
			ClothConfigScreen.Builder builder = new ClothConfigScreen.Builder(parent, I18n.translate("config.farlands.title"), null);

			ConfigScreenBuilder.CategoryBuilder general = builder.addCategory("config.farlands.category.general");
			ConfigScreenBuilder.CategoryBuilder fixes = builder.addCategory("config.farlands.category.fixes");

			// Adds the option for enabling the Far Lands in the "General" category
			general.addOption(new BooleanListEntry(
				"config.farlands.farLandsEnabled",
				Config.getConfig().farLandsEnabled,
				"text.cloth.reset_value",
				() -> true,
				bool -> Config.getConfig().farLandsEnabled = bool
			));

			// Adds the option for killing entities in the Far Lands in the "General" category
			general.addOption(new BooleanListEntry(
				"config.farlands.killEntities",
				Config.getConfig().killFallingBlockEntitiesInFarLands,
				"text.cloth.reset_value",
				() -> false,
				bool -> Config.getConfig().killFallingBlockEntitiesInFarLands = bool
			));


			// Adds the option for setting the coordinate scale in the "General" category
			general.addOption(new DoubleListEntry(
				"config.farlands.coordinateScale",
				Config.getConfig().coordinateScale,
				"text.cloth.reset_value",
				() -> 684.4119873046875,
				scale -> Config.getConfig().coordinateScale = scale
			));

			// Adds the option for setting the coordinate scale multiplier in the "General" category
			general.addOption(new DoubleListEntry(
				"config.farlands.coordinateScaleMultiplier",
				Config.getConfig().coordinateScaleMultiplier,
				"text.cloth.reset_value",
				() -> 1.0,
				scale -> Config.getConfig().coordinateScaleMultiplier = scale
			));

			// Adds the option for setting the height scale in the "General" category
			general.addOption(new DoubleListEntry(
				"config.farlands.heightScale",
				Config.getConfig().heightScale,
				"text.cloth.reset_value",
				() -> 684.4119873046875,
				scale -> Config.getConfig().heightScale = scale
			));

			// Adds the option for setting the height scale multiplier in the "General" category
			general.addOption(new DoubleListEntry(
				"config.farlands.heightScaleMultiplier",
				Config.getConfig().heightScaleMultiplier,
				"text.cloth.reset_value",
				() -> 1.0,
				scale -> Config.getConfig().heightScaleMultiplier = scale
			));


			// Adds the option for fixing ore generation in the "Fixes" category
			fixes.addOption(new BooleanListEntry(
				"config.farlands.fixOreGeneration",
				Config.getConfig().fixOreGeneration,
				"text.cloth.reset_value",
				() -> false,
				bool -> Config.getConfig().fixOreGeneration = bool
			));

			// Adds the option for fixing particles/entities in the "Fixes" category
			fixes.addOption(new BooleanListEntry(
				"config.farlands.fixParticles",
				Config.getConfig().fixParticles,
				"text.cloth.reset_value",
				() -> false,
				bool -> Config.getConfig().fixParticles = bool
			));

			// Adds the option for fixing lighting in the "Fixes" category
			fixes.addOption(new BooleanListEntry(
				"config.farlands.fixLighting",
				Config.getConfig().fixLighting,
				"text.cloth.reset_value",
				() -> false,
				bool -> Config.getConfig().fixLighting = bool
			));

			builder.setOnSave(savedConfig -> Config.saveConfig());

			MinecraftClient.getInstance().openScreen(builder.build());
		});
	}
}
