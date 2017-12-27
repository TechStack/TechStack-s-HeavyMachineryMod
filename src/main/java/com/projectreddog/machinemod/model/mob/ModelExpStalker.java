package com.projectreddog.machinemod.model.mob;

import java.io.IOException;
import java.util.HashMap;

import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.MachineModModelHelper;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class ModelExpStalker extends ModelBase {
	// fields

	public OBJModel objModel;
	private HashMap<String, IBakedModel> modelParts;

	public ModelExpStalker() {
		try {
			objModel = (OBJModel) OBJLoader.INSTANCE.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/expstalker.obj"));
			modelParts = MachineModModelHelper.getModelsForGroups(objModel);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		// render body
		this.renderGroupObject("Body_Cube");
		// render leg (tentacle) segments
		this.renderGroupObject("RFSeg1_Cube.016");
		this.renderGroupObject("RFSeg2_Cube.015");
		this.renderGroupObject("RBSeg2_Cube.014");
		this.renderGroupObject("RBSeg1_Cube.013");
		this.renderGroupObject("LFSeg1_Cube.012");
		this.renderGroupObject("LFSeg2_Cube.011");
		this.renderGroupObject("LBSeg2_Cube.010");
		this.renderGroupObject("LBSeg1_Cube.009");
		this.renderGroupObject("BRSeg1_Cube.008");
		this.renderGroupObject("BRSeg2_Cube.007");
		this.renderGroupObject("BLSeg2_Cube.006");
		this.renderGroupObject("BLSeg1_Cube.005");
		this.renderGroupObject("FRSeg1_Cube.004");
		this.renderGroupObject("FRSEg2_Cube.003");
		this.renderGroupObject("FLSeg2_Cube.002");
		this.renderGroupObject("FLSeg1_Cube.001");

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

	public ResourceLocation getTexture() {

		return new ResourceLocation("machinemod", Reference.MODEL_BAGGER_TEXTURE_LOCATION);
	}
}
