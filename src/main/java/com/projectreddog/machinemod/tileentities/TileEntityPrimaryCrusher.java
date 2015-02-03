package com.projectreddog.machinemod.tileentities;

import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;

public class TileEntityPrimaryCrusher extends TileEntity implements IUpdatePlayerListBox  , ISidedInventory{
	protected ItemStack[] inventory;
	private static int[] bottomSlots =  new int[] {55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107};
	private static int[] topSlots =  new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53};
	
	public TileEntityPrimaryCrusher() {
		inventory = new ItemStack[108];
		
	}
	@Override
	public void update() {

		// LogHelper.info("TE update entity called");
		for (int i = 0; i < this.getSizeInventory()-54; i++) {
			ItemStack item = this.getStackInSlot(i);

			if (item != null && item.stackSize > 0) {
				
				this.setInventorySlotContents(i+54, item);

				this.setInventorySlotContents(i, null);
			}
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
	public boolean isUseableByPlayer(EntityPlayer playerIn) {
		return playerIn.getDistanceSq(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ()) < 64;
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
	public int[] getSlotsForFace(EnumFacing side) {
		if (side == EnumFacing.DOWN){
			return bottomSlots;
		}else if (side == EnumFacing.UP){
			return topSlots;
		}
		int[] topSlots2 =  new int[] {0};	
		return  topSlots2;
		
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemStackIn, EnumFacing direction) {
		if (slot < 54 && direction == EnumFacing.UP){
		return true;
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, EnumFacing direction) {
		if (slot >= 54 && direction == EnumFacing.DOWN){
			return true;
			}
			return false;
	}

	

}
