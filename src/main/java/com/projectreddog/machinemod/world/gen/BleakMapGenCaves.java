package com.projectreddog.machinemod.world.gen;

import com.projectreddog.machinemod.init.ModBlocks;

import net.minecraft.block.state.BlockState;
import net.minecraft.world.gen.MapGenCaves;

public class BleakMapGenCaves extends MapGenCaves {

	@Override
	protected boolean canReplaceBlock(BlockState p_175793_1_, BlockState p_175793_2_) {
		if (p_175793_1_.getBlock() == ModBlocks.machinebleakstone) {
			return true;
		} else if (p_175793_1_.getBlock() == ModBlocks.machinebleakdirt) {
			return true;
		} else {
			return false;
		}
	}
}
