package site.geni.FarLands.mixins.common;

import net.minecraft.server.command.ForceLoadCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@SuppressWarnings("unused")
@Mixin(ForceLoadCommand.class)
public abstract class ForceLoadCommandMixin {
	@ModifyConstant(constant = @Constant(intValue = 30000000), method = "setForceLoaded")
	private static int forceLoadUpToPositiveIntegerMaxValue(int original) {
		return Integer.MAX_VALUE;
	}

	@ModifyConstant(constant = @Constant(intValue = -30000000), method = "setForceLoaded")
	private static int forceLoadUpToNegativeIntegerMaxValue(int original) {
		return -Integer.MAX_VALUE;
	}
}
