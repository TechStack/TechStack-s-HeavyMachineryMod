package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.creativetab.CreativeTabMachineMod;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityBatteryBank;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.BlockStateContainer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMachineModBatteryBank extends BlockContainer {
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	protected BlockMachineModBatteryBank(Material material) {
		super(material);

		// can override later ;)
		this.setCreativeTab(CreativeTabMachineMod.MACHINEMOD_BLOCKS_TAB);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));

		// 1.8
		// REMOVED 1.14
		// this.setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ":" +
		// Reference.MODBLOCK_MACHINE_BATTERY_BANK);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_BATTERY_BANK);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);
		// this.setHardness(15f);// not sure on the hardness
		this.setSoundType(SoundType.METAL);
		this.setHardness(1.5f);

	}

	/**
	 * Possibly modify the given BlockState before rendering it on an Entity (Minecarts, Endermen, ...)
	 */
	@SideOnly(Side.CLIENT)
	public BlockState getStateForEntityRender(BlockState state) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
	}

	public BlockMachineModBatteryBank() {
		// Generic constructor (set to rock by default)
		this(Material.IRON);
	}

	@Override
	public EnumBlockRenderType getRenderType(BlockState state) {
		// 3 for normal block 2 for TESR 1 liquid -1 nothing ( like air)
		// return 3;
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {

		// NEED TO return the TE here
		return new TileEntityBatteryBank();
	}

	@Override
	public boolean isOpaqueCube(BlockState state) {
		return false;
	}

	public BlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

		if (stack.hasDisplayName()) {
			TileEntity tileentity = worldIn.getTileEntity(pos);

			if (tileentity instanceof TileEntityFurnace) {
				((TileEntityFurnace) tileentity).setCustomInventoryName(stack.getDisplayName());
			}
		}
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public BlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);
		if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
			enumfacing = EnumFacing.NORTH;
		}
		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(BlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@SideOnly(Side.CLIENT)
	static final class SwitchEnumFacing {
		static final int[] field_180356_a = new int[EnumFacing.values().length];
		private static final String __OBFID = "CL_00002111";

		static {
			try {
				field_180356_a[EnumFacing.WEST.ordinal()] = 1;
			} catch (NoSuchFieldError var4) {
				;
			}

			try {
				field_180356_a[EnumFacing.EAST.ordinal()] = 2;
			} catch (NoSuchFieldError var3) {
				;
			}

			try {
				field_180356_a[EnumFacing.NORTH.ordinal()] = 3;
			} catch (NoSuchFieldError var2) {
				;
			}

			try {
				field_180356_a[EnumFacing.SOUTH.ordinal()] = 4;
			} catch (NoSuchFieldError var1) {
				;
			}
		}
	}
}
