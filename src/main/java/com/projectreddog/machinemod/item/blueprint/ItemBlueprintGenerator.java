package com.projectreddog.machinemod.item.blueprint;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.utility.LogHelper;

public class ItemBlueprintGenerator extends ItemBlueprint {
	public String registryName = "blueprintgenerator";

	public ItemBlueprintGenerator() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);
		this.workRequired = 1000;

	}

	public void Init() {
		this.outputItemName = ModBlocks.machinegenerator.getRegistryName().toString();
		ingredents.add(new BlueprintIngredent(Items.IRON_INGOT.getRegistryName().toString(), 53));
		ingredents.add(new BlueprintIngredent(Items.REDSTONE.getRegistryName().toString(), 10));
		ingredents.add(new BlueprintIngredent(ModBlocks.machineconduit.getRegistryName().toString(), 5));
		ingredents.add(new BlueprintIngredent(Items.SLIME_BALL.getRegistryName().toString(), 2));

		LogHelper.info(this);
	}
}
