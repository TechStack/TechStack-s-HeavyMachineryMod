package com.projectreddog.machinemod.entity;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;

import net.minecraft.block.BlockStone;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class EntityDrillingRig extends EntityMachineModRideable {
	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public double bladeOffset = 2.0d;
	public int currentDepth = 0;

	public EntityDrillingRig(World world) {
		super(world);
		setSize(5.7F, 3F);

		SIZE = 0;
		inventory = new ItemStackHandler(SIZE);
		// inventory = new ItemStack[0];
		this.mountedOffsetY = .75D;
		this.mountedOffsetX = -1D;
		this.mountedOffsetZ = 1.2D;
		this.maxAngle = 170;
		this.minAngle = 0;
		this.ignoreFrustumCheck = true;

		this.droppedItem = ModItems.drillingrig;

	}

	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

	@Override
	public void onUpdate() {

		if (!world.isRemote) {

			if (this.Attribute1 > 89) {
				// drill is extended disable all movement controls
				this.isPlayerTurningLeft = false;
				this.isPlayerTurningRight = false;
				this.isPlayerAccelerating = false;
				this.isPlayerBreaking = false;

			}
		}
		super.onUpdate();
		if (!world.isRemote) {

			if (this.Attribute1 > 90) {
				// drilling arm fully extended so now for every 10 units drill another block
				currentDepth = (int) ((this.Attribute1 - 90) / 5);
				if (currentDepth > 15) {
					// fail safe limit
					currentDepth = 15;
				}

				BlockPos bp = new BlockPos(posX + calcTwoOffsetX(7, 0, 0), posY - currentDepth - 1, posZ + calcTwoOffsetZ(7, 0, 0));
				if (world.getBlockState(bp).getBlock() == Blocks.STONE && world.getBlockState(bp).getBlock().getMetaFromState(world.getBlockState(bp)) == BlockStone.EnumType.STONE.getMetadata()) {
					// world.getBlockState(bp).getBlock().dropBlockAsItem(worldObj, bp, world.getBlockState(bp), 0);
					// world.setBlockToAir(bp);
					world.setBlockState(bp, ModBlocks.machinedrilledstone.getDefaultState());

				} else if (world.getBlockState(bp).getBlock() == Blocks.STONE && world.getBlockState(bp).getBlock().getMetaFromState(world.getBlockState(bp)) == BlockStone.EnumType.ANDESITE.getMetadata()) {
					// world.getBlockState(bp).getBlock().dropBlockAsItem(worldObj, bp, world.getBlockState(bp), 0);
					// world.setBlockToAir(bp);
					world.setBlockState(bp, ModBlocks.machinedrilledandesite.getDefaultState());

				} else if (world.getBlockState(bp).getBlock() == Blocks.STONE && world.getBlockState(bp).getBlock().getMetaFromState(world.getBlockState(bp)) == BlockStone.EnumType.DIORITE.getMetadata()) {
					// world.getBlockState(bp).getBlock().dropBlockAsItem(worldObj, bp, world.getBlockState(bp), 0);
					// world.setBlockToAir(bp);
					world.setBlockState(bp, ModBlocks.machinedrilleddiorite.getDefaultState());

				} else if (world.getBlockState(bp).getBlock() == Blocks.STONE && world.getBlockState(bp).getBlock().getMetaFromState(world.getBlockState(bp)) == BlockStone.EnumType.GRANITE.getMetadata()) {
					// world.getBlockState(bp).getBlock().dropBlockAsItem(worldObj, bp, world.getBlockState(bp), 0);
					// world.setBlockToAir(bp);
					world.setBlockState(bp, ModBlocks.machinedrilledgranite.getDefaultState());

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

}
