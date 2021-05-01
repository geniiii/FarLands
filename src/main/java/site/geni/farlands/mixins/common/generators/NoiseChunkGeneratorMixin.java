package site.geni.farlands.mixins.common.generators;

import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import site.geni.farlands.FarLands;

import static net.minecraft.util.math.noise.OctavePerlinNoiseSampler.maintainPrecision;

@SuppressWarnings("unused")
@Mixin(NoiseChunkGenerator.class)
public abstract class NoiseChunkGeneratorMixin {
	/**
	 * Maintains precision based on the mod's configuration
	 *
	 * @param coordinate A coordinate (X/Y/Z, does not matter)
	 * @return If the Far Lands are enabled, the coordinate without maintaining precision; if not, vanilla behaviour
	 * @author geni
	 */
	@Redirect(
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/util/math/noise/OctavePerlinNoiseSampler;maintainPrecision(D)D"
			),
			method = "sampleNoise"
	)
	private double dontMaintainPrecision(double coordinate) {
		return FarLands.getConfig().farLandsEnabled.getValue() ? coordinate : maintainPrecision(coordinate);
	}
	/**
	 * Sets the overworld generator type's coordinate scale to the one set in the mod's configuration
	 *
	 * @param original Original double value of 684.412
	 * @return Coordinate scale as set in the mod's configuration
	 * @author geni
	 */
	@ModifyConstant(
			constant = @Constant(
					doubleValue = 684.412D,
					ordinal = 0
			),
			method = "sampleNoiseColumn([DII)V"
	)
	private static double setCoordinateScaleOW(double original) {
		return FarLands.getConfig().coordinateScale.getValue() * FarLands.getConfig().coordinateScaleMultiplier.getValue();
	}
	/**
	 * Sets the overworld generator type's height scale to the one set in the mod's configuration
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
			method = "sampleNoiseColumn([DII)V"
	)
	private static double setHeightScaleOW(double original) {
		return FarLands.getConfig().heightScale.getValue() * FarLands.getConfig().heightScaleMultiplier.getValue();
	}

}
