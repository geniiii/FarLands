package site.geni.FarLands.mixins.client.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.MyceliumBlock;
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
@Mixin(MyceliumBlock.class)
public abstract class MyceliumBlockMixin {
	@Inject(
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleParameters;DDDDDD)V"
			),
			method = "randomDisplayTick"
	)
	private void addParticlesProperly(BlockState blockState, World world, BlockPos blockPos, Random random, CallbackInfo ci) {
		if (Config.getConfig().fixParticles && Config.getConfig().farLandsEnabled) {
			world.addParticle(ParticleTypes.MYCELIUM, blockPos.getX() + random.nextDouble(), blockPos.getY() + 1.1D, blockPos.getZ() + random.nextDouble(), 0.0D, 0.0D, 0.0D);

			ci.cancel();
		}
	}
}
