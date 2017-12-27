package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.creativetab.CreativeTabMachineMod;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityLauncher;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMachineModLauncher extends BlockContainer {

	public BlockMachineModLauncher(Material material) {
		super(material);
		// 1.8
		this.setCreativeTab(CreativeTabMachineMod.MACHINEMOD_BLOCKS_TAB);
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_LAUNCHER);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_LAUNCHER);

		// this.setBlockName(Reference.MODBLOCK_MACHINE_DRILLED_STONE);
		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_DRILLED_STONE);
		// this.setHardness(15f);// not sure on the hardness
		this.setSoundType(SoundType.STONE);
		this.setHardness(1.5f);

	}

	public BlockMachineModLauncher() {
		// Generic constructor (set to rock by default)
		this(Material.ROCK);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {

		// NEED TO return the TE here
		return new TileEntityLauncher();
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

}
