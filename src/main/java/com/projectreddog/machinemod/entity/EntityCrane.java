package com.projectreddog.machinemod.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import com.projectreddog.machinemod.init.ModItems;

public class EntityCrane extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public EntityCrane(World world) {
		super(world);

		setSize(9f, 24f);
		inventory = new ItemStack[9];

		this.mountedOffsetY = 0.6D;
		this.mountedOffsetX = 0.4D;
		this.mountedOffsetZ = 0.4D;
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

			BlockPos bp;
			bp = new BlockPos(posX + calcTwoOffsetX(10, 0, 0), posY - ((int) this.Attribute1), posZ + calcTwoOffsetZ(10, 0, 0));
			if (worldObj.getBlockState(bp).getBlock().getBlockHardness(worldObj, bp) < 100) {
				// TODO : need to BreakEvent
				worldObj.getBlockState(bp).getBlock().dropBlockAsItem(worldObj, bp, worldObj.getBlockState(bp), 0);
				worldObj.setBlockToAir(bp);
			}

			// }
		}

	}

	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

}
