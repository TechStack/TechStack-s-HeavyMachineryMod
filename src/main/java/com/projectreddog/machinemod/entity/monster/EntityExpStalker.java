package com.projectreddog.machinemod.entity.monster;

import java.util.Random;

import com.projectreddog.machinemod.entity.ai.EntityFlyFastTurnHelper;
import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityExpStalker extends EntityMob {

	public EntityExpStalker(World worldIn) {
		super(worldIn);
		// TODO : Set Size properly.
		setSize(1f, 1f);
		this.moveHelper = new EntityFlyFastTurnHelper(this);
		this.setNoGravity(true);

	}

	@Override
	public void onUpdate() {
		this.noClip = true;

		super.onUpdate();
	}

	public boolean isFlying() {
		return !this.onGround;
	}

	public void fall(float distance, float damageMultiplier) {
	}

	public void travel(float strafe, float vertical, float forward) {
		float f = 0.91F;

		if (this.onGround) {
			BlockPos underPos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ));
			IBlockState underState = this.world.getBlockState(underPos);
			f = underState.getBlock().getSlipperiness(underState, this.world, underPos, this) * 0.91F;
		}

		if (this.getAttackTarget() != null) {

			EntityLivingBase elb = this.getAttackTarget();
			LogHelper.info("attack target not null" + elb.posX + " " + elb.posY + " " + elb.posZ);

		}

		float f1 = .3F;
		this.moveRelative(strafe, vertical, forward, f1);
		f = 0.91F;

		if (this.onGround) {
			BlockPos underPos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ));
			IBlockState underState = this.world.getBlockState(underPos);
			f = underState.getBlock().getSlipperiness(underState, this.world, underPos, this) * 0.91F;
		}

		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		this.motionX *= (double) f;
		this.motionY *= (double) f;
		this.motionZ *= (double) f;

		this.prevLimbSwingAmount = this.limbSwingAmount;
		double d1 = this.posX - this.prevPosX;
		double d0 = this.posZ - this.prevPosZ;
		float f2 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;

		if (f2 > 1.0F) {
			f2 = 1.0F;
		}

		this.limbSwingAmount += (f2 - this.limbSwingAmount) * 0.4F;
		this.limbSwing += this.limbSwingAmount;

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

		// this.tasks.addTask(0, new EntityAIWanderAvoidWaterAvoidLightFlying(this, .5d));
		// this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
		// this.tasks.addTask(0, new EntityAIFleeLight(this, .75f));
		// this.tasks.addTask(3, new EntityAILeapAtTarget(this, .75f));
		// this.tasks.addTask(1, new EntityAIAttackMelee(this, .45d, true));
		this.tasks.addTask(4, new AIRandomFly(this));

		// this.targetTasks.addTask(0, new EntityAIWanderAvoidWaterAvoidLightFlying(this, .5d));

		// this.targetTasks.addTask(0, new EntityAIFleeLight(this, .75f));
		// this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));

		// this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));

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
			double d0 = this.parentEntity.posX;
			double d1 = this.parentEntity.posY;
			double d2 = this.parentEntity.posZ;
			for (int i = 0; i < 50; i++) {
				Random random = this.parentEntity.getRNG();
				d0 = this.parentEntity.posX + (double) ((random.nextFloat() * 2.0F - 1.0F) * 5.0F);

				d2 = this.parentEntity.posZ + (double) ((random.nextFloat() * 2.0F - 1.0F) * 5.0F);

				d1 = this.parentEntity.world.getHeight((int) d0, (int) d2) + 15;
				if (this.parentEntity.world.getLight(new BlockPos(d0, d1 - 14, d2)) == 0) {
					i = 50;

					this.parentEntity.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
				}
			}

		}
	}

}
