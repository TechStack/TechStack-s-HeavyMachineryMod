package com.projectreddog.machinemod.item.components;

import com.projectreddog.machinemod.item.ItemMachineMod;

public class ItemLiquidTankSegment extends ItemMachineMod {
	public String registryName = "liquidtanksegment";

	public ItemLiquidTankSegment() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
