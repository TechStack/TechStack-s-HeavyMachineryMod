package com.projectreddog.machinemod.item;

public class ItemTrackSegment extends ItemMachineMod {
	public String registryName = "tracksegment";

	public ItemTrackSegment() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

}
