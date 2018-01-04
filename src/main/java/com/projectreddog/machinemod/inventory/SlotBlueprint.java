package com.projectreddog.machinemod.inventory;

import com.projectreddog.machinemod.item.blueprint.ItemBlueprint;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBlueprint extends Slot {

	public SlotBlueprint(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
		super(inventoryIn, slotIndex, xPosition, yPosition);
	}

	/**
	 * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
	 */
	public boolean isItemValid(ItemStack stack) {

		return isBlueprint(stack);

	}

	public int getItemStackLimit(ItemStack stack) {
		return 1;
	}

	public static boolean isBlueprint(ItemStack stack) {
		return stack != null && stack.getItem() != null && stack.getItem() instanceof ItemBlueprint;
	}

}
