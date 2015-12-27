package com.projectreddog.machinemod.handler.events;

import com.projectreddog.machinemod.init.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.util.Random;

public class EventHandler {

	// public static BucketHandler INSTANCE = new BucketHandler();
	// public static Map<Block, Item> buckets = new HashMap<Block, Item>();

	@SubscribeEvent
	public void HarvestDropEvent(BlockEvent.HarvestDropsEvent event) {
		if (event.state.getBlock() == Blocks.tallgrass) {
			// event.world.getBiomeGenForCoords(event.pos).
			if (BiomeDictionary.isBiomeOfType(event.world.getBiomeGenForCoords(event.pos), Type.PLAINS)) {
				Random r = new Random();
				if (r.nextFloat() > .8) {
					event.drops.add(new ItemStack(ModItems.cornseed));
				}
			} else if (BiomeDictionary.isBiomeOfType(event.world.getBiomeGenForCoords(event.pos), Type.SAVANNA)) {
				Random r = new Random();
				if (r.nextFloat() > .97) {
					event.drops.add(new ItemStack(ModItems.cornseed));
				}
			}

		}

	}

	// @SubscribeEvent
	// public void onBucketFill(FillBucketEvent event) {
	//
	// ItemStack result = fillCustomBucket(event.world, event.target);
	//
	// if (result == null)
	// return;
	//
	// event.result = result;
	// event.setResult(Result.ALLOW);
	// }
	//
	// private ItemStack fillCustomBucket(World world, MovingObjectPosition pos) {
	//
	// IBlockState state = world.getBlockState(pos.getBlockPos());
	//
	// Item bucket = buckets.get(state.getBlock());
	//
	// // TODO: Replace with BlockState check
	// if (bucket != null && state.getBlock().getMetaFromState(state) == 0) {
	// world.setBlockToAir(pos.getBlockPos());
	// return new ItemStack(bucket);
	// } else {
	// return null;
	// }
	// }
}
