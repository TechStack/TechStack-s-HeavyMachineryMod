package com.projectreddog.machinemod.container;

import com.projectreddog.machinemod.inventory.SlotFractionalDistllerBucket;
import com.projectreddog.machinemod.tileentities.TileEntityFractionalDistillation;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerFractionalDistiller extends Container {

	protected TileEntityFractionalDistillation fractionaldistiller;
	private int lastFuelStorage;
	private int lastRemainBurnTime;
	private boolean isSlot1Active = true;
	private boolean isSlot2Active = false;
	private boolean isSlot3Active = false;
	private boolean isSlot4Active = false;
	private boolean isSlot5Active = false;
	private int lastValue[];

	public boolean isSlot1Active() {
		return isSlot1Active;
	}

	public boolean isSlot2Active() {
		return isSlot2Active;
	}

	public boolean isSlot3Active() {
		return isSlot3Active;
	}

	public boolean isSlot4Active() {
		return isSlot4Active;
	}

	public boolean isSlot5Active() {
		return isSlot5Active;
	}

	public ContainerFractionalDistiller(InventoryPlayer inventoryPlayer, TileEntityFractionalDistillation fractionaldistiller) {
		this.fractionaldistiller = fractionaldistiller;
		lastValue = new int[this.fractionaldistiller.getFieldCount()];
		addSlotToContainer(new SlotFurnaceFuel(fractionaldistiller, 0, 80, 108));
		addSlotToContainer(new SlotFractionalDistllerBucket(fractionaldistiller, 1, 126, 90));
		if (fractionaldistiller.hasSlot(2)) {
			addSlotToContainer(new SlotFractionalDistllerBucket(fractionaldistiller, 2, 126, 66));
			isSlot3Active = true;
		}
		if (fractionaldistiller.hasSlot(3)) {
			addSlotToContainer(new SlotFractionalDistllerBucket(fractionaldistiller, 3, 126, 48));
			isSlot3Active = true;
		}
		if (fractionaldistiller.hasSlot(4)) {
			addSlotToContainer(new SlotFractionalDistllerBucket(fractionaldistiller, 4, 126, 30));
			isSlot4Active = true;
		}
		if (fractionaldistiller.hasSlot(5)) {
			addSlotToContainer(new SlotFractionalDistllerBucket(fractionaldistiller, 5, 126, 12));
			isSlot5Active = true;
		}

		//
		// for (int i = 0; i < 1; i++) {
		// for (int j = 0; j < 1; j++) {

		// addSlotToContainer(new Slot(fractionaldistiller, j + i * 9, 8 + j * 18, 18 + i * 18));
		// }
		// }

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
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 139 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 197));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);

		// null checks and checks if the item can be stacked (maxStackSize > 1)
		if (slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();

			// merges the item into player inventory since its in the Entity
			if (slot < 7) {
				if (!this.mergeItemStack(stackInSlot, 6, this.inventorySlots.size(), true)) {
					return null;
				}
			}
			// places it into the tileEntity is possible since its in the player
			// inventory
			else if (!this.mergeItemStack(stackInSlot, 0, 6, false)) {
				return null;
			}

			if (stackInSlot.stackSize == 0) {
				slotObject.putStack(null);
			} else {
				slotObject.onSlotChanged();
			}

			if (stackInSlot.stackSize == stack.stackSize) {
				return null;
			}
			slotObject.onPickupFromSlot(player, stackInSlot);
		}
		return stack;
	}

	/**
	 * Looks for changes made in the container, sends them to every listener.
	 */
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int j = 0; j < this.fractionaldistiller.getFieldCount(); j++) {
			for (int i = 0; i < this.crafters.size(); ++i) {
				ICrafting icrafting = (ICrafting) this.crafters.get(i);

				if (lastValue[j] != this.fractionaldistiller.getField(j)) {
					icrafting.sendProgressBarUpdate(this, j, this.fractionaldistiller.getField(j));
				}
			}
			lastValue[j] = this.fractionaldistiller.getField(j);

		}

	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.fractionaldistiller.setField(id, data);
	}
}
