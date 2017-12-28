package com.projectreddog.machinemod.item;

import com.projectreddog.machinemod.entity.EntityElytraJet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemElytraJetControl extends ItemMachineMod {
	public String registryName = "elytrajetcontroller";

	public ItemElytraJetControl() {
		super();
		this.setUnlocalizedName(registryName);
		this.setRegistryName(registryName);

		this.maxStackSize = 64;

	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (playerIn.isElytraFlying()) {
			ItemStack itemstack = playerIn.getHeldItem(handIn);

			if (!worldIn.isRemote) {
				EntityElytraJet entityElytraJet = new EntityElytraJet(worldIn);
				entityElytraJet.posX = playerIn.posX;
				entityElytraJet.posY = playerIn.posY;
				entityElytraJet.posZ = playerIn.posZ;

				entityElytraJet.setBoostedEntity(playerIn);
				entityElytraJet.ActivateBoost();
				worldIn.spawnEntity(entityElytraJet);

				if (!playerIn.capabilities.isCreativeMode) {
					// itemstack.shrink(1);
				}
			}

			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
		} else {
			return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
		}
	}

}
