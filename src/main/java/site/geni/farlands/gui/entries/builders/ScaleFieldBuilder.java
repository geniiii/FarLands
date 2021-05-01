package site.geni.farlands.gui.entries.builders;

import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.impl.builders.FieldBuilder;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import site.geni.farlands.gui.entries.ScaleListEntry;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class ScaleFieldBuilder extends FieldBuilder<Double, ScaleListEntry> {
	private final Double value;
	private final ConfigCategory category;
	private Consumer<Double> saveConsumer = null;
	private Function<Double, Optional<Text[]>> tooltipSupplier = str -> Optional.empty();

	public ScaleFieldBuilder(String resetButtonKey, String fieldNameKey, Double value, ConfigCategory category) {
		super(Text.of(resetButtonKey), Text.of(fieldNameKey));
		this.value = value;
		this.category = category;
	}

	@SuppressWarnings("deprecation")
	@Override
	public @NotNull ScaleListEntry build() {
		ScaleListEntry entry = new ScaleListEntry(this.getFieldNameKey(), this.value, this.getResetButtonKey(), this.defaultValue, this.saveConsumer);
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

	public ScaleFieldBuilder setTooltip(Text... tooltip) {
		this.tooltipSupplier = str -> Optional.ofNullable(tooltip);
		return this;
	}
}
