package site.geni.FarLands.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;

@Environment(EnvType.CLIENT)
public class CustomizeFarLandsButton extends ButtonWidget {
	public CustomizeFarLandsButton(Screen parent, int x, int y, int width, int height, String text) {
		super(x, y, width, height, text, (buttonWidget) -> {
			MinecraftClient.getInstance().openScreen(new CustomizeFarLandsScreen(parent));
		});

	}
}
