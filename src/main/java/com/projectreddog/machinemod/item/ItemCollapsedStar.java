package com.projectreddog.machinemod.item;

import com.projectreddog.machinemod.block.BlockMachineBleakPortal;
import com.projectreddog.machinemod.block.BlockMachineBleakPortalFrame;
import com.projectreddog.machinemod.init.ModBlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCollapsedStar extends ItemMachineMod {
	public String registryName = "collapsedstar";

	public ItemCollapsedStar() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 1;

	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float xOff, float yOff, float zOff) {
		ItemStack stack = player.getHeldItem(hand);
		boolean result = false;

		if (!world.isRemote) {
			pos = pos.up();// move up off the block they clicked on
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			EnumFacing portalFacing = null;
			// CHEck if portal already exists & bail (fail if it does)
			if (world.isAirBlock(pos)) {
				// its air no portal or other block exist!
				if (world.isAirBlock(pos.up())) {
					// its air no portal or other block exist!
					if (world.isAirBlock(pos.up(2))) {
						// its air no portal or other block exist!
						// Check E W first
						if (world.getBlockState(pos.east()).getBlock() == ModBlocks.machinebleakportalframe && world.getBlockState(pos.east()).getValue(BlockMachineBleakPortalFrame.FACING) == EnumFacing.WEST) {
							if (world.getBlockState(pos.west()).getBlock() == ModBlocks.machinebleakportalframe && world.getBlockState(pos.west()).getValue(BlockMachineBleakPortalFrame.FACING) == EnumFacing.EAST) {
								if (world.getBlockState(pos.east().up()).getBlock() == ModBlocks.machinebleakportalframe && world.getBlockState(pos.east().up()).getValue(BlockMachineBleakPortalFrame.FACING) == EnumFacing.WEST) {
									if (world.getBlockState(pos.west().up()).getBlock() == ModBlocks.machinebleakportalframe && world.getBlockState(pos.west().up()).getValue(BlockMachineBleakPortalFrame.FACING) == EnumFacing.EAST) {
										if (world.getBlockState(pos.east().up().up()).getBlock() == ModBlocks.machinebleakportalframe && world.getBlockState(pos.east().up().up()).getValue(BlockMachineBleakPortalFrame.FACING) == EnumFacing.WEST) {
											if (world.getBlockState(pos.west().up().up()).getBlock() == ModBlocks.machinebleakportalframe && world.getBlockState(pos.west().up().up()).getValue(BlockMachineBleakPortalFrame.FACING) == EnumFacing.EAST) {
												// all portalframe blocks are found with correct orentation.
												// spawn the portal with this direction
												portalFacing = EnumFacing.EAST;

												world.setBlockState(pos.east(), ModBlocks.machinebleakportalframe.getDefaultState().withProperty(BlockMachineBleakPortalFrame.FACING, EnumFacing.WEST).withProperty(BlockMachineBleakPortalFrame.HAS_STAR, Boolean.valueOf(true)));
												IBlockState state = world.getBlockState(pos.east());
												world.notifyBlockUpdate(pos.east(), state, state, 3);

												world.setBlockState(pos.east().up(), ModBlocks.machinebleakportalframe.getDefaultState().withProperty(BlockMachineBleakPortalFrame.FACING, EnumFacing.WEST).withProperty(BlockMachineBleakPortalFrame.HAS_STAR, Boolean.valueOf(true)));
												state = world.getBlockState(pos.east().up());
												world.notifyBlockUpdate(pos.east().up(), state, state, 3);

												world.setBlockState(pos.east().up().up(), ModBlocks.machinebleakportalframe.getDefaultState().withProperty(BlockMachineBleakPortalFrame.FACING, EnumFacing.WEST).withProperty(BlockMachineBleakPortalFrame.HAS_STAR, Boolean.valueOf(true)));
												state = world.getBlockState(pos.east().up().up());
												world.notifyBlockUpdate(pos.east().up().up(), state, state, 3);

												world.setBlockState(pos.west(), ModBlocks.machinebleakportalframe.getDefaultState().withProperty(BlockMachineBleakPortalFrame.FACING, EnumFacing.EAST).withProperty(BlockMachineBleakPortalFrame.HAS_STAR, Boolean.valueOf(true)));
												state = world.getBlockState(pos.west());
												world.notifyBlockUpdate(pos.west(), state, state, 3);

												world.setBlockState(pos.west().up(), ModBlocks.machinebleakportalframe.getDefaultState().withProperty(BlockMachineBleakPortalFrame.FACING, EnumFacing.EAST).withProperty(BlockMachineBleakPortalFrame.HAS_STAR, Boolean.valueOf(true)));
												state = world.getBlockState(pos.west().up());
												world.notifyBlockUpdate(pos.west().up(), state, state, 3);

												world.setBlockState(pos.west().up().up(), ModBlocks.machinebleakportalframe.getDefaultState().withProperty(BlockMachineBleakPortalFrame.FACING, EnumFacing.EAST).withProperty(BlockMachineBleakPortalFrame.HAS_STAR, Boolean.valueOf(true)));

												state = world.getBlockState(pos.west().up().up());
												world.notifyBlockUpdate(pos.west().up().up(), state, state, 3);

											}
										}
									}
								}
							}
						}

						if (world.getBlockState(pos.north()).getBlock() == ModBlocks.machinebleakportalframe && world.getBlockState(pos.north()).getValue(BlockMachineBleakPortalFrame.FACING) == EnumFacing.SOUTH) {
							if (world.getBlockState(pos.south()).getBlock() == ModBlocks.machinebleakportalframe && world.getBlockState(pos.south()).getValue(BlockMachineBleakPortalFrame.FACING) == EnumFacing.NORTH) {
								if (world.getBlockState(pos.north().up()).getBlock() == ModBlocks.machinebleakportalframe && world.getBlockState(pos.north().up()).getValue(BlockMachineBleakPortalFrame.FACING) == EnumFacing.SOUTH) {
									if (world.getBlockState(pos.south().up()).getBlock() == ModBlocks.machinebleakportalframe && world.getBlockState(pos.south().up()).getValue(BlockMachineBleakPortalFrame.FACING) == EnumFacing.NORTH) {
										if (world.getBlockState(pos.north().up().up()).getBlock() == ModBlocks.machinebleakportalframe && world.getBlockState(pos.north().up().up()).getValue(BlockMachineBleakPortalFrame.FACING) == EnumFacing.SOUTH) {
											if (world.getBlockState(pos.south().up().up()).getBlock() == ModBlocks.machinebleakportalframe && world.getBlockState(pos.south().up().up()).getValue(BlockMachineBleakPortalFrame.FACING) == EnumFacing.NORTH) {
												// all portalframe blocks are found with correct orentation.
												// spawn the portal with this direction
												portalFacing = EnumFacing.SOUTH;

												world.setBlockState(pos.south(), ModBlocks.machinebleakportalframe.getDefaultState().withProperty(BlockMachineBleakPortalFrame.FACING, EnumFacing.NORTH).withProperty(BlockMachineBleakPortalFrame.HAS_STAR, Boolean.valueOf(true)));
												IBlockState state = world.getBlockState(pos.south());
												world.notifyBlockUpdate(pos.south(), state, state, 3);
												world.setBlockState(pos.south().up(), ModBlocks.machinebleakportalframe.getDefaultState().withProperty(BlockMachineBleakPortalFrame.FACING, EnumFacing.NORTH).withProperty(BlockMachineBleakPortalFrame.HAS_STAR, Boolean.valueOf(true)));
												state = world.getBlockState(pos.south().up());
												world.notifyBlockUpdate(pos.south().up(), state, state, 3);

												world.setBlockState(pos.south().up().up(), ModBlocks.machinebleakportalframe.getDefaultState().withProperty(BlockMachineBleakPortalFrame.FACING, EnumFacing.NORTH).withProperty(BlockMachineBleakPortalFrame.HAS_STAR, Boolean.valueOf(true)));
												state = world.getBlockState(pos.south().up().up());
												world.notifyBlockUpdate(pos.south().up().up(), state, state, 3);

												world.setBlockState(pos.north(), ModBlocks.machinebleakportalframe.getDefaultState().withProperty(BlockMachineBleakPortalFrame.FACING, EnumFacing.SOUTH).withProperty(BlockMachineBleakPortalFrame.HAS_STAR, Boolean.valueOf(true)));
												state = world.getBlockState(pos.north());
												world.notifyBlockUpdate(pos.north(), state, state, 3);

												world.setBlockState(pos.north().up(), ModBlocks.machinebleakportalframe.getDefaultState().withProperty(BlockMachineBleakPortalFrame.FACING, EnumFacing.SOUTH).withProperty(BlockMachineBleakPortalFrame.HAS_STAR, Boolean.valueOf(true)));
												state = world.getBlockState(pos.north().up());
												world.notifyBlockUpdate(pos.north().up(), state, state, 3);

												world.setBlockState(pos.north().up().up(), ModBlocks.machinebleakportalframe.getDefaultState().withProperty(BlockMachineBleakPortalFrame.FACING, EnumFacing.SOUTH).withProperty(BlockMachineBleakPortalFrame.HAS_STAR, Boolean.valueOf(true)));

												state = world.getBlockState(pos.north().up().up());
												world.notifyBlockUpdate(pos.north().up().up(), state, state, 3);
											}
										}
									}
								}
							}
						}
						if (portalFacing != null) {
							world.setBlockState(pos, ModBlocks.machinebleakportal.getDefaultState().withProperty(BlockMachineBleakPortal.FACING, portalFacing));
							IBlockState state = world.getBlockState(pos);
							world.notifyBlockUpdate(pos, state, state, 3);

							world.setBlockState(pos.up(), ModBlocks.machinebleakportal.getDefaultState().withProperty(BlockMachineBleakPortal.FACING, portalFacing));
							state = world.getBlockState(pos.up());
							world.notifyBlockUpdate(pos.up(), state, state, 3);

							world.setBlockState(pos.up().up(), ModBlocks.machinebleakportal.getDefaultState().withProperty(BlockMachineBleakPortal.FACING, portalFacing));
							state = world.getBlockState(pos.up().up());
							world.notifyBlockUpdate(pos.up().up(), state, state, 3);
							result = true;
						}

					}

				}
			}
			// CHeck if the potral frame is here
			// if not create the portal

			if (result && !player.capabilities.isCreativeMode) {
				stack.setCount(stack.getCount() - 1);
			}
		}
		if (result)

		{
			return EnumActionResult.PASS;
		} else {
			return EnumActionResult.FAIL;
		}

	}

}
