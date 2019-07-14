package com.projectreddog.machinemod.container;

import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.inventory.SlotBlueprint;
import com.projectreddog.machinemod.inventory.SlotOutputOnly;
import com.projectreddog.machinemod.network.MachineModMessageTEIntFieldToClient;
import com.projectreddog.machinemod.tileentities.TileEntityAssemblyTable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.IContainerListener;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ContainerAssemblyTable extends Container {

	protected TileEntityAssemblyTable assemblyTable;
	protected int lasttotalWorkNeededForThisTask;
	protected int lastworkConsumedForThisTask;
	protected int lastHasBuildProject;

	public ContainerAssemblyTable(InventoryPlayer playerInventory, TileEntityAssemblyTable assemblyTable) {
		this.assemblyTable = assemblyTable;
		lasttotalWorkNeededForThisTask = -1;
		lastworkConsumedForThisTask = -1;
		lastHasBuildProject = -1;
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 1; j++) {
				addSlot(new SlotBlueprint(assemblyTable, j + i * 9, (8 + j * 18), (18 + i * 18) - 2));
			}
		}

		addSlot(new SlotOutputOnly(assemblyTable, 1, 113, 16));

		// commonly used vanilla code that adds the player's inventory
		bindPlayerInventory(playerInventory);
	}

	@Override
	public boolean canInteractWith(PlayerEntity player) {
		return true;
	}

	protected void bindPlayerInventory(PlayerInventory inventoryPlayer) {
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
			if (slot < 2) {
				if (!this.mergeItemStack(stackInSlot, 2, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			}
			// places it into the tileEntity is possible since its in the player
			// inventory
			else if (!this.mergeItemStack(stackInSlot, 0, 2, false)) {
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

	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener icrafting = (IContainerListener) this.listeners.get(i);

			if (this.lasttotalWorkNeededForThisTask != this.assemblyTable.getField(0)) {
				// icrafting.sendWindowProperty(this, 0, this.assemblyTable.getField(0));
				if (icrafting instanceof ServerPlayerEntity) {
					ServerPlayerEntity ep = (ServerPlayerEntity) icrafting;
					ModNetwork.simpleNetworkWrapper.sendTo(new MachineModMessageTEIntFieldToClient(this.assemblyTable.getPos().getX(), this.assemblyTable.getPos().getY(), this.assemblyTable.getPos().getZ(), 0, this.assemblyTable.getField(0)), ep);
				}
			}
			if (this.lastworkConsumedForThisTask != this.assemblyTable.getField(1)) {
				// icrafting.sendWindowProperty(this, 1, this.assemblyTable.getField(1));
				if (icrafting instanceof ServerPlayerEntity) {
					ServerPlayerEntity ep = (ServerPlayerEntity) icrafting;
					ModNetwork.simpleNetworkWrapper.sendTo(new MachineModMessageTEIntFieldToClient(this.assemblyTable.getPos().getX(), this.assemblyTable.getPos().getY(), this.assemblyTable.getPos().getZ(), 1, this.assemblyTable.getField(1)), ep);
				}
			}
			if (this.lastHasBuildProject != this.assemblyTable.getField(2)) {
				// icrafting.sendWindowProperty(this, 2, this.assemblyTable.getField(2));
				if (icrafting instanceof ServerPlayerEntity) {
					ServerPlayerEntity ep = (ServerPlayerEntity) icrafting;
					ModNetwork.simpleNetworkWrapper.sendTo(new MachineModMessageTEIntFieldToClient(this.assemblyTable.getPos().getX(), this.assemblyTable.getPos().getY(), this.assemblyTable.getPos().getZ(), 1, this.assemblyTable.getField(1)), ep);
				}
			}
		}

		this.lasttotalWorkNeededForThisTask = this.assemblyTable.getField(0);
		this.lastworkConsumedForThisTask = this.assemblyTable.getField(1);
		this.lastHasBuildProject = this.assemblyTable.getField(2);

	}

	@OnlyIn(Dist.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.assemblyTable.setField(id, data);
	}
}
