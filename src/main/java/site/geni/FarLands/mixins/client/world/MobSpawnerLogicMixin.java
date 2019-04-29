package site.geni.FarLands.mixins.client.world;

import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.MobSpawnerLogic;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import site.geni.FarLands.FarLands;
import site.geni.FarLands.config.Config;

@SuppressWarnings("unused")
@Mixin(MobSpawnerLogic.class)
public abstract class MobSpawnerLogicMixin {
	@Shadow
	private double field_9161;

	@Shadow
	private double field_9159;

	@Shadow
	private int spawnDelay = 20;

	@Shadow
	public abstract BlockPos getPos();

	@Shadow
	public abstract World getWorld();


	/**
	 * If {@link Config#fixParticlesEntities} is true, adds particles created by mob spawners using {@link Double} for positions <br>
	 * instead of {@link Float} in order to have precise particle positions
	 *
	 * @param ci {@link CallbackInfo} required for {@link Inject}
	 * @author geni
	 */
	@Inject(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleParameters;DDDDDD)V",
			ordinal = 0
		),
		method = "update",
		cancellable = true
	)
	private void addParticlesProperly(CallbackInfo ci) {
		if (FarLands.getConfig().fixParticlesEntities) {
			final BlockPos blockPos = this.getPos();
			final World world = this.getWorld();

			final double x = blockPos.getX() + (double) world.random.nextFloat();
			final double y = blockPos.getY() + (double) world.random.nextFloat();
			final double z = blockPos.getZ() + (double) world.random.nextFloat();

			world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
			world.addParticle(ParticleTypes.FLAME, x, y, z, 0.0D, 0.0D, 0.0D);

			if (this.spawnDelay > 0) {
				--this.spawnDelay;
			}

			this.field_9159 = this.field_9161;
			this.field_9161 = (this.field_9161 + (1000.0F / (this.spawnDelay + 200.0F))) % 360.0D;

			ci.cancel();
		}
	}
}
