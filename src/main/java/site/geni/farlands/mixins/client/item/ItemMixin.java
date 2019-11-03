package site.geni.farlands.mixins.client.item;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@SuppressWarnings("unused")
@Mixin(Item.class)
public abstract class ItemMixin {
	/**
	 * The {@link Item}'s {@link Random} instance <br>
	 * Used by {@link BoneMealItemMixin#addParticlesProperly}
	 */
	@Shadow
	@Final
	protected static Random RANDOM;
}
