package com.projectreddog.machinemod.item.blueprint;

import java.util.List;

import com.projectreddog.machinemod.item.ItemMachineMod;

public class ItemBlueprint extends ItemMachineMod {

	public String outputItemName;

	public List<BlueprintIngredent> ingredents;

	public String ToString() {
		String output = "Output: " + outputItemName + "\n";
		for (int i = 0; i < ingredents.size(); i++) {
			output = output + ingredents.get(i).getName() + " X " + ingredents.get(i).getCount() + "\n";
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
