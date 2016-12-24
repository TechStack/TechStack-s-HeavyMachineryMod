package com.projectreddog.machinemod.block;

import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;

public class BlockMachineModBlastedStone2 extends BlockMachineModBlastedStoneBase {
	public static final PropertyEnum PROPERTYORE = PropertyEnum.create("ore", EnumModOres.class);

	public BlockMachineModBlastedStone2() {
		super();
		// 1.8
		this.setUnlocalizedName(Reference.MODBLOCK_MACHINE_BLASTED_STONE2);
		// this.setBlockTextureName(Reference.MODBLOCK_MACHINE_BLASTED_STONE);
		// this.setHardness(15f);// not sure on the hardness
		this.setSoundType(SoundType.STONE);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumModOres ore = EnumModOres.byMetadata(meta);
		return this.getDefaultState().withProperty(PROPERTYORE, ore);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumModOres ore = (EnumModOres) state.getValue(PROPERTYORE);

		return ore.getMetadata();
	}

	@Override
	/**
	 * Get the damage value that this Block should drop
	 */
	public int damageDropped(IBlockState state) {

		return this.getMetaFromState(state);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { PROPERTYORE });
	}

	public static enum EnumModOres implements IStringSerializable {
		COPPER(0, "copper"), TIN(1, "tin"), SILVER(2, "silver"), LEAD(3, "lead"), QUARTZ(4, "quartz"), RUBY(5, "ruby"), SAPPHIRE(6, "sapphire"), URANIUM(7, "uranium"), ALUMINUM(8, "aluminum");

		public int getMetadata() {
			return this.meta;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static EnumModOres byMetadata(int meta) {
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
		private static final EnumModOres[] META_LOOKUP = new EnumModOres[values().length];

		private EnumModOres(int i_meta, String i_name) {
			this.meta = i_meta;
			this.name = i_name;
		}

		static {
			for (EnumModOres ore : values()) {
				META_LOOKUP[ore.getMetadata()] = ore;
			}
		}
	}

}
