package com.projectreddog.machinemod.model;

import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.entity.EntityBagger;
import com.projectreddog.machinemod.reference.Reference;
import com.projectreddog.machinemod.utility.MachineModModelHelper;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class ModelBagger extends ModelTransportable {
	// fields

	public OBJModel objModel;
	private HashMap<String, IBakedModel> modelParts;

	public ModelBagger() {
		try {
			objModel = (OBJModel) OBJLoader.INSTANCE.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/bagger.obj"));
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

		// this.renderGroupObject("Cube.002");
		this.renderGroupObject("Cube.001");
		this.renderGroupObject("Cube");
		GL11.glTranslatef(0f, -3.955f, -5.f);
		this.renderGroupObject("Cab_Cube.002");
		GL11.glTranslatef(0f, -3.4f, 4f);

		if (entity != null) {

			GL11.glRotatef(((EntityBagger) entity).Attribute1, 1, 0, 0);
		}
		this.renderGroupObject("ARM_Cube.003");

		GL11.glTranslatef(0f, -0.25f, -12.75f);
		if (entity != null) {

			GL11.glRotatef(((EntityBagger) entity).Attribute2 * -1, 1, 0, 0);
		}

		// GL11.glTranslatef(0f, 1.2f, -1.2f);
		// GL11.glTranslatef(0f, 1.8f, -1.9f);
		// if (entity != null) {
		// if (((EntityLoader) entity).Attribute1 < -30) {
		// GL11.glRotatef((((EntityLoader) entity).Attribute1 + 30) * -2f, 1, 0, 0);
		// }
		// }

		this.renderGroupObject("Wheel_Cylinder");

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

		return new ResourceLocation("machinemod", Reference.MODEL_BAGGER_TEXTURE_LOCATION);
	}
}
