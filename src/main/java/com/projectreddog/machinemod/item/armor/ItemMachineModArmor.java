package com.projectreddog.machinemod.item.armor;

import com.projectreddog.machinemod.creativetab.CreativeTabMachineMod;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemMachineModArmor extends ItemArmor {

	public ItemMachineModArmor(ItemArmor.ArmorMaterial material, int renderIndexIn, EntityEquipmentSlot armorType) {
		super(material, renderIndexIn, armorType);
		// can override later ;)
		this.setCreativeTab(CreativeTabMachineMod.MACHINEMOD_ITEMS_TAB);

	}

	@Override
	public String getUnlocalizedName() {
		return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	// 1.8
	// @Override
	// @SideOnly(Side.CLIENT)
	// public void registerIcons(IIconRegister iconRegister)
	// {
	// itemIcon =
	// iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".")+1));
	// }

	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}
