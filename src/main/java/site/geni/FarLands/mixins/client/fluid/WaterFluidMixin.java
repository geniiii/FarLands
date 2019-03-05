package site.geni.FarLands.mixins.client.fluid;

import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import site.geni.FarLands.utils.Config;

import java.util.Random;

@SuppressWarnings("unused")
@Mixin(WaterFluid.class)
public abstract class WaterFluidMixin {
	@Inject(
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleParameters;DDDDDD)V"
			),
			method = "randomDisplayTick",
			cancellable = true
	)
	private void addParticlesProperly(World world, BlockPos blockPos, FluidState fluidState, Random random, CallbackInfo ci) {
		if (Config.getConfig().fixParticles && Config.getConfig().farLandsEnabled) {
			world.addParticle(ParticleTypes.UNDERWATER, blockPos.getX() + random.nextDouble(), blockPos.getY() + random.nextDouble(), blockPos.getZ() + random.nextDouble(), 0.0D, 0.0D, 0.0D);

			ci.cancel();
		}
	}
}
