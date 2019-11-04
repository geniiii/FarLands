package site.geni.farlands.gui.entries.builders;

import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.impl.builders.FieldBuilder;
import site.geni.farlands.gui.entries.ScaleListEntry;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class ScaleFieldBuilder extends FieldBuilder<Double, ScaleListEntry> {
	private Double value;
	private ConfigCategory category;
	private Consumer<Double> saveConsumer = null;
	private Function<Double, Optional<String[]>> tooltipSupplier = str -> Optional.empty();

	public ScaleFieldBuilder(String resetButtonKey, String fieldNameKey, Double value, ConfigCategory category) {
		super(resetButtonKey, fieldNameKey);
		this.value = value;
		this.category = category;
	}

	@SuppressWarnings("deprecation")
	@Override
	public ScaleListEntry build() {
		ScaleListEntry entry = new ScaleListEntry(this.getFieldNameKey(), this.value, this.getResetButtonKey(), this.defaultValue, this.saveConsumer, this.category);
		entry.setTooltipSupplier(() -> this.tooltipSupplier.apply(entry.getValue()));

		return entry;
	}

	public ScaleFieldBuilder setDefaultValue(Double defaultValue) {
		this.defaultValue = () -> Objects.requireNonNull(defaultValue);
		return this;
	}

	public ScaleFieldBuilder setSaveConsumer(Consumer<Double> saveConsumer) {
		this.saveConsumer = saveConsumer;
		return this;
	}

	public ScaleFieldBuilder setTooltip(String... tooltip) {
		this.tooltipSupplier = str -> Optional.ofNullable(tooltip);
		return this;
	}
}
