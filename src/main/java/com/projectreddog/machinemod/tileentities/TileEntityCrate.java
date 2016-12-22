package com.projectreddog.machinemod.tileentities;

import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.network.MachineModMessageRequestTEAllInventoryToServer;
import com.projectreddog.machinemod.network.MachineModMessageTEInventoryChangedToClient;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import scala.Int;

public class TileEntityCrate extends TileEntity implements ITickable, ISidedInventory {
	protected ItemStack[] inventory;
	boolean shouldRequestInvetoryUpdates = true;

	boolean shouldSendInvetoryUpdates = true;

	int inventorySize = 1;
	public int rotAmt = 0;

	public TileEntityCrate() {
		inventory = new ItemStack[inventorySize];

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
	public ItemStack removeStackFromSlot(int slot) {
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
		shouldSendInvetoryUpdates = true;

	}

	@Override
	public int getInventoryStackLimit() {
		return Int.MaxValue();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return player.getDistanceSq(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ()) < 64;

	}

	@Override
	public void openInventory(EntityPlayer player) {

	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;

	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {

	}

	@Override
	public int getFieldCount() {
		return 0;
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
	public int[] getSlotsForFace(EnumFacing side) {
		int[] Slots = new int[] { 0 };
		return Slots;

	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemStackIn, EnumFacing direction) {
		if (slot < inventorySize && (direction == EnumFacing.NORTH || direction == EnumFacing.SOUTH || direction == EnumFacing.EAST || direction == EnumFacing.WEST || direction == EnumFacing.UP)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, EnumFacing direction) {
		if (slot < inventorySize && (direction == EnumFacing.DOWN)) {
			return true;
		}
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {

		super.readFromNBT(compound);

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
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

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

		return compound;

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

		if (worldObj.isRemote) {
			rotAmt = rotAmt + 1;
			if (rotAmt > 360) {
				rotAmt = 0;
			}
			// client side so request inventory
			if (shouldRequestInvetoryUpdates) {
				ModNetwork.simpleNetworkWrapper.sendToServer((new MachineModMessageRequestTEAllInventoryToServer(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ())));
				shouldRequestInvetoryUpdates = false;
			}

		} else {
			// server side send update if it has changed
			if (shouldSendInvetoryUpdates) {
				for (int i = 0; i < inventory.length; i++) {
					ModNetwork.simpleNetworkWrapper.sendToAll(new MachineModMessageTEInventoryChangedToClient(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), i, inventory[i]));
				}
				shouldSendInvetoryUpdates = false;
			}
		}

	}

	public void sendAllInventoryToPlayer(EntityPlayerMP player) {
		for (int i = 0; i < inventory.length; i++) {

			ModNetwork.simpleNetworkWrapper.sendTo(new MachineModMessageTEInventoryChangedToClient(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), i, inventory[i]), player);

		}

	}
}
