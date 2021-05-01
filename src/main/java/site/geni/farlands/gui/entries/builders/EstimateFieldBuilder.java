package site.geni.farlands.gui.entries.builders;

import me.shedaniel.clothconfig2.impl.builders.FieldBuilder;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import site.geni.farlands.gui.entries.EstimateListEntry;

public class EstimateFieldBuilder extends FieldBuilder<String, EstimateListEntry> {
    private final String value;

    public EstimateFieldBuilder(String fieldNameKey, String value) {
        super(Text.of(""), Text.of(fieldNameKey));
        this.value = value;
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull EstimateListEntry build() {
        return new EstimateListEntry(this.getFieldNameKey(), this.value);
    }
}
