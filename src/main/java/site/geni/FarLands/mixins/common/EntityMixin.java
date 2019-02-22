package site.geni.FarLands.mixins.common;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@SuppressWarnings("unused")
@Mixin(Entity.class)
public abstract class EntityMixin {
	@ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;clamp(DDD)D", ordinal = 0), method = "setPositionAnglesAndUpdate(DDDFF)V", index = 1)
	private double clampToNegativeDoubleMaxValueX(double original) {
		return -Double.MAX_VALUE;
	}

	@ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;clamp(DDD)D", ordinal = 0), method = "setPositionAnglesAndUpdate(DDDFF)V", index = 2)
	private double clampToPositiveDoubleMaxValueX(double original) {
		return Double.MAX_VALUE;
	}

	@ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;clamp(DDD)D", ordinal = 1), method = "setPositionAnglesAndUpdate(DDDFF)V", index = 1)
	private double clampToNegativeDoubleMaxValueZ(double original) {
		return -Double.MAX_VALUE;
	}

	@ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;clamp(DDD)D", ordinal = 1), method = "setPositionAnglesAndUpdate(DDDFF)V", index = 2)
	private double clampToPositiveDoubleMaxValueZ(double original) {
		return Double.MAX_VALUE;
	}
}
