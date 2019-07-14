package com.projectreddog.machinemod.container;

import com.projectreddog.machinemod.entity.EntityGrader;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerGrader extends Container {

	protected EntityGrader grader;

	public ContainerGrader(InventoryPlayer inventoryPlayer, EntityGrader grader) {
		this.grader = grader;

		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new SlotItemHandler(grader.inventory, j + i * 9, 8 + j * 18, 18 + i * 18));
			}
		}

		// commonly used vanilla code that adds the player's inventory
		bindPlayerInventory(inventoryPlayer);
	}

	@Override
	public boolean canInteractWith(PlayerEntity player) {
		return grader.isUsableByPlayer(player);
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
			if (slot < 9) {
				if (!this.mergeItemStack(stackInSlot, 9, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			}
			// places it into the tileEntity is possible since its in the player
			// inventory
			else if (!this.mergeItemStack(stackInSlot, 0, 9, false)) {
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
