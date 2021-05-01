package site.geni.farlands.mixins.client.item;

import net.minecraft.item.BoneMealItem;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import site.geni.farlands.FarLands;


@SuppressWarnings("unused")
@Mixin(BoneMealItem.class)
public abstract class BoneMealItemMixin extends ItemMixin {
	private static BlockPos pos;

	/**
	 * Sets {@link #pos} to the block the item is being used on's {@link BlockPos} <br>
	 *
	 * @param blockPos   {@link BlockPos} of the block
	 * @param ci         {@link CallbackInfo} required for {@link Inject}
	 * @author geni
	 */
	@Inject(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/WorldAccess;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V",
			shift = At.Shift.BEFORE
		),
		method = "createParticles"
	)
	private static void setVariables(WorldAccess world, BlockPos blockPos, int count, CallbackInfo ci) {
		if (FarLands.getConfig().fixParticlesEntities.getValue()) {
			pos = blockPos;
		}
	}

	/**
	 * Adds particles created by bone meal using {@link Double} for positions instead of {@link Float} in order to have precise particle positions
	 *
	 * @param worldAccess         {@link WorldAccess} of the item
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
			target = "Lnet/minecraft/world/WorldAccess;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"
		),
		method = "createParticles"
	)
	private static void addParticlesProperly(WorldAccess worldAccess, ParticleEffect ParticleEffect, double xOrig, double yOrig, double zOrig, double velocityX, double velocityY, double velocityZ) {
		if (FarLands.getConfig().fixParticlesEntities.getValue()) {
			final double x = pos.getX() + RANDOM.nextDouble();
			final double y = pos.getY() + RANDOM.nextDouble();
			final double z = pos.getZ() + RANDOM.nextDouble();

			worldAccess.addParticle(ParticleTypes.HAPPY_VILLAGER, x, y, z, velocityX, velocityY, velocityZ);
		} else {
			worldAccess.addParticle(ParticleTypes.HAPPY_VILLAGER, xOrig, yOrig, zOrig, velocityX, velocityY, velocityZ);
		}
	}
}
