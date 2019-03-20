package site.geni.FarLands.mixins.client.gui;

import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.menu.NewLevelScreen;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.world.level.LevelGeneratorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import site.geni.FarLands.gui.CustomizeFarLandsButton;

@SuppressWarnings("unused")
@Mixin(NewLevelScreen.class)
public abstract class NewLevelScreenMixin extends Screen {
	private final CustomizeFarLandsButton customizeFarLandsButton = new CustomizeFarLandsButton(this, 0, 120, 150, 20, I18n.translate("config.farlands.customize"));

	@Shadow
	private int generatorType;

	/**
	 * Adds the "Customize FarLands" button to the "Create New World" screen's "More World Options..."
	 *
	 * @param ci {@link CallbackInfo} required for {@link Inject}
	 * @author geni
	 */
	@Inject(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/gui/menu/NewLevelScreen;method_2710(Z)V",
			shift = At.Shift.BEFORE
		),
		method = "onInitialized"
	)
	private void addCustomizeFarLandsButton(CallbackInfo ci) {
		final LevelGeneratorType levelGenType = LevelGeneratorType.TYPES[this.generatorType];

		this.customizeFarLandsButton.field_2069 = levelGenType.hasInfo() || levelGenType.isCustomizable() ? this.screenWidth / 2 - 155 : this.screenWidth / 2 + 5;
		this.customizeFarLandsButton.visible = false;

		this.addButton(this.customizeFarLandsButton);
	}

	/**
	 * Shows or hides the "Customize FarLands" button depending on which part of "Create New World" is open
	 *
	 * @param ci     {@link CallbackInfo} required for {@link Inject}
	 * @param toggle If the "More World Options..." are open or not
	 * @author geni
	 */
	@Inject(
		at = @At(
			value = "HEAD"
		),
		method = "method_2710"
	)
	private void showOrHideCustomizeFarLandsButton(boolean toggle, CallbackInfo ci) {
		final LevelGeneratorType levelGenType = LevelGeneratorType.TYPES[this.generatorType];

		this.customizeFarLandsButton.field_2069 = levelGenType.hasInfo() || levelGenType.isCustomizable() ? this.screenWidth / 2 - 155 : this.screenWidth / 2 + 5;
		this.customizeFarLandsButton.visible = toggle;
	}
}

