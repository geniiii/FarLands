package site.geni.FarLands.gui.entries.builders;

import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.impl.builders.FieldBuilder;
import site.geni.FarLands.gui.entries.ScaleListEntry;

import java.util.Objects;
import java.util.function.Consumer;

public class ScaleFieldBuilder extends FieldBuilder<Double, ScaleListEntry> {
	private Double value;
	private Consumer<Double> saveConsumer = null;
	private ConfigCategory category;

	public ScaleFieldBuilder(String resetButtonKey, String fieldNameKey, Double value, ConfigCategory category) {
		super(resetButtonKey, fieldNameKey);
		this.value = value;
		this.category = category;
	}

	@SuppressWarnings("deprecation")
	@Override
	public ScaleListEntry build() {
		return new ScaleListEntry(this.getFieldNameKey(), this.value, this.getResetButtonKey(), this.defaultValue, this.saveConsumer, this.category);
	}


	public ScaleFieldBuilder setDefaultValue(Double defaultValue) {
		this.defaultValue = () -> Objects.requireNonNull(defaultValue);
		return this;
	}

	public ScaleFieldBuilder setSaveConsumer(Consumer<Double> saveConsumer) {
		this.saveConsumer = saveConsumer;
		return this;
	}
}
