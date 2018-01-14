package com.projectreddog.machinemod.handler.events;

import com.projectreddog.machinemod.init.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.util.Random;

public class EventHandler {

	// public static BucketHandler INSTANCE = new BucketHandler();
	// public static Map<Block, Item> buckets = new HashMap<Block, Item>();

	@SubscribeEvent
	public void HarvestDropEvent(BlockEvent.HarvestDropsEvent event) {
		if (event.getState().getBlock() == Blocks.TALLGRASS) {
			// event.world.getBiomeGenForCoords(event.pos).
			if (BiomeDictionary.hasType(event.getWorld().getBiome(event.getPos()), Type.PLAINS)) {
				Random r = new Random();
				if (r.nextFloat() > .8) {
					event.getDrops().add(new ItemStack(ModItems.cornseed));
				}
			} else if (BiomeDictionary.hasType(event.getWorld().getBiome(event.getPos()), Type.SAVANNA)) {
				Random r = new Random();
				if (r.nextFloat() > .97) {
					event.getDrops().add(new ItemStack(ModItems.cornseed));
				}
			}

		}

	}

	@SubscribeEvent
	public void onLivingAttackEvent(LivingAttackEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			if (((EntityPlayer) event.getEntity()).getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == ModItems.crashhelmet) {
				if (event.getSource() == DamageSource.FLY_INTO_WALL) {
					event.setCanceled(true);
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
	// //
	// if (bucket != null && state.getBlock().getMetaFromState(state) == 0) {
	// world.setBlockToAir(pos.getBlockPos());
	// return new ItemStack(bucket);
	// } else {
	// return null;
	// }
	// }
}
