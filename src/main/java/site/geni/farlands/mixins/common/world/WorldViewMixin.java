package site.geni.farlands.mixins.common.world;

import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@SuppressWarnings("unused")
@Mixin(WorldView.class)
public interface WorldViewMixin {
	/**
	 * Make anything down to X/Z: {@link Integer#MIN_VALUE} a valid position
	 *
	 * @param original Original integer of -30000000
	 * @return {@link Integer#MIN_VALUE}
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			intValue = -30000000
		),
		method = "getLightLevel(Lnet/minecraft/util/math/BlockPos;I)I"
	)
	static int getLightLevelUpToNegativeIntegerMaxValueXZ(int original) {
		return Integer.MIN_VALUE;
	}

	/**
	 * Make anything down to X/Z: {@link Integer#MAX_VALUE} a valid position
	 *
	 * @param original Original integer of 30000000
	 * @return {@link Integer#MAX_VALUE}
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			intValue = 30000000
		),
		method = "getLightLevel(Lnet/minecraft/util/math/BlockPos;I)I"
	)
	static int getLightLevelUpToIntegerMaxValueXZ(int original) {
		return Integer.MAX_VALUE;
	}
}
