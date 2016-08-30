package com.projectreddog.machinemod.tileentities;

import java.util.List;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;

public class TileEntityScreen extends TileEntity implements ITickable, ISidedInventory {
	protected ItemStack[] inventory;
	private static int[] topSlots = new int[] { 4 };

	public final int inventorySize = 5;
	public AxisAlignedBB boundingBox;
	public int coolDownAmount = 5;
	public int timeTillCoolDown = 0;

	// slot 0 = north
	// slot 1 = east
	// slot 2 = south
	// slot 3 = west

	public TileEntityScreen() {
		inventory = new ItemStack[inventorySize];

	}

	@Override
	public void update() {

		if (!worldObj.isRemote) {
			if (timeTillCoolDown > 0) {
				timeTillCoolDown--;
				return;
			}
			timeTillCoolDown = coolDownAmount;

			if (worldObj.getBlockState(pos).getBlock() == ModBlocks.machinescreen) {

				boundingBox = new AxisAlignedBB(this.pos.up(), this.pos.up().add(1, 1, 1));
			}
			List list = worldObj.getEntitiesWithinAABB(EntityItem.class, boundingBox);
			processEntitiesInList(list);
			// TODO add method to sort item
			MoveItemsInnventory();
		}
	}

	private void MoveItemsInnventory() {
		for (int i = 0; i < 4; i++) {
			if (getStackInSlot(4) != null && getStackInSlot(i) != null && getStackInSlot(4).getItem() != null && getStackInSlot(i).getItem() != null) {
				if (getStackInSlot(i).getItem() == getStackInSlot(4).getItem() && getStackInSlot(i).getItem().getMetadata(getStackInSlot(i)) == getStackInSlot(4).getItem().getMetadata(getStackInSlot(4))) {
					double x = 0, y = 0, z = 0;
					y = this.pos.getY() + .5d;
					if (i == 0) {
						x = this.pos.getX() + .5d;
						z = this.pos.getZ() - .5d;
					} else if (i == 1) {
						x = this.pos.getX() + 1.5d;
						z = this.pos.getZ() + .5d;
					} else if (i == 2) {
						x = this.pos.getX() + .5d;
						z = this.pos.getZ() + 1.5d;
					} else if (i == 3) {
						x = this.pos.getX() - .5d;
						z = this.pos.getZ() + .5d;
					}

					ItemStack tmpstack = getStackInSlot(4).copy();
					if (tmpstack.stackSize > 1) {
						tmpstack.stackSize = 1;
					}
					EntityItem ei = new EntityItem(worldObj, x, y, z, tmpstack);
					ei.motionX = 0;
					ei.motionY = 0;
					ei.motionZ = 0;
					if (worldObj.spawnEntityInWorld(ei)) {
						decrStackSize(4, 1);
						return;
					}

				}
			}
		}

		for (int i = 0; i < 4; i++) {
			if (getStackInSlot(4) != null && getStackInSlot(i) == null && getStackInSlot(4).getItem() != null) {

				double x = 0, y = 0, z = 0;
				y = this.pos.getY() + .5d;
				if (i == 0) {
					x = this.pos.getX() + .5d;
					z = this.pos.getZ() - .5d;
				} else if (i == 1) {
					x = this.pos.getX() + 1.5d;
					z = this.pos.getZ() + .5d;
				} else if (i == 2) {
					x = this.pos.getX() + .5d;
					z = this.pos.getZ() + 1.5d;
				} else if (i == 3) {
					x = this.pos.getX() - .5d;
					z = this.pos.getZ() + .5d;
				}
				ItemStack tmpstack = getStackInSlot(4).copy();
				if (tmpstack.stackSize > 1) {
					tmpstack.stackSize = 1;
				}
				EntityItem ei = new EntityItem(worldObj, x, y, z, tmpstack);
				ei.motionX = 0;
				ei.motionY = 0;
				ei.motionZ = 0;

				if (worldObj.spawnEntityInWorld(ei)) {
					decrStackSize(4, 1);
					return;
				}

			}
		}
	}

	private void processEntitiesInList(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity != null) {
				if (entity instanceof EntityItem) {
					ItemStack is = ((EntityItem) entity).getEntityItem().copy();
					is.setItemDamage(((EntityItem) entity).getEntityItem().getItemDamage());
					if (!entity.isDead) {
						if (is.stackSize > 0) {
							ItemStack is1 = addToinventory(is);

							if (is1 != null && is1.stackSize != 0) {
								((EntityItem) entity).setEntityItemStack(is1);
							} else {
								entity.setDead();
							}
						}
					}
				}
			}
		}
	}

	protected ItemStack addToinventory(ItemStack is) {
		int i = getSizeInventory();

		int j = 4;
		if (is != null) {

			if (getStackInSlot(j) != null) {
				if (getStackInSlot(j).getItem() == is.getItem() && getStackInSlot(j).getItemDamage() == is.getItemDamage()) {
					// same item remove from is put into slot any amt not to
					// excede stack max
					if (getStackInSlot(j).stackSize < getStackInSlot(j).getMaxStackSize()) {
						// we have room to add to this stack
						if (is.stackSize <= getStackInSlot(j).getMaxStackSize() - getStackInSlot(j).stackSize) {
							// /all of the stack will fit in this slot do
							// so.

							setInventorySlotContents(j, new ItemStack(getStackInSlot(j).getItem(), getStackInSlot(j).stackSize + is.stackSize, is.getItemDamage()));
							is = null;
						} else {
							// we have more
							int countRemain = is.stackSize - (getStackInSlot(j).getMaxStackSize() - getStackInSlot(j).stackSize);
							setInventorySlotContents(j, new ItemStack(is.getItem(), getStackInSlot(j).getMaxStackSize(), is.getItemDamage()));
							is.stackSize = countRemain;
						}

					}
				}
			} else {
				// nothing in slot so set contents
				setInventorySlotContents(j, is.copy());
				is = null;
			}

		}
		// bug fix for picking up items that cannot be put in inventory
		return is;

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
		// TODO Auto-generated method stub

	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

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
				inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "COOLDOWN", timeTillCoolDown);

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

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		// TODO Auto-generated method stub
		if (side == EnumFacing.UP) {
			return topSlots;
		}

		int[] i = new int[] {};
		return i;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		if (index == 4 && direction == EnumFacing.UP) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {

		return false;
	}
}
