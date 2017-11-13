package com.projectreddog.machinemod.item;

public class ItemSteelIngot extends ItemMachineMod {
	public String registryName = "steelingot";

	public ItemSteelIngot() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
