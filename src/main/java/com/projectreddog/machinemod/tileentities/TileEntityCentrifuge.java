package com.projectreddog.machinemod.tileentities;

import java.util.List;

import com.projectreddog.machinemod.iface.IFuelContainer;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityCentrifuge extends TileEntity implements ITickableTileEntity, ISidedInventory, IFuelContainer {
	protected ItemStack[] inventory;
	private static int[] bottomSlots = new int[] { 1 };
	private static int[] topSlots = new int[] { 0 };
	public final int maxFuelStorage = 5000; // store up to 10k (can fill all 9 cans & have room for one more
	public int fuelStorage = 0;
	public AxisAlignedBB boundingBox;
	public int coolDownAmount = 60;
	public int timeTillCoolDown = 0;

	public final int BlastedStoneOreMultiplier = 3;
	public final int VanillaOreMultiplier = 2;
	public final int BlastedStoneCoalMultiplier = 3;
	public final int BlastedStoneGemMultiplier = 2;
	public final int BlastedStoneLapisMultiplier = 12;
	public final int BlastedStoneRedstoneMultiplier = 8;
	int inventorySize = 2;

	public TileEntityCentrifuge(TileEntityType<?> tileEntityTypeIn) {
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
				ItemStack item = this.getStackInSlot(0);
				if (!item.isEmpty()) {
					if (item.getItem() != null) {
						if (item.getItem() == Items.CLAY_BALL) {
							if (fuelStorage > 0) {
								fuelStorage = fuelStorage - 1;
							}
						}
					}
				}
				return;
			}
			timeTillCoolDown = coolDownAmount;

			ItemStack item = this.getStackInSlot(0);
			if (!item.isEmpty()) {
				if (item.getItem() != null) {
					if (item.getItem() == Items.CLAY_BALL) {
						if (fuelStorage > 0) {
							// fuelStorage = fuelStorage - 1;

							if (!this.getStackInSlot(1).isEmpty()) {
								if (this.getStackInSlot(1).getCount() < this.getStackInSlot(1).getMaxStackSize()) {
									// TODO Create asphalt item
									this.decrStackSize(0, 1);
									this.getStackInSlot(1).setCount(this.getStackInSlot(1).getCount() + 1);

									return;
								}
							} else {
								this.setInventorySlotContents(1, new ItemStack(ModItems.rawasphalt, 1));
								this.decrStackSize(0, 1);

								// fuelStorage = fuelStorage - 1;

								return;
							}
						}
					}
				}
			}

		}
	}

	private void processEntitiesInList(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity != null) {
				if (entity instanceof ItemEntity) {
					ItemStack is = ((ItemEntity) entity).getItem();
					is.setDamage(((ItemEntity) entity).getItem().getDamage());
					if (!entity.isDead) {
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
				// entity.setPosition(entity.getPosition().getX() + 0.1d, entity.getPosition().getY(), entity.getPosition().getZ());
			}
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

		timeTillCoolDown = compound.getInt(Reference.MACHINE_MOD_NBT_PREFIX + "COOLDOWN");
		fuelStorage = compound.getInt(Reference.MACHINE_MOD_NBT_PREFIX + "FUEL");

		// inventory
		ListNBT tagList = compound.getTagList(Reference.MACHINE_MOD_NBT_PREFIX + "Inventory", compound.getId());
		for (int i = 0; i < tagList.tagCount(); i++) {
			CompoundNBT tag = (CompoundNBT) tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inventory.length) {
				inventory[slot] = new ItemStack(tag);
			}
		}
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);

		compound.putInt(Reference.MACHINE_MOD_NBT_PREFIX + "COOLDOWN", timeTillCoolDown);
		compound.putInt(Reference.MACHINE_MOD_NBT_PREFIX + "FUEL", fuelStorage);

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
		if (side == Direction.DOWN) {
			return bottomSlots;
		} else if (side == Direction.UP) {
			return topSlots;
		}
		// int[] topSlots2 = new int[] { 0 };
		return null;

	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemStackIn, Direction direction) {
		if (slot < 54 && direction == Direction.UP) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, Direction direction) {
		if (slot < 54 && direction == Direction.DOWN) {
			return true;
		}
		return false;
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
	public Direction outputDirection() {
		return null;
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
