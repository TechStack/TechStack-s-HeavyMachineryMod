package com.projectreddog.machinemod.tileentities;

import java.util.List;

import com.projectreddog.machinemod.entity.EntitySemiTractor;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.item.trailer.ItemSemiTrailerTanker;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

public class TileEntityFractionalDistillation extends TileEntity implements ITickableTileEntity, IFluidTank, ISidedInventory {

	public final int maxOilStorage = 1000; // store up to 100k
	public final int inventorySize = 6;
	protected ItemStack[] inventory;
	private static int[] sideSlots = new int[] { 0 };

	public AxisAlignedBB boundingBox;
	public int coolDownAmount = 5;
	public int timeTillCoolDown = 0;

	public final int BlastedStoneOreMultiplier = 3;
	public final int VanillaOreMultiplier = 2;
	public final int BlastedStoneCoalMultiplier = 3;
	public final int BlastedStoneGemMultiplier = 2;
	public final int BlastedStoneLapisMultiplier = 12;
	public final int BlastedStoneRedstoneMultiplier = 8;
	protected FluidStack fluid;// = new FluidStack(ModBlocks.fluidOil, 0);
	public int transferOilAmount = 10;
	public boolean firstTick = true;

	public int remainBurnTime = 0;
	public int fluidLevelAbove[];

	public TileEntityFractionalDistillation() {
		inventory = new ItemStack[inventorySize];
		for (int i = 0; i < inventorySize; i++) {
			inventory[i] = ItemStack.EMPTY;
		}
		fluidLevelAbove = new int[5];
	}

	public int getStackOrder() {
		if (this.pos == null || this.world == null || this.world.getBlockState(this.pos.down()).getBlock() == null) {
			return 1;
		}
		if (this.world.getBlockState(this.pos.down()).getBlock() == ModBlocks.machinefractionaldistillation) {
			if (this.world.getTileEntity(this.pos.down()) instanceof TileEntityFractionalDistillation) {
				TileEntityFractionalDistillation te = (TileEntityFractionalDistillation) this.world.getTileEntity(this.pos.down());
				return te.getStackOrder() + 1;
			}
		}
		return 1;
	}

	public boolean hasSlot(int slotIndex) {
		int offset = slotIndex - 1;
		if (this.world.getBlockState(this.pos.up(offset)).getBlock() == ModBlocks.machinefractionaldistillation) {
			return true;
		}
		return false;

	}

	@Override
	public void tick() {
		if (!world.isRemote) {
			if (firstTick) {
				LogHelper.info("Stack order:" + getStackOrder());
				firstTick = !firstTick;
			}
			if (amIBottom()) {

				if (timeTillCoolDown > 0) {
					timeTillCoolDown--;
					return;
				}
				timeTillCoolDown = coolDownAmount;

				// LogHelper.info("TE update entity called");
				// boundingBox = new AxisAlignedBB(this.pos.north(3).west(3).down(1), this.pos.south(3).east(3).up(1));
				// List list = worldObj.getEntitiesWithinAABB(EntitySemiTractor.class, boundingBox);
				// processEntitiesInList(list);
				tryDistill();
			}
		} else {
			// is not do nothing !
		}

	}

	public void tryDistill() {
		if (this.amIBottom()) {
			if (this.getFluidAmount() > 0 && this.getFluid().isFluidEqual(new FluidStack(ModBlocks.fluidOil, 0))) {
				// WE have oil.
				if (remainBurnTime > 0) {
					// we have fuel

					distill();
				} else {

					// consume more fuel
					// only if it has mash to process
					// use the furnace's default burn times
					remainBurnTime = TileEntityFurnace.getItemBurnTime(this.getStackInSlot(0));
					if (remainBurnTime > 0) {
						// found fuel reduce item stack (AKA consume /brun the item)
						decrStackSize(0, 1);
					}

				}
			}
		}
	}

	public void distill() {
		// TODO: need to add logic for distilling where the bottom TE's fluid level is decreased and the TE's above are filled with the respective
		// fluid for the Height they are at in the stack.

		// variable that tells us if it can distill. based on TE's above fluids matching what they should or
		// and not being too full.
		boolean canDistill = true;
		for (int i = 1; i <= 4; i++) {

			if (world.getTileEntity(this.pos.up(i)) instanceof TileEntityFractionalDistillation) {
				// there is a TE of this type i blocks above
				TileEntityFractionalDistillation tefd = (TileEntityFractionalDistillation) world.getTileEntity(this.pos.up(i));
				if (tefd.getFluid() == null || tefd.getFluid().isFluidEqual(new FluidStack(getfluidForHeight(i + 1), 0))) {
					// its the fluid it should be or it is a null fluid.
					if (getfluidForHeight(i + 1) != null) {
						if (tefd.fill(new FluidStack(getfluidForHeight(i + 1), 1), false) > 0) {

						} else {
							canDistill = false;
						}
					}
				} else {
					canDistill = false;
				}

			}

		}
		if (canDistill) {

			// do same loop as above but this time increase fluid amts by +1

			for (int i = 1; i <= 4; i++) {

				if (world.getTileEntity(this.pos.up(i)) instanceof TileEntityFractionalDistillation) {
					// there is a TE of this type i blocks above
					TileEntityFractionalDistillation tefd = (TileEntityFractionalDistillation) world.getTileEntity(this.pos.up(i));

					tefd.fill(new FluidStack(getfluidForHeight(i + 1), 1), true);
				}

			}

			this.drain(1, true);
			remainBurnTime--;

		}
	}

