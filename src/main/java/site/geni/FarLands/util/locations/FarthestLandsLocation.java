package site.geni.FarLands.util.locations;

import site.geni.FarLands.config.Config;
import site.geni.FarLands.util.Locations;

public class FarthestLandsLocation extends Location {
	@Override
	public void update(Config config) {
		this.value = Locations.FARTHERER_LANDS.getValue() * 80;
	}

	@Override
	public boolean isValid() {
		return super.isValid() && Locations.FARTHERER_LANDS.isValid();
	}
}
