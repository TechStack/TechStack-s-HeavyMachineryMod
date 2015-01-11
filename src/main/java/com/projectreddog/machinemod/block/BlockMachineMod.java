package com.projectreddog.machinemod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.projectreddog.machinemod.creativetab.CreativeTabMachineMod;
import com.projectreddog.machinemod.reference.Reference;

public class BlockMachineMod  extends Block {

	protected BlockMachineMod(Material material) {
		super(material);

		//can override later ;)
		this.setCreativeTab(CreativeTabMachineMod.MACHINEMOD_TAB);


	}
	public BlockMachineMod() {
		//Generic constructor (set to rock by default)
		this(Material.rock);
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() +":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()) );
	}

// 1.8 not needed?	
//	@Override
//	@SideOnly(Side.CLIENT)
//	public void registerBlockIcons(IIconRegister iconRegister)
//	{
//		blockIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")+1));
//	}
//	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".")+1);
	}
	


}
