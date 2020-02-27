package site.geni.farlands;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.screen.Screen;
import site.geni.farlands.gui.CustomizeFarLandsScreen;

@SuppressWarnings("unused")
public class FarLandsModMenuApiImpl implements ModMenuApi {
	@Override
	public String getModId() {
		return "farlands";
	}

	@Override
	public ConfigScreenFactory<Screen> getModConfigScreenFactory() {
		return CustomizeFarLandsScreen::createConfigScreen;
	}
}
