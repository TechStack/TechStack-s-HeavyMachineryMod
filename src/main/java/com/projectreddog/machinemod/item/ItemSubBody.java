package com.projectreddog.machinemod.item;

public class ItemSubBody extends ItemMachineMod {
	public String registryName = "subbody";

	public ItemSubBody() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
