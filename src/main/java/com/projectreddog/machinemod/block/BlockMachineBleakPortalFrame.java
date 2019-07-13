package com.projectreddog.machinemod.block;

import javax.annotation.Nullable;

import com.projectreddog.machinemod.creativetab.CreativeTabMachineMod;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.BlockUtil;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.state.IProperty;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.BlockStateContainer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMachineBleakPortalFrame extends BlockMachineMod {
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool HAS_STAR = PropertyBool.create("netherstar");
	protected static final AxisAlignedBB AABB_BLOCK_WEST = new AxisAlignedBB(0.5D, 0.0D, 0.0D, 1.0D, 1.D, 1.0D);
	protected static final AxisAlignedBB AABB_BLOCK_EAST = new AxisAlignedBB(0.0D, 0.0D, 0.0D, .5D, 1.D, 1.0D);
	protected static final AxisAlignedBB AABB_BLOCK_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.5D, 1.0D, 1.D, 1.0D);
	protected static final AxisAlignedBB AABB_BLOCK_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.D, 0.5D);

	protected BlockMachineBleakPortalFrame(Material material) {
		super(material);

		// can override later ;)
		this.setCreativeTab(CreativeTabMachineMod.MACHINEMOD_BLOCKS_TAB);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(HAS_STAR, Boolean.valueOf(false)));

		// 1.8
		// REMOVED 1.14
		// this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_BLEAK_PORTAL_FRAME);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_BLEAK_PORTAL_FRAME);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);
		// this.setHardness(15f);// not sure on the hardness
		this.setSoundType(SoundType.METAL);
		this.setHardness(1.5f);

	}

	public BlockMachineBleakPortalFrame() {
		this(Material.ANVIL);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());// .withProperty(HAS_STAR,
																										// false);
	}

	/**
	 * Possibly modify the given BlockState before rendering it on an Entity
	 * (Minecarts, Endermen, ...)
	 */
	@SideOnly(Side.CLIENT)
	public IBlockState getStateForEntityRender(IBlockState state) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
	}

	/**
	 * Called by ItemBlocks just before a block is actually set in the world, to
	 * allow for adjustments to the IBlockstate
	 */
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(HAS_STAR, Boolean.valueOf(false));
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(HAS_STAR, Boolean.valueOf((meta & 4) != 0)).withProperty(FACING, EnumFacing.getHorizontal(meta & 3));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();

		if (((Boolean) state.getValue(HAS_STAR)).booleanValue()) {
			i |= 4;
		}

		return i;
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, HAS_STAR });
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		if (blockState.getValue(FACING) == EnumFacing.WEST) {
			return AABB_BLOCK_WEST;
		}
		if (blockState.getValue(FACING) == EnumFacing.EAST) {
			return AABB_BLOCK_EAST;
		}
		if (blockState.getValue(FACING) == EnumFacing.NORTH) {
			return AABB_BLOCK_NORTH;
		}
		if (blockState.getValue(FACING) == EnumFacing.SOUTH) {
			return AABB_BLOCK_SOUTH;
		}
		return NULL_AABB;

	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		pos = pos.offset(side);
		EnumFacing enumfacing = null;

		if (blockState.getBlock() == this) {
			enumfacing = (EnumFacing) blockState.getValue(FACING);

			if (enumfacing == null) {
				return false;
			}

			if ((enumfacing == EnumFacing.NORTH || enumfacing == EnumFacing.SOUTH) && side != EnumFacing.EAST && side != EnumFacing.WEST) {
				return false;
			}

			if ((enumfacing == EnumFacing.EAST || enumfacing == EnumFacing.WEST) && side != EnumFacing.SOUTH && side != EnumFacing.NORTH) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (!validatePortal(worldIn, pos)) {
			// TODO actually turn everything off if above line is false !
			BlockPos bottomPortalFrame = findBottomPortalFrame(worldIn, pos);

			DisablePortalForThisBlock(worldIn, bottomPortalFrame);
		}
	}

	public void DisablePortalForThisBlock(World world, BlockPos bottomOfOneSide) {
		EnumFacing ef = world.getBlockState(bottomOfOneSide).getValue(FACING);

		if (world.getBlockState(bottomOfOneSide).getBlock() == ModBlocks.machinebleakportalframe) {
			BlockUtil.setBlockandNotify(world, bottomOfOneSide, ModBlocks.machinebleakportalframe.getDefaultState().withProperty(FACING, ef).withProperty(HAS_STAR, Boolean.valueOf(false)));
		}

	}

	public BlockPos findBottomPortalFrame(World world, BlockPos pos) {
		BlockPos result = pos;
		EnumFacing ef = world.getBlockState(pos).getValue(FACING);
		int depth = 1;
		while (world.getBlockState(result.down()).getBlock() == ModBlocks.machinebleakportalframe && depth < 3) {
			// its a portal frame
			// Check if its on & facing the same direction
			if (world.getBlockState(result.down()).getValue(FACING) == ef) {
				// facing same
				if (world.getBlockState(result.down()).getValue(HAS_STAR) == Boolean.valueOf(true)) {
					// its on !
					depth++;
					result = result.down();
				} else {
					// star state different so break
					return result;

				}
			} else {
				// facing different so break !
				return result;

			}
		}

		return result;
	}

	public BlockPos findCenterBottom(World world, BlockPos pos) {
		if (world.getBlockState(pos).getBlock() == ModBlocks.machinebleakportalframe) {
			EnumFacing ef = world.getBlockState(pos).getValue(FACING);
			BlockPos portalColumn = pos.offset(ef);
			while (world.getBlockState(portalColumn.down()).getBlock() == ModBlocks.machinebleakportal) {
				portalColumn = portalColumn.down();
			}

			return portalColumn;
		}
		return null;
	}

	public boolean validatePortal(World world, BlockPos pos) {
		boolean result = true;
		EnumFacing ef = world.getBlockState(pos).getValue(FACING);
		BlockPos bp = findCenterBottom(world, pos);
		if (bp == null) {
			result = false;
			return result;
		} else {
			for (int i = 0; i < 2; i++) {
				if (world.getBlockState(bp.up(i)).getBlock() != ModBlocks.machinebleakportal) {
					result = false;
					return result;
				}
				if (world.getBlockState(bp.up(i).offset(ef)).getBlock() != ModBlocks.machinebleakportalframe) {
					result = false;
					return result;
				}
				if (world.getBlockState(bp.up(i).offset(ef.getOpposite())).getBlock() != ModBlocks.machinebleakportalframe) {
					result = false;
					return result;
				}

			}
		}
		return result;
	}

}
