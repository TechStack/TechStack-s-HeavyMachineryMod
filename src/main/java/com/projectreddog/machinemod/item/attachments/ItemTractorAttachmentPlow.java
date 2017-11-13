package com.projectreddog.machinemod.item.attachments;

public class ItemTractorAttachmentPlow extends ItemTractorAttachment {
	public String registryName = "plow";

	public ItemTractorAttachmentPlow() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 1;

	}
}
