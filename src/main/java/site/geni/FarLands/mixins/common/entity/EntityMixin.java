package site.geni.FarLands.mixins.common.entity;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@SuppressWarnings("unused")
@Mixin(Entity.class)
public abstract class EntityMixin {
	@Shadow
	public double x;
	@Shadow
	public double z;

	@ModifyConstant(
			constant = @Constant(
					doubleValue = -3.0E7D
			),
			method = "setPositionAnglesAndUpdate(DDDFF)V"
	)
	private static double clampPositionToNegativeDoubleMaxValueXZ(double original) {
		return -Double.MAX_VALUE;
	}

	@ModifyConstant(
			constant = @Constant(
					doubleValue = 3.0E7D
			),
			method = "setPositionAnglesAndUpdate(DDDFF)V"
	)
	private static double clampPositionToPositiveDoubleMaxValueXZ(double original) {
		return Double.MAX_VALUE;
	}

	@ModifyConstant(
			constant = @Constant(
					doubleValue = -2.9999872E7D
			),
			method = "changeDimension"
	)
	private static double clampTeleportToNegativeDoubleMaxValueXZ(double original) {
		return -Double.MAX_VALUE;
	}

	@ModifyConstant(
			constant = @Constant(
					doubleValue = 2.9999872E7D
			),
			method = "changeDimension"
	)
	private static double clampTeleportToPositiveDoubleMaxValueXZ(double original) {
		return Double.MAX_VALUE;
	}

	/* used by the FallingBlockEntity mixin */
	@Shadow
	public abstract void kill();
}
