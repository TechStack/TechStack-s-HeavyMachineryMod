package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockMachineBleakOreLimoniteum extends BlockMachineMod {

	public BlockMachineBleakOreLimoniteum() {
		super(Block.Properties.create(Material.ROCK).hardnessAndResistance(-1.0F, 6000000.0F).sound(SoundType.METAL));
		// 1.8
		// REMOVED 1.14
		// this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_BLEAK_ORE_LIMONITEUM);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_BLEAK_ORE_LIMONITEUM);

	}

}
