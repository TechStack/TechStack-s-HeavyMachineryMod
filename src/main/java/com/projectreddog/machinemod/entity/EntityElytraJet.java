package com.projectreddog.machinemod.entity;

import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityElytraJet extends Entity {
	private static final DataParameter<Integer> BOOSTED_ENTITY_ID = EntityDataManager.<Integer> createKey(EntityFireworkRocket.class, DataSerializers.VARINT);
	private EntityLivingBase boostedEntity;
	private boolean boostActive;
	private double boostAmt = 2.5d;

	public EntityElytraJet(World worldIn) {
		super(worldIn);
		boostActive = true;

		// TODO Auto-generated constructor stub
	}

	public void setBoostedEntity(EntityLivingBase boostedEntity) {
		this.dataManager.set(BOOSTED_ENTITY_ID, Integer.valueOf(boostedEntity.getEntityId()));
		this.boostedEntity = boostedEntity;
	}

	public void ActivateBoost() {
		boostActive = true;
	}

	public void DeactivateBoost() {
		boostActive = false;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate() {
		// if (boostActive) {
		this.lastTickPosX = this.posX;
		this.lastTickPosY = this.posY;
		this.lastTickPosZ = this.posZ;
		super.onUpdate();
		LogHelper.info("elytraJet Active");
		if (this.boostedEntity == null) {
			LogHelper.info("Null bost entity");
			Entity entity = this.world.getEntityByID(((Integer) this.dataManager.get(BOOSTED_ENTITY_ID)).intValue());

			if (entity instanceof EntityLivingBase) {
				this.boostedEntity = (EntityLivingBase) entity;

			}
		}

		if (this.boostedEntity != null) {
			LogHelper.info("non Null bost entity");
			if (this.boostedEntity.isElytraFlying()) {
				LogHelper.info("Elytra flying");

				Vec3d vec3d = this.boostedEntity.getLookVec();
				double d0 = 1.5D;
				double d1 = 0.1D;
				double preX = this.boostedEntity.motionX;
				double preZ = this.boostedEntity.motionZ;
				this.boostedEntity.motionX += (vec3d.x * boostAmt);
				this.boostedEntity.motionY += (vec3d.y * boostAmt);
				this.boostedEntity.motionZ += (vec3d.z * boostAmt);

				LogHelper.info("Pre X, Z :" + preX + " , " + preZ + "Post : " + boostedEntity.motionX + "," + boostedEntity.motionZ);
			} else {
				LogHelper.info("killing");
				this.setDead();
			}

			this.setPosition(this.boostedEntity.posX, this.boostedEntity.posY, this.boostedEntity.posZ);
			this.motionX = this.boostedEntity.motionX;
			this.motionY = this.boostedEntity.motionY;
			this.motionZ = this.boostedEntity.motionZ;
		}

		float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
		this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));

		for (this.rotationPitch = (float) (MathHelper.atan2(this.motionY, (double) f) * (180D / Math.PI)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
			;
		}

		while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
			this.prevRotationPitch += 360.0F;
		}

		while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
			this.prevRotationYaw -= 360.0F;
		}

		while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
			this.prevRotationYaw += 360.0F;
		}

		this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
		this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;

		if (!this.isSilent()) {
			// this.world.playSound((EntityPlayer) null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_FIREWORK_LAUNCH, SoundCategory.AMBIENT, 3.0F, 1.0F);
		}

		if (this.world.isRemote) {
			// this.world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, this.posX, this.posY - 0.3D, this.posZ, this.rand.nextGaussian() * 0.05D, -this.motionY * 0.5D, this.rand.nextGaussian() * 0.05D);
		}

		// }
	}

	/*
	 * Updates the entity motion clientside, called by packets from the server
	 */
	@SideOnly(Side.CLIENT)
	public void setVelocity(double x, double y, double z) {
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt(x * x + z * z);
			this.rotationYaw = (float) (MathHelper.atan2(x, z) * (180D / Math.PI));
			this.rotationPitch = (float) (MathHelper.atan2(y, (double) f) * (180D / Math.PI));
			this.prevRotationYaw = this.rotationYaw;
			this.prevRotationPitch = this.rotationPitch;
		}
	}

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		this.dataManager.register(BOOSTED_ENTITY_ID, Integer.valueOf(0));

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub

	}

}
