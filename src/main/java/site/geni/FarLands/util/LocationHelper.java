package site.geni.FarLands.util;

import site.geni.FarLands.config.Config;

public class LocationHelper {

	/**
	 * Updates all {@link Location}s defined in {@link Location#LOCATIONS}
	 *
	 * @param config The {@link Config} to take the values from
	 * @author geni
	 */
	public static void updateAll(Config config) {
		for (Location location : Location.LOCATIONS) {
			location.update(config);
		}
	}
}
