package com.projectreddog.machinemod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockMachineMod extends Block {

	protected BlockMachineMod(Properties blockProperties) {
		super(blockProperties);

		// can override later ;)
		// TODO FIX creative tabs on blocks!
		// this.setCreativeTab(CreativeTabMachineMod.MACHINEMOD_BLOCKS_TAB);
	}

	public BlockMachineMod() {
		// Generic constructor (set to rock by default)
		this(Block.Properties.create(Material.ROCK));
	}
// 1.14 removed 
//	@Override
//	public String getTranslationKey() {
//		return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getTranslationKey()));
//	}

	// 1.8 not needed?
	// @Override
	// @OnlyIn(Dist.CLIENT)
	// public void registerBlockIcons(IIconRegister iconRegister)
	// {
	// blockIcon =
	// iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")+1));
	// }
	//
	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}

}
