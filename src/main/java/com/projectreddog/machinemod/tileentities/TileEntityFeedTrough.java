package com.projectreddog.machinemod.tileentities;

import java.util.List;

import com.projectreddog.machinemod.block.BlockMachineModFeedTrough;
import com.projectreddog.machinemod.init.ModBlocks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class TileEntityFeedTrough extends TileEntity implements ITickable {

	public AxisAlignedBB boundingBox;

	private int feedSizeHalf = 8;

	public TileEntityFeedTrough() {

	}

	public boolean getPowered() {
		return world.isBlockPowered(this.pos);
	}

	@Override
	public void update() {

		if (!world.isBlockPowered(this.pos)) {

			if (world.getBlockState(pos).getBlock() == ModBlocks.machinefeedtrough) {
				EnumFacing checkDirection = (EnumFacing) world.getBlockState(this.pos).getValue(BlockMachineModFeedTrough.FACING);
				BlockPos bp = this.pos;// this.pos.offset(checkDirection);

				boundingBox = new AxisAlignedBB(this.pos.add(-feedSizeHalf, 0, -feedSizeHalf), this.pos.add(feedSizeHalf, 1, feedSizeHalf));

				List list = world.getEntitiesWithinAABB(EntityAnimal.class, boundingBox);
				processEntitiesInList(list);

			}
		}

	}

	private void processEntitiesInList(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity != null) {
				if (entity instanceof EntityAnimal) {
					EntityAnimal ea = (EntityAnimal) entity;
					if (!ea.isInLove() && ea.getGrowingAge() == 0) {
						ea.setInLove(null);

					}

				}
			}
		}
	}

}
