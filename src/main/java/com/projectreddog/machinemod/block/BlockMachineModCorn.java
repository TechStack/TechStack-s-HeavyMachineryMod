package com.projectreddog.machinemod.block;

import java.util.Random;

import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.BlockStateContainer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMachineModCorn extends BlockBush implements IGrowable {
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 6);

	public BlockMachineModCorn() {
		super();
		// 1.8
		// REMOVED 1.14
		// this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_CORN);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_CORN);

		this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
		this.setTickRandomly(true);
		float f = 0.5F;
		// TODO Find bounds fix
		// this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F +
		// f);
		this.setCreativeTab((CreativeTabs) null);
		this.setHardness(0.0F);
		this.setSoundType(SoundType.GROUND);// was grass
		this.disableStats();

	}

	/**
	 * is the block grass, dirt or farmland
	 */
	protected boolean canPlaceBlockOn(Block ground) {
		return ground == Blocks.FARMLAND;
	}

	public void updateTick(World worldIn, BlockPos pos, BlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);

		if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {
			int i = ((Integer) state.getValue(AGE)).intValue();

			if (i < 6) {
				float f = getGrowthChance(this, worldIn, pos);

				if (rand.nextInt((int) (25.0F / f) + 1) == 0) {
					worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(i + 1)), 3);
				}
			}
		}
	}

	public void growCrops(World worldIn, BlockPos p_176487_2_, BlockState p_176487_3_) {
		int i = ((Integer) p_176487_3_.getValue(AGE)).intValue() + MathHelper.getInt(worldIn.rand, 2, 4);

		if (i > 6) {
			i = 6;
		}

		worldIn.setBlockState(p_176487_2_, p_176487_3_.withProperty(AGE, Integer.valueOf(i)), 3);
	}

	protected static float getGrowthChance(Block p_180672_0_, World worldIn, BlockPos p_180672_2_) {
		float f = 1.0F;
		BlockPos blockpos1 = p_180672_2_.down();

		for (int i = -1; i <= 1; ++i) {
			for (int j = -1; j <= 1; ++j) {
				float f1 = 0.0F;
				BlockState BlockState = worldIn.getBlockState(blockpos1.add(i, 0, j));

				if (BlockState.getBlock().canSustainPlant(BlockState, worldIn, blockpos1.add(i, 0, j), net.minecraft.util.Direction.UP, (net.minecraftforge.common.IPlantable) p_180672_0_)) {
					f1 = 1.0F;

					if (BlockState.getBlock().isFertile(worldIn, blockpos1.add(i, 0, j))) {
						f1 = 3.0F;
					}
				}

				if (i != 0 || j != 0) {
					f1 /= 4.0F;
				}

				f += f1;
			}
		}

		BlockPos blockpos2 = p_180672_2_.north();
		BlockPos blockpos3 = p_180672_2_.south();
		BlockPos blockpos4 = p_180672_2_.west();
		BlockPos blockpos5 = p_180672_2_.east();
		boolean flag = p_180672_0_ == worldIn.getBlockState(blockpos4).getBlock() || p_180672_0_ == worldIn.getBlockState(blockpos5).getBlock();
		boolean flag1 = p_180672_0_ == worldIn.getBlockState(blockpos2).getBlock() || p_180672_0_ == worldIn.getBlockState(blockpos3).getBlock();

		if (flag && flag1) {
			f /= 2.0F;
		} else {
			boolean flag2 = p_180672_0_ == worldIn.getBlockState(blockpos4.north()).getBlock() || p_180672_0_ == worldIn.getBlockState(blockpos5.north()).getBlock() || p_180672_0_ == worldIn.getBlockState(blockpos5.south()).getBlock() || p_180672_0_ == worldIn.getBlockState(blockpos4.south()).getBlock();

			if (flag2) {
				f /= 2.0F;
			}
		}

		return f;
	}

	public boolean canBlockStay(World worldIn, BlockPos p_180671_2_, BlockState p_180671_3_) {
		return (worldIn.getLight(p_180671_2_) >= 8 || worldIn.canSeeSky(p_180671_2_)) && this.canPlaceBlockOn(worldIn.getBlockState(p_180671_2_.down()).getBlock());
	}

	protected Item getSeed() {
		return ModItems.cornseed;
	}

	protected Item getCrop() {
		return ModItems.cornseed;
	}

	/**
	 * Spawns this Block's drops into the World as ItemEntitys.
	 * 
	 * @param chance The chance that each Item is actually spawned (1.0 =
	 * always, 0.0 = never)
	 * @param fortune The player's fortune level
	 */
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, BlockState state, float chance, int fortune) {
		super.dropBlockAsItemWithChance(worldIn, pos, state, chance, 0);
	}

	/**
	 * Get the Item that this Block should drop when harvested.
	 * 
	 * @param fortune the level of the Fortune enchantment on the player's tool
	 */
	public Item getItemDropped(BlockState state, Random rand, int fortune) {
		return ((Integer) state.getValue(AGE)).intValue() == 6 ? this.getCrop() : this.getSeed();
	}

	public boolean isStillGrowing(World worldIn, BlockPos p_176473_2_, BlockState p_176473_3_, boolean p_176473_4_) {
		return ((Integer) p_176473_3_.getValue(AGE)).intValue() < 6;
	}

	public boolean canUseBonemeal(World worldIn, Random p_180670_2_, BlockPos p_180670_3_, BlockState p_180670_4_) {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public Item getItem(World worldIn, BlockPos pos) {
		return this.getSeed();
	}

	public void grow(World worldIn, Random p_176474_2_, BlockPos p_176474_3_, BlockState p_176474_4_) {
		this.growCrops(worldIn, p_176474_3_, p_176474_4_);
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public BlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(AGE, Integer.valueOf(meta));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(BlockState state) {
		return ((Integer) state.getValue(AGE)).intValue();
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { AGE });
	}

	@Override
	public java.util.List<ItemStack> getDrops(net.minecraft.world.IBlockAccess world, BlockPos pos, BlockState state, int fortune) {
		java.util.List<ItemStack> ret = super.getDrops(world, pos, state, fortune);
		int age = ((Integer) state.getValue(AGE)).intValue();
		Random rand = world instanceof World ? ((World) world).rand : new Random();

		if (age >= 6) {
			int k = 3 + fortune;

			for (int i = 0; i < 3 + fortune; ++i) {
				if (rand.nextInt(15) <= age) {
					ret.add(new ItemStack(this.getSeed(), 1, 0));
				}
			}
		}
		return ret;
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return ((Integer) state.getValue(AGE)).intValue() < 6;

	}
}
