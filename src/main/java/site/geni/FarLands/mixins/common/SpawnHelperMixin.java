package site.geni.FarLands.mixins.common;

import com.sun.istack.internal.Nullable;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sortme.SpawnHelper;
import net.minecraft.sortme.SpawnRestriction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPos;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Objects;
import java.util.Random;

@SuppressWarnings("unused")
@Mixin(SpawnHelper.class)
public abstract class SpawnHelperMixin {
	private static double x;
	private static double z;

	@Shadow
	@Final
	private static Logger LOGGER;

	@Shadow
	private static BlockPos method_8657(World world, WorldChunk worldChunk) {
		return null;
	}

	@Shadow
	private static Biome.SpawnEntry method_8664(ChunkGenerator<?> chunkGenerator_1, EntityCategory entityCategory_1, Random random_1, BlockPos blockPos_1) {
		return null;
	}

	@Shadow
	public static boolean canSpawn(SpawnRestriction.Location spawnRestriction$Location_1, ViewableWorld viewableWorld_1, BlockPos blockPos_1, @Nullable EntityType<?> entityType_1) {
		return true;
	}

	@Shadow
	private static boolean method_8659(ChunkGenerator<?> chunkGenerator_1, EntityCategory entityCategory_1, Biome.SpawnEntry biome$SpawnEntry_1, BlockPos blockPos_1) {
		return true;
	}

	/**
	 * @author geni
	 */
	@Overwrite
	public static void spawnEntitiesInChunk(EntityCategory entityCategory_1, World world_1, WorldChunk worldChunk_1, BlockPos blockPos_1) {
		ChunkGenerator<?> chunkGenerator_1 = world_1.getChunkManager().getChunkGenerator();
		int int_1 = 0;
		BlockPos blockPos_2 = method_8657(world_1, worldChunk_1);
		int int_2 = blockPos_2.getX();
		int int_3 = blockPos_2.getY();
		int int_4 = blockPos_2.getZ();
		if (int_3 >= 1) {
			BlockState blockState_1 = worldChunk_1.getBlockState(blockPos_2);
			if (!blockState_1.isSimpleFullBlock(worldChunk_1, blockPos_2)) {
				BlockPos.Mutable blockPos$Mutable_1 = new BlockPos.Mutable();
				int int_5 = 0;

				while (int_5 < 3) {
					int int_6 = int_2;
					int int_7 = int_4;
					boolean int_8 = true;
					Biome.SpawnEntry biome$SpawnEntry_1 = null;
					EntityData entityData_1 = null;
					int int_9 = MathHelper.ceil(Math.random() * 4.0D);
					int int_10 = 0;
					int int_11 = 0;

					while (true) {
						label119:
						{
							if (int_11 < int_9) {
								label118:
								{
									int_6 += world_1.random.nextInt(6) - world_1.random.nextInt(6);
									int_7 += world_1.random.nextInt(6) - world_1.random.nextInt(6);
									blockPos$Mutable_1.set(int_6, int_3, int_7);
									// important part!
									double float_1 = int_6 + 0.5D;
									double float_2 = int_7 + 0.5D;
									PlayerEntity playerEntity_1 = world_1.method_18457(float_1, float_2, -1.0D);
									if (playerEntity_1 == null || playerEntity_1.squaredDistanceTo(float_1, (double) int_3, float_2) <= 576.0D || blockPos_1.isWithinDistance(new Vec3d(float_1, (double) int_3, float_2), 24.0D)) {
										break label119;
									}

									ChunkPos chunkPos_1 = new ChunkPos(blockPos$Mutable_1);
									if (!Objects.equals(chunkPos_1, worldChunk_1.getPos())) {
										break label119;
									}

									if (biome$SpawnEntry_1 == null) {
										biome$SpawnEntry_1 = method_8664(chunkGenerator_1, entityCategory_1, world_1.random, blockPos$Mutable_1);
										if (biome$SpawnEntry_1 == null) {
											break label118;
										}

										int_9 = biome$SpawnEntry_1.minGroupSize + world_1.random.nextInt(1 + biome$SpawnEntry_1.maxGroupSize - biome$SpawnEntry_1.minGroupSize);
									}

									if (biome$SpawnEntry_1.type.getEntityClass() == EntityCategory.MISC) {
										break label119;
									}

									EntityType<?> entityType_1 = biome$SpawnEntry_1.type;
									if (!entityType_1.isSummonable() || !method_8659(chunkGenerator_1, entityCategory_1, biome$SpawnEntry_1, blockPos$Mutable_1)) {
										break label119;
									}

									SpawnRestriction.Location spawnRestriction$Location_1 = SpawnRestriction.getLocation(entityType_1);
									if (spawnRestriction$Location_1 == null || !canSpawn(spawnRestriction$Location_1, world_1, blockPos$Mutable_1, entityType_1) || !world_1.doesNotCollide(entityType_1.createSimpleBoundingBox(float_1, (double) int_3, float_2))) {
										break label119;
									}

									MobEntity mobEntity_2;
									try {
										Entity entity_1 = entityType_1.create(world_1);
										if (!(entity_1 instanceof MobEntity)) {
											throw new IllegalStateException("Trying to spawn a non-mob: " + Registry.ENTITY_TYPE.getId(entityType_1));
										}

										mobEntity_2 = (MobEntity) entity_1;
									} catch (Exception var29) {
										LOGGER.warn("Failed to create mob", var29);
										return;
									}

									mobEntity_2.setPositionAndAngles(float_1, (double) int_3, float_2, world_1.random.nextFloat() * 360.0F, 0.0F);
									if (playerEntity_1.squaredDistanceTo(float_1, (double) int_3, float_2) > 16384.0D && mobEntity_2.canImmediatelyDespawn(playerEntity_1.squaredDistanceTo(float_1, (double) int_3, float_2)) || !mobEntity_2.canSpawn(world_1, SpawnType.NATURAL) || !mobEntity_2.method_5957(world_1)) {
										break label119;
									}

									entityData_1 = mobEntity_2.prepareEntityData(world_1, world_1.getLocalDifficulty(new BlockPos(mobEntity_2)), SpawnType.NATURAL, entityData_1, null);
									++int_1;
									++int_10;
									world_1.spawnEntity(mobEntity_2);
									if (int_1 >= mobEntity_2.getLimitPerChunk()) {
										return;
									}

									if (!mobEntity_2.spawnsTooManyForEachTry(int_10)) {
										break label119;
									}
								}
							}

							++int_5;
							break;
						}

						++int_11;
					}
				}

			}
		}
	}
}
