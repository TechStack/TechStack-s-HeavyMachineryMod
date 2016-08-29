package com.projectreddog.machinemod.entity;

import com.projectreddog.machinemod.block.BlockMachineMowedGrass;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class EntityLawnmower extends EntityMachineModRideable {
	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public double bladeOffset = 2.0d;

	public EntityLawnmower(World world) {
		super(world);
		setSize(1.5F, 2F);
		inventory = new ItemStack[9];
		this.mountedOffsetY = 0.55D;
		this.mountedOffsetX = -0.65d;
		this.mountedOffsetZ = -0.65d;
		this.maxAngle = 0;
		this.minAngle = -60;
		this.droppedItem = ModItems.lawnmower;
		this.shouldSendClientInvetoryUpdates = false;

	}

	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {
			// digMethodA();
			BlockPos bp;
			int angle = 0;
			// this will calcl the offset for three positions behind the tractor
			// (3 wide)
			if (this.isPlayerPushingSprintButton) {
				bp = new BlockPos(posX, posY - 1, posZ);
				if (worldObj.getBlockState(bp).getBlock() == Blocks.GRASS) {

					// withProperty(FACING, placer.getHorizontalFacing().getOpposite()
					if (this.yaw < 45 || this.yaw > 315) {
						worldObj.setBlockState(bp, ModBlocks.machinemowedgrass.getDefaultState().withProperty(BlockMachineMowedGrass.FACING, EnumFacing.SOUTH));
					} else if (this.yaw > 45 && this.yaw < 135) {
						worldObj.setBlockState(bp, ModBlocks.machinemowedgrass.getDefaultState().withProperty(BlockMachineMowedGrass.FACING, EnumFacing.WEST));
					} else if (this.yaw > 135 && this.yaw < 225) {
						worldObj.setBlockState(bp, ModBlocks.machinemowedgrass.getDefaultState().withProperty(BlockMachineMowedGrass.FACING, EnumFacing.NORTH));
					} else {
						worldObj.setBlockState(bp, ModBlocks.machinemowedgrass.getDefaultState().withProperty(BlockMachineMowedGrass.FACING, EnumFacing.EAST));
					}
					ItemStack is = new ItemStack(Blocks.tallgrass, 1, 1);
					EntityItem entityItem = new EntityItem(worldObj, bp.getX(), bp.getY() + 1, bp.getZ(), is);

					entityItem.forceSpawn = true;
					entityItem.motionX = 0;
					entityItem.motionY = 0;
					entityItem.motionZ = 0;
					worldObj.spawnEntityInWorld(entityItem);
				}

			}

		}
	}
}
