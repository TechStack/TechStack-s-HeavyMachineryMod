package com.projectreddog.machinemod.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.projectreddog.machinemod.init.ModItems;

public class EntityDrillingRig extends EntityMachineModRideable {

	public double bladeOffset = 2.0d;

	public EntityDrillingRig(World world) {
		super(world);
		setSize(5.7F, 3F);
		inventory = new ItemStack[0];
		this.mountedOffsetY = .5D;
		this.mountedOffsetX = 3D;
		this.mountedOffsetZ = 3D;
		this.maxAngle = 90;
		this.minAngle = 0;
		this.droppedItem = ModItems.drillingrig;

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {

		}
	}

}
