package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.MachineMod;
import com.projectreddog.machinemod.creativetab.CreativeTabMachineMod;
import com.projectreddog.machinemod.init.ModBlocks;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityExpCollector;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMachineExpCollector extends BlockContainer {
	public static final PropertyInteger SECTION = PropertyInteger.create("section", 0, 2);

	public BlockMachineExpCollector() {
		super(Material.IRON);
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_EXP_COLLECTOR);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_EXP_COLLECTOR);
		this.setCreativeTab(CreativeTabMachineMod.MACHINEMOD_BLOCKS_TAB);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setHardness(15f);// not sure on the hardness
		this.setSoundType(SoundType.METAL);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return new TileEntityExpCollector();
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		// 3 for normal block 2 for TESR 1 liquid -1 nothing ( like air)
		// return 3;
		return EnumBlockRenderType.MODEL;

	}

	/**
	 * Gets the {@link IBlockState} to place
	 * 
	 * @param world  The world the block is being placed in
	 * @param pos    The position the block is being placed at
	 * @param facing The side the block is being placed on
	 * @param hitX   The X coordinate of the hit vector
	 * @param hitY   The Y coordinate of the hit vector
	 * @param hitZ   The Z coordinate of the hit vector
	 * @param meta   The metadata of {@link ItemStack} as processed by {@link Item#getMetadata(int)}
	 * @param placer The entity placing the block
	 * @param hand   The player hand used to place this block
	 * @return The state to be placed in the world
	 */
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		if (world.getBlockState(pos.down()).getBlock() != ModBlocks.machineexpcollector) {
			return getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, 0, placer);
		} else if (world.getBlockState(pos.down()).getBlock() == ModBlocks.machineexpcollector && world.getBlockState(pos.down()).getBlock().getMetaFromState(world.getBlockState(pos.down())) == 0) {
			// block under this block is the base make this the center
			return getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, 1, placer);
		} else if (world.getBlockState(pos.down()).getBlock() == ModBlocks.machineexpcollector && world.getBlockState(pos.down()).getBlock().getMetaFromState(world.getBlockState(pos.down())) == 1) {
			// block under this block is the center make this the top
			return getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, 2, placer);
		} else if (world.getBlockState(pos.up()).getBlock() == ModBlocks.machineexpcollector && world.getBlockState(pos.up()).getBlock().getMetaFromState(world.getBlockState(pos.up())) == 2) {
			// block above is the top make this the center
			return getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, 1, placer);
		} else if (world.getBlockState(pos.up()).getBlock() == ModBlocks.machineexpcollector && world.getBlockState(pos.up()).getBlock().getMetaFromState(world.getBlockState(pos.up())) == 1) {
			// block above is the center make this the bottom
			return getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, 0, placer);
		} else {
			return getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer);
		}

	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { SECTION });
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta) {

		return this.getDefaultState().withProperty(SECTION, meta);
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state) {
		return ((Integer) state.getValue(SECTION)).intValue();
	}

	@Override
	public String getUnlocalizedName() {
		return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		// TODO give the xp

		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof IInventory) {
			InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileentity);
			worldIn.updateComparatorOutputLevel(pos, this);
		}

		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack heldItem = playerIn.getActiveItemStack();
		TileEntity te = worldIn.getTileEntity(pos);
		if (te != null && !playerIn.isSneaking()) {

			if (te instanceof TileEntityExpCollector) {
				TileEntityExpCollector teec = (TileEntityExpCollector) te;

				if (teec.isCenterBlock()) {
					playerIn.openGui(MachineMod.instance, Reference.GUI_EXP_COLLECTOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
					return true;
				} else if (teec.isBottomBlock()) {
					playerIn.openGui(MachineMod.instance, Reference.GUI_EXP_COLLECTOR, worldIn, pos.getX(), pos.up().getY(), pos.getZ());
					return true;
				} else if (teec.isTopBlock()) {
					playerIn.openGui(MachineMod.instance, Reference.GUI_EXP_COLLECTOR, worldIn, pos.getX(), pos.down().getY(), pos.getZ());
					return true;
				}

			}
			return false;

		} else {

			return false;
		}
	}

}
