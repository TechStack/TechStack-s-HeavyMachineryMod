package com.projectreddog.machinemod.tileentities;

import java.util.List;

import com.projectreddog.machinemod.entity.EntitySemiTractor;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.item.trailer.ItemSemiTrailerTanker;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

public class TileEntityWellHead extends TileEntity implements ITickable, IFluidTank {

	public final int maxOilStorage = 100000; // store up to 100k

	// public int capacity = 0;
	public BlockPos currentOilDeposit;
	public int blocksFound = 0;
	public final int coolDownReset = 240;
	public int cooldown = coolDownReset;
	protected FluidStack fluid = new FluidStack(ModBlocks.fluidOil, 0);
	public int transferOilAmount = 10;

	public TileEntityWellHead() {

	}

	@Override
	public void update() {
		if (!world.isRemote) {

			cooldown = cooldown - 1;
			// LogHelper.info("TE FERMENTER CD" + cooldown);
			if (cooldown <= 0) {
				cooldown = coolDownReset;
				// LogHelper.info("TE update entity called");
				findOilDepoist();

				if (currentOilDeposit != null) {
					pumpOil();
				}

			}
			transferOil();

		}
	}

	public void transferOil() {
		// find nearby oil tanker trucks and pump oil to them!
		AxisAlignedBB pumpboundingBox = new AxisAlignedBB(this.pos.north(3).west(3).down(1), this.pos.south(3).east(3).up(1));

		// List list = this.world.getEntitiesWithinAABBExcludingEntity(this, pumpboundingBox);
		List list = this.world.getEntitiesWithinAABB(EntitySemiTractor.class, pumpboundingBox);

		collidedEntitiesInList(list);
	}

	private void collidedEntitiesInList(List par1List) {

		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity != null) {
				if (entity instanceof EntitySemiTractor) {
					EntitySemiTractor semi = (EntitySemiTractor) entity;
					if (semi.getStackInSlot(0) != null) {
						if (semi.getStackInSlot(0).getItem() instanceof ItemSemiTrailerTanker) {

							if (!semi.isDead) {

								if (getFluidAmount() >= transferOilAmount) {
									FluidStack moveStack = new FluidStack(fluid, transferOilAmount);

									fill(drain(semi.fill(moveStack, true), true), true);
								}

							}
						}
					}
				}
			}
		}
	}

	public void findOilDepoist() {
		if (currentOilDeposit == null) {
			// Reference.crudeOilStoneGenMinlevel
			// Reference.wellHeadMaxRange
			BlockPos currentPos;
			for (int x = this.getPos().getX() - Reference.wellHeadMaxRange; x <= this.getPos().getX() + Reference.wellHeadMaxRange; x++) {
				for (int z = this.getPos().getZ() - Reference.wellHeadMaxRange; z <= this.getPos().getZ() + Reference.wellHeadMaxRange; z++) {
					for (int y = Reference.crudeOilStoneGenMinlevel; y <= Reference.crudeOilStoneGenMaxlevel; y++) {
						currentPos = new BlockPos(x, y, z);
						if (this.world.getBlockState(currentPos).getBlock() == ModBlocks.machinecrudeoilstone) {
							currentOilDeposit = currentPos;
							return;
						}
					}
				}
			}
		} else {
			// has deposit Do nothing for now Later we must check if
			// it has been fully depleted
			if (this.world.getBlockState(currentOilDeposit).getBlock() == ModBlocks.machinecrudeoilstone) {
				return;
			} else {
				currentOilDeposit = null;
			}
		}
	}

	public void pumpOil() {
		world.setBlockState(currentOilDeposit, Blocks.AIR.getDefaultState());
		fill(new FluidStack(ModBlocks.fluidOil, 1000), true);

		blocksFound = blocksFound + 1;
		LogHelper.info("Oil Pumped blocks found so far:" + blocksFound);

		LogHelper.info("Oil amount : " + fluid.amount);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		int x;
		int y;
		int z;

		cooldown = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "COOL_DOWN");

		if (compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "CURRENT_OIL_DEPOSIT_NULL") == 0) {
			x = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "CURRENT_OIL_DEPOSIT_X");
			y = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "CURRENT_OIL_DEPOSIT_Y");
			z = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "CURRENT_OIL_DEPOSIT_Z");
			currentOilDeposit = new BlockPos(x, y, z);
		} else {
			currentOilDeposit = null;
		}

		if (!compound.hasKey("Empty")) {
			FluidStack fluid = FluidStack.loadFluidStackFromNBT(compound);
			setFluid(fluid);
		} else {
			setFluid(null);
		}

	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "COOL_DOWN", cooldown);

		if (currentOilDeposit != null) {
			compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "CURRENT_OIL_DEPOSIT_NULL", 0);
			compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "CURRENT_OIL_DEPOSIT_X", currentOilDeposit.getX());
			compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "CURRENT_OIL_DEPOSIT_Y", currentOilDeposit.getY());
			compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "CURRENT_OIL_DEPOSIT_Z", currentOilDeposit.getZ());
		}
		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "CURRENT_OIL_DEPOSIT_NULL", 1);
		if (fluid != null) {
			fluid.writeToNBT(compound);
		} else {
			compound.setString("Empty", "");
		}
		return compound;
	}

	public int getFieldCount() {
		return 2;
	}

	/////////////////////////////////////

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
