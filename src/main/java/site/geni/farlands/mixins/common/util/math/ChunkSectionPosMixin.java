package site.geni.farlands.mixins.common.util.math;

import net.minecraft.util.math.ChunkSectionPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("unused")
@Mixin(ChunkSectionPos.class)
public abstract class ChunkSectionPosMixin {

	/**
	 * Packs X/Y/Z into a single long <br>
	 * Returns X/Y/Z packed into one long
	 *
	 * @param cir {@link CallbackInfoReturnable} required by {@link Inject}
	 * @param x   X coordinate
	 * @param y   Y coordinate
	 * @param z   Z coordinate
	 * @author geni
	 */
	@Inject(
		at = @At(
			value = "HEAD"
		),
		method = "asLong(III)J",
		cancellable = true
	)
	private static void asLongFixed(int x, int y, int z, CallbackInfoReturnable<Long> cir) {
		cir.setReturnValue((((long) x & 0x7FFFFFL) << 41 | ((long) y & 0x3FFFFL) | ((long) z & 0x7FFFFFL) << 18));
	}


	/**
	 * Unpacks and returns X coordinate from long value returned by {@link #asLongFixed}
	 *
	 * @param cir    {@link CallbackInfoReturnable} required by {@link Inject}
	 * @param coords Coordinates in long form
	 * @author geni
	 * @see #asLongFixed
	 */
	@Inject(
		at = @At(
			value = "HEAD"
		),
		method = "unpackX",
		cancellable = true
	)
	private static void getXFixed(long coords, CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue((int) (coords >> 41));
	}

	/**
	 * Unpacks and returns Y coordinate from long value returned by {@link #asLongFixed}
	 *
	 * @param cir    {@link CallbackInfoReturnable} required by {@link Inject}
	 * @param coords Coordinates in long form
	 * @author geni
	 * @see #asLongFixed
	 */
	@Inject(
		at = @At(
			value = "HEAD"
		),
		method = "unpackY",
		cancellable = true
	)
	private static void getYFixed(long coords, CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue((int) (coords << 48 >> 48));
	}

	/**
	 * Unpacks and returns Z coordinate from long value returned by {@link #asLongFixed}
	 *
	 * @param cir    {@link CallbackInfoReturnable} required by {@link Inject}
	 * @param coords Coordinates in long form
	 * @author geni
	 * @see #asLongFixed
	 */
	@Inject(
		at = @At(
			value = "HEAD"
		),
		method = "unpackZ",
		cancellable = true
	)
	private static void getZFixed(long coords, CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue((int) (coords << 23 >> 41));
	}


	/**
	 * Coordinates with Y set to 0
	 *
	 * @param cir    {@link CallbackInfoReturnable} required by {@link Inject}
	 * @param coords Coordinates in long form
	 * @author geni
	 * @see #asLongFixed
	 */
	@Inject(
		at = @At(
			value = "HEAD"
		),
		method = "withZeroY",
		cancellable = true
	)
	private static void withZeroYFixed(long coords, CallbackInfoReturnable<Long> cir) {
		cir.setReturnValue(coords & -0x40000L);
	}

}
