package site.geni.FarLands.gui.entries;

import me.shedaniel.cloth.gui.entries.BooleanListEntry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.AbstractButtonWidget;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class OutsideWorldListEntry extends BooleanListEntry {
	public OutsideWorldListEntry(String fieldName, boolean bool, String resetButtonKey, Supplier<Boolean> defaultValue, Consumer<Boolean> saveConsumer, Supplier<Optional<String[]>> tooltipSupplier) {
		super(fieldName, bool, resetButtonKey, defaultValue, saveConsumer, tooltipSupplier);

		// The button itself
		((AbstractButtonWidget) this.children().get(0)).active = MinecraftClient.getInstance().world == null;
	}
}
