package com.projectreddog.machinemod.item.armor;

import javax.annotation.Nullable;

import com.projectreddog.machinemod.model.armor.ModelElytraJetLegs;
import com.projectreddog.machinemod.render.armor.RenderElytraJetAlegs;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMachineModElytraJetLegs extends ItemMachineModArmor {
	public String registryName = "elytrajetleg";

	public ItemMachineModElytraJetLegs(ArmorMaterial material, EntityEquipmentSlot armorType) {
		super(material, 1, armorType);
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@Nullable
	public ModelBiped getArmorModel(EntityLivingBase living, ItemStack stack, EntityEquipmentSlot slot, ModelBiped defaultModel) {
		if (!stack.isEmpty()) {
			if (stack.getItem() instanceof ItemArmor) {
				if (EntityEquipmentSlot.LEGS == slot) {
					ModelElytraJetLegs armorModel = RenderElytraJetAlegs.elytraJetLegsModel;
					armorModel.isSneak = defaultModel.isSneak;
					armorModel.isRiding = defaultModel.isRiding;
					armorModel.isChild = defaultModel.isChild;

					return armorModel;
				}
			}
		}
		return null;
	}
}
