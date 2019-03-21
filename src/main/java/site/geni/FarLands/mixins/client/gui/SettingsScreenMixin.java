package site.geni.FarLands.mixins.client.gui;

import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.menu.SettingsScreen;
import net.minecraft.client.resource.language.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import site.geni.FarLands.gui.CustomizeFarLandsButton;

@SuppressWarnings("unused")
@Mixin(SettingsScreen.class)
public abstract class SettingsScreenMixin extends ScreenMixin {
	/**
	 * Adds the "Customize FarLands" button to the "Settings" screen
	 *
	 * @param ci {@link CallbackInfo} required for {@link Inject}
	 * @author geni
	 */
	@Inject(
		at = @At(
			value = "HEAD"
		),
		method = "onInitialized"
	)
	private void addCustomizeFarLandsButton(CallbackInfo ci) {
		this.addButton(new CustomizeFarLandsButton((Screen)(Object)this, this.screenWidth / 2 - 75, this.screenHeight / 6 + 18, 150, 20, I18n.translate("config.farlands.customize")));
	}
}
