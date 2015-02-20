package com.projectreddog.machinemod.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;

import com.projectreddog.machinemod.block.BlockMachineModPrimaryCrusher;
import com.projectreddog.machinemod.iface.IFuelContainer;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.reference.Reference;

public class TileEntityDistiller extends TileEntity implements IUpdatePlayerListBox, IFuelContainer, ISidedInventory {
	protected ItemStack[] inventory;

	public final int maxFuelStorage = 1000; // store up to 1k
	public final int inventorySize = 1;
	private static int[] sideSlots = new int[] { 0 };

	public int fuelStorage = 0;
	// public final int coolDownReset = 1200;
	// public int cooldown = coolDownReset;
	public int remainBurnTime = 0;

	public TileEntityDistiller() {
		inventory = new ItemStack[inventorySize];

	}

	@Override
	public void update() {
		if (!worldObj.isRemote) {
			// LogHelper.info("TE update entity called");

			if (remainBurnTime > 0) {
				remainBurnTime--;

				transferFuel();
			} else {

				// consume more fuel
				// only if it has mash to process
				if (fuelStorage > 0) {
					// use the furnace's default burn times
					remainBurnTime = TileEntityFurnace.getItemBurnTime(this.getStackInSlot(0));
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
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		fuelStorage = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "FUEL_STORAGE");
		remainBurnTime = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "BURN_TIME");
		// inventory
		NBTTagList tagList = compound.getTagList(Reference.MACHINE_MOD_NBT_PREFIX + "Inventory", compound.getId());
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inventory.length) {
				inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "FUEL_STORAGE", fuelStorage);
		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "BURN_TIME", remainBurnTime);
		// inventory
		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < inventory.length; i++) {
			ItemStack stack = inventory[i];
			if (stack != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		compound.setTag(Reference.MACHINE_MOD_NBT_PREFIX + "Inventory", itemList);

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

			if (worldObj.getBlockState(this.pos.offset(this.outputDirection())).getBlock() == ModBlocks.machinefuelpump) {
				// its a distiller so we can transfer fuel!
				TileEntityFuelPump tEC = (TileEntityFuelPump) worldObj.getTileEntity(this.pos.offset(this.outputDirection()));
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
	public EnumFacing outputDirection() {
		EnumFacing ef = (EnumFacing) worldObj.getBlockState(this.getPos()).getValue(BlockMachineModPrimaryCrusher.FACING);
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
	public boolean isUseableByPlayer(EntityPlayer playerIn) {
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
		if (stack != null) {
			if (stack.stackSize <= amt) {
				setInventorySlotContents(slot, null);
			} else {
				stack = stack.splitStack(amt);
				if (stack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}

			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			setInventorySlotContents(slot, null);
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
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
			inventory[i] = null;
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IChatComponent getDisplayName() {
		// TODO Auto-generated method stub
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

}
