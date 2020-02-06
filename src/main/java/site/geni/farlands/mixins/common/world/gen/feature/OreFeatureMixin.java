package site.geni.farlands.mixins.common.world.gen.feature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import site.geni.farlands.FarLands;

import java.util.Random;

@SuppressWarnings("unused")
@Mixin(OreFeature.class)
public abstract class OreFeatureMixin {
	@Shadow
	protected abstract boolean generateVeinPart(IWorld iWorld_1, Random random_1, OreFeatureConfig oreFeatureConfig_1, double double_1, double double_2, double double_3, double double_4, double double_5, double double_6, int int_1, int int_2, int int_3, int int_4, int int_5);

	@Inject(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/gen/feature/OreFeature;generateVeinPart(Lnet/minecraft/world/IWorld;Ljava/util/Random;Lnet/minecraft/world/gen/feature/OreFeatureConfig;DDDDDDIIIII)Z",
			shift = At.Shift.BEFORE
		),
		method = "generate",
		locals = LocalCapture.CAPTURE_FAILHARD,
		cancellable = true
	)
	private void generateOresProperly(IWorld iWorld, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator, Random random, BlockPos blockPos, OreFeatureConfig oreFeatureConfig, CallbackInfoReturnable<Boolean> cir, float float_1, float float_2, int int_1, double double_1, double double_2, double double_3, double double_4, int int_2, double double_5, double double_6, int int_3, int int_4, int int_5, int int_6, int int_7, int int_8, int int_9) {
		if (FarLands.getConfig().fixOreGeneration.getValue() && FarLands.getConfig().farLandsEnabled.getValue()) {
			double_1 = blockPos.getX() + (double) (MathHelper.sin(float_1) * float_2);
			double_2 = blockPos.getX() - (double) (MathHelper.sin(float_1) * float_2);
			double_3 = blockPos.getZ() + (double) (MathHelper.cos(float_1) * float_2);
			double_4 = blockPos.getZ() - (double) (MathHelper.cos(float_1) * float_2);

			cir.setReturnValue(this.generateVeinPart(iWorld, random, oreFeatureConfig, double_1, double_2, double_3, double_4, double_5, double_6, int_3, int_4, int_5, int_6, int_7));
		}
	}
}
