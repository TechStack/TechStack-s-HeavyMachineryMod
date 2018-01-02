package com.projectreddog.machinemod.render.armor;

import com.projectreddog.machinemod.model.armor.ModelElytraJetLegs;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderElytraJetAlegs {
	public static final ModelElytraJetLegs elytraJetLegsModel = new ModelElytraJetLegs();

	public static ResourceLocation getEntityTexture(Entity entity) {

		return new ResourceLocation("machinemod", Reference.MODEL_BULLDOZER_TEXTURE_LOCATION);
	}

}
