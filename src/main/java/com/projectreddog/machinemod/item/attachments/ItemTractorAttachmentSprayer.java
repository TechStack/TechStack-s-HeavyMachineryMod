package com.projectreddog.machinemod.item.attachments;

public class ItemTractorAttachmentSprayer extends ItemTractorAttachment {
	public String registryName = "sprayer";

	public ItemTractorAttachmentSprayer() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 1;

	}
}
