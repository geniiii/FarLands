package site.geni.farlands.mixins.common.world;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.SpawnHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import site.geni.farlands.FarLands;

@SuppressWarnings("unused")
@Mixin(SpawnHelper.class)
public abstract class SpawnHelperMixin {
    private static double x;
    private static double z;

    @Redirect(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/math/BlockPos;isWithinDistance(Lnet/minecraft/util/math/Position;D)Z"
            ),
            method = "spawnEntitiesInChunk"
    )
    private static boolean isWithinDistanceProperly(BlockPos blockPos, Position position, double distance) {
        return FarLands.getConfig().fixMobSpawning.getValue() ? blockPos.isWithinDistance(new Vec3d(x, position.getY(), z), distance) : blockPos.isWithinDistance(position, distance);
    }

    @Redirect(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/EntityType;createSimpleBoundingBox(DDD)Lnet/minecraft/util/math/Box;"
            ),
            method = "spawnEntitiesInChunk"
    )
    private static Box createSimpleBoundingBoxProperly(EntityType entityType, double origX, double y, double origZ) {
        return FarLands.getConfig().fixMobSpawning.getValue() ? entityType.createSimpleBoundingBox(x, y, z) : entityType.createSimpleBoundingBox(origX, y, origZ);
    }

    @Redirect(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/mob/MobEntity;refreshPositionAndAngles(DDDFF)V"
            ),
            method = "spawnEntitiesInChunk"
    )
    private static void setPositionAndAnglesProperly(MobEntity mobEntity, double origX, double y, double origZ, float yaw, float pitch) {
        if (FarLands.getConfig().fixMobSpawning.getValue()) {
            mobEntity.refreshPositionAndAngles(x, y, z, yaw, pitch);
        } else {
            mobEntity.refreshPositionAndAngles(origX, y, origZ, yaw, pitch);
        }
    }
}
