package com.projectreddog.machinemod.model;

import java.io.IOException;
import java.util.HashMap;

import com.projectreddog.machinemod.entity.EntitySemiTractor;
import com.projectreddog.machinemod.item.trailer.ItemSemiTrailerCargo;
import com.projectreddog.machinemod.item.trailer.ItemSemiTrailerFlatBed;
import com.projectreddog.machinemod.item.trailer.ItemSemiTrailerLivestock;
import com.projectreddog.machinemod.item.trailer.ItemSemiTrailerTanker;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.MachineModModelHelper;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class ModelSemiTractor extends ModelBase {
	// fields

	public OBJModel objModel;
	private HashMap<String, IBakedModel> modelParts;

	public ModelSemiTractor() {
		try {
			objModel = (OBJModel) OBJLoader.INSTANCE.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/semitractor.obj"));
			modelParts = MachineModModelHelper.getModelsForGroups(objModel);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		// myModel.renderAll();
		this.renderGroupObject("SemiTractor_Cube");

		EntitySemiTractor eDT = ((EntitySemiTractor) entity);
		ItemStack is = eDT.inventory.getStackInSlot(0);
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
		MachineModModelHelper.renderBakedModel(modelParts.get(groupName));

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
