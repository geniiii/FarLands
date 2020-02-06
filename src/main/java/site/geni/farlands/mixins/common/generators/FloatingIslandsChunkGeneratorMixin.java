package site.geni.farlands.mixins.common.generators;

import net.minecraft.world.gen.chunk.FloatingIslandsChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import site.geni.farlands.FarLands;

@SuppressWarnings("unused")
@Mixin(FloatingIslandsChunkGenerator.class)
public abstract class FloatingIslandsChunkGeneratorMixin {
	/**
	 * Sets Buffet's Floating Islands'/The End's chunk generator type's coordinate scale to the one set in the mod's configuration
	 *
	 * @param original Original double value of 1368.824
	 * @return Coordinate scale as set in the mod's configuration
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			doubleValue = 1368.824D,
			ordinal = 1
		),
		method = "sampleNoiseColumn"
	)
	private static double setCoordinateScale(double original) {
		return FarLands.getConfig().coordinateScale.getValue() * FarLands.getConfig().coordinateScaleMultiplier.getValue();
	}

	/**
	 * Sets Buffet's Floating Islands'/The End's chunk generator type's height scale to the one set in the mod's configuration
	 *
	 * @param original Original double value of 684.412
	 * @return Height scale as set in the mod's configuration
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			doubleValue = 684.412D,
			ordinal = 1
		),
		method = "sampleNoiseColumn"
	)
	private static double setHeightScale(double original) {
		return FarLands.getConfig().heightScale.getValue() * FarLands.getConfig().heightScaleMultiplier.getValue();
	}
}
