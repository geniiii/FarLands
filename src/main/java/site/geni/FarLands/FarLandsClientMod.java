package site.geni.FarLands;

import net.fabricmc.api.ClientModInitializer;
import site.geni.FarLands.client.commands.CustomizeFarLandsCommand;

@SuppressWarnings("unused")
public class FarLandsClientMod implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		CustomizeFarLandsCommand.register();
	}
}
