package com.projectreddog.machinemod.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.projectreddog.machinemod.creativetab.CreativeTabMachineMod;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityConveyor;

public class BlockMachineModConveyor extends BlockContainer {

	protected BlockMachineModConveyor(Material material) {
		super(material);

		// can override later ;)
		this.setCreativeTab(CreativeTabMachineMod.MACHINEMOD_TAB);

		// 1.8
		this.setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ":" + Reference.MODBLOCK_MACHINE_CONVEYOR);
		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);
		// this.setHardness(15f);// not sure on the hardness
		this.setStepSound(soundTypeMetal);
	}

	public BlockMachineModConveyor() {
		// Generic constructor (set to rock by default)
		this(Material.rock);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {

		// NEED TO return the TE here
		return new TileEntityConveyor();
	}
	@Override
	 public int getRenderType()
	    {
		// 3 for normal block 2 for TESR 1 liquid  -1 nothing ( like air)
	        return 3;
	    }

	
	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }
}
