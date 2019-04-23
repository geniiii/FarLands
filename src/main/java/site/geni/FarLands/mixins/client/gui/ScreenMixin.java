package site.geni.FarLands.mixins.client.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(Screen.class)
public abstract class ScreenMixin {
	@Shadow
	protected MinecraftClient minecraft;
}
