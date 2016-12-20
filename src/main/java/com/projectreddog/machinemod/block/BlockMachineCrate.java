package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityCrate;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMachineCrate extends BlockContainer {

	public BlockMachineCrate() {
		// Generic constructor (set to rock by default)
		this(Material.WOOD);
	}

	protected BlockMachineCrate(Material material) {
		super(material);
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_CRATE);
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
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof IInventory) {
			InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileentity);
			worldIn.updateComparatorOutputLevel(pos, this);
		}

		super.breakBlock(worldIn, pos, state);
	}
}