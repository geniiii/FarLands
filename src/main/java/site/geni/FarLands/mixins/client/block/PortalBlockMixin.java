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
import site.geni.FarLands.utils.Config;

import java.util.Random;


@SuppressWarnings("unused")
@Mixin(PortalBlock.class)
public abstract class PortalBlockMixin {
	private static final ThreadLocal<BlockPos> blockPosThreadLocal = new ThreadLocal<>();
	private static final ThreadLocal<Random> randomThreadLocal = new ThreadLocal<>();


	@Inject(
			at = @At(
					value = "HEAD"
			),
			method = "randomDisplayTick"
	)
	private void setThreadLocals(BlockState blockState, World world, BlockPos blockPos, Random random, CallbackInfo ci) {
		if (Config.getConfig().fixParticles && Config.getConfig().farLandsEnabled) {
			blockPosThreadLocal.set(blockPos);
			randomThreadLocal.set(random);
		}
	}

	@Redirect(
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleParameters;DDDDDD)V"
			),
			method = "randomDisplayTick"
	)
	private void addParticlesProperly(World world, ParticleParameters particleParameters, double xOrig, double yOrig, double zOrig, double velocityXOrig, double velocityYOrig, double velocityZOrig) {
		if (Config.getConfig().fixParticles && Config.getConfig().farLandsEnabled) {
			final Random random = randomThreadLocal.get();
			final BlockPos blockPos = blockPosThreadLocal.get();

			double x = blockPos.getX() + random.nextDouble();
			final double y = blockPos.getY() + random.nextDouble();
			double z = blockPos.getZ() + random.nextDouble();

			double velocityX = (random.nextDouble() - 0.5D) * 0.5D;
			final double velocityY = (random.nextDouble() - 0.5D) * 0.5D;
			double velocityZ = (random.nextDouble() - 0.5D) * 0.5D;

			int int_2 = random.nextInt(2) * 2 - 1;

			if (!(world.getBlockState(blockPos.west()).getBlock() instanceof PortalBlock) && !(world.getBlockState(blockPos.east()).getBlock() instanceof PortalBlock)) {
				x = blockPos.getX() + 0.5D + 0.25D * (double) int_2;
				velocityX = random.nextDouble() * 2.0D * (double) int_2;
			} else {
				z = blockPos.getZ() + 0.5D + 0.25D * (double) int_2;
				velocityZ = random.nextDouble() * 2.0D * (double) int_2;
			}

			world.addParticle(particleParameters, x, y, z, velocityX, velocityY, velocityZ);
		} else {
			world.addParticle(particleParameters, xOrig, yOrig, zOrig, velocityXOrig, velocityYOrig, velocityZOrig);
		}
	}

	@Inject(
			at = @At(
					value = "RETURN"
			),
			method = "randomDisplayTick"
	)
	private void removeThreadLocals(BlockState blockState, World world, BlockPos blockPos, Random random, CallbackInfo ci) {
		if (Config.getConfig().fixParticles && Config.getConfig().farLandsEnabled) {
			blockPosThreadLocal.remove();
			randomThreadLocal.remove();
		}
	}
}
