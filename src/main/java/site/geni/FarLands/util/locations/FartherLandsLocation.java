package site.geni.FarLands.util.locations;

import site.geni.FarLands.config.Config;
import site.geni.FarLands.util.Locations;

public class FartherLandsLocation extends Location {
	@Override
	public boolean isValid() {
		return super.isValid() && Locations.getFarLands().isValid();
	}

	@Override
	public void update(Config config) {
		this.value = Locations.getFarLands().getValue() * 80;
	}
}
