package com.reptiles.common;

import java.util.Random;
import javax.annotation.Nullable;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class EntityAIFleeCold extends EntityAIBase {
    private final EntityCreature creature;
    private double warmX;
    private double warmY;
    private double warmZ;
    private final double movementSpeed;
    private final World world;

    public EntityAIFleeCold(EntityCreature creatureArg, double movementSpeedArg) {
        creature = creatureArg;
        movementSpeed = movementSpeedArg;
        world = creatureArg.world;
        setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        BlockPos bp = new BlockPos(creature.posX, creature.getEntityBoundingBox().minY, creature.posZ);
        if (isWarmClimate(bp)) {
            return false;
        } else {
            Vec3d vec3d = findWarmClimate();

            if (vec3d == null) {
                return false;
            } else {
                warmX = vec3d.x;
                warmY = vec3d.y;
                warmZ = vec3d.z;
                return true;
            }
        }
    }

    @Override
    public boolean shouldContinueExecuting() {
        return !creature.getNavigator().noPath();
    }

    private boolean isWarmClimate(BlockPos bp) {
        Biome.TempCategory tc = world.getBiome(bp).getTempCategory();
        return tc.compareTo(Biome.TempCategory.WARM) == 0;
    }

    @Override
    public void startExecuting() {
        creature.getNavigator().tryMoveToXYZ(warmX, warmY, warmZ, movementSpeed);
    }

    @Nullable
    private Vec3d findWarmClimate() {
        Random random = creature.getRNG();
        BlockPos bp = new BlockPos(creature.posX, creature.getEntityBoundingBox().minY, creature.posZ);

        for (int i = 0; i < 10; ++i) {
            BlockPos bp1 = bp.add(random.nextInt(20) - 10, random.nextInt(6) - 3, random.nextInt(20) - 10);
            if (isWarmClimate(bp1) && creature.getBlockPathWeight(bp1) < 0.0F) {
                return new Vec3d((double) bp1.getX(), (double) bp1.getY(), (double) bp1.getZ());
            }
        }
        return null;
    }
}
