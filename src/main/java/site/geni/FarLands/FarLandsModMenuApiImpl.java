package site.geni.farlands;

import io.github.prospector.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.screen.Screen;
import site.geni.farlands.gui.CustomizeFarLandsScreen;

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
