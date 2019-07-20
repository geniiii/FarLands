package site.geni.FarLands.gui.entries.builders;

import me.shedaniel.clothconfig2.impl.builders.FieldBuilder;
import site.geni.FarLands.gui.entries.OutsideWorldListEntry;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class OutsideWorldFieldBuilder extends FieldBuilder<Boolean, OutsideWorldListEntry> {
	private boolean value;
	private Consumer<Boolean> saveConsumer = null;
	private Supplier<Optional<String[]>> tooltipSupplier = null;

	public OutsideWorldFieldBuilder(String resetButtonKey, String fieldNameKey, boolean value) {
		super(resetButtonKey, fieldNameKey);
		this.value = value;
	}

	@SuppressWarnings("deprecation")
	@Override
	public OutsideWorldListEntry build() {
		return new OutsideWorldListEntry(this.getFieldNameKey(), this.value, this.getResetButtonKey(), this.defaultValue, this.saveConsumer, this.tooltipSupplier);
	}

	public OutsideWorldFieldBuilder setDefaultValue(boolean defaultValue) {
		this.defaultValue = () -> defaultValue;
		return this;
	}

	public OutsideWorldFieldBuilder setSaveConsumer(Consumer<Boolean> saveConsumer) {
		this.saveConsumer = saveConsumer;
		return this;
	}

	public OutsideWorldFieldBuilder setTooltip(String... tooltip) {
		this.tooltipSupplier = () -> Optional.ofNullable(tooltip);
		return this;
	}
}
