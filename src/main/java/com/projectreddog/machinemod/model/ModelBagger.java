package com.projectreddog.machinemod.model;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.entity.EntityBagger;
import com.projectreddog.machinemod.model.advanced.AdvancedModelLoader;
import com.projectreddog.machinemod.model.advanced.IModelCustom;
import com.projectreddog.machinemod.reference.Reference;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ModelBagger extends ModelTransportable {
	// fields
	private IModelCustom myModel;

	public ModelBagger() {

		// LogHelper.info("LOADING dump truck MODEL!");
		myModel = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/bagger.obj"));
		// casinoTexture = new ResourceLocation("modid",
		// "textures/casinoTexture.png");

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		// myModel.renderAll();

		// this.renderGroupObject("Cube.002");
		this.renderGroupObject("Cube.001");
		this.renderGroupObject("Cube");
		GL11.glTranslatef(0f, -7.5f, -1f);
		this.renderGroupObject("ARM_Cube.003");
		GL11.glTranslatef(0f, 1.7f, -7f);
		this.renderGroupObject("Cab_Cube.002");

		GL11.glTranslatef(0f, -1.7f, -6f);
		if (entity != null) {

			GL11.glRotatef(((EntityBagger) entity).Attribute2, 1, 0, 0);
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

	public ResourceLocation getTexture() {

		return new ResourceLocation("machinemod", Reference.MODEL_LOADER_TEXTURE_LOCATION);
	}
}
