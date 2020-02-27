package site.geni.farlands.mixins.client.fluid;

import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import site.geni.farlands.FarLands;

import java.util.Random;

@SuppressWarnings("unused")
@Mixin(LavaFluid.class)
public abstract class LavaFluidMixin {
	/**
	 * Adds particles and sounds created by lava fluids using {@link Double} for positions instead of {@link Float} in order to have precise particle and sound positions
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
			double x = blockPos.getX() + (double) random.nextFloat();
			double y = blockPos.getY() + 1;
			double z = blockPos.getZ() + (double) random.nextFloat();

			world.addParticle(ParticleTypes.LAVA, x, y, z, 0.0D, 0.0D, 0.0D);
			world.playSound(x, y, z, SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);

			if (random.nextInt(200) == 0) {
				world.playSound(blockPos.getX(),  blockPos.getY(), blockPos.getZ(), SoundEvents.BLOCK_LAVA_AMBIENT, SoundCategory.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
			}

			ci.cancel();
		}
	}
}
