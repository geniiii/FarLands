package site.geni.FarLands.util.locations;

import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.TextFormat;
import site.geni.FarLands.FarLands;
import site.geni.FarLands.config.Config;

public abstract class Location {
	private static final String INVALID = TextFormat.RED + I18n.translate("config.farlands.invalid");


	protected long value;

	public Location() {
		this.update(FarLands.getConfig());
	}

	public String getText() {
		return this.isValid() ? "±" + this.value : INVALID;
	}

	public boolean isValid() {
		return this.value >= 0 && this.value != Long.MAX_VALUE;
	}

	public long getValue() {
		return this.value;
	}

	public abstract void update(Config config);
}
