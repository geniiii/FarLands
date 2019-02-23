package site.geni.FarLands.mixins.common;

import net.minecraft.world.gen.chunk.OverworldChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import site.geni.FarLands.utils.Config;

@SuppressWarnings("unused")
@Mixin(OverworldChunkGenerator.class)
public abstract class OverworldChunkGeneratorMixin {
	@ModifyConstant(constant = @Constant(doubleValue = 684.4119873046875D, ordinal = 2), method = "sampleNoiseColumn")
	private static double setCoordinateScale(double original) {
		return Config.getConfig().coordinateScale * Config.getConfig().coordinateScaleMultiplier;
	}

	@ModifyConstant(constant = @Constant(doubleValue = 684.4119873046875D, ordinal = 3), method = "sampleNoiseColumn")
	private static double setHeightScale(double original) {
		return Config.getConfig().heightScale * Config.getConfig().heightScaleMultiplier;
	}
}
