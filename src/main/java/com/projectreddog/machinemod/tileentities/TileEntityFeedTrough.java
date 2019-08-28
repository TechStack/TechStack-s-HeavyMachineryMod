package com.projectreddog.machinemod.tileentities;

import java.util.List;

import com.projectreddog.machinemod.block.BlockMachineModFeedTrough;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

public class TileEntityFeedTrough extends TileEntity implements ITickable, ISidedInventory {
	protected ItemStack[] inventory;
	private static int[] topSlots = new int[] { 0 };
	private static int[] bottomSlots = new int[] {};
	int inventorySize = 1;

	public AxisAlignedBB boundingBox;
	public int timeTillCoolDown = 0;
	public int coolDownAmount = 100;

	public int MaxAnimalLimit = 200;
	private int feedSizeHalf = 8;

	public TileEntityFeedTrough() {
		inventory = new ItemStack[inventorySize];
		for (int i = 0; i < inventorySize; i++) {
			inventory[i] = ItemStack.EMPTY;
		}
	}

	public boolean getPowered() {
		return world.isBlockPowered(this.pos);
	}

	@Override
	public void update() {

		if (!world.isBlockPowered(this.pos)) {
			if (timeTillCoolDown > 0) {
				timeTillCoolDown--;
				return;
			}
			timeTillCoolDown = coolDownAmount;

			if (world.getBlockState(pos).getBlock() == ModBlocks.machinefeedtrough) {
				EnumFacing checkDirection = (EnumFacing) world.getBlockState(this.pos).getValue(BlockMachineModFeedTrough.FACING);
				BlockPos bp = this.pos;// this.pos.offset(checkDirection);

				boundingBox = new AxisAlignedBB(this.pos.add(-feedSizeHalf, 0, -feedSizeHalf), this.pos.add(feedSizeHalf, 1, feedSizeHalf));

				List list = world.getEntitiesWithinAABB(EntityAnimal.class, boundingBox);
				processEntitiesInList(list);

			}
		}

	}

	private void processEntitiesInList(List par1List) {
		int breedCount = 0;
		if (par1List.size() <= MaxAnimalLimit) {
			for (int i = 0; i < par1List.size(); ++i) {
				Entity entity = (Entity) par1List.get(i);
				if (entity != null) {
					if (entity instanceof EntityAnimal) {
						EntityAnimal ea = (EntityAnimal) entity;
						if (!ea.isInLove() && ea.getGrowingAge() == 0) {
							if (ea.isBreedingItem(getStackInSlot(0))) {
								ea.setInLove(null);
								decrStackSize(0, 1);
								breedCount++;
								if (breedCount > 1) {
									break;
								}
							}
						}
					}
				}
			}
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
	public boolean isUsableByPlayer(EntityPlayer playerIn) {
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
	public boolean isEmpty() {
		for (int i = 0; i < inventory.length; i++) {
			if (!inventory[i].isEmpty()) {
				return false;
			}
		}

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
			inventory[i] = ItemStack.EMPTY;
		}
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		if (side == EnumFacing.DOWN) {
			return bottomSlots;
		} else if (side == EnumFacing.UP) {
			return topSlots;
		}
		int[] topSlots2 = new int[] { 0 };
		return topSlots2;

	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemStackIn, EnumFacing direction) {
		if (slot < 54 && direction == EnumFacing.UP) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, EnumFacing direction) {
		if (slot < 54 && direction == EnumFacing.DOWN) {
			return true;
		}
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {

		super.readFromNBT(compound);

		timeTillCoolDown = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "COOLDOWN");

		// inventory
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

		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "COOLDOWN", timeTillCoolDown);

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
		compound.setTag(Reference.MACHINE_MOD_NBT_PREFIX + "Inventory", itemList);
		return compound;

	}
}
