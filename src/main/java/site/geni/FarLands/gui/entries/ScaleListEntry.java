package site.geni.FarLands.gui.entries;

import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.gui.entries.DoubleListEntry;
import me.shedaniel.clothconfig2.gui.entries.TextFieldListEntry;
import net.minecraft.client.gui.widget.TextFieldWidget;
import site.geni.FarLands.FarLands;
import site.geni.FarLands.config.Config;
import site.geni.FarLands.util.Categories;
import site.geni.FarLands.util.Location;
import site.geni.FarLands.util.LocationHelper;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ScaleListEntry extends DoubleListEntry {
	public ScaleListEntry(String fieldName, Double value, String resetButtonKey, Supplier<Double> defaultValue, Consumer<Double> saveConsumer, ConfigCategory category) {
		super(fieldName, value, resetButtonKey, defaultValue, saveConsumer);
	}

	/**
	 * Updates the estimates in the "World" category
	 *
	 * @param config   {@link Config} to use
	 */
	private static void updateOptions(Config config) {
		LocationHelper.updateAll(config);

		if (Categories.World.FAR_LANDS_ESTIMATE != null) {
			final TextFieldWidget entryWidget = (TextFieldWidget) ((TextFieldListEntry) Categories.World.FAR_LANDS_ESTIMATE).children().get(0);

			entryWidget.setText(Location.FAR_LANDS.getText());
		}

		if (Categories.World.FARTHER_LANDS_ESTIMATE != null) {
			final TextFieldWidget entryWidget = (TextFieldWidget) ((TextFieldListEntry) Categories.World.FARTHER_LANDS_ESTIMATE).children().get(0);

			entryWidget.setText(Location.FARTHER_LANDS.getText());
		}

		if (Categories.World.FARTHERER_LANDS_ESTIMATE != null) {
			final TextFieldWidget entryWidget = (TextFieldWidget) ((TextFieldListEntry) Categories.World.FARTHERER_LANDS_ESTIMATE).children().get(0);

			entryWidget.setText(Location.FARTHERER_LANDS.getText());
		}

		if (Categories.World.FARTHEST_LANDS_ESTIMATE != null) {
			final TextFieldWidget entryWidget = (TextFieldWidget) ((TextFieldListEntry) Categories.World.FARTHEST_LANDS_ESTIMATE).children().get(0);

			entryWidget.setText(Location.FARTHEST_LANDS.getText());
		}

		/*for (Object object : category.getEntries()) {
			final AbstractConfigEntry entry = (AbstractConfigEntry) object;

			if (entry instanceof SubCategoryListEntry) {
				final List<? extends Element> elements = ((SubCategoryListEntry) entry).children();

				for (final Element element : elements) {
					if (!(element instanceof TextFieldListEntry)) {
						continue;
					}

					final String entryName = ((TextFieldListEntry) element).getFieldName();
					final TextFieldWidget entryWidget = (TextFieldWidget) ((TextFieldListEntry) element).children().get(0);

					switch (entryName) {
						case "config.farlands.estimatedPosition":
							entryWidget.setText(Location.FAR_LANDS.getText());

							break;
						case "config.farlands.estimatedFartherPosition":
							entryWidget.setText(Location.FARTHER_LANDS.getText());

							break;
						case "config.farlands.estimatedFarthererPosition":
							entryWidget.setText(Location.FARTHERER_LANDS.getText());

							break;
						case "config.farlands.estimatedFarthestPosition":
							entryWidget.setText(Location.FARTHEST_LANDS.getText());

							break;
					}

					// Moves cursor back to the start in order to avoid cutting off text
					entryWidget.method_1870();
				}
			}
		}*/
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
			final double scale = this.getValue();

			final Config config = FarLands.getConfig();
			switch (this.getFieldName()) {
				case "config.farlands.coordinateScale":
					config.coordinateScale = scale;
					break;
				case "config.farlands.coordinateScaleMultiplier":
					config.coordinateScaleMultiplier = scale;
					break;
			}

			ScaleListEntry.updateOptions(config);

			return true;
		} catch (ArithmeticException | NumberFormatException ignored) {

		}

		return false;
	}
}
