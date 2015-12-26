package com.projectreddog.machinemod.model;

import com.projectreddog.machinemod.entity.EntitySemiTractor;
import com.projectreddog.machinemod.item.trailer.ItemSemiTrailerCargo;
import com.projectreddog.machinemod.item.trailer.ItemSemiTrailerFlatBed;
import com.projectreddog.machinemod.item.trailer.ItemSemiTrailerLivestock;
import com.projectreddog.machinemod.item.trailer.ItemSemiTrailerTanker;
import com.projectreddog.machinemod.model.advanced.AdvancedModelLoader;
import com.projectreddog.machinemod.model.advanced.IModelCustom;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ModelSemiTractor extends ModelBase {
	// fields
	private IModelCustom myModel;

	public ModelSemiTractor() {

		// LogHelper.info("LOADING dump truck MODEL!");
		myModel = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/semitractor.obj"));
		// casinoTexture = new ResourceLocation("modid",
		// "textures/casinoTexture.png");

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		// myModel.renderAll();
		this.renderGroupObject("SemiTractor_Cube");

		EntitySemiTractor eDT = ((EntitySemiTractor) entity);
		ItemStack is = eDT.getStackInSlot(0);
		if (is != null) {

			if (is.getItem() instanceof ItemSemiTrailerCargo) {
				this.renderGroupObject("SemiTrailer_Cube.006");
			} else if (is.getItem() instanceof ItemSemiTrailerLivestock) {
				this.renderGroupObject("AnimalTrailer_Cube.003");
			} else if (is.getItem() instanceof ItemSemiTrailerTanker) {
				this.renderGroupObject("TankerTailer_Cube.002");
			} else if (is.getItem() instanceof ItemSemiTrailerFlatBed) {
				this.renderGroupObject("FlatBedTrailer_Cube.001");
			}
		}
		//

		//

		// this.renderGroupObject("TankerTailer_Cube.002");

		// this.renderGroupObject("FlatBedTrailer_Cube.001");

	}

	public void renderGroupObject(String groupName) {
		myModel.renderPart(groupName);

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}

	protected ResourceLocation getTexture() {

		return new ResourceLocation("machinemod", Reference.MODEL_SEMI_TEXTURE_LOCATION);
	}

}
