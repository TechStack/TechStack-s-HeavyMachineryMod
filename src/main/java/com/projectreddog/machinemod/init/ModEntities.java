package com.projectreddog.machinemod.init;

import com.projectreddog.machinemod.entity.EntityBagger;
import com.projectreddog.machinemod.entity.EntityBulldozer;
import com.projectreddog.machinemod.entity.EntityChopper;
import com.projectreddog.machinemod.entity.EntityCombine;
import com.projectreddog.machinemod.entity.EntityCrane;
import com.projectreddog.machinemod.entity.EntityDrillingRig;
//import com.projectreddog.machinemod.entity.EntityDrillingRig;
import com.projectreddog.machinemod.entity.EntityDumpTruck;
import com.projectreddog.machinemod.entity.EntityExcavator;
import com.projectreddog.machinemod.entity.EntityGrader;
import com.projectreddog.machinemod.entity.EntityLaserMiner;
import com.projectreddog.machinemod.entity.EntityLawnmower;
import com.projectreddog.machinemod.entity.EntityLoader;
import com.projectreddog.machinemod.entity.EntityOilRig;
import com.projectreddog.machinemod.entity.EntityPaver;
import com.projectreddog.machinemod.entity.EntityRoadRoller;
import com.projectreddog.machinemod.entity.EntitySemiTractor;
import com.projectreddog.machinemod.entity.EntitySub;
import com.projectreddog.machinemod.entity.EntityTractor;
import com.projectreddog.machinemod.entity.monster.EntityExpStalker;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModEntities {

	public static int entityID = 0;

	// public static final EntityBulldozer entityBulldozer= new
	// EntityBulldozer(new World);
	public static void init(Object mod) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "drillingrig"), EntityDrillingRig.class, "drillingrig", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "bulldozer"), EntityBulldozer.class, "bulldozer", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "dumptruck"), EntityDumpTruck.class, "dumptruck", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "loader"), EntityLoader.class, "loader", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "tractor"), EntityTractor.class, "tractor", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "combine"), EntityCombine.class, "combine", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "widebedtruck"), EntitySemiTractor.class, "widebedtruck", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "crane"), EntityCrane.class, "crane", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "excavator"), EntityExcavator.class, "excavator", ++entityID, mod, 224, 1, false);

		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "paver"), EntityPaver.class, "paver", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "lawnmower"), EntityLawnmower.class, "lawnmower", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "grader"), EntityGrader.class, "grader", ++entityID, mod, 224, 1, false);

		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "bagger"), EntityBagger.class, "bagger", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "roadroller"), EntityRoadRoller.class, "roadroller", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "oilrig"), EntityOilRig.class, "oilrig", ++entityID, mod, 224, 1, false);

		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "sub"), EntitySub.class, "sub", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "chopper"), EntityChopper.class, "chopper", ++entityID, mod, 224, 1, false);
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "laserminer"), EntityLaserMiner.class, "laserminer", ++entityID, mod, 224, 1, false);

		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + "expstalker"), EntityExpStalker.class, "expstalker", ++entityID, mod, 224, 1, false, 0xFFFFFF, 0x000050);

		//
		EntityRegistry.addSpawn(EntityExpStalker.class, 100, 3, 5, EnumCreatureType.MONSTER, Biomes.PLAINS);

		// EntityRegistry.registerModEntity(EntityPumpJack.class, "pumpjack", ++entityID, mod, 224, 1, false);

	}
}
