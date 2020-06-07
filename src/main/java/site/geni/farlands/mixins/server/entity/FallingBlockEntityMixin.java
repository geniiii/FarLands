package site.geni.farlands.mixins.server.entity;

import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.SurfaceChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import site.geni.farlands.FarLands;
import site.geni.farlands.mixins.common.entity.EntityMixin;
import site.geni.farlands.mixins.common.generators.SurfaceChunkGeneratorAccessorMixin;

@SuppressWarnings("unused")
@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin extends EntityMixin {
	/**
	 * Kills falling block entities located in the Far Lands depending on the mod's configuration
	 *
	 * @param ci {@link CallbackInfo} required by {@link Inject}
	 * @author geni
	 */
	@Inject(
		at = @At(
			value = "HEAD"
		),
		method = "tick"
	)
	private void killFallingBlockEntities(CallbackInfo ci) {
		if (FarLands.getConfig().general.farLandsEnabled) {
			if (!(this.world instanceof ServerWorld)) return;
			final ChunkGenerator chunkGenerator = ((ServerWorld) this.world).getChunkManager().getChunkGenerator();
			if (!(chunkGenerator instanceof SurfaceChunkGenerator)) return;
			final double xzScale = 684.412D * ((SurfaceChunkGeneratorAccessorMixin) chunkGenerator).getChunkGeneratorType().method_28559().method_28583().method_28576();
			final long farLandsLocation = (long) (Long.MAX_VALUE / (xzScale / 4));

			if (this.getX() >= farLandsLocation
				|| this.getX() <= -farLandsLocation
				|| this.getZ() >= farLandsLocation
				|| this.getZ() <= -farLandsLocation) {
				this.remove();
			}
		}
	}
}
