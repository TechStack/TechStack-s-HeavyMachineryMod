package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.creativetab.CreativeTabMachineMod;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityConduit;

import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.Direction;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.BlockStateContainer;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMachineModConduit extends ContainerBlock {

	public static final PropertyBool UP = PropertyBool.create("up");

	public static final PropertyBool DOWN = PropertyBool.create("down");

	public static final PropertyBool NORTH = PropertyBool.create("north");

	public static final PropertyBool SOUTH = PropertyBool.create("south");

	public static final PropertyBool EAST = PropertyBool.create("east");

	public static final PropertyBool WEST = PropertyBool.create("west");

	protected BlockMachineModConduit(Material material) {
		super(material);

		// can override later ;)
		this.setCreativeTab(CreativeTabMachineMod.MACHINEMOD_BLOCKS_TAB);
		this.setDefaultState(this.blockState.getBaseState().withProperty(UP, false).withProperty(DOWN, false).withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false));

		// 1.8
		// REMOVED 1.14
		// this.setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ":" +
		// Reference.MODBLOCK_MACHINE_CONDUIT);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_CONDUIT);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);
		// this.setHardness(15f);// not sure on the hardness
		this.setSoundType(SoundType.METAL);
		this.setHardness(1.5f);

	}

	@Deprecated
	public boolean isFullCube(BlockState state) {
		return false;
	}

	/**
	 * Possibly modify the given BlockState before rendering it on an Entity
	 * (Minecarts, Endermen, ...)
	 */
	@SideOnly(Side.CLIENT)
	public BlockState getStateForEntityRender(BlockState state) {
		return this.getDefaultState().withProperty(UP, false).withProperty(DOWN, false).withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false);
	}

	public BlockMachineModConduit() {
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
		return new TileEntityConduit();
	}

	@Override
	public boolean isOpaqueCube(BlockState state) {
		return false;
	}

	public BlockState onBlockPlaced(World worldIn, BlockPos pos, Direction facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(UP, false).withProperty(DOWN, false).withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false);
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(UP, false).withProperty(DOWN, false).withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false), 3);

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
		Direction Direction = Direction.getFront(meta);
		if (Direction.getAxis() == Direction.Axis.Y) {
			Direction = Direction.NORTH;
		}
		return this.getDefaultState().withProperty(UP, false).withProperty(DOWN, false).withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false);
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(BlockState state) {
		return 0;
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { UP, DOWN, NORTH, EAST, SOUTH, WEST });
	}

	public boolean canConnect(IBlockAccess world, BlockPos pos, Direction ef) {
		TileEntity te = world.getTileEntity(pos.offset(ef));
		if (te != null) {
			if (te.hasCapability(CapabilityEnergy.ENERGY, ef.getOpposite())) {
				// has the cap
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}

	}

	public BlockState getActualState(BlockState state, IBlockAccess world, BlockPos pos) {

		return state.withProperty(NORTH, canConnect(world, pos, Direction.NORTH)).withProperty(EAST, canConnect(world, pos, Direction.EAST)).withProperty(SOUTH, canConnect(world, pos, Direction.SOUTH)).withProperty(WEST, canConnect(world, pos, Direction.WEST)).withProperty(UP, canConnect(world, pos, Direction.UP)).withProperty(DOWN, canConnect(world, pos, Direction.DOWN));

	}

}
