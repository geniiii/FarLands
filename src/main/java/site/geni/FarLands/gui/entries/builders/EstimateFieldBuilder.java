package site.geni.farlands.gui.entries.builders;

import me.shedaniel.clothconfig2.impl.builders.FieldBuilder;
import site.geni.farlands.gui.entries.EstimateListEntry;

public class EstimateFieldBuilder extends FieldBuilder<String, EstimateListEntry> {
	private String value;

	public EstimateFieldBuilder(String fieldNameKey, String value) {
		super("", fieldNameKey);
		this.value = value;
	}

	@SuppressWarnings("deprecation")
	@Override
	public EstimateListEntry build() {
		return new EstimateListEntry(this.getFieldNameKey(), this.value);
	}
}
