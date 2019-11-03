package site.geni.farlands.mixins.client.item;

import net.minecraft.block.BlockState;
import net.minecraft.item.BoneMealItem;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import site.geni.farlands.FarLands;


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
			target = "Lnet/minecraft/world/IWorld;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V",
			shift = At.Shift.BEFORE
		),
		method = "createParticles",
		locals = LocalCapture.CAPTURE_FAILHARD
	)
	private static void setVariables(IWorld iWorld, BlockPos blockPos, int unknown, CallbackInfo ci, BlockState blockState, int forInt, double velocityX, double velocityY, double velocityZ) {
		if (FarLands.getConfig().fixParticlesEntities.getValue()) {
			pos = blockPos;
		}
	}

	/**
	 * Adds particles created by bone meal using {@link Double} for positions instead of {@link Float} in order to have precise particle positions
	 *
	 * @param iWorld         {@link IWorld} of the item
	 * @param ParticleEffect {@link ParticleEffect} to use when adding particle
	 * @param xOrig          The particle's original X position
	 * @param yOrig          The particle's original Y position
	 * @param zOrig          The particle's original Z position
	 * @param velocityX      The particle's X velocity
	 * @param velocityY      The particle's Y velocity
	 * @param velocityZ      The particle's Z velocity
	 * @author geni
	 */
	@Redirect(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/IWorld;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"
		),
		method = "createParticles"
	)
	private static void addParticlesProperly(IWorld iWorld, ParticleEffect ParticleEffect, double xOrig, double yOrig, double zOrig, double velocityX, double velocityY, double velocityZ) {
		if (FarLands.getConfig().fixParticlesEntities.getValue()) {
			final double x = pos.getX() + RANDOM.nextDouble();
			final double y = pos.getY() + RANDOM.nextDouble();
			final double z = pos.getZ() + RANDOM.nextDouble();

			iWorld.addParticle(ParticleTypes.HAPPY_VILLAGER, x, y, z, velocityX, velocityY, velocityZ);
		} else {
			iWorld.addParticle(ParticleTypes.HAPPY_VILLAGER, xOrig, yOrig, zOrig, velocityX, velocityY, velocityZ);
		}
	}
}
