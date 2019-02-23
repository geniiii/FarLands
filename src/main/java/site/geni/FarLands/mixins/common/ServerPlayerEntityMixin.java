package site.geni.FarLands.mixins.common;

import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@SuppressWarnings("unused")
@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {
	@ModifyConstant(constant = @Constant(doubleValue = -2.9999872E7D), method = "changeDimension")
	private static double clampTeleportToNegativeDoubleMaxValueXZ(double original) {
		System.out.println(original);
		return -Double.MAX_VALUE;
	}

	@ModifyConstant(constant = @Constant(doubleValue = 2.9999872E7D), method = "changeDimension")
	private static double clampTeleportToPositiveDoubleMaxValueXZ(double original) {
		return Double.MAX_VALUE;
	}
}
