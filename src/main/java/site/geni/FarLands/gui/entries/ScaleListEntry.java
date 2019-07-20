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
	@Deprecated
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

		// method_1870 moves the cursor back to the start in order to avoid cutting off text
		if (Categories.World.FAR_LANDS_ESTIMATE != null) {
			final TextFieldWidget entryWidget = (TextFieldWidget) ((TextFieldListEntry) Categories.World.FAR_LANDS_ESTIMATE).children().get(0);

			entryWidget.setText(Location.FAR_LANDS.getText());
			entryWidget.method_1870();
		}

		if (Categories.World.FARTHER_LANDS_ESTIMATE != null) {
			final TextFieldWidget entryWidget = (TextFieldWidget) ((TextFieldListEntry) Categories.World.FARTHER_LANDS_ESTIMATE).children().get(0);

			entryWidget.setText(Location.FARTHER_LANDS.getText());
			entryWidget.method_1870();
		}

		if (Categories.World.FARTHERER_LANDS_ESTIMATE != null) {
			final TextFieldWidget entryWidget = (TextFieldWidget) ((TextFieldListEntry) Categories.World.FARTHERER_LANDS_ESTIMATE).children().get(0);

			entryWidget.setText(Location.FARTHERER_LANDS.getText());
			entryWidget.method_1870();
		}

		if (Categories.World.FARTHEST_LANDS_ESTIMATE != null) {
			final TextFieldWidget entryWidget = (TextFieldWidget) ((TextFieldListEntry) Categories.World.FARTHEST_LANDS_ESTIMATE).children().get(0);

			entryWidget.setText(Location.FARTHEST_LANDS.getText());
			entryWidget.method_1870();
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
