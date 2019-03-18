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
	private static BlockPos pos;

	/**
	 * Sets {@link #pos} to the block the item is being used on's {@link BlockPos} <br>
	 *
	 * @param iWorld     {@link IWorld} of the item
	 * @param blockPos   {@link BlockPos} of the block
	 * @param unknown    Unknown
	 * @param ci         {@link CallbackInfo} required for {@link Inject}
	 * @param blockState {@link BlockState} of the block
	 * @param forInt     Integer used in a {@code for} loop
	 * @param velocityX  The particle's X velocity
	 * @param velocityY  The particle's Y velocity
	 * @param velocityZ  The particle's Z velocity
	 * @author geni
	 */
	@Inject(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/IWorld;addParticle(Lnet/minecraft/particle/ParticleParameters;DDDDDD)V",
			shift = At.Shift.BEFORE
		),
		method = "method_7721",
		locals = LocalCapture.CAPTURE_FAILHARD
	)
	private static void setVariables(IWorld iWorld, BlockPos blockPos, int unknown, CallbackInfo ci, BlockState blockState, int forInt, double velocityX, double velocityY, double velocityZ) {
		if (Config.getConfig().fixParticles) {
			pos = blockPos;
		}
	}

	/**
	 * Adds particles created by bone meal using {@link Double} for positions instead of {@link Float} in order to have precise particle positions
	 *
	 * @param iWorld             {@link IWorld} of the item
	 * @param particleParameters {@link ParticleParameters} to use when adding particle
	 * @param xOrig              The particle's original X position
	 * @param yOrig              The particle's original Y position
	 * @param zOrig              The particle's original Z position
	 * @param velocityX          The particle's X velocity
	 * @param velocityY          The particle's Y velocity
	 * @param velocityZ          The particle's Z velocity
	 * @author geni
	 */
	@Redirect(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/IWorld;addParticle(Lnet/minecraft/particle/ParticleParameters;DDDDDD)V"
		),
		method = "method_7721"
	)
	private static void addParticlesProperly(IWorld iWorld, ParticleParameters particleParameters, double xOrig, double yOrig, double zOrig, double velocityX, double velocityY, double velocityZ) {
		if (Config.getConfig().fixParticles) {
			final double x = pos.getX() + random.nextDouble();
			final double y = pos.getY() + random.nextDouble();
			final double z = pos.getZ() + random.nextDouble();

			iWorld.addParticle(ParticleTypes.HAPPY_VILLAGER, x, y, z, velocityX, velocityY, velocityZ);
		} else {
			iWorld.addParticle(ParticleTypes.HAPPY_VILLAGER, xOrig, yOrig, zOrig, velocityX, velocityY, velocityZ);
		}
	}
}
