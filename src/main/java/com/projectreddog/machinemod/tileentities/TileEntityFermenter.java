package com.projectreddog.machinemod.tileentities;

import com.projectreddog.machinemod.block.BlockMachineModPrimaryCrusher;
import com.projectreddog.machinemod.iface.IFuelContainer;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityFermenter extends TileEntity implements ITickableTileEntity, ISidedInventory, IFuelContainer {
	protected ItemStack[] inventory;
	private static int[] sideSlots = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
	public AxisAlignedBB boundingBox;

	public final int maxFuelStorage = 1000; // store up to 1k
	public int fuelStorage = 0;
	public final int inventorySize = 9;
	public final int fuelAmountFromCorn = 250;
	public final int coolDownReset = 1200;
	public int cooldown = coolDownReset;

	public TileEntityFermenter(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
		inventory = new ItemStack[inventorySize];
		for (int i = 0; i < inventorySize; i++) {
			inventory[i] = ItemStack.EMPTY;
		}
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

	public boolean canAcceptFluid() {
		if (fuelStorage < maxFuelStorage) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void tick() {
		if (!world.isRemote) {
			// LogHelper.info("TE update entity called");

			// LogHelper.info("TE update entity called");
			cooldown = cooldown - 1;
			// LogHelper.info("TE FERMENTER CD" + cooldown);
			if (cooldown <= 0) {
				cooldown = coolDownReset;
				for (int i = 0; i < this.getSizeInventory(); i++) {
					ItemStack item = this.getStackInSlot(i);
					if (!item.isEmpty()) {
						if (item.getItem() == ModItems.cornseed) {
							produceFuel(i);
							// only process one at a time so dont check the next slot!
							i = this.getSizeInventory();
						}
					}
				}

			}
			// always transfer fule
			transferFuel();
		}
	}

	public boolean transferFuel() {
		if (this.fuelStorage > 0) {

			if (world.getBlockState(this.pos.offset(this.outputDirection())).getBlock() == ModBlocks.machinedistiller) {
				// its a distiller so we can transfer fuel!
				TileEntityDistiller tED = (TileEntityDistiller) world.getTileEntity(this.pos.offset(this.outputDirection()));
				if (tED.canAcceptFluid()) {
					tED.addFluid(1);
					this.fuelStorage = this.fuelStorage - 1;
					this.markDirty();
					return true;
				}
			}
		}
		return false;
	}

	public boolean produceFuel(int slot) {
		// turn the corn in this slot into fuel!
		if (canAcceptFluid()) {
			decrStackSize(slot, 1);
			addFluid(fuelAmountFromCorn);// any excess will go to waste :O
			this.markDirty();
			return true;
		} else {
			return false;
		}
	}

	protected ItemStack addToinventory(ItemStack is) {
		int i = getSizeInventory();
		for (int j = 0; j < i && !is.isEmpty() && is.getCount() > 0; ++j) {
			if (!is.isEmpty()) {

				if (!getStackInSlot(j).isEmpty()) {
					if (getStackInSlot(j).getItem() == is.getItem() && getStackInSlot(j).getDamage() == is.getDamage()) {
						// same item remove from is put into slot any amt not to
						// excede stack max
						if (getStackInSlot(j).getCount() < getStackInSlot(j).getMaxStackSize()) {
							// we have room to add to this stack
							if (is.getCount() <= getStackInSlot(j).getMaxStackSize() - getStackInSlot(j).getCount()) {
								// /all of the stack will fit in this slot do
								// so.

								setInventorySlotContents(j, new ItemStack(getStackInSlot(j).getItem(), getStackInSlot(j).getCount() + is.getCount(), is.getDamage()));
								is = ItemStack.EMPTY;
							} else {
								// we have more
								int countRemain = is.getCount() - (getStackInSlot(j).getMaxStackSize() - getStackInSlot(j).getCount());
								setInventorySlotContents(j, new ItemStack(is.getItem(), getStackInSlot(j).getMaxStackSize(), is.getDamage()));
								is.setCount(countRemain);
							}

						}
					}
				} else {
					// nothign in slot so set contents
					setInventorySlotContents(j, new ItemStack(is.getItem(), is.getCount(), is.getDamage()));
					is = ItemStack.EMPTY;
				}

			}

		}

		return is;

	}

	@Override
	public void read(CompoundNBT compound) {

		super.read(compound);

		// inventory
		fuelStorage = compound.getInt(Reference.MACHINE_MOD_NBT_PREFIX + "FUEL_STORAGE");
		cooldown = compound.getInt(Reference.MACHINE_MOD_NBT_PREFIX + "COOL_DOWN");

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

		// inventory

		compound.putInt(Reference.MACHINE_MOD_NBT_PREFIX + "FUEL_STORAGE", fuelStorage);
		compound.putInt(Reference.MACHINE_MOD_NBT_PREFIX + "COOL_DOWN", cooldown);

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
	public boolean isUsableByPlayer(PlayerEntity playerIn) {
		return playerIn.getDistanceSq(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ()) < 64;
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
	public int getField(int id) {
		switch (id) {
		case 0:
			return this.fuelStorage;

		default:
			break;
		}
		return 0;

	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
		case 0:
			this.fuelStorage = value;
			break;

		default:
			break;
		}

	}

	@Override
	public int getFieldCount() {
		return 1;
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
