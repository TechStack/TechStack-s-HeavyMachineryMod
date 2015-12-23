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
import com.projectreddog.machinemod.entity.EntityOilRig;
import com.projectreddog.machinemod.entity.EntityPaver;
import com.projectreddog.machinemod.entity.EntityPumpJack;
import com.projectreddog.machinemod.entity.EntityRoadRoller;
import com.projectreddog.machinemod.entity.EntitySub;
import com.projectreddog.machinemod.entity.EntityTractor;
import com.projectreddog.machinemod.entity.EntityWideBedTruck;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModEntities {

	public static int entityID = 0;

	// public static final EntityBulldozer entityBulldozer= new
	// EntityBulldozer(new World);
	public static void init(Object mod) {
		EntityRegistry.registerModEntity(EntityDrillingRig.class, "drillingrig", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityBulldozer.class, "bulldozer", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityDumpTruck.class, "dumptruck", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityLoader.class, "loader", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityTractor.class, "tractor", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityCombine.class, "combine", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityWideBedTruck.class, "widebedtruck", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityCrane.class, "crane", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityExcavator.class, "excavator", ++entityID, mod, 224, 1, false);

		EntityRegistry.registerModEntity(EntityPaver.class, "paver", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityLawnmower.class, "lawnmower", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityGrader.class, "grader", ++entityID, mod, 224, 1, false);

		EntityRegistry.registerModEntity(EntityBagger.class, "bagger", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityRoadRoller.class, "roadroller", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityOilRig.class, "oilrig", ++entityID, mod, 224, 1, false);

		EntityRegistry.registerModEntity(EntitySub.class, "sub", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(EntityPumpJack.class, "pumpjack", ++entityID, mod, 224, 1, false);

	}
}
