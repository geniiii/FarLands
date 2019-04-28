package site.geni.FarLands.gui;

import me.shedaniel.cloth.api.ConfigScreenBuilder;
import me.shedaniel.cloth.gui.ClothConfigScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.resource.language.I18n;
import site.geni.FarLands.util.Categories;
import site.geni.FarLands.util.Config;

import javax.annotation.Nullable;

public class CustomizeFarLandsScreen {
	/**
	 * Creates the mod's {@link ClothConfigScreen}
	 *
	 * @param parent The parent {@link Screen}
	 * @return The mod's {@link ClothConfigScreen}
	 */
	public static ClothConfigScreen createConfigScreen(@Nullable Screen parent) {
		final ConfigScreenBuilder builder = ConfigScreenBuilder.create(parent, I18n.translate("config.farlands.title"), null);

		Categories.General.createCategory(builder.addCategory("config.farlands.category.general"));
		Categories.Fixes.createCategory(builder.addCategory("config.farlands.category.fixes"));
		Categories.World.createCategory(builder.addCategory("config.farlands.category.world"));

		builder.setOnSave(savedConfig -> Config.saveConfig());

		return builder.build();
	}

	/**
	 * Creates and opens the mod's {@link ClothConfigScreen}
	 *
	 * @param parent The parent {@link Screen}
	 */
	public static void createAndOpenConfigScreen(Screen parent) {
		MinecraftClient.getInstance().openScreen(createConfigScreen(parent));
	}
}
