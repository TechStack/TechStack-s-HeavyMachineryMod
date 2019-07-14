package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.DirectionProperty;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.BlockStateContainer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMachineDrilledAndesite extends BlockMachineModManyTexture {
	public static final DirectionProperty FACING = DirectionProperty.create("facing");

	public BlockMachineDrilledAndesite() {
		super();
		// 1.8
		// REMOVED 1.14
		// this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_DRILLED_ANDESITE);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_DRILLED_ANDESITE);

		// this.setBlockName(Reference.MODBLOCK_MACHINE_DRILLED_STONE);
		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_DRILLED_STONE);
		// this.setHardness(15f);// not sure on the hardness
		this.setSoundType(SoundType.STONE);
		this.setHardness(1.5f);

	}

	public BlockState onBlockPlaced(World worldIn, BlockPos pos, Direction facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, func_180695_a(worldIn, pos, placer));
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, func_180695_a(worldIn, pos, placer)), 2);
	}

	public static Direction func_180695_a(World worldIn, BlockPos p_180695_1_, EntityLivingBase p_180695_2_) {
		if (MathHelper.abs((float) p_180695_2_.posX - (float) p_180695_1_.getX()) < 2.0F && MathHelper.abs((float) p_180695_2_.posZ - (float) p_180695_1_.getZ()) < 2.0F) {
			double d0 = p_180695_2_.posY + (double) p_180695_2_.getEyeHeight();

			if (d0 - (double) p_180695_1_.getY() > 2.0D) {
				return Direction.UP;
			}

			if ((double) p_180695_1_.getY() - d0 > 0.0D) {
				return Direction.DOWN;
			}
		}

		return p_180695_2_.getHorizontalFacing().getOpposite();
	}

	/**
	 * Possibly modify the given BlockState before rendering it on an Entity (Minecarts, Endermen, ...)
	 */
	@SideOnly(Side.CLIENT)
	public BlockState getStateForEntityRender(BlockState state) {
		return this.getDefaultState().withProperty(FACING, Direction.SOUTH);
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public BlockState getStateFromMeta(int meta) {
		Direction Direction = Direction.getFront(meta);

		return this.getDefaultState().withProperty(FACING, Direction);
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(BlockState state) {
		return ((Direction) state.getValue(FACING)).getIndex();
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@SideOnly(Side.CLIENT)
	static final class SwitchDirection {
		static final int[] field_180356_a = new int[Direction.values().length];

		static {
			try {
				field_180356_a[Direction.DOWN.ordinal()] = 1;
			} catch (NoSuchFieldError var6) {
				;
			}

			try {
				field_180356_a[Direction.UP.ordinal()] = 2;
			} catch (NoSuchFieldError var5) {
				;
			}

			try {
				field_180356_a[Direction.NORTH.ordinal()] = 3;
			} catch (NoSuchFieldError var4) {
				;
			}

			try {
				field_180356_a[Direction.SOUTH.ordinal()] = 4;
			} catch (NoSuchFieldError var3) {
				;
			}

			try {
				field_180356_a[Direction.WEST.ordinal()] = 5;
			} catch (NoSuchFieldError var2) {
				;
			}

			try {
				field_180356_a[Direction.EAST.ordinal()] = 6;
			} catch (NoSuchFieldError var1) {
				;
			}
		}
	}
}
