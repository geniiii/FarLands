package site.geni.farlands.mixins.common.world.gen.decorator;

import net.minecraft.world.gen.decorator.HellFireDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import site.geni.farlands.FarLands;

@Mixin(HellFireDecorator.class)
public abstract class HellFireDecoratorMixin {
	/**
	 * Sets the height limit for fire generation to 120 (+ 4), depending on the mod's configuration
	 *
	 * @param original The original integer value of 120
	 * @return Either 248 or the default of 120, depending on the mod's configuration
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			intValue = 120
		),
		method = "method_15947"
	)
	private static int setHeightLimit(int original) {
		return FarLands.getConfig().raiseNetherHeightLimit.getValue() ? 248 : original;
	}
}
