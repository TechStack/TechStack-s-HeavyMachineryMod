package com.projectreddog.machinemod.tileentities;

import com.projectreddog.machinemod.block.BlockMachineModPrimaryCrusher;
import com.projectreddog.machinemod.iface.IFuelContainer;
import com.projectreddog.machinemod.iface.IWorkConsumer;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;

public class TileEntityFactory extends TileEntity implements ITickableTileEntity, IFuelContainer, ISidedInventory {
	protected ItemStack[] inventory;

	public final int maxFuelStorage = 1000; // store up to 1k
	public final int inventorySize = 1;
	private static int[] sideSlots = new int[] { 0 };

	public int fuelStorage = 0;
	// public final int coolDownReset = 1200;
	// public int cooldown = coolDownReset;
	public int remainBurnTime = 0;

	private int MAX_ENERGY_STORAGE = 1000;
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

	public TileEntityFactory() {
		inventory = new ItemStack[inventorySize];
		for (int i = 0; i < inventorySize; i++) {
			inventory[i] = ItemStack.EMPTY;
		}
	}

	@Override
	public void tick() {
		if (!world.isRemote) {
			IWorkConsumer wc = getWorkConsumer(getTargetLocation());
			if (energyStroage.getEnergyStored() > 50) {
				if (wc != null) {
					if (wc.isWorkNeeded()) {
						energyStroage.extractEnergy(50, false);
						wc.appyWork(10);
					}
				}
			}
		}

		// LogHelper.info("Energy Level:" + energyStroage.getEnergyStored());

	}

	public IWorkConsumer getWorkConsumer(BlockPos bp) {
		TileEntity te = this.world.getTileEntity(bp);
		if (te instanceof IWorkConsumer) {
			return (IWorkConsumer) te;
		}

		return null;
	}

	public BlockPos getTargetLocation() {

		return this.pos.offset(outputDirection(), 3);

	}

	public boolean canAcceptFluid() {
		if (fuelStorage < maxFuelStorage) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		fuelStorage = compound.getInt(Reference.MACHINE_MOD_NBT_PREFIX + "FUEL_STORAGE");
		remainBurnTime = compound.getInt(Reference.MACHINE_MOD_NBT_PREFIX + "BURN_TIME");
		// inventory
		ListNBT tagList = compound.getList(Reference.MACHINE_MOD_NBT_PREFIX + "Inventory", compound.getId());
		for (int i = 0; i < tagList.size(); i++) {
			CompoundNBT tag = (CompoundNBT) tagList.getCompound(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inventory.length) {
				inventory[slot] = ItemStack.read(tag);
			}
		}
		if (compound.contains("Energy")) {
			this.energyStroage = new EnergyStorage(MAX_ENERGY_STORAGE, MAX_ENERGY_RECEIVE, MAX_ENERGY_EXTRACT, compound.getInt("Energy"));
		}
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		compound.putInt(Reference.MACHINE_MOD_NBT_PREFIX + "FUEL_STORAGE", fuelStorage);
		compound.putInt(Reference.MACHINE_MOD_NBT_PREFIX + "BURN_TIME", remainBurnTime);
		// inventory
		ListNBT itemList = new ListNBT();
		for (int i = 0; i < inventory.length; i++) {
			ItemStack stack = inventory[i];
			if (!stack.isEmpty()) {
				CompoundNBT tag = new CompoundNBT();
				tag.putByte("Slot", (byte) i);
				stack.write(tag);
				itemList.add(tag);
			}
		}
		compound.put(Reference.MACHINE_MOD_NBT_PREFIX + "Inventory", itemList);

		compound.putInt("Energy", this.energyStroage.getEnergyStored());
		return compound;

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

			if (world.getBlockState(this.pos.offset(this.outputDirection())).getBlock() == ModBlocks.machinefuelpump) {
				// its a distiller so we can transfer fuel!
				TileEntityFuelPump tEC = (TileEntityFuelPump) world.getTileEntity(this.pos.offset(this.outputDirection()));
				if (tEC.canAcceptFluid()) {
					tEC.addFluid(1);
					this.fuelStorage = this.fuelStorage - 1;
					return true;
				}
			}
		}
		return false;
	}

	// KEEP ME EVEN IF WE REMOVE THE FUEL interface
	public EnumFacing outputDirection() {
		EnumFacing ef = (EnumFacing) world.getBlockState(this.getPos()).getValue(BlockMachineModPrimaryCrusher.FACING);
		// switch (ef) {
		// case NORTH:
		// return EnumFacing.SOUTH;
		// case SOUTH:
		// return EnumFacing.NORTH;
		// case EAST:
		// return EnumFacing.WEST;
		// case WEST:
		// return EnumFacing.EAST;
		// default:
		// return null;
		// }
		return ef;
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
	public boolean isUsableByPlayer(EntityPlayer playerIn) {
		return playerIn.getDistanceSq(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ()) < 64;
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amt) {
		ItemStack stack = getStackInSlot(slot);
		if (!stack.isEmpty()) {
			if (stack.getCount() <= amt) {
				setInventorySlotContents(slot, ItemStack.EMPTY);
			} else {
				stack = stack.splitStack(amt);
				if (stack.getCount() == 0) {
					setInventorySlotContents(slot, ItemStack.EMPTY);
				}

			}
		}
		return stack;
	}

	@Override
	public ItemStack removeStackFromSlot(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (!stack.isEmpty()) {
			setInventorySlotContents(slot, ItemStack.EMPTY);
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;
		if (!stack.isEmpty() && stack.getCount() > getInventoryStackLimit()) {
			stack.setCount(getInventoryStackLimit());
		}

	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void openInventory(EntityPlayer playerIn) {

	}

	@Override
	public void closeInventory(EntityPlayer playerIn) {

	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public void clear() {
		for (int i = 0; i < inventory.length; ++i) {
			inventory[i] = ItemStack.EMPTY;
		}
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
		return null;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		if (side == EnumFacing.NORTH || side == EnumFacing.SOUTH || side == EnumFacing.EAST || side == EnumFacing.WEST) {
			return sideSlots;
		}
		int[] topSlots2 = new int[] { 0 };
		return topSlots2;

	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemStackIn, EnumFacing direction) {
		if (slot < inventorySize && (direction == EnumFacing.NORTH || direction == EnumFacing.SOUTH || direction == EnumFacing.EAST || direction == EnumFacing.WEST)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, EnumFacing direction) {
		if (slot < inventorySize && (direction == EnumFacing.NORTH || direction == EnumFacing.SOUTH || direction == EnumFacing.EAST || direction == EnumFacing.WEST)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		for (int i = 0; i < inventory.length; i++) {
			if (!inventory[i].isEmpty()) {
				return false;
			}
		}

		return true;
	}
}
