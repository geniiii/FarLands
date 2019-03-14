package site.geni.FarLands.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_4185;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Screen;

@Environment(EnvType.CLIENT)
public class CustomizeFarLandsButton extends class_4185 {
	private Screen parent;

	public CustomizeFarLandsButton(Screen parent, int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);

		this.parent = parent;
	}

	@Override
	public void method_1826() {
		MinecraftClient.getInstance().openScreen(new CustomizeFarLandsScreen(this.parent));
	}
}
