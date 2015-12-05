package com.projectreddog.machinemod.block;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.projectreddog.machinemod.reference.Reference;

public class BlockMachineDrilledAndesite extends BlockMachineModManyTexture {
	public static final PropertyDirection FACING = PropertyDirection.create("facing");

	public BlockMachineDrilledAndesite() {
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_DRILLED_ANDESITE);
		// this.setBlockName(Reference.MODBLOCK_MACHINE_DRILLED_STONE);
		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_DRILLED_STONE);
		// this.setHardness(15f);// not sure on the hardness
		this.setStepSound(soundTypeStone);
		this.setHardness(1.5f);

	}

	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, func_180695_a(worldIn, pos, placer));
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, func_180695_a(worldIn, pos, placer)), 2);
	}

	public static EnumFacing func_180695_a(World worldIn, BlockPos p_180695_1_, EntityLivingBase p_180695_2_) {
		if (MathHelper.abs((float) p_180695_2_.posX - (float) p_180695_1_.getX()) < 2.0F && MathHelper.abs((float) p_180695_2_.posZ - (float) p_180695_1_.getZ()) < 2.0F) {
			double d0 = p_180695_2_.posY + (double) p_180695_2_.getEyeHeight();

			if (d0 - (double) p_180695_1_.getY() > 2.0D) {
				return EnumFacing.UP;
			}

			if ((double) p_180695_1_.getY() - d0 > 0.0D) {
				return EnumFacing.DOWN;
			}
		}

		return p_180695_2_.getHorizontalFacing().getOpposite();
	}

	/**
	 * Possibly modify the given BlockState before rendering it on an Entity (Minecarts, Endermen, ...)
	 */
	@SideOnly(Side.CLIENT)
	public IBlockState getStateForEntityRender(IBlockState state) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);

		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { FACING });
	}

	@SideOnly(Side.CLIENT)
	static final class SwitchEnumFacing {
		static final int[] field_180356_a = new int[EnumFacing.values().length];

		static {
			try {
				field_180356_a[EnumFacing.DOWN.ordinal()] = 1;
			} catch (NoSuchFieldError var6) {
				;
			}

			try {
				field_180356_a[EnumFacing.UP.ordinal()] = 2;
			} catch (NoSuchFieldError var5) {
				;
			}

			try {
				field_180356_a[EnumFacing.NORTH.ordinal()] = 3;
			} catch (NoSuchFieldError var4) {
				;
			}

			try {
				field_180356_a[EnumFacing.SOUTH.ordinal()] = 4;
			} catch (NoSuchFieldError var3) {
				;
			}

			try {
				field_180356_a[EnumFacing.WEST.ordinal()] = 5;
			} catch (NoSuchFieldError var2) {
				;
			}

			try {
				field_180356_a[EnumFacing.EAST.ordinal()] = 6;
			} catch (NoSuchFieldError var1) {
				;
			}
		}
	}
}
