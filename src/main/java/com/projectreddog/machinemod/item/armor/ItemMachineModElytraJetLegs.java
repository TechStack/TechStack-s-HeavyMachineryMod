package com.projectreddog.machinemod.item.armor;

import java.util.List;

import javax.annotation.Nullable;

import com.projectreddog.machinemod.model.armor.ModelElytraJetLegs;
import com.projectreddog.machinemod.render.armor.RenderElytraJetAlegs;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMachineModElytraJetLegs extends ItemMachineModArmor {
	public String registryName = "elytrajetleg";
	public static final int MaxFuel = 15000;

	public ItemMachineModElytraJetLegs(ArmorMaterial material, EntityEquipmentSlot armorType) {
		super(material, 1, armorType);
		this.setMaxDamage(MaxFuel);
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);
		this.maxStackSize = 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Equip on your waist and sneak while flying to activate!");
		tooltip.add("Fill up with Fuel in Fuel Pump.");

		tooltip.add("\u00A7aRemaining Fuel: " + (MaxFuel - stack.getItemDamage()) + "/" + MaxFuel);

	}

	@Override
	@SideOnly(Side.CLIENT)
	@Nullable
	public ModelBiped getArmorModel(EntityLivingBase living, ItemStack stack, EntityEquipmentSlot slot, ModelBiped defaultModel) {
		if (!stack.isEmpty()) {
			if (stack.getItem() instanceof ItemArmor) {
				if (EntityEquipmentSlot.LEGS == slot) {
					ModelElytraJetLegs armorModel = RenderElytraJetAlegs.elytraJetLegsModel;

					armorModel.isRiding = defaultModel.isRiding;
					armorModel.isChild = defaultModel.isChild;
					armorModel.isElytraFlying = living.isElytraFlying();

					return armorModel;
				}
			}
		}
		return null;
	}

	private int timealive;

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		boolean boostActive = player.isSneaking();
		int remaningFuel = MaxFuel - itemStack.getItemDamage();
		// check for 1 so it will not go below 1 . Ensure it is not broken!
		if (remaningFuel <= 0) {
			boostActive = false;
		}
		if (boostActive) {
			timealive++;
			if (player.isElytraFlying()) {

				Vec3d vec3d = player.getLookVec();
				double d0 = 1.5D;
				double d1 = 0.1D;
				double preX = player.motionX;
				double preZ = player.motionZ;

				player.motionX += vec3d.x * 0.1D + (vec3d.x * 1.5D - player.motionX) * 0.5D;
				player.motionY += vec3d.y * 0.1D + (vec3d.y * 1.5D - player.motionY) * 0.5D;
				player.motionZ += vec3d.z * 0.1D + (vec3d.z * 1.5D - player.motionZ) * 0.5D;

				if (world.isRemote && (timealive % 5) == 0) {
					world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, player.posX, player.posY + 0.4D, player.posZ, 0, 0, 0);
				}
				remaningFuel--;
				itemStack.setItemDamage(MaxFuel - remaningFuel);
			}

		}
	}

}
