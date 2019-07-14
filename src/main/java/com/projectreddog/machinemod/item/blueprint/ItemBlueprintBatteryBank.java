package com.projectreddog.machinemod.item.blueprint;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.item.Items;

public class ItemBlueprintBatteryBank extends ItemBlueprint {
	public String registryName = "blueprintbatterybank";

	public ItemBlueprintBatteryBank() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);
		this.workRequired = 10000;

	}

	public void Init() {
		this.outputItemName = ModBlocks.machinebatterybank.getRegistryName().toString();
		ingredents.add(new BlueprintIngredent(Items.IRON_INGOT.getRegistryName().toString(), 4));
		ingredents.add(new BlueprintIngredent(Items.REDSTONE.getRegistryName().toString(), 112));
		ingredents.add(new BlueprintIngredent(ModBlocks.machineconduit.getRegistryName().toString(), 3));

		LogHelper.info(this);
	}
}
