package com.projectreddog.machinemod.entity;

import com.projectreddog.machinemod.init.ModItems;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntitySub extends EntityMachineModRideable {
	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public double bladeOffset = 2.0d;

	public EntitySub(World world) {
		super(world);
		setSize(2.5F, 4F);
		inventory = new ItemStack[9];
		this.mountedOffsetY = 0.35D;
		this.mountedOffsetX = 2d;
		this.mountedOffsetZ = 2d;
		this.maxAngle = 0;
		this.minAngle = -60;
		this.droppedItem = ModItems.sub;
		this.shouldSendClientInvetoryUpdates = false;
		this.willSink = false;
		this.maxSpeed = .4d;
		this.isWaterOnly = true;
		this.nextParticleAtTick = 5;

	}

	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {

			if (this.currentFuelLevel > 0 && isPlayerPushingSprintButton) {
				this.motionY -= 0.04D;
			}
			if (this.currentFuelLevel > 0 && isPlayerPushingJumpButton) {
				if (worldObj.getBlockState(new BlockPos((int) (posX - .5d), (int) posY, (int) (posZ - .5d))).getBlock().getMaterial() == Material.water) {
					this.motionY += 0.04D;
				}
			}

			if (this.currentFuelLevel > 0 && this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer) {
				EntityPlayer entityPlayer = (EntityPlayer) this.riddenByEntity;
				entityPlayer.setAir(300);
				entityPlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, 600, 0, true, false));
				entityPlayer.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 600, 0, true, false));

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
