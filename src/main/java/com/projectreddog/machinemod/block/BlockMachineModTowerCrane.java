package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.MachineMod;
import com.projectreddog.machinemod.creativetab.CreativeTabMachineMod;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityAsphaltMixer;
import com.projectreddog.machinemod.utility.LogHelper;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockMachineModTowerCrane extends BlockContainer {

	protected BlockMachineModTowerCrane(Material material) {
		super(material);

		// can override later ;)
		this.setCreativeTab(CreativeTabMachineMod.MACHINEMOD_BLOCKS_TAB);
		// 1.8
		this.setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ":" + Reference.MODBLOCK_MACHINE_TOWER_CRANE);
		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);
		// this.setHardness(15f);// not sure on the hardness
		this.setStepSound(soundTypeAnvil);
		this.setHardness(15f);

	}

	public BlockMachineModTowerCrane() {
		// Generic constructor (set to rock by default)
		this(Material.rock);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {

		// NEED TO return the TE here
		return new TileEntityAsphaltMixer();
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
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, net.minecraft.entity.player.EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		int maxX=3;
		int maxY =3; 
		int maxZ = 3;
		for(int x=0;x < maxX; x++){
			for (int y =0; y<maxY; y++){
				for (int z = 0 ; z< maxZ; z++){
					BlockPos bp = new BlockPos(pos.getX()+x,pos.getY()+y,pos.getZ()+z);
					LogHelper.info( "x:" + x + " y:" + y +" z:" + z + " Block:" +  worldIn.getBlockState(bp).getBlock().getUnlocalizedName());
				}
			}
		}
		return true;
	}

}
