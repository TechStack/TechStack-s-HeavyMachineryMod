package com.projectreddog.machinemod.creativetab;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CreativeTabMachineMod {
	// all creative tabls should define icon & name

	public static final ItemGroup MACHINEMOD_ITEMS_TAB = new ItemGroup("Machine Mod - Items") {

		@Override
		public ItemStack createIcon() {

			return new ItemStack(ModItems.fuelcan);
		}

	};

	public static final ItemGroup MACHINEMOD_MACHINES_TAB = new ItemGroup("Machine Mod - Machines") {

		@Override
		public ItemStack createIcon() {

			return new ItemStack(ModItems.bulldozer);
		}

	};

	public static final ItemGroup MACHINEMOD_BLOCKS_TAB = new ItemGroup("Machine Mod - Blocks") {

		@Override
		public ItemStack createIcon() {

			return new ItemStack(ModBlocks.machineasphalt);
		}

	};
}
