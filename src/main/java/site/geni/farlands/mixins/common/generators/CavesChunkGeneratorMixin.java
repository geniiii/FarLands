package site.geni.farlands.mixins.common.generators;

import net.minecraft.world.gen.chunk.CavesChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import site.geni.farlands.FarLands;

@SuppressWarnings("unused")
@Mixin(CavesChunkGenerator.class)
public abstract class CavesChunkGeneratorMixin {
	/**
	 * Sets Buffet's Caves'/The Nether's chunk generator type's coordinate scale to the one set in the mod's configuration
	 *
	 * @param original Original double value of 684.412
	 * @return Coordinate scale as set in the mod's configuration
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			doubleValue = 684.412D,
			ordinal = 1
		),
		method = "sampleNoiseColumn"
	)
	private static double setCoordinateScale(double original) {
		return FarLands.getConfig().coordinateScale.getValue() * FarLands.getConfig().coordinateScaleMultiplier.getValue();
	}

	/**
	 * Sets Buffet's Caves'/The Nether's chunk generator type's height scale to the one set in the mod's configuration
	 *
	 * @param original Original double value of 2053.236
	 * @return Height scale as set in the mod's configuration
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			doubleValue = 2053.236D,
			ordinal = 1
		),
		method = "sampleNoiseColumn"
	)
	private static double setHeightScale(double original) {
		return FarLands.getConfig().heightScale.getValue() * FarLands.getConfig().heightScaleMultiplier.getValue();
	}

	/**
	 * Sets Buffet's Caves'/The Nether's chunk generator type's height limit to the one set in the mod's configuration
	 *
	 * @param original Original integer value of 128
	 * @return Either the default of 128 or 256, depending on the mod's configuration
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			intValue = 128
		),
		method = "<init>"
	)
	private static int setHeightLimitConstructor(int original) {
		return FarLands.getConfig().raiseNetherHeightLimit.getValue() ? 256 : original;
	}

	/**
	 * Sets Buffet's Caves'/The Nether's chunk generator type's max Y to the one set in the mod's configuration
	 *
	 * @param original Original integer value of 128
	 * @return Either 256 or the default of 128 , depending on the mod's configuration
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			intValue = 128
		),
		method = "getMaxY"
	)
	private static int setMaxY(int original) {
		return FarLands.getConfig().raiseNetherHeightLimit.getValue() ? 256 : original;
	}
}
