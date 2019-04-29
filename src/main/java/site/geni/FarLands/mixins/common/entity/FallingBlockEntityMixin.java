package site.geni.FarLands.mixins.common.entity;

import net.minecraft.entity.FallingBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import site.geni.FarLands.FarLands;

@SuppressWarnings("unused")
@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin extends EntityMixin {
	/**
	 * The Far Lands' (estimated, not exact!) positive X/Z position
	 */
	private final static double farLandsLocation = Integer.MAX_VALUE / ((FarLands.getConfig().coordinateScale * FarLands.getConfig().coordinateScaleMultiplier) / 4);

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
		if (FarLands.getConfig().killFallingBlockEntitiesInFarLands && (this.x >= farLandsLocation || this.x <= -farLandsLocation || this.z >= farLandsLocation || this.z <= -farLandsLocation)) {
			this.remove();
		}
	}
}
