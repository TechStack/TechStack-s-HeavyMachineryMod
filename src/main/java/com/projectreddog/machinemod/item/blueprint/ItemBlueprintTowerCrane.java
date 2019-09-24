package com.projectreddog.machinemod.item.blueprint;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.init.Items;

public class ItemBlueprintTowerCrane extends ItemBlueprint {
	public String registryName = "blueprinttowercrane";

	public ItemBlueprintTowerCrane() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);
		this.workRequired = 100000;

	}

	public void Init() {
		this.outputItemName = ModBlocks.machinetowercrane.getRegistryName().toString();
		ingredents.add(new BlueprintIngredent(ModItems.rigging.getRegistryName().toString(), 16));
		ingredents.add(new BlueprintIngredent(ModBlocks.steelblock.getRegistryName().toString(), 20));
		ingredents.add(new BlueprintIngredent(Items.STRING.getRegistryName().toString(), 128));
		LogHelper.info(this);
	}
}
