package com.projectreddog.machinemod.init;

import com.projectreddog.machinemod.entity.EntityBagger;
import com.projectreddog.machinemod.entity.EntityBulldozer;
import com.projectreddog.machinemod.entity.EntityCombine;
import com.projectreddog.machinemod.entity.EntityCrane;
import com.projectreddog.machinemod.entity.EntityDrillingRig;
//import com.projectreddog.machinemod.entity.EntityDrillingRig;
import com.projectreddog.machinemod.entity.EntityDumpTruck;
import com.projectreddog.machinemod.entity.EntityExcavator;
import com.projectreddog.machinemod.entity.EntityGrader;
import com.projectreddog.machinemod.entity.EntityLawnmower;
import com.projectreddog.machinemod.entity.EntityLoader;
import com.projectreddog.machinemod.entity.EntityPaver;
import com.projectreddog.machinemod.entity.EntityTractor;
import com.projectreddog.machinemod.entity.EntityWideBedTruck;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModEntities {

	// public static final EntityBulldozer entityBulldozer= new
	// EntityBulldozer(new World);
	public static void init(Object mod) {
		EntityRegistry.registerModEntity(EntityDrillingRig.class, "drillingrig", 1, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityBulldozer.class, "bulldozer", 2, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityDumpTruck.class, "dumptruck", 3, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityLoader.class, "loader", 4, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityTractor.class, "tractor", 5, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityCombine.class, "combine", 6, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityWideBedTruck.class, "widebedtruck", 7, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityCrane.class, "crane", 8, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityExcavator.class, "excavator", 9, mod, 224, 1, false);

		EntityRegistry.registerModEntity(EntityPaver.class, "paver", 12, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityLawnmower.class, "lawnmower", 13, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityGrader.class, "grader", 14, mod, 224, 1, false);

		EntityRegistry.registerModEntity(EntityBagger.class, "bagger", 15, mod, 224, 1, false);

	}
}
