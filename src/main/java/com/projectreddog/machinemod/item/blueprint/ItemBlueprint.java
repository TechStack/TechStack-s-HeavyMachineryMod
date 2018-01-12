package com.projectreddog.machinemod.item.blueprint;

import java.util.ArrayList;
import java.util.List;

import com.projectreddog.machinemod.item.ItemMachineMod;

public class ItemBlueprint extends ItemMachineMod {

	public String outputItemName;
	public int workRequired;
	public List<BlueprintIngredent> ingredents = new ArrayList<BlueprintIngredent>();

	public ItemBlueprint() {
		super();
		this.maxStackSize = 1;
		this.workRequired = 100000;

	}

	@Override
	public String toString() {
		String output = "\nOutput: " + outputItemName + "\n";
		for (int i = 0; i < ingredents.size(); i++) {
			output = output + "Input item: " + ingredents.get(i).getName() + " X " + ingredents.get(i).getCount() + "\n";
		}
		return output;
	}

	public class BlueprintIngredent {
		private int count;
		private String name;

		public BlueprintIngredent(String name, int count) {
			this.count = count;
			this.name = name;

		}

		public int getCount() {
			return count;
		}

		public String getName() {
			return name;
		}
	}
}
