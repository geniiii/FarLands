package site.geni.FarLands.mixins.common.block;

import net.minecraft.block.TntBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.PrimedTntEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import site.geni.FarLands.utils.Config;

@SuppressWarnings("unused")
@Mixin(TntBlock.class)
public abstract class TntBlockMixin {
	/**
	 * Spawns primed TNT (primed by an entity) using {@link Double} for positions instead of {@link Float} in order to have precise spawn and sound positions
	 *
	 * @param ci           {@link CallbackInfo} required for {@link Inject}
	 * @param world        {@link World} of the block
	 * @param blockPos     {@link BlockPos} of the block
	 * @param livingEntity {@link LivingEntity} that caused the TNT to be primed
	 * @author geni
	 */
	@Inject(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/entity/PrimedTntEntity;<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/entity/LivingEntity;)V"
		),
		method = "primeTnt(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/LivingEntity;)V",
		cancellable = true
	)
	private static void primeTntProperly(World world, BlockPos blockPos, LivingEntity livingEntity, CallbackInfo ci) {
		if (Config.getConfig().fixParticles) {
			final PrimedTntEntity primedTntEntity = new PrimedTntEntity(world, blockPos.getX() + 0.5D, blockPos.getY(), blockPos.getZ() + 0.5D, livingEntity);

			world.spawnEntity(primedTntEntity);
			world.playSound(null, primedTntEntity.x, primedTntEntity.y, primedTntEntity.z, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCK, 1.0F, 1.0F);

			ci.cancel();
		}
	}

	/**
	 * Spawns primed TNT (primed by an explosion) using {@link Double} for positions instead of {@link Float} in order to have precise spawn and sound positions
	 *
	 * @param ci        {@link CallbackInfo} required for {@link Inject}
	 * @param world     {@link World} of the block
	 * @param blockPos  {@link BlockPos} of the block
	 * @param explosion {@link Explosion} that caused the TNT to be primed
	 * @author geni
	 */
	@Inject(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/entity/PrimedTntEntity;<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/entity/LivingEntity;)V"
		),
		method = "onDestroyedByExplosion",
		cancellable = true
	)
	private void primeTntPrimedByExplosionProperly(World world, BlockPos blockPos, Explosion explosion, CallbackInfo ci) {
		if (Config.getConfig().fixParticles) {
			final PrimedTntEntity primedTntEntity = new PrimedTntEntity(world, blockPos.getX() + 0.5D, blockPos.getY(), blockPos.getZ() + 0.5D, explosion.getCausingEntity());
			primedTntEntity.setFuse((short) (world.random.nextInt(primedTntEntity.getFuseTimer() / 4) + primedTntEntity.getFuseTimer() / 8));

			world.spawnEntity(primedTntEntity);

			ci.cancel();
		}
	}
}
