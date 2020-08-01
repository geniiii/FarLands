package site.geni.farlands.mixins.common.generators;

import net.minecraft.world.gen.chunk.ChunkGeneratorType;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Supplier;

@Mixin(NoiseChunkGenerator.class)
public interface NoiseChunkGeneratorAccessorMixin {
	@Accessor(value = "settings")
	Supplier<ChunkGeneratorType> getSettings();
}
