package com.projectreddog.machinemod.item.armor;

import com.projectreddog.machinemod.creativetab.CreativeTabMachineMod;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.util.EnumHelper;

public class ItemMachineModArmor extends ItemArmor {

	public static ArmorMaterial MachineFuleConsumerMaterial = EnumHelper.addArmorMaterial("MachineFuel", "iron", 0, new int[] { 0, 0, 0, 0 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0);

	public static ArmorMaterial SteelMaterial = EnumHelper.addArmorMaterial("Steel", "iron", 60, new int[] { 2, 6, 5, 2 }, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0);
	// public static ArmorMaterial addArmorMaterial(String name, String
	// textureName, int durability, int[] reductionAmounts, int enchantability,
	// SoundEvent soundOnEquip, float toughness)

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
