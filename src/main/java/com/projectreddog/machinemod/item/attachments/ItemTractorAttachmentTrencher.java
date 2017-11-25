package com.projectreddog.machinemod.item.attachments;

public class ItemTractorAttachmentTrencher extends ItemTractorAttachment {
	public String registryName = "trencher";

	public ItemTractorAttachmentTrencher() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 1;

	}
}
