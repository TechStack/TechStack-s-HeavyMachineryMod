package com.projectreddog.machinemod.entity.monster;

import java.util.Random;

import com.projectreddog.machinemod.entity.ai.EntityAIFleeLight;
import com.projectreddog.machinemod.entity.ai.EntityAIWanderAvoidWaterAvoidLightFlying;
import com.projectreddog.machinemod.entity.ai.EntityFlyFastTurnHelper;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityExpStalker extends EntityMob implements EntityFlying {

	public EntityExpStalker(World worldIn) {
		super(worldIn);
		// TODO : Set Size properly.
		setSize(1f, 1f);
		this.moveHelper = new EntityFlyFastTurnHelper(this);
		this.setNoGravity(true);

	}

	@Override
	public void onUpdate() {

		this.setNoGravity(true);
		super.onUpdate();
		this.setNoGravity(true);
	}

	public boolean isFlying() {
		return !this.onGround;
	}

	public void fall(float distance, float damageMultiplier) {
	}

	@Override
	protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);

		this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(.65D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(.65D);

		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(64.0D);
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new AIRandomFly(this));

		this.tasks.addTask(0, new EntityAIWanderAvoidWaterAvoidLightFlying(this, .5d));
		// this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
		this.tasks.addTask(0, new EntityAIFleeLight(this, .75f));
		this.tasks.addTask(0, new EntityAILeapAtTarget(this, .75f));
		this.tasks.addTask(1, new EntityAIAttackMelee(this, .45d, true));
		this.targetTasks.addTask(0, new EntityAIWanderAvoidWaterAvoidLightFlying(this, .5d));

		this.targetTasks.addTask(0, new EntityAIFleeLight(this, .75f));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));

		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));

	}

	@Override
	protected boolean isValidLightLevel() {
		return true;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 5;
	}

	static class AIRandomFly extends EntityAIBase {
		private final EntityExpStalker parentEntity;

		public AIRandomFly(EntityExpStalker entityExpStalker) {
			this.parentEntity = entityExpStalker;
			this.setMutexBits(1);
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean shouldExecute() {
			EntityMoveHelper entitymovehelper = this.parentEntity.getMoveHelper();

			if (!entitymovehelper.isUpdating()) {
				return true;
			} else {
				double d0 = entitymovehelper.getX() - this.parentEntity.posX;
				double d1 = entitymovehelper.getY() - this.parentEntity.posY;
				double d2 = entitymovehelper.getZ() - this.parentEntity.posZ;
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				return d3 < 1.0D || d3 > 3600.0D;
			}
		}

		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		public boolean shouldContinueExecuting() {
			return false;
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void startExecuting() {
			Random random = this.parentEntity.getRNG();
			double d0 = this.parentEntity.posX + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			double d1 = this.parentEntity.posY + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			double d2 = this.parentEntity.posZ + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			this.parentEntity.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
		}
	}

}
