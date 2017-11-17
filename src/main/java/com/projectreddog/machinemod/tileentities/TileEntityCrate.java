package com.projectreddog.machinemod.tileentities;

import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.network.MachineModMessageRequestTEAllInventoryToServer;
import com.projectreddog.machinemod.network.MachineModMessageTEInventoryChangedToClient;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import scala.Int;

public class TileEntityCrate extends TileEntity implements ITickable, ISidedInventory {
	protected ItemStack[] inventory;
	protected ItemStack DeepStorageType;

	int inventorySize = 2;// slot 0 = Output (down) Slot 1 = input (up & sides)
	boolean shouldRequestInvetoryUpdates = true;

	boolean shouldSendInvetoryUpdates = true;

	public int rotAmt = 0;

	public int AmtInReserve = 0;

	public ItemStack HeldItem;

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
			if (stack.getCount() <= amt) {
				setInventorySlotContents(slot, null);
			} else {
				stack = stack.splitStack(amt);
				if (stack.getCount() == 0) {
					setInventorySlotContents(slot, null);
				}

			}
		}
		shouldSendInvetoryUpdates = true;
		return stack;
	}

	@Override
	public ItemStack removeStackFromSlot(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			setInventorySlotContents(slot, null);
		}
		shouldSendInvetoryUpdates = true;

		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {

		inventory[slot] = stack;
		if (stack != null && stack.getCount() > getInventoryStackLimit()) {
			stack.setCount(getInventoryStackLimit());
		}
		shouldSendInvetoryUpdates = true;

	}

	@Override
	public int getInventoryStackLimit() {
		return Int.MaxValue();
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
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
		// slot 0 = Output (down) Slot 1 = input (up & sides)
		int[] Slots;
		if (side == EnumFacing.DOWN) {
			Slots = new int[] { 0 };
		} else {
			Slots = new int[] { 1 };
		}

		return Slots;

	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemStackIn, EnumFacing direction) {

		// only slot 1 is input !
		// need to check item too
		if (itemStackIn.isItemEqual(HeldItem) || HeldItem == null) {
			// same input item or held item is null so accept the item !
			if (slot == 1 && (direction == EnumFacing.NORTH || direction == EnumFacing.SOUTH || direction == EnumFacing.EAST || direction == EnumFacing.WEST || direction == EnumFacing.UP)) {
				return true;
			}
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
		AmtInReserve = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "AMTINRESERVE");

		NBTTagList tagList = compound.getTagList(Reference.MACHINE_MOD_NBT_PREFIX + "Inventory", compound.getId());
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inventory.length) {
				inventory[slot] = new ItemStack(tag);
			}
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		// inventory
		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "AMTINRESERVE", AmtInReserve);

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

	/*
	 * 
	 */
	public boolean AddStack(ItemStack stackToAdd) {

		// TODO Null crash needs fixed
		if (stackToAdd.isItemEqual(HeldItem) || HeldItem == null) {
			processInputOutputSlots();
			setInventorySlotContents(1, stackToAdd);
			shouldSendInvetoryUpdates = true;

			return true;
		} else {
			return false;
		}
	}

	/*
	 * amount is ignored for now will only return full stack !
	 */
	public boolean removeStack(int amount) {
		processInputOutputSlots();
		if (getStackInSlot(0) == null) {
			processInputOutputSlots();
			if (getStackInSlot(0) == null) {

				return false;
			}
		}
		InventoryHelper.spawnItemStack(this.world, this.pos.getX(), this.pos.getY(), this.pos.getZ(), getStackInSlot(0));
		setInventorySlotContents(0, null);
		processInputOutputSlots();
		shouldSendInvetoryUpdates = true;
		return true;
	}

	public void DropItemsOnBreak() {
		while (AmtInReserve > 0 || getStackInSlot(0) != null) {
			// this will be ugly for full crates !
			if (!removeStack(-1)) {
				// we couldnt spawn everything break the loop
				break;
			}
		}

	}

	public void processInputOutputSlots() {
		if (!world.isRemote) {
			// server side
			// AmtInReserve
			// AmtInReserve = 999999900;
			if (getStackInSlot(0) != null) {
				if (getStackInSlot(0).getCount() == getStackInSlot(0).getMaxStackSize()) {
					// output full fill if input is not full
					if (getStackInSlot(1) != null) {// input
						// have stack in input and output full move to reserve if same item
						if (getStackInSlot(1).isItemEqual(getStackInSlot(0))) {
							// same item move to reserve
							AmtInReserve = AmtInReserve + getStackInSlot(1).getCount();
							// set empty slot contents of input slot !
							setInventorySlotContents(1, null);
						}
					}
				} else {
					// output not full
					if (getStackInSlot(1) != null) {
						// something in output so take it please !
						if (getStackInSlot(0).getMaxStackSize() - getStackInSlot(0).getCount() >= getStackInSlot(1).getCount()) {// we can take it all so do so
							getStackInSlot(0).setCount(getStackInSlot(0).getCount() + getStackInSlot(1).getCount());
							setInventorySlotContents(1, null);
						} else {
							// to much input so take what we can and reserve the rest
							// find leftover by taking input Stack size - amt needed ( max size - curr size)
							int LeftOverAmt = getStackInSlot(1).getCount() - (getStackInSlot(0).getMaxStackSize() - getStackInSlot(0).getCount());
							AmtInReserve = AmtInReserve + LeftOverAmt;
							getStackInSlot(0).setCount(getStackInSlot(0).getMaxStackSize());

							setInventorySlotContents(1, null);
						}
					}
				}
			} else {
				// nothing was in output stack check input
				if (getStackInSlot(1) != null) {
					// we have an item !! so lets store it in the held item for comparison purposes
					HeldItem = getStackInSlot(1).copy();
					// move to output stack
					setInventorySlotContents(0, getStackInSlot(1).copy());
					// clear input for more items to arrive
					setInventorySlotContents(1, null);

				}

			}
			// Need to refill output from reserve

			if (getStackInSlot(0) != null) {
				// has an item top it off if possible.
				if (getStackInSlot(0).getCount() < getStackInSlot(0).getMaxStackSize()) {
					// has room for more
					int amtNeeded = getStackInSlot(0).getMaxStackSize() - getStackInSlot(0).getCount();
					if (amtNeeded <= AmtInReserve) {
						// we have enough top it off all the way !
						AmtInReserve = AmtInReserve - amtNeeded;
						ItemStack TmpStack = getStackInSlot(0).copy();
						TmpStack.setCount(TmpStack.getMaxStackSize());

						setInventorySlotContents(0, TmpStack);
					} else {
						// Do not have enough !!! Help !

						ItemStack TmpStack = getStackInSlot(0).copy();
						TmpStack.setCount(TmpStack.getCount() + AmtInReserve);
						AmtInReserve = 0;
						setInventorySlotContents(0, TmpStack);
					}
				} // else already full no action needed
			} else {
				// no output stack !
				// check reserve first !
				if (AmtInReserve > 0) {
					// has reserve need to check held item !
					if (HeldItem != null) {

						// has held item replenish the output stack then do normal checks
						if (AmtInReserve >= HeldItem.getMaxStackSize()) {

							// can create full stack
							ItemStack TmpStack = HeldItem.copy();
							TmpStack.setCount(TmpStack.getMaxStackSize());
							AmtInReserve = AmtInReserve - TmpStack.getMaxStackSize();
							setInventorySlotContents(0, TmpStack);
						} else {
							// need partial stack

							ItemStack TmpStack = HeldItem.copy();
							;
							TmpStack.setCount(AmtInReserve);
							AmtInReserve = 0;
							setInventorySlotContents(0, TmpStack);
						}
					}
				}
			}
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

		if (world.isRemote) {
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
			processInputOutputSlots();
			// server side send update if it has changed
			if (shouldSendInvetoryUpdates) {
				for (int i = 0; i < inventory.length; i++) {
					ModNetwork.simpleNetworkWrapper.sendToAll(new MachineModMessageTEInventoryChangedToClient(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), i, inventory[i], this.AmtInReserve));
				}
				shouldSendInvetoryUpdates = false;
			}
		}

	}

	public void sendAllInventoryToPlayer(EntityPlayerMP player) {
		for (int i = 0; i < inventory.length; i++) {

			ModNetwork.simpleNetworkWrapper.sendTo(new MachineModMessageTEInventoryChangedToClient(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), i, inventory[i], this.AmtInReserve), player);

		}

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
