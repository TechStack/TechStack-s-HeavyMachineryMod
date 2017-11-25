package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;

public class BlockMachineModBlastedStone extends BlockMachineModBlastedStoneBase {
	public static final PropertyEnum PROPERTYORE = PropertyEnum.create("ore", EnumVanillaOres.class);

	public BlockMachineModBlastedStone() {
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);
		this.setRegistryName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);

		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);
		// this.setHardness(15f);// not sure on the hardness
		this.setSoundType(SoundType.STONE);
		this.setHardness(1.5f);

	}

	@Override
	/**
	 * Get the damage value that this Block should drop
	 */
	public int damageDropped(IBlockState state) {

		return this.getMetaFromState(state);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumVanillaOres ore = EnumVanillaOres.byMetadata(meta);
		return this.getDefaultState().withProperty(PROPERTYORE, ore);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumVanillaOres ore = (EnumVanillaOres) state.getValue(PROPERTYORE);

		return ore.getMetadata();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { PROPERTYORE });
	}

	public static enum EnumVanillaOres implements IStringSerializable {
		STONE(0, "stone"), GRANITE(1, "granite"), DIORITE(2, "diorite"), ANDESITE(3, "andesite"), GOLD(4, "gold"), IRON(5, "iron"), COAL(6, "coal"), LAPIS(7, "lapis"), DIAMOND(8, "diamond"), REDSTONE(9, "redstone"), EMERALD(10, "emerald");

		public int getMetadata() {
			return this.meta;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static EnumVanillaOres byMetadata(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length) {
				meta = 0;
			}

			return META_LOOKUP[meta];
		}

		public String getName() {
			return this.name;
		}

		private final int meta;
		private final String name;
		private static final EnumVanillaOres[] META_LOOKUP = new EnumVanillaOres[values().length];

		private EnumVanillaOres(int i_meta, String i_name) {
			this.meta = i_meta;
			this.name = i_name;
		}

		static {
			for (EnumVanillaOres ore : values()) {
				META_LOOKUP[ore.getMetadata()] = ore;
			}
		}
	}

}
