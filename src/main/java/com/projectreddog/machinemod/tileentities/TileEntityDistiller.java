package com.projectreddog.machinemod.tileentities;

import com.projectreddog.machinemod.block.BlockMachineModPrimaryCrusher;
import com.projectreddog.machinemod.iface.IFuelContainer;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;

public class TileEntityDistiller extends TileEntity implements ITickableTileEntity, IFuelContainer, ISidedInventory {
	protected ItemStack[] inventory;

	public final int maxFuelStorage = 1000; // store up to 1k
	public final int inventorySize = 1;
	private static int[] sideSlots = new int[] { 0 };

	public int fuelStorage = 0;
	// public final int coolDownReset = 1200;
	// public int cooldown = coolDownReset;
	public int remainBurnTime = 0;

	public TileEntityDistiller(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
		inventory = new ItemStack[inventorySize];
		for (int i = 0; i < inventorySize; i++) {
			inventory[i] = ItemStack.EMPTY;
		}
	}

	@Override
	public void tick() {
		if (!world.isRemote) {
			// LogHelper.info("TE update entity called");

			if (remainBurnTime > 0) {
				remainBurnTime--;

				transferFuel();
			} else {

				// consume more fuel
				// only if it has mash to process
				if (fuelStorage > 0) {
					// use the furnace's default burn times
					remainBurnTime = FurnaceTileEntity.getItemBurnTime(this.getStackInSlot(0));
					if (remainBurnTime > 0) {
						// found fuel reduce item stack (AKA consume /brun the item)
						decrStackSize(0, 1);
					}
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

	@Override
	public Direction outputDirection() {
		Direction ef = (Direction) world.getBlockState(this.getPos()).getValue(BlockMachineModPrimaryCrusher.FACING);
		// switch (ef) {
		// case NORTH:
		// return Direction.SOUTH;
		// case SOUTH:
		// return Direction.NORTH;
		// case EAST:
		// return Direction.WEST;
		// case WEST:
		// return Direction.EAST;
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
	public boolean isUsableByPlayer(PlayerEntity playerIn) {
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
				stack = stack.split(amt);
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
	public void openInventory(PlayerEntity playerIn) {

	}

	@Override
	public void closeInventory(PlayerEntity playerIn) {

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
	public int[] getSlotsForFace(Direction side) {
		if (side == Direction.NORTH || side == Direction.SOUTH || side == Direction.EAST || side == Direction.WEST) {
			return sideSlots;
		}
		int[] topSlots2 = new int[] { 0 };
		return topSlots2;

	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemStackIn, Direction direction) {
		if (slot < inventorySize && (direction == Direction.NORTH || direction == Direction.SOUTH || direction == Direction.EAST || direction == Direction.WEST)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, Direction direction) {
		if (slot < inventorySize && (direction == Direction.NORTH || direction == Direction.SOUTH || direction == Direction.EAST || direction == Direction.WEST)) {
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
