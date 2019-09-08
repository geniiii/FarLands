package site.geni.farlands.mixins.server.player;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@SuppressWarnings("unused")
@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin {
	/**
	 * Lets the player move up to {@link Double#MAX_VALUE} without getting kicked for being in an invalid position
	 *
	 * @param original Original double of 3.0E7
	 * @return {@link Double#MAX_VALUE}
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			doubleValue = 3.0E7D
		),
		method = "validatePlayerMove"
	)
	private static double letPlayerMoveUpToDoubleMaxValue(double original) {
		return Double.MAX_VALUE;
	}
}
