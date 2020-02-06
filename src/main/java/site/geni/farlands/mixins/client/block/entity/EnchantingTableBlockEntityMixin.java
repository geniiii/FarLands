package site.geni.farlands.mixins.client.block.entity;

import org.objectweb.asm.Opcodes;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import site.geni.farlands.FarLands;

@SuppressWarnings("unused")
@Mixin(EnchantingTableBlockEntity.class)
abstract class EnchantingTableBlockEntityMixin {
	private static PlayerEntity playerEntity;

	/**
	 * Sets {@code playerEntity} to the {@link PlayerEntity} close to the {@link EnchantingTableBlockEntity} <br>
	 * Finds the closest player using {@link Double} for positions instead of {@link Float} in order to have it detect the player more precisely
	 *
	 * @param original Original {@link PlayerEntity}
	 * @return Depending on the mod's configuration, either {@code original} or a more precisely located player
	 * @author geni
	 * @see EnchantingTableBlockEntity#tick
	 */
	@ModifyVariable(
		at = @At(
			value = "JUMP",
			opcode = Opcodes.IFNULL,
			ordinal = 0,
			shift = At.Shift.BEFORE
		),
		method = "tick"
	)
	private PlayerEntity setPlayerEntity(PlayerEntity original) {
		if (FarLands.getConfig().fixParticlesEntities.getValue()) {
			final BlockPos pos = ((BlockEntityMixin) this).getPos();

			original = (((BlockEntityMixin) this).getWorld().getClosestPlayer(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 3.0D, false));
			playerEntity = original;
		}

		return original;
	}

	/**
	 * Gets X distance to player using {@link Double} instead of {@link Float} (casted to {@link Double}) in order to have precise render positions
	 *
	 * @param original Original X distance to player
	 * @return Depending on the configuration of the mod, either proper position or original position
	 * @author geni
	 */
	@ModifyVariable(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/util/math/BlockPos;getZ()I",
			ordinal = 1
		),
		method = "tick"
	)
	private double setX(double original) {
		if (FarLands.getConfig().fixParticlesEntities.getValue()) {
			return playerEntity.getX() - (((BlockEntityMixin) this).getPos().getX() + 0.5D);
		}

		return original;
	}

	/**
	 * Gets Z distance to player using {@link Double} instead of {@link Float} (casted to {@link Double}) in order to have precise render positions
	 *
	 * @param original Original Z distance to player
	 * @return Depending on the configuration of the mod, either proper position or original position
	 * @author geni
	 */
	@ModifyVariable(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/util/math/MathHelper;atan2(DD)D"
		),
		ordinal = 1,
		method = "tick"
	)
	private double setZ(double original) {
		if (FarLands.getConfig().fixParticlesEntities.getValue()) {
			return playerEntity.getZ() - (((BlockEntityMixin) this).getPos().getZ() + 0.5D);
		}

		return original;
	}
}
