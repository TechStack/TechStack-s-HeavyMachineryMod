package com.projectreddog.machinemod.entity;

import com.projectreddog.machinemod.init.ModItems;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class EntitySub extends EntityMachineModRideable {
	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public double bladeOffset = 2.0d;

	public EntitySub(World world) {
		super(world);
		setSize(2.5F, 4F);
		SIZE = 9;
		inventory = new ItemStackHandler(SIZE);
		// inventory = new ItemStack[9];
		this.mountedOffsetY = 0.35D;
		this.mountedOffsetX = 1.3d;
		this.mountedOffsetZ = 1.3d;
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
		if (!world.isRemote) {

			if (this.currentFuelLevel > 0 && isPlayerPushingSprintButton) {
				this.motionY -= 0.04D;
			}
			if (this.currentFuelLevel > 0 && isPlayerPushingJumpButton) {
				if (world.getBlockState(new BlockPos((int) (posX - .5d), (int) posY, (int) (posZ - .5d))).getBlock().getMaterial(world.getBlockState(new BlockPos((int) (posX - .5d), (int) posY, (int) (posZ - .5d)))) == Material.WATER) {
					this.motionY += 0.04D;
				}
			}

			if (this.currentFuelLevel > 0 && this.getControllingPassenger() != null && this.getControllingPassenger() instanceof PlayerEntity) {
				PlayerEntity PlayerEntity = (PlayerEntity) this.getControllingPassenger();
				PlayerEntity.setAir(300);
				PlayerEntity.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("night_vision"), 600, 0, true, false));
				PlayerEntity.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("water_breathing"), 600, 0, true, false));

			}

		}
	}

	public double particleBackOffset = -1.5d;
	public double particleSideOffset = 1.2d;
	public double particleTopOffset = 1.5d;
	// public double particleBottmOffset = -.3d;

	@Override
	public void doParticleEffects() {
		if (this.currentFuelLevel > 0 && this.getControllingPassenger() != null && (this.isPlayerAccelerating || this.isPlayerBreaking || this.isPlayerPushingJumpButton || this.isPlayerPushingSprintButton || this.isPlayerTurningLeft || this.isPlayerTurningRight)) {
			for (int i = 0; i < 3; i++) {
				world.spawnParticle(ParticleTypes.WATER_BUBBLE, this.posX + calcTwoOffsetX(particleBackOffset, -90, particleSideOffset), this.posY + particleTopOffset, this.posZ + calcTwoOffsetZ(particleBackOffset, -90, particleSideOffset), 0, 0, 0, 0);
				world.spawnParticle(ParticleTypes.WATER_BUBBLE, this.posX + calcTwoOffsetX(particleBackOffset, -90, particleSideOffset * -1), this.posY + particleTopOffset, this.posZ + calcTwoOffsetZ(particleBackOffset, -90, particleSideOffset * -1), 0, 0, 0, 0);

			}
		}
	}
}
