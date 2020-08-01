package site.geni.farlands.mixins.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import site.geni.farlands.mixins.server.entity.FallingBlockEntityMixin;

import javax.annotation.Nullable;

@SuppressWarnings("unused")
@Mixin(Entity.class)
public abstract class EntityMixin {
	@Shadow
	@Nullable
	public abstract MinecraftServer getServer();

	@Shadow
	public abstract double getZ();

	@Shadow
	public abstract double getX();

	/**
	 * Clamps entity position to -{@link Double#MAX_VALUE}
	 *
	 * @param original Original double of -2.9999872E7
	 * @return -{@link Double#MAX_VALUE}
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			doubleValue = -3.0E7D
		),
		method = "method_30634"
	)
	private static double clampPositionNegativeDoubleMaxValueXZ(double original) {
		return -Double.MAX_VALUE;
	}

	/**
	 * Clamps entity position to {@link Double#MAX_VALUE}
	 *
	 * @param original Original double of 2.9999872E7
	 * @return {@link Double#MAX_VALUE}
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			doubleValue = 3.0E7D
		),
		method = "method_30634"
	)
	private static double clampPositionToPositiveDoubleMaxValueXZ(double original) {
		return Double.MAX_VALUE;
	}

	/**
	 * Removes/kills the entity. <br>
	 * Used by {@link FallingBlockEntityMixin#killFallingBlockEntities(CallbackInfo ci)}
	 *
	 * @author geni
	 * @see FallingBlockEntityMixin#killFallingBlockEntities(CallbackInfo ci)
	 */
	@Shadow
	public abstract void remove();
}
