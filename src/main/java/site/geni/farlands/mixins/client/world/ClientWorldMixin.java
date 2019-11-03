package site.geni.farlands.mixins.client.world;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import site.geni.farlands.FarLands;
import site.geni.farlands.mixins.common.world.WorldMixin;

import java.util.Random;

@SuppressWarnings("unused")
@Mixin(ClientWorld.class)
public abstract class ClientWorldMixin extends WorldMixin {
	private static int x;
	private static int y;
	private static int z;

	@Shadow
	public abstract void addParticle(ParticleEffect ParticleEffect, double x, double y, double z, double velocityX, double velocityY, double velocityZ);

	/**
	 * Adds particles created by barrier blocks using {@link Double} for positions instead of {@link Float} in order to have precise particle positions
	 *
	 * @param x                     X position of the block
	 * @param y                     Y position of the block
	 * @param z                     Z position of the block
	 * @param maxOffset             Maximum offset of the particle's positions (?)
	 * @param random                {@link Random} instance passed to the method
	 * @param ifHoldingBarrierBlock Whether the player is holding a barrier block in their main hand or not
	 * @param blockPos              {@link BlockPos} later used for getting the block from the previously passed coordinates
	 * @param ci                    {@link CallbackInfo} required by {@link Inject}
	 * @param xRandom               X + a random value
	 * @param yRandom               Y + a random value
	 * @param zRandom               Z + a random value
	 * @author geni
	 */
	@Inject(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/util/math/BlockPos$Mutable;set(III)Lnet/minecraft/util/math/BlockPos$Mutable;"
		),
		method = "randomBlockDisplayTick",
		locals = LocalCapture.CAPTURE_FAILHARD
	)
	private void setXYZ(int x, int y, int z, int maxOffset, Random random, boolean ifHoldingBarrierBlock, BlockPos.Mutable blockPos, CallbackInfo ci, int xRandom, int yRandom, int zRandom) {
		if (FarLands.getConfig().fixParticlesEntities.getValue()) {
			ClientWorldMixin.x = xRandom;
			ClientWorldMixin.y = yRandom;
			ClientWorldMixin.z = zRandom;
		}
	}

	@Redirect(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/world/ClientWorld;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"
		),
		method = "randomBlockDisplayTick"
	)
	private void addParticlesProperly(ClientWorld clientWorld, ParticleEffect ParticleEffect, double xOrig, double yOrig, double zOrig, double velocityX, double velocityZ, double velocityY) {
		if (FarLands.getConfig().fixParticlesEntities.getValue()) {
			this.addParticle(ParticleTypes.BARRIER, x + 0.5D, y + 0.5D, z + 0.5D, velocityX, velocityY, velocityZ);
		} else {
			this.addParticle(ParticleTypes.BARRIER, xOrig, yOrig, zOrig, velocityX, velocityY, velocityZ);
		}
	}
}
