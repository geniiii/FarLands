package site.geni.FarLands.mixins.client.block.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

@SuppressWarnings("unused")
@Mixin(BlockEntity.class)
public interface BlockEntityMixin {
	/**
	 * Gets the {@link BlockEntity}'s {@link BlockPos} <br>
	 * Used by {@link EnchantingTableBlockEntityMixin}
	 *
	 * @see EnchantingTableBlockEntityMixin#setX
	 * @see EnchantingTableBlockEntityMixin#setZ
	 * @return {@link BlockEntity}'s {@link BlockPos}
	 */
	@Accessor
	BlockPos getPos();
}
