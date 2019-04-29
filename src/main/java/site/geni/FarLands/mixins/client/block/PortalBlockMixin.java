package site.geni.FarLands.mixins.client.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.PortalBlock;
import net.minecraft.particle.ParticleParameters;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import site.geni.FarLands.FarLands;

import java.util.Random;


@SuppressWarnings("unused")
@Mixin(PortalBlock.class)
public abstract class PortalBlockMixin {
	private static BlockPos pos;
	private static Random random;

	/**
	 * Sets {@link #pos} and {@link #random} to the block's {@link BlockPos} and the block's world's {@link Random} instance respectively
	 *
	 * @param ci          {@link CallbackInfo} required for {@link Inject}
	 * @param blockState  {@link BlockState} of the block
	 * @param world       {@link World} of the block
	 * @param blockPos    {@link BlockPos} of the block
	 * @param worldRandom {@code world}'s {@link Random} instance
	 * @author geni
	 */
	@Inject(
		at = @At(
			value = "HEAD"
		),
		method = "randomDisplayTick"
	)
	private void setVariables(BlockState blockState, World world, BlockPos blockPos, Random worldRandom, CallbackInfo ci) {
		if (FarLands.getConfig().fixParticlesEntities) {
			random = worldRandom;
			pos = blockPos;
		}
	}

	/**
	 * Adds particles created by Nether portal blocks using {@link Double} for positions instead of {@link Float} in order to have precise particle positions
	 *
	 * @param world              {@link World} of the block
	 * @param particleParameters {@link ParticleParameters} to use when adding particle
	 * @param xOrig              The particle's original X position
	 * @param yOrig              The particle's original Y position
	 * @param zOrig              The particle's original Z position
	 * @param velocityXOrig      The particle's original X velocity
	 * @param velocityYOrig      The particle's original Y velocity
	 * @param velocityZOrig      The particle's original Z velocity
	 * @author geni
	 */
	@Redirect(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleParameters;DDDDDD)V"
		),
		method = "randomDisplayTick"
	)
	private void addParticlesProperly(World world, ParticleParameters particleParameters, double xOrig, double yOrig, double zOrig, double velocityXOrig, double velocityYOrig, double velocityZOrig) {
		if (FarLands.getConfig().fixParticlesEntities) {
			double x = pos.getX() + (double) random.nextFloat();
			final double y = pos.getY() + (double) random.nextFloat();
			double z = pos.getZ() + (double) random.nextFloat();

			double velocityX = ((double) random.nextFloat() - 0.5D) * 0.5D;
			final double velocityY = ((double) random.nextFloat() - 0.5D) * 0.5D;
			double velocityZ = ((double) random.nextFloat() - 0.5D) * 0.5D;

			int int_2 = random.nextInt(2) * 2 - 1;

			if (!(world.getBlockState(pos.west()).getBlock() instanceof PortalBlock) && !(world.getBlockState(pos.east()).getBlock() instanceof PortalBlock)) {
				x = pos.getX() + 0.5D + 0.25D * (double) int_2;
				velocityX = (double) random.nextFloat() * 2.0D * (double) int_2;
			} else {
				z = pos.getZ() + 0.5D + 0.25D * (double) int_2;
				velocityZ = (double) random.nextFloat() * 2.0D * (double) int_2;
			}

			world.addParticle(particleParameters, x, y, z, velocityX, velocityY, velocityZ);
		} else {
			world.addParticle(particleParameters, xOrig, yOrig, zOrig, velocityXOrig, velocityYOrig, velocityZOrig);
		}
	}
}
