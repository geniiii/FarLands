package site.geni.FarLands.mixins.client;

import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.menu.NewLevelScreen;
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
	@Shadow
	private int generatorType;

	private final CustomizeFarLandsButton customizeFarLandsButton = new CustomizeFarLandsButton(this, 0, 120, 150, 20, "Customize FarLands");


	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/menu/NewLevelScreen;method_2710(Z)V", shift = At.Shift.BEFORE), method = "onInitialized")
	private void addCustomizeFarLandsButton(CallbackInfo ci) {
		this.customizeFarLandsButton.x = this.width / 2 + 5;
		this.customizeFarLandsButton.visible = false;

		this.addButton(customizeFarLandsButton);
	}

	@Inject(at = @At("RETURN"), method = "method_2710")
	private void showOrHideCustomizeFarLandsButton(boolean boolean_1, CallbackInfo ci) {
		this.customizeFarLandsButton.visible = LevelGeneratorType.TYPES[this.generatorType] != LevelGeneratorType.FLAT && LevelGeneratorType.TYPES[this.generatorType] != LevelGeneratorType.BUFFET && boolean_1;
	}
}

