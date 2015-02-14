package com.projectreddog.machinemod.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import com.projectreddog.machinemod.block.BlockMachineModPrimaryCrusher;
import com.projectreddog.machinemod.iface.IFuelContainer;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.reference.Reference;

public class TileEntityDistiller extends TileEntity implements IUpdatePlayerListBox, IFuelContainer {

	public final int maxFuelStorage = 1000; // store up to 1k
	public int fuelStorage = 0;
	public final int coolDownReset = 1200;
	public int cooldown = coolDownReset;

	public TileEntityDistiller() {

	}

	@Override
	public void update() {
		if (!worldObj.isRemote) {
			// LogHelper.info("TE update entity called");
			cooldown = cooldown - 1;
			if (cooldown <= 0) {
				cooldown = coolDownReset;
				transferFuel();
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

	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "FUEL_STORAGE", fuelStorage);

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
		if (this.fuelStorage > 1) {

			if (worldObj.getBlockState(this.pos.offset(this.outputDirection())).getBlock() == ModBlocks.machinemodcanner) {
				// its a distiller so we can transfer fuel!
				TileEntityCanner tEC = (TileEntityCanner) worldObj.getTileEntity(this.pos.offset(this.outputDirection()));
				if (tEC.canAcceptFluid()) {
					tEC.addFluid(1);
					this.fuelStorage = this.fuelStorage - 1;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public EnumFacing outputDirection() {
		EnumFacing ef = (EnumFacing) worldObj.getBlockState(this.getPos()).getValue(BlockMachineModPrimaryCrusher.FACING);
		switch (ef) {
		case NORTH:
			return EnumFacing.SOUTH;
		case SOUTH:
			return EnumFacing.NORTH;
		case EAST:
			return EnumFacing.WEST;
		case WEST:
			return EnumFacing.EAST;
		default:
			return null;
		}

	}

	public int getField(int id) {
		switch (id) {
		case 0:
			return this.fuelStorage;

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

		default:
			break;
		}

	}

	public int getFieldCount() {
		return 1;
	}

	public boolean isUseableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
		return false;
	}

}
