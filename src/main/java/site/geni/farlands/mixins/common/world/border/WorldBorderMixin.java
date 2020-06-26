package site.geni.farlands.mixins.common.world.border;

import net.minecraft.world.border.WorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@SuppressWarnings("unused")
@Mixin(WorldBorder.class)
public abstract class WorldBorderMixin {
	@ModifyConstant(
		constant = @Constant(
			doubleValue = 6.0E7D
		),
		method = "<clinit>"
	)
	private static double setDefaultWorldBorderSize(double original) {
		return Double.MAX_VALUE;
	}
}
