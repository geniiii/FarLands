package site.geni.FarLands.mixins.client.block.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(BlockEntity.class)
public abstract class BlockEntityMixin {
	/**
	 * The {@link BlockEntity}'s {@link BlockPos} <br>
	 * Used by {@link EnchantingTableBlockEntityMixin}
	 *
	 * @see EnchantingTableBlockEntityMixin#setX
	 * @see EnchantingTableBlockEntityMixin#setZ
	 */
	@Shadow
	protected BlockPos pos;
}
