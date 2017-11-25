package com.projectreddog.machinemod.item;

public class ItemGraderBlade extends ItemMachineMod {
	public String registryName = "graderblade";

	public ItemGraderBlade() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
