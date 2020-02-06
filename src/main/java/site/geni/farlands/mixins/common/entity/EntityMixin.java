package site.geni.farlands.mixins.common.entity;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
@Mixin(Entity.class)
public abstract class EntityMixin {
	@Shadow
	public abstract double getZ();

	@Shadow
	public abstract double getX();

	/**
	 * Clamps entity positions to -{@link Double#MAX_VALUE} instead of -3.0E7
	 *
	 * @param original Original double of -3.0E7
	 * @return -{@link Double#MAX_VALUE}
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			doubleValue = -3.0E7D
		),
		method = "updatePositionAndAngles(DDDFF)V"
	)
	private static double clampPositionToNegativeDoubleMaxValueXZ(double original) {
		return -Double.MAX_VALUE;
	}

	/**
	 * Clamps entity positions to positive {@link Double#MAX_VALUE} instead of 3.0E7
	 *
	 * @param original Original double of 3.0E7
	 * @return {@link Double#MAX_VALUE}
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			doubleValue = 3.0E7D
		),
		method = "updatePositionAndAngles(DDDFF)V"
	)
	private static double clampPositionToPositiveDoubleMaxValueXZ(double original) {
		return Double.MAX_VALUE;
	}

	/**
	 * Clamps entity portal teleportation to -{@link Double#MAX_VALUE}
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
	 * Clamps entity portal teleportation to {@link Double#MAX_VALUE}
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

	/**
	 * Kills/invalidates the entity. <br>
	 * Used by {@link FallingBlockEntityMixin#killFallingBlockEntities(CallbackInfo ci)}
	 *
	 * @author geni
	 * @see FallingBlockEntityMixin#killFallingBlockEntities(CallbackInfo ci)
	 */
	@Shadow
	public abstract void remove();
}
