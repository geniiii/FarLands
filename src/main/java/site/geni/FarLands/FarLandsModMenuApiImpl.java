package site.geni.FarLands;

import io.github.prospector.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.Screen;
import site.geni.FarLands.gui.CustomizeFarLandsScreen;

import java.util.Optional;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class FarLandsModMenuApiImpl implements ModMenuApi {
	@Override
	public String getModId() {
		return "farlands";
	}

	@Override
	public Optional<Supplier<Screen>> getConfigScreen(Screen parent) {
		return Optional.of(
			() -> CustomizeFarLandsScreen.createConfigScreen(parent)
		);
	}
}
