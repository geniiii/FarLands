package site.geni.farlands.mixins.common.world.biome;

import net.minecraft.world.biome.NetherBiome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import site.geni.farlands.FarLands;

@Mixin(NetherBiome.class)
public abstract class NetherBiomeMixin {
	/**
	 * Sets the Nether biome's features' height limit to 256, depending on the mod's configuration
	 *
	 * @param original Original integer value of 128
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
