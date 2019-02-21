package site.geni.FarLands.mixins.common;

import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings("unused")
@Mixin(OctavePerlinNoiseSampler.class)
public abstract class OctavePerlinNoiseSamplerMixin {
	@Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/noise/OctavePerlinNoiseSampler;maintainPrecision(D)D"), method = "sample(DDDDDZ)D")
	private double dontMaintainPrecision(double double_1) {
		return double_1;
	}
}
