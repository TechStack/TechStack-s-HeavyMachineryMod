package com.projectreddog.machinemod.entity;

import com.projectreddog.machinemod.block.BlockMachineMowedGrass;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class EntityLawnmower extends EntityMachineModRideable {
	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public double bladeOffset = 2.0d;

	public EntityLawnmower(World world) {
		super(world);
		setSize(1.5F, 2F);
		SIZE = 9;
		inventory = new ItemStackHandler(SIZE);
		// inventory = new ItemStack[9];
		this.mountedOffsetY = 0.75D;
		this.mountedOffsetX = -0.90d;
		this.mountedOffsetZ = -0.9d;
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
		if (!world.isRemote) {
			// digMethodA();
			BlockPos bp;
			int angle = 0;
			// this will calcl the offset for three positions behind the tractor
			// (3 wide)
			if (this.isPlayerPushingSprintButton) {
				bp = new BlockPos(posX, posY - 1, posZ);
				if (world.getBlockState(bp).getBlock() == Blocks.GRASS) {

					// withProperty(FACING, placer.getHorizontalFacing().getOpposite()
					if (this.yaw < 45 || this.yaw > 315) {
						world.setBlockState(bp, ModBlocks.machinemowedgrass.getDefaultState().withProperty(BlockMachineMowedGrass.FACING, EnumFacing.SOUTH));
					} else if (this.yaw > 45 && this.yaw < 135) {
						world.setBlockState(bp, ModBlocks.machinemowedgrass.getDefaultState().withProperty(BlockMachineMowedGrass.FACING, EnumFacing.WEST));
					} else if (this.yaw > 135 && this.yaw < 225) {
						world.setBlockState(bp, ModBlocks.machinemowedgrass.getDefaultState().withProperty(BlockMachineMowedGrass.FACING, EnumFacing.NORTH));
					} else {
						world.setBlockState(bp, ModBlocks.machinemowedgrass.getDefaultState().withProperty(BlockMachineMowedGrass.FACING, EnumFacing.EAST));
					}
					ItemStack is = new ItemStack(Blocks.TALLGRASS, 1, 1);
					EntityItem entityItem = new EntityItem(world, bp.getX(), bp.getY() + 1, bp.getZ(), is);

					entityItem.forceSpawn = true;
					entityItem.motionX = 0;
					entityItem.motionY = 0;
					entityItem.motionZ = 0;
					world.spawnEntity(entityItem);
				}

			}

		}
	}
}
