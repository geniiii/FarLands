package site.geni.FarLands.client.commands;

import io.github.cottonmc.clientcommands.ArgumentBuilders;
import io.github.cottonmc.clientcommands.ClientCommands;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.ingame.ChatScreen;
import site.geni.FarLands.gui.CustomizeFarLandsScreen;

public class CustomizeFarLandsCommand {
	public static void register() {
		ClientCommands.registerCommand(dispatcher -> dispatcher.register(ArgumentBuilders.literal("farlands").executes(
			source -> {
				CustomizeFarLandsScreen.openAndCreateConfigScreen(MinecraftClient.getInstance().currentScreen instanceof ChatScreen ? null : MinecraftClient.getInstance().currentScreen);

				return 1;
			}
		)));
	}
}
