package com.projectreddog.machinemod.block;

import java.util.Random;

import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.reference.Reference;

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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMachineBleakInfusedCrystal extends BlockMachineMod {
	public static final PropertyInteger AMTINFUSED = PropertyInteger.create("amtinfused", 0, 15);

	public BlockMachineBleakInfusedCrystal() {
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_INFUSED_CRYSTAL);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_INFUSED_CRYSTAL);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setHardness(0.0F);// not sure on the hardness
		this.setCreativeTab((CreativeTabs) null);

		this.setSoundType(SoundType.GLASS);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack heldItem = playerIn.getActiveItemStack();
		if (heldItem.getItem() == Items.EXPERIENCE_BOTTLE) {

			return true;
		} else {

			return false;
		}
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { AMTINFUSED });
	}

	protected Item getSelf() {
		return ModItems.bleakcrystal;
	}

	protected Item getInfused() {
		return ModItems.bleakcrystal;
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state) {
		return ((Integer) state.getValue(AMTINFUSED)).intValue();
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(AMTINFUSED, Integer.valueOf(meta));
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ((Integer) state.getValue(AMTINFUSED)).intValue() == 15 ? this.getInfused() : this.getSelf();
	}

	@SideOnly(Side.CLIENT)
	public Item getItem(World worldIn, BlockPos pos) {
		return this.getSelf();
	}

	@Override
	public java.util.List<ItemStack> getDrops(net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		java.util.List<ItemStack> ret = super.getDrops(world, pos, state, fortune);
		int amtinfused = ((Integer) state.getValue(AMTINFUSED)).intValue();
		Random rand = world instanceof World ? ((World) world).rand : new Random();

		if (amtinfused >= 15) {
			int k = 3 + fortune;

			for (int i = 0; i < 5 + fortune; ++i) {
				if (rand.nextInt(15) <= amtinfused) {
					ret.add(new ItemStack(this.getSelf(), 1, 0));
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

}
