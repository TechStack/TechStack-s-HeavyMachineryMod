package com.projectreddog.machinemod.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerLaserLevel extends Container {

	public ContainerLaserLevel(InventoryPlayer inventoryPlayer) {

	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

}
