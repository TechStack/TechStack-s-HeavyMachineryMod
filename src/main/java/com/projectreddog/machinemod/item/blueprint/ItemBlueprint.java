package com.projectreddog.machinemod.item.blueprint;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.projectreddog.machinemod.item.ItemMachineMod;
import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.ForgeRegistries;

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

	public String toolTipToString() {
		String output = "";
		if (outputItemName != null) {
			Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(outputItemName));
			if (item != null) {
				String displayName = item.getItemStackDisplayName(new ItemStack(item));

				output = "\u00A7aMakes: " + displayName + "\n";
			}

			output = output + "Ingredent: \n";
			for (int i = 0; i < ingredents.size(); i++) {

				item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(ingredents.get(i).getName()));
				if (item != null) {
					String displayName = item.getItemStackDisplayName(new ItemStack(item));
					output = output + "   " + displayName + " X " + ingredents.get(i).getCount() + "\n";
				} else {
					LogHelper.info("An Output of an ingredent is null Tell Tech please!" + outputItemName);
				}

				// output = output + "Ingredent: " + ingredents.get(i).getName() + " X " + ingredents.get(i).getCount() + "\n";
			}
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

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(this.toolTipToString());
	}
}
