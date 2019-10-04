package com.projectreddog.machinemod.tileentities;

import com.projectreddog.machinemod.block.BlockMachineModPrimaryCrusher;
import com.projectreddog.machinemod.block.BlockMachineModTowerCrane;
import com.projectreddog.machinemod.iface.IFuelContainer;
import com.projectreddog.machinemod.iface.ITEGuiButtonHandler;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.network.MachineModMessageEntityBluerprintBlockStateToClient;
import com.projectreddog.machinemod.network.MachineModMessageTEIntFieldToClient;
import com.projectreddog.machinemod.network.MachineModMessageTEInventoryChangedToClient;
import com.projectreddog.machinemod.network.MachineModMessageTETowerCranePosToClient;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.BlockBlueprintHelper;
import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockSpecial;
import net.minecraft.item.ItemRedstone;
import net.minecraft.item.ItemSign;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityTowerCrane extends TileEntity implements ITickable, ISidedInventory, IFuelContainer, ITEGuiButtonHandler {
	protected ItemStack[] inventory;
	private static int[] sideSlots = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
	public final int maxFuelStorage = 10000; // store up to 10k (can fill all 9 cans & have room for one more
	public int fuelStorage = 0;
	public final int inventorySize = 54;
	public final int coolDownReset = 1200;
	public int cooldown = coolDownReset;
	public IBlockState[][][] BlockBluePrintArray;

	private ItemStack clawHolding;

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
	public double targetGantryPos = 2;
	public double targetWenchPos = 0;

	public int currentX = 0;
	public int currentY = 0;
	public int currentZ = 0;
	public int dx;
	public int dy;
	public int dz;

	private double prevArmRotation;
	private double prevGantryPos;
	private double prevWencPos;

	private String fileName = "";
	private boolean running = false;
	private boolean lastRunningValue = false;
	private int xOffset = 17;
	private int zOffset = 17;

	private boolean loadedNullWorld = false;

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {

		if (this.world != null) {
			if (!this.world.isRemote) {
				// server send packet to clients (FRom server)
				ModNetwork.simpleNetworkWrapper.sendToAllAround(new MachineModMessageTEIntFieldToClient(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 1, running ? 1 : 0), new TargetPoint(this.world.provider.getDimension(), this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 48));

			}
		}
		lastRunningValue = this.running;
		this.running = running;
		this.markDirty();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		if (this.world != null) {
			if (!this.world.isRemote) {
				if (fileName == null) {
					fileName = "";
				}
				this.fileName = fileName;
				BlockBluePrintArray = BlockBlueprintHelper.getBlockStateArray(fileName);
				BlockPos bp = BlockBlueprintHelper.getBlueprintArea(fileName);
				if (bp != null) {
					dx = bp.getX();
					dy = bp.getY();
					dz = bp.getZ();
				}
				if (!loadedNullWorld) {
					setRunning(false);

					xOffset = dx + 1;
					zOffset = dz + 1;
					currentX = 0;
					currentY = 0;
					currentZ = 0;
				}
				SendBlockBluePrintArrayToClients();
				loadedNullWorld = false;
			}
		} else {
			this.fileName = fileName;
			loadedNullWorld = true;
		}
	}

	public void SendBlockBluePrintArrayToClients() {
		if (BlockBluePrintArray != null) {
			ModNetwork.simpleNetworkWrapper.sendToAllAround(new MachineModMessageEntityBluerprintBlockStateToClient(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), BlockBluePrintArray), new TargetPoint(this.world.provider.getDimension(), this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 48));
		}

	}

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
		if (loadedNullWorld) {
			setFileName(this.fileName);
		}
		// FAILSAFE CHECK.
		// IF NOT FILE NAME IS SET TURN OFF RUNNING !
		if ((fileName == null || fileName.equals("")) && isRunning() && !this.world.isRemote) {
			if (!loadedNullWorld) {
				setRunning(false);
				LogHelper.info("WARNING FOUND NO FILENAME WHIE RUNNING WAS TRUE!");
			}
		}

		if (!world.isRemote && isRunning()) { // only run on server
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

			double stepAmt = Reference.TowerCraneSpeedMultiplier;

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
				if (BlockBluePrintArray[currentX][currentY][currentZ] != null) {
					if (containsBlock(BlockBluePrintArray[currentX][currentY][currentZ]) || BlockBluePrintArray[currentX][currentY][currentZ].getBlock() == Blocks.AIR || state != 1) {
						int tmpX = getPlacingXWithOffset(currentX, currentZ);
						int tmpZ = getPlacingZWithOffset(currentX, currentZ);
						int tmpY = currentY;
						if ((state == 3 && this.world.getBlockState(this.pos.add(tmpX, tmpY, tmpZ)).getBlock().isAir(this.world.getBlockState(this.pos.add(tmpX, tmpY, tmpZ)), null, null)) || state != 3) {
							state = state + 1;
						}
						// set new targets for state

						setTargetsForState();
					}
				}
			}

			ModNetwork.sendPacketToAllAround(new MachineModMessageTETowerCranePosToClient(this.pos.getX(), this.pos.getY(), this.pos.getZ(), state, armRotation, gantryPos, wenchPos, targetArmRotation, targetGantryPos, targetWenchPos, currentX, currentY, currentZ), new TargetPoint(world.provider.getDimension(), this.pos.getX(), this.pos.getY(), this.pos.getZ(), 224)); // sendInterval = 0;
			this.markDirty();

		} else if (!world.isRemote && state == -1) {
			targetArmRotation = getPickupRotationLocation();
			armRotation = targetArmRotation;
			targetGantryPos = 2;
			wenchPos = targetWenchPos;

			targetWenchPos = 0;
			gantryPos = targetGantryPos;

			ModNetwork.sendPacketToAllAround(new MachineModMessageTETowerCranePosToClient(this.pos.getX(), this.pos.getY(), this.pos.getZ(), state, armRotation, gantryPos, wenchPos, targetArmRotation, targetGantryPos, targetWenchPos, currentX, currentY, currentZ), new TargetPoint(world.provider.getDimension(), this.pos.getX(), this.pos.getY(), this.pos.getZ(), 224)); // sendInterval = 0;
			this.markDirty();

		}

		if (this.world != null) {
			if (!this.world.isRemote) {
				if (lastRunningValue != this.running) {
					ModNetwork.simpleNetworkWrapper.sendToAllAround(new MachineModMessageTEIntFieldToClient(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 1, running ? 1 : 0), new TargetPoint(this.world.provider.getDimension(), this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 48));
					lastRunningValue = this.running;
				}

			}
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
			return x - xOffset;
		case SOUTH:
			return xOffset - x;// return this.boundingBox.minX + x;
		case WEST:
			return z - zOffset;
		case EAST:
			return zOffset - z;
		default:
			return x;
		}
	}

	public int getZWithOffset(int x, int z) {

		EnumFacing enumfacing = getFacing();
		switch (enumfacing) {
		case NORTH:
			return zOffset - z;
		case SOUTH:
			return z - zOffset;// return this.boundingBox.minX + x;
		case WEST:
			return x - xOffset;
		case EAST:
			return xOffset - x;
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
			return x - xOffset;
		case SOUTH:
			return xOffset - x;// return this.boundingBox.minX + x;
		case WEST:
			return z - zOffset;
		case EAST:
			return zOffset - z;
		default:
			return x;
		}
	}

	public int getPlacingZWithOffset(int x, int z) {

		EnumFacing enumfacing = getFacing();
		switch (enumfacing) {
		case NORTH:
			return z - zOffset;
		case SOUTH:
			return zOffset - z;// return this.boundingBox.minX + x;
		case WEST:
			return xOffset - x;
		case EAST:
			return x - xOffset;
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
			return 0;
		default:
			return 0d;
		}
	}

	public void setTargetsForState() {
		if (!loadedNullWorld) {
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

				if (DrainsBlock(BlockBluePrintArray[currentX][currentY][currentZ])) {

					setInventorySlotContents(-1, BlockBluePrintArray[currentX][currentY][currentZ].getBlock().getItem(null, null, BlockBluePrintArray[currentX][currentY][currentZ]));

				}

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

				/// TESTING CODE
				///
				if (BlockBluePrintArray != null) {
					// TODO add call to event to allow it to be canceled by things like FTB utilities.

					BlockBlueprintHelper.setBlockState(this.world, this.pos.add(placingposX, currentY, placingposZ), BlockBluePrintArray[currentX][currentY][currentZ], getFacing());
					setInventorySlotContents(-1, ItemStack.EMPTY);
				}
				// prevArmRotation=targetGantryPos;
				// prevGantryPos=targetGantryPos;
				// prevWencPos;

				currentX = currentX + 1;
				if (currentX > dx) {
					currentX = 0;
					currentZ = currentZ + 1;
				}
				if (currentZ > dz) {
					currentZ = 0;
					currentY = currentY + 1;
				}

				if (currentY > dy) {
					currentY = 0;
					currentX = 0;
					currentZ = 0;
					state = -1;

					setRunning(false);
				}

				if (BlockBluePrintArray != null) {
					while (BlockBluePrintArray[currentX][currentY][currentZ].getBlock() == Blocks.AIR) {

						currentX = currentX + 1;
						if (currentX > dx) {
							currentX = 0;
							currentZ = currentZ + 1;
						}
						if (currentZ > dz) {
							currentZ = 0;
							currentY = currentY + 1;
						}

						if (currentY > dy) {
							currentY = 0;
							currentX = 0;
							currentZ = 0;
							state = -1;
							setRunning(false);

						}
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

		setFileName(compound.getString(Reference.MACHINE_MOD_NBT_PREFIX + "filename"));

		currentX = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "currentX");
		currentY = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "currentY");
		currentZ = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "currentZ");
		setRunning(compound.getBoolean(Reference.MACHINE_MOD_NBT_PREFIX + "running"));
		fuelStorage = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "FUEL_STORAGE");
		cooldown = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "COOL_DOWN");

		state = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "STATE");
		armRotation = compound.getDouble(Reference.MACHINE_MOD_NBT_PREFIX + "armRotation");
		gantryPos = compound.getDouble(Reference.MACHINE_MOD_NBT_PREFIX + "gantryPos");
		wenchPos = compound.getDouble(Reference.MACHINE_MOD_NBT_PREFIX + "wenchPos");
		targetArmRotation = compound.getDouble(Reference.MACHINE_MOD_NBT_PREFIX + "targetArmRotation");
		targetGantryPos = compound.getDouble(Reference.MACHINE_MOD_NBT_PREFIX + "targetGantryPos");
		targetWenchPos = compound.getDouble(Reference.MACHINE_MOD_NBT_PREFIX + "targetWenchPos");
		xOffset = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "xOffset");

		zOffset = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "zOffset");
		// inventory
		NBTTagList tagList = compound.getTagList(Reference.MACHINE_MOD_NBT_PREFIX + "Inventory", compound.getId());
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inventory.length) {
				inventory[slot] = new ItemStack(tag);
			}
			if (slot == -1) {
				clawHolding = new ItemStack(tag);
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
		compound.setBoolean(Reference.MACHINE_MOD_NBT_PREFIX + "running", running);
		compound.setString(Reference.MACHINE_MOD_NBT_PREFIX + "filename", fileName);
		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "xOffset", xOffset);

		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "zOffset", zOffset);

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

		ItemStack stack = clawHolding;
		if (stack != null) {
			if (!stack.isEmpty()) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) -1);
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

	public boolean containsBlock(IBlockState blockState) {
		boolean result = false;
		for (int i = 0; i < inventory.length; i++) {
//loop thru all inventory
			if (inventory[i] != null) {
				if (inventory[i].getItem() instanceof ItemBlock) {
					ItemBlock ib = (ItemBlock) inventory[i].getItem();
					if (ib.getBlock() == blockState.getBlock()) {
						// same block check meta
						if (inventory[i].getItem().getMetadata(inventory[i]) == blockState.getBlock().getItem(null, null, blockState).getMetadata()) {
							result = true;
							return result;
						}

					}
				} else if (inventory[i].getItem() instanceof ItemBlockSpecial) {
					ItemBlockSpecial ib = (ItemBlockSpecial) inventory[i].getItem();
					if (ib.getBlock() == blockState.getBlock()) {
						// same block check meta
						if (inventory[i].getItem().getMetadata(inventory[i]) == blockState.getBlock().getItem(null, null, blockState).getMetadata()) {
							result = true;
							return result;
						}

					} else if (ib.getBlock() == Blocks.UNPOWERED_REPEATER && blockState.getBlock() == Blocks.POWERED_REPEATER) {

						if (inventory[i].getItem().getMetadata(inventory[i]) == blockState.getBlock().getItem(null, null, blockState).getMetadata()) {
							result = true;
							return result;
						}

					}
				} else if (inventory[i].getItem() instanceof ItemRedstone) {
					ItemRedstone ib = (ItemRedstone) inventory[i].getItem();
					if (Blocks.REDSTONE_WIRE == blockState.getBlock()) {
						result = true;
						return result;

					}
				} else if (inventory[i].getItem() instanceof ItemSign) {
					ItemSign ib = (ItemSign) inventory[i].getItem();
					if (Blocks.STANDING_SIGN == blockState.getBlock() || Blocks.WALL_SIGN == blockState.getBlock()) {
						// same block check meta
						if (inventory[i].getItem().getMetadata(inventory[i]) == blockState.getBlock().getItem(null, null, blockState).getMetadata()) {
							result = true;
							return result;
						}

					}
				}

			}
		}

		return result;
	}

	public boolean DrainsBlock(IBlockState blockState) {
		boolean result = false;
		for (int i = 0; i < inventory.length; i++) {
//loop thru all inventory
			if (inventory[i] != null) {
				if (inventory[i].getItem() instanceof ItemBlock) {
					ItemBlock ib = (ItemBlock) inventory[i].getItem();
					if (ib.getBlock() == blockState.getBlock()) {
						// same block check meta
						if (inventory[i].getItem().getMetadata(inventory[i]) == blockState.getBlock().getItem(null, null, blockState).getMetadata()) {
							decrStackSize(i, 1);
							result = true;
							return result;
						}

					}
				}
			}

		}
		return result;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		// NOTE This is not normal code for slot handeling due to this thing needing the claw inventory set blah

		if (slot == -1) {
			setClawHolding(stack);
			if (!this.world.isRemote) {
				ModNetwork.simpleNetworkWrapper.sendToAllAround(new MachineModMessageTEInventoryChangedToClient(this.pos.getX(), this.pos.getY(), this.pos.getZ(), -1, stack, 0), new TargetPoint(this.world.provider.getDimension(), this.pos.getX(), this.pos.getY(), this.pos.getZ(), 48));
			}

		} else {
			inventory[slot] = stack;
			if (!stack.isEmpty() && stack.getCount() > getInventoryStackLimit()) {
				stack.setCount(getInventoryStackLimit());
			}
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
		case 1:
			if (value == 0) {
				// Set running to false;
				setRunning(false);
			} else if (value == 1) {
				// set running to true;
				setRunning(true);
			}
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

	public ItemStack getClawHolding() {
		return clawHolding;
	}

	public void setClawHolding(ItemStack clawHolding) {
		this.clawHolding = clawHolding;
	}

	@Override
	public void HandleGuiButton(int buttonId, EntityPlayer player) {
		// TODO Auto-generated method stub

		if (buttonId == Reference.GUI_TOWER_CRANE_BUTTON_START) {
			// if (!isRunning()) {
			setRunning(true);
			// }
		}

	}
}
