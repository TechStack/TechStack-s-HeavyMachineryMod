package com.projectreddog.machinemod.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.utility.LogHelper;

public class EntityCrane extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
	private float previousYaw;
	private float fakeMomentum = 0;
	private int ballDirection = 0; // 0 == no direction 1 = (+ attribute) 2 = ( - attribute )
	private int previousBallDirection = 0;

	public EntityCrane(World world) {
		super(world);

		setSize(9f, 24f);
		inventory = new ItemStack[9];

		this.mountedOffsetY = .2D;
		this.mountedOffsetX = 5D;
		this.mountedOffsetZ = 5D;
		this.maxAngle = 256;
		this.minAngle = 0;
		this.droppedItem = ModItems.crane;

	}

	@Override
	public void onUpdate() {

		if (!worldObj.isRemote) {

			previousYaw = this.yaw;
			previousBallDirection = this.ballDirection;
			super.onUpdate();
			if (this.yaw == previousYaw) {
				// not moving reset momentum
				this.fakeMomentum = 0;
				LogHelper.info("fakeMomentomrest due to no movment");
				ballDirection = 0;
			} else if (this.yaw < previousYaw) {
				// going - direction
				ballDirection = 2;
			} else if (this.yaw > previousYaw) {
				// going + direction
				ballDirection = 1;
			}
			if (ballDirection != 0) {
				if (ballDirection == previousBallDirection) {
					// increase momentum we are headed in the same direction
					fakeMomentum = fakeMomentum + .1f;
				}
			} else {
				fakeMomentum = 0;
				LogHelper.info("fakeMomentomrest due to no direction");
			}

			// if (this.Attribute1 == this.getMaxAngle()) {
			// bucket Down
			// break blocks first

			if (fakeMomentum > 0) {
				int angle;
				for (int i = -1; i < 2; i++) {
					for (int j = -1; j < 2; j++) {
						for (int k = -1; k < 2; k++) {
							if (i == 0) {
								angle = 0;
							} else {
								angle = 90;
							}
							BlockPos bp;
							bp = new BlockPos(posX + calcTwoOffsetX(10 + j, angle, i), posY + k + 26 - ((int) this.Attribute1), posZ + calcTwoOffsetZ(10 + j, angle, i));
							if (!worldObj.isAirBlock(bp)) {

								// worldObj.getBlockState(bp).getBlock().dropBlockAsItem(worldObj, bp, worldObj.getBlockState(bp), 0);
								// worldObj.setBlockToAir(bp);
								if (fakeMomentum > 0.2) {

									worldObj.newExplosion(this.riddenByEntity, bp.getX(), bp.getY(), bp.getZ(), fakeMomentum, false, true);
									LogHelper.info("HIT ! fake momentum was " + fakeMomentum);
								}

								fakeMomentum = 0;
								ballDirection = 0;
								previousBallDirection = ballDirection;
								return;// only 1 explosion

							}
						}
					}

				}
			}
			// }
		} else {
			// client
			super.onUpdate();

		}

	}

	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

}
