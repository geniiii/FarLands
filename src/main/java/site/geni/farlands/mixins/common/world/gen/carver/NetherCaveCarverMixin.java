package site.geni.farlands.mixins.common.world.gen.carver;

import net.minecraft.world.gen.carver.NetherCaveCarver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import site.geni.farlands.FarLands;

@Mixin(NetherCaveCarver.class)
public abstract class NetherCaveCarverMixin {
	/**
	 * Sets the Nether cave carver's height limit to 256, depending on the mod's configuration
	 *
	 * @param original The original integer value of 128
	 * @return Either 256 or the default of 128, depending on the mod's configuration
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			intValue = 128
		),
		method = "<init>"
	)
	private static int setHeightLimit(int original) {
		return FarLands.getConfig().raiseNetherHeightLimit.getValue() ? 256 : original;
	}
}
