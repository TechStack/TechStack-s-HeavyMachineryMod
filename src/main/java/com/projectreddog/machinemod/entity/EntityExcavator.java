package com.projectreddog.machinemod.entity;

import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.network.MachineModMessageEntityCurrentTargetPosToClient;
import com.projectreddog.machinemod.utility.BlockUtil;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class EntityExcavator extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
	public static float AmrLength = 3.1f;
	public static float armPiviotForward = 1.4f;
	public static float armPiviotUp = -0f;

	public BlockPos targetBlockPos;
	public double currPosX;
	public double currPosY;
	public double currPosZ;
	public double mainBodyRotation = 0;

	public double angleArm1 = 0;
	public double angleArm2 = 0;

	public double angleArm3 = 0;

	public double armSpeed = .1d;

	public EntityExcavator(World world) {
		super(world);

		setSize(4f, 2f);
		inventory = new ItemStack[9];

		this.mountedOffsetY = .5D;
		this.mountedOffsetX = -1.5D;
		this.mountedOffsetZ = 2D;
		this.maxAngle = 256;
		this.minAngle = 0;
		this.droppedItem = ModItems.excavator;
		currPosX = this.posX;
		currPosY = this.posY;
		currPosZ = this.posZ;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {
			// if (this.Attribute1 == this.getMaxAngle()) {
			// bucket Down
			// break blocks first
			if (currentFuelLevel > 0) {
				// server move bucket towards target and send it's new pos to the client via network
				if (this.isPlayerPushingSegment1Down) {
					this.angleArm1--;
				}

				if (this.isPlayerPushingSegment1Up) {
					this.angleArm1++;
				}

				if (this.isPlayerPushingSegment2Down) {
					this.angleArm2--;
				}
				if (this.isPlayerPushingSegment2Up) {
					this.angleArm2++;
				}
				if (this.isPlayerPushingSegment3Down) {
					this.angleArm3--;
				}
				if (this.isPlayerPushingSegment3Up) {
					this.angleArm3++;
				}

				if (this.isPlayerPushingTurretLeft) {
					mainBodyRotation++;
				}
				if (this.isPlayerPushingTurretRight) {
					mainBodyRotation--;
				}
				ModNetwork.sendPacketToAllAround((new MachineModMessageEntityCurrentTargetPosToClient(this.getEntityId(), this.currPosX, this.currPosY, this.currPosZ, this.angleArm1, this.angleArm2, this.angleArm3, this.mainBodyRotation)), new TargetPoint(worldObj.provider.getDimension(), posX, posY, posZ, 80));
				if (this.isPlayerPushingSprintButton) {
					// player wants to break the block
					BlockPos bp;
					bp = new BlockPos(currPosX, currPosY, currPosZ);

					BlockUtil.BreakBlock(worldObj, bp, this.getControllingPassenger());

				}
			}

		}

	}

	@Override
	public double getMountedXOffset() {
		// should be overridden in extended class if not default;

		return calcTwoOffsetX(this.mountedOffsetZ, 90, this.mountedOffsetX);
		// return calcOffsetX(mountedOffsetX);
	}

	@Override
	public double getMountedZOffset() {
		// should be overridden in extended class if not default;

		return calcTwoOffsetZ(this.mountedOffsetZ, 90, this.mountedOffsetX);
		// return calcOffsetX(mountedOffsetX);
	}

	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

}
