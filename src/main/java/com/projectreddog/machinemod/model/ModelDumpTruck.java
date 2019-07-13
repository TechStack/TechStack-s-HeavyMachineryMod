package com.projectreddog.machinemod.model;

import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.entity.EntityDumpTruck;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.MachineModModelHelper;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class ModelDumpTruck extends EntityModel {
	// fields

	public OBJModel objModel;
	private HashMap<String, IBakedModel> modelParts;

	public ModelDumpTruck() {

		try {
			objModel = (OBJModel) OBJLoader.INSTANCE.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/dumptruck.obj"));
			modelParts = MachineModModelHelper.getModelsForGroups(objModel);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		// myModel.renderAll();
		// this.renderGroupObject("Truck_Base_Cube.002");

		GL11.glTranslatef(0f, 0, -2.8f);

		this.renderGroupObject("Object.1");
		this.renderGroupObject("Object.2");
		this.renderGroupObject("Object.3");
		this.renderGroupObject("Object.4");
		this.renderGroupObject("Object.5");
		this.renderGroupObject("Object.6");
		this.renderGroupObject("Object.7");
		this.renderGroupObject("Object.8");
		this.renderGroupObject("Object.9");
		this.renderGroupObject("Object.10");
		this.renderGroupObject("Object.11");
		this.renderGroupObject("Object.12");
		this.renderGroupObject("Object.13");

		this.renderGroupObject("Object.15");
		this.renderGroupObject("Object.16");
		this.renderGroupObject("Object.17");
		this.renderGroupObject("Object.18");
		this.renderGroupObject("Object.19");
		this.renderGroupObject("Object.20");
		this.renderGroupObject("Object.21");
		this.renderGroupObject("Object.22");
		this.renderGroupObject("Object.23");
		this.renderGroupObject("Object.24");
		this.renderGroupObject("Object.25");
		this.renderGroupObject("Object.26");
		this.renderGroupObject("Object.27");
		this.renderGroupObject("Object.28");
		this.renderGroupObject("Object.29");
		this.renderGroupObject("Object.30");
		this.renderGroupObject("Object.31");
		this.renderGroupObject("Object.32");
		this.renderGroupObject("Object.33");
		this.renderGroupObject("Object.34");
		this.renderGroupObject("Object.35");
		this.renderGroupObject("Object.36");
		this.renderGroupObject("Object.000");
		this.renderGroupObject("Object.38");
		this.renderGroupObject("Object.39");

		this.renderGroupObject("Object.41");
		this.renderGroupObject("Object.042");
		this.renderGroupObject("Object.043");
		GL11.glTranslatef(0f, -1.95f, 7.9f);
		if (entity != null) {
			GL11.glRotatef(((EntityDumpTruck) entity).Attribute1, 1, 0, 0);
		}
		this.renderGroupObject("Object.40");
		// this.renderGroupObject("Bed_Cube.000");

	}

	public void renderGroupObject(String groupName) {
		MachineModModelHelper.renderBakedModel(modelParts.get(groupName));

	}

	private void setRotation(RendererModel model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
	}

	public ResourceLocation getTexture() {

		return new ResourceLocation("machinemod", Reference.MODEL_DUMPTRUCK_TEXTURE_LOCATION);
	}
}
