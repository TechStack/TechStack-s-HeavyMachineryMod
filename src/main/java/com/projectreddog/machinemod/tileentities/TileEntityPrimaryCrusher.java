package com.projectreddog.machinemod.tileentities;

import java.util.List;

import com.projectreddog.machinemod.block.BlockMachineModPrimaryCrusher;
import com.projectreddog.machinemod.iface.IFuelContainer;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.BlockStone;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;

public class TileEntityPrimaryCrusher extends TileEntity implements ITickableTileEntity, ISidedInventory, IFuelContainer {
	protected ItemStack[] inventory;
	private static int[] bottomSlots = new int[] {};
	private static int[] topSlots = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53 };
	public final int maxFuelStorage = 5000; // store up to 10k (can fill all 9 cans & have room for one more
	public int fuelStorage = 0;
	public AxisAlignedBB boundingBox;
	public int coolDownAmount = 5;
	public int timeTillCoolDown = 0;

	public final int BlastedStoneOreMultiplier = 3;
	public final int VanillaOreMultiplier = 2;
	public final int BlastedStoneCoalMultiplier = 3;
	public final int BlastedStoneGemMultiplier = 2;
	public final int BlastedStoneLapisMultiplier = 12;
	public final int BlastedStoneRedstoneMultiplier = 8;
	int inventorySize = 54;

	public TileEntityPrimaryCrusher() {
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

			// LogHelper.info("TE update entity called");
			boundingBox = new AxisAlignedBB(this.pos.up(), this.pos.up().add(1, 1, 1));
			List list = world.getEntitiesWithinAABB(EntityItem.class, boundingBox);
			processEntitiesInList(list);
			for (int i = 0; i < this.getSizeInventory(); i++) {
				ItemStack item = this.getStackInSlot(i);
				if (!item.isEmpty()) {
					if (item.getItem() == Item.getItemFromBlock(ModBlocks.machineblastedgranite)) {
						dropDust(i, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.GRANITE.getMetadata()));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(ModBlocks.machineblastediron)) {
						dropDust(i, new ItemStack(ModItems.irondust, BlastedStoneOreMultiplier));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(ModBlocks.machineblastedgold)) {
						dropDust(i, new ItemStack(ModItems.golddust, BlastedStoneOreMultiplier));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(ModBlocks.machineblastedstone)) {
						dropDust(i, new ItemStack(Blocks.COBBLESTONE, 1));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(ModBlocks.machineblastedandesite)) {
						dropDust(i, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.ANDESITE.getMetadata()));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(ModBlocks.machineblasteddiorite)) {
						dropDust(i, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.DIORITE.getMetadata()));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(ModBlocks.machineblastedstone)) {
						dropDust(i, new ItemStack(Blocks.STONE, 1, BlockStone.EnumType.DIORITE.getMetadata()));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(ModBlocks.machineblastedcoal)) {
						dropDust(i, new ItemStack(Items.COAL, BlastedStoneCoalMultiplier));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(ModBlocks.machineblasteddiamond)) {
						dropDust(i, new ItemStack(Items.DIAMOND, BlastedStoneGemMultiplier));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(ModBlocks.machineblastedemerald)) {
						dropDust(i, new ItemStack(Items.EMERALD, BlastedStoneGemMultiplier));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(ModBlocks.machineblastedlapis)) {
						dropDust(i, new ItemStack(Items.DYE, BlastedStoneLapisMultiplier, EnumDyeColor.BLUE.getDyeDamage()));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(ModBlocks.machineblastedredstone)) {
						dropDust(i, new ItemStack(Items.REDSTONE, BlastedStoneRedstoneMultiplier));
						return;

					} else if (item.getItem() == Item.getItemFromBlock(Blocks.IRON_ORE)) {
						dropDust(i, new ItemStack(ModItems.irondust, VanillaOreMultiplier));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(Blocks.GOLD_ORE)) {
						dropDust(i, new ItemStack(ModItems.golddust, VanillaOreMultiplier));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(Blocks.STONE) && item.getMetadata() == BlockStone.EnumType.STONE.getMetadata()) {
						dropDust(i, new ItemStack(Blocks.COBBLESTONE, 1));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(Blocks.STONE)) {
						dropDust(i, new ItemStack(item.getItem(), 1, item.getItemDamage()));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(Blocks.COAL_ORE)) {
						dropDust(i, new ItemStack(Items.COAL, 1));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(Blocks.DIAMOND_ORE)) {
						dropDust(i, new ItemStack(Items.DIAMOND, 1));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(Blocks.EMERALD_ORE)) {
						dropDust(i, new ItemStack(Items.EMERALD, 1));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(Blocks.LAPIS_ORE)) {
						dropDust(i, new ItemStack(Items.DYE, 7, EnumDyeColor.BLUE.getDyeDamage()));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(Blocks.REDSTONE_ORE)) {
						dropDust(i, new ItemStack(Items.REDSTONE, 5));
						return;
					} else if (item.getItem() == Items.BONE) {
						dropDust(i, new ItemStack(Items.DYE, 5, EnumDyeColor.WHITE.getDyeDamage()));
						return;
					} else if (item.getItem() == Items.COAL) {
						dropDust(i, new ItemStack(ModItems.carbondust, 1));
						return;
					} else if (item.getItem() == Items.IRON_INGOT) {
						dropDust(i, new ItemStack(ModItems.irondust, 1));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(Blocks.GRAVEL)) {
						dropDust(i, new ItemStack(Blocks.SAND, 1));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(Blocks.SANDSTONE)) {
						dropDust(i, new ItemStack(Blocks.SAND, 4));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(Blocks.WOOL)) {
						dropDust(i, new ItemStack(Items.STRING, 4));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(Blocks.COBBLESTONE)) {
						dropDust(i, new ItemStack(Blocks.GRAVEL, 1));
						return;
					} else {

						ItemStack tmpstack = item.copy();
						if (tmpstack.getCount() > 1) {
							tmpstack.setCount(1);
						}

						dropDust(i, tmpstack);
						return;
					}
				}
			}
		}

	}

	/*
	 * Drops an item on the ground after reducing the
	 */
	private void dropDust(int slot, ItemStack is) {
		if (fuelStorage > 0) {
			decrStackSize(slot, 1);
			fuelStorage = fuelStorage - 1;
			double ejectOffsetX = 0d;
			double ejectOffsetZ = 0d;
			EnumFacing ef = (EnumFacing) world.getBlockState(this.getPos()).getValue(BlockMachineModPrimaryCrusher.FACING);
			switch (ef) {
			case NORTH:
				// no rotate?
				ejectOffsetX = .5d;
				ejectOffsetZ = -.2d;
				break;
			case SOUTH:
				// rotate to south
				ejectOffsetX = .5d;
				ejectOffsetZ = 1.2d;
				break;
			case EAST:
				ejectOffsetX = 1.2d;
				ejectOffsetZ = .5d;
				break;
			case WEST:
				ejectOffsetX = -.2d;
				ejectOffsetZ = .5d;
				break;
			default:
				// should never happen because we are constrained to the horizontal plane so just break with 0 (default) offsets
				break;

			}
			EntityItem entityItem = new EntityItem(world, this.pos.getX() + ejectOffsetX, this.pos.getY(), this.pos.getZ() + ejectOffsetZ, is);

			entityItem.forceSpawn = true;
			entityItem.motionX = 0;
			entityItem.motionY = 0;
			entityItem.motionZ = 0;
			world.spawnEntity(entityItem);
		}
	}

	private void processEntitiesInList(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity != null) {
				if (entity instanceof EntityItem) {
					ItemStack is = ((EntityItem) entity).getItem();
					is.setItemDamage(((EntityItem) entity).getItem().getItemDamage());
					if (!entity.isDead) {
						if (is.getCount() > 0) {
							ItemStack is1 = addToinventory(is);

							if (!is1.isEmpty() && is1.getCount() != 0) {
								((EntityItem) entity).setItem(is1);
							} else {
								entity.setDead();
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
					if (getStackInSlot(j).getItem() == is.getItem() && getStackInSlot(j).getItemDamage() == is.getItemDamage()) {
						// same item remove from is put into slot any amt not to
						// excede stack max
						if (getStackInSlot(j).getCount() < getStackInSlot(j).getMaxStackSize()) {
							// we have room to add to this stack
							if (is.getCount() <= getStackInSlot(j).getMaxStackSize() - getStackInSlot(j).getCount()) {
								// /all of the stack will fit in this slot do
								// so.

								setInventorySlotContents(j, new ItemStack(getStackInSlot(j).getItem(), getStackInSlot(j).getCount() + is.getCount(), is.getItemDamage()));
								is = ItemStack.EMPTY;
							} else {
								// we have more
								int countRemain = is.getCount() - (getStackInSlot(j).getMaxStackSize() - getStackInSlot(j).getCount());
								setInventorySlotContents(j, new ItemStack(is.getItem(), getStackInSlot(j).getMaxStackSize(), is.getItemDamage()));
								is.setCount(countRemain);
							}

						}
					}
				} else {
					// nothign in slot so set contents
					setInventorySlotContents(j, is.copy());
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
	public int getField(int id) {
		switch (id) {
		case 0:
			return this.fuelStorage;

		default:
			break;
		}
		return 0;

	}

	@Override
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
	public EnumFacing outputDirection() {
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
