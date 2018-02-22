package com.projectreddog.machinemod.tileentities;

import com.projectreddog.machinemod.block.BlockMachineModPrimaryCrusher;
import com.projectreddog.machinemod.block.BlockMachineModTowerCrane;
import com.projectreddog.machinemod.iface.IFuelContainer;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.network.MachineModMessageTETowerCranePosToClient;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.BlockBlueprintHelper;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityTowerCrane extends TileEntity implements ITickable, ISidedInventory, IFuelContainer {
	protected ItemStack[] inventory;
	private static int[] sideSlots = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
	public final int maxFuelStorage = 10000; // store up to 10k (can fill all 9 cans & have room for one more
	public int fuelStorage = 0;
	public final int inventorySize = 9;
	public final int coolDownReset = 1200;
	public int cooldown = coolDownReset;
	public IBlockState[][][] BlockBluePrintArray;

	public int state = -1;
	// 0 Arm rotate to loading position and gantry moving to 0 as well.
	// 1 Arm in loading pos - Lower wench
	// 2 Wench picked up the block - return up
	// 3 arm rotating to pos.
	// 4 wench lowering
	// 5 at pos place block !!!
	// 6 wench going up

	public double armRotation;
	public double gantryPos;
	public double wenchPos;

	public double targetArmRotation;
	public double targetGantryPos;
	public double targetWenchPos;

	public int currentX = 0;
	public int currentY = 0;
	public int currentZ = 0;

	private double prevArmRotation;
	private double prevGantryPos;
	private double prevWencPos;

	public TileEntityTowerCrane() {
		inventory = new ItemStack[inventorySize];
		for (int i = 0; i < inventorySize; i++) {
			inventory[i] = ItemStack.EMPTY;
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.util.math.AxisAlignedBB getRenderBoundingBox() {
		return TileEntity.INFINITE_EXTENT_AABB;
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
	public void update() {
		if (world.isRemote || !world.isRemote) {

			if (BlockBluePrintArray == null) {
				BlockBluePrintArray = BlockBlueprintHelper.getBlockStateArray("TESTFILE");
			}
		}
		if (!world.isRemote) { // only run on server

			// TODO FIx to make server only latter and then use packets to update clients around !! yeah
			//

			// public int state = 0;
			// // 0 Arm rotate to loading position and gantry moving to 0 as well.
			// // 1 Arm in loading pos - Lower wench
			// // 2 Wench picked up the block - return up
			// // 3 arm rotating to pos.
			// // 4 wench lowering
			// // 5 at pos place block !!!
			// // NOt needed 6 wench going up
			//
			// public double armRotation;
			// public double gantryPos;
			// public double wenchPos;
			//
			// public double targetArmRotation;
			// public double targetGantryPos;
			// public double targetWenchPos;

			double stepAmt = 1d;

			if (Math.abs(targetArmRotation - armRotation) < stepAmt) {
				armRotation = targetArmRotation;
			}

			if (armRotation < targetArmRotation) {
				armRotation = armRotation + stepAmt;
			} else if (armRotation > targetArmRotation) {
				armRotation = armRotation - stepAmt;
			}

			if (Math.abs(targetGantryPos - gantryPos) < stepAmt) {
				gantryPos = targetGantryPos;
			}

			if (gantryPos < targetGantryPos) {
				gantryPos = gantryPos + stepAmt;
			} else if (gantryPos > targetGantryPos) {
				gantryPos = gantryPos - stepAmt;
			}

			if (Math.abs(targetWenchPos - wenchPos) < stepAmt) {
				wenchPos = targetWenchPos;
			}
			if (wenchPos < targetWenchPos) {
				wenchPos = wenchPos + stepAmt;
			} else if (wenchPos > targetWenchPos) {
				wenchPos = wenchPos - stepAmt;
			}

			if ((armRotation == targetArmRotation && gantryPos == targetGantryPos && wenchPos == targetWenchPos) || state == -1) {
				state = state + 1;
				// set new targets for state

				setTargetsForState();

			}
			ModNetwork.sendPacketToAllAround(new MachineModMessageTETowerCranePosToClient(this.pos.getX(), this.pos.getY(), this.pos.getZ(), state, armRotation, gantryPos, wenchPos, targetArmRotation, targetGantryPos, targetWenchPos, currentX, currentY, currentZ), new TargetPoint(world.provider.getDimension(), this.pos.getX(), this.pos.getY(), this.pos.getZ(), 224)); // sendInterval = 0;
		}
	}

	public EnumFacing getFacing() {

		EnumFacing ef;
		if (this.getWorld().getBlockState(this.getPos()).getBlock() == ModBlocks.machinetowercrane) {
			ef = (EnumFacing) this.getWorld().getBlockState(this.getPos()).getValue(BlockMachineModTowerCrane.FACING);
		} else {
			ef = EnumFacing.NORTH;
		}

		return ef;
	}

	public int getXWithOffset(int x, int z) {

		EnumFacing enumfacing = getFacing();
		switch (enumfacing) {
		case NORTH:
			return x - 17;
		case SOUTH:
			return 17 - x;// return this.boundingBox.minX + x;
		case WEST:
			return z - 17;
		case EAST:
			return 17 - z;
		default:
			return x;
		}
	}

	public int getZWithOffset(int x, int z) {

		EnumFacing enumfacing = getFacing();
		switch (enumfacing) {
		case NORTH:
			return 17 - z;
		case SOUTH:
			return z - 17;// return this.boundingBox.minX + x;
		case WEST:
			return x - 17;
		case EAST:
			return 17 - x;
		default:
			return z;
		}
	}

	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {
		return 65536D;
	}

	public int getPlacingXWithOffset(int x, int z) {

		EnumFacing enumfacing = getFacing();
		switch (enumfacing) {
		case NORTH:
			return x - 17;
		case SOUTH:
			return 17 - x;// return this.boundingBox.minX + x;
		case WEST:
			return z - 17;
		case EAST:
			return 17 - z;
		default:
			return x;
		}
	}

	public int getPlacingZWithOffset(int x, int z) {

		EnumFacing enumfacing = getFacing();
		switch (enumfacing) {
		case NORTH:
			return z - 17;
		case SOUTH:
			return 17 - z;// return this.boundingBox.minX + x;
		case WEST:
			return 17 - x;
		case EAST:
			return x - 17;
		default:
			return z;
		}
	}

	public double getPickupRotationLocation() {
		EnumFacing enumfacing = getFacing();
		switch (enumfacing) {
		case NORTH:
			return 90d;
		case SOUTH:
			return -90d;// return this.boundingBox.minX + x;
		case WEST:
			return 180d;
		case EAST:
			return 90d;
		default:
			return 0d;
		}
	}

	public void setTargetsForState() {

		int adjustedX = getXWithOffset(currentX, currentZ);
		int adjustedZ = getZWithOffset(currentX, currentZ);

		int placingposX = getPlacingXWithOffset(currentX, currentZ);
		int placingposZ = getPlacingZWithOffset(currentX, currentZ);
		if (state > 6) {
			state = 0;
		}
		if (state == 0) {
			targetArmRotation = getPickupRotationLocation();
			targetGantryPos = 2;
			targetWenchPos = currentY + 5;

		}
		if (state == 1) {
			targetArmRotation = getPickupRotationLocation();
			targetGantryPos = 2;
			targetWenchPos = 0;

		}
		if (state == 2) {
			targetArmRotation = getPickupRotationLocation();
			targetGantryPos = 2;
			targetWenchPos = currentY + 5;

		}
		if (state == 3) {
			// // GL11.glRotated(90d - MathHelper.atan2(x, z) * 180d / 3.14, 0, 1, 0);

			targetArmRotation = 90d - MathHelper.atan2(adjustedX, adjustedZ) * 180d / 3.14;
			targetGantryPos = Math.sqrt(adjustedX * adjustedX + (adjustedZ) * (adjustedZ));
			targetWenchPos = currentY + 5;

		}
		if (state == 4) {
			targetArmRotation = 90d - MathHelper.atan2(adjustedX, adjustedZ) * 180d / 3.14;
			targetGantryPos = Math.sqrt(adjustedX * adjustedX + (adjustedZ) * (adjustedZ));
			targetWenchPos = currentY;

		}

		if (state == 5) {//
							// TODO call block place code!

			// BlockBlueprintHelper.BuildBlocks("TESTFILE", this.world, this.pos, Rotation.NONE, false, currentX, currentY, currentZ);
			BlockBlueprintHelper.setBlockState(this.world, this.pos.add(placingposX, currentY, placingposZ), BlockBluePrintArray[currentX][currentY][currentZ], getFacing());

			// prevArmRotation=targetGantryPos;
			// prevGantryPos=targetGantryPos;
			// prevWencPos;

			currentX = currentX + 1;
			if (currentX > 16) {
				currentX = 0;
				currentZ = currentZ + 1;
			}
			if (currentZ > 16) {
				currentZ = 0;
				currentY = currentY + 1;
			}

			if (currentY > 16) {
				currentY = 0;
				currentX = 0;
				currentZ = 0;

			}

			while (BlockBluePrintArray[currentX][currentY][currentZ].getBlock() == Blocks.AIR) {

				currentX = currentX + 1;
				if (currentX > 16) {
					currentX = 0;
					currentZ = currentZ + 1;
				}
				if (currentZ > 16) {
					currentZ = 0;
					currentY = currentY + 1;
				}

				if (currentY > 16) {
					currentY = 0;
					currentX = 0;
					currentZ = 0;

				}
			}

			// state = 4;
			targetArmRotation = 90d - MathHelper.atan2(adjustedX, adjustedZ) * 180d / 3.14;
			targetGantryPos = Math.sqrt(adjustedX * adjustedX + (adjustedZ) * (adjustedZ));
			targetWenchPos = currentY + 5;

		}
		if (state == 6) {//
			// state = 0;
			// targetArmRotation = 90d - MathHelper.atan2(adjustedX, adjustedZ) * 180d / 3.14;
			// targetGantryPos = Math.sqrt(adjustedX * adjustedX + (adjustedZ) * (adjustedZ));
			// targetWenchPos = currentY + 5;

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
					setInventorySlotContents(j, new ItemStack(is.getItem(), is.getCount(), is.getItemDamage()));
					is = ItemStack.EMPTY;
				}

			}

		}

		return is;

	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {

		super.readFromNBT(compound);
		fuelStorage = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "FUEL_STORAGE");
		cooldown = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "COOL_DOWN");

		state = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "STATE");
		armRotation = compound.getDouble(Reference.MACHINE_MOD_NBT_PREFIX + "armRotation");
		gantryPos = compound.getDouble(Reference.MACHINE_MOD_NBT_PREFIX + "gantryPos");
		wenchPos = compound.getDouble(Reference.MACHINE_MOD_NBT_PREFIX + "wenchPos");
		targetArmRotation = compound.getDouble(Reference.MACHINE_MOD_NBT_PREFIX + "targetArmRotation");
		targetGantryPos = compound.getDouble(Reference.MACHINE_MOD_NBT_PREFIX + "targetGantryPos");
		targetWenchPos = compound.getDouble(Reference.MACHINE_MOD_NBT_PREFIX + "targetWenchPos");
		currentX = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "currentX");
		currentY = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "currentY");
		currentZ = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "currentZ");

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
		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "FUEL_STORAGE", fuelStorage);
		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "COOL_DOWN", cooldown);

		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "STATE", state);
		compound.setDouble(Reference.MACHINE_MOD_NBT_PREFIX + "armRotation", armRotation);
		compound.setDouble(Reference.MACHINE_MOD_NBT_PREFIX + "gantryPos", gantryPos);
		compound.setDouble(Reference.MACHINE_MOD_NBT_PREFIX + "wenchPos", wenchPos);
		compound.setDouble(Reference.MACHINE_MOD_NBT_PREFIX + "targetArmRotation", targetArmRotation);
		compound.setDouble(Reference.MACHINE_MOD_NBT_PREFIX + "targetGantryPos", targetGantryPos);
		compound.setDouble(Reference.MACHINE_MOD_NBT_PREFIX + "targetWenchPos", targetWenchPos);
		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "currentX", currentX);
		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "currentY", currentY);
		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "currentZ", currentZ);

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
		if (side == EnumFacing.NORTH || side == EnumFacing.SOUTH || side == EnumFacing.EAST || side == EnumFacing.WEST) {
			return sideSlots;
		}
		int[] topSlots2 = new int[] { 0 };
		return topSlots2;

	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemStackIn, EnumFacing direction) {
		if (slot < inventorySize && (direction == EnumFacing.NORTH || direction == EnumFacing.SOUTH || direction == EnumFacing.EAST || direction == EnumFacing.WEST)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, EnumFacing direction) {
		if (slot < inventorySize && (direction == EnumFacing.NORTH || direction == EnumFacing.SOUTH || direction == EnumFacing.EAST || direction == EnumFacing.WEST)) {
			return true;
		}
		return false;
	}

	@Override
	public EnumFacing outputDirection() {
		EnumFacing ef = (EnumFacing) world.getBlockState(this.getPos()).getValue(BlockMachineModPrimaryCrusher.FACING);
		// switch (ef) {
		// case NORTH:
		// return EnumFacing.SOUTH;
		// case SOUTH:
		// return EnumFacing.NORTH;
		// case EAST:
		// return EnumFacing.WEST;
		// case WEST:
		// return EnumFacing.EAST;
		// default:
		// return null;
		// }
		return ef;
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
