package site.geni.FarLands.mixins.common.commands;

import net.minecraft.server.command.WorldBorderCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@SuppressWarnings("unused")
@Mixin(WorldBorderCommand.class)
public abstract class WorldBorderCommandMixin {
	@ModifyConstant(
			constant = @Constant(
					floatValue = -6.0E7F
			),
			method = "register"
	)
	private static float radiusArgumentUnderNegative60Mil(float i) {
		return -Float.MAX_VALUE;
	}

	@ModifyConstant(
			constant = @Constant(
					floatValue = 6.0E7F
			),
			method = "register"
	)
	private static float radiusArgumentOverPositive60Mil(float i) {
		return Float.MAX_VALUE;
	}

	@ModifyConstant(
			constant = @Constant(
					doubleValue = 6.0E7D
			),
			method = "method_13854(Lnet/minecraft/server/command/ServerCommandSource;DJ)I"
	)
	private static double ableToSetRadiusOver60Mil(double i) {
		return Double.MAX_VALUE;
	}
}
