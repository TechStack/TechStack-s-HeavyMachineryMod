package com.projectreddog.machinemod.tileentities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

public class TileEntityBatteryBank extends TileEntity implements ITickableTileEntity {

	private int MAX_ENERGY_STORAGE = 10000;
	private int MAX_ENERGY_RECEIVE = 100;
	private int MAX_ENERGY_EXTRACT = 100;
	private EnergyStorage energyStroage = new EnergyStorage(MAX_ENERGY_STORAGE, MAX_ENERGY_RECEIVE, MAX_ENERGY_EXTRACT, 0);

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityEnergy.ENERGY) {
			return (T) energyStroage;
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void tick() {
		if (!this.world.isRemote) {
			// server

			this.transferPower(this.pos.up(), EnumFacing.DOWN);
			this.transferPower(this.pos.down(), EnumFacing.UP);

			this.transferPower(this.pos.north(), EnumFacing.SOUTH);
			this.transferPower(this.pos.south(), EnumFacing.NORTH);

			this.transferPower(this.pos.east(), EnumFacing.WEST);
			this.transferPower(this.pos.west(), EnumFacing.EAST);

		}
	}

	private void transferPower(BlockPos bp, EnumFacing ef) {
		TileEntity te = this.world.getTileEntity(bp);
		if (te != null) {
			if (te.hasCapability(CapabilityEnergy.ENERGY, ef)) {
				IEnergyStorage remoteES = te.getCapability(CapabilityEnergy.ENERGY, ef);

				int maxTransferExtract = this.energyStroage.extractEnergy(this.energyStroage.getEnergyStored(), true);
				int maxRemoteTransferIn = remoteES.receiveEnergy(maxTransferExtract, true);

				this.energyStroage.extractEnergy(maxRemoteTransferIn, false);
				remoteES.receiveEnergy(maxRemoteTransferIn, false);

			}
		}

	}

	// NBT data
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putInt("Energy", this.energyStroage.getEnergyStored());

		return super.write(compound);
	}

	@Override
	public void read(CompoundNBT compound) {
		if (compound.contains("Energy")) {
			this.energyStroage = new EnergyStorage(MAX_ENERGY_STORAGE, MAX_ENERGY_RECEIVE, MAX_ENERGY_EXTRACT, compound.getInteger("Energy"));
		}
	}
}
