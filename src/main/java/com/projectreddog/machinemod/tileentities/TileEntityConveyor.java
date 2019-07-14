package com.projectreddog.machinemod.tileentities;

import java.util.List;

import com.projectreddog.machinemod.block.BlockMachineModConveyor;
import com.projectreddog.machinemod.init.ModBlocks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class TileEntityConveyor extends TileEntity implements ITickableTileEntity {

	public AxisAlignedBB boundingBox;
	public final double MoveSpeed = .1d;

	public TileEntityConveyor() {

	}

	public boolean getPowered() {
		return world.isBlockPowered(this.pos);
	}

	@Override
	public void tick() {

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
					Direction checkDirection = (Direction) world.getBlockState(this.pos).getValue(BlockMachineModConveyor.FACING);
					BlockPos bp = this.pos;// this.pos.offset(checkDirection);
					BlockPos bp2 = this.pos.up().add(1, 1, 1);
					BlockPos temp;
					double xOffset = 0, zOffset = 0;
					if (checkDirection == Direction.EAST) {
						bp = bp.west();
						// working
					} else if (checkDirection == Direction.WEST) {
						bp2 = bp2.east();
					} else if (checkDirection == Direction.NORTH) {
						bp2 = bp2.south();
						// not working :(
					} else if (checkDirection == Direction.SOUTH) {
						bp = bp.north();
						// works naturally
					}

					boundingBox = new AxisAlignedBB(bp, bp2);

					// LogHelper.info("Block at" + this.pos + "pos1" + bp + "POS2" + bp2 + "EF" + checkDirection);
				} else {
					boundingBox = new AxisAlignedBB(this.pos.up(), this.pos.up().add(1, 1, 1));
				}
				List list = world.getEntitiesWithinAABB(ItemEntity.class, boundingBox);
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
				Direction ef = (Direction) world.getBlockState(this.pos).getValue(BlockMachineModConveyor.FACING);
				double x = 0, y = 0, z = 0;
				if (ef == Direction.EAST) {
					x = MoveSpeed;
					z = 0;
				} else if (ef == Direction.WEST) {
					x = -MoveSpeed;
					z = 0;
				} else if (ef == Direction.NORTH) {
					x = 0;
					z = -MoveSpeed;
				} else if (ef == Direction.SOUTH) {
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

					} else {
						// LogHelper.info("ent Y" + entity.posY + " bock pos " + this.pos.getY());

						if (entity.posY < this.pos.getY() + 1) {
							// LogHelper.info("ent Y" + entity.posY + " bock pos " + this.pos.getY());
							entity.fallDistance = 0; // set so no damage to players going up long virt shafts
							entity.motionY = 0;
							y = MoveSpeed;
						}
					}

				}

				entity.move(MoverType.SELF, x, y, z);
				// entity.setPosition(entity.getPosition().getX() + 0.1d, entity.getPosition().getY(), entity.getPosition().getZ());
				// }
			}
		}
	}

}
