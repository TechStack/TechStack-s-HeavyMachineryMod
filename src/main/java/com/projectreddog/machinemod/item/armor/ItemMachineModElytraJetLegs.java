package com.projectreddog.machinemod.item.armor;

import javax.annotation.Nullable;

import com.projectreddog.machinemod.model.armor.ModelElytraJetLegs;
import com.projectreddog.machinemod.render.armor.RenderElytraJetAlegs;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
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
					armorModel.isElytraFlying = living.isElytraFlying();

					return armorModel;
				}
			}
		}
		return null;
	}

	private int timealive;

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return null;
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		boolean boostActive = player.isSneaking();

		if (boostActive) {
			timealive++;
			// LogHelper.info("elytraJet Active");

			// LogHelper.info("non Null bost entity");
			if (player.isElytraFlying()) {
				// LogHelper.info("Elytra flying");

				Vec3d vec3d = player.getLookVec();
				double d0 = 1.5D;
				double d1 = 0.1D;
				double preX = player.motionX;
				double preZ = player.motionZ;
				// this.boostedEntity.motionX += (vec3d.x * boostAmt);
				// this.boostedEntity.motionY += (vec3d.y * boostAmt);
				// this.boostedEntity.motionZ += (vec3d.z * boostAmt);

				player.motionX += vec3d.x * 0.1D + (vec3d.x * 1.5D - player.motionX) * 0.5D;
				player.motionY += vec3d.y * 0.1D + (vec3d.y * 1.5D - player.motionY) * 0.5D;
				player.motionZ += vec3d.z * 0.1D + (vec3d.z * 1.5D - player.motionZ) * 0.5D;

				// LogHelper.info("Pre X, Z :" + preX + " , " + preZ + "Post : " + boostedEntity.motionX + "," + boostedEntity.motionZ);

				if (world.isRemote && (timealive % 5) == 0) {
					world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, player.posX, player.posY + 0.4D, player.posZ, 0, 0, 0);
				}

			}

		}
	}

}
