package site.geni.FarLands.mixins.common;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@SuppressWarnings("unused")
@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
	@ModifyConstant(constant = @Constant(intValue = 29999984), method = "getMaxWorldBorderRadius")
	private int setMaxRadius(int i) {
		return Integer.MAX_VALUE;
	}
}
