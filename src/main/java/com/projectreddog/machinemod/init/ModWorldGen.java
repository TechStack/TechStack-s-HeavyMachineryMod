package com.projectreddog.machinemod.init;

import com.projectreddog.machinemod.world.MachineModWorldGenBleak;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModWorldGen {

	public static void init() {

		GameRegistry.registerWorldGenerator(new MachineModWorldGenBleak(), 0);

	}
}
