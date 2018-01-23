package com.projectreddog.machinemod.entity.ai;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAIWanderAvoidWaterAvoidLightFlying extends EntityAIWanderAvoidWater {

	public EntityAIWanderAvoidWaterAvoidLightFlying(EntityCreature p_i47413_1_, double p_i47413_2_) {
		super(p_i47413_1_, p_i47413_2_);
	}

	@Override
	@Nullable
	protected Vec3d getPosition() {
		Vec3d vec3d = null;

		int tries = 0;

		vec3d = RandomPositionGenerator.getLandPos(this.entity, 15, 15);
		if (vec3d != null) {
			vec3d = new Vec3d(vec3d.x, vec3d.y + 10, vec3d.z);

			BlockPos bp = new BlockPos(vec3d);

			while (this.entity.world.getLight(bp) > 0 && tries < 50) {
				vec3d = RandomPositionGenerator.getLandPos(this.entity, 15, 15);
				vec3d = new Vec3d(vec3d.x, vec3d.y + 10, vec3d.z);
				bp = new BlockPos(vec3d);
				tries++;

			}
		}
		return vec3d == null ? super.getPosition() : vec3d;
	}

}