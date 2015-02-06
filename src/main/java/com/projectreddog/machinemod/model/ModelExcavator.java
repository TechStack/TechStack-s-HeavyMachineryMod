package com.projectreddog.machinemod.model;

import net.minecraft.client.Minecraft;
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

		// rotate rest of machine to correct orentation (first)
		if (entity != null) {
			// (180.0F - ((EntityMachineModRideable) entity).yaw + )
			float calcRot = (float) (((EntityExcavator) entity).mainBodyRotation - 180 - ((EntityMachineModRideable) entity).yaw);
			// if (calcRot > 360) {
			// calcRot = 360 - calcRot;
			// } else if (calcRot < 0) {
			// calcRot = 360 + calcRot;
			// }
			GL11.glRotatef(calcRot, 0.0F, 1.0F, 0.0F);
			// GL11.glRotatef((float) ((EntityExcavator) entity).mainBodyRotation, 0, 1, 0f);
			if (((EntityExcavator) entity).riddenByEntity != null && ((EntityExcavator) entity).riddenByEntity == Minecraft.getMinecraft().thePlayer) {
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glColor4d(1, 1, 1, .50d);
			}
		}
		this.renderGroupObject("body_Cube");
		GL11.glTranslatef(0f, -.8f, EntityExcavator.armPiviotForward);
		if (entity != null) {

			GL11.glRotatef((float) ((EntityExcavator) entity).angleArm1 * -1, 1, 0, 0f);

			// change to rotate
			// GL11.glTranslatef(0f, , 0f);

		}

		this.renderGroupObject("Arm1_Cube.002");

		GL11.glTranslatef(0f, EntityExcavator.AmrLength * -1, 0f);
		GL11.glRotatef((float) ((EntityExcavator) entity).angleArm2 * -1, 1, 0, 0f);

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
