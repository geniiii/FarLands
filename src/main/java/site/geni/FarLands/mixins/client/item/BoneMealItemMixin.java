package site.geni.FarLands.mixins.client.item;

import net.minecraft.block.BlockState;
import net.minecraft.item.BoneMealItem;
import net.minecraft.particle.ParticleParameters;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import site.geni.FarLands.utils.Config;


@SuppressWarnings("unused")
@Mixin(BoneMealItem.class)
public abstract class BoneMealItemMixin extends ItemMixin {
	private static final ThreadLocal<BlockPos> blockPosThreadLocal = new ThreadLocal<>();

	private static final ThreadLocal<Double> velocityXThreadLocal = new ThreadLocal<>();
	private static final ThreadLocal<Double> velocityYThreadLocal = new ThreadLocal<>();
	private static final ThreadLocal<Double> velocityZThreadLocal = new ThreadLocal<>();

	@Inject(
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/IWorld;addParticle(Lnet/minecraft/particle/ParticleParameters;DDDDDD)V",
					shift = At.Shift.BEFORE
			),
			method = "method_7721",
			locals = LocalCapture.CAPTURE_FAILHARD
	)
	private static void setThreadLocals(IWorld iWorld, BlockPos blockPos, int int_1, CallbackInfo ci, BlockState blockState, int forInt, double velocityX, double velocityY, double velocityZ) {
		if (Config.getConfig().fixParticles && Config.getConfig().farLandsEnabled) {
			blockPosThreadLocal.set(blockPos);

			velocityXThreadLocal.set(velocityX);
			velocityYThreadLocal.set(velocityY);
			velocityZThreadLocal.set(velocityZ);
		}
	}

	@Redirect(
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/IWorld;addParticle(Lnet/minecraft/particle/ParticleParameters;DDDDDD)V"
			),
			method = "method_7721"
	)
	private static void addParticlesProperly(IWorld iWorld, ParticleParameters var1, double var2, double var4, double var6, double var8, double var10, double var12) {
		if (Config.getConfig().fixParticles && Config.getConfig().farLandsEnabled) {
			final BlockPos blockPos = blockPosThreadLocal.get();

			final double x = blockPos.getX() + random.nextDouble();
			final double y = blockPos.getY() + random.nextDouble();
			final double z = blockPos.getZ() + random.nextDouble();

			iWorld.addParticle(ParticleTypes.HAPPY_VILLAGER, x, y, z, velocityXThreadLocal.get(), velocityYThreadLocal.get(), velocityZThreadLocal.get());
		} else {
			iWorld.addParticle(ParticleTypes.HAPPY_VILLAGER, var2, var4, var6, var8, var10, var12);
		}
	}

	@Inject(
			at = @At(
					value = "RETURN"
			),
			method = "method_7721"
	)
	private static void removeThreadLocals(IWorld iWorld_1, BlockPos blockPos_1, int int_1, CallbackInfo ci) {
		if (Config.getConfig().fixParticles && Config.getConfig().farLandsEnabled) {
			blockPosThreadLocal.remove();

			velocityXThreadLocal.remove();
			velocityYThreadLocal.remove();
			velocityZThreadLocal.remove();
		}
	}
}
