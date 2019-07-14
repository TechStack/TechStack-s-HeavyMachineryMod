package com.projectreddog.machinemod.utility;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.world.BlockEvent;

public class BlockUtil {

	public static boolean BreakBlock(World worldObj, BlockPos pos, Entity player) {
		BlockState state = worldObj.getBlockState(pos);

		PlayerEntity passedPlayer;

		if (player instanceof PlayerEntity) {
			passedPlayer = (PlayerEntity) player;
		} else {
			passedPlayer = FakePlayerFactory.get((ServerWorld) worldObj, Reference.gameProfile);
		}

		BlockEvent.BreakEvent breakEvent = new BlockEvent.BreakEvent(worldObj, pos, state, passedPlayer);
		MinecraftForge.EVENT_BUS.post(breakEvent);

		if (breakEvent.isCanceled()) {
			return false;
		}

		// worldObj.getBlockState(pos).getBlock().dropBlockAsItem(worldObj, pos, state, 0);
		// TODO add block break sound
		// TODO CHECK FOR SOUND & CHECK FOR BLOCK DROPPING PROPER ITEM as we removed the above to and added true here

		worldObj.destroyBlock(pos, true);

		return true;
	}

	public static boolean setBlockandNotify(World world, BlockPos bp, BlockState bs) {
		boolean result = true;
		world.setBlockState(bp, bs);
		BlockState state = world.getBlockState(bp);
		world.notifyBlockUpdate(bp, state, state, 3);
		return result;
	}
}
