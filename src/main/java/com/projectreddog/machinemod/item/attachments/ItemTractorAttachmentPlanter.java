package com.projectreddog.machinemod.item.attachments;

public class ItemTractorAttachmentPlanter extends ItemTractorAttachment {
	public String registryName = "planter";

	public ItemTractorAttachmentPlanter() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 1;

	}
}
