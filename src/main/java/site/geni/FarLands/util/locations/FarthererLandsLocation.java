package site.geni.FarLands.util.locations;

import site.geni.FarLands.config.Config;

public class FarthererLandsLocation extends Location {
	@Override
	public void update(Config config) {
		this.value = (long) (Long.MAX_VALUE / ((config.coordinateScale * config.coordinateScaleMultiplier) / 4));
	}
}
