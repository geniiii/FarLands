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
import site.geni.FarLands.FarLands;

import java.util.Random;

@SuppressWarnings("unused")
@Mixin(WaterFluid.class)
public abstract class WaterFluidMixin {
	/**
	 * Adds particles created by water fluids using {@link Double} for positions instead of {@link Float} in order to have precise particle positions
	 *
	 * @param ci         {@link CallbackInfo} required for {@link Inject}
	 * @param fluidState {@link FluidState} of the block
	 * @param world      {@link World} of the block
	 * @param blockPos   {@link BlockPos} of the block
	 * @param random     {@code world}'s {@link Random} instance
	 * @author geni
	 */
	@Inject(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"
		),
		method = "randomDisplayTick",
		cancellable = true
	)
	private void addParticlesProperly(World world, BlockPos blockPos, FluidState fluidState, Random random, CallbackInfo ci) {
		if (FarLands.getConfig().fixParticlesEntities.getValue()) {
			world.addParticle(ParticleTypes.UNDERWATER, blockPos.getX() + (double) random.nextFloat(), blockPos.getY() + (double) random.nextFloat(), blockPos.getZ() + (double) random.nextFloat(), 0.0D, 0.0D, 0.0D);

			ci.cancel();
		}
	}
}
