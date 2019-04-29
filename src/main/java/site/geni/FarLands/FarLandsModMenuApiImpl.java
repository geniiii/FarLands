package site.geni.FarLands;

import io.github.prospector.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.Screen;
import site.geni.FarLands.gui.CustomizeFarLandsScreen;

import java.util.function.Function;

@SuppressWarnings("unused")
public class FarLandsModMenuApiImpl implements ModMenuApi {
	@Override
	public String getModId() {
		return "farlands";
	}

	@Override
	public Function<Screen, ? extends Screen> getConfigScreenFactory() {
		return CustomizeFarLandsScreen::createConfigScreen;
	}
}
