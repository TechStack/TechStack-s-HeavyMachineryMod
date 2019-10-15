package com.projectreddog.machinemod.tileentities;

import java.util.List;

import com.projectreddog.machinemod.iface.ITEGuiButtonHandler;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class TileEntityExpCollector extends TileEntity implements ITickable, IInventory, ITEGuiButtonHandler {

	public AxisAlignedBB boundingBox;
	public AxisAlignedBB boundingBox2;

	public final double MoveSpeed = .1d;
	public int expAmount = 0;

	public TileEntityExpCollector() {

	}

	public boolean getPowered() {
		return world.isBlockPowered(this.pos);
	}

	public boolean isCenterBlock() {
		boolean ret = false;
		if (this.world.getBlockState(this.pos.up()).getBlock() == ModBlocks.machineexpcollector) {
			if (this.world.getBlockState(this.pos.down()).getBlock() == ModBlocks.machineexpcollector) {
				ret = true;
			}
		}
		return ret;
	}

	public boolean isBottomBlock() {
		boolean ret = false;

		if (this.world.getBlockState(this.pos.up().up()).getBlock() == ModBlocks.machineexpcollector) {
			if (this.world.getBlockState(this.pos.up()).getBlock() == ModBlocks.machineexpcollector) {
				ret = true;
			}
		}
		return ret;
	}

	public boolean isTopBlock() {
		boolean ret = false;
		if (this.world.getBlockState(this.pos.down().down()).getBlock() == ModBlocks.machineexpcollector) {
			if (this.world.getBlockState(this.pos.down()).getBlock() == ModBlocks.machineexpcollector) {
				ret = true;
			}
		}
		return ret;
	}

	@Override
	public void update() {
		if (this.world != null) {
			if (isCenterBlock()) {
				if (world.getBlockState(pos).getBlock() == ModBlocks.machineexpcollector) {
					BlockPos bp = this.pos;
					boundingBox = new AxisAlignedBB(bp).grow(2, 1, 2);
					bp = this.pos;

					boundingBox2 = new AxisAlignedBB(bp.up().up()).grow(1, 1, 1);
				}
				List list = world.getEntitiesWithinAABB(EntityXPOrb.class, boundingBox);
				processEntitiesInList(list);

				list = world.getEntitiesWithinAABB(EntityXPOrb.class, boundingBox2);
				processEntitiesInListTop(list);
			}
		}

	}

	public double getMoveSpeed() {
		return this.MoveSpeed;
	}

	private void processEntitiesInListTop(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity != null) {
				double x = entity.posX;
				double y = entity.posY;
				double z = entity.posZ;
				double tx = this.getPos().getX();
				double ty = this.getPos().getY() + .5d;
				double tz = this.getPos().getZ() + 1.5d;

				double dx = tx - x;
				double dy = ty - y;
				double dz = tz - z;
				if (dx > 0) {
					dx = MathHelper.clamp(getMoveSpeed(), getMoveSpeed(), dx);
				} else if (dx < 0) {
					dx = MathHelper.clamp(-getMoveSpeed(), -getMoveSpeed(), dx);
				}

				if (dy > 0) {
					dy = MathHelper.clamp(getMoveSpeed(), getMoveSpeed(), dy);
				} else if (dy < 0) {
					dy = MathHelper.clamp(-getMoveSpeed(), -getMoveSpeed(), dy);
				}

				if (dz > 0) {
					dz = MathHelper.clamp(getMoveSpeed(), getMoveSpeed(), dz);
				} else if (dz < 0) {
					dz = MathHelper.clamp(-getMoveSpeed(), -getMoveSpeed(), dz);
				}

				entity.fallDistance = 0; // set so no damage to players going up long virt shafts
				entity.motionY = dy;
				entity.motionX = dx;
				entity.motionZ = dz;
				entity.lastTickPosX = entity.posX;
				entity.lastTickPosY = entity.posY;
				entity.lastTickPosZ = entity.posZ;
				entity.move(MoverType.SELF, dx, dy, dz);

			}
		}
	}

	private void processEntitiesInList(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity != null) {
				double x = entity.posX;
				double y = entity.posY;
				double z = entity.posZ;
				double tx = this.getPos().getX() + .5d;
				double ty = this.getPos().getY() + .5d;
				double tz = this.getPos().getZ() + .5d;

				double dx = tx - x;
				double dy = ty - y;
				double dz = tz - z;
				double distance = MathHelper.sqrt((dx * dx) + (dy * dy) + (dz * dz));
				if (!this.world.isRemote) {
					if (distance > 0 & distance < 1d || distance < 0 && distance > -1d) {

						if (entity instanceof EntityXPOrb) {
							EntityXPOrb expo = (EntityXPOrb) entity;
							this.expAmount = this.expAmount + expo.getXpValue();
						}

						entity.setDead();
					}
				}
				if (dx > 0) {
					dx = MathHelper.clamp(getMoveSpeed(), getMoveSpeed(), dx);
				} else if (dx < 0) {
					dx = MathHelper.clamp(-getMoveSpeed(), -getMoveSpeed(), dx);
				}

				if (dy > 0) {
					dy = MathHelper.clamp(getMoveSpeed(), getMoveSpeed(), dy);
				} else if (dy < 0) {
					dy = MathHelper.clamp(-getMoveSpeed(), -getMoveSpeed(), dy);
				}

				if (dz > 0) {
					dz = MathHelper.clamp(getMoveSpeed(), getMoveSpeed(), dz);
				} else if (dz < 0) {
					dz = MathHelper.clamp(-getMoveSpeed(), -getMoveSpeed(), dz);
				}

				entity.fallDistance = 0; // set so no damage to players going up long virt shafts
				entity.motionY = dy;
				entity.motionX = dx;
				entity.motionZ = dz;
				entity.lastTickPosX = entity.posX;
				entity.lastTickPosY = entity.posY;
				entity.lastTickPosZ = entity.posZ;
				entity.move(MoverType.SELF, dx, dy, dz);

			}
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
		return player.getDistanceSq(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ()) < 64;
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
			return expAmount;
		}
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		if (id == 0) {
			expAmount = value;
		}
	}

	@Override
	public int getFieldCount() {
		return 1;
	}

	@Override
	public void clear() {

	}

	@Override
	public void HandleGuiButton(int buttonId, EntityPlayer player) {
		// TODO TAKE action when player clicks the button

		int requestedAmt = 0;
		if (buttonId == 1) {
			requestedAmt = 1;
		} else if (buttonId == 2) {
			requestedAmt = 10;
		} else if (buttonId == 3) {
			requestedAmt = 100;
		} else if (buttonId == 4) {
			requestedAmt = 1000;
		} else if (buttonId == 5) {
			requestedAmt = 10000;

		} else if (buttonId == 6) {
			// requestedAmt = 10000;

			requestedAmt = 1;
			int savedLevel = player.experienceLevel;

			while (savedLevel + 1 > player.experienceLevel && expAmount > 0)

			{
				player.addExperience(requestedAmt);
				expAmount = expAmount - requestedAmt;
			}
			requestedAmt = 0;
			return;
		}

		if (requestedAmt <= expAmount) {

			player.addExperience(requestedAmt);
			expAmount = expAmount - requestedAmt;
		}

	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {

		super.readFromNBT(compound);

		expAmount = compound.getInteger(Reference.MACHINE_MOD_NBT_PREFIX + "EXPAMOUNT");

	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		compound.setInteger(Reference.MACHINE_MOD_NBT_PREFIX + "EXPAMOUNT", expAmount);

		return compound;

	}

}
