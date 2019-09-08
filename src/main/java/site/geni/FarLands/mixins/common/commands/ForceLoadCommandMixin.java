package site.geni.farlands.mixins.common.commands;

import net.minecraft.server.command.ForceLoadCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@SuppressWarnings("unused")
@Mixin(ForceLoadCommand.class)
public abstract class ForceLoadCommandMixin {
	/**
	 * Lets the player use the {@code /forceload} command up to X/Z: 3.0E7
	 *
	 * @param original Original integer value of 30000000
	 * @return {@link Integer#MAX_VALUE}
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			intValue = 30000000
		),
		method = "executeChange"
	)
	private static int forceLoadUpToPositiveIntegerMaxValue(int original) {
		return Integer.MAX_VALUE;
	}

	/**
	 * Lets the player use the {@code /forceload} command down to X/Z: -3.0E7
	 *
	 * @param original Original integer value of -30000000
	 * @return {@link Integer#MIN_VALUE}
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			intValue = -30000000
		),
		method = "executeChange"
	)
	private static int forceLoadUpToNegativeIntegerMaxValue(int original) {
		return Integer.MIN_VALUE;
	}
}
