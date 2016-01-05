package com.projectreddog.machinemod.tileentities;

import java.util.List;

import com.projectreddog.machinemod.entity.EntitySemiTractor;
import com.projectreddog.machinemod.iface.ILiquidConnection;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.item.trailer.ItemSemiTrailerTanker;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

public class TileEntityFractionalDistillation extends TileEntity implements IUpdatePlayerListBox, IFluidTank, ILiquidConnection {

	public final int maxOilStorage = 10000; // store up to 100k

	public AxisAlignedBB boundingBox;
	public int coolDownAmount = 5;
	public int timeTillCoolDown = 0;

	public final int BlastedStoneOreMultiplier = 3;
	public final int VanillaOreMultiplier = 2;
	public final int BlastedStoneCoalMultiplier = 3;
	public final int BlastedStoneGemMultiplier = 2;
	public final int BlastedStoneLapisMultiplier = 12;
	public final int BlastedStoneRedstoneMultiplier = 8;
	protected FluidStack fluid = new FluidStack(ModBlocks.fluidOil, 0);
	public int transferOilAmount = 10;

	public TileEntityFractionalDistillation() {

	}

	@Override
	public void update() {
		if (!worldObj.isRemote) {

			if (amIBottom()) {

				if (timeTillCoolDown > 0) {
					timeTillCoolDown--;
					return;
				}
				timeTillCoolDown = coolDownAmount;

				// LogHelper.info("TE update entity called");
				boundingBox = new AxisAlignedBB(this.pos.north(3).west(3).down(1), this.pos.south(3).east(3).up(1));
				List list = worldObj.getEntitiesWithinAABB(EntitySemiTractor.class, boundingBox);
				processEntitiesInList(list);

			}
		} else {
			// is not do nothing !
		}

	}

	public boolean amIBottom() {
		if (this.worldObj.getBlockState(pos.down()).getBlock() == ModBlocks.machinefractionaldistillation) {
			return false;
		}
		return true;
	}

	private void processEntitiesInList(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity != null) {
				if (entity instanceof EntitySemiTractor) {
					EntitySemiTractor est = (EntitySemiTractor) entity;

					if (est.getStackInSlot(0) != null) {
						if (est.getStackInSlot(0).getItem() instanceof ItemSemiTrailerTanker) {

							if (!est.isDead) {

								if (est.getFluidAmount() >= transferOilAmount) {
									if (est.getFluid() != null) {
										if (fluid != null) {
											if (est.getFluid().getFluid() == fluid.getFluid()) {
												FluidStack moveStack = new FluidStack(fluid, transferOilAmount);

												fill(est.drain(transferOilAmount, true), true);
											}
										}
									}
								}
							}
						}
					}
				}
				// entity.setPosition(entity.getPosition().getX() + 0.1d, entity.getPosition().getY(), entity.getPosition().getZ());
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {

		super.readFromNBT(compound);

		timeTillCoolDown = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "COOLDOWN");

		if (!compound.hasKey("Empty")) {
			FluidStack fluid = FluidStack.loadFluidStackFromNBT(compound);
			setFluid(fluid);
		} else {
			setFluid(null);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "COOLDOWN", timeTillCoolDown);
		if (fluid != null) {
			fluid.writeToNBT(compound);
		} else {
			compound.setString("Empty", "");
		}
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

		return this.maxOilStorage;
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
				return Math.min(maxOilStorage, resource.amount);
			}

			if (!fluid.isFluidEqual(resource)) {
				return 0;
			}

			return Math.min(maxOilStorage - fluid.amount, resource.amount);
		}

		if (fluid == null) {
			fluid = new FluidStack(resource, Math.min(maxOilStorage, resource.amount));

			if (this != null) {
				FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(fluid, this.getWorld(), this.getPos(), this, fluid.amount));
			}
			return fluid.amount;
		}

		if (!fluid.isFluidEqual(resource)) {
			return 0;
		}
		int filled = maxOilStorage - fluid.amount;

		if (resource.amount < filled) {
			fluid.amount += resource.amount;
			filled = resource.amount;
		} else {
			fluid.amount = maxOilStorage;
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
