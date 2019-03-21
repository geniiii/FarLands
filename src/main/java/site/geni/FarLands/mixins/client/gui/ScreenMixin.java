package site.geni.FarLands.mixins.client.gui;

import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(Screen.class)
public abstract class ScreenMixin {
	@Shadow
	public int screenWidth;

	@Shadow
	public int screenHeight;

	/**
	 * Adds a {@link AbstractButtonWidget} to the {@link Screen} <br>
	 * Used by {@link SettingsScreenMixin} and {@link NewLevelScreenMixin}
	 *
	 * @param abstractButtonWidget {@link AbstractButtonWidget} to add
	 * @param <T>                  Any class that extends {@link AbstractButtonWidget}
	 * @return The added {@link AbstractButtonWidget}
	 * @author geni
	 * @see SettingsScreenMixin
	 * @see NewLevelScreenMixin
	 */
	@Shadow
	protected abstract <T extends AbstractButtonWidget> T addButton(T abstractButtonWidget);
}
