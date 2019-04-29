package site.geni.FarLands.util.locations;

import site.geni.FarLands.config.Config;
import site.geni.FarLands.util.Locations;

public class FartherLandsLocation extends Location {
	@Override
	public boolean isValid() {
		return super.isValid() && Locations.FAR_LANDS.isValid();
	}

	@Override
	public void update(Config config) {
		this.value = Locations.FAR_LANDS.getValue() * 80;
	}
}
