package com.reptiles.common;

import java.util.Random;
import javax.annotation.Nullable;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class EntityAIAvoidCold extends EntityAIBase {
    private final EntityCreature creature;
    private double warmX;
    private double warmY;
    private double warmZ;
    private final double movementSpeed;
    private final World world;
    private int coldTimer;

    public EntityAIAvoidCold(EntityCreature theCreatureIn, double movementSpeedIn) {
        creature = theCreatureIn;
        movementSpeed = movementSpeedIn;
        world = theCreatureIn.world;
        setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        BlockPos bp = new BlockPos(creature.posX, creature.posY, creature.posZ);
        Biome biome = world.getBiome(bp);
        Biome.TempCategory tempCat = biome.getTempCategory();
        if (tempCat != Biome.TempCategory.COLD) {
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

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting() {
        return !creature.getNavigator().noPath() && coldTimer > 0;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        coldTimer = 200;
        creature.getNavigator().tryMoveToXYZ(warmX, warmY, warmZ, movementSpeed);
    }

    public void resetTask() {
        coldTimer = 0;
    }

    public void updateTask() {
        coldTimer = Math.max(0, coldTimer - 1);
        if (coldTimer < 4) {
            creature.setDead();
        }
    }

    @Nullable
    private Vec3d findWarmClimate() {
        Random random = creature.getRNG();
        BlockPos bp = new BlockPos(creature.posX, creature.getEntityBoundingBox().minY, creature.posZ);

        for (int i = 0; i < 10; ++i) {
            BlockPos bp1 = bp.add(random.nextInt(20) - 10, random.nextInt(6) - 3, random.nextInt(20) - 10);
            Biome biome = world.getBiome(bp);
            Biome.TempCategory tempCat = biome.getTempCategory();

            if (tempCat != Biome.TempCategory.COLD && creature.getBlockPathWeight(bp1) < 0.0F) {
                return new Vec3d((double) bp1.getX(), (double) bp1.getY(), (double) bp1.getZ());
            }
        }

        return null;
    }
}
