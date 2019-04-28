package site.geni.FarLands;

import io.github.prospector.modmenu.api.ModMenuApi;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.Screen;
import site.geni.FarLands.gui.CustomizeFarLandsScreen;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

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
