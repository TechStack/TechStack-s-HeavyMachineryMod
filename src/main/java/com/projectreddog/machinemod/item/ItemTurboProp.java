package com.projectreddog.machinemod.item;

public class ItemTurboProp extends ItemMachineMod {
	public String registryName = "turboprop";

	public ItemTurboProp() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
