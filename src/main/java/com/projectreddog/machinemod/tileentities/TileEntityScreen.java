package com.projectreddog.machinemod.tileentities;

import java.util.List;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
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
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TileEntityScreen extends TileEntity implements ITickableTileEntity, ISidedInventory {
	protected ItemStack[] inventory;
	private static int[] topSlots = new int[] { 4 };

	public final int inventorySize = 5;
	public AxisAlignedBB boundingBox;
	public int coolDownAmount = 1;
	public int timeTillCoolDown = 0;

	// slot 0 = north
	// slot 1 = east
	// slot 2 = south
	// slot 3 = west

	public TileEntityScreen(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
		inventory = new ItemStack[inventorySize];
		for (int i = 0; i < inventorySize; i++) {
			inventory[i] = ItemStack.EMPTY;
		}
	}

	@Override
	public void tick() {

		if (!world.isRemote) {
			if (timeTillCoolDown > 0) {
				timeTillCoolDown--;
				return;
			}
			timeTillCoolDown = coolDownAmount;

			if (world.getBlockState(pos).getBlock() == ModBlocks.machinescreen) {

				boundingBox = new AxisAlignedBB(this.pos.up(), this.pos.up().add(1, 1, 1));
			}
			List list = world.getEntitiesWithinAABB(ItemEntity.class, boundingBox);
			processEntitiesInList(list);
			MoveItemsInnventory();
		}
	}

	private void MoveItemsInnventory() {
		for (int i = 0; i < 4; i++) {
			if (!getStackInSlot(4).isEmpty() && !getStackInSlot(i).isEmpty() && getStackInSlot(4).getItem() != null && getStackInSlot(i).getItem() != null) {
				if (getStackInSlot(i).getItem() == getStackInSlot(4).getItem() && getStackInSlot(i).getItem().getMetadata(getStackInSlot(i)) == getStackInSlot(4).getItem().getMetadata(getStackInSlot(4))) {
					double x = 0, y = 0, z = 0;
					y = this.pos.getY() + .5d;
					Direction ef = null;
					if (i == 0) {
						// north ?
						ef = Direction.NORTH;
						x = this.pos.getX() + .5d;
						z = this.pos.getZ() - .5d;
					} else if (i == 1) {
						// east
						ef = Direction.EAST;
						x = this.pos.getX() + 1.5d;
						z = this.pos.getZ() + .5d;
					} else if (i == 2) {
						// south
						ef = Direction.SOUTH;
						x = this.pos.getX() + .5d;
						z = this.pos.getZ() + 1.5d;
					} else if (i == 3) {
						// west
						ef = Direction.WEST;
						x = this.pos.getX() - .5d;
						z = this.pos.getZ() + .5d;
					}

					ItemStack tmpstack = getStackInSlot(4).copy();
					if (tmpstack.getCount() > 1) {
						tmpstack.setCount(tmpstack.getCount());
					}

					// check for invetory and use it if possible
					BlockPos bp = new BlockPos(x, y, z);
					TileEntity te = world.getTileEntity(bp);
					if (te != null) {
						if (te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, ef)) {
							IItemHandler iih = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, ef);
							for (int slotIndex = 0; slotIndex < iih.getSlots(); slotIndex++) {
								ItemStack is = iih.insertItem(slotIndex, tmpstack, false);
								tmpstack = is.copy();
								setInventorySlotContents(4, tmpstack);
								if (is.isEmpty()) {
									// insert worked nothing todo anymore return ;
									setInventorySlotContents(4, ItemStack.EMPTY);
									return;
								} else {

								}
							}

						}

						if (te instanceof TileEntityCrate) {
							TileEntityCrate tec = (TileEntityCrate) te;
							if (tec.canInsertItem(1, tmpstack, Direction.UP)) {
								// checked if it can insert so do insert.
								tec.setInventorySlotContents(1, tmpstack);
								setInventorySlotContents(4, ItemStack.EMPTY);

								return;

							}
						}
					}

					ItemEntity ei = new ItemEntity(world, x, y, z, tmpstack);
					ei.motionX = 0;
					ei.motionY = 0;
					ei.motionZ = 0;
					if (world.spawnEntity(ei)) {
						decrStackSize(4, tmpstack.getCount());
						return;
					}

				}
			}
		}

		for (int i = 0; i < 4; i++) {
			if (!getStackInSlot(4).isEmpty() && getStackInSlot(i).isEmpty() && getStackInSlot(4).getItem() != null) {
				Direction ef = null;

				double x = 0, y = 0, z = 0;
				y = this.pos.getY() + .5d;
				if (i == 0) {
					// north ?
					ef = Direction.NORTH;
					x = this.pos.getX() + .5d;
					z = this.pos.getZ() - .5d;
				} else if (i == 1) {

					// east
					ef = Direction.EAST;
					x = this.pos.getX() + 1.5d;
					z = this.pos.getZ() + .5d;
				} else if (i == 2) {
					// south
					ef = Direction.SOUTH;
					x = this.pos.getX() + .5d;
					z = this.pos.getZ() + 1.5d;
				} else if (i == 3) {
					// west
					ef = Direction.WEST;
					x = this.pos.getX() - .5d;
					z = this.pos.getZ() + .5d;
				}
				ItemStack tmpstack = getStackInSlot(4).copy();
				if (tmpstack.getCount() > 1) {
					tmpstack.setCount(tmpstack.getCount());
				}

				// check for invetory and use it if possible
				BlockPos bp = new BlockPos(x, y, z);
				TileEntity te = world.getTileEntity(bp);
				if (te != null) {
					if (te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, ef)) {
						IItemHandler iih = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, ef);
						for (int slotIndex = 0; slotIndex < iih.getSlots(); slotIndex++) {
							ItemStack is = iih.insertItem(slotIndex, tmpstack, false);
							tmpstack = is.copy();
							setInventorySlotContents(4, tmpstack);
							if (is.isEmpty()) {
								// insert worked nothing todo anymore return ;
								setInventorySlotContents(4, ItemStack.EMPTY);
								return;
							} else {

							}
						}

					}
					if (te instanceof TileEntityCrate) {
						TileEntityCrate tec = (TileEntityCrate) te;
						if (tec.canInsertItem(1, tmpstack, Direction.UP)) {
							// checked if it can insert so do insert.
							tec.setInventorySlotContents(1, tmpstack);
							setInventorySlotContents(4, ItemStack.EMPTY);

							return;

						}
					}
				}
				ItemEntity ei = new ItemEntity(world, x, y, z, tmpstack);
				ei.motionX = 0;
				ei.motionY = 0;
				ei.motionZ = 0;

				if (world.spawnEntity(ei)) {
					decrStackSize(4, tmpstack.getCount());
					return;
				}

			}
		}
	}

	private void processEntitiesInList(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity != null) {
				if (entity instanceof ItemEntity) {
					ItemStack is = ((ItemEntity) entity).getItem().copy();
					is.setItemDamage(((ItemEntity) entity).getItem().getItemDamage());
					if (entity.isAlive()) {
						if (is.getCount() > 0) {
							ItemStack is1 = addToinventory(is);

							if (!is1.isEmpty() && is1.getCount() != 0) {
								((ItemEntity) entity).setItem(is1);
							} else {
								entity.remove();
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
				// nothing in slot so set contents
				setInventorySlotContents(j, is.copy());
				is = ItemStack.EMPTY;
			}

		}
		// bug fix for picking up items that cannot be put in inventory
		return is;

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

	}

	@Override
	public void read(CompoundNBT compound) {

		super.read(compound);

		timeTillCoolDown = compound.getInt(Reference.MACHINE_MOD_NBT_PREFIX + "COOLDOWN");

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

		compound.putInt(Reference.MACHINE_MOD_NBT_PREFIX + "COOLDOWN", timeTillCoolDown);

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
		compound.setTag(Reference.MACHINE_MOD_NBT_PREFIX + "Inventory", itemList);
		return compound;

	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		if (side == Direction.UP) {
			return topSlots;
		}

		int[] i = new int[] {};
		return i;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, Direction direction) {
		if (index == 4 && direction == Direction.UP) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, Direction direction) {

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
