package com.projectreddog.machinemod.village;

import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;

public class VillagerProfessionEngineer extends VillagerProfession {
	public VillagerCareerMining miner = new VillagerCareerMining(this, "Miner");

	public VillagerProfessionEngineer(String name, String texture, String zombie) {
		super(name, texture, zombie);
		// TODO Auto-generated constructor stub
	}

	public VillagerCareer getCareer(int id) {
		return miner;
	}

}
