package com.projectreddog.machinemod.tileentities;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityConveyor extends TileEntity implements IUpdatePlayerListBox {

	public AxisAlignedBB boundingBox;

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
		boundingBox = new AxisAlignedBB(this.pos.offsetUp(), this.pos.offsetUp().add(1, 1, 1));
		List list = worldObj.getEntitiesWithinAABB(EntityItem.class, boundingBox);
		processEntitiesInList(list);
		boundingBox = new AxisAlignedBB(this.pos.offsetUp(), this.pos.offsetUp().add(1, 1, 1));
		list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, boundingBox);
		processEntitiesInList(list);

	}

	private void processEntitiesInList(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity != null) {
				if (entity instanceof EntityLivingBase) {
					((EntityLivingBase) entity).moveEntity(.1d, 0, 0);
				} else {

					entity.moveEntity(.1d, 0, 0);
					// entity.setPosition(entity.getPosition().getX() + 0.1d, entity.getPosition().getY(), entity.getPosition().getZ());
				}
			}
		}
	}

}
