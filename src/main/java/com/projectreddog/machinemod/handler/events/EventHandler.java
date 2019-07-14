package com.projectreddog.machinemod.handler.events;

import java.util.Random;

import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandler {

	// public static BucketHandler INSTANCE = new BucketHandler();
	// public static Map<Block, Item> buckets = new HashMap<Block, Item>();

	@SubscribeEvent
	public void HarvestDropEvent(BlockEvent.HarvestDropsEvent event) {
		if (event.getState().getBlock() == Blocks.TALL_GRASS) {
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
		if (event.getEntity() instanceof PlayerEntity) {
			if (((PlayerEntity) event.getEntity()).getItemStackFromSlot(EquipmentSlotType.HEAD)
					.getItem() == ModItems.crashhelmet) {
				if (event.getSource() == DamageSource.FLY_INTO_WALL) {
					event.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public void onGenerateMinable(GenerateMinable event) {
		if (event.getWorld().provider.getDimension() == Reference.BleakDimID) {// was
																				// get
																				// dim
																				// id
			if (event.getType() != GenerateMinable.EventType.CUSTOM) {
				event.setResult(Result.DENY);
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
	// private ItemStack fillCustomBucket(World world, MovingObjectPosition pos)
	// {
	//
	// BlockState state = world.getBlockState(pos.getBlockPos());
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
