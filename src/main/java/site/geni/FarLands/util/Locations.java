package site.geni.FarLands.util;

import site.geni.FarLands.config.Config;
import site.geni.FarLands.util.locations.FarLandsLocation;
import site.geni.FarLands.util.locations.FartherLandsLocation;
import site.geni.FarLands.util.locations.FarthererLandsLocation;
import site.geni.FarLands.util.locations.FarthestLandsLocation;

public class Locations {
	private static final FarLandsLocation FAR_LANDS = new FarLandsLocation();
	private static final FartherLandsLocation FARTHER_LANDS = new FartherLandsLocation();
	private static final FarthererLandsLocation FARTHERER_LANDS = new FarthererLandsLocation();
	private static final FarthestLandsLocation FARTHEST_LANDS = new FarthestLandsLocation();

	/**
	 * Updates {@link #FAR_LANDS}, {@link #FARTHER_LANDS}, {@link #FARTHERER_LANDS} and {@link #FARTHEST_LANDS}
	 *
	 * @param config {@link Config} to take values from
	 * @author geni
	 */
	public static void update(Config config) {
		FAR_LANDS.update(config);
		FARTHER_LANDS.update(config);
		FARTHERER_LANDS.update(config);
		FARTHEST_LANDS.update(config);
	}


	public static FarLandsLocation getFarLands() {
		return FAR_LANDS;
	}

	public static FartherLandsLocation getFartherLands() {
		return FARTHER_LANDS;
	}

	public static FarthererLandsLocation getFarthererLands() {
		return FARTHERER_LANDS;
	}

	public static FarthestLandsLocation getFarthestLands() {
		return FARTHEST_LANDS;
	}
}
