package site.geni.FarLands.mixins.common;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@SuppressWarnings("unused")
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
	@ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;clamp(DDD)D", ordinal = 0), method = "update", index = 1)
	private double clampToNegativeDoubleMaxValue(double original) {
		return -Double.MAX_VALUE;
	}

	@ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;clamp(DDD)D", ordinal = 0), method = "update", index = 2)
	private double clampToPositiveDoubleMaxValue(double original) {
		return Double.MAX_VALUE;
	}
}
