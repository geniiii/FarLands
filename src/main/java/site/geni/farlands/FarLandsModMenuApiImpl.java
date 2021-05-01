package site.geni.farlands;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.screen.Screen;
import site.geni.farlands.gui.CustomizeFarLandsScreen;

public class FarLandsModMenuApiImpl implements ModMenuApi {

	@Override
	public ConfigScreenFactory<Screen> getModConfigScreenFactory() {
		return CustomizeFarLandsScreen::createConfigScreen;
	}


}
