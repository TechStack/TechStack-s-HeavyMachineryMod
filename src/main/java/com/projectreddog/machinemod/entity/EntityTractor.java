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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.items.ItemStackHandler;

public class EntityTractor extends EntityMachineModRideable {
	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);

	public double bladeOffset = 2.0d;

	public EntityTractor(World world) {
		super(world);
		setSize(2.5F, 2F);
		SIZE = 54;
		inventory = new ItemStackHandler(SIZE);
		// inventory = new ItemStack[9];
		this.mountedOffsetY = 1.35D;
		this.mountedOffsetX = 0.d;
		this.mountedOffsetZ = 0.d;
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
		if (!world.isRemote) {
			// digMethodA();
			BlockPos bp;
			int angle = 0;
			// this will calcl the offset for three positions behind the tractor
			// (3 wide)
			if (this.isPlayerPushingSprintButton) {
				for (int i = -4; i < 5; i++) {

					if (i == 0) {
						angle = 0;
					} else {
						angle = 90;
					}
					bp = new BlockPos(posX + calcTwoOffsetX(-2.5, angle, i), posY - 1 + .1d, posZ + calcTwoOffsetZ(-2.5, angle, i));

					if (!this.inventory.getStackInSlot(0).isEmpty()) {
						if (this.inventory.getStackInSlot(0).getItem() instanceof ItemTractorAttachment) {
							if (this.inventory.getStackInSlot(0).getItem() instanceof ItemTractorAttachmentPlow) {
								if (world.getBlockState(bp).getBlock() == Blocks.DIRT || world.getBlockState(bp).getBlock() == Blocks.GRASS) {
									world.setBlockState(bp, Blocks.FARMLAND.getDefaultState());
									if (world.getBlockState(bp.up()).getBlock().getMaterial(world.getBlockState(bp.up())) == Material.PLANTS || world.getBlockState(bp.up()).getBlock().getMaterial(world.getBlockState(bp.up())).isReplaceable()) {
										BlockUtil.BreakBlock(world, bp.up(), this.getControllingPassenger());

									} else {
										// LogHelper.info(world.getBlockState(bp.offsetUp()).getBlock().getMaterial());
									}
								}
							} else if (this.inventory.getStackInSlot(0).getItem() instanceof ItemTractorAttachmentPlanter) {
								bp = new BlockPos(posX + calcTwoOffsetX(-4.5, angle, i), posY - 1 + .1d, posZ + calcTwoOffsetZ(-4.5, angle, i));

								for (int j = 1; j < SIZE; j++)// start at 1 because
								// first slot is
								// attachment only
								{
									if (this.inventory.getStackInSlot(j) != null) {
										if (this.inventory.getStackInSlot(j).getCount() > 0) {

											if (this.inventory.getStackInSlot(j).getItem() instanceof IPlantable) {
												if (world.getBlockState(bp).getBlock().canSustainPlant(world.getBlockState(bp), world, bp, EnumFacing.UP, (IPlantable) this.inventory.getStackInSlot(j).getItem()) && world.isAirBlock(bp.up())) {

													world.setBlockState(bp.up(), ((IPlantable) this.inventory.getStackInSlot(j).getItem()).getPlant(world, bp.up()));
													this.inventory.extractItem(j, 1, false);
													j = SIZE;

												}
											}
										}
									}
								}
								// if (world.getBlockState(bp).getBlock() ==
								// Blocks.farmland &&
								// world.isAirBlock(bp.offset(EnumFacing.UP,
								// 1))) {
								//
								// world.setBlockState(bp.offset(EnumFacing.UP,
								// 1), Blocks.wheat.getDefaultState());
								// }
							} else if (this.inventory.getStackInSlot(0).getItem() instanceof ItemTractorAttachmentSprayer) {
								// Fertilize checks & actions

								for (int j = 1; j < SIZE; j++)// start at 1 because
								// first slot is
								// attachment only
								{
									if (this.inventory.getStackInSlot(j) != null) {
										if (this.inventory.getStackInSlot(j).getCount() > 0) {

											if (this.inventory.getStackInSlot(j).getItem() instanceof ItemDye) {

												// / NOT UPDATE PROOF ( CALLS
												// non named function )
												if (EnumDyeColor.byDyeDamage(this.inventory.getStackInSlot(j).getItemDamage()) == EnumDyeColor.WHITE) {

													EntityPlayer p;
													if (this.getControllingPassenger() != null && this.getControllingPassenger() instanceof EntityPlayer) {
														p = ((EntityPlayer) this.getControllingPassenger());
													} else {
														p = net.minecraftforge.common.util.FakePlayerFactory.getMinecraft((net.minecraft.world.WorldServer) world);
													}
													boolean didUse = ((ItemDye) this.inventory.getStackInSlot(j).getItem()).applyBonemeal(this.inventory.getStackInSlot(j), world, bp.up(), p, EnumHand.MAIN_HAND);

													if (didUse) {
														// used to clear out 0
														// size stack
														if (this.inventory.getStackInSlot(j).getCount() == 0) {
															inventory.extractItem(j, inventory.getStackInSlot(j).getCount(), false);
															// inventory.insertItem(j, ItemStack.EMPTY, false);
														}

														j = SIZE;
													}

												}
											}
										}
									}
								}

							} else if (this.inventory.getStackInSlot(0).getItem() instanceof ItemTractorAttachmentTrencher) {
								// Fertilize checks & actions
								if (i == 0) {
									if (world.getBlockState(bp).getBlock() == Blocks.DIRT || world.getBlockState(bp).getBlock() == Blocks.GRASS || world.getBlockState(bp).getBlock() == Blocks.FARMLAND) {
										BlockUtil.BreakBlock(world, bp, this.getControllingPassenger());

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
