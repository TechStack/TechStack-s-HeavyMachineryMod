package com.projectreddog.machinemod.init;

import java.util.ArrayList;
import java.util.Random;

import com.projectreddog.machinemod.village.TradeListEngineer;
import com.projectreddog.machinemod.village.VillagerProfessionEngineer;
import com.projectreddog.machinemod.world.gen.EngineerCreationHandler;
import com.projectreddog.machinemod.world.gen.structure.EngineerHouse;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class ModVillage {

	public static final VillagerProfessionEngineer engineer = new VillagerProfessionEngineer("machinemod:engineer", "machinemod:textures/entity/village/engineer.png", "machinemod:textures/entity/village/zombieengineer.png");

	public static void init() {
		ForgeRegistries.VILLAGER_PROFESSIONS.register(engineer);
		engineer.getCareer(0).addTrade(1, new TradeListEngineer());

		ArrayList<StructureVillagePieces.PieceWeight> pieces = new ArrayList<StructureVillagePieces.PieceWeight>();

		pieces.add(new StructureVillagePieces.PieceWeight(EngineerHouse.class, 10, 1));

		VillagerRegistry.addExtraVillageComponents(pieces, new Random(), 1);
		VillagerRegistry.instance().registerVillageCreationHandler(new EngineerCreationHandler());

		MapGenStructureIO.registerStructureComponent(EngineerHouse.class, "machinemod:engineerhouse");
	}
}
