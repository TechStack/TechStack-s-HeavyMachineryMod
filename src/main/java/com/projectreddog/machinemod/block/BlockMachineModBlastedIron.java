package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockMachineModBlastedIron extends BlockMachineModBlastedStoneBase {

	public BlockMachineModBlastedIron() {
		super(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f).sound(SoundType.STONE));
		// 1.8
		// REMOVED 1.14
		// this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_BLASTED_IRON);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_BLASTED_IRON);

	}

}
