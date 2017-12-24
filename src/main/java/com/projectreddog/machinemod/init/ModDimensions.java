package com.projectreddog.machinemod.init;

import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.world.BleakWorldProvider;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class ModDimensions {

	public static DimensionType bleakDimensionType;

	// TODO add to the configs the DIM ID For bleak Dim
	public static int bleakDimID = 57;

	public static void init() {

		// TODO add to the configs the DIM ID For bleak Dim
		// set init value for bleakDimID based on configs

		registerDimensionTypes();
		registerDimensions();
	}

	private static void registerDimensionTypes() {

		bleakDimensionType = DimensionType.register(Reference.MOD_ID, "_bleak", bleakDimID, BleakWorldProvider.class, false);
	}

	private static void registerDimensions() {

		DimensionManager.registerDimension(bleakDimID, bleakDimensionType);
	}

}
