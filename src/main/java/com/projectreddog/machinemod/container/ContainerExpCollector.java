package com.projectreddog.machinemod.container;

import com.projectreddog.machinemod.tileentities.TileEntityExpCollector;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

;

public class ContainerExpCollector extends Container {

	protected TileEntityExpCollector expCollector;

	int expAmount = 0;

	public ContainerExpCollector(InventoryPlayer inventoryPlayer, TileEntityExpCollector expCollector) {
		this.expCollector = expCollector;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return expCollector.isUsableByPlayer(player);
	}

	/**
	 * Looks for changes made in the container, sends them to every listener.
	 */
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener icrafting = (IContainerListener) this.listeners.get(i);
			if (this.expAmount != this.expCollector.getField(0)) {
				icrafting.sendWindowProperty(this, 0, this.expCollector.getField(0));
			}
		}
		this.expAmount = this.expCollector.getField(0);
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.expCollector.setField(id, data);
	}

}
