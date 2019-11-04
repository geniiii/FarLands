package site.geni.farlands.mixins.client.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.EndGatewayBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import site.geni.farlands.FarLands;

import java.util.Random;

@Mixin(EndGatewayBlock.class)
public abstract class EndGatewayBlockMixin {
	private static BlockPos blockPos;
	private static Random random;

	/**
	 * Adds particles created by the end gateway block using {@link Double} for positions instead of {@link Float} in order to have precise particle positions
	 *
	 * @param ci         {@link CallbackInfo} required for {@link Inject}
	 * @param blockState {@link BlockState} of the block
	 * @param world      {@link World} of the block
	 * @param _blockPos  {@link BlockPos} of the block
	 * @param _random    {@code world}'s {@link Random} instance
	 * @author geni
	 */
	@Inject(
		at = @At(
			value = "HEAD"
		),
		method = "randomDisplayTick"
	)
	private void setVariables(BlockState blockState, World world, BlockPos _blockPos, Random _random, CallbackInfo ci) {
		if (FarLands.getConfig().fixParticlesEntities.getValue()) {
			random = _random;
			blockPos = _blockPos;
		}
	}

	@ModifyVariable(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/util/math/BlockPos;getY()I"
		),
		method = "randomDisplayTick",
		ordinal = 0
	)
	private double setX(double original) {
		return FarLands.getConfig().fixParticlesEntities.getValue() ? blockPos.getX() + (double) random.nextFloat() : original;
	}

	@ModifyVariable(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/util/math/BlockPos;getZ()I"
		),
		method = "randomDisplayTick",
		ordinal = 1
	)
	private double setY(double original) {
		return FarLands.getConfig().fixParticlesEntities.getValue() ? blockPos.getY() + (double) random.nextFloat() : original;
	}

	@ModifyVariable(
		at = @At(
			value = "INVOKE",
			target = "Ljava/util/Random;nextFloat()F",
			ordinal = 3
		),
		method = "randomDisplayTick",
		ordinal = 2
	)
	private double setZ(double original) {
		return FarLands.getConfig().fixParticlesEntities.getValue() ? blockPos.getZ() + (double) random.nextFloat() : original;
	}
}
