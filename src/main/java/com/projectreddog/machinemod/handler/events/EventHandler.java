package com.projectreddog.machinemod.handler.events;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.util.Random;

import com.projectreddog.machinemod.init.ModItems;

public class EventHandler {

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
}
