package com.projectreddog.machinemod.entity;

import java.util.List;

import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.init.ModNetwork;
import com.projectreddog.machinemod.network.MachineModMessageEntityCurrentTargetPosToClient;
import com.projectreddog.machinemod.utility.BlockUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.items.ItemStackHandler;

public class EntityExcavator extends EntityMachineModRideable {

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
	public static float AmrLength = 3.1f;
	public static float armPiviotForward = 1.4f;
	public static float armPiviotUp = -0f;

	public BlockPos targetBlockPos;
	public double currPosX;
	public double currPosY;
	public double currPosZ;
	public double mainBodyRotation = 0;

	public double angleArm1 = 0;
	public double angleArm2 = 0;

	public double angleArm3 = 0;

	public double armSpeed = .1d;

	public EntityExcavator(World world) {
		super(world);

		setSize(4f, 4f);
		SIZE = 9;
		inventory = new ItemStackHandler(SIZE);
		// inventory = new ItemStack[9];

		this.mountedOffsetY = .70D;
		this.mountedOffsetX = -1.2D;
		this.mountedOffsetZ = 1.1D;
		this.maxAngle = 256;
		this.minAngle = 0;
		this.droppedItem = ModItems.excavator;
		currPosX = this.posX;
		currPosY = this.posY;
		currPosZ = this.posZ;
		this.shouldSendClientInvetoryUpdates = true;
		this.ignoreFrustumCheck = true;

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!world.isRemote) {
			// if (this.Attribute1 == this.getMaxAngle()) {
			// bucket Down
			// break blocks first
			if (currentFuelLevel > 0) {
				// server move bucket towards target and send it's new pos to the client via network
				if (this.isPlayerPushingSegment1Down) {
					this.angleArm1--;
				}

				if (this.isPlayerPushingSegment1Up) {
					this.angleArm1++;
				}

				if (this.angleArm1 < -10) {
					angleArm1 = -10;
				}
				if (this.angleArm1 > 25) {
					angleArm1 = 25;
				}

				if (this.isPlayerPushingSegment2Down) {
					this.angleArm2--;
				}
				if (this.isPlayerPushingSegment2Up) {
					this.angleArm2++;
				}

				if (this.angleArm2 < 00) {
					angleArm2 = 0;
				}
				if (this.angleArm2 > 90) {
					angleArm2 = 90;
				}
				if (this.isPlayerPushingSegment3Down) {
					this.angleArm3--;
				}
				if (this.isPlayerPushingSegment3Up) {
					this.angleArm3++;
				}

				if (this.angleArm3 < -90) {
					angleArm3 = -90;
				}
				if (this.angleArm3 > 45) {
					angleArm3 = 45;
				}

				if (this.isPlayerPushingTurretLeft) {
					mainBodyRotation++;
				}
				if (this.isPlayerPushingTurretRight) {
					mainBodyRotation--;
				}

				// BlockPos bp = new BlockPos(posX + calcTwoOffsetX(10, 0, 0), posY + +3, posZ + calcTwoOffsetZ(10, 0, 0));

				ModNetwork.sendPacketToAllAround((new MachineModMessageEntityCurrentTargetPosToClient(this.getEntityId(), this.currPosX, this.currPosY, this.currPosZ, this.angleArm1, this.angleArm2, this.angleArm3, this.mainBodyRotation)), new TargetPoint(world.provider.getDimension(), posX, posY, posZ, 80));
				// if (this.isPlayerPushingSprintButton) {
				// player wants to break the block
				// bp = new BlockPos(currPosX, currPosY, currPosZ);
				// public BlockPos calculateBlockPosGivenStartAngleDistance4(double startX, double startY, double startZ, float angleX1, float angleY1, float angleZ1, double distance1, float angleX2, float angleY2, float angleZ2, double distance2, float angleX3, float angleY3, float angleZ3, double distance3, float angleX4, float angleY4, float angleZ4, double distance4) {
				// LogHelper.info(this.yaw);
				// LogHelper.info(this.rotationYaw);
				// TS old model BlockPos BP = this.calculateBlockPosGivenStartAngleDistance4(this.posX, this.posY, this.posZ, (360 - this.yaw) + 90, 0, -.5d, (360 - this.yaw), (float) (45f + this.angleArm1), 9.5d, (360 - this.yaw), (float) (this.angleArm2 + 270 + this.angleArm1), 7.5d, (360 - this.yaw), (float) (this.angleArm3 + 90), -2.75d);
				// public BlockPos calculateBlockPosGivenStartAngleDistance4(double startX, double startY, double startZ, float yaw1, float pitch1, double distance1, float yaw2, float pitch2, double distance2, float yaw3, float pitch3, double distance3, float yaw4, float pitch4, double distance4) {
				// PRE UPDATE NEW MODEL BlockPos BP = this.calculateBlockPosGivenStartAngleDistance4(this.posX, this.posY, this.posZ, (360 - this.yaw) + 90, 0, -.5d, (360 - this.yaw), (float) (45f + this.angleArm1), 11.5d, (360 - this.yaw), (float) (this.angleArm2 + 270 + this.angleArm1), 6.5d, (360 - this.yaw), (float) (this.angleArm3 + 90), -2.75d);

				BlockPos BP = this.calculateBlockPosGivenStartAngleDistance4(this.posX, this.posY, this.posZ, (360 - this.yaw) + 90, 0, -0.5d, (360 - this.yaw), (float) (55f + this.angleArm1), 12.5d, (360 - this.yaw), (float) (this.angleArm2 + 270 + this.angleArm1), 8.5d, (360 - this.yaw), (float) (this.angleArm3) + 90, +2.75d);
				// BlockPos BP = this.calculateBlockPosGivenStartAngleDistance4(this.posX, this.posY, this.posZ, (360 - this.yaw) + 90, 0, -.5d, (360 - this.yaw), (float) (45f + this.angleArm1), 9.5d, (360 - this.yaw), (float) (this.angleArm2 + 270 + this.angleArm1), 7.5d, 0,0,0);

				// BlockPos BP = this.calculateBlockPosGivenStartAngleDistance4(this.posX, this.posY, this.posZ, (360 - this.yaw) + 90, 0, -.5d, (360 - this.yaw), (float) (45f + this.angleArm1), 9.5d, (360 - this.yaw), (float) (this.angleArm2 + 265), 7.5d, 0, 0, 0);

				// BlockPos BP = this.calculateBlockPosGivenStartAngleDistance4(this.posX, this.posY, this.posZ, (360 - this.yaw) + 90, 0, -1d, (360 - this.yaw), (float) this.angleArm1 - 40, 10d, (360 - this.yaw), (float) this.angleArm2 - 90, 10d, 0, 0, 0);
				if (this.angleArm3 < 15) {

					if (world.getBlockState(BP).getBlock() == Blocks.BEDROCK && world.getBlockState(BP).getBlockHardness(world, BP) != -1) {
						BlockUtil.BreakBlock(world, BP, this.getControllingPassenger());
					}
					AxisAlignedBB bucketboundingBox = new AxisAlignedBB(BP);
					// was expand
					bucketboundingBox.grow(1d);
					List list = this.world.getEntitiesWithinAABBExcludingEntity(this, bucketboundingBox);
					collidedEntitiesInList(list);
				}
				// LogHelper.info(BP);
				// world.setBlockState(BP, Blocks.DIRT.getDefaultState());

				// }
				if (this.angleArm3 > 42) {
					// bucket up
					// Drop blocks
					// TODO needs something to pace it a bit more now it drops
					// everything way to fast.
					for (int i = 0; i < SIZE; i++) {
						ItemStack item = this.inventory.getStackInSlot(i);

						if (item != null && item.getCount() > 0) {
							;

							ItemEntity ItemEntity = new ItemEntity(world, BP.getX(), BP.getY() - 1, BP.getZ(), item);

							if (item.hasTagCompound()) {
								ItemEntity.getItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
							}

							float factor = 0.05F;
							// ItemEntity.motionX = rand.nextGaussian() * factor;
							ItemEntity.motionY = 0;
							// ItemEntity.motionZ = rand.nextGaussian() * factor;
							ItemEntity.forceSpawn = true;
							world.spawnEntity(ItemEntity);
							// item.stackSize = 0;
							inventory.extractItem(i, inventory.getStackInSlot(i).getCount(), false);
							// this.inventory.insertItem(i, ItemStack.EMPTY, false);
						}
					}
				}
			}

		}

	}

	private void collidedEntitiesInList(List par1List) {
		for (int i = 0; i < par1List.size(); ++i) {
			Entity entity = (Entity) par1List.get(i);
			if (entity != null) {
				if (entity instanceof ItemEntity) {
					ItemStack is = ((ItemEntity) entity).getItem().copy();
					is.setItemDamage(((ItemEntity) entity).getItem().getItemDamage());
					if (!entity.isDead) {
						if (is.getCount() > 0) {
							ItemStack is1 = addToinventory(is);

							if (is1 != null && is1.getCount() != 0) {
								((ItemEntity) entity).setItem(is1);
							} else {
								entity.remove();
							}
						}
					}
				}
			}
		}
	}

	@Override
	public double getMountedXOffset() {
		// should be overridden in extended class if not default;

		return calcTwoOffsetX(this.mountedOffsetZ, 90, this.mountedOffsetX);
		// return calcOffsetX(mountedOffsetX);
	}

	@Override
	public double getMountedZOffset() {
		// should be overridden in extended class if not default;

		return calcTwoOffsetZ(this.mountedOffsetZ, 90, this.mountedOffsetX);
		// return calcOffsetX(mountedOffsetX);
	}

	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

}
