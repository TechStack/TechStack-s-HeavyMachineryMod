package com.projectreddog.machinemod.entity;

import java.util.List;

import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.item.machines.ItemTransportable;
import com.projectreddog.machinemod.item.trailer.ItemSemiTrailerFlatBed;
import com.projectreddog.machinemod.item.trailer.ItemSemiTrailerLivestock;
import com.projectreddog.machinemod.item.trailer.ItemSemiTrailerTanker;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.items.ItemStackHandler;

public class EntitySemiTractor extends EntityMachineModRideable implements IFluidTank {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
	private int carriedMachinesFuelStorage;
	private float bedRampBackOffset = -18f;

	public final int maxOilStorage = 100000; // store up to 100k
	protected FluidStack fluid = new FluidStack(net.minecraftforge.fluids.FluidRegistry.WATER, 0);

	public EntityLiving CarriedEntities[];

	public EntitySemiTractor(World world) {
		super(world);
		CarriedEntities = new EntityLiving[10];
		setSize(3, 4);
		SIZE = 9;
		inventory = new ItemStackHandler(SIZE);
		// inventory = new ItemStack[9];
		this.mountedOffsetY = 0.65D;
		this.mountedOffsetX = 1.0D;
		this.mountedOffsetZ = 1.0D;
		this.maxAngle = 10;
		this.minAngle = 0;
		this.ignoreFrustumCheck = true;

		this.droppedItem = ModItems.semitractor;
		this.shouldSendClientInvetoryUpdates = true;
		this.maxSpeed = 0.8d;
		this.accelerationAmount = .08d;
		this.turnRate = 3d;
	}

	protected void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		carriedMachinesFuelStorage = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "CMFULE");

	}

	protected void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "CMFULE", carriedMachinesFuelStorage);

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!world.isRemote) {

			AxisAlignedBB bucketboundingBox = new AxisAlignedBB(calcTwoOffsetX(bedRampBackOffset, 90, -1) + posX - .5d, posY, calcTwoOffsetZ(bedRampBackOffset, 90, -1) + posZ - .5d, calcTwoOffsetX(bedRampBackOffset, 90, 1) + posX + .5d, posY + 1, calcTwoOffsetZ(bedRampBackOffset, 90, 1) + posZ + .5d);

			List list = this.world.getEntitiesWithinAABBExcludingEntity(this, bucketboundingBox);
			collidedEntitiesInList(list);

			if (!this.inventory.getStackInSlot(0).isEmpty()) {
				if (this.inventory.getStackInSlot(0).getItem() instanceof ItemSemiTrailerFlatBed) {

					if (this.Attribute1 > 9) {

						if (!this.inventory.getStackInSlot(1).isEmpty() && this.inventory.getStackInSlot(1).getItem() instanceof ItemTransportable) {

							EntityMachineModRideable eMMR = ((ItemTransportable) this.inventory.getStackInSlot(1).getItem()).getEntityToSpawn(world);

							eMMR.setPosition(calcTwoOffsetX(bedRampBackOffset + -3, 90, -1) + posX - .5d, posY, calcTwoOffsetZ(bedRampBackOffset + -3, 90, -1) + posZ - .5d);
							eMMR.prevPosX = calcTwoOffsetX(bedRampBackOffset + -1, 90, -1) + posX - .5d;
							eMMR.prevPosY = posY + 1.0d;
							eMMR.prevPosZ = calcTwoOffsetZ(bedRampBackOffset + -1, 90, -1) + posZ - .5d;
							eMMR.currentFuelLevel = carriedMachinesFuelStorage;
							world.spawnEntity(eMMR);
							carriedMachinesFuelStorage = 0;
							this.inventory.extractItem(1, 1, false);
						}
					}
				} else if (this.inventory.getStackInSlot(0).getItem() instanceof ItemSemiTrailerLivestock) {
					if (this.Attribute1 > 9) {

						for (int j = CarriedEntities.length - 1; j >= 0; j--) {
							if (CarriedEntities[j] != null) {

								// TODO set other attributes of the entity !!
								// EntityCow temp = (EntityCow) CarriedEntities[j];
								EntityCow temp = new EntityCow(world);
								temp.setHealth(CarriedEntities[j].getHealth());
								temp.setPosition(calcTwoOffsetX(bedRampBackOffset + -3, 90, -1) + posX - .5d, posY, calcTwoOffsetZ(bedRampBackOffset + -3, 90, -1) + posZ - .5d);
								world.spawnEntity(temp);
								CarriedEntities[j] = null;
								return;
							}
						}
					}
				}

			}
			if (fluid != null && fluid.amount > 0) {

				// LogHelper.info(fluid.amount);
			}
		}

	}

	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

	private void collidedEntitiesInList(List par1List) {
		if (!inventory.getStackInSlot(0).isEmpty()) {
			if (inventory.getStackInSlot(0).getItem() instanceof ItemSemiTrailerFlatBed) {
				if (inventory.getStackInSlot(1).isEmpty()) {
					for (int i = 0; i < par1List.size(); ++i) {
						Entity entity = (Entity) par1List.get(i);
						if (entity != null) {
							if (entity instanceof EntityMachineModRideable && ((EntityMachineModRideable) entity).droppedItem instanceof ItemTransportable) {
								ItemStack is = new ItemStack(((EntityMachineModRideable) entity).getItemToBeDropped());
								if (!entity.isDead) {
									if (is.getCount() > 0) {
										ItemStack is1 = addToinventory(is);
										carriedMachinesFuelStorage = ((EntityMachineModRideable) entity).currentFuelLevel;
										entity.setDead();
										return; // only add 1 item
										// TODO way to store the contents of the machine's FUel level

									}
								}
							}
						}
					}
				}
			} else if (inventory.getStackInSlot(0).getItem() instanceof ItemSemiTrailerLivestock) {
				for (int i = 0; i < par1List.size(); ++i) {
					Entity entity = (Entity) par1List.get(i);
					if (entity != null) {
						if (entity instanceof EntityCow) {
							for (int j = 0; j < CarriedEntities.length; j++) {
								if (CarriedEntities[j] == null) {
									CarriedEntities[j] = (EntityCow) entity;
									CarriedEntities[j].setHealth(((EntityCow) entity).getHealth());
									entity.setDead();
									return;
								}
							}
						}
					}
				}
			}
		}
	}

	public boolean isFluidTanker() {

		if (inventory.getStackInSlot(0) != null) {
			if (inventory.getStackInSlot(0).getItem() instanceof ItemSemiTrailerTanker) {
				return true;
			}
		}

		return false;
	}

	/////

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

		if (!isFluidTanker()) {
			return 0;

		}
		if (resource == null) {
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
				FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(fluid, world, this.getPosition(), this, fluid.amount));
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
			FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(fluid, world, this.getPosition(), this, filled));
		}
		return filled;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {

		if (!isFluidTanker()) {
			return null;

		}

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
				FluidEvent.fireEvent(new FluidEvent.FluidDrainingEvent(fluid, world, this.getPosition(), this, drained));
			}
		}
		return stack;
	}
}
