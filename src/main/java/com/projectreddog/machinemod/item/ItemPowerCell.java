package com.projectreddog.machinemod.item;

public class ItemPowerCell extends ItemMachineMod {
	public String registryName = "powercell";

	public ItemPowerCell() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
