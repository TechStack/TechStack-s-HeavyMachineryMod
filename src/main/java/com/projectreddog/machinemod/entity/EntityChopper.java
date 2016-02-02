package com.projectreddog.machinemod.entity;

import com.projectreddog.machinemod.init.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityChopper extends EntityMachineModRideable {
	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public double bladeOffset = 2.0d;

	public EntityChopper(World world) {
		super(world);
		setSize(2.5F, 2.5F);
		inventory = new ItemStack[9];
		this.mountedOffsetY = 0.1D;
		this.mountedOffsetX = 1d;
		this.mountedOffsetZ = 1d;
		this.maxAngle = 0;
		this.minAngle = -60;
		this.droppedItem = ModItems.chopper;
		this.shouldSendClientInvetoryUpdates = false;
		this.willSink = false;
		this.maxSpeed = 2.4d;
		this.accelerationAmount = .08d;
		this.turnRate = 5d;
		this.canFly = true;
		// this.isWaterOnly = true;
		this.nextParticleAtTick = 5;

	}

	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {
			if (this.riddenByEntity != null && currentFuelLevel > 0) {
				this.Attribute2++;
				currentFuelLevel--;
			}

			if ((this.currentFuelLevel > 0 && isPlayerPushingSprintButton) || this.riddenByEntity == null || this.currentFuelLevel == 0) {
				this.motionY -= 0.04D;
				if (this.riddenByEntity == null && !this.isCollidedVertically && this.currentFuelLevel > 0) {
					this.Attribute2++;
				}

			}
			if (this.currentFuelLevel > 0 && isPlayerPushingJumpButton) {

				if (worldObj.isAirBlock(new BlockPos((int) (posX - .5d), (int) posY, (int) (posZ - .5d)))) {
					this.motionY += 0.08D;
				}
			}

			if (this.currentFuelLevel > 0 && this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer) {
				EntityPlayer entityPlayer = (EntityPlayer) this.riddenByEntity;
				// entityPlayer.setAir(300);
				// entityPlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, 600, 0, true, false));
				// entityPlayer.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 600, 0, true, false));
				// reset fall distance so the player wont take extra fall damage once they land.
				entityPlayer.fallDistance = 0;
				// reset fall distance of the choppa because its pased on to the riding entity during the call to fall()
				this.fallDistance = 0;
				entityPlayer.motionY = 0;

			}
			if (this.isCollidedHorizontally) {
				this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 5, true);
				this.setDead();
			}
		}
	}

	public double particleBackOffset = -.6d;
	public double particleSideOffset = 2.3d;
	public double particleTopOffset = 3.9d;
	public double particleBottmOffset = -.3d;

	@Override
	public void doParticleEffects() {
		if (this.currentFuelLevel > 0 && this.riddenByEntity != null && (this.isPlayerAccelerating || this.isPlayerBreaking || this.isPlayerPushingJumpButton || this.isPlayerPushingSprintButton || this.isPlayerTurningLeft || this.isPlayerTurningRight)) {
			for (int i = 0; i < 3; i++) {
				worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + calcTwoOffsetX(particleBackOffset, -90, particleSideOffset), this.posY + particleTopOffset, this.posZ + calcTwoOffsetZ(particleBackOffset, -90, particleSideOffset), 0, 0, 0, 0);
				worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + calcTwoOffsetX(particleBackOffset, -90, particleSideOffset * -1), this.posY + particleTopOffset, this.posZ + calcTwoOffsetZ(particleBackOffset, -90, particleSideOffset * -1), 0, 0, 0, 0);
				worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + calcTwoOffsetX(particleBackOffset, -90, particleSideOffset), this.posY - particleBottmOffset, this.posZ + calcTwoOffsetZ(particleBackOffset, -90, particleSideOffset), 0, 0, 0, 0);
				worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + calcTwoOffsetX(particleBackOffset, -90, particleSideOffset * -1), this.posY - particleBottmOffset, this.posZ + calcTwoOffsetZ(particleBackOffset, -90, particleSideOffset * -1), 0, 0, 0, 0);
			}
		}
	}
}
