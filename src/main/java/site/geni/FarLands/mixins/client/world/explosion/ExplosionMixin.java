package site.geni.FarLands.mixins.client.world.explosion;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import site.geni.FarLands.FarLands;

@SuppressWarnings("unused")
@Mixin(Explosion.class)
public abstract class ExplosionMixin {
	private static BlockPos blockPos;
	@Shadow
	@Final
	private World world;

	/**
	 * Sets {@link #blockPos} to the block which triggered the explosion's {@link BlockPos}
	 *
	 * @param orig {@link BlockPos} of the block which triggered the explosion
	 * @return {@code orig}
	 * @author geni
	 */
	@ModifyVariable(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/World;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;",
			shift = At.Shift.BEFORE
		),
		method = "affectWorld"
	)
	private BlockPos setBlockPos(BlockPos orig) {
		if (FarLands.getConfig().fixParticlesEntities.getValue()) {
			blockPos = orig;
		}

		return orig;
	}

	/**
	 * Sets the X position of the explosion particles using {@link Double} instead of {@link Float} (casted to {@link Double}) in order to have precise spawn positions
	 *
	 * @param original Original X position of the explosion particle
	 * @return Depending on the configuration of the mod, either proper position or original position
	 * @author geni
	 */
	@ModifyVariable(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/util/math/BlockPos;getY()I",
			shift = At.Shift.BEFORE
		),
		index = 7,
		method = "affectWorld"
	)
	private double setX(double original) {
		return FarLands.getConfig().fixParticlesEntities.getValue() ? blockPos.getX() + (double) this.world.random.nextFloat() : original;
	}

	/**
	 * Sets the Y position of the explosion particles using {@link Double} instead of {@link Float} (casted to {@link Double}) in order to have precise spawn positions
	 *
	 * @param original Original Y position of the explosion particle
	 * @return Depending on the configuration of the mod, either proper position or original position
	 * @author geni
	 */
	@ModifyVariable(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/util/math/BlockPos;getZ()I",
			shift = At.Shift.BEFORE
		),
		index = 9,
		method = "affectWorld"
	)
	private double setY(double original) {
		return FarLands.getConfig().fixParticlesEntities.getValue() ? blockPos.getY() + (double) this.world.random.nextFloat() : original;
	}

	/**
	 * Sets the Z position of the explosion particles using {@link Double} instead of {@link Float} (casted to {@link Double}) in order to have precise spawn positions
	 *
	 * @param original Original Z position of the explosion particle
	 * @return Depending on the configuration of the mod, either proper position or original position
	 * @author geni
	 */
	@ModifyVariable(
		at = @At(
			value = "FIELD",
			opcode = Opcodes.GETFIELD,
			target = "Lnet/minecraft/world/explosion/Explosion;x:D",
			ordinal = 3,
			shift = At.Shift.BEFORE
		),
		index = 11,
		method = "affectWorld"
	)
	private double setZ(double original) {
		return FarLands.getConfig().fixParticlesEntities.getValue() ? blockPos.getZ() + (double) this.world.random.nextFloat() : original;
	}
}
