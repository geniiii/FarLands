package site.geni.farlands.mixins.common.entity;

import net.minecraft.entity.FallingBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import site.geni.farlands.FarLands;
import site.geni.farlands.util.Location;

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
		if (FarLands.getConfig().killFallingBlockEntitiesInFarLands.getValue() &&
			(this.getX() >= Location.FAR_LANDS.getValue()
				|| this.getX() <= -Location.FAR_LANDS.getValue()
				|| this.getZ() >= Location.FAR_LANDS.getValue()
				|| this.getZ() <= -Location.FAR_LANDS.getValue())) {
			this.remove();
		}
	}
}
