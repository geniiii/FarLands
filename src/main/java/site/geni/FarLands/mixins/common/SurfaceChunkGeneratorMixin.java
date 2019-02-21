package site.geni.FarLands.mixins.common;

import net.minecraft.world.gen.chunk.SurfaceChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings("unused")
@Mixin(SurfaceChunkGenerator.class)
public abstract class SurfaceChunkGeneratorMixin {
	@Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/noise/OctavePerlinNoiseSampler;maintainPrecision(D)D"), method = "sampleNoise")
	private double dontMaintainPrecision(double double_1) {
		return double_1;
	}
}
