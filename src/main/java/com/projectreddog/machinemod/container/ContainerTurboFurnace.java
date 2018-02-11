package com.projectreddog.machinemod.container;

import com.projectreddog.machinemod.inventory.SlotBlazePowder;
import com.projectreddog.machinemod.inventory.SlotNotBlazePowder;
import com.projectreddog.machinemod.inventory.SlotOutputOnlyTurobFurnace;
import com.projectreddog.machinemod.tileentities.TileEntityTurboFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

;

public class ContainerTurboFurnace extends Container {

	protected TileEntityTurboFurnace turbofurnace;

	protected int lastFuleBurnTimeRemaining = 0;
	protected int lastProcessingTimeRemaining = 0;

	public ContainerTurboFurnace(InventoryPlayer inventoryPlayer, TileEntityTurboFurnace turbofurnace) {
		this.turbofurnace = turbofurnace;

		lastFuleBurnTimeRemaining = -1;
		lastProcessingTimeRemaining = -1;

		// for (int i = 0; i < 1; i++) {
		// for (int j = 0; j < 3; j++) {
		addSlotToContainer(new SlotNotBlazePowder(turbofurnace, 0, 47, 34));
		addSlotToContainer(new SlotOutputOnlyTurobFurnace(inventoryPlayer.player, turbofurnace, 1, 110, 53));
		addSlotToContainer(new SlotBlazePowder(turbofurnace, 2, 47, 74));
		// }
		// }

		// commonly used vanilla code that adds the player's inventory
		bindPlayerInventory(inventoryPlayer);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return turbofurnace.isUsableByPlayer(player);
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
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slotObject = (Slot) inventorySlots.get(slot);

		// null checks and checks if the item can be stacked (maxStackSize > 1)
		if (slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();

			// merges the item into player inventory since its in the Entity
			if (slot < 3) {
				if (!this.mergeItemStack(stackInSlot, 3, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}

				slotObject.onSlotChange(stackInSlot, stack);
			}
			// places it into the tileEntity is possible since its in the player
			// inventory
			else if (!this.mergeItemStack(stackInSlot, 0, 3, false)) {
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

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener icrafting = (IContainerListener) this.listeners.get(i);
			if (this.lastFuleBurnTimeRemaining != this.turbofurnace.getField(0)) {
				icrafting.sendWindowProperty(this, 0, this.turbofurnace.getField(0));
			}

			if (this.lastProcessingTimeRemaining != this.turbofurnace.getField(1)) {
				icrafting.sendWindowProperty(this, 1, this.turbofurnace.getField(1));
			}

		}

		this.lastFuleBurnTimeRemaining = this.turbofurnace.getField(0);
		this.lastProcessingTimeRemaining = this.turbofurnace.getField(1);
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.turbofurnace.setField(id, data);
	}

}
