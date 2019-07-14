package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockMachineAsphalt extends BlockMachineMod {

	public BlockMachineAsphalt() {

		super(Block.Properties.create(Material.ROCK).hardnessAndResistance(15f).sound(SoundType.STONE));

		// 1.8

		// REMOVED 1.14
		// this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_ASPHALT);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_ASPHALT);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);

	}

}
