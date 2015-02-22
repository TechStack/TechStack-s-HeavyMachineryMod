package com.projectreddog.machinemod.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import com.projectreddog.machinemod.MachineMod;
import com.projectreddog.machinemod.creativetab.CreativeTabMachineMod;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityScreen;

public class BlockMachineModScreen extends BlockContainer {

	protected BlockMachineModScreen(Material material) {
		super(material);

		// can override later ;)
		this.setCreativeTab(CreativeTabMachineMod.MACHINEMOD_TAB);

		// 1.8
		this.setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ":" + Reference.MODBLOCK_MACHINE_SCREEN);
		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);
		// this.setHardness(15f);// not sure on the hardness
		this.setStepSound(soundTypeMetal);
		this.setHardness(1.5f);

	}

	public BlockMachineModScreen() {
		// Generic constructor (set to rock by default)
		this(Material.rock);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, net.minecraft.entity.player.EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (te != null && !playerIn.isSneaking()) {
			playerIn.openGui(MachineMod.instance, Reference.GUI_SCREEN, worldIn, pos.getX(), pos.getY(), pos.getZ());
			return true;
		} else {
			return false;
		}
	};

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {

		// NEED TO return the TE here
		return new TileEntityScreen();
	}

	@Override
	public int getRenderType() {
		// 3 for normal block 2 for TESR 1 liquid -1 nothing ( like air)
		return 3;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

}
