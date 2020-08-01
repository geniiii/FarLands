package site.geni.farlands.mixins.server.entity;

import net.minecraft.entity.FallingBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import site.geni.farlands.FarLands;
import site.geni.farlands.mixins.common.entity.EntityMixin;
import site.geni.farlands.mixins.common.generators.NoiseChunkGeneratorAccessorMixin;

@SuppressWarnings("unused")
@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin extends EntityMixin {
	@Inject(
		at = @At(
			value = "HEAD"
		),
		method = "tick"
	)
	private void killFallingBlockEntities(CallbackInfo ci) {
		if (this.getServer() == null) {
			return;
		}

		// what the fuck
		final double xzScale = ((NoiseChunkGeneratorAccessorMixin) this.getServer().getSaveProperties().getGeneratorOptions().getChunkGenerator()).getSettings().get().getNoiseConfig().getSampling().getXZScale();
		final double farLandsLocation = Integer.MAX_VALUE / ((684.17D * xzScale) / 4);
		if (FarLands.getConfig().general.killFallingBlockEntitiesInFarLands.booleanValue() &&
			(Math.abs(this.getX()) <= farLandsLocation || Math.abs(this.getZ()) <= farLandsLocation)) {
			this.remove();
		}

	}
}
