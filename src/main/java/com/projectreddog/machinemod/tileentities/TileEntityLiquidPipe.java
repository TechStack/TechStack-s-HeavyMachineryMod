package com.projectreddog.machinemod.tileentities;

import com.projectreddog.machinemod.iface.ILiquidConnection;
import com.projectreddog.machinemod.iface.ILiquidPipe;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.network.MachineModMessageLiquidPipeToClient;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class TileEntityLiquidPipe extends TileEntity implements IUpdatePlayerListBox, ILiquidPipe {

	private boolean connectedNorth = false;
	private boolean connectedSouth = false;
	private boolean connectedEast = false;
	private boolean connectedWest = false;
	private boolean connectedDown = false;
	private boolean connectedUp = false;
	private final int maxLiquidStorage = 1000;
	private boolean firstTick = true;
	protected FluidStack fluid = new FluidStack(ModBlocks.fluidOil, 0);
	private final int connectionUpdateTimer = Reference.updateConnectionTimer;
	private int ticksSinceLastConnectionUpdate = 0;
	private TileEntity te;

	public TileEntityLiquidPipe() {

	}

	public boolean transferFluid(TileEntity te) {
		// pump in first
		if (te instanceof ILiquidConnection) {
			ILiquidConnection lc = (ILiquidConnection) te;
			if (lc.getFluidAmount() >= getFluidAmount()) {
				if (lc.getFluid() != null) {
					if (this.getFluid() != null) {
						if (lc.getFluid().getFluid() == this.getFluid().getFluid()) {
							// same fluid try to drain
							FluidStack drained = lc.drain(1, true);
							this.fill(drained, true);
							// break the for e: Enum loop
							LogHelper.info(this.getFluidAmount());
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public void update() {
		if (firstTick) {
			updateConnections();
			firstTick = false;
		}
		if (ticksSinceLastConnectionUpdate > connectionUpdateTimer) {
			updateConnections();
		}

		if (!this.worldObj.isRemote) {
			int tempFluidID;
			if (this.getFluid() != null) {
				tempFluidID = this.getFluid().getFluidID();
			} else {
				tempFluidID = -1;
			}

			ModNetwork.simpleNetworkWrapper.sendToAllAround(new MachineModMessageLiquidPipeToClient(this.pos.getX(), this.pos.getY(), this.pos.getZ(), this.getFluidAmount(), tempFluidID), new TargetPoint(this.worldObj.provider.getDimensionId(), this.pos.getX(), this.pos.getY(), this.pos.getZ(), 48));
		}

		ticksSinceLastConnectionUpdate++;
		if (getFluidAmount() < getCapacity()) {
			// has room for fluid
			if (connectedNorth) {
				// check the TE to the north to see if it has more fluid
				te = this.worldObj.getTileEntity(pos.north());
				if (transferFluid(te)) {
					return;
				}
			}
			// south
			if (connectedSouth) {
				// check the TE to the north to see if it has more fluid

				te = this.worldObj.getTileEntity(pos.south());
				if (transferFluid(te)) {
					return;
				}
			}
			// east
			if (connectedEast) {
				// check the TE to the north to see if it has more fluid

				te = this.worldObj.getTileEntity(pos.east());
				if (transferFluid(te)) {
					return;
				}
			}
			// west
			if (connectedWest) {
				// check the TE to the north to see if it has more fluid

				te = this.worldObj.getTileEntity(pos.west());
				if (transferFluid(te)) {
					return;
				}
			}
			// up
			if (connectedUp) {
				// check the TE to the north to see if it has more fluid

				te = this.worldObj.getTileEntity(pos.up());
				if (transferFluid(te)) {
					return;
				}
			}

			// down
			if (connectedDown) {
				// check the TE to the north to see if it has more fluid

				te = this.worldObj.getTileEntity(pos.down());
				if (transferFluid(te)) {
					return;
				}
			}

		}

	}

	public void updateConnections() {
		ticksSinceLastConnectionUpdate = 0;
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

	@Override
	public FluidStack getFluid() {
		return fluid;
	}

	@Override
	public int getFluidAmount() {

		if (fluid == null) {
			return 0;
		}
		return fluid.amount;
	}

	@Override
	public int getCapacity() {

		return this.maxLiquidStorage;
	}

	@Override
	public FluidTankInfo getInfo() {
		return new FluidTankInfo(this);
	}

	public void setFluid(FluidStack fluid) {
		this.fluid = fluid;
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (resource == null) {
			return 0;
		}

		if (!doFill) {
			if (fluid == null) {
				return Math.min(maxLiquidStorage, resource.amount);
			}

			if (!fluid.isFluidEqual(resource)) {
				return 0;
			}

			return Math.min(maxLiquidStorage - fluid.amount, resource.amount);
		}

		if (fluid == null) {
			fluid = new FluidStack(resource, Math.min(maxLiquidStorage, resource.amount));

			if (this != null) {
				FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(fluid, this.getWorld(), this.getPos(), this, fluid.amount));
			}
			return fluid.amount;
		}

		if (!fluid.isFluidEqual(resource)) {
			return 0;
		}
		int filled = maxLiquidStorage - fluid.amount;

		if (resource.amount < filled) {
			fluid.amount += resource.amount;
			filled = resource.amount;
		} else {
			fluid.amount = maxLiquidStorage;
		}

		if (this != null) {
			FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(fluid, this.getWorld(), this.getPos(), this, filled));
		}
		return filled;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if (fluid == null) {
			return null;
		}

		int drained = maxDrain;
		if (fluid.amount < drained) {
			drained = fluid.amount;
		}

		FluidStack stack = new FluidStack(fluid, drained);
		if (doDrain) {
			fluid.amount -= drained;
			if (fluid.amount <= 0) {
				fluid = null;
			}

			if (this != null) {
				FluidEvent.fireEvent(new FluidEvent.FluidDrainingEvent(fluid, this.getWorld(), this.getPos(), this, drained));
			}
		}
		return stack;
	}
}