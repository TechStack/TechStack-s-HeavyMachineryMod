package com.projectreddog.machinemod.entity;

import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.item.attachments.ItemTractorAttachment;
import com.projectreddog.machinemod.item.attachments.ItemTractorAttachmentPlanter;
import com.projectreddog.machinemod.item.attachments.ItemTractorAttachmentPlow;
import com.projectreddog.machinemod.item.attachments.ItemTractorAttachmentSprayer;
import com.projectreddog.machinemod.item.attachments.ItemTractorAttachmentTrencher;
import com.projectreddog.machinemod.utility.BlockUtil;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class EntityTractor extends EntityMachineModRideable {
	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public double bladeOffset = 2.0d;

	public EntityTractor(World world) {
		super(world);
		setSize(1.5F, 2F);
		inventory = new ItemStack[9];
		this.mountedOffsetY = 0.55D;
		this.mountedOffsetX = 0.65d;
		this.mountedOffsetZ = 0.65d;
		this.maxAngle = 0;
		this.minAngle = -60;
		this.droppedItem = ModItems.tractor;
		this.shouldSendClientInvetoryUpdates = true;

	}

	public AxisAlignedBB getBoundingBox() {
		return boundingBox;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!worldObj.isRemote) {
			// digMethodA();
			BlockPos bp;
			int angle = 0;
			// this will calcl the offset for three positions behind the tractor
			// (3 wide)
			if (this.isPlayerPushingSprintButton) {
				for (int i = -1; i < 2; i++) {

					if (i == 0) {
						angle = 0;
					} else {
						angle = 90;
					}
					bp = new BlockPos(posX + calcTwoOffsetX(-3.5, angle, i), posY - 1, posZ + calcTwoOffsetZ(-3.5, angle, i));

					if (this.getStackInSlot(0) != null) {
						if (this.getStackInSlot(0).getItem() instanceof ItemTractorAttachment) {
							if (this.getStackInSlot(0).getItem() instanceof ItemTractorAttachmentPlow) {
								if (worldObj.getBlockState(bp).getBlock() == Blocks.dirt || worldObj.getBlockState(bp).getBlock() == Blocks.GRASS) {
									worldObj.setBlockState(bp, Blocks.FARMLAND.getDefaultState());
									if (worldObj.getBlockState(bp.up()).getBlock().getMaterial() == Material.PLANTS || worldObj.getBlockState(bp.up()).getBlock().getMaterial().isReplaceable()) {
										BlockUtil.BreakBlock(worldObj, bp.up(), this.getControllingPassenger());

									} else {
										// LogHelper.info(worldObj.getBlockState(bp.offsetUp()).getBlock().getMaterial());
									}
								}
							} else if (this.getStackInSlot(0).getItem() instanceof ItemTractorAttachmentPlanter) {

								for (int j = 1; j < 9; j++)// start at 1 because
								// first slot is
								// attachment only
								{
									if (this.getStackInSlot(j) != null) {
										if (this.getStackInSlot(j).stackSize > 0) {

											if (this.getStackInSlot(j).getItem() instanceof IPlantable) {
												if (worldObj.getBlockState(bp).getBlock().canSustainPlant(worldObj, bp, EnumFacing.UP, (IPlantable) this.getStackInSlot(j).getItem()) && worldObj.isAirBlock(bp.up())) {

													worldObj.setBlockState(bp.up(), ((IPlantable) this.getStackInSlot(j).getItem()).getPlant(worldObj, bp.up()));
													this.decrStackSize(j, 1);
													j = 9;

												}
											}
										}
									}
								}
								// if (worldObj.getBlockState(bp).getBlock() ==
								// Blocks.farmland &&
								// worldObj.isAirBlock(bp.offset(EnumFacing.UP,
								// 1))) {
								//
								// worldObj.setBlockState(bp.offset(EnumFacing.UP,
								// 1), Blocks.wheat.getDefaultState());
								// }
							} else if (this.getStackInSlot(0).getItem() instanceof ItemTractorAttachmentSprayer) {
								// Fertilize checks & actions

								for (int j = 1; j < 9; j++)// start at 1 because
								// first slot is
								// attachment only
								{
									if (this.getStackInSlot(j) != null) {
										if (this.getStackInSlot(j).stackSize > 0) {

											if (this.getStackInSlot(j).getItem() instanceof ItemDye) {

												// / NOT UPDATE PROOF ( CALLS
												// non named function )
												if (EnumDyeColor.byDyeDamage(this.getStackInSlot(j).getItemDamage()) == EnumDyeColor.WHITE) {

													EntityPlayer p;
													if (this.getControllingPassenger() != null && this.getControllingPassenger() instanceof EntityPlayer) {
														p = ((EntityPlayer) this.getControllingPassenger());
													} else {
														p = net.minecraftforge.common.util.FakePlayerFactory.getMinecraft((net.minecraft.world.WorldServer) worldObj);
													}
													boolean didUse = ((ItemDye) this.getStackInSlot(j).getItem()).applyBonemeal(this.getStackInSlot(j), worldObj, bp.up(), p);

													if (didUse) {
														// used to clear out 0
														// size stack
														if (this.getStackInSlot(j).stackSize == 0) {
															setInventorySlotContents(j, null);
														}

														j = 9;
													}

												}
											}
										}
									}
								}

							} else if (this.getStackInSlot(0).getItem() instanceof ItemTractorAttachmentTrencher) {
								// Fertilize checks & actions
								if (i == 0) {
									if (worldObj.getBlockState(bp).getBlock() == Blocks.dirt || worldObj.getBlockState(bp).getBlock() == Blocks.GRASS || worldObj.getBlockState(bp).getBlock() == Blocks.farmland) {
										BlockUtil.BreakBlock(worldObj, bp, this.getControllingPassenger());

									}
								}

							}

						}
					}
				}

			}

		}
	}
}
