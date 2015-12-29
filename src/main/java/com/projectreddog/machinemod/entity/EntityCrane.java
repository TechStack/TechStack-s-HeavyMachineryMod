package com.projectreddog.machinemod.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.utility.BlockUtil;

public class EntityCrane extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

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
		super.onUpdate();
		if (!worldObj.isRemote) {
			// if (this.Attribute1 == this.getMaxAngle()) {
			// bucket Down
			// break blocks first
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
						if (worldObj.getBlockState(bp).getBlock().getBlockHardness(worldObj, bp) < 100) {
							BlockUtil.BreakBlock(worldObj, bp, this.riddenByEntity);

						}
					}
				}

			}
			// }
		}

	}

	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

}
