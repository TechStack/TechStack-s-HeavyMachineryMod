package com.projectreddog.machinemod.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.projectreddog.machinemod.entity.EntityExcavator;
import com.projectreddog.machinemod.entity.EntityMachineModRideable;
import com.projectreddog.machinemod.model.advanced.AdvancedModelLoader;
import com.projectreddog.machinemod.model.advanced.IModelCustom;
import com.projectreddog.machinemod.reference.Reference;

public class ModelExcavator extends ModelTransportable {
	// fields
	private IModelCustom myModel;

	public ModelExcavator() {

		// LogHelper.info("LOADING dump truck MODEL!");
		myModel = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID.toLowerCase(), "models/excavator.obj"));
		// casinoTexture = new ResourceLocation("modid",
		// "textures/casinoTexture.png");

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		// myModel.renderAll();

		// render tracks before anything
		this.renderGroupObject("tracks_Cube.003");
		GL11.glPopMatrix();
		GL11.glPushMatrix();

		// rotate rest of machine to correct orentation (first)
		if (entity != null) {
			// GL11.glRotatef(180.0F - ((EntityMachineModRideable) entity).yaw , 0.0F, 1.0F, 0.0F);

			GL11.glRotatef((float) ((EntityExcavator) entity).mainBodyRotation, 0, 1, 0f);
		}
		this.renderGroupObject("body_Cube");
		GL11.glTranslatef(0f, -.8f, 1.4f);
		if (entity != null) {

			GL11.glRotatef(((EntityMachineModRideable) entity).Attribute1 * -1, 1, 0, 0f);

			// change to rotate
			// GL11.glTranslatef(0f, , 0f);
		}
		this.renderGroupObject("Arm1_Cube.002");

		GL11.glTranslatef(0f, -6, 0f);
		GL11.glRotatef(-90f, 1, 0, 0f);

		this.renderGroupObject("Arm2_Cube.001");

		// this.renderGroupObject("Wrecking_ball_Sphere");

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

		return new ResourceLocation("machinemod", Reference.MODEL_EXCAVATOR_TEXTURE_LOCATION);
	}

}