	public Fluid getfluidForHeight(int height) {
		if (height == 1) {
			return ModBlocks.fluidOil;
		} else if (height == 2) {
			return ModBlocks.fluidBitumen;
		} else if (height == 3) {
			return ModBlocks.fluidDiesel;
		} else if (height == 4) {
			return ModBlocks.fluidJetFuel;
		} else if (height == 5) {
			return ModBlocks.fluidNaphtha;
		} else {
			return null;
		}
	}

	public boolean amIBottom() {
		if (this.world.getBlockState(pos.down()).getBlock() == ModBlocks.machinefractionaldistillation) {
			return false;
		}
		return true;
	}

	private void processEntitiesInList(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity != null) {
				if (entity instanceof EntitySemiTractor) {
					EntitySemiTractor est = (EntitySemiTractor) entity;

					if (!est.inventory.getStackInSlot(0).isEmpty()) {
						if (est.inventory.getStackInSlot(0).getItem() instanceof ItemSemiTrailerTanker) {

							if (!est.isDead) {

								if (est.getFluidAmount() >= transferOilAmount) {
									if (est.getFluid() != null) {
										if (fluid != null) {
											if (est.getFluid().getFluid() == fluid.getFluid()) {
												FluidStack moveStack = new FluidStack(fluid, transferOilAmount);

												fill(est.drain(transferOilAmount, true), true);
											}
										} else {
											// no fluid in this block so we can pull the fluid from the tanker
											if (est.getFluid().getFluid() == ModBlocks.fluidOil) {
												FluidStack moveStack = new FluidStack(ModBlocks.fluidOil, transferOilAmount);

												fill(est.drain(transferOilAmount, true), true);
											}
										}
									}
								}
							}
						}
					}
				}
				// entity.setPosition(entity.getPosition().getX() + 0.1d, entity.getPosition().getY(), entity.getPosition().getZ());
			}
		}
	}

	@Override
	public void read(CompoundNBT compound) {

		super.read(compound);

		timeTillCoolDown = compound.getInt(Reference.MACHINE_MOD_NBT_PREFIX + "COOLDOWN");

		if (!compound.contains("Empty")) {
			FluidStack fluid = FluidStack.loadFluidStackFromNBT(compound);
			setFluid(fluid);
		} else {
			setFluid(null);
		}
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);

		compound.putInt(Reference.MACHINE_MOD_NBT_PREFIX + "COOLDOWN", timeTillCoolDown);
		if (fluid != null) {
			fluid.writeToNBT(compound);
		} else {
			compound.putString("Empty", "");
		}
		return compound;
	}

	@Override
	public FluidStack getFluid() {
		return fluid;
	}

	@Override
	public int getFluidAmount() {

		if (fluid == null) {
			return 0;
		}
		return fluid.amount;
	}

	@Override
	public int getCapacity() {

		return this.maxOilStorage;
	}

	@Override
	public FluidTankInfo getInfo() {
		return new FluidTankInfo(this);
	}

	public void setFluid(FluidStack fluid) {
		this.fluid = fluid;
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (resource == null) {
			return 0;
		}
		if (!(resource.isFluidEqual(new FluidStack(getfluidForHeight(getStackOrder()), 1)))) {
			return 0;
		}
		if (!doFill) {
			if (fluid == null) {
				return Math.min(maxOilStorage, resource.amount);
			}

			if (!fluid.isFluidEqual(resource)) {
				return 0;
			}

			return Math.min(maxOilStorage - fluid.amount, resource.amount);
		}

		if (fluid == null) {
			fluid = new FluidStack(resource, Math.min(maxOilStorage, resource.amount));

			if (this != null) {
				FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(fluid, this.getWorld(), this.getPos(), this, fluid.amount));
			}
			return fluid.amount;
		}

		if (!fluid.isFluidEqual(resource)) {
			return 0;
		}
		int filled = maxOilStorage - fluid.amount;

		if (resource.amount < filled) {
			fluid.amount += resource.amount;
			filled = resource.amount;
		} else {
			fluid.amount = maxOilStorage;
		}

		if (this != null) {
			FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(fluid, this.getWorld(), this.getPos(), this, filled));
		}
		return filled;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if (fluid == null) {
			return null;
		}

		int drained = maxDrain;
		if (fluid.amount < drained) {
			drained = fluid.amount;
		}

		FluidStack stack = new FluidStack(fluid, drained);
		if (doDrain) {
			fluid.amount -= drained;
			if (fluid.amount <= 0) {
				fluid = null;
			}

			if (this != null) {
				FluidEvent.fireEvent(new FluidEvent.FluidDrainingEvent(fluid, this.getWorld(), this.getPos(), this, drained));
			}
		}
		return stack;
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

	public int getField(int id) {
		switch (id) {
		case 0:
			return this.remainBurnTime;
		case 1:
			return this.getFluidAmount();

		case 2:

		case 3:
		case 4:
		case 5:
			TileEntity te = this.world.getTileEntity(this.pos.up(id - 1));
			if (te instanceof TileEntityFractionalDistillation) {
				return ((TileEntityFractionalDistillation) te).getFluidAmount();
			}

		default:
			break;
		}
		return 0;

	}

	public void setField(int id, int value) {
		switch (id) {
		case 0:
			this.remainBurnTime = value;
			break;
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
			fluidLevelAbove[id - 1] = value;
			break;
		default:
			break;
		}

	}

	public int getFieldCount() {
		return 6;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer playerIn) {
		return playerIn.getDistanceSq(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ()) < 64;
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
