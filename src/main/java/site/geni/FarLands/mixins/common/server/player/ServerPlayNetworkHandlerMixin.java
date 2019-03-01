package site.geni.FarLands.mixins.common.server.player;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@SuppressWarnings("unused")
@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin {
	@ModifyConstant(
			constant = @Constant(
					doubleValue = 3.0E7D
			),
			method = "validatePlayerMove"
	)
	private static double letPlayerMoveUpToDoubleMaxValue(double d) {
		return Double.MAX_VALUE;
	}
}
