package com.projectreddog.machinemod.container;

import com.projectreddog.machinemod.tileentities.TileEntityTowerCrane;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

;

public class ContainerTowerCraneSettings extends Container {

	protected TileEntityTowerCrane towercrane;

	protected int lastFuleBurnTimeRemaining = 0;
	protected int lastProcessingTimeRemaining = 0;

	public ContainerTowerCraneSettings(InventoryPlayer inventoryPlayer, TileEntityTowerCrane towercrane) {
		this.towercrane = towercrane;

		lastFuleBurnTimeRemaining = -1;
		lastProcessingTimeRemaining = -1;

	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return towercrane.isUsableByPlayer(player);
	}

	/**
	 * Looks for changes made in the container, sends them to every listener.
	 */
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener icrafting = (IContainerListener) this.listeners.get(i);
			if (this.lastFuleBurnTimeRemaining != this.towercrane.getField(0)) {
				icrafting.sendWindowProperty(this, 0, this.towercrane.getField(0));
			}

			if (this.lastProcessingTimeRemaining != this.towercrane.getField(1)) {
				icrafting.sendWindowProperty(this, 1, this.towercrane.getField(1));
			}

		}

		this.lastFuleBurnTimeRemaining = this.towercrane.getField(0);
		this.lastProcessingTimeRemaining = this.towercrane.getField(1);
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.towercrane.setField(id, data);
	}

}
