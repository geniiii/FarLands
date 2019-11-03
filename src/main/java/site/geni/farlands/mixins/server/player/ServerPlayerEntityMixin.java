package site.geni.farlands.mixins.server.player;

import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@SuppressWarnings("unused")
@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {
	/**
	 * Clamps server player entity portal teleportation to -{@link Double#MAX_VALUE}
	 *
	 * @param original Original double of -2.9999872E7
	 * @return -{@link Double#MAX_VALUE}
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			doubleValue = -2.9999872E7D
		),
		method = "changeDimension"
	)
	private static double clampTeleportToNegativeDoubleMaxValueXZ(double original) {
		return -Double.MAX_VALUE;
	}

	/**
	 * Clamps server player entity portal teleportation to {@link Double#MAX_VALUE}
	 *
	 * @param original Original double of 2.9999872E7
	 * @return {@link Double#MAX_VALUE}
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			doubleValue = 2.9999872E7D
		),
		method = "changeDimension"
	)
	private static double clampTeleportToPositiveDoubleMaxValueXZ(double original) {
		return Double.MAX_VALUE;
	}
}
