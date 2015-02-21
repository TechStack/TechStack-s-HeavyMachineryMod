package com.projectreddog.machinemod.tileentities;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

import com.projectreddog.machinemod.block.BlockMachineModConveyor;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.utility.LogHelper;

public class TileEntityConveyor extends TileEntity implements IUpdatePlayerListBox {

	public AxisAlignedBB boundingBox;
	public final double MoveSpeed = .1d;

	public TileEntityConveyor() {

	}

	@Override
	public void update() {

		// MAJOR WIP need to handle other entities
		// need to take initial velocity of the entity into account
		// need to change bounding box to not use int from block pos and instead
		// use the double version of it instead
		// could cache the bounding box also because it shouldn't change over
		// time unless the block is broken & moved.
		// need to make processEntitiesInList method take the enum facing
		// property of the block
		// need to add the block state's enum facing to this block so it can be
		// rotated.

		if (!worldObj.isBlockPowered(this.pos)) {

			if (worldObj.getBlockState(pos).getBlock() == ModBlocks.machineconveyor) {
				if (BlockMachineModConveyor.shouldLift(worldObj, this.pos)) {
					EnumFacing checkDirection = (EnumFacing) worldObj.getBlockState(this.pos).getValue(BlockMachineModConveyor.FACING);
					BlockPos bp = this.pos;// this.pos.offset(checkDirection);
					BlockPos bp2 = this.pos.offsetUp().add(1, 1, 1);
					BlockPos temp;
					double xOffset = 0, zOffset = 0;
					if (checkDirection == EnumFacing.EAST) {
						bp = bp.offsetWest();
						// working
					} else if (checkDirection == EnumFacing.WEST) {
						bp2 = bp2.offsetEast();
					} else if (checkDirection == EnumFacing.NORTH) {
						bp2 = bp2.offsetSouth();
						// not working :(
					} else if (checkDirection == EnumFacing.SOUTH) {
						bp = bp.offsetNorth();
						// works naturally
					}

					boundingBox = new AxisAlignedBB(bp, bp2);

					// LogHelper.info("Block at" + this.pos + "pos1" + bp + "POS2" + bp2 + "EF" + checkDirection);
				} else {
					boundingBox = new AxisAlignedBB(this.pos.offsetUp(), this.pos.offsetUp().add(1, 1, 1));
				}
				List list = worldObj.getEntitiesWithinAABB(EntityItem.class, boundingBox);
				processEntitiesInList(list);

				list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, boundingBox);
				processEntitiesInList(list);
			}
		}

	}

	private void processEntitiesInList(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity != null) {
				// LogHelper.info("ent in bounds");

				// if (entity instanceof EntityLivingBase) {
				// ((EntityLivingBase) entity).moveEntity(.1d, 0, 0);
				// } else {
				EnumFacing ef = (EnumFacing) worldObj.getBlockState(this.pos).getValue(BlockMachineModConveyor.FACING);
				double x = 0, y = 0, z = 0;
				if (ef == EnumFacing.EAST) {
					x = MoveSpeed;
					z = 0;
				} else if (ef == EnumFacing.WEST) {
					x = -MoveSpeed;
					z = 0;
				} else if (ef == EnumFacing.NORTH) {
					x = 0;
					z = -MoveSpeed;
				} else if (ef == EnumFacing.SOUTH) {
					x = 0;
					z = MoveSpeed;
				} else {
					// err so no moment to prevent err?
					x = 0;
					z = 0;
				}

				if (BlockMachineModConveyor.shouldLift(worldObj, this.pos)) {
					// Should lift maybe?
					if (new BlockPos(entity.posX, entity.posY, entity.posZ).getX() == this.pos.getX() && new BlockPos(entity.posX, entity.posY, entity.posZ).getZ() == this.pos.getZ()) {
						// entity in same pos no Y adustment
						// LogHelper.info("ent Y" + entity.posY + " bock pos " + this.pos.getY());

					} else {
						// LogHelper.info("ent Y" + entity.posY + " bock pos " + this.pos.getY());

						if (entity.posY < this.pos.getY() + 1) {
							LogHelper.info("ent Y" + entity.posY + " bock pos " + this.pos.getY());
							entity.fallDistance = 0; // set so no damage to players going up long virt shafts
							entity.motionY = 0;
							y = MoveSpeed;
						}
					}

				}

				entity.moveEntity(x, y, z);
				// entity.setPosition(entity.getPosition().getX() + 0.1d, entity.getPosition().getY(), entity.getPosition().getZ());
				// }
			}
		}
	}
}
