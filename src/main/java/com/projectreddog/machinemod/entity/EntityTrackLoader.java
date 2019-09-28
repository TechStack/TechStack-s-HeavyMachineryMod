package com.projectreddog.machinemod.entity;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.utility.BlockUtil;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class EntityTrackLoader extends EntityMachineModRideable {
	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public double bladeOffset = 2.0d;

	public EntityTrackLoader(World world) {
		super(world);
		setSize(3.5F, 2.5F);
		// inventory = new ItemStack[0];
		SIZE = 9;
		inventory = new ItemStackHandler(SIZE);
		this.mountedOffsetY = .87D;
		this.mountedOffsetX = -.75D;
		this.mountedOffsetZ = -.75D;
		this.maxAngle = 15;
		this.minAngle = -15;
		this.droppedItem = ModItems.trackloader;
		this.shouldSendClientInvetoryUpdates = true;

	}

	public void doParticleEffects() {
		if (this.currentFuelLevel > 0 && this.getControllingPassenger() != null) {
			world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + calcTwoOffsetX(1, -90, .4), this.posY + 3.9, this.posZ + calcTwoOffsetZ(1, -90, .4), 0, 0, 0, 0);
		}
	}

	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

	@Override
	public void onUpdate() {
		// 3 back break when theeth are down
		// 4 forward when bucket is down.
		super.onUpdate();
		if (!world.isRemote) {
			int bucketOffsetY = 0;
			int bladeOffsetY = 0;

			if (this.Attribute1 > 7) {
				bucketOffsetY = -1;
			} else if (this.Attribute1 < -7) {
				bucketOffsetY = 1;
			} else if (this.Attribute1 < -8) {
				bladeOffsetY = -1;
			}

			// bucket Down
			// break blocks first
			int angle;
			for (int j = 0; j < 2; j++) {
				for (int i = -2; i < 3; i++) {
					if (i == 0) {
						angle = 0;
					} else {
						angle = 90;
					}
					BlockPos bp;
					bp = new BlockPos(posX + calcTwoOffsetX(5, angle, i), posY + j + bucketOffsetY, posZ + calcTwoOffsetZ(5, angle, i));
					if (!world.isAirBlock(bp) && world.getBlockState(bp).getBlock() != Blocks.BEDROCK && world.getBlockState(bp).getMaterial() != Material.WATER && world.getBlockState(bp).getMaterial() != Material.LAVA && world.getBlockState(bp).getBlock() != ModBlocks.machinebleakportal && world.getBlockState(bp).getBlock() != ModBlocks.machinebleakportalframe) {
						BlockUtil.BreakBlock(world, bp, this.getControllingPassenger());

					}
					toppleTree(bp, 0, 0, world.getBlockState(bp).getBlock());

				}
			}

		}
	}

	public int BladePos1X;
	public int BladePos1Z;
	public int BladePos2X;
	public int BladePos2Z;
	public int BladePos3X;
	public int BladePos3Z;

	public void setBladePosFromYaw() {
		if ((yaw >= 0 && yaw < 23) || yaw > 337) {
			BladePos1X = (int) Math.round(posX);
			BladePos1Z = (int) Math.round(posZ + bladeOffset);
			BladePos2X = (int) Math.round(posX + 1);
			BladePos2Z = (int) Math.round(posZ + bladeOffset);
			BladePos3X = (int) Math.round(posX - 1);
			BladePos3Z = (int) Math.round(posZ + bladeOffset);
		} else if (yaw >= 23 && yaw < 69) {

			BladePos1X = (int) Math.round(posX + bladeOffset);
			BladePos1Z = (int) Math.round(posZ + bladeOffset);
			BladePos2X = (int) Math.round(posX + bladeOffset) + 1;
			BladePos2Z = (int) Math.round(posZ + bladeOffset) + 1;
			BladePos3X = (int) Math.round(posX + bladeOffset - 1);
			BladePos3Z = (int) Math.round(posZ + bladeOffset) - 1;

		}
	}

}
