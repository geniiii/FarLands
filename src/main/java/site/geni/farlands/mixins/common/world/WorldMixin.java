package site.geni.farlands.mixins.common.world;

import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.World;
import net.minecraft.world.WorldProperties;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@SuppressWarnings("unused")
@Mixin(World.class)
public abstract class WorldMixin {
	/**
	 * Make anything down to X/Z: {@link Integer#MIN_VALUE} a valid position
	 *
	 * @param original Original integer of -30000000
	 * @return {@link Integer#MIN_VALUE}
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			intValue = -30000000
		),
		method = "isValid"
	)
	private static int validUpToNegativeIntegerMaxValueXZ(int original) {
		return Integer.MIN_VALUE;
	}

	/**
	 * Make anything up to X/Z: {@link Integer#MAX_VALUE} a valid position
	 *
	 * @param original Original integer value of 30000000
	 * @return {@link Integer#MAX_VALUE}
	 * @author geni
	 */
	@ModifyConstant(
		constant = @Constant(
			intValue = 30000000
		),
		method = "isValid"
	)
	private static int validUpToPositiveIntegerMaxValueXZ(int original) {
		return Integer.MAX_VALUE;
	}

	@Shadow
	public abstract WorldBorder getWorldBorder();

	@Shadow public abstract WorldProperties getLevelProperties();

	/**
	 * Sets world border maximum radius to {@link Integer#MAX_VALUE} and size to {@link Double#MAX_VALUE}
	 *
	 * @author geni
	 */
	@Inject(
		at = @At(
			value = "RETURN"
		),
		method = "<init>"
	)
	private void setSizeAndMaxRadius(MutableWorldProperties mutableWorldProperties, RegistryKey<World> registryKey, RegistryKey<DimensionType> registryKey2, DimensionType dimensionType, Supplier<Profiler> profiler, boolean bl, boolean bl2, long l, CallbackInfo ci) {
		this.getWorldBorder().setMaxWorldBorderRadius(Integer.MAX_VALUE);
		this.getWorldBorder().setSize(Double.MAX_VALUE);

		this.getLevelProperties().getWorld
	}
}
