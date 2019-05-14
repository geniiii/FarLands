package site.geni.FarLands.mixins.client.gui;

import me.shedaniel.cloth.gui.ClothConfigScreen;
import net.minecraft.client.gui.ingame.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("unused")
@Mixin(ChatScreen.class)
public abstract class ChatScreenMixin {
	/**
	 * Do not close the chat screen if the current screen is an instance of a Cloth config screen, as commands are executed before the chat processes pressed keys. <br>
	 * Without this, the config screen is closed before it can draw any frames.
	 *
	 * @param charcode Character code of the pressed key
	 * @param int_2    ?
	 * @param int_3    ?
	 * @param cir      {@link CallbackInfoReturnable} required by {@link Inject}
	 * @author geni
	 */
	@Inject(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/MinecraftClient;openScreen(Lnet/minecraft/client/gui/Screen;)V"
		),
		method = "keyPressed",
		cancellable = true
	)
	private void dontCloseIfGuiOpen(int charcode, int int_2, int int_3, CallbackInfoReturnable<Boolean> cir) {
		if (((ScreenMixin) this).getMinecraft().currentScreen instanceof ClothConfigScreen) {
			cir.setReturnValue(true);
		}
	}
}
