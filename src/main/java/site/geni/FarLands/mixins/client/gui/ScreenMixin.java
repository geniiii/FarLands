package site.geni.FarLands.mixins.client.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@SuppressWarnings("unused")
@Mixin(Screen.class)
public interface ScreenMixin {
	@Accessor
	MinecraftClient getMinecraft();
}
