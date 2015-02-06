package com.projectreddog.machinemod.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.network.MachineModMessageEntityCurrentTargetPosToClient;
import com.projectreddog.machinemod.utility.LogHelper;

public class EntityExcavator extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
	public BlockPos targetBlockPos;
	public double currPosX;
	public double currPosY;
	public double currPosZ;
	public double mainBodyRotation = 0;

	public EntityExcavator(World world) {
		super(world);

		setSize(2f, 1f);
		inventory = new ItemStack[9];

		this.mountedOffsetY = .2D;
		this.mountedOffsetX = 0D;
		this.mountedOffsetZ = 0D;
		this.maxAngle = 256;
		this.minAngle = 0;
		this.droppedItem = ModItems.crane;
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

			// server move bucket towards target and send it's new pos to the client via network
			if (targetBlockPos != null) {
				// + .5d for center of block
				//
				if (currPosX > targetBlockPos.getX() + .5d) {
					currPosX -= .05d;
				} else if (currPosX < targetBlockPos.getX() + .5d) {
					currPosX += .05d;
				}
				if (currPosY > targetBlockPos.getY() + .5d) {
					currPosY -= .05d;
				} else if (currPosY < targetBlockPos.getY() + .5d) {
					currPosY += .05d;
				}
				if (currPosZ > targetBlockPos.getZ() + .5d) {
					currPosZ -= .05d;
				} else if (currPosZ < targetBlockPos.getZ() + .5d) {
					currPosZ += .05d;
				}

				if (currPosX - targetBlockPos.getX() < .55d && currPosX - targetBlockPos.getX() > 0) {
					currPosX = targetBlockPos.getX() + .5d;
				} else if (currPosX - targetBlockPos.getX() < -.55d && currPosX - targetBlockPos.getX() < 0) {
					currPosX = targetBlockPos.getX() + .5d;
				}
				if (currPosY - targetBlockPos.getY() < .55d && currPosY - targetBlockPos.getY() > 0) {
					currPosY = targetBlockPos.getY() + .5d;
				} else if (currPosY - targetBlockPos.getY() + .5d < -.05d && currPosY - targetBlockPos.getY() < 0) {
					currPosY = targetBlockPos.getY() + .5d;
				}
				if (currPosZ - targetBlockPos.getZ() < .55d && currPosZ - targetBlockPos.getZ() > 0) {
					currPosZ = targetBlockPos.getZ() + .5d;
				} else if (currPosZ - targetBlockPos.getZ() < -.55d && currPosZ - targetBlockPos.getZ() < 0) {
					currPosZ = targetBlockPos.getZ() + .5d;
				}

				ModNetwork.sendPacketToAllAround((new MachineModMessageEntityCurrentTargetPosToClient(this.getEntityId(), this.currPosX, this.currPosY, this.currPosZ)), new TargetPoint(worldObj.provider.getDimensionId(), posX, posY, posZ, 80));

				if (this.isPlayerPushingSprintButton) {
					// player wants to break the block
					BlockPos bp;
					bp = new BlockPos(currPosX, currPosY, currPosZ);

					// TODO : need to BreakEvent
					worldObj.getBlockState(bp).getBlock().dropBlockAsItem(worldObj, bp, worldObj.getBlockState(bp), 0);
					worldObj.setBlockToAir(bp);
				}
			} else {
				// null target probably because it was just placed so set the current pos close by

				// TODO need to set this to an offset that will be the "Default " home pos for the arm
				currPosX = this.posX;
				currPosY = this.posY;
				currPosZ = this.posZ;
			}
			// }
		} else {
			// move client bucket based on client smoothing logic???
			if (targetBlockPos != null) {

				// // do calc of angel in this client side update so its 20 per second instead of 120 (if fps is 120 per second) should help avoid fps hit
				// double dX = this.posX - currPosX;
				// double dZ = this.posZ - currPosZ;
				// // * 180 / Math.PI if need to convert from raidians to degrees i believe it returns degrees after
				// // inital testing orig Z over X
				// this.mainBodyRotation = (Math.atan(dX / dZ) * 180 / Math.PI);
				// LogHelper.info("Rotation vlaue:" + this.mainBodyRotation + " " + currPosX + " " + currPosZ);
				//
				// // clamp to 360 for my sanity
				// if (this.mainBodyRotation > 360) {
				// this.mainBodyRotation = 360 - this.mainBodyRotation;
				// } else if (this.mainBodyRotation < 0) {
				// this.mainBodyRotation = 360 + this.mainBodyRotation;
				// }
				// x = currPos
				// x0 = pos

				double l = currPosX - this.posX;
				double w = currPosZ - this.posZ;
				double c = Math.sqrt(l * l + w * w);
				double alpha1 = (Math.asin(l / c) * -1) / Math.PI * 180;
				double alpha2 = (Math.acos(w / c)) / Math.PI * 180;
				if (alpha2 > 90) {
					this.mainBodyRotation = 180 - alpha1;
				} else {
					this.mainBodyRotation = alpha1;
				}
				if (this.mainBodyRotation > 360) {
					this.mainBodyRotation = 360 - this.mainBodyRotation;
				} else if (this.mainBodyRotation < 0) {
					this.mainBodyRotation = 360 + this.mainBodyRotation;
				}
				LogHelper.info("Rotation vlaue:" + this.mainBodyRotation + " " + currPosX + " " + currPosZ);
			}

		}

	}

	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

}