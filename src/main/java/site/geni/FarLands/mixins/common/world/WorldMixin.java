package site.geni.FarLands.mixins.common.world;

import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.ChunkManager;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.level.LevelProperties;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiFunction;

@SuppressWarnings("unused")
@Mixin(World.class)
public abstract class WorldMixin {
	@Shadow
	@Final
	public Dimension dimension;

	@Shadow
	@Final
	private WorldBorder border;

	@ModifyConstant(constant = @Constant(intValue = -30000000), method = "isValid(Lnet/minecraft/util/math/BlockPos;)Z")
	private static int validUpToNegativeIntegerMaxValueXZ(int original) {
		return Integer.MIN_VALUE;
	}

	@ModifyConstant(constant = @Constant(intValue = 30000000), method = "isValid(Lnet/minecraft/util/math/BlockPos;)Z")
	private static int validUpToPositiveIntegerMaxValueXZ(int original) {
		return Integer.MAX_VALUE;
	}

	@ModifyConstant(constant = @Constant(intValue = -30000000), method = "getLightLevel(Lnet/minecraft/util/math/BlockPos;I)I")
	private static int getLightLevelUpToNegativeIntegerMaxValueXZ(int original) {
		return Integer.MIN_VALUE;
	}

	@ModifyConstant(constant = @Constant(intValue = 30000000), method = "getLightLevel(Lnet/minecraft/util/math/BlockPos;I)I")
	private static int getLightLevelUpToPositiveIntegerMaxValueXZ(int original) {
		return Integer.MAX_VALUE;
	}

	@ModifyConstant(constant = @Constant(intValue = -30000000), method = "getTop")
	private static int getTopUpToNegativeIntegerMaxValueXZ(int original) {
		return Integer.MIN_VALUE;
	}

	@ModifyConstant(constant = @Constant(intValue = 30000000), method = "getTop")
	private static int getTopUpToPositiveIntegerMaxValueXZ(int original) {
		return Integer.MAX_VALUE;
	}

	@Shadow
	public abstract WorldBorder getWorldBorder();

	@Shadow
	public abstract World getWorld();

	@Shadow
	public abstract Dimension getDimension();

	@Shadow
	public abstract LevelProperties getLevelProperties();

	@Inject(at = @At(value = "RETURN"), method = "<init>(Lnet/minecraft/world/level/LevelProperties;Lnet/minecraft/world/dimension/DimensionType;Ljava/util/function/BiFunction;Lnet/minecraft/util/profiler/Profiler;Z)V")
	private void setSizeAndMaxRadius(LevelProperties levelProperties_1, DimensionType dimensionType_1, BiFunction<World, Dimension, ChunkManager> biFunction_1, Profiler profiler_1, boolean boolean_1, CallbackInfo ci) {
		this.getWorldBorder().setMaxWorldBorderRadius(Integer.MAX_VALUE);
		this.getWorldBorder().setSize(Double.MAX_VALUE);
		this.getLevelProperties().setBorderSize(Double.MAX_VALUE);
	}
}
