package com.projectreddog.machinemod.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.projectreddog.machinemod.creativetab.CreativeTabMachineMod;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityDrilingRig;

public class BlockMachineModDrillingRig extends BlockContainer {
	
	protected BlockMachineModDrillingRig(Material material) {
		super(material);

		//can override later ;)
		this.setCreativeTab(CreativeTabMachineMod.MACHINEMOD_TAB);

		//1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_DRILLING_RIG);
//		this.setBlockTextureName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);
		//this.setHardness(15f);// not sure on the hardness
		this.setStepSound(soundTypeStone);
	}
	public BlockMachineModDrillingRig() {
		//Generic constructor (set to rock by default)
		this(Material.rock);
	}
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		
		// NEED TO return the TE here
		return new TileEntityDrilingRig ();
	}
	

}
