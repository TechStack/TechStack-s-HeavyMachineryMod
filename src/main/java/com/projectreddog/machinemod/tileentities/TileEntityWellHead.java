package com.projectreddog.machinemod.tileentities;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

public class TileEntityWellHead extends TileEntity implements IUpdatePlayerListBox {

	public final int maxOilStorage = 100000; // store up to 100k

	public int oilStorage = 1000;
	public BlockPos currentOilDeposit;
	public int blocksFound = 0;
	public final int coolDownReset = 2400;
	public int cooldown = coolDownReset;

	public TileEntityWellHead() {

	}

	@Override
	public void update() {
		if (!worldObj.isRemote) {

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
						if (this.worldObj.getBlockState(currentPos).getBlock() == ModBlocks.machinecrudeoilstone) {
							currentOilDeposit = currentPos;
							return;
						}
					}
				}
			}
		} else {
			// has deposit Do nothing for now Later we must check if
			// it has been fully depleted
			if (this.worldObj.getBlockState(currentOilDeposit).getBlock() == ModBlocks.machinecrudeoilstone) {
				return;
			} else {
				currentOilDeposit = null;
			}
		}
	}

	public void pumpOil() {
		worldObj.setBlockState(currentOilDeposit, Blocks.air.getDefaultState());
		oilStorage = oilStorage + 1000;
		if (oilStorage > maxOilStorage) {
			oilStorage = maxOilStorage;
		}
		blocksFound = blocksFound + 1;
		LogHelper.info("Oil Pumped blocks found so far:" + blocksFound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		int x;
		int y;
		int z;
		oilStorage = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "OIL_STORAGE");
		cooldown = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "COOL_DOWN");

		if (compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "CURRENT_OIL_DEPOSIT_NULL") == 0) {
			x = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "CURRENT_OIL_DEPOSIT_X");
			y = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "CURRENT_OIL_DEPOSIT_Y");
			z = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "CURRENT_OIL_DEPOSIT_Z");
			currentOilDeposit = new BlockPos(x, y, z);
		} else {
			currentOilDeposit = null;
		}

	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "OIL_STORAGE", oilStorage);
		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "COOL_DOWN", cooldown);

		if (currentOilDeposit != null) {
			compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "CURRENT_OIL_DEPOSIT_NULL", 0);
			compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "CURRENT_OIL_DEPOSIT_X", currentOilDeposit.getX());
			compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "CURRENT_OIL_DEPOSIT_Y", currentOilDeposit.getY());
			compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "CURRENT_OIL_DEPOSIT_Z", currentOilDeposit.getZ());
		}
		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "CURRENT_OIL_DEPOSIT_NULL", 1);

	}

	public int getFieldCount() {
		return 2;
	}

}
