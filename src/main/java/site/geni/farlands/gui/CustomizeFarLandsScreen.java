package site.geni.farlands.gui;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.gui.ClothConfigScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resource.language.I18n;
import site.geni.farlands.FarLands;

public class CustomizeFarLandsScreen {
	/**
	 * Creates the mod's {@link ClothConfigScreen}
	 *
	 * @param parent The parent {@link Screen}
	 * @return The mod's {@link ClothConfigScreen}
	 * @author geni
	 */
	public static Screen createConfigScreen(Screen parent) {
		final ConfigBuilder builder = ConfigBuilder.create()
			.setParentScreen(parent)
			.setTitle(I18n.translate("config.farlands.title"))
			.setSavingRunnable(FarLands::saveConfig);

		Categories.General.createCategory(builder.getOrCreateCategory("config.farlands.category.general"));
		Categories.Fixes.createCategory(builder.getOrCreateCategory("config.farlands.category.fixes"));
		Categories.World.createCategory(builder.getOrCreateCategory("config.farlands.category.world"));

		return builder.build();
	}

	/**
	 * Creates and opens the mod's {@link ClothConfigScreen}
	 *
	 * @param parent The parent {@link Screen}
	 * @author geni
	 */
	public static void createAndOpenConfigScreen(Screen parent) {
		MinecraftClient.getInstance().openScreen(createConfigScreen(parent));
	}
}
