package com.projectreddog.machinemod.tileentities;

import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.network.MachineModMessageLiquidPipeToClient;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fml.network.PacketDistributor.TargetPoint;

public class TileEntityLiquidPipe extends TileEntity implements ITickableTileEntity, IFluidTank {

	private boolean connectedNorth = false;
	private boolean connectedSouth = false;
	private boolean connectedEast = false;
	private boolean connectedWest = false;
	private boolean connectedDown = false;
	private boolean connectedUp = false;
	private final int maxLiquidStorage = 100;
	private boolean firstTick = true;
	protected FluidStack fluid;// = new FluidStack(ModBlocks.fluidOil, 0);
	private final int connectionUpdateTimer = Reference.updateConnectionTimer;
	private int ticksSinceLastConnectionUpdate = 0;
	private TileEntity te;
	private int cooldown;

	public TileEntityLiquidPipe() {

	}

	public float getPercentFull(IFluidTank tank) {
		return (((float) tank.getFluidAmount() / tank.getCapacity()));
	}

	public boolean transferFluid(TileEntity te) {
		// pump in first
		if (te instanceof IFluidTank) {
			IFluidTank lc = (IFluidTank) te;
			if ((getPercentFull(lc) > getPercentFull(this)) && (this.world.isBlockPowered(this.pos) || lc instanceof TileEntityLiquidPipe)) {
				// target is fuller than source so Pull!
				if (lc.getFluid() != null) {
					if (this.getFluid() != null) {
						if (lc.getFluid().getFluid() == this.getFluid().getFluid()) {
							// same fluid try to drain
							FluidStack drained = lc.drain(1, false);
							if (drained.amount > 0 && this.fill(drained, false) > 0) {
								drained = lc.drain(1, true);
								this.fill(drained, true);
							}

							// break the for e: Enum loop
							// LogHelper.info(this.getFluidAmount());
							// return true;
						}
					} else {
						// i have no fluid so accept

						FluidStack drained = lc.drain(1, true);
						this.fill(drained, true);
						// break the for e: Enum loop
						// LogHelper.info("SAME" + this.getFluidAmount());
						// return true;
					}
				}
			} else if (getPercentFull(lc) != getPercentFull(this) && (((!(this.world.isBlockPowered(this.pos))) || lc instanceof TileEntityLiquidPipe))) {
				// the lc has less fluid so pump to it
				if (this.getFluid() != null) {
					if (lc.getFluid() != null) {
						if (lc.getFluid().getFluid() == this.getFluid().getFluid()) {
							// same fluid try to drain

							FluidStack drained = this.drain(1, false);

							if (drained.amount > 0 && lc.fill(drained, false) > 0) {
								drained = this.drain(1, true);
								lc.fill(drained, true);
							}
							// break the for e: Enum loop
							// LogHelper.info(this.getFluidAmount());
							// return true;
						}
					} else {
						// the LC has no fluid so accept

						FluidStack drained = this.drain(1, false);

						if (drained.amount > 0 && lc.fill(drained, false) > 0) {
							drained = this.drain(1, true);
							lc.fill(drained, true);
						}
						// break the for e: Enum loop
						// LogHelper.info("SAME" + this.getFluidAmount());
						// return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void tick() {
		if (firstTick) {
			updateConnections();
			firstTick = false;
		}
		if (ticksSinceLastConnectionUpdate > connectionUpdateTimer) {
			updateConnections();
		}

		if (Reference.enableDebugPipeCode) {
			if (!this.world.isRemote) {
				int tempFluidID;
				if (this.getFluid() != null) {
					// TODO FIX LIQUID PIPES
					// Next line is TEMP
					tempFluidID = 0;
					// tempFluidID = this.getFluid().getFluid().getID();
				} else {
					tempFluidID = -1;
				}

				ModNetwork.simpleNetworkWrapper.sendToAllAround(new MachineModMessageLiquidPipeToClient(this.pos.getX(), this.pos.getY(), this.pos.getZ(), this.getFluidAmount(), tempFluidID), new TargetPoint(this.world.provider.getDimension(), this.pos.getX(), this.pos.getY(), this.pos.getZ(), 48));
			}
		}
		ticksSinceLastConnectionUpdate++;
		if (getFluidAmount() <= getCapacity()) {
			// has room for fluid
			if (connectedNorth) {
				// check the TE to the north to see if it has more fluid
				te = this.world.getTileEntity(pos.north());
				if (transferFluid(te)) {
					return;
				}
			}
			// south
			if (connectedSouth) {
				// check the TE to the north to see if it has more fluid

				te = this.world.getTileEntity(pos.south());
				if (transferFluid(te)) {
					return;
				}
			}
			// east
			if (connectedEast) {
				// check the TE to the north to see if it has more fluid

				te = this.world.getTileEntity(pos.east());
				if (transferFluid(te)) {
					return;
				}
			}
			// west
			if (connectedWest) {
				// check the TE to the north to see if it has more fluid

				te = this.world.getTileEntity(pos.west());
				if (transferFluid(te)) {
					return;
				}
			}
			// up
			if (connectedUp) {
				// check the TE to the north to see if it has more fluid

				te = this.world.getTileEntity(pos.up());
				if (transferFluid(te)) {
					return;
				}
			}

			// down
			if (connectedDown) {
				// check the TE to the north to see if it has more fluid

				te = this.world.getTileEntity(pos.down());
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
		for (Direction e : Direction.VALUES) {
			if (this.world.getTileEntity(this.pos.offset(e)) instanceof IFluidTank) {
				// LogHelper.info("Connection point found to the : " + e.toString());
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

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		int x;
		int y;
		int z;

		cooldown = compound.getInt(Reference.MACHINE_MOD_NBT_PREFIX + "COOL_DOWN");

		if (!compound.contains("Empty")) {
			FluidStack fluid = FluidStack.loadFluidStackFromNBT(compound);
			setFluid(fluid);
		} else {
			setFluid(null);
		}

	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		compound.putInt(Reference.MACHINE_MOD_NBT_PREFIX + "COOL_DOWN", cooldown);

		if (fluid != null) {
			fluid.writeToNBT(compound);
		} else {
			compound.putString("Empty", "");
		}
		return compound;
	}
}