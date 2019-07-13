package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockMachineBleakDirt extends BlockMachineMod {

	public BlockMachineBleakDirt() {
		super(Block.Properties.create(Material.ROCK).hardnessAndResistance(-1.0F, 6000000.0F).sound(SoundType.GROUND));
		// REMOVED 1.14
		// this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_BLEAK_DIRT);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_BLEAK_DIRT);
	}
}
