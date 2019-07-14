package com.projectreddog.machinemod.tileentities;

import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

public class TileEntityConduit extends TileEntity implements ITickableTileEntity {

	private int MAX_ENERGY_STORAGE = 100;
	private int MAX_ENERGY_RECEIVE = 100;
	private int MAX_ENERGY_EXTRACT = 100;
	private EnergyStorage energyStroage = new EnergyStorage(MAX_ENERGY_STORAGE, MAX_ENERGY_RECEIVE, MAX_ENERGY_EXTRACT, 0);

	@Override
	public boolean hasCapability(Capability<?> capability, Direction facing) {
		if (capability == CapabilityEnergy.ENERGY) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, Direction facing) {
		if (capability == CapabilityEnergy.ENERGY) {
			return (T) energyStroage;
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void tick() {
		if (!this.world.isRemote) {
			// server

			if (this.energyStroage.getEnergyStored() > 0) {
				LogHelper.info("conduit at : " + this.pos + " I Have : " + this.energyStroage.getEnergyStored());
			}
			this.transferPower(this.pos.up(), Direction.DOWN);
			this.transferPower(this.pos.down(), Direction.UP);

			this.transferPower(this.pos.north(), Direction.SOUTH);
			this.transferPower(this.pos.south(), Direction.NORTH);

			this.transferPower(this.pos.east(), Direction.WEST);
			this.transferPower(this.pos.west(), Direction.EAST);

		}
	}

	private void transferPower(BlockPos bp, Direction ef) {
		TileEntity te = this.world.getTileEntity(bp);
		if (te != null) {
			if (te.hasCapability(CapabilityEnergy.ENERGY, ef)) {
				IEnergyStorage remoteES = te.getCapability(CapabilityEnergy.ENERGY, ef);

				int maxTransferExtract = this.energyStroage.extractEnergy(this.energyStroage.getEnergyStored(), true);
				int maxRemoteTransferIn = remoteES.receiveEnergy(maxTransferExtract, true);

				this.energyStroage.extractEnergy(maxRemoteTransferIn, false);
				remoteES.receiveEnergy(maxRemoteTransferIn, false);
				if (maxRemoteTransferIn > 0) {
					LogHelper.info("conduit at : " + this.pos + " I just transfered: " + maxRemoteTransferIn + " to " + bp + " on " + ef);
				}

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
			this.energyStroage = new EnergyStorage(MAX_ENERGY_STORAGE, MAX_ENERGY_RECEIVE, MAX_ENERGY_EXTRACT, compound.getInt("Energy"));
		}
	}
}
