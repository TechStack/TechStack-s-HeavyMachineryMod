package com.projectreddog.machinemod.entity.ai;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityAIFleeLight extends EntityAIBase {
	private final CreatureEntity creature;
	private double shelterX;
	private double shelterY;
	private double shelterZ;
	private final double movementSpeed;
	private final World world;

	public EntityAIFleeLight(CreatureEntity theCreatureIn, double movementSpeedIn) {
		this.creature = theCreatureIn;
		this.movementSpeed = movementSpeedIn;
		this.world = theCreatureIn.world;
		this.setMutexBits(1);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {

		if (this.world.getLight(new BlockPos(this.creature.posX, this.creature.getEntityBoundingBox().minY, this.creature.posZ)) == 0) {
			return false;
		} else {
			Vec3d vec3d = this.findPossibleShelter();

			if (vec3d == null) {
				return false;
			} else {
				this.shelterX = vec3d.x;
				this.shelterY = vec3d.y + 10;
				this.shelterZ = vec3d.z;
				return true;
			}
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean shouldContinueExecuting() {
		return !this.creature.getNavigator().noPath();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		this.creature.getNavigator().tryMoveToXYZ(this.shelterX, this.shelterY, this.shelterZ, this.movementSpeed);
	}

	@Nullable
	private Vec3d findPossibleShelter() {
		Random random = this.creature.getRNG();
		BlockPos blockpos = new BlockPos(this.creature.posX, this.creature.getEntityBoundingBox().minY, this.creature.posZ);

		for (int i = 0; i < 10; ++i) {
			BlockPos blockpos1 = blockpos.add(random.nextInt(40) - 20, random.nextInt(8) - 4, random.nextInt(40) - 20);

			if ((this.world.getLight(blockpos1) == 0)) {
				return new Vec3d((double) blockpos1.getX(), (double) blockpos1.getY(), (double) blockpos1.getZ());
			}
		}

		return null;
	}
}