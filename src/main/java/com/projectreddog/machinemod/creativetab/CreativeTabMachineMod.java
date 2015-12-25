package com.projectreddog.machinemod.creativetab;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabMachineMod {
	// all creative tabls should define icon & name

	public static final CreativeTabs MACHINEMOD_ITEMS_TAB = new CreativeTabs(Reference.MOD_ID) {

		@Override
		public Item getTabIconItem() {

			return ModItems.fuelcan;
		}

		@Override
		public String getTranslatedTabLabel() {
			return "Machine Mod - Items";
		}
	};

	public static final CreativeTabs MACHINEMOD_MACHINES_TAB = new CreativeTabs(Reference.MOD_ID) {

		@Override
		public Item getTabIconItem() {

			return ModItems.bulldozer;
		}

		@Override
		public String getTranslatedTabLabel() {
			return "Machine Mod - Machines";
		}
	};

	public static final CreativeTabs MACHINEMOD_BLOCKS_TAB = new CreativeTabs(Reference.MOD_ID) {

		@Override
		public Item getTabIconItem() {

			return Item.getItemFromBlock(ModBlocks.machineasphalt);
		}

		@Override
		public String getTranslatedTabLabel() {
			return "Machine Mod - Blocks";
		}
	};
}
