package com.projectreddog.machinemod.tileentities;

import java.util.List;

import com.projectreddog.machinemod.init.ModBlocks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityLauncher extends TileEntity implements ITickable {

	public AxisAlignedBB boundingBox;
	public final double MoveSpeed = 20d;

	public TileEntityLauncher() {

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

		if (world.isBlockPowered(this.pos)) {

			if (world.getBlockState(pos).getBlock() == ModBlocks.machinelauncher) {
				boundingBox = new AxisAlignedBB(pos, pos.up(2).east().south());

				List list = world.getEntitiesWithinAABB(EntityItem.class, boundingBox);
				processEntitiesInList(list);
				list = world.getEntitiesWithinAABB(EntityLivingBase.class, boundingBox);
				processEntitiesInList(list);

				// list = world.getEntitiesWithinAABB(EntityXPOrb.class, boundingBox);
				// processEntitiesInList(list);

			}
		}

	}

	private void processEntitiesInList(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity != null) {

				double x = 0, y = 0, z = 0;
				x = entity.posX;
				y = entity.posY;
				z = 0;

				entity.motionY = 0;
				y = y + MoveSpeed;

				entity.move(MoverType.PISTON, x, y, z);

			}
		}
	}

}
