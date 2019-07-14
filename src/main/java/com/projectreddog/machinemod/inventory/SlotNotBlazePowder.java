package com.projectreddog.machinemod.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class SlotNotBlazePowder extends Slot {

	public SlotNotBlazePowder(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
		super(inventoryIn, slotIndex, xPosition, yPosition);
	}

	/**
	 * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
	 */
	public boolean isItemValid(ItemStack stack) {

		if (!stack.isEmpty()) {
			if (stack.getItem() != Items.BLAZE_POWDER) {
				return true;
			}
		}
		return false;

	}

}
