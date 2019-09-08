package site.geni.farlands.mixins.server;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@SuppressWarnings("unused")
@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
	/**
	 * Sets maximum world border radius to {@link Integer#MAX_VALUE}
	 *
	 * @param original Original integer of 29999984
	 * @return {@link Integer#MAX_VALUE}
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			intValue = 29999984
		),
		method = "getMaxWorldBorderRadius"
	)
	private int setMaxRadius(int original) {
		return Integer.MAX_VALUE;
	}
}
