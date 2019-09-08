package com.projectreddog.machinemod.container;

import com.projectreddog.machinemod.tileentities.TileEntityHoloScanner;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerHoloMiner extends Container {
	private TileEntityHoloScanner tell;
	private int lastfront = -1;
	private int lastright = -1;
	private int lastup = -1;

	public ContainerHoloMiner(InventoryPlayer inventoryPlayer, TileEntityHoloScanner tell) {
		this.tell = tell;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	/**
	 * Looks for changes made in the container, sends them to every listener.
	 */
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener icrafting = (IContainerListener) this.listeners.get(i);

			if (this.lastfront != this.tell.getField(0)) {
				icrafting.sendWindowProperty(this, 0, this.tell.getField(0));

			}
			if (this.lastright != this.tell.getField(1)) {
				icrafting.sendWindowProperty(this, 1, this.tell.getField(1));

			}

			if (this.lastup != this.tell.getField(2)) {
				icrafting.sendWindowProperty(this, 2, this.tell.getField(2));

			}

		}

		this.tell.front = this.tell.getField(0);
		this.tell.right = this.tell.getField(1);
		this.tell.up = this.tell.getField(2);

	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.tell.setField(id, data);
	}
}
