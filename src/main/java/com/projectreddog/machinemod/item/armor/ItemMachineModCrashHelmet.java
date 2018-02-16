package com.projectreddog.machinemod.item.armor;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMachineModCrashHelmet extends ItemMachineModArmor {
	public String registryName = "crashhelmet";

	public ItemMachineModCrashHelmet(ArmorMaterial material, EntityEquipmentSlot armorType) {
		super(material, 1, armorType);
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);
		this.maxStackSize = 1;

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Equip on your head to protect your noggin during flight!");
	}
}
