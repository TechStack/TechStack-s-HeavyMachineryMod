package com.projectreddog.machinemod.item;

public class ItemBoomArmSegment extends ItemMachineMod {
	public String registryName = "boomarmsegment";

	public ItemBoomArmSegment() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
