package com.projectreddog.machinemod.block;

import java.util.Random;

import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMachineBleakCrystal extends BlockBush implements IGrowable {
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 6);

	public BlockMachineBleakCrystal() {
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_BLEAK_CRYSTAL);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_BLEAK_CRYSTAL);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setHardness(0.0F);// not sure on the hardness
		this.setCreativeTab((CreativeTabs) null);

		this.setSoundType(SoundType.GLASS);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack heldItem = playerIn.getActiveItemStack();
		if (heldItem.getItem() == Items.EXPERIENCE_BOTTLE) {
			worldIn.setBlockState(pos, ModBlocks.machineinfusedcrystal.getDefaultState());
			return true;
		} else {

			return false;
		}
	}

	@Override
	public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {

		if ((state.getValue(AGE)).intValue() == 6) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * is the block grass, dirt or farmland
	 */
	protected boolean canPlaceBlockOn(Block ground) {
		return ground == ModBlocks.machinebleakdirt;
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { AGE });
	}

	/**
	 * Spawns this Block's drops into the World as EntityItems.
	 * 
	 * @param chance  The chance that each Item is actually spawned (1.0 = always, 0.0 = never)
	 * @param fortune The player's fortune level
	 */
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
		super.dropBlockAsItemWithChance(worldIn, pos, state, chance, 0);
	}

	protected static float getGrowthChance(Block p_180672_0_, World worldIn, BlockPos p_180672_2_) {
		float f = 1.0F;
		BlockPos blockpos1 = p_180672_2_.down();

		for (int i = -1; i <= 1; ++i) {
			for (int j = -1; j <= 1; ++j) {
				float f1 = 0.0F;
				IBlockState iblockstate = worldIn.getBlockState(blockpos1.add(i, 0, j));

				if (iblockstate.getBlock().canSustainPlant(iblockstate, worldIn, blockpos1.add(i, 0, j), net.minecraft.util.EnumFacing.UP, (net.minecraftforge.common.IPlantable) p_180672_0_)) {

					f1 = 3.0F;

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

	@Override
	public boolean canBlockStay(World worldIn, BlockPos p_180671_2_, IBlockState p_180671_3_) {
		return worldIn.provider.getDimension() == Reference.BleakDimID && (worldIn.getLight(p_180671_2_) == 0 && this.canPlaceBlockOn(worldIn.getBlockState(p_180671_2_.down()).getBlock()));
	}

	protected Item getSeed() {
		return ModItems.bleakcrystal;
	}

	protected Item getCrop() {
		return ModItems.bleakcrystal;
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state) {
		return ((Integer) state.getValue(AGE)).intValue();
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(AGE, Integer.valueOf(meta));
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ((Integer) state.getValue(AGE)).intValue() == 6 ? this.getCrop() : this.getSeed();
	}

	public boolean isStillGrowing(World worldIn, BlockPos p_176473_2_, IBlockState p_176473_3_, boolean p_176473_4_) {
		return ((Integer) p_176473_3_.getValue(AGE)).intValue() < 6;
	}

	@SideOnly(Side.CLIENT)
	public Item getItem(World worldIn, BlockPos pos) {
		return this.getSeed();
	}

	@Override
	public java.util.List<ItemStack> getDrops(net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		java.util.List<ItemStack> ret = super.getDrops(world, pos, state, fortune);
		int age = ((Integer) state.getValue(AGE)).intValue();
		Random rand = world instanceof World ? ((World) world).rand : new Random();

		if (age >= 6) {
			int k = 3 + fortune;

			for (int i = 0; i < 5 + fortune; ++i) {
				if (rand.nextInt(15) <= age) {
					ret.add(new ItemStack(this.getSeed(), 1, 0));
				}
			}
		}
		return ret;
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks for render
	 */
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	/**
	 * Get the geometry of the queried face at the given position and state. This is used to decide whether things like buttons are allowed to be placed on the face, or how glass panes connect to the face, among other things.
	 * <p>
	 * Common values are {@code SOLID}, which is the default, and {@code UNDEFINED}, which represents something that does not fit the other descriptions and will generally cause other things not to connect to the face.
	 * 
	 * @return an approximation of the form of the given face
	 */

	@Override
	public String getUnlocalizedName() {
		return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		// TODO Auto-generated method stub
		return false;
	}

	public void growCrops(World worldIn, BlockPos p_176487_2_, IBlockState p_176487_3_) {
		int i = ((Integer) p_176487_3_.getValue(AGE)).intValue() + MathHelper.getInt(worldIn.rand, 2, 4);

		if (i > 6) {
			i = 6;
		}

		worldIn.setBlockState(p_176487_2_, p_176487_3_.withProperty(AGE, Integer.valueOf(i)), 3);
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		this.growCrops(worldIn, pos, state);

	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);

		if (worldIn.getLightFromNeighbors(pos.up()) == 0) {
			int i = ((Integer) state.getValue(AGE)).intValue();

			if (i < 6) {
				float f = getGrowthChance(this, worldIn, pos);

				if (rand.nextInt((int) (25.0F / f) + 1) == 0) {
					worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(i + 1)), 3);
				}
			}
		}
	}
}
