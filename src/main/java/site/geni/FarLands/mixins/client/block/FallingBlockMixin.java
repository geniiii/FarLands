package site.geni.FarLands.mixins.client.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.particle.BlockStateParticleParameters;
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
@Mixin(FallingBlock.class)
public abstract class FallingBlockMixin {
	@Inject(
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleParameters;DDDDDD)V"
			),
			method = "randomDisplayTick",
			cancellable = true
	)
	private void addParticlesProperly(BlockState blockState, World world, BlockPos blockPos, Random random, CallbackInfo ci) {
		if (Config.getConfig().fixParticles && Config.getConfig().farLandsEnabled) {
			final double x = blockPos.getX() + random.nextDouble();
			final double y = blockPos.getY() - 0.05D;
			final double z = blockPos.getZ() + random.nextDouble();

			world.addParticle(new BlockStateParticleParameters(ParticleTypes.FALLING_DUST, blockState), x, y, z, 0.0D, 0.0D, 0.0D);

			ci.cancel();
		}
	}
}
