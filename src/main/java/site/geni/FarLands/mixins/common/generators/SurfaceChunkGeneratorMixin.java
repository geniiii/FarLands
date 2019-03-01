package site.geni.FarLands.mixins.common.generators;

import net.minecraft.world.gen.chunk.SurfaceChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import site.geni.FarLands.utils.Config;

import static net.minecraft.util.math.noise.OctavePerlinNoiseSampler.maintainPrecision;

@SuppressWarnings("unused")
@Mixin(SurfaceChunkGenerator.class)
public abstract class SurfaceChunkGeneratorMixin {
	@Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/noise/OctavePerlinNoiseSampler;maintainPrecision(D)D"), method = "sampleNoise")
	private double dontMaintainPrecision(double double_1) {
		if (Config.getConfig().farLandsEnabled) {
			return double_1;
		} else {
			return maintainPrecision(double_1);
		}
	}
}
