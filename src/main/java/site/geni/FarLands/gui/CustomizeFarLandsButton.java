package site.geni.FarLands.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;

@Environment(EnvType.CLIENT)
public class CustomizeFarLandsButton extends ButtonWidget {
	private Screen parent;

	public CustomizeFarLandsButton(Screen parent, int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);

		this.parent = parent;
	}

	@Override
	public void onPressed(double double_1, double double_2) {
		super.onPressed(double_1, double_2);

		MinecraftClient.getInstance().openScreen(new CustomizeFarLandsScreen(this.parent));
	}
}
