package com.projectreddog.machinemod.item.blueprint;

import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.init.Items;

public class ItemBlueprintLaserMiner extends ItemBlueprint {
	public String registryName = "blueprintlaserminer";

	public ItemBlueprintLaserMiner() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);
		this.workRequired = 100000;

	}

	public void Init() {
		this.outputItemName = ModItems.laserminer.getRegistryName().toString();
		ingredents.add(new BlueprintIngredent(ModItems.steelingot.getRegistryName().toString(), 128));
		// ingredents.add(new BlueprintIngredent(Items..getRegistryName().toString(), 27));
		ingredents.add(new BlueprintIngredent(Items.REDSTONE.getRegistryName().toString(), 16));
		ingredents.add(new BlueprintIngredent(Items.LEATHER.getRegistryName().toString(), 24));
		ingredents.add(new BlueprintIngredent(Items.SLIME_BALL.getRegistryName().toString(), 16));

		LogHelper.info(this);
	}
}
