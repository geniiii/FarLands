package site.geni.FarLands.mixins.common.world;

import net.minecraft.class_4538;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(class_4538.class)
public interface Unmapped4538Mixin {
	/**
	 * Gets top block inside the ground in sample up to X/Z: {@link Integer#MIN_VALUE} instead of -3.0E7
	 *
	 * @param original Original integer of -30000000
	 * @return {@link Integer#MIN_VALUE}
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			intValue = -30000000
		),
		method = "method_22346"
	)
	default int getTopUpToNegativeIntegerMaxValueXZ(int original) {
		return Integer.MIN_VALUE;
	}

	/**
	 * Gets top block inside the ground in sample up to X/Z: {@link Integer#MAX_VALUE} instead of 3.0E7
	 *
	 * @param original Original integer of 30000000
	 * @return {@link Integer#MAX_VALUE}
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			intValue = 30000000
		),
		method = "method_22346"
	)
	default int getTopUpToPositiveIntegerMaxValueXZ(int original) {
		return Integer.MAX_VALUE;
	}
}
