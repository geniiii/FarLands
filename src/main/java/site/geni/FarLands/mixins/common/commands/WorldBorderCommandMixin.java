package site.geni.farlands.mixins.common.commands;

import net.minecraft.server.command.WorldBorderCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@SuppressWarnings("unused")
@Mixin(WorldBorderCommand.class)
public abstract class WorldBorderCommandMixin {
	/**
	 * Lets the player use the {@code /worldborder set} command down to -{@link Float#MAX_VALUE}
	 *
	 * @param original Original float value of -6.0E7
	 * @return -{@link Float#MAX_VALUE}
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			floatValue = -6.0E7F
		),
		method = "register"
	)
	private static float radiusArgumentUnderNegative60Mil(float original) {
		return -Float.MAX_VALUE;
	}

	/**
	 * Lets the player use the {@code /worldborder set} command up to {@link Float#MAX_VALUE}
	 *
	 * @param original Original float value of 6.0E7
	 * @return {@link Float#MAX_VALUE}
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			floatValue = 6.0E7F
		),
		method = "register"
	)
	private static float radiusArgumentOverPositive60Mil(float original) {
		return Float.MAX_VALUE;
	}

	/**
	 * Sets {@code /worldborder set} limit to {@link Double#MAX_VALUE}
	 *
	 * @param original Original double value of 6.0E7
	 * @return {@link Double#MAX_VALUE}
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			doubleValue = 6.0E7D
		),
		method = "executeSet"
	)
	private static double ableToSetRadiusOver60Mil(double original) {
		return Double.MAX_VALUE;
	}
}
