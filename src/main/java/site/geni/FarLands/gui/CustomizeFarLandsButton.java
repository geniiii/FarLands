package site.geni.FarLands.gui;

import javafx.util.Pair;
import me.shedaniel.cloth.api.ConfigScreenBuilder;
import me.shedaniel.cloth.gui.ClothConfigScreen;
import me.shedaniel.cloth.gui.entries.BooleanListEntry;
import me.shedaniel.cloth.gui.entries.DoubleListEntry;
import me.shedaniel.cloth.gui.entries.StringListEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.resource.language.I18n;
import site.geni.FarLands.utils.Config;

@Environment(EnvType.CLIENT)
public class CustomizeFarLandsButton extends ButtonWidget {
	private static int farLandsLocation = (int) (Integer.MAX_VALUE / ((Config.getConfig().coordinateScale * Config.getConfig().coordinateScaleMultiplier) / 4));
	private static long fartherLandsLocation = farLandsLocation * 80;

	private static long farthererLandsLocation = (long) (Long.MAX_VALUE / ((Config.getConfig().coordinateScale * Config.getConfig().coordinateScaleMultiplier) / 4));
	private static long farthestLandsLocation = farthererLandsLocation * 80;

	public CustomizeFarLandsButton(Screen parent, int x, int y, int width, int height, String text) {
		super(x, y, width, height, text, (buttonWidget) -> {
			ClothConfigScreen.Builder builder = new ClothConfigScreen.Builder(parent, I18n.translate("config.farlands.title"), null);

			ConfigScreenBuilder.CategoryBuilder general = builder.addCategory("config.farlands.category.general");
			ConfigScreenBuilder.CategoryBuilder fixes = builder.addCategory("config.farlands.category.fixes");
			ConfigScreenBuilder.CategoryBuilder world = builder.addCategory("config.farlands.category.world");

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
			world.addOption(new DoubleListEntry(
				"config.farlands.coordinateScale",
				Config.getConfig().coordinateScale,
				"text.cloth.reset_value",
				() -> 684.4119873046875,
				scale -> Config.getConfig().coordinateScale = scale
			) {
				@Override
				public boolean charTyped(char character, int charCode) {
					if (super.charTyped(character, charCode)) {
						try {
							double coordinateScale = this.getObject();

							Config.ConfigSpec config = Config.getConfig();
							config.coordinateScale = coordinateScale;

							CustomizeFarLandsButton.updateOptions(world, config);
						} catch (ArithmeticException | NumberFormatException ignore) {

						}

						return true;
					}

					return false;
				}

				@Override
				public boolean keyPressed(int charCode, int int_1, int int_2) {
					if (super.keyPressed(charCode, int_1, int_2)) {
						try {
							double coordinateScale = this.getObject();

							Config.ConfigSpec config = Config.getConfig();
							config.coordinateScale = coordinateScale;

							CustomizeFarLandsButton.updateOptions(world, config);

							return true;
						} catch (ArithmeticException | NumberFormatException ignore) {

						}
					}

					return false;
				}
			});

			// Adds the option for setting the coordinate scale multiplier in the "General" category
			world.addOption(new DoubleListEntry(
				"config.farlands.coordinateScaleMultiplier",
				Config.getConfig().coordinateScaleMultiplier,
				"text.cloth.reset_value",
				() -> 1.0,
				scale -> Config.getConfig().coordinateScaleMultiplier = scale
			) {
				@Override
				public boolean charTyped(char character, int charCode) {
					if (super.charTyped(character, charCode)) {
						try {
							Double coordinateScaleMultiplier = Double.parseDouble(this.getObject() + String.valueOf(character));

							Config.ConfigSpec config = Config.getConfig();
							config.coordinateScaleMultiplier = coordinateScaleMultiplier;

							CustomizeFarLandsButton.updateOptions(world, config);
						} catch (ArithmeticException | NumberFormatException ignore) {

						}

						return true;
					}

					return false;
				}

				@Override
				public boolean keyPressed(int charCode, int int_1, int int_2) {
					if (super.keyPressed(charCode, int_1, int_2)) {
						try {
							double coordinateScaleMultiplier = this.getObject();

							Config.ConfigSpec config = Config.getConfig();
							config.coordinateScaleMultiplier = coordinateScaleMultiplier;

							CustomizeFarLandsButton.updateOptions(world, config);
						} catch (ArithmeticException | NumberFormatException ignore) {

						}

						return true;
					}

					return false;
				}
			});

			// Adds the option for setting the height scale in the "General" category
			world.addOption(new DoubleListEntry(
				"config.farlands.heightScale",
				Config.getConfig().heightScale,
				"text.cloth.reset_value",
				() -> 684.4119873046875,
				scale -> Config.getConfig().heightScale = scale
			));

			// Adds the option for setting the height scale multiplier in the "General" category
			world.addOption(new DoubleListEntry(
				"config.farlands.heightScaleMultiplier",
				Config.getConfig().heightScaleMultiplier,
				"text.cloth.reset_value",
				() -> 1.0,
				scale -> Config.getConfig().heightScaleMultiplier = scale
			));

			// Adds the estimate for the Far Lands' location
			world.addOption(new EstimateEntry(
				"config.farlands.estimatedPosition",
				farLandsLocation >= 0 && farLandsLocation != Integer.MAX_VALUE ? "±" + farLandsLocation : "Invalid!"
			));

			// Adds the estimate for the Farther Lands' location
			world.addOption(new EstimateEntry(
				"config.farlands.estimatedFartherPosition",
				fartherLandsLocation >= 0 ? "±" + fartherLandsLocation : "Invalid!"
			));

			// Adds the estimate for the Fartherer Lands' location
			world.addOption(new EstimateEntry(
				"config.farlands.estimatedFarthererPosition",
				farthererLandsLocation >= 0 && farthererLandsLocation != Long.MAX_VALUE ? "±" + farthererLandsLocation : "Invalid!"
			));

			// Adds the estimate for the Farthest Lands' location
			world.addOption(new EstimateEntry(
				"config.farlands.estimatedFarthestPosition",
				farthestLandsLocation >= 0 ? "±" + farthestLandsLocation : "Invalid!"
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

	/**
	 * Updates the estimates in the World category
	 *
	 * @param category {@link me.shedaniel.cloth.api.ConfigScreenBuilder.CategoryBuilder} to take the options from
	 * @param config {@link site.geni.FarLands.utils.Config.ConfigSpec} to use
	 */
	private static void updateOptions(ConfigScreenBuilder.CategoryBuilder category, Config.ConfigSpec config) {
		for (Pair<String, Object> option : category.getOptions()) {
			if (option.getValue() instanceof StringListEntry) {
				StringListEntry entry = (StringListEntry) (option.getValue());
				String name = option.getKey();

				farLandsLocation = (int) (Integer.MAX_VALUE / ((config.coordinateScale * config.coordinateScaleMultiplier) / 4));
				fartherLandsLocation = farLandsLocation * 80;

				farthererLandsLocation = (long) (Long.MAX_VALUE / ((config.coordinateScale * config.coordinateScaleMultiplier) / 4));
				farthestLandsLocation = farthererLandsLocation * 80;

				switch (name) {
					case "config.farlands.estimatedPosition":
						TextFieldWidget farLandsLocationWidget = (TextFieldWidget) entry.children().get(0);

						farLandsLocationWidget.setText(farLandsLocation >= 0 && farLandsLocation != Integer.MAX_VALUE ? "±" + farLandsLocation : "Invalid!");

						break;
					case "config.farlands.estimatedFartherPosition":
						TextFieldWidget fartherLandsLocationWidget = (TextFieldWidget) entry.children().get(0);

						fartherLandsLocationWidget.setText(fartherLandsLocation >= 0 ? "±" + fartherLandsLocation : "Invalid!");

						break;
					case "config.farlands.estimatedFarthererPosition":
						TextFieldWidget farthererLandsLocationWidget = (TextFieldWidget) entry.children().get(0);

						farthererLandsLocationWidget.setText(farthererLandsLocation >= 0 && farthererLandsLocation != Long.MAX_VALUE ? "±" + farthererLandsLocation : "Invalid!");

						break;
					case "config.farlands.estimatedFarthestPosition":
						TextFieldWidget farthestLandsLocationWidget = (TextFieldWidget) entry.children().get(0);

						farthestLandsLocationWidget.setText(farthestLandsLocation >= 0 ? "±" + farthestLandsLocation : "Invalid!");

						break;
				}
			}
		}
	}
}
