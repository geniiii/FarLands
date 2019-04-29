package site.geni.FarLands.mixins.common.world;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BoundingBox;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import site.geni.FarLands.FarLands;

@SuppressWarnings("unused")
@Mixin(SpawnHelper.class)
public abstract class SpawnHelperMixin {
	private static double x;
	private static double z;

	/**
	 * Sets {@link #x} and {@link #z} to the mob to be spawned's X and Z position, respectively
	 *
	 * @param entityCategory   The {@link EntityCategory} of the mobs to be spawned
	 * @param world            The world in which the chunk in which the mobs are to be spawned is
	 * @param worldChunk       The chunk in which the mobs are to be spawned
	 * @param spawnPos         {@code world}'s spawn position
	 * @param ci               {@link CallbackInfo} required by {@link Inject}
	 * @param chunkGenerator   The world's {@link ChunkGenerator}
	 * @param mobsSpawned      The amount of mobs spawned in {@code worldChunk}
	 * @param mobGroupBlockPos The mob group's {@link BlockPos}
	 * @param mobGroupX        The X position at which the mob group starts
	 * @param mobGroupY        The Y position at which the mob group starts
	 * @param mobGroupZ        The Z position at which the mob group starts
	 * @param blockState       The block at {@code spawnPos}'s {@link BlockState}
	 * @param mobGroupsSpawned The amount of mob groups spawned in the chunk
	 * @param mobX             The mob's X position
	 * @param mobZ             The mob's Z position
	 * @param unknown_1        Unknown
	 * @param biomeSpawnEntry  The {@link Biome.SpawnEntry} where the mob group is located at (?)
	 * @param mobEntityData    The mob's {@link EntityData}
	 * @param mobGroupSize     The size of the mob group
	 * @param unknown_2        Unknown
	 * @param unknown_3        Unknown
	 * @param mobXF            {@code mobX} casted to a float + 0.5F
	 * @param mobZF            {@code mobZ} casted to a float + 0.5F
	 * @author geni
	 */
	@Inject(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/World;getClosestPlayer(DDD)Lnet/minecraft/entity/player/PlayerEntity;"
		),
		method = "spawnEntitiesInChunk",
		locals = LocalCapture.CAPTURE_FAILHARD
	)
	private static void setXZ(EntityCategory entityCategory, World world, WorldChunk worldChunk, BlockPos spawnPos, CallbackInfo ci, ChunkGenerator chunkGenerator, int mobsSpawned, BlockPos mobGroupBlockPos, int mobGroupX, int mobGroupY, int mobGroupZ, BlockState blockState, BlockPos.Mutable blockPosMutable, int mobGroupsSpawned, int mobX, int mobZ, int unknown_1, Biome.SpawnEntry biomeSpawnEntry, EntityData mobEntityData, int mobGroupSize, int unknown_2, int unknown_3, float mobXF, float mobZF) {
		if (FarLands.getConfig().fixMobSpawning) {
			x = mobX + 0.5D;
			z = mobZ + 0.5D;
		}
	}

	@Redirect(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/World;getClosestPlayer(DDD)Lnet/minecraft/entity/player/PlayerEntity;"
		),
		method = "spawnEntitiesInChunk"
	)
	private static PlayerEntity getPlayerEntityClosestToCoordinatesProperly(World world, double origX, double y, double origZ) {
		return FarLands.getConfig().fixMobSpawning ? world.getClosestPlayer(x, y, z) : world.getClosestPlayer(origX, y, origZ);
	}

	@Redirect(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/entity/player/PlayerEntity;squaredDistanceTo(DDD)D"
		),
		method = "spawnEntitiesInChunk"
	)
	private static double getPlayerSquaredDistanceToProperly(PlayerEntity playerEntity, double origX, double y, double origZ) {
		return FarLands.getConfig().fixMobSpawning ? playerEntity.squaredDistanceTo(x, y, z) : playerEntity.squaredDistanceTo(origX, y, origZ);
	}

	@Redirect(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/util/math/BlockPos;isWithinDistance(Lnet/minecraft/util/math/Position;D)Z"
		),
		method = "spawnEntitiesInChunk"
	)
	private static boolean isWithinDistanceProperly(BlockPos blockPos, Position position, double distance) {
		return FarLands.getConfig().fixMobSpawning ? blockPos.isWithinDistance(new Vec3d(x, position.getY(), z), distance) : blockPos.isWithinDistance(position, distance);
	}

	@Redirect(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/entity/EntityType;createSimpleBoundingBox(DDD)Lnet/minecraft/util/math/BoundingBox;"
		),
		method = "spawnEntitiesInChunk"
	)
	private static BoundingBox createSimpleBoundingBoxProperly(EntityType entityType, double origX, double y, double origZ) {
		return FarLands.getConfig().fixMobSpawning ? entityType.createSimpleBoundingBox(x, y, z) : entityType.createSimpleBoundingBox(origX, y, origZ);
	}

	@Redirect(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/entity/mob/MobEntity;setPositionAndAngles(DDDFF)V"
		),
		method = "spawnEntitiesInChunk"
	)
	private static void setPositionAndAnglesProperly(MobEntity mobEntity, double origX, double y, double origZ, float yaw, float pitch) {
		if (FarLands.getConfig().fixMobSpawning) {
			mobEntity.setPositionAndAngles(x, y, z, yaw, pitch);
		} else {
			mobEntity.setPositionAndAngles(origX, y, origZ, yaw, pitch);
		}
	}
}
