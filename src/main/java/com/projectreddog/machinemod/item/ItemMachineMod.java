package com.projectreddog.machinemod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.projectreddog.machinemod.creativetab.CreativeTabMachineMod;
import com.projectreddog.machinemod.reference.Reference;

public class ItemMachineMod extends Item {
// this is the wrapper class that will define the common attributes for the items used in this mod
// specific settings will be in held in classes that extend this class
  
	public ItemMachineMod()
	{
		super();
		//can override later ;)
		this.setCreativeTab(CreativeTabMachineMod.MACHINEMOD_TAB);

	}
	
	@Override
	public String getUnlocalizedName()
	{
		return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() +":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()) );
	}
	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() +":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()) );
	}
	//1.8
//	@Override
//	@SideOnly(Side.CLIENT)
//	public void registerIcons(IIconRegister iconRegister)
//	{
//		itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")+1));
//	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".")+1);
	}
}
