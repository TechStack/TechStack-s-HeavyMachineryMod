package com.projectreddog.machinemod.block;

import javax.annotation.Nullable;

import com.projectreddog.machinemod.creativetab.CreativeTabMachineMod;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModDimensions;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.world.BleakTeleporter;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMachineBleakPortal extends BlockMachineMod {
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	protected BlockMachineBleakPortal(Material material) {
		super(Material.PORTAL);

		// can override later ;)
		this.setCreativeTab(CreativeTabMachineMod.MACHINEMOD_BLOCKS_TAB);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));

		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_BLEAK_PORTAL);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_BLEAK_PORTAL);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);
		// this.setHardness(15f);// not sure on the hardness
		this.setSoundType(SoundType.METAL);
		this.setHardness(-1f);
		this.setLightLevel(1.0F);
	}

	public BlockMachineBleakPortal() {
		this(Material.ANVIL);
	}

	/**
	 * Called When an Entity Collided with the Block
	 */
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (!entityIn.isRiding() && !entityIn.isBeingRidden() && entityIn.isNonBoss()) {
			if (!entityIn.world.isRemote) {
				// server
				if (entityIn instanceof EntityPlayerMP && entityIn.isSneaking()) {
					int targetDim = ModDimensions.bleakDimID;
					if (entityIn.world.provider.getDimension() == ModDimensions.bleakDimID) {
						targetDim = 0;
					}
					// if (player.isSneaking()) {
					// BleakTeleporter.teleportToDimension(player, 0, pos.getX(), pos.getY(), pos.getZ());
					// } else {
					BlockPos bp = pos;
					if (state.getValue(FACING) == EnumFacing.EAST || state.getValue(FACING) == EnumFacing.WEST) {
						bp = pos.offset(EnumFacing.NORTH, 1);
					} else {
						bp = pos.offset(EnumFacing.EAST, 1);
					}
					entityIn.setSneaking(false);
					BleakTeleporter.teleportToDimension((EntityPlayerMP) entityIn, targetDim, bp.getX(), bp.getY(), bp.getZ(), pos, state.getValue(FACING));
					// }
				}
			}
		}
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());// .withProperty(HAS_STAR, false);
	}

	/**
	 * Possibly modify the given BlockState before rendering it on an Entity (Minecarts, Endermen, ...)
	 */
	@SideOnly(Side.CLIENT)
	public IBlockState getStateForEntityRender(IBlockState state) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
	}

	/**
	 * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the IBlockstate
	 */
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 3));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
		return i;
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (!validatePortal(worldIn, pos)) {
			// TODO actually turn everything off if above line is false !

			DisablePortalForThisBlock(worldIn, pos);
		}
	}

	public void DisablePortalForThisBlock(World world, BlockPos bottomOfOneSide) {
		EnumFacing ef = world.getBlockState(bottomOfOneSide).getValue(FACING);

		if (world.getBlockState(bottomOfOneSide).getBlock() == ModBlocks.machinebleakportal) {
			world.setBlockToAir(bottomOfOneSide);
		}

	}

	public BlockPos findCenterBottom(World world, BlockPos pos) {
		if (world.getBlockState(pos).getBlock() == ModBlocks.machinebleakportal) {
			EnumFacing ef = world.getBlockState(pos).getValue(FACING);
			BlockPos portalColumn = pos;
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
			for (int i = 0; i < 3; i++) {
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
