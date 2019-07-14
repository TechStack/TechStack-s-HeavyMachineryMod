package com.projectreddog.machinemod.tileentities;

import java.util.ArrayList;
import java.util.List;

import com.projectreddog.machinemod.iface.ITEGuiButtonHandler;
import com.projectreddog.machinemod.iface.IWorkConsumer;
import com.projectreddog.machinemod.item.blueprint.ItemBlueprint;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityAssemblyTable extends TileEntity implements ITickableTileEntity, ISidedInventory, IWorkConsumer, ITEGuiButtonHandler {
	protected ItemStack[] inventory;

	public final int inventorySize = 2;
	private static int[] sideSlots = new int[] { 0 };

	public int totalWorkNeededForThisTask = 0;// 1000
	public int workConsumedForThisTask = 0;// 100

	public boolean hasBuildProject = false;

	public TileEntityAssemblyTable() {
		inventory = new ItemStack[inventorySize];
		for (int i = 0; i < inventorySize; i++) {
			inventory[i] = ItemStack.EMPTY;
		}
		totalWorkNeededForThisTask = 1000;

	}

	@Override
	public void tick() {

		if (!world.isRemote) {
			if (inventory[0].getItem() instanceof ItemBlueprint) {
				totalWorkNeededForThisTask = ((ItemBlueprint) inventory[0].getItem()).workRequired;
			}

			if (totalWorkNeededForThisTask == workConsumedForThisTask) {
				// TODO : Generate the output somehow!
				// LogHelper.info("Total Work Reached!");
				if (getStackInSlot(1) == ItemStack.EMPTY) {
					setInventorySlotContents(1, getOutputItemStack());

					workConsumedForThisTask = 0;
					hasBuildProject = false;
					this.markDirty();
				}
			}
		}
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		// inventory
		ListNBT tagList = compound.getList(Reference.MACHINE_MOD_NBT_PREFIX + "Inventory", compound.getId());
		for (int i = 0; i < tagList.size(); i++) {
			CompoundNBT tag = (CompoundNBT) tagList.getCompound(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inventory.length) {
				inventory[slot] = ItemStack.read(tag);
			}
		}

		totalWorkNeededForThisTask = compound.getInt(Reference.MACHINE_MOD_NBT_PREFIX + "totalWorkNeededForThisTask");

		workConsumedForThisTask = compound.getInt(Reference.MACHINE_MOD_NBT_PREFIX + "workConsumedForThisTask");
		hasBuildProject = compound.getBoolean(Reference.MACHINE_MOD_NBT_PREFIX + "hasBuildProject");

	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
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

		compound.putInt(Reference.MACHINE_MOD_NBT_PREFIX + "totalWorkNeededForThisTask", totalWorkNeededForThisTask);
		compound.putInt(Reference.MACHINE_MOD_NBT_PREFIX + "workConsumedForThisTask", workConsumedForThisTask);
		compound.putBoolean(Reference.MACHINE_MOD_NBT_PREFIX + "hasBuildProject", hasBuildProject);

		return compound;

	}

	public int getField(int id) {
		switch (id) {
		case 0:
			return totalWorkNeededForThisTask;
		case 1:
			return workConsumedForThisTask;
		case 2:
			return hasBuildProject ? 1 : 0;
		default:
			break;
		}
		return 0;

	}

	public void setField(int id, int value) {
		switch (id) {
		case 0:
			totalWorkNeededForThisTask = value;
		case 1:
			workConsumedForThisTask = value;
		case 2:
			hasBuildProject = value == 0 ? false : true;
		default:
			break;
		}

	}

	public int getFieldCount() {
		return 3;
	}

	@Override
	public boolean isUsableByPlayer(PlayerEntity playerIn) {
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
	public ITextComponent getDisplayName() {
		return null;
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
	public boolean isEmpty() {
		for (int i = 0; i < inventory.length; i++) {
			if (!inventory[i].isEmpty()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public int appyWork(int Amount) {

		if (amountCanConsume() >= Amount) {
			// 0 return value we can consume it all
			workConsumedForThisTask += Amount;
			this.markDirty();
			return 0;
		} else {
			workConsumedForThisTask += amountCanConsume();
			this.markDirty();
			return Amount - amountCanConsume();
		}

	}

	@Override
	public boolean isWorkNeeded() {
		// TODO Auto-generated method stub
		if (hasBuildProject == false) {
			// no active project so dont accept work.
			return false;
		}
		if (totalWorkNeededForThisTask > workConsumedForThisTask) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int amountCanConsume() {

		return totalWorkNeededForThisTask - workConsumedForThisTask;

	}

	public String getFriendlyOutputname() {
		if (!getStackInSlot(0).isEmpty()) {
			if (getStackInSlot(0).getItem() instanceof ItemBlueprint) {
				String outputItemName = ((ItemBlueprint) getStackInSlot(0).getItem()).outputItemName;
				Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(outputItemName));
				if (item != null) {
					String displayName = item.getItemStackDisplayName(new ItemStack(item));
					return displayName;
				} else {
					LogHelper.info("An Output of an ingredent is null Tell Tech please!" + outputItemName);
				}
			}
		}
		return null;
	}

	public ItemStack getOutputItemStack() {
		ItemStack is = ItemStack.EMPTY;
		if (!getStackInSlot(0).isEmpty()) {
			if (getStackInSlot(0).getItem() instanceof ItemBlueprint) {
				String outputItemName = ((ItemBlueprint) getStackInSlot(0).getItem()).outputItemName;
				Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(outputItemName));
				if (item != null) {
					is = new ItemStack(item, 1);
				} else {
					LogHelper.info("An Output of an ingredent is null Tell Tech please!" + outputItemName);
				}

			}
		}
		return is;
	}

	public List<String> getFriendlyIngredentList() {
		List<String> returnValue = new ArrayList<String>();
		if (!getStackInSlot(0).isEmpty()) {
			if (getStackInSlot(0).getItem() instanceof ItemBlueprint) {

				for (ItemBlueprint.BlueprintIngredent ingredent : ((ItemBlueprint) getStackInSlot(0).getItem()).ingredents) {
					String ingredentName = ingredent.getName();
					Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(ingredentName));
					if (item != null) {
						returnValue.add(item.getItemStackDisplayName(new ItemStack(item)) + " X " + ingredent.getCount());

					} else {
						LogHelper.info("An Ingredent is null Tell Tech please!" + ingredentName);
					}
				}
			}
		}
		return returnValue;
	}

	public boolean playerHasNeededItemsInInventory(PlayerEntity player) {
		boolean result = false;
		if (!getStackInSlot(0).isEmpty()) {
			if (getStackInSlot(0).getItem() instanceof ItemBlueprint) {

				Item[] is = new Item[((ItemBlueprint) getStackInSlot(0).getItem()).ingredents.size()];
				int[] amtNeeded = new int[is.length];
				int j = 0;
				for (ItemBlueprint.BlueprintIngredent ingredent : ((ItemBlueprint) getStackInSlot(0).getItem()).ingredents) {
					is[j] = ForgeRegistries.ITEMS.getValue(new ResourceLocation(ingredent.getName()));
					if (is[j] != null) {
						amtNeeded[j] = ingredent.getCount();
						j++;
					} else {
						LogHelper.info("An Ingredent is null Tell Tech please!" + ingredent.getName());

					}
				}

				InventoryPlayer ip = player.inventory;
				int size = ip.getSizeInventory();
				for (int i = 0; i < size; i++) {
					ItemStack playerStack = ip.getStackInSlot(i);

					if (!playerStack.isEmpty()) {
						for (j = 0; j < is.length; j++) {
							if (areItemsEqualIgnoreSize(playerStack.getItem(), is[j])) {
								// same item check for amounts is it enough to satisfy if not just reduce the amt need by amt in this slot.
								amtNeeded[j] = amtNeeded[j] - playerStack.getCount();
								if (amtNeeded[j] <= 0) {
									// we finally have enough of this item go on to next player slot!
									j = is.length;
								}
							}
						}
					}
				}
				result = true;// assume we are OK and only set false if we are not

				for (j = 0; j < is.length; j++) {
					// check all the blueprint slots to seee if we have satisfied them all. if we have set the var to true

					if (amtNeeded[j] > 0) {
						// found one with more than one so sorry we failed.
						player.sendMessage(new TextComponentString(TextFormatting.RED + "Missing: " + TextFormatting.WHITE + is[j].getItemStackDisplayName(new ItemStack(is[j])) + " X " + TextFormatting.BLUE + amtNeeded[j]));
						result = false;
					}
				}

			}
		}
		return result;
	}

	public void removeBlueprintItemsFromPlayersInventory(PlayerEntity player) {
		boolean result = false;
		if (!getStackInSlot(0).isEmpty()) {
			if (getStackInSlot(0).getItem() instanceof ItemBlueprint) {

				Item[] is = new Item[((ItemBlueprint) getStackInSlot(0).getItem()).ingredents.size()];
				int[] amtNeeded = new int[is.length];
				int j = 0;
				for (ItemBlueprint.BlueprintIngredent ingredent : ((ItemBlueprint) getStackInSlot(0).getItem()).ingredents) {
					is[j] = ForgeRegistries.ITEMS.getValue(new ResourceLocation(ingredent.getName()));
					if (is[j] != null) {
						amtNeeded[j] = ingredent.getCount();
						j++;
					} else {
						LogHelper.info("An Ingredent is null Tell Tech please!" + ingredent.getName());

					}
				}

				InventoryPlayer ip = player.inventory;
				int size = ip.getSizeInventory();
				for (int i = 0; i < size; i++) {
					ItemStack playerStack = ip.getStackInSlot(i);

					if (!playerStack.isEmpty()) {
						for (j = 0; j < is.length; j++) {
							if (areItemsEqualIgnoreSize(playerStack.getItem(), is[j])) {
								// same item check for amounts is it enough to satisfy if not just reduce the amt need by amt in this slot.
								int count = playerStack.getCount();
								if (amtNeeded[j] > playerStack.getCount()) {
									// remove all
									ip.setInventorySlotContents(i, ItemStack.EMPTY);
									amtNeeded[j] = amtNeeded[j] - count;
								} else {
									// amt needed is less than stack so reduce the count by amtneeded
									ip.decrStackSize(i, amtNeeded[j]);
									amtNeeded[j] = amtNeeded[j] - amtNeeded[j];
								}

							}
						}
					}
				}

			}
		}
	}

	private boolean areItemsEqualIgnoreSize(Item a, Item b) {
		if (b != a) {
			return false;
		} else {
			return true;
		}

	}

	// TODO: add check to see if we habe items then Need to remove the items from the inventory still
	@Override
	public void HandleGuiButton(int buttonId, PlayerEntity player) {
		/// LogHelper.info("the button was clicked and the server knows!");
		if (!getStackInSlot(0).isEmpty()) {
			if (getStackInSlot(0).getItem() instanceof ItemBlueprint) {
				if (getStackInSlot(1) == ItemStack.EMPTY) {
					if (playerHasNeededItemsInInventory(player)) {
						removeBlueprintItemsFromPlayersInventory(player);
						workConsumedForThisTask = 0;
						hasBuildProject = true;
						this.markDirty();
					}
				}
			}
		}
	}
}
