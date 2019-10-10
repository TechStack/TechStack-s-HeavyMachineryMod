package com.projectreddog.machinemod.tileentities;

import com.projectreddog.machinemod.block.BlockMachineModHoloScanner;
import com.projectreddog.machinemod.iface.ITEGuiButtonHandler;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityHoloScanner extends TileEntity implements ITickable, IInventory, ITEGuiButtonHandler {

	public AxisAlignedBB boundingBox;

	public int front = 1;
	public int right = 1;
	public int up = 1;

	private int lastFront = -1;
	private int lastRight = -1;
	private int lastUp = -1;

	public int rotAmt;
	public float bounceAmt = .5f;

	public float bounceDir = 1;

	public BlockPos point1;
	public BlockPos point2;
	public AxisAlignedBB areaBB;

	public TileEntityHoloScanner() {

	}

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.util.math.AxisAlignedBB getRenderBoundingBox() {

		if (areaBB != null) {
			return areaBB.grow(1).offset(this.getPos());

		} else {
			return world.getBlockState(getPos()).getCollisionBoundingBox(world, pos).offset(pos);
		}
	}

	public boolean getPowered() {
		return world.isBlockPowered(this.pos);
	}

	@Override
	public void update() {
		if (world.isRemote) {
			rotAmt = rotAmt + 1;
			if (rotAmt > 360) {
				rotAmt = 0;
			}
			bounceAmt = bounceAmt + (.005f * bounceDir);
			if (bounceAmt > 1 || bounceAmt < .5) {
				bounceDir = bounceDir * -1;
			}

		}
		if (lastFront != front || lastRight != right || lastUp != up) {

			EnumFacing ef;
			if (this.world.getBlockState(this.getPos()).getBlock() == ModBlocks.machineholoscanner) {
				ef = (EnumFacing) this.getWorld().getBlockState(this.getPos()).getValue(BlockMachineModHoloScanner.FACING);
			} else {
				ef = EnumFacing.NORTH;
			}
			switch (ef) {
			case NORTH:
				point1 = this.getPos().north().east();
				areaBB = new AxisAlignedBB(0.5, 0, -0.5, right + .5, up, -front - .5);
				break;
			case SOUTH:
				point1 = this.getPos().south().west();
				areaBB = new AxisAlignedBB(-0.5, 0, 0.5, -right - .5, up, front + .5);

				break;
			case EAST:
				point1 = this.getPos().east().south();
				areaBB = new AxisAlignedBB(0.5, 0, 0.5, front + .5, up, right + .5);

				break;
			case WEST:
				point1 = this.getPos().west().north();

				areaBB = new AxisAlignedBB(-0.5, 0, -0.5, -front - .5, up, -right - .5);

				break;
			default:
				// should never happen because we are constrained to the horizontal plane so just break with no addtional rotation applied
				break;
			}

			lastFront = front;
			lastRight = right;
			lastUp = up;

			point2 = point1.up(up - 1).offset(ef, front - 1).offset(ef.rotateAround(EnumFacing.Axis.Y), right - 1);

		}

	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		front = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "FRONT");

		right = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "RIGHT");
		up = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "UP");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "FRONT", front);

		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "RIGHT", right);
		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "UP", up);

		return compound;

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
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return null;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {

	}

	@Override
	public int getInventoryStackLimit() {
		return 0;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return false;
	}

	@Override
	public int getField(int id) {

		if (id == 0) {
			return front;
		} else if (id == 1) {
			return right;
		} else if (id == 2) {
			return up;
		}

		return 0;
	}

	@Override
	public void setField(int id, int value) {
		if (id == 0) {
			front = value;
		} else if (id == 1) {
			right = value;
		} else if (id == 2) {
			up = value;
		}
	}

	@Override
	public int getFieldCount() {
		return 3;
	}

	@Override
	public void clear() {

	}

	@Override
	public void HandleGuiButton(int buttonId, EntityPlayer player) {
		switch (buttonId) {
		case Reference.GUI_HOLO_SCANNER_BUTTON_FRONT_PLUS:
			front++;
			break;
		case Reference.GUI_HOLO_SCANNER_BUTTON_FRONT_MINUS:
			front--;
			break;
		case Reference.GUI_HOLO_SCANNER_BUTTON_RIGHT_PLUS:
			right++;
			break;
		case Reference.GUI_HOLO_SCANNER_BUTTON_RIGHT_MINUS:
			right--;
			break;
		case Reference.GUI_HOLO_SCANNER_BUTTON_UP_PLUS:
			up++;
			break;
		case Reference.GUI_HOLO_SCANNER_BUTTON_UP_MINUS:
			up--;
			break;
		}
		front = MathHelper.clamp(front, 1, 32);
		right = MathHelper.clamp(right, 1, 32);
		up = MathHelper.clamp(up, 1, 32);
		markDirty();

	}

	public EnumFacing getFacing() {

		EnumFacing ef;
		if (this.getWorld().getBlockState(this.getPos()).getBlock() == ModBlocks.machineholoscanner) {
			ef = (EnumFacing) this.getWorld().getBlockState(this.getPos()).getValue(BlockMachineModHoloScanner.FACING);
		} else {
			ef = EnumFacing.NORTH;
		}

		return ef;
	}
}
