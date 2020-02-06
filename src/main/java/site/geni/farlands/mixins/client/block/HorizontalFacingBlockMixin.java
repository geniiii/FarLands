package site.geni.farlands.mixins.client.block;

import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.state.property.DirectionProperty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(HorizontalFacingBlock.class)
public abstract class HorizontalFacingBlockMixin {
	@Shadow
	@Final
	public static DirectionProperty FACING;
}
