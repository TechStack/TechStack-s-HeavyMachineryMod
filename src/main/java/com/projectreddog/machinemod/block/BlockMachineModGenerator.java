package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.creativetab.CreativeTabMachineMod;
import com.projectreddog.machinemod.init.ModItems;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.tileentities.TileEntityGenerator;

import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IProperty;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.BlockStateContainer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockMachineModGenerator extends ContainerBlock {
	public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);

	protected BlockMachineModGenerator(Material material) {
		super(Block.Properties.create(Material.IRON).hardnessAndResistance(1.5f).sound(SoundType.METAL));
		// can override later ;)
		this.setCreativeTab(CreativeTabMachineMod.MACHINEMOD_BLOCKS_TAB);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, Direction.NORTH));

		// 1.8
		// REMOVED 1.14
		// this.setUnlocalizedName(Reference.MOD_ID.toLowerCase() + ":" +
		// Reference.MODBLOCK_MACHINE_GENERATOR);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_GENERATOR);

	}

	/**
	 * Possibly modify the given BlockState before rendering it on an Entity (Minecarts, Endermen, ...)
	 */
	@OnlyIn(Dist.CLIENT)
	public BlockState getStateForEntityRender(BlockState state) {
		return this.getDefaultState().withProperty(FACING, Direction.SOUTH);
	}

	public BlockMachineModGenerator() {
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
		return new TileEntityGenerator();
	}

	@Override
	public boolean isOpaqueCube(BlockState state) {
		return false;
	}

	public BlockState onBlockPlaced(World worldIn, BlockPos pos, Direction facing, float hitX, float hitY, float hitZ, int meta, LivingEntity placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

		if (stack.hasDisplayName()) {
			TileEntity tileentity = worldIn.getTileEntity(pos);

			if (tileentity instanceof FurnaceTileEntity) {
				((FurnaceTileEntity) tileentity).setCustomInventoryName(stack.getDisplayName());
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
		return this.getDefaultState().withProperty(FACING, Direction);
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(BlockState state) {
		return ((Direction) state.getValue(FACING)).getIndex();
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	@OnlyIn(Dist.CLIENT)
	static final class SwitchDirection {
		static final int[] field_180356_a = new int[Direction.values().length];
		private static final String __OBFID = "CL_00002111";

		static {
			try {
				field_180356_a[Direction.WEST.ordinal()] = 1;
			} catch (NoSuchFieldError var4) {
				;
			}

			try {
				field_180356_a[Direction.EAST.ordinal()] = 2;
			} catch (NoSuchFieldError var3) {
				;
			}

			try {
				field_180356_a[Direction.NORTH.ordinal()] = 3;
			} catch (NoSuchFieldError var2) {
				;
			}

			try {
				field_180356_a[Direction.SOUTH.ordinal()] = 4;
			} catch (NoSuchFieldError var1) {
				;
			}
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, BlockState state, PlayerEntity playerIn, EnumHand hand, Direction side, float hitX, float hitY, float hitZ) {
		ItemStack heldItem = playerIn.getActiveItemStack();
		TileEntity te = worldIn.getTileEntity(pos);
		if (te != null && !playerIn.isSneaking()) {
			ItemStack playerItem = playerIn.getHeldItem(EnumHand.MAIN_HAND);

			if (playerItem != null) {
				if (playerItem.getItem() == ModItems.fuelcan && playerItem.getItemDamage() < playerItem.getMaxDamage()) {

					// put its a fuel can and has fuel !

					if (te instanceof TileEntityGenerator) {
						TileEntityGenerator tEPC = (TileEntityGenerator) te;
						if (tEPC.fuelStorage < tEPC.maxFuelStorage) {
							// can hold more fuel.
							// calc remaining fuel in can see if it is = or >
							// than the remaining fuel storage of this machine
							int amountInCan = (playerIn.getHeldItem(EnumHand.MAIN_HAND).getMaxDamage() - playerIn.getHeldItem(EnumHand.MAIN_HAND).getItemDamage());
							int roomInEntityTank = tEPC.maxFuelStorage - tEPC.fuelStorage;
							if (amountInCan > roomInEntityTank) {

								playerIn.getHeldItem(EnumHand.MAIN_HAND).setItemDamage(playerIn.getHeldItem(EnumHand.MAIN_HAND).getMaxDamage() - (amountInCan - roomInEntityTank));
								// will fill machine completely !
								tEPC.fuelStorage = tEPC.maxFuelStorage;
							} else {
								// can will be empty becuase entity can hold
								// 100% of the fuel from the can :O
								playerIn.getHeldItem(EnumHand.MAIN_HAND).setItemDamage(playerIn.getHeldItem(EnumHand.MAIN_HAND).getMaxDamage());
								tEPC.fuelStorage = tEPC.fuelStorage + amountInCan;
							}
						}
					}
				} else {
					// it was not a fuel can or it was empty so open gui !
					// playerIn.openGui(MachineMod.instance,
					// Reference.GUI_PRIMARY_CRUSHER, worldIn, pos.getX(),
					// pos.getY(), pos.getZ());
					return true;
				}
			} else {
				// no item in hand so open gui!
				// playerIn.openGui(MachineMod.instance,
				// Reference.GUI_PRIMARY_CRUSHER, worldIn, pos.getX(),
				// pos.getY(), pos.getZ());
				return true;

			}
			return true;
		} else {
			return false;
		}
	}

}
