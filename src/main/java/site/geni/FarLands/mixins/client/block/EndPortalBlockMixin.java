package site.geni.FarLands.mixins.client.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalBlock;
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
@Mixin(EndPortalBlock.class)
public abstract class EndPortalBlockMixin {
	/**
	 * Adds particles created by mob spawners using {@link Double} for positions instead of {@link Float} in order to have precise particle positions
	 *
	 * @param ci         {@link CallbackInfo} required for {@link Inject}
	 * @param blockState {@link BlockState} of the block
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
	private void addParticlesProperly(BlockState blockState, World world, BlockPos blockPos, Random random, CallbackInfo ci) {
		if (FarLands.getConfig().fixParticlesEntities) {
			final double x = blockPos.getX() + (double) random.nextFloat();
			final double y = blockPos.getY() + 0.08D;
			final double z = blockPos.getZ() + (double) random.nextFloat();

			world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);

			ci.cancel();
		}
	}
}
