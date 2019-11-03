package site.geni.farlands.mixins.common.noise;

import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import site.geni.farlands.FarLands;

import static net.minecraft.util.math.noise.OctavePerlinNoiseSampler.maintainPrecision;


@SuppressWarnings("unused")
@Mixin(OctavePerlinNoiseSampler.class)
public abstract class OctavePerlinNoiseSamplerMixin {
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
		method = "sample(DDDDDZ)D"
	)
	private double dontMaintainPrecision(double coordinate) {
		return FarLands.getConfig().farLandsEnabled.getValue() ? coordinate : maintainPrecision(coordinate);
	}
}
