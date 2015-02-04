package com.projectreddog.machinemod.init;

import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.projectreddog.machinemod.entity.EntityBulldozer;
import com.projectreddog.machinemod.entity.EntityCombine;
import com.projectreddog.machinemod.entity.EntityCrane;
//import com.projectreddog.machinemod.entity.EntityDrillingRig;
import com.projectreddog.machinemod.entity.EntityDumpTruck;
import com.projectreddog.machinemod.entity.EntityLoader;
import com.projectreddog.machinemod.entity.EntityTractor;
import com.projectreddog.machinemod.entity.EntityWideBedTruck;
import com.projectreddog.machinemod.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModEntities {

	// public static final EntityBulldozer entityBulldozer= new
	// EntityBulldozer(new World);
	public static void init(Object mod) {
		// EntityRegistry.registerModEntity(EntityDrillingRig.class,
		// "drillingrig", 1, mod, 80, 1, false);
		EntityRegistry.registerModEntity(EntityBulldozer.class, "bulldozer", 2, mod, 80, 1, false);
		EntityRegistry.registerModEntity(EntityDumpTruck.class, "dumptruck", 3, mod, 80, 1, false);
		EntityRegistry.registerModEntity(EntityLoader.class, "loader", 4, mod, 80, 1, false);
		EntityRegistry.registerModEntity(EntityTractor.class, "tractor", 5, mod, 80, 1, false);
		EntityRegistry.registerModEntity(EntityCombine.class, "combine", 6, mod, 80, 1, false);
		EntityRegistry.registerModEntity(EntityWideBedTruck.class, "widebedtruck", 7, mod, 80, 1, false);
		EntityRegistry.registerModEntity(EntityCrane.class, "crane", 8, mod, 80, 1, false);

	}
}
