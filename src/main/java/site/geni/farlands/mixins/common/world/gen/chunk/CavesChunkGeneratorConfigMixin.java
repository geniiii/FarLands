package site.geni.farlands.mixins.common.world.gen.chunk;

import net.minecraft.world.gen.chunk.CavesChunkGeneratorConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import site.geni.farlands.FarLands;

@Mixin(CavesChunkGeneratorConfig.class)
public abstract class CavesChunkGeneratorConfigMixin {
	/**
	 * Sets Buffet's Caves'/the Nether's chunk generator config's max Y, depending on the mod's configuration
	 *
	 * @param original Original integer value of 128
	 * @return Either 256 or the default of 128, depending on the mod's configuration
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			intValue = 127
		),
		method = "getMaxY"
	)
	private static int setMaxY(int original) {
		return FarLands.getConfig().raiseNetherHeightLimit.getValue() ? 255 : original;
	}
}
