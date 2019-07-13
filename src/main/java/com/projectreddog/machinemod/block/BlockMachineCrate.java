package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.creativetab.CreativeTabMachineMod;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityCrate;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMachineCrate extends BlockContainer {

	public BlockMachineCrate() {
		// Generic constructor (set to rock by default)
		this(Material.WOOD);
	}

	protected BlockMachineCrate(Material material) {
		super(material);
		this.setCreativeTab(CreativeTabMachineMod.MACHINEMOD_BLOCKS_TAB);
		// REMOVED 1.14
		// this.setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ":" +
		// Reference.MODBLOCK_MACHINE_CRATE);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_CRATE);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setHardness(15f);// not sure on the hardness
		this.setSoundType(SoundType.WOOD);

	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {

		// NEED TO return the TE here
		return new TileEntityCrate();
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		// 3 for normal block 2 for TESR 1 liquid -1 nothing ( like air)
		// return 2;
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;

	}

	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (!worldIn.isRemote) {
			// server
			if (te != null && !playerIn.isSneaking()) {
				if (te instanceof TileEntityCrate) {

					TileEntityCrate crate = (TileEntityCrate) te;
					if (playerIn.getHeldItemMainhand().isEmpty()) {
						crate.removeStack(-1);// -1 for full stack to be done
												// later
					}
				}
			}
		}
	}

	@Override
	// public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState
	// state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float
	// hitX, float hitY, float hitZ)

	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		playerIn.getActiveItemStack();
		ItemStack heldItem = playerIn.getHeldItem(hand);
		TileEntity te = worldIn.getTileEntity(pos);
		if (!worldIn.isRemote) {
			// server
			if (te != null && !playerIn.isSneaking()) {
				if (te instanceof TileEntityCrate) {
					// its a crate
					TileEntityCrate crate = (TileEntityCrate) te;
					if (!heldItem.isEmpty()) {
						boolean result = crate.AddStack(heldItem);
						if (result) {
							// success we added it so remove from players
							// inventory
							playerIn.setHeldItem(hand, ItemStack.EMPTY);
						}
					}
					return true;
				}
			}
		}
		return true;
	};

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof TileEntityCrate) {
			((TileEntityCrate) tileentity).DropItemsOnBreak();
			worldIn.updateComparatorOutputLevel(pos, this);
		}

		super.breakBlock(worldIn, pos, state);
	}
}