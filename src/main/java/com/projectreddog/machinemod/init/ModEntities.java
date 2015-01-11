package com.projectreddog.machinemod.init;

import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.projectreddog.machinemod.entity.EntityBulldozer;
import com.projectreddog.machinemod.entity.EntityDrillingRig;
import com.projectreddog.machinemod.entity.EntityDumpTruck;
import com.projectreddog.machinemod.entity.EntityLoader;
import com.projectreddog.machinemod.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)

public class ModEntities {

	
	//public static final EntityBulldozer entityBulldozer= new EntityBulldozer(new World);
	public static void init(Object mod)
	{
		EntityRegistry.registerModEntity(EntityDrillingRig.class, "drillingrig", 1,mod , 80, 1, true);
	    EntityRegistry.registerModEntity(EntityBulldozer.class, "bulldozer", 2,mod , 80,1, true);
	    EntityRegistry.registerModEntity(EntityDumpTruck.class, "dumptruck", 3,mod , 80,1,true);
	    EntityRegistry.registerModEntity(EntityLoader.class, "loader", 4,mod , 80,1,true);

	}
}
