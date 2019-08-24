package com.projectreddog.machinemod.block;

import java.util.Random;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IProperty;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraft.world.chunk.BlockStateContainer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockMachineMowedGrass extends BlockMachineMod {
	public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);

	public int intRandom = 0;

	public BlockMachineMowedGrass() {
		super(Block.Properties.create(Material.EARTH).hardnessAndResistance(.25f).sound(SoundType.GROUND));
		// 1.8
		// REMOVED 1.14
		// this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_MOWED_GRASS);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_MOWED_GRASS);

		this.setTickRandomly(true);

	}

	/**
	 * Possibly modify the given BlockState before rendering it on an Entity (Minecarts, Endermen, ...)
	 */
	@OnlyIn(Dist.CLIENT)
	public BlockState getStateForEntityRender(BlockState state) {
		return this.getDefaultState().withProperty(FACING, Direction.SOUTH);
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public BlockState getStateFromMeta(int meta) {
		Direction Direction = Direction.getFront(meta);

		if (Direction.getAxis() == Direction.Axis.Y) {
			Direction = Direction.NORTH;
		}

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

	@OnlyIn(Dist.CLIENT)
	static final class SwitchDirection {
		static final int[] field_180356_a = new int[Direction.values().length];
		private static final String __OBFID = "CL_00002111";

		static {
			try {
				field_180356_a[Direction.WEST.ordinal()] = 1;
			} catch (NoSuchFieldError var4) {
				;
			}

			try {
				field_180356_a[Direction.EAST.ordinal()] = 2;
			} catch (NoSuchFieldError var3) {
				;
			}

			try {
				field_180356_a[Direction.NORTH.ordinal()] = 3;
			} catch (NoSuchFieldError var2) {
				;
			}

			try {
				field_180356_a[Direction.SOUTH.ordinal()] = 4;
			} catch (NoSuchFieldError var1) {
				;
			}
		}
	}

	public void updateTick(World worldIn, BlockPos pos, BlockState state, Random rand) {

		if (worldIn.getLightFromNeighbors(pos.up()) < 4 && worldIn.getBlockState(pos.up()).getBlock().getLightOpacity(state, worldIn, pos.up()) > 2) {
			worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
		} else {
			intRandom = worldIn.rand.nextInt(100);
			if (intRandom >= 97) {
				worldIn.setBlockState(pos, Blocks.GRASS.getDefaultState());
			}
		}

	}

	/**
	 * Get the Item that this Block should drop when harvested.
	 * 
	 * @param fortune the level of the Fortune enchantment on the player's tool
	 */
	public Item getItemDropped(BlockState state, Random rand, int fortune) {
		return Blocks.DIRT.getItemDropped(Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT), rand, fortune);
	}

	@OnlyIn(Dist.CLIENT)
	public int getBlockColor() {
		return ColorizerGrass.getGrassColor(0.5D, 1.0D);
	}

	@OnlyIn(Dist.CLIENT)
	public int getRenderColor(BlockState state) {
		return this.getBlockColor();
	}

	@OnlyIn(Dist.CLIENT)
	public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
		return BiomeColorHelper.getGrassColorAtPos(worldIn, pos);
	}

	@OnlyIn(Dist.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	public BlockState onBlockPlaced(World worldIn, BlockPos pos, Direction facing, float hitX, float hitY, float hitZ, int meta, LivingEntity placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}

}
