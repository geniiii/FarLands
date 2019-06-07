package site.geni.FarLands.util;

import net.minecraft.ChatFormat;
import net.minecraft.client.resource.language.I18n;
import site.geni.FarLands.FarLands;
import site.geni.FarLands.config.Config;

import java.util.function.Function;

public enum Location {
	FAR_LANDS(
		config -> (long) (Integer.MAX_VALUE / ((config.coordinateScale * config.coordinateScaleMultiplier) / 4))
	),
	FARTHER_LANDS(
		config -> FAR_LANDS.getValue() * 80,
		FAR_LANDS
	),
	FARTHERER_LANDS(
		config -> (long) (Long.MAX_VALUE / ((config.coordinateScale * config.coordinateScaleMultiplier) / 4))
	),
	FARTHEST_LANDS(
		config -> FARTHERER_LANDS.getValue() * 80,
		FARTHERER_LANDS
	);

	private static final String INVALID = ChatFormat.RED + I18n.translate("config.farlands.invalid");

	public static final Location[] LOCATIONS = new Location[]{FAR_LANDS, FARTHER_LANDS, FARTHERER_LANDS, FARTHEST_LANDS};

	protected long value;

	private Function<Config, Long> updateFunction;
	private Location dependsOn;


	/**
	 * Initializes a {@link Location}
	 *
	 * @param update {@link Function} used for updating the location's value
	 * @author geni
	 */
	Location(Function<Config, Long> update) {
		this.updateFunction = update;

		this.update(FarLands.getConfig());
	}

	/**
	 * Initializes a {@link Location} with a separate {@link Location} it depends on being valid
	 *
	 * @param update    The {@link Function} used for updating the location's value
	 * @param dependsOn The {@link Location} this depends on being valid
	 * @author geni
	 */
	Location(Function<Config, Long> update, Location dependsOn) {
		this(update);

		this.dependsOn = dependsOn;
	}


	/**
	 * Gets the adequate text for this {@link Location}
	 *
	 * @return A {@link String} which is either {@link #INVALID} or this {@link Location}'s value
	 * @author geni
	 */
	public String getText() {
		return this.isValid() ? "±" + this.value : INVALID;
	}

	/**
	 * @return Whether this location is valid or not
	 * @author geni
	 */
	public boolean isValid() {
		return this.value >= 0 && this.value != Long.MAX_VALUE && (this.dependsOn == null || this.dependsOn.isValid());
	}

	/**
	 * @return This {@link Location}'s {@link #value}
	 * @author geni
	 */
	public long getValue() {
		return this.value;
	}

	/**
	 * Updates the {@link Location}'s {@link #value}
	 *
	 * @param config The {@link Config} to take values from
	 * @author geni
	 */
	public void update(Config config) {
		this.value = this.updateFunction.apply(config);
	}
}
