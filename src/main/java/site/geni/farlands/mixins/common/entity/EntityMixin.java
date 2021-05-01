package site.geni.farlands.mixins.common.entity;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@SuppressWarnings("unused")
@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    public abstract double getZ();

    @Shadow
    public abstract double getX();

    /**
     * Clamps entity positions to -{@link Double#MAX_VALUE} instead of -3.0E7
     *
     * @param original Original double of -3.0E7
     * @return -{@link Double#MAX_VALUE}
     * @author geni
     */
    @ModifyConstant(
            constant = @Constant(
                    doubleValue = -3.0E7D
            ),
            method = "method_30634"
    )
    private static double clampPositionToNegativeDoubleMaxValueXZ(double original) {
        return -Double.MAX_VALUE;
    }

    /**
     * Clamps entity positions to positive {@link Double#MAX_VALUE} instead of 3.0E7
     *
     * @param original Original double of 3.0E7
     * @return {@link Double#MAX_VALUE}
     * @author geni
     */
    @ModifyConstant(
            constant = @Constant(
                    doubleValue = 3.0E7D
            ),
            method = "method_30634"
    )
    private static double clampPositionToPositiveDoubleMaxValueXZ(double original) {
        return Double.MAX_VALUE;
    }

    /**
     * Clamps entity portal teleportation to -{@link Double#MAX_VALUE}
     *
     * @param original Original double of -2.9999872E7
     * @return -{@link Double#MAX_VALUE}
     * @author geni
     */
    @ModifyConstant(
            constant = @Constant(
                    doubleValue = -2.9999872E7D
            ),
            method = "getTeleportTarget"
    )
    private static double clampTeleportToNegativeDoubleMaxValueXZ(double original) {
        return -Double.MAX_VALUE;
    }

    /**
     * Clamps entity portal teleportation to {@link Double#MAX_VALUE}
     *
     * @param original Original double of 2.9999872E7
     * @return {@link Double#MAX_VALUE}
     * @author geni
     */
    @ModifyConstant(
            constant = @Constant(
                    doubleValue = 2.9999872E7D
            ),
            method = "getTeleportTarget"
    )
    private static double clampTeleportToPositiveDoubleMaxValueXZ(double original) {
        return Double.MAX_VALUE;
    }


    @Shadow
    public abstract void remove();
}
