package site.geni.FarLands.mixins.common.generators;

import net.minecraft.world.gen.chunk.OverworldChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import site.geni.FarLands.utils.Config;

@SuppressWarnings("unused")
@Mixin(OverworldChunkGenerator.class)
public abstract class OverworldChunkGeneratorMixin {
	/**
	 * Sets the overworld generator type's coordinate scale to the one set in the mod's configuration
	 *
	 * @param original Original double value of 684.4119873046875
	 * @return Coordinate scale as set in the mod's configuration
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			doubleValue = 684.4119873046875D,
			ordinal = 2
		),
		method = "sampleNoiseColumn"
	)
	private static double setCoordinateScale(double original) {
		return Config.getConfig().coordinateScale * Config.getConfig().coordinateScaleMultiplier;
	}

	/**
	 * Sets the overworld generator type's height scale to the one set in the mod's configuration
	 *
	 * @param original Original double value of 684.4119873046875
	 * @return Height scale as set in the mod's configuration
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			doubleValue = 684.4119873046875D,
			ordinal = 3
		),
		method = "sampleNoiseColumn"
	)
	private static double setHeightScale(double original) {
		return Config.getConfig().heightScale * Config.getConfig().heightScaleMultiplier;
	}
}
