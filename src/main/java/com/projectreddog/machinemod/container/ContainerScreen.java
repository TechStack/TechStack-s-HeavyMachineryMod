package com.projectreddog.machinemod.container;

import com.projectreddog.machinemod.tileentities.TileEntityScreen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerScreen extends Container {

	protected TileEntityScreen screen;
	private int lastFuelStorage;

	public ContainerScreen(InventoryPlayer inventoryPlayer, TileEntityScreen screen) {
		this.screen = screen;
		lastFuelStorage = -1;
		// slot 0 = north
		// slot 1 = east
		// slot 2 = south
		// slot 3 = west
		addSlotToContainer(new Slot(screen, 0, 81, 30));// north = 0

		addSlotToContainer(new Slot(screen, 1, 114, 64)); // east

		addSlotToContainer(new Slot(screen, 2, 81, 98)); // south

		addSlotToContainer(new Slot(screen, 3, 44, 64)); // west

		addSlotToContainer(new Slot(screen, 4, 81, 64));// / center

		// commonly used vanilla code that adds the player's inventory
		bindPlayerInventory(inventoryPlayer);
	}

	@Override
	public boolean canInteractWith(PlayerEntity player) {
		return screen.isUsableByPlayer(player);
	}

	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 139 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 197));
		}
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity player, int slot) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slotObject = (Slot) inventorySlots.get(slot);

		// null checks and checks if the item can be stacked (maxStackSize > 1)
		if (slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();

			// merges the item into player inventory since its in the Entity
			if (slot < 5) {
				if (!this.mergeItemStack(stackInSlot, 5, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			}
			// places it into the tileEntity is possible since its in the player
			// inventory
			else if (!this.mergeItemStack(stackInSlot, 4, 5, false)) {
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

}
