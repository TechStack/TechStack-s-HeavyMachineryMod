package com.projectreddog.machinemod.tileentities;

import com.projectreddog.machinemod.iface.ILiquidConnection;
import com.projectreddog.machinemod.iface.ILiquidPipe;
import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileEntityLiquidPipe extends TileEntity implements IUpdatePlayerListBox, ILiquidPipe {

	private boolean connectedNorth = false;
	private boolean connectedSouth = false;
	private boolean connectedEast = false;
	private boolean connectedWest = false;
	private boolean connectedDown = false;
	private boolean connectedUp = false;

	private boolean firstTick = true;

	public TileEntityLiquidPipe() {

	}

	@Override
	public void update() {
		if (firstTick) {
			updateConnections();
			firstTick = false;
		}

	}

	public void updateConnections() {
		connectedNorth = false;
		connectedSouth = false;
		connectedEast = false;
		connectedWest = false;
		connectedDown = false;
		connectedUp = false;
		for (EnumFacing e : EnumFacing.VALUES) {
			if (this.worldObj.getTileEntity(this.pos.offset(e)) instanceof ILiquidConnection) {
				LogHelper.info("Connection point found to the : " + e.toString());
				switch (e) {
				case NORTH:
					connectedNorth = true;
					break;
				case SOUTH:
					connectedSouth = true;
					break;
				case EAST:
					connectedEast = true;
					break;
				case WEST:
					connectedWest = true;
					break;
				case UP:
					connectedUp = true;
					break;
				case DOWN:
					connectedDown = true;
					break;
				}

			}
		}
	}

	public boolean isConnectedNorth() {
		return connectedNorth;
	}

	public boolean isConnectedSouth() {
		return connectedSouth;
	}

	public boolean isConnectedEast() {
		return connectedEast;
	}

	public boolean isConnectedWest() {
		return connectedWest;
	}

	public boolean isConnectedDown() {
		return connectedDown;
	}

	public boolean isConnectedUp() {
		return connectedUp;
	}

}