package site.geni.farlands.mixins.common.world;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@SuppressWarnings("unused")
@Mixin(WorldView.class)
public interface WorldViewMixin extends BlockRenderView {
	/**
	 * Make anything down/up to X/Z: {@link Integer#MIN_VALUE}/{@link Integer#MAX_VALUE} a valid position
	 *
	 * @author geni
	 */
	@Overwrite
	default int getLightLevel(BlockPos pos, int ambientDarkness) {
		return this.getBaseLightLevel(pos, ambientDarkness);
	}
}
