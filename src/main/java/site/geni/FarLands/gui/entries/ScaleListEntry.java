package site.geni.farlands.gui.entries;

import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.gui.entries.DoubleListEntry;
import me.shedaniel.clothconfig2.gui.entries.TextFieldListEntry;
import net.minecraft.client.gui.widget.TextFieldWidget;
import site.geni.farlands.FarLands;
import site.geni.farlands.config.Config;
import site.geni.farlands.gui.Categories;
import site.geni.farlands.util.Location;
import site.geni.farlands.util.LocationHelper;

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

		if (Categories.World.FAR_LANDS_ESTIMATE != null) {
			final TextFieldWidget entryWidget = (TextFieldWidget) ((TextFieldListEntry) Categories.World.FAR_LANDS_ESTIMATE).children().get(0);

			entryWidget.setText(Location.FAR_LANDS.getText());
			entryWidget.setCursorToStart();
		}

		if (Categories.World.FARTHER_LANDS_ESTIMATE != null) {
			final TextFieldWidget entryWidget = (TextFieldWidget) ((TextFieldListEntry) Categories.World.FARTHER_LANDS_ESTIMATE).children().get(0);

			entryWidget.setText(Location.FARTHER_LANDS.getText());
			entryWidget.setCursorToStart();
		}

		if (Categories.World.FARTHERER_LANDS_ESTIMATE != null) {
			final TextFieldWidget entryWidget = (TextFieldWidget) ((TextFieldListEntry) Categories.World.FARTHERER_LANDS_ESTIMATE).children().get(0);

			entryWidget.setText(Location.FARTHERER_LANDS.getText());
			entryWidget.setCursorToStart();
		}

		if (Categories.World.FARTHEST_LANDS_ESTIMATE != null) {
			final TextFieldWidget entryWidget = (TextFieldWidget) ((TextFieldListEntry) Categories.World.FARTHEST_LANDS_ESTIMATE).children().get(0);

			entryWidget.setText(Location.FARTHEST_LANDS.getText());
			entryWidget.setCursorToStart();
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
					config.coordinateScale.setValue(scale);
					break;
				case "config.farlands.coordinateScaleMultiplier":
					config.coordinateScaleMultiplier.setValue(scale);
					break;
			}

			ScaleListEntry.updateOptions(config);

			return true;
		} catch (ArithmeticException | NumberFormatException ignored) {

		}

		return false;
	}
}
