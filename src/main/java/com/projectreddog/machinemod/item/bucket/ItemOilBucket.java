package com.projectreddog.machinemod.item.bucket;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBucket;

public class ItemOilBucket extends ItemBucket {
	public String registryName = "oilbucket";

	public ItemOilBucket(Block containedBlock) {
		super(containedBlock);
		// TODO Auto-generated constructor stub
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

	}

}
