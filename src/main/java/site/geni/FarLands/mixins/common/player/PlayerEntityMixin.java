package site.geni.FarLands.mixins.common.player;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@SuppressWarnings("unused")
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
	@ModifyConstant(
			constant = @Constant(
					doubleValue = -2.9999999E7D
			),
			method = "update"
	)
	private static double clampToNegativeDoubleMaxValueXZ(double original) {
		return Double.MIN_VALUE;
	}

	@ModifyConstant(
			constant = @Constant(
					doubleValue = 2.9999999E7D
			),
			method = "update"
	)
	private static double clampToPositiveDoubleMaxValueXZ(double original) {
		return Double.MAX_VALUE;
	}
}
