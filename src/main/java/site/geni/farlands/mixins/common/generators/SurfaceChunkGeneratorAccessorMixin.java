package site.geni.farlands.mixins.common.generators;

import net.minecraft.world.gen.chunk.ChunkGeneratorType;
import net.minecraft.world.gen.chunk.SurfaceChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SurfaceChunkGenerator.class)
public interface SurfaceChunkGeneratorAccessorMixin {
	@Accessor(value = "field_24774")
	ChunkGeneratorType getChunkGeneratorType();
}
