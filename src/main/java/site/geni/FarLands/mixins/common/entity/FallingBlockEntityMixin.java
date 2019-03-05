package site.geni.FarLands.mixins.common.entity;

import net.minecraft.entity.FallingBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import site.geni.FarLands.utils.Config;

@SuppressWarnings("unused")
@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin extends EntityMixin {
	private final static double farLandsLocation = Integer.MAX_VALUE / ((Config.getConfig().coordinateScale * Config.getConfig().coordinateScaleMultiplier) / 4);

	@Inject(
			at = @At(
					value = "RETURN"
			),
			method = "update"
	)
	private void killFallingBlockEntities(CallbackInfo ci) {
		if (Config.getConfig().killFallingBlockEntitiesInFarLands && this.x >= farLandsLocation || this.x <= -farLandsLocation || this.z >= farLandsLocation || this.z <= -farLandsLocation) {
			this.kill();
		}
	}
}
