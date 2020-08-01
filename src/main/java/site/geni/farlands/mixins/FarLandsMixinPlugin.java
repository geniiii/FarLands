package site.geni.farlands.mixins;

import com.google.common.collect.ImmutableMap;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import site.geni.farlands.FarLands;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public final class FarLandsMixinPlugin implements IMixinConfigPlugin {
	private static final Map<String, Supplier<Boolean>> CONDITIONS = ImmutableMap.of(
		"site.geni.farlands.mixins.util.math.BlockPosMixin", () -> FarLands.getConfig().fixes.fixLighting,
		"site.geni.farlands.mixins.util.math.ChunkSectionPos", () -> FarLands.getConfig().fixes.fixLighting
	);

	@Override
	public void onLoad(String mixinPackage) {
	}

	@Override
	public String getRefMapperConfig() {
		return null;
	}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		return CONDITIONS.getOrDefault(mixinClassName, () -> true).get();
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
	}

	@Override
	public List<String> getMixins() {
		return null;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
	}
}