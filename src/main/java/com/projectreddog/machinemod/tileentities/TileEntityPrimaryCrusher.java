package com.projectreddog.machinemod.tileentities;

import java.util.List;

import net.minecraft.block.BlockStone;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;

import com.projectreddog.machinemod.block.BlockMachineModBlastedStone;
import com.projectreddog.machinemod.block.BlockMachineModPrimaryCrusher;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.reference.Reference;

public class TileEntityPrimaryCrusher extends TileEntity implements IUpdatePlayerListBox, ISidedInventory {
	protected ItemStack[] inventory;
	private static int[] bottomSlots = new int[] {};
	private static int[] topSlots = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53 };
	public AxisAlignedBB boundingBox;

	public final int BlastedStoneOreMultiplier = 3;
	public final int VanillaOreMultiplier = 2;
	public final int BlastedStoneCoalMultiplier = 3;
	public final int BlastedStoneGemMultiplier = 2;
	public final int BlastedStoneLapisMultiplier = 2;
	public final int BlastedStoneRedstoneMultiplier = 2;

	public TileEntityPrimaryCrusher() {
		inventory = new ItemStack[54];

	}

	@Override
	public void update() {
		if (!worldObj.isRemote) {
			// LogHelper.info("TE update entity called");
			boundingBox = new AxisAlignedBB(this.pos.offsetUp(), this.pos.offsetUp().add(1, 1, 1));
			List list = worldObj.getEntitiesWithinAABB(EntityItem.class, boundingBox);
			processEntitiesInList(list);
			for (int i = 0; i < this.getSizeInventory(); i++) {
				ItemStack item = this.getStackInSlot(i);
				if (item != null) {
					if (item.getItem() == Item.getItemFromBlock(ModBlocks.machinemodblastedstone)) {
						if (item.getMetadata() == BlockMachineModBlastedStone.EnumVanillaOres.IRON.getMetadata()) {
							dropDust(i, new ItemStack(ModItems.irondust, BlastedStoneOreMultiplier));
							return;
						} else if (item.getMetadata() == BlockMachineModBlastedStone.EnumVanillaOres.GOLD.getMetadata()) {
							dropDust(i, new ItemStack(ModItems.golddust, BlastedStoneOreMultiplier));
							return;
						} else if (item.getMetadata() == BlockMachineModBlastedStone.EnumVanillaOres.STONE.getMetadata()) {
							dropDust(i, new ItemStack(Blocks.cobblestone, 1));
							return;
						} else if (item.getMetadata() == BlockMachineModBlastedStone.EnumVanillaOres.ANDESITE.getMetadata()) {
							dropDust(i, new ItemStack(Blocks.stone, 1, BlockStone.EnumType.ANDESITE.getMetaFromState()));
							return;
						} else if (item.getMetadata() == BlockMachineModBlastedStone.EnumVanillaOres.DIORITE.getMetadata()) {
							dropDust(i, new ItemStack(Blocks.stone, 1, BlockStone.EnumType.DIORITE.getMetaFromState()));
							return;
						} else if (item.getMetadata() == BlockMachineModBlastedStone.EnumVanillaOres.GRANITE.getMetadata()) {
							dropDust(i, new ItemStack(Blocks.stone, 1, BlockStone.EnumType.GRANITE.getMetaFromState()));
							return;
						} else if (item.getMetadata() == BlockMachineModBlastedStone.EnumVanillaOres.COAL.getMetadata()) {
							dropDust(i, new ItemStack(Items.coal, BlastedStoneCoalMultiplier));
							return;
						} else if (item.getMetadata() == BlockMachineModBlastedStone.EnumVanillaOres.DIAMOND.getMetadata()) {
							dropDust(i, new ItemStack(Items.diamond, BlastedStoneGemMultiplier));
							return;
						} else if (item.getMetadata() == BlockMachineModBlastedStone.EnumVanillaOres.EMERALD.getMetadata()) {
							dropDust(i, new ItemStack(Items.emerald, BlastedStoneGemMultiplier));
							return;
						} else if (item.getMetadata() == BlockMachineModBlastedStone.EnumVanillaOres.LAPIS.getMetadata()) {
							dropDust(i, new ItemStack(Items.dye, BlastedStoneLapisMultiplier, EnumDyeColor.BLUE.getDyeColorDamage()));
							return;
						} else if (item.getMetadata() == BlockMachineModBlastedStone.EnumVanillaOres.REDSTONE.getMetadata()) {
							dropDust(i, new ItemStack(Items.redstone, BlastedStoneRedstoneMultiplier));
							return;
						}
					} else if (item.getItem() == Item.getItemFromBlock(Blocks.iron_ore)) {
						dropDust(i, new ItemStack(ModItems.irondust, VanillaOreMultiplier));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(Blocks.gold_ore)) {
						dropDust(i, new ItemStack(ModItems.golddust, VanillaOreMultiplier));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(Blocks.stone) && item.getMetadata() == BlockStone.EnumType.STONE.getMetaFromState()) {
						dropDust(i, new ItemStack(Blocks.cobblestone, 1));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(Blocks.stone)) {
						dropDust(i, new ItemStack(item.getItem(), 1));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(Blocks.coal_ore)) {
						dropDust(i, new ItemStack(Items.coal, 1));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(Blocks.diamond_ore)) {
						dropDust(i, new ItemStack(Items.diamond, 1));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(Blocks.emerald_ore)) {
						dropDust(i, new ItemStack(Items.emerald, 1));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(Blocks.lapis_ore)) {
						dropDust(i, new ItemStack(Items.dye, 1, EnumDyeColor.BLUE.getDyeColorDamage()));
						return;
					} else if (item.getItem() == Item.getItemFromBlock(Blocks.redstone_ore)) {
						dropDust(i, new ItemStack(Items.redstone, 1));
						return;
					} else {
						dropDust(i, new ItemStack(item.getItem(), 1, item.getMetadata()));
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
		decrStackSize(slot, 1);
		// TODO position entity based on enum facing :D
		double ejectOffsetX = 0d;
		double ejectOffsetZ = 0d;
		EnumFacing ef = (EnumFacing) worldObj.getBlockState(this.getPos()).getValue(BlockMachineModPrimaryCrusher.FACING);
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
		EntityItem entityItem = new EntityItem(worldObj, this.pos.getX() + ejectOffsetX, this.pos.getY(), this.pos.getZ() + ejectOffsetZ, is);

		entityItem.forceSpawn = true;
		entityItem.setVelocity(0, 0, 0);
		worldObj.spawnEntityInWorld(entityItem);
	}

	private void processEntitiesInList(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity != null) {
				if (entity instanceof EntityItem) {
					ItemStack is = ((EntityItem) entity).getEntityItem();
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
				// entity.setPosition(entity.getPosition().getX() + 0.1d, entity.getPosition().getY(), entity.getPosition().getZ());
			}
		}
	}

	protected ItemStack addToinventory(ItemStack is) {
		int i = getSizeInventory();

		for (int j = 0; j < i && is != null && is.stackSize > 0; ++j) {
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
					// nothign in slot so set contents
					setInventorySlotContents(j, new ItemStack(is.getItem(), is.stackSize, is.getItemDamage()));
					is = null;
				}

			}

		}

		return is;

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
	public void writeToNBT(NBTTagCompound compound) {
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

}
