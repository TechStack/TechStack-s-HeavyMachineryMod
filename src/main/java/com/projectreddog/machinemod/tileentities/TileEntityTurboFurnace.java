package com.projectreddog.machinemod.tileentities;

import javax.annotation.Nullable;

import com.projectreddog.machinemod.item.crafting.TruboFurnaceRecipes;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import scala.Int;

public class TileEntityTurboFurnace extends TileEntity implements ITickable, ISidedInventory {
	int inventorySize = 3;

	boolean shouldSendInvetoryUpdates = false;
	protected ItemStack[] inventory;

	int fuleBurnTimeRemaining = 0;
	int processingTimeRemaining = 200;

	public static int resetProcessingTime = 200;
	public static int BLACE_POWDER_FUEL_AMT = 40;

	// slot 0 = Input
	// SLOT 1 = Output
	// Slot 2 = FUEL
	public TileEntityTurboFurnace() {
		inventory = new ItemStack[inventorySize];
		for (int i = 0; i < inventorySize; i++) {
			inventory[i] = ItemStack.EMPTY;
		}
	}

	/**
	 * Furnace isBurning
	 */
	public boolean isBurning() {
		return fuleBurnTimeRemaining > 0;
	}

	@Override
	public void update() {
		if (!this.world.isRemote) {
			// server
			if (isBurning()) {
				fuleBurnTimeRemaining--;

				if (canSmelt()) {
					processingTimeRemaining--;
					if (processingTimeRemaining <= 0) {
						smeltStack();
						this.processingTimeRemaining = resetProcessingTime;

					}
				}
			} else {
				// not burning
				if (canSmelt()) {
					this.fuleBurnTimeRemaining = getItemBurnTime(getStackInSlot(2));
					if (this.isBurning()) {
						// consume the fuel
						decrStackSize(2, 1);
					}
				}
			}

		}
	}

	/**
	 * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
	 */
	public void smeltStack() {
		if (this.canSmelt()) {

			// slot 0 = Input
			// SLOT 1 = Output
			// Slot 2 = FUEL

			ItemStack inputItemStack = getStackInSlot(0);
			ItemStack resultItemStack = FurnaceRecipes.instance().getSmeltingResult(inputItemStack);

			if (resultItemStack.isEmpty()) {
				resultItemStack = checkTruboFurnaceRecipes(inputItemStack);
			}

			ItemStack outputSlotItemStack = getStackInSlot(1);

			if (outputSlotItemStack.isEmpty()) {
				setInventorySlotContents(1, resultItemStack.copy());
			} else if (outputSlotItemStack.getItem() == resultItemStack.getItem()) {
				outputSlotItemStack.grow(resultItemStack.getCount());
			}

			inputItemStack = ItemStack.EMPTY;
			setInventorySlotContents(0, ItemStack.EMPTY);
		}
	}

