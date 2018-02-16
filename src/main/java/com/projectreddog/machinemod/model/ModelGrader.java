package com.projectreddog.machinemod.model;

import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.MachineModModelHelper;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class ModelGrader extends ModelTransportable {
	// fields
	public OBJModel objModel;
	private HashMap<String, IBakedModel> modelParts;

	public ModelGrader() {
		try {
			objModel = (OBJModel) OBJLoader.INSTANCE.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/graderprototype.obj"));
			modelParts = MachineModModelHelper.getModelsForGroups(objModel);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		// renderGroupObject(MachineModModelHelper.ALL_PARTS);
		GL11.glTranslatef(0f, 0f, 4.00f);

		// renderGroupObject("Object.1");
		// renderGroupObject("Object.2");
		// renderGroupObject("Object.3");
		// renderGroupObject("Object.4");
		renderGroupObject("Cylinder");
		renderGroupObject("Cylinder.001");
		renderGroupObject("Cylinder.002");
		renderGroupObject("Cylinder.003");
		renderGroupObject("Cylinder.004");
		renderGroupObject("Cylinder.005");

		renderGroupObject("Object.5");
		renderGroupObject("Object.6");
		renderGroupObject("Object.7");
		renderGroupObject("Object.8");
		renderGroupObject("Object.9");
		// renderGroupObject("Object.10");
		// renderGroupObject("Object.11");
		renderGroupObject("Object.12");
		renderGroupObject("Object.13");
		renderGroupObject("Object.14");
		renderGroupObject("Object.15");
		renderGroupObject("Object.16");
		renderGroupObject("Object.17");
		renderGroupObject("Object.18");
		renderGroupObject("Object.19");
		renderGroupObject("Object.20");
		renderGroupObject("Object.21");
		renderGroupObject("Object.22");
		renderGroupObject("Object.23");
		renderGroupObject("Object.24");
		renderGroupObject("Object.25");
		renderGroupObject("Object.26");
		renderGroupObject("Object.27");
		renderGroupObject("Object.28");
		// renderGroupObject("Object.29"); // TOOD REMOVE ME was merged with 28
		renderGroupObject("Object.30");

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

		return new ResourceLocation("machinemod", Reference.MODEL_GRADER_TEXTURE_LOCATION);
	}
}
