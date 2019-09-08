package site.geni.farlands.mixins.client.block.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@SuppressWarnings("unused")
@Mixin(BlockEntity.class)
public interface BlockEntityMixin {
	/**
	 * Gets the {@link BlockEntity}'s {@link BlockPos} <br>
	 * Used by {@link EnchantingTableBlockEntityMixin}
	 *
	 * @return {@link BlockEntity}'s {@link BlockPos}
	 * @see EnchantingTableBlockEntityMixin#setX
	 * @see EnchantingTableBlockEntityMixin#setZ
	 */
	@Accessor
	BlockPos getPos();

	/**
	 * Gets the {@link BlockEntity}'s {@link World} <br>
	 * Used by {@link EnchantingTableBlockEntityMixin}
	 *
	 * @return {@link BlockEntity}'s {@link World}
	 * @see EnchantingTableBlockEntityMixin#setPlayerEntity
	 */
	@Accessor
	World getWorld();
}
