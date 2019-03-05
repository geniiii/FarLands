package site.geni.FarLands.mixins.client.item;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@SuppressWarnings("unused")
@Mixin(Item.class)
public abstract class ItemMixin {
	/* used by the BoneMealItem mixin */
	@Shadow
	@Final
	protected static Random random;
}
