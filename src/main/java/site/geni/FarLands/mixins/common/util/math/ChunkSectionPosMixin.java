package site.geni.FarLands.mixins.common.util.math;

import net.minecraft.util.math.ChunkSectionPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import site.geni.FarLands.utils.Config;

@SuppressWarnings("unused")
@Mixin(ChunkSectionPos.class)
public abstract class ChunkSectionPosMixin {

	/**
	 * Converts X/Y/Z from integer form to a single long <br>
	 * Returns X/Y/Z combined in one long
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
		if (Config.getConfig().fixLighting) {
			cir.setReturnValue((((long) x & 0x7FFFFFL) << 41 | ((long) y & 0x3FFFFL) | ((long) z & 0x7FFFFFL) << 18));
		}
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
		method = "unpackLongX",
		cancellable = true
	)
	private static void unpackLongXFixed(long coords, CallbackInfoReturnable<Integer> cir) {
		if (Config.getConfig().fixLighting) {
			cir.setReturnValue((int) (coords >> 41));
		}
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
		method = "unpackLongY",
		cancellable = true
	)
	private static void unpackLongYFixed(long coords, CallbackInfoReturnable<Integer> cir) {
		if (Config.getConfig().fixLighting) {
			cir.setReturnValue((int) (coords << 48 >> 48));
		}
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
		method = "unpackLongZ",
		cancellable = true
	)
	private static void unpackLongZFixed(long coords, CallbackInfoReturnable<Integer> cir) {
		if (Config.getConfig().fixLighting) {
			cir.setReturnValue((int) (coords << 23 >> 41));
		}
	}


	/**
	 * Returns given long value returned by {@link #asLongFixed} with its Y coordinate set to 0
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
		method = "method_18693",
		cancellable = true
	)
	private static void setYToZeroFixed(long coords, CallbackInfoReturnable<Long> cir) {
		if (Config.getConfig().fixLighting) {
			cir.setReturnValue(coords & -0x40000L);
		}
	}

}
