package com.projectreddog.machinemod.tileentities;

import com.projectreddog.machinemod.iface.IFuelContainer;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileEntityWellHead extends TileEntity implements IUpdatePlayerListBox, IFuelContainer {

	public final int maxFuelStorage = 1000; // store up to 1k
	private static int[] sideSlots = new int[] { 0 };

	public int fuelStorage = 0;
	// public final int coolDownReset = 1200;
	// public int cooldown = coolDownReset;
	public int remainBurnTime = 0;

	public TileEntityWellHead() {

	}

	@Override
	public void update() {
		if (!worldObj.isRemote) {
			// LogHelper.info("TE update entity called");

			if (remainBurnTime > 0) {
				remainBurnTime--;

				transferFuel();
			} else {

				// consume more fuel
				// only if it has mash to process
				if (fuelStorage > 0) {
					// use the furnace's default burn times

				}
			}
		}
	}

	public boolean canAcceptFluid() {
		if (fuelStorage < maxFuelStorage) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		fuelStorage = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "FUEL_STORAGE");
		remainBurnTime = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "BURN_TIME");
		// inventory

	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "FUEL_STORAGE", fuelStorage);
		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "BURN_TIME", remainBurnTime);
		// inventory

	}

	public int addFluid(int amount) {
		int returnAmount;
		if (canAcceptFluid()) {
			if (fuelStorage + amount > maxFuelStorage) {
				// fill to brim return amount left over
				returnAmount = (fuelStorage + amount - maxFuelStorage);

				fuelStorage = maxFuelStorage;
			} else {
				// not going to return any this container can hold all of the fuel
				fuelStorage = fuelStorage + amount;
				returnAmount = 0;
			}
		} else {
			returnAmount = amount;
		}
		return returnAmount;
	}

	public boolean transferFuel() {
		if (this.fuelStorage > 0) {

			if (worldObj.getBlockState(this.pos.offset(this.outputDirection())).getBlock() == ModBlocks.machinefuelpump) {
				// its a distiller so we can transfer fuel!
				TileEntityFuelPump tEC = (TileEntityFuelPump) worldObj.getTileEntity(this.pos.offset(this.outputDirection()));
				if (tEC.canAcceptFluid()) {
					tEC.addFluid(1);
					this.fuelStorage = this.fuelStorage - 1;
					return true;
				}
			}
		}
		return false;
	}

	public int getField(int id) {
		switch (id) {
		case 0:
			return this.fuelStorage;
		case 1:
			return this.remainBurnTime;
		default:
			break;
		}
		return 0;

	}

	public void setField(int id, int value) {
		switch (id) {
		case 0:
			this.fuelStorage = value;
			break;
		case 1:
			this.remainBurnTime = value;
			break;
		default:
			break;
		}

	}

	public int getFieldCount() {
		return 2;
	}

	@Override
	public EnumFacing outputDirection() {
		// TODO Auto-generated method stub
		return null;
	}

}
