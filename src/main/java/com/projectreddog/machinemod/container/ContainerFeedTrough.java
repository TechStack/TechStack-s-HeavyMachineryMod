package com.projectreddog.machinemod.container;

import com.projectreddog.machinemod.tileentities.TileEntityFeedTrough;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerFeedTrough extends Container {

	protected TileEntityFeedTrough feedtrough;
	private int lastFuelStorage;
	private int lastRemainBurnTime;

	public ContainerFeedTrough(InventoryPlayer inventoryPlayer, TileEntityFeedTrough feedtrough) {
		this.feedtrough = feedtrough;
		lastFuelStorage = -1;
		lastRemainBurnTime = -1;
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 1; j++) {
				addSlotToContainer(new Slot(feedtrough, j + i * 9, 80 + j * 18, 57 + i * 18));
			}
		}

		// commonly used vanilla code that adds the player's inventory
		bindPlayerInventory(inventoryPlayer);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 140 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 198));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slotObject = (Slot) inventorySlots.get(slot);

		// null checks and checks if the item can be stacked (maxStackSize > 1)
		if (slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();

			// merges the item into player inventory since its in the Entity
			if (slot < 1) {
				if (!this.mergeItemStack(stackInSlot, 1, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			}
			// places it into the tileEntity is possible since its in the player
			// inventory
			else if (!this.mergeItemStack(stackInSlot, 0, 1, false)) {
				return ItemStack.EMPTY;
			}

			if (stackInSlot.getCount() == 0) {
				slotObject.putStack(ItemStack.EMPTY);
			} else {
				slotObject.onSlotChanged();
			}

			if (stackInSlot.getCount() == stack.getCount()) {
				return ItemStack.EMPTY;
			}
			slotObject.onTake(player, stackInSlot);
		}
		return stack;
	}

	/**
	 * Looks for changes made in the container, sends them to every listener.
	 */
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
	}
}
