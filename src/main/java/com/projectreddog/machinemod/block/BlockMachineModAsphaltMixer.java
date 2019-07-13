package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.MachineMod;
import com.projectreddog.machinemod.creativetab.CreativeTabMachineMod;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityAsphaltMixer;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMachineModAsphaltMixer extends BlockContainer {

	protected BlockMachineModAsphaltMixer(Material material) {
		super(material);

		// can override later ;)
		this.setCreativeTab(CreativeTabMachineMod.MACHINEMOD_BLOCKS_TAB);
		// 1.8
		// REMOVED 1.14
		// this.setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ":" +
		// Reference.MODBLOCK_MACHINE_ASPHALT_MIXER);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_ASPHALT_MIXER);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);
		// this.setHardness(15f);// not sure on the hardness
		this.setSoundType(SoundType.ANVIL);
		this.setHardness(15f);

	}

	public BlockMachineModAsphaltMixer() {
		// Generic constructor (set to rock by default)
		this(Material.ROCK);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {

		// NEED TO return the TE here
		return new TileEntityAsphaltMixer();
	}

	@Override

	public EnumBlockRenderType getRenderType(IBlockState state) {
		// 3 for normal block 2 for TESR 1 liquid -1 nothing ( like air)
		// return 3;
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof IInventory) {
			InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileentity);
			worldIn.updateComparatorOutputLevel(pos, this);
		}

		super.breakBlock(worldIn, pos, state);
	}

	@Override
	// public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState
	// state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack
	// heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	// public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState
	// state, net.minecraft.entity.player.EntityPlayer playerIn,EnumHand hand,
	// EnumFacing side, float hitX, float hitY, float hitZ) {

	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack heldItem = playerIn.getActiveItemStack();
		TileEntity te = worldIn.getTileEntity(pos);
		if (te != null && !playerIn.isSneaking()) {
			if (te instanceof TileEntityAsphaltMixer) {
				playerIn.openGui(MachineMod.instance, Reference.GUI_FRACTIONALDISTILLATION, worldIn, pos.getX(), pos.getY(), pos.getZ());

			}

			return true;
		} else {
			return false;
		}
	}

}
