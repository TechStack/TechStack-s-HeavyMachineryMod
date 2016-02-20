package com.projectreddog.machinemod.inventory;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class SlotFractionalDistllerBucket extends Slot {

	public SlotFractionalDistllerBucket(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
	      super(inventoryIn, slotIndex, xPosition, yPosition);
	    }

	    /**
	     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
	     */
	    public boolean isItemValid(ItemStack stack)
	    {
	    	if (	    	this.getSlotIndex() >1){
	        return  isBucket(stack);
	    	}
	    	// TODO: add a check to see if the slot index =1 and its an oil bucket
			return false;
	    }

	    public int getItemStackLimit(ItemStack stack)
	    {
	        return 1;
	    }

	    public static boolean isBucket(ItemStack stack)
	    {
	        return stack != null && stack.getItem() != null && stack.getItem() == Items.bucket;
	    }

}
