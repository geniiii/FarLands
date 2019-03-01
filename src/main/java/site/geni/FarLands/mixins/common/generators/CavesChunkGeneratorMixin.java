package site.geni.FarLands.mixins.common.generators;

import net.minecraft.world.gen.chunk.CavesChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import site.geni.FarLands.utils.Config;

@SuppressWarnings("unused")
@Mixin(CavesChunkGenerator.class)
public abstract class CavesChunkGeneratorMixin {
	@ModifyConstant(constant = @Constant(doubleValue = 684.412D, ordinal = 1), method = "sampleNoiseColumn")
	private static double setCoordinateScale(double original) {
		return Config.getConfig().coordinateScale * Config.getConfig().coordinateScaleMultiplier;
	}

	@ModifyConstant(constant = @Constant(doubleValue = 2053.236D, ordinal = 1), method = "sampleNoiseColumn")
	private static double setHeightScale(double original) {
		return Config.getConfig().heightScale * Config.getConfig().heightScaleMultiplier;
	}
}
