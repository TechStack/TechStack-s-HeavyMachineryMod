package com.projectreddog.machinemod.utility;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.world.BlockEvent;

public class BlockUtil {

	public static boolean BreakBlock(World worldObj, BlockPos pos, Entity player) {
		IBlockState state = worldObj.getBlockState(pos);

		EntityPlayer passedPlayer;

		if (player instanceof EntityPlayer) {
			passedPlayer = (EntityPlayer) player;
		} else {
			passedPlayer = FakePlayerFactory.get((WorldServer) worldObj, Reference.gameProfile);
		}

		BlockEvent.BreakEvent breakEvent = new BlockEvent.BreakEvent(worldObj, pos, state, passedPlayer);
		MinecraftForge.EVENT_BUS.post(breakEvent);

		if (breakEvent.isCanceled()) {
			return false;
		}

		worldObj.getBlockState(pos).getBlock().dropBlockAsItem(worldObj, pos, state, 0);
		// TODO add block break sound
		worldObj.setBlockToAir(pos);

		return true;
	}
}
