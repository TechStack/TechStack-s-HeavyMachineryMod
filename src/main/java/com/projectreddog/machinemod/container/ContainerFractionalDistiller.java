package com.projectreddog.machinemod.container;

import com.projectreddog.machinemod.inventory.SlotFractionalDistllerBucket;
import com.projectreddog.machinemod.tileentities.TileEntityFractionalDistillation;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerFractionalDistiller extends Container {

	protected TileEntityFractionalDistillation fractionaldistiller;
	private int lastFuelStorage;
	private int lastRemainBurnTime;

	public ContainerFractionalDistiller(InventoryPlayer inventoryPlayer, TileEntityFractionalDistillation fractionaldistiller) {
		this.fractionaldistiller = fractionaldistiller;

		addSlotToContainer(new SlotFurnaceFuel(fractionaldistiller, 0, 80, 108));
			addSlotToContainer(new SlotFractionalDistllerBucket(fractionaldistiller, 1, 126, 90));
		if (fractionaldistiller.hasSlot(2)){
			addSlotToContainer(new SlotFractionalDistllerBucket(fractionaldistiller, 2, 126, 66));
		}
		if (fractionaldistiller.hasSlot(3)){
			addSlotToContainer(new SlotFractionalDistllerBucket(fractionaldistiller, 3, 126, 48));
		}
		if (fractionaldistiller.hasSlot(4)){
			addSlotToContainer(new SlotFractionalDistllerBucket(fractionaldistiller, 4, 126, 30));
		}
		if (fractionaldistiller.hasSlot(5)){
			addSlotToContainer(new SlotFractionalDistllerBucket(fractionaldistiller, 5, 126, 12));
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
		// super.detectAndSendChanges();
		//
		// for (int i = 0; i < this.crafters.size(); ++i) {
		// ICrafting icrafting = (ICrafting) this.crafters.get(i);
		//
		// if (this.lastFuelStorage != this.fractionaldistiller.getField(0)) {
		// icrafting.sendProgressBarUpdate(this, 0, this.fractionaldistiller.getField(0));
		// }
		// if (this.lastRemainBurnTime != this.fractionaldistiller.getField(1)) {
		// icrafting.sendProgressBarUpdate(this, 1, this.fractionaldistiller.getField(1));
		// }
		//
		// }
		//
		// this.lastFuelStorage = this.fractionaldistiller.getField(0);
		// this.lastRemainBurnTime = this.fractionaldistiller.getField(1);

	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		// this.distiller.setField(id, data);
	}
}
