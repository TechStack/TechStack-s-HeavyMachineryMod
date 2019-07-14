package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.MachineMod;
import com.projectreddog.machinemod.creativetab.CreativeTabMachineMod;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityAssemblyTable;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMachineAssemblyTable extends BlockContainer {

	public BlockMachineAssemblyTable() {
		super(Material.IRON);
		// 1.8
		// REMOVED 1.14
		// this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setCreativeTab(CreativeTabMachineMod.MACHINEMOD_BLOCKS_TAB);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_ASSEMBLY_TABLE);
		this.setHardness(15f);// not sure on the hardness
		this.setSoundType(SoundType.METAL);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return new TileEntityAssemblyTable();
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		// 3 for normal block 2 for TESR 1 liquid -1 nothing ( like air)
		// return 3;
		return BlockRenderType.MODEL;

	}

	@Override
	public String getUnlocalizedName() {
		return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, BlockState state, PlayerEntity playerIn, Hand hand, Direction side, float hitX, float hitY, float hitZ) {
		ItemStack heldItem = playerIn.getActiveItemStack();
		TileEntity te = worldIn.getTileEntity(pos);
		if (te != null && !playerIn.isSneaking()) {
			playerIn.openGui(MachineMod.instance, Reference.GUI_ASSEMBLY_TABLE, worldIn, pos.getX(), pos.getY(), pos.getZ());
			return true;
		} else {

			return false;
		}
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, BlockState state) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof IInventory) {
			InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileentity);
			worldIn.updateComparatorOutputLevel(pos, this);
		}

		super.breakBlock(worldIn, pos, state);
	}
}
