package site.geni.farlands.mixins.common.player;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@SuppressWarnings("unused")
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
	/**
	 * Clamps player entity position to -{@link Double#MAX_VALUE} instead of -2.9999999E7D
	 *
	 * @param original Original double of -2.9999999E7D
	 * @return -{@link Double#MAX_VALUE}
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			doubleValue = -2.9999999E7D
		),
		method = "tick"
	)
	private static double clampToNegativeDoubleMaxValueXZ(double original) {
		return -Double.MAX_VALUE;
	}

	/**
	 * Clamps player entity position to {@link Double#MAX_VALUE} instead of 2.9999999E7D
	 *
	 * @param original Original double of 2.9999999E7D
	 * @return {@link Double#MAX_VALUE}
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			doubleValue = 2.9999999E7D
		),
		method = "tick"
	)
	private static double clampToPositiveDoubleMaxValueXZ(double original) {
		return Double.MAX_VALUE;
	}
}
