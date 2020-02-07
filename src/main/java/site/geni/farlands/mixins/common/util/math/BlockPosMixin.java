package site.geni.farlands.mixins.common.util.math;

import me.zeroeightsix.fiber.exception.FiberException;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import site.geni.farlands.config.Config;

@SuppressWarnings("unused")
@Mixin(BlockPos.class)
public abstract class BlockPosMixin {
	@Shadow
	@Final
	@Mutable
	private static int SIZE_BITS_X;

	@Shadow
	@Final
	@Mutable
	private static int SIZE_BITS_Y;

	@Shadow
	@Final
	@Mutable
	private static int SIZE_BITS_Z;

	@Shadow
	@Final
	@Mutable
	private static int BIT_SHIFT_X;

	@Shadow
	@Final
	@Mutable
	private static int BIT_SHIFT_Z;

	@Shadow
	@Final
	@Mutable
	private static long BITS_X;

	@Shadow
	@Final
	@Mutable
	private static long BITS_Y;

	@Shadow
	@Final
	@Mutable
	private static long BITS_Z;

	/**
	 * Overwrites bit masks, bit shifts and amount of bits of each coordinate depending on the mod's configuration
	 *
	 * @param ci {@link CallbackInfo} required for {@link Inject}
	 * @author geni
	 */
	@SuppressWarnings("UnresolvedMixinReference")
	@Inject(
		at = @At(
			"RETURN"
		),
		method = "<clinit>"
	)
	private static void overwriteBits(CallbackInfo ci) throws FiberException {
		if (new Config().load().fixLighting.getValue()) {
			SIZE_BITS_X = 27;
			SIZE_BITS_Z = SIZE_BITS_X; // 27
			SIZE_BITS_Y = 64 - SIZE_BITS_X - SIZE_BITS_Z; // 10
			BITS_X = (1L << SIZE_BITS_X) - 1L; // 0x7FFFFFF
			BITS_Y = (1L << SIZE_BITS_Y) - 1L; // 0x3FF
			BITS_Z = (1L << SIZE_BITS_Z) - 1L; // 0x7FFFFFF
			BIT_SHIFT_Z = SIZE_BITS_Y; // 10
			BIT_SHIFT_X = SIZE_BITS_Y + SIZE_BITS_Z; // 37
		}
	}
}
