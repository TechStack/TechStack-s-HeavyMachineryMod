package com.projectreddog.machinemod.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.projectreddog.machinemod.init.ModItems;

public class EntityTank extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public EntityTank(World world) {
		super(world);

		setSize(2.8f, 2.5f);
		inventory = new ItemStack[1];

		this.mountedOffsetY = 0.6D;
		this.mountedOffsetX = 0.4D;
		this.mountedOffsetZ = 0.4D;
		this.maxAngle = 0;
		this.minAngle = -90;
		this.droppedItem = ModItems.tank;
		this.shouldSendClientInvetoryUpdates = true;

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {

		}

	}

	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

}
