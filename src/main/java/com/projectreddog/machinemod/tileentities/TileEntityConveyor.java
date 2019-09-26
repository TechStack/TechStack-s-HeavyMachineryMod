package com.projectreddog.machinemod.tileentities;

import java.util.List;

import com.projectreddog.machinemod.block.BlockMachineModConveyor;
import com.projectreddog.machinemod.init.ModBlocks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class TileEntityConveyor extends TileEntity implements ITickable {

	public AxisAlignedBB boundingBox;
	public final double MoveSpeed = .1d;

	public TileEntityConveyor() {

	}

	public boolean getPowered() {
		return world.isBlockPowered(this.pos);
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

		if (!world.isBlockPowered(this.pos)) {

			if (world.getBlockState(pos).getBlock() == ModBlocks.machineconveyor) {
				if (BlockMachineModConveyor.shouldLift(world, this.pos)) {
					EnumFacing checkDirection = (EnumFacing) world.getBlockState(this.pos).getValue(BlockMachineModConveyor.FACING);
					BlockPos bp = this.pos;// this.pos.offset(checkDirection);
					BlockPos bp2 = this.pos.up().add(1, 1, 1);
					BlockPos temp;
					double xOffset = 0, zOffset = 0;
					if (checkDirection == EnumFacing.EAST) {
						bp = bp.west();
						// working
					} else if (checkDirection == EnumFacing.WEST) {
						bp2 = bp2.east();
					} else if (checkDirection == EnumFacing.NORTH) {
						bp2 = bp2.south();
						// not working :(
					} else if (checkDirection == EnumFacing.SOUTH) {
						bp = bp.north();
						// works naturally
					}

					boundingBox = new AxisAlignedBB(bp, bp2);

					// LogHelper.info("Block at" + this.pos + "pos1" + bp + "POS2" + bp2 + "EF" + checkDirection);
				} else {
					boundingBox = new AxisAlignedBB(this.pos.up(), this.pos.up().add(1, 1, 1));
				}
				List list = world.getEntitiesWithinAABB(EntityItem.class, boundingBox);
				processEntitiesInList(list);

				list = world.getEntitiesWithinAABB(EntityLivingBase.class, boundingBox);
				processEntitiesInList(list);

				list = world.getEntitiesWithinAABB(EntityXPOrb.class, boundingBox);
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
				EnumFacing ef = (EnumFacing) world.getBlockState(this.pos).getValue(BlockMachineModConveyor.FACING);
				double ySave = entity.motionY;
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

				if (BlockMachineModConveyor.shouldLift(world, this.pos)) {
					// Should lift maybe?
					if (new BlockPos(entity.posX, entity.posY, entity.posZ).getX() == this.pos.getX() && new BlockPos(entity.posX, entity.posY, entity.posZ).getZ() == this.pos.getZ()) {
						// entity in same pos no Y adustment
						// LogHelper.info("ent Y" + entity.posY + " bock pos " + this.pos.getY());
						y = ySave;
					} else {
						// LogHelper.info("ent Y" + entity.posY + " bock pos " + this.pos.getY());

						if (entity.posY < this.pos.getY() + 1) {
							// LogHelper.info("ent Y" + entity.posY + " bock pos " + this.pos.getY());
							entity.fallDistance = 0; // set so no damage to players going up long virt shafts
							entity.motionY = 0;
							y = MoveSpeed;
						} else {

						}
					}

				} else {
					y = ySave;

				}

				entity.move(MoverType.SELF, x, y, z);
				// entity.setPosition(entity.getPosition().getX() + 0.1d, entity.getPosition().getY(), entity.getPosition().getZ());
				// }
			}
		}
	}

}