	/**
	 * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
	 */
	private boolean canSmelt() {

		// slot 0 = Input
		// SLOT 1 = Output
		// Slot 2 = FUEL
		if (((ItemStack) getStackInSlot(0)).isEmpty()) {
			// no input can't smelt it!
			return false;
		} else {
			ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(getStackInSlot(0));
			if (itemstack.isEmpty()) {
				itemstack = checkTruboFurnaceRecipes(getStackInSlot(0));
			}

			if (itemstack.isEmpty()) {
				return false;
			} else {
				itemstack.setCount(((ItemStack) getStackInSlot(0)).getCount());
				ItemStack itemstack1 = getStackInSlot(1);

				if (itemstack1.isEmpty()) {
					return true;
				} else if (!itemstack1.isItemEqual(itemstack)) {
					return false;
				} else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) // Forge fix: make furnace respect stack sizes in furnace recipes
				{
					return true;
				} else {
					return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
				}
			}
		}
	}

	private ItemStack checkTruboFurnaceRecipes(ItemStack input) {
		ItemStack is = TruboFurnaceRecipes.instance().getSmeltingResult(input);

		return is;
	}

	/**
	 * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't fuel
	 */
	public static int getItemBurnTime(ItemStack stack) {
		if (stack.isEmpty()) {
			return 0;
		} else {

			Item item = stack.getItem();

			if (item == Items.BLAZE_POWDER) {
				return BLACE_POWDER_FUEL_AMT;
			}
		}
		return 0;

	}

	public static boolean isItemFuel(ItemStack stack) {
		return getItemBurnTime(stack) > 0;
	}

	// NBT data
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		// TODO WRITE OUT THE NBT CORRECLTY FOR THE VARIOUS BITs

		super.writeToNBT(nbt);

		nbt.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "fuleBurnTimeRemaining", fuleBurnTimeRemaining);
		nbt.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "processingTimeRemaining", processingTimeRemaining);

		// inventory
		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < inventory.length; i++) {
			ItemStack stack = inventory[i];
			if (!stack.isEmpty()) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		nbt.setTag(Reference.MACHINE_MOD_NBT_PREFIX + "Inventory", itemList);
		return nbt;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		fuleBurnTimeRemaining = nbt.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "fuleBurnTimeRemaining");
		processingTimeRemaining = nbt.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "processingTimeRemaining");

		// inventory
		NBTTagList tagList = nbt.getTagList(Reference.MACHINE_MOD_NBT_PREFIX + "Inventory", nbt.getId());
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inventory.length) {
				inventory[slot] = new ItemStack(tag);
			}
		}
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
		shouldSendInvetoryUpdates = true;
		return stack;
	}

	@Override
	public ItemStack removeStackFromSlot(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (!stack.isEmpty()) {
			setInventorySlotContents(slot, ItemStack.EMPTY);
		}
		shouldSendInvetoryUpdates = true;

		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {

		inventory[slot] = stack;
		if (!stack.isEmpty() && stack.getCount() > getInventoryStackLimit()) {
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

	@Nullable
	public ITextComponent getDisplayName() {
		return new TextComponentString("Turbo Furnace");
	}

	@Override
	public void openInventory(EntityPlayer player) {

	}

	@Override
	public void closeInventory(EntityPlayer player) {

	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;

	}

	@Override
	public int getField(int id) {
		switch (id) {
		case 0:
			return fuleBurnTimeRemaining;

		case 1:
			return processingTimeRemaining;
		}
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		switch (id) {
		case 0:
			fuleBurnTimeRemaining = value;
			break;
		case 1:
			processingTimeRemaining = value;
			break;
		default:
			break;
		}

	}

	// int fuleBurnTimeRemaining = 0;
	// int processingTimeRemaining = 0;
	@Override
	public int getFieldCount() {
		return 2;
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
	public int[] getSlotsForFace(EnumFacing side) {
		// slot 0 = Output (down) Slot 1 = input (up & sides)
		int[] Slots = new int[] {};
		if (side == EnumFacing.UP) {
			Slots = new int[] { 0 };
		} else if (side == EnumFacing.EAST || side == EnumFacing.WEST || side == EnumFacing.NORTH || side == EnumFacing.SOUTH) {
			Slots = new int[] { 2 };
		} else if (side == EnumFacing.DOWN) {
			Slots = new int[] { 1 };
		}

		// slot 0 = Input
		// SLOT 1 = Output
		// Slot 2 = FUEL

		return Slots;

	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemStackIn, EnumFacing direction) {

		// TODO MODIFY ME LATER PLEASE
		if (direction == EnumFacing.UP) {
			return true;
		}
		if (direction == EnumFacing.WEST || direction == EnumFacing.EAST || direction == EnumFacing.NORTH || direction == EnumFacing.SOUTH) {
			if (isItemFuel(itemStackIn)) {
				return true;
			}
			return false;
		}

		return true;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, EnumFacing direction) {
		if (slot < inventorySize && (direction == EnumFacing.DOWN)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
