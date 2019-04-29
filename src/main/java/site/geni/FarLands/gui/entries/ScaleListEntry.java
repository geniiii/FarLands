package site.geni.FarLands.gui.entries;

import me.shedaniel.cloth.api.ConfigScreenBuilder;
import me.shedaniel.cloth.gui.entries.DoubleListEntry;
import me.shedaniel.cloth.gui.entries.SubCategoryListEntry;
import me.shedaniel.cloth.gui.entries.TextFieldListEntry;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.TextFormat;
import net.minecraft.util.Pair;
import site.geni.FarLands.FarLands;
import site.geni.FarLands.util.Config;
import site.geni.FarLands.util.LocationUtil;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ScaleListEntry extends DoubleListEntry {
	private final ConfigScreenBuilder.CategoryBuilder category;

	public ScaleListEntry(String fieldName, Double value, String resetButtonKey, Supplier<Double> defaultValue, Consumer<Double> saveConsumer, ConfigScreenBuilder.CategoryBuilder category) {
		super(fieldName, value, resetButtonKey, defaultValue, saveConsumer);

		this.category = category;
	}

	/**
	 * Updates the estimates in the "World" category
	 *
	 * @param category {@link me.shedaniel.cloth.api.ConfigScreenBuilder.CategoryBuilder} to take the options from
	 * @param config   {@link Config} to use
	 */
	@SuppressWarnings("deprecation")
	private static void updateOptions(ConfigScreenBuilder.CategoryBuilder category, Config config) {
		LocationUtil.updateLocations(config);

		for (Pair<String, Object> option : category.getOptions()) {
			if (option.getRight() instanceof SubCategoryListEntry) {
				final List<? extends Element> entries = ((SubCategoryListEntry) option.getRight()).children();

				for (final Element entry : entries) {
					if (!(entry instanceof TextFieldListEntry)) {
						continue;
					}

					final String entryName = ((TextFieldListEntry) entry).getFieldName();
					final TextFieldWidget entryWidget = (TextFieldWidget) ((TextFieldListEntry) entry).children().get(0);

					switch (entryName) {
						case "config.farlands.estimatedPosition":
							entryWidget.setText(LocationUtil.getFarlandsLocationString());

							break;
						case "config.farlands.estimatedFartherPosition":
							entryWidget.setText(LocationUtil.getFartherLandsLocationString());

							break;
						case "config.farlands.estimatedFarthererPosition":
							entryWidget.setText(LocationUtil.getFarthererLandsLocationString());

							break;
						case "config.farlands.estimatedFarthestPosition":
							entryWidget.setText(LocationUtil.getFarthestLandsLocationString());

							break;
					}

					// Moves cursor back to the start in order to avoid cutting off text
					entryWidget.method_1870();
				}
			}
		}
	}

	@Override
	public boolean keyPressed(int charCode, int int_1, int int_2) {
		return super.keyPressed(charCode, int_1, int_2) && updateScales();
	}

	@Override
	public boolean charTyped(char character, int charcode) {
		return super.charTyped(character, charcode) && updateScales();
	}

	private boolean updateScales() {
		try {
			final double scale = this.getObject();

			final Config config = FarLands.getConfig();
			switch (this.getFieldName()) {
				case "config.farlands.coordinateScale":
					config.coordinateScale = scale;
					break;
				case "config.farlands.coordinateScaleMultiplier":
					config.coordinateScaleMultiplier = scale;
					break;
			}

			ScaleListEntry.updateOptions(this.category, config);

			return true;
		} catch (ArithmeticException | NumberFormatException ignored) {

		}

		return false;
	}
}
