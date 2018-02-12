package com.projectreddog.machinemod.item.blueprint;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.utility.LogHelper;

public class ItemBlueprintShredder extends ItemBlueprint {
	public String registryName = "blueprintshredder";

	public ItemBlueprintShredder() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);
		this.workRequired = 10000;

	}

	public void Init() {
		this.outputItemName = ModBlocks.machineshredder.getRegistryName().toString();
		ingredents.add(new BlueprintIngredent(ModItems.rollerwheel.getRegistryName().toString(), 2));
		ingredents.add(new BlueprintIngredent(ModItems.unobtaniumgem.getRegistryName().toString(), 2));
		ingredents.add(new BlueprintIngredent(ModItems.limoniteumingot.getRegistryName().toString(), 6));
		ingredents.add(new BlueprintIngredent(ModItems.engine.getRegistryName().toString(), 1));
		LogHelper.info(this);
	}
}
