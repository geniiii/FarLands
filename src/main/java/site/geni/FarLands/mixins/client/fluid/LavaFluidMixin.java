package site.geni.FarLands.mixins.client.fluid;

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
import site.geni.FarLands.utils.Config;

import java.util.Random;

@SuppressWarnings("unused")
@Mixin(LavaFluid.class)
public abstract class LavaFluidMixin {
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

			double x = blockPos.getX() + random.nextDouble();
			double y = blockPos.getY() + 1;
			double z = blockPos.getZ() + random.nextDouble();

			world.addParticle(ParticleTypes.LAVA, x, y, z, 0.0D, 0.0D, 0.0D);
			world.playSound(x, y, z, SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCK, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);

			ci.cancel();
		}
	}
}
